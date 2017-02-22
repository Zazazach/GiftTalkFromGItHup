package lanou.com.gifttalk.fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.homepage.FragmentAdapter;
import lanou.com.gifttalk.adaptergroup.homepage.FragmentChildRecyclerAdapter;
import lanou.com.gifttalk.bean.homepage.FHRuningPicBean;
import lanou.com.gifttalk.bean.homepage.FRChildBean;
import lanou.com.gifttalk.bean.homepage.HomeSixPicesBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.HOME_PICES;
import static lanou.com.gifttalk.finaldata.Http.RUNNING_PIC;

/**
 * Created by dllo on 17/2/13.
 */

public class FragmentHomeChild extends Fragment {
    private static final String TAG = "FragmentHomeChild";
    private RecyclerView recyclerView;
    private FragmentChildRecyclerAdapter adapter;
    private ArrayList<FRChildBean.DataBean.ItemsBean> list;
    private int id;
    private List<FHRuningPicBean.DataBean.BannersBean> bannersBeen;
    private int vpPos;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Log.e(TAG, "onCreate: " + list.size());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome_fragmentchild_layout, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_framentchild);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        adapter = new FragmentChildRecyclerAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Bundle b = getArguments();
        id = b.getInt("value");
        vpPos = b.getInt("pos");

        adapter.setIdType(vpPos);
        String PAGE_URL = "http://api.liwushuo.com/v2/channels/" + id + "/items_v2?gender=1&generation=2&limit=20&offset=0";


        ParserTool.getInstance().praser(PAGE_URL, FRChildBean.class, new ParseMethod<FRChildBean>() {

            @Override
            public void onSucceed(FRChildBean something) {

                list = (ArrayList<FRChildBean.DataBean.ItemsBean>) something.getData().getItems();

                Log.d(TAG, "list.size():" + list.size());
                adapter.setList(list);

            }

        });




        ParserTool.getInstance().praser(RUNNING_PIC, FHRuningPicBean.class, new ParseMethod<FHRuningPicBean>() {
            @Override
            public void onSucceed(FHRuningPicBean something) {

                Log.d("FragmentHomeChild", "aaaa");


                bannersBeen = something.getData().getBanners();

                ArrayList<String> imageUrle=new ArrayList<String>();
                for (int i = 0; i < bannersBeen.size(); i++) {
                    imageUrle.add(bannersBeen.get(i).getImage_url());
                }
                adapter.setImageUrls(imageUrle);

            }
        });


        ParserTool.getInstance().praser(HOME_PICES, HomeSixPicesBean.class, new ParseMethod<HomeSixPicesBean>() {
            @Override
            public void onSucceed(HomeSixPicesBean something) {
                ArrayList<String> picList=new ArrayList<String>();
                for (int i = 0; i < something.getData().getSecondary_banners().size(); i++) {
                    picList.add(something.getData().getSecondary_banners().get(i).getImage_url());
                }

                adapter.setPicesList(picList);
                recyclerView.setAdapter(adapter);
                Log.d(TAG, "picList.size():" + picList.size());

            }
        });


    }

    public static FragmentHomeChild newInstance(int pos) {

        Bundle args = new Bundle();

        int id = FragmentAdapter.getId(pos);
        args.putInt("value", id);
        args.putInt("pos",pos);
        FragmentHomeChild fragment = new FragmentHomeChild();
        fragment.setArguments(args);
        return fragment;
    }
}
