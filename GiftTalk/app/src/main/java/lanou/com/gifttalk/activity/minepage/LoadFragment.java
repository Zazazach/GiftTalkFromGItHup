package lanou.com.gifttalk.activity.minepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;


import cn.sharesdk.tencent.qq.QQ;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.fragment.minepage.FragmentMine;
import lanou.com.gifttalk.greendao.BuildTypeBean;
import lanou.com.gifttalk.greendao.PublicTool;

/**
 * Created by dllo on 17/3/2.
 */

public class LoadFragment extends Fragment{

    private RecyclerView recyclerView;
    private View view;
    private FragmentTransaction transaction;
    private PlatformDb platDB;
    private ImageView icon;
    private TextView name;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ShareSDK.initSDK(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.loadsuccess_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        icon= (ImageView) view.findViewById(R.id.profile_head);
        name= (TextView) view.findViewById(R.id.profile_name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        view.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Platform qzone = ShareSDK.getPlatform(QQ.NAME);

                Log.d("LoadFragment", "qzone.isAuthValid():" + qzone.isAuthValid());
                if (qzone.isAuthValid()) {
                    qzone.removeAccount(true);


                    transaction.replace(R.id.fl_actmajor, new FragmentMine());
                    transaction.commit();

                    int BUILD_TYPE = 0;
                    Toast.makeText(getActivity(), ""+BUILD_TYPE, Toast.LENGTH_SHORT).show();

                    PublicTool.getInstance().deleteDataZero();
                    PublicTool.getInstance().deleteDataOne();
                    PublicTool.getInstance().insertData(BUILD_TYPE);


                }
                // qzone.setPlatformActionListener(null);
                //authorize与showUser单独调用一个即可
                //qzone.authorize();//单独授权，OnComplete返回的hashmap是空的
               // qzone.showUser(null);//授权并获取用户信息


            //isValid和removeAccount不开启线程，会直接返回。
            }
        });

        if (PublicTool.getInstance().queryImageData()!=null&&PublicTool.getInstance().queryNameData()!=null) {

            Glide.with(getContext()).load(PublicTool.getInstance().queryImageData()).into(icon);
            name.setText(PublicTool.getInstance().queryNameData());

        }



    }
}
