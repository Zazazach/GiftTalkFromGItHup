package lanou.com.gifttalk.fragment.itempage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.itempage.SecondLevelAct;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemAdapter;
import lanou.com.gifttalk.adaptergroup.itempage.FragmentItemRvAdapter;
import lanou.com.gifttalk.bean.itempage.ItemChildBean;
import lanou.com.gifttalk.inter.ItemClicker;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

/**
 * Created by dllo on 17/2/16.
 */

public class FragmentItemChild extends Fragment {

    private RecyclerView recyclerView;
    private FragmentItemRvAdapter adapter;

    private ItemChildBean childBean;



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



        final Bundle bundle=getArguments();
        int id=bundle.getInt("id");
        final int postion=bundle.getInt("pos");

        String  BASE_URL ="http://api.liwushuo.com/v2/ranks_v3/ranks/" ;
        String  OTHER = "?limit=20&offset=0";

        String ITEM_URL=BASE_URL+""+id+OTHER;

        ParserTool.getInstance().praser(ITEM_URL, ItemChildBean.class, new ParseMethod<ItemChildBean>() {
            @Override
            public void onSucceed(ItemChildBean something) {
                adapter=new FragmentItemRvAdapter(getContext());
                final GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);

                recyclerView.setLayoutManager(layoutManager);

                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (adapter.isHeadView(position))?layoutManager.getSpanCount():1;
                    }
                });

                childBean = something;
                adapter.setBean(childBean);
                adapter.setVpPos(postion);
                recyclerView.setAdapter(adapter);



                adapter.setClicker(new ItemClicker() {
                    @Override
                    public void itemClicker(int postion) {

                        Intent intent=new Intent(getActivity(),SecondLevelAct.class);
                        intent.putExtra("bean",childBean.getData().getItems().get(postion-1));
                        intent.putExtra("bean2",childBean);
                        startActivity(intent);
                        Log.d("FragmentItemChild", "333333333");
                    }
                });
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
