package lanou.com.gifttalk.fragment.classifypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.classifypage.ClassifyLeftRvAdapter;
import lanou.com.gifttalk.bean.classifypage.LeftBottomBean;
import lanou.com.gifttalk.bean.classifypage.LeftTopBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.LEFT_BOTTOM;
import static lanou.com.gifttalk.finaldata.Http.LEFT_TOP;

/**
 * Created by dllo on 17/2/18.
 */

public class ClassifyLeftFragment extends Fragment {

    private RecyclerView recyclerView;
    private ClassifyLeftRvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classifyleftfragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_classify_l);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ParserTool.getInstance().praser(LEFT_TOP, LeftTopBean.class, new ParseMethod<LeftTopBean>() {
            @Override
            public void onSucceed(LeftTopBean something) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter=new ClassifyLeftRvAdapter(getContext());

                adapter.setLeftTopBean(something);



                ParserTool.getInstance().praser(LEFT_BOTTOM, LeftBottomBean.class, new ParseMethod<LeftBottomBean>() {
                    @Override
                    public void onSucceed(LeftBottomBean something) {
                        adapter.setLeftBottomBean(something);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });





    }

}
