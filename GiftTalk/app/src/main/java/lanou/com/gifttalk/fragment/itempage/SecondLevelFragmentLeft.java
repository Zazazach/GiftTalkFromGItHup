package lanou.com.gifttalk.fragment.itempage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.itempage.SecondLevelLeftRvAdapter;
import lanou.com.gifttalk.bean.itempage.FloatBean;
import lanou.com.gifttalk.bean.itempage.ItemChildBean;

/**
 * Created by dllo on 17/2/24.
 */

public class SecondLevelFragmentLeft extends Fragment {

    private RecyclerView recyclerView;
    private SecondLevelLeftRvAdapter adapter;
    private ImageView floatSticky;
    private ItemChildBean.DataBean.ItemsBean itemsBean;
    private ItemChildBean bean;
    private int state;
    private float length=0f;

    public void setBean(ItemChildBean bean) {
        this.bean = bean;
    }

    public void setItemsBean(ItemChildBean.DataBean.ItemsBean itemsBean) {
        this.itemsBean = itemsBean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.secondlevel_rv,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_secondlevel);
        floatSticky= (ImageView) view.findViewById(R.id.iv_stick_float);
        adapter=new SecondLevelLeftRvAdapter(getContext());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter.setItemsBean(itemsBean);
        adapter.setBean(bean);

        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        //对滑动监听,并把滑动的y值通过EventBus传给Act
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                length=length+dy;
                Log.d("SecondLevelFragmentLeft", "length:" + length);
                Log.d("SecondLevelFragmentLeft", "100f/1000f:" + (length / 1000f));
                if (length<500f){
                    FloatBean floatBean=new FloatBean();
                    floatBean.setAlpha(length/500f);
                    EventBus.getDefault().post(floatBean);

                }
            }
        });


        //GirdLayoutmanager 占位处理
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position==0?2:1;
            }
        });


        //浮标 点击回到首位
        floatSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}
