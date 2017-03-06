package lanou.com.gifttalk.activity.classifypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.classifypage.SpecificAdapter;
import lanou.com.gifttalk.bean.classifypage.IndividualityBean;
import lanou.com.gifttalk.bean.classifypage.SpecificBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.R.id.info;
import static lanou.com.gifttalk.R.id.iv_sp_function;

/**
 * Created by dllo on 17/3/4.
 */

public class SpecificAct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SpecificAdapter adapter;
    private IndividualityBean.DataBean.CategoriesBean.SubcategoriesBean bean;
    private ImageView back,function;
    private TextView title;
    private SpecificBean specificBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_layot);

        back= (ImageView) findViewById(R.id.iv_sp);
        function= (ImageView) findViewById(iv_sp_function);
        title= (TextView) findViewById(R.id.tv_sp);

        Intent intent=getIntent();
        bean = intent.getParcelableExtra("icon");

        title.setText(bean.getName());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapter =new SpecificAdapter(this);
        recyclerView= (RecyclerView) findViewById(R.id.rv_sp);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        String BASE_URL= "http://api.liwushuo.com/v2/item_subcategories/";
        String OTHER_URL="/items?limit=20&offset=0";

        ParserTool.getInstance().praser(BASE_URL + "" + bean.getId() + OTHER_URL, SpecificBean.class, new ParseMethod<SpecificBean>() {
            @Override
            public void onSucceed(SpecificBean something) {
                Log.d("SpecificAct", "something==null:" + (something == null));
                adapter.setBean(something);
                specificBean = something;
                adapter.notifyDataSetChanged();
            }
        });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState==RecyclerView.SCROLL_STATE_IDLE&&adapter.getPOSITION()+1==adapter.getItemCount()){

                    ParserTool.getInstance().praser(specificBean.getData().getPaging().getNext_url(), SpecificBean.class, new ParseMethod<SpecificBean>() {
                        @Override
                        public void onSucceed(SpecificBean something) {

                           adapter.setBean(something);
                            adapter.notifyDataSetChanged();
                        }
                    });


                }

            }

        });




















    }

}
