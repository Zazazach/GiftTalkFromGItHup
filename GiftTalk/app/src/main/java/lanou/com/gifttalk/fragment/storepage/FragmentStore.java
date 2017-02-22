package lanou.com.gifttalk.fragment.storepage;

import android.content.Intent;
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

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.homepage.SearchAct;
import lanou.com.gifttalk.adaptergroup.storepage.StoreRvAdapter;
import lanou.com.gifttalk.adaptergroup.storepage.StoreUpperRvAdapter;
import lanou.com.gifttalk.bean.storepage.StoreDownerBean;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.STORE_UPPER;
import static lanou.com.gifttalk.finaldata.Http.STORE_DOWNER;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentStore extends Fragment {

    private RecyclerView recyclerView;
    private StoreRvAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private StoreUpperBean storeUpperBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentstore_layout,container,false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_fragmentstore_layout);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter=new StoreRvAdapter(getContext());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false));


        ParserTool.getInstance().praser(STORE_UPPER, StoreUpperBean.class, new ParseMethod<StoreUpperBean>() {
            @Override
            public void onSucceed(StoreUpperBean something) {
                Log.d("FragmentStore", "something.getData().getItems().size():" + something.getData().getItems().size());

                adapter.setStoreUpperBean(something);
                storeUpperBean = something;

            }
        });

        ParserTool.getInstance().praser(STORE_DOWNER, StoreDownerBean.class, new ParseMethod<StoreDownerBean>() {
            @Override
            public void onSucceed(StoreDownerBean something) {

                adapter.setStoreDownerBean(something);

                final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(position <= 7){
                            return 2;
                        }else {
                            return 1;
                        }


                    }
                });
                recyclerView.setLayoutManager(gridLayoutManager);


                recyclerView.setAdapter(adapter);

            }
        });


        getActivity().findViewById(R.id.et_fragmentstore_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SearchAct.class));
            }
        });


    }
}
