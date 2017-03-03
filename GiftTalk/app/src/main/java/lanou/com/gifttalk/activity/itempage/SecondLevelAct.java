package lanou.com.gifttalk.activity.itempage;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;
import lanou.com.gifttalk.adaptergroup.itempage.MyViewAdapter;
import lanou.com.gifttalk.bean.homepage.ChildBean;
import lanou.com.gifttalk.bean.itempage.FloatBean;
import lanou.com.gifttalk.bean.itempage.ItemChildBean;
import lanou.com.gifttalk.fragment.itempage.SecondLevelFragmentLeft;
import lanou.com.gifttalk.fragment.itempage.SecondLevelFragmentRt;
import lanou.com.gifttalk.fragment.itempage.SencondLevelFragmentMid;

/**
 * Created by dllo on 17/2/24.
 */

public class SecondLevelAct extends BaseActivity {
    private ViewPager viewPager;
    private Intent intent;
    private List<Fragment> list;
    private MyViewAdapter adapter;
    private ImageView imageView;
    private TabLayout tabLayout;
    private EventBus eventBus;
    private ItemChildBean.DataBean.ItemsBean itemsBean;


    @Override
    public int bindLayout() {
        return R.layout.secondlevel_layout;
    }

    @Override
    public void initView() {
        viewPager= (ViewPager) findViewById(R.id.vp_secondlevel);
        tabLayout= (TabLayout) findViewById(R.id.tl_secondlevel);
        intent = getIntent();
        list=new ArrayList<>();
        imageView= (ImageView) findViewById(R.id.tv_secondlevel_back);
//        EventBus.getDefault().register(this);
        EventBus.getDefault().register(this);


    }

    @Override
    public void initData() {
        itemsBean = intent.getParcelableExtra("bean");
        ItemChildBean bean=intent.getParcelableExtra("bean2");
        SecondLevelFragmentLeft left=new SecondLevelFragmentLeft();
        left.setItemsBean(itemsBean);
        left.setBean(bean);


        SencondLevelFragmentMid mid=new SencondLevelFragmentMid();
        mid.setUrl(itemsBean.getUrl());

        list.add(left);
        list.add(mid);
        list.add(new SecondLevelFragmentRt());

        adapter=new MyViewAdapter(getSupportFragmentManager(),this);
        adapter.setList(list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.tv_secondlevel_taobao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondLevelAct.this,PurchaseAct.class);
                Log.d("SecondLevelAct", (itemsBean.getPurchase_url()==null)+"");
                intent.putExtra("purchase", itemsBean.getPurchase_url());
                startActivity(intent);
            }
        });






    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void setAlphaaa(FloatBean floatBean){

      //  findViewById(R.id.rl_hide).setAlpha(length/5);
        if (floatBean.getAlpha()>0.7){
            findViewById(R.id.rl_hide).setAlpha(1.0f);
        }else {
            findViewById(R.id.rl_hide).setAlpha(floatBean.getAlpha());
        }

        Log.d("SecondLevelAct", "length:**" + floatBean.getAlpha());

        //覆盖一直没有达成
        // TODO 覆盖一直没有达成

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
