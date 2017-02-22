package lanou.com.gifttalk.fragment.classifypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.homepage.SearchAct;
import lanou.com.gifttalk.adaptergroup.classifypage.ClassifyAdapter;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentClassify extends Fragment {

    private List<Fragment> list;
    private ClassifyAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentclassify_layout,container,false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager_classify);
        tabLayout= (TabLayout) view.findViewById(R.id.tablayout_classify);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=new ArrayList<>();
        list.add(new ClassifyLeftFragment());
        list.add(new ClassifyRightFragment());

        adapter=new ClassifyAdapter(getChildFragmentManager());

        adapter.setList(list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        getActivity().findViewById(R.id.et_classify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchAct.class));
            }
        });
    }
}
