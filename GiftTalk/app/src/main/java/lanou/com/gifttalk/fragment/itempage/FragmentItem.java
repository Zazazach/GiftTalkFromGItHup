package lanou.com.gifttalk.fragment.itempage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemAdapter;
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

    private FragmentItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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


    }



}
