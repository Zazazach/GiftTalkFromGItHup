package lanou.com.gifttalk.fragment.itempage;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.MyApp;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemAdapter;
import lanou.com.gifttalk.bean.itempage.ItemChildBean;
import lanou.com.gifttalk.bean.itempage.ItemTitleBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.ITEM_TITLE;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentItem extends Fragment {
    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<ItemTitleBean.DataBean.RanksBean> list;
    private ItemChildBean bean;
    private FragmentItemAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ShareSDK.initSDK(getContext(),"sharesdk的appkey");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // EventBus.getDefault().register(MyApp.getContext());
        View view=inflater.inflate(R.layout.fragmentitem_layout,container,false);
        tabLayout= (TabLayout) view.findViewById(R.id.tl_fragmentitem);
        viewPager= (ViewPager) view.findViewById(R.id.vp_fragmentitem);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView= (ImageView) view.findViewById(R.id.iv_fragmentitem_compact);
        tabLayout= (TabLayout) view.findViewById(R.id.tl_fragmentitem);
        viewPager= (ViewPager) view.findViewById(R.id.vp_fragmentitem);
        adapter=new FragmentItemAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ParserTool.getInstance().praser(ITEM_TITLE, ItemTitleBean.class, new ParseMethod<ItemTitleBean>() {
            @Override
            public void onSucceed(ItemTitleBean something) {
                list=new ArrayList<>();
                list= (ArrayList<ItemTitleBean.DataBean.RanksBean>) something.getData().getRanks();

                adapter.setList(list);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showShare();
            }
        });


    }







    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("每日推荐好礼:..");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://hawaii.liwushuo.com/ranks_v2/ranks/1");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("送礼物,就上礼物说!");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");

        oks.setImageUrl("http://img02.liwushuo.com/image/160909/3gnib47x3.png-w720");

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://img03.liwushuo.com/image/170222/ulztswz58_w.jpg-w720");

        // 启动分享GUI
        oks.show(getContext());
    }



}
