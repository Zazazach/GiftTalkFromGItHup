package lanou.com.gifttalk.fragment.classifypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private IndividualityBean bean=new IndividualityBean();
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

        //双方近性监听,
        // 1.首先是 title监听 点击item icon显示制定position 很简单粗暴的方法
        titleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iconLv.setSelection(position);
                adapter.setRedPlace(position);


            }
        });

        //2.右侧滑动方的监听 通过方法名就能了解到大概
        //onScrollStateChanged 获得当前滑动的状态 是停止还是在滑 我们要滑动时的状态,但这之前需要个变量来传接下
        iconLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            int scroll;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                scroll=scrollState;
            }

            //如果滑动停止,那么此时此刻就不需要 你把position传过来 (上一毫秒已经传送完毕,
            // 如果不这么设置 会一直卡在这位置)
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (scroll == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                return;
            }
                titleLv.setSelection(firstVisibleItem);
                adapter.setRedPlace(firstVisibleItem);
                Log.d("ClassifyRightFragment", "firstVisibleItem:" + firstVisibleItem);
            }
        });









    }
}
