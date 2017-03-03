package lanou.com.gifttalk.fragment.minepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;

import cn.sharesdk.tencent.qq.QQ;
import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.MyApp;
import lanou.com.gifttalk.activity.minepage.LoadAct;
import lanou.com.gifttalk.activity.minepage.LoadFragment;
import lanou.com.gifttalk.greendao.BuildTypeBean;
import lanou.com.gifttalk.greendao.PublicTool;


/**
 * Created by dllo on 17/2/11.
 */

public class FragmentMine extends Fragment {

    private ImageView qq;
    private int BUILD_TYPE=0;
    private FragmentTransaction transaction;
    private PlatformDb platDB;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ShareSDK.initSDK(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_layout,container,false);

            return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qq = (ImageView) view.findViewById(R.id.iv_qq_login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();



        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Platform weibo = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weibo.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        //用户资源都保存到res
                        //通过打印res数据看看有哪些数据是你想要的
                        if (i == Platform.ACTION_USER_INFOR) {
                            //获取数平台数据DB
                            platDB = platform.getDb();
                            //通过DB获取各种数据
                            platDB.getToken();
                            platDB.getUserGender();
                            platDB.getUserIcon();
                            platDB.getUserId();

                            transaction.replace(R.id.fl_actmajor,new LoadFragment());

                            if (platform.getDb().getUserId()!=null) {
                                Log.d("FragmentMine11", platDB.getUserName());
                                BUILD_TYPE=1;
                                PublicTool.getInstance().deleteDataZero();
                                PublicTool.getInstance().deleteDataOne();
                                PublicTool.getInstance().insertData(BUILD_TYPE);
                                transaction.commit();

                                BuildTypeBean bean=new BuildTypeBean();
                                bean.setImageUrl(platDB.getUserIcon());
                                bean.setName(platDB.getUserName());
                                PublicTool.getInstance().insertData(bean);
                                PublicTool.getInstance().insertPicData(platDB.getUserIcon());



                            }
                        }
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                //authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
                //移除授权

                //weibo.removeAccount(true);
            }
        });





    }









}
