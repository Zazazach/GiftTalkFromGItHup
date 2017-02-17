package lanou.com.gifttalk.adaptergroup;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.bean.homepage.FRChildBean;
import lanou.com.gifttalk.bean.homepage.FRTitleBean;
import lanou.com.gifttalk.fragment.homepage.FragmentHomeChild;

/**
 * Created by dllo on 17/2/13.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private static ArrayList<FRChildBean.DataBean.ItemsBean> list;

    private static List<FRTitleBean.DataBean.ChannelsBean> titleList;



    private Context context;

    private static final String TAG = "FragmentAdapter";


    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    public void setTitleList(List<FRTitleBean.DataBean.ChannelsBean> titleList) {
        FragmentAdapter.titleList = titleList;
        notifyDataSetChanged();
    }

    public void setList(ArrayList<FRChildBean.DataBean.ItemsBean> list) {
        FragmentAdapter.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        return FragmentHomeChild.newInstance(position);
    }

    @Override
    public int getCount() {
        return titleList != null ? titleList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position).getName();
    }

   public static int getId(int pos){
       return titleList.get(pos).getId();
   }


}
