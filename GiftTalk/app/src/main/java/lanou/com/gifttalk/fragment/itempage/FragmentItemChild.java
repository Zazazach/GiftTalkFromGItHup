package lanou.com.gifttalk.fragment.itempage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemAdapter;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemRvAdapter;
import lanou.com.gifttalk.bean.itempage.FRItemChildBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

/**
 * Created by dllo on 17/2/16.
 */

public class FragmentItemChild extends Fragment {

    private RecyclerView recyclerView;
    private FragmentItemRvAdapter adapter;
    private FRItemChildBean bean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragmentitemchild_layout,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_fragmentitemchild);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        Bundle bundle=getArguments();
        int id=bundle.getInt("id");
        final int postion=bundle.getInt("pos");

        String  BASE_URL ="http://api.liwushuo.com/v2/ranks_v3/ranks/" ;
        String  OTHER = "?limit=20&offset=0";

        String ITEM_URL=BASE_URL+""+id+OTHER;

        ParserTool.getInstance().praser(ITEM_URL, FRItemChildBean.class, new ParseMethod<FRItemChildBean>() {
            @Override
            public void onSucceed(FRItemChildBean something) {
                adapter=new FragmentItemRvAdapter(getContext());
                final GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);

                recyclerView.setLayoutManager(layoutManager);

                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (adapter.isHeadView(position))?layoutManager.getSpanCount():1;
                    }
                });

                adapter.setBean(something);
                adapter.setVpPos(postion);
                recyclerView.setAdapter(adapter);
                Log.d("FragmentItemChild", "something:" + something);
            }
        });

    }

    public static FragmentItemChild newInstance(int pos) {

        Bundle args = new Bundle();

        args.putInt("id", FragmentItemAdapter.getId(pos));
        args.putInt("pos",pos);
        FragmentItemChild fragment = new FragmentItemChild();
        fragment.setArguments(args);
        return fragment;
    }
}
