package lanou.com.gifttalk.fragment.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.homepage.SearchAct;
import lanou.com.gifttalk.adaptergroup.homepage.FragmentAdapter;
import lanou.com.gifttalk.bean.homepage.ChildBean;
import lanou.com.gifttalk.bean.homepage.TitleBean;
import lanou.com.gifttalk.finaldata.Http;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentHome extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChildBean bean;
    private EditText searchEt;

    private FragmentAdapter adapter;
    private View v;
    private ArrayList<TitleBean.DataBean.ChannelsBean> list;
    private static final String TAG = "FragmentHome";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmenthome_layout, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.vp_fragmenthome);
        tabLayout= (TabLayout) v.findViewById(R.id.tl_fragmenthome);
        searchEt= (EditText) v.findViewById(R.id.et_home);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        adapter = new FragmentAdapter(getChildFragmentManager(), getContext());

        // viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        ParserTool.getInstance().praser(Http.HOME_GREAT, ChildBean.class, new ParseMethod<ChildBean>() {
            @Override
            public void onSucceed(ChildBean something) {

                adapter.setList((ArrayList<ChildBean.DataBean.ItemsBean>) something.getData().getItems());
            }
        });


        ParserTool.getInstance().praser(Http.HOME_TITLE, TitleBean.class, new ParseMethod<TitleBean>() {
            @Override
            public void onSucceed(TitleBean something) {
                list = (ArrayList<TitleBean.DataBean.ChannelsBean>) something.getData().getChannels();
                Log.e(TAG, "onSucceed: " + list.size());

                adapter.setTitleList(list);
            }


        });

        //————————————————————————————上方搜索
        searchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SearchAct.class));



            }
        });

    }

}
