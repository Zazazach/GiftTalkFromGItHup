package lanou.com.gifttalk.activity.storepage;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;
import lanou.com.gifttalk.adaptergroup.storepage.PopRvAdapter;
import lanou.com.gifttalk.adaptergroup.storepage.SecStoreRvAdapter;
import lanou.com.gifttalk.bean.itempage.FloatBean;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;
import lanou.com.gifttalk.inter.ItemClicker;

/**
 * Created by dllo on 17/2/27.
 */

public class SecondStoreAct extends BaseActivity {
    private RecyclerView recyclerView;
    private ImageView floatSticky;
    private SecStoreRvAdapter adapter;
    private float length = 0f;
    private ImageView floatbtn;
    private PopupWindow popupWindow;
    private TextView purchase;
    private View view;
    private View pop;
    private int interPostion;
    private StoreUpperBean.DataBean.ItemsBeanX.ItemsBean thisBean;


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
        pop = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout,null);

        popupWindow=new PopupWindow(pop);

        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(900);


        purchase= (TextView) findViewById(R.id.tv_secstore_instance);
        view = LayoutInflater.from(this).inflate(R.layout.secondstoreact_layout,null);
    }

    @Override
    public void initData() {
        //接到对应position的bean
        Intent intent=getIntent();
        final StoreUpperBean.DataBean.ItemsBeanX toDetailBean=intent.getParcelableExtra("store");
        interPostion = intent.getIntExtra("num",0);
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


//                dimBackground(1.0f,0.5f);
            }
        });


        //底部弹出popupwindow
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.findViewById(R.id.iv_pop_pic);

                Glide.with(SecondStoreAct.this).load(thisBean.getSkus().get(0).getCover_image_url()).into((ImageView) pop.findViewById(R.id.iv_pop_pic));
                popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
            }
        });

        thisBean = toDetailBean.getItems().get(interPostion);
        pop.findViewById(R.id.iv_pop_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        pop.findViewById(R.id.iv_pop_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText num= (EditText) pop.findViewById(R.id.tv_pop_num);
                int number=Integer.valueOf(num.getText().toString());
                num.setText(""+(number+1));
            }
        });

        RecyclerView popRv= (RecyclerView) pop.findViewById(R.id.rv_pop_choice);
        PopRvAdapter rvAdapter=new PopRvAdapter(this);
        popRv.setLayoutManager(new GridLayoutManager(this,4,LinearLayoutManager.VERTICAL,false));
        popRv.setAdapter(rvAdapter);
        ArrayList<String> specsList=new ArrayList<>();
        specsList= (ArrayList<String>) thisBean.getSpecs_domains().get(0).getDomains();
        rvAdapter.setList(specsList);
        rvAdapter.setItemClicker(new ItemClicker() {
            @Override
            public void itemClicker(int postion) {

                Glide.with(SecondStoreAct.this).load(thisBean.getSkus().get(postion).getCover_image_url()).into((ImageView) pop.findViewById(R.id.iv_pop_pic));
                Log.d("SecondStoreAct", ""+postion);
            }
        });


        //点击屏幕外 pop  dismiss
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());




    }



    //屏幕变暗 5s内
//    private void dimBackground(final float from, final float to) {
//        final Window window = getWindow();
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
//        valueAnimator.setDuration(5000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//            }
//        });
//
//        valueAnimator.start();
//    }


}
