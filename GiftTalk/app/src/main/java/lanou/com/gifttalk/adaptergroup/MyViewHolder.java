package lanou.com.gifttalk.adaptergroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import lanou.com.gifttalk.bean.homepage.FHRuningPicBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

/**
 * Created by dllo on 17/2/14.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private  static View mView;
    private SparseArray<View> sparseArray;
    private Context context;
    private ArrayList<String> list;

    private static final String TAG = "MyViewHolder";

    public MyViewHolder(View itemView,Context context) {
        super(itemView);
        mView=itemView;
        this.context=context;
        list=new ArrayList<>();
        sparseArray=new SparseArray<>();
    }
    public static MyViewHolder createViewHolder(Context context, ViewGroup group,int id){

        View view= LayoutInflater.from(context).inflate(id,group,false);

        MyViewHolder holder=new MyViewHolder(view,context);

        return holder;
    }
    public  <T extends View> T getView(int id){
        View v=sparseArray.get(id);
        if (v==null){
            v=mView.findViewById(id);
            sparseArray.put(id,v);
        }
        return (T) v;
    }



    public MyViewHolder whriteText(String content,int id){
        TextView textView=getView(id);
        if (content!=null){
            textView.setText(content);
        }
        return this;
    }

    public MyViewHolder whriteImage(String url,int id){
        ImageView imageView=getView(id);
        if (url!=null){
            Glide.with(context).load(url).into(imageView);
        }
        return this;
    }





    public MyViewHolder setBanner(final int id, String URL){
         final Banner banner=getView(id);
        ParserTool.getInstance().praser(URL, FHRuningPicBean.class, new ParseMethod<FHRuningPicBean>() {
           @Override
            public void onSucceed(FHRuningPicBean something) {
               Log.d("kkkkk", "aaaa");
                for (int i = 0; i < something.getData().getBanners().size(); i++) {
                    list.add(something.getData().getBanners().get(i).getImage_url());
                }



               Log.e(TAG, "setBanner: "+""+list.size());
                   banner.setImages(list);
                   banner.setImageLoader(new BannerImageLoader());
                   banner.isAutoPlay(true);
                   banner.setDelayTime(3000);
                   banner.setIndicatorGravity(BannerConfig.CENTER);
                   banner.start();


            }
        });



        return this;
    }


    public static View getmView(){
        return mView;
    }




    class BannerImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);

            // java.lang.IllegalArgumentException: Unknown type class lanou.com.gifttalk.bean.homepage.FHRuningPicBean$DataBean$BannersBean. You must provide a Model of a type for which there is a registered ModelLoader, if you are using a custom model, you must first call Glide#register with a ModelLoaderFactory for your custom model class
//            at com.bumptech.glide.RequestManager.loadGeneric(RequestManager.java:629)
//            at com.bumptech.glide.RequestManager.load(RequestManager.java:598)
//            at lanou.com.gifttalk.adaptergroup.MyViewHolder$BannerImageLoader.displayImage(MyViewHolder.java:95)
//            at lanou.com.gifttalk.adaptergroup.MyViewHolder$BannerImageLoader.displayImage(MyViewHolder.java:91)
//            at com.youth.banner.Banner.setImageList(Banner .java:351)
//            at com.youth.banner.Banner.start(Banner.java:258)
//            at lanou.com.gifttalk.adaptergroup.MyViewHolder.setBanner(MyViewHolder.java:84)
//            at lanou.com.gifttalk.adaptergroup.homepage.FragmentChildRecyclerAdapter.onBindViewHolder(FragmentChildRecyclerAdapter.java:69)
//            at lanou.com.gifttalk.adaptergroup.homepage.FragmentChildRecyclerAdapter.onBindViewHolder(FragmentChildRecyclerAdapter.java:28)
        }
    }

}
