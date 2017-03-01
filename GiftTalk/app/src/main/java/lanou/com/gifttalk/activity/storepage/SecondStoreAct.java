package lanou.com.gifttalk.activity.storepage;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import de.greenrobot.event.EventBus;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;
import lanou.com.gifttalk.adaptergroup.storepage.SecStoreRvAdapter;
import lanou.com.gifttalk.bean.itempage.FloatBean;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;

/**
 * Created by dllo on 17/2/27.
 */

public class SecondStoreAct extends BaseActivity {
    private RecyclerView recyclerView;
    private ImageView floatSticky;
    private SecStoreRvAdapter adapter;
    private float length = 0f;
    private ImageView floatbtn;


    @Override
    public int bindLayout() {
        return R.layout.secondstoreact_layout;
    }

    @Override
    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.rv_secstore);
        floatSticky= (ImageView) findViewById(R.id.iv_secstore_float);
        adapter=new SecStoreRvAdapter(this);
        floatbtn = (ImageView) findViewById(R.id.iv_secstore_float);

    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        StoreUpperBean.DataBean.ItemsBeanX toDetailBean=intent.getParcelableExtra("store");
        int interPostion=intent.getIntExtra("num",0);
        adapter.setToDetailBean(toDetailBean);
        adapter.setInterPosition(interPostion);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.iv_secstore_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int rvState;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                rvState=newState;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                length=length+dy;
                if (length<500f){
                    if (length/500f>0.5){
                        findViewById(R.id.rl_secstore).setAlpha(1);
                    }else {
                        findViewById(R.id.rl_secstore).setAlpha(length / 500f);
                    }
                }


                if (dy>0){
                    floatbtn.setVisibility(View.VISIBLE);
                }else {
                    floatbtn.setVisibility(View.INVISIBLE);
                }


            }
        });

        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            recyclerView.smoothScrollToPosition(0);


                dimBackground(1.0f,0.5f);
            }
        });







    }

    private void dimBackground(final float from, final float to) {
        final Window window = getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

        valueAnimator.start();
    }


}
