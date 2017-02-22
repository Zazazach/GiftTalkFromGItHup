package lanou.com.gifttalk.fragment.classifypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.classifypage.IconListAdapter;
import lanou.com.gifttalk.adaptergroup.classifypage.TitleListAdapter;
import lanou.com.gifttalk.bean.classifypage.IndividualityBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.INDIVI;

/**
 * Created by dllo on 17/2/18.
 */

public class ClassifyRightFragment extends Fragment {

    private ListView titleLv,iconLv;
    private TitleListAdapter adapter;
    private IconListAdapter iconListAdapter;
    private List<String> titleList,iconList;
    private IndividualityBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.classifyrightfragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleLv= (ListView) view.findViewById(R.id.lv_right);
        iconLv= (ListView) view.findViewById(R.id.lv_right_right);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ParserTool.getInstance().praser(INDIVI, IndividualityBean.class, new ParseMethod<IndividualityBean>() {
            @Override
            public void onSucceed(IndividualityBean something) {
                titleList =new ArrayList<>();
                iconList=new ArrayList<String>();
                bean=something;
                for (int i = 0; i < something.getData().getCategories().size(); i++) {
                    titleList.add(something.getData().getCategories().get(i).getName());
                    iconList.add(something.getData().getCategories().get(i).getIcon_url());
                }
                adapter=new TitleListAdapter(getContext());
                adapter.setList(titleList);
                titleLv.setAdapter(adapter);


                iconListAdapter=new IconListAdapter(getContext());
                iconListAdapter.setIconlist(iconList);
                iconListAdapter.setBean(something);
                iconLv.setAdapter(iconListAdapter);


            }

        });

        titleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iconLv.setSelection(position);

            }
        });
            //TODO 滑动联动不会写
//        iconLv.setOnScrollListener(new AbsListView.OnScrollListener() {
//                private int scollState;
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                this.scollState=scrollState;
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                int current=bean.getData().getCategories().indexOf(firstVisibleItem);
//                int currentItem=0;
//                if (currentItem!=current&&current>=0){
//                    currentItem=current;
//                }
//            }
//        });




    }
}
