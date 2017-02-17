package lanou.com.gifttalk.fragment.storepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.bean.storepage.StoreBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.STORE_WHOLE;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentStore extends Fragment {

    private RecyclerView recyclerView;


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

        ParserTool.getInstance().praser(STORE_WHOLE, StoreBean.class, new ParseMethod<StoreBean>() {
            @Override
            public void onSucceed(StoreBean something) {


            }
        });


    }
}
