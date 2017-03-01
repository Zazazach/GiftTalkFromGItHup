package lanou.com.gifttalk.adaptergroup.itempage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import lanou.com.gifttalk.bean.itempage.ItemTitleBean;
import lanou.com.gifttalk.fragment.itempage.FragmentItemChild;

/**
 * Created by dllo on 17/2/15.
 */

public class FragmentItemAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private static ArrayList<ItemTitleBean.DataBean.RanksBean> list;

    public FragmentItemAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    public void setList(ArrayList<ItemTitleBean.DataBean.RanksBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }



    @Override
    public Fragment getItem(int position) {
        return FragmentItemChild.newInstance(position);
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }

    public static int getId(int pos){
        return list.get(pos).getId();
    }


}
