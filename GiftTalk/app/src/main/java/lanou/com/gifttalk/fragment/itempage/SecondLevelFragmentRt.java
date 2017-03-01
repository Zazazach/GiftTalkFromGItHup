package lanou.com.gifttalk.fragment.itempage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;

/**
 * Created by dllo on 17/2/25.
 */

public class SecondLevelFragmentRt extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.secondlevel_rt,container,false);
        return view;
    }
}
