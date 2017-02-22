package lanou.com.gifttalk.adaptergroup.classifypage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class ClassifyAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;


    public ClassifyAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size()>0?list.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "攻略";

        }else {
            return "单品";
        }
    }
}
