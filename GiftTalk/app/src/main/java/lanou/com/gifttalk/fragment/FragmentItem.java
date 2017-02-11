package lanou.com.gifttalk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;

/**
 * Created by dllo on 17/2/11.
 */

public class FragmentItem extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentitem_layout,container,false);

        return view;

    }
}
