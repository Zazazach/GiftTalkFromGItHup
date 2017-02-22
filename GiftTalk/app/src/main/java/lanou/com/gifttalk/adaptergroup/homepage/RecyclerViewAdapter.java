package lanou.com.gifttalk.adaptergroup.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.bean.homepage.FRChildBean;

/**
 * Created by dllo on 17/2/15.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private List<FRChildBean.DataBean.ItemsBean> list;
    private Context context;
    private int idType;
    private ArrayList<String> imageUrls;

    private int RUNNING=1;
    private int NORMAL =2;
    private Inner inner;

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    public void setList(List<FRChildBean.DataBean.ItemsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inner = null;
        if (viewType==RUNNING){
            View view= LayoutInflater.from(context).inflate(R.layout.fragmenthome_rv_headview,parent,false);
            inner =new Inner(view);
            return inner;
        }else {
            View view= LayoutInflater.from(context).inflate(R.layout.fragmenthome_child_recyclerline,parent,false);
            inner =new Inner(view);
            return inner;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type=getItemViewType(position);
        if (type==RUNNING){
            inner= (Inner) holder;
            if (imageUrls !=null){
                inner.banner.isAutoPlay(true).setImageLoader(new InnerImageLoader()).setImages(imageUrls).setDelayTime(2700);
                inner.banner.start();
                inner.descrpection.setText(list.get(position).getIntroduction());
            }


        }else if (idType==0&&type== NORMAL){
            Glide.with(context).load(imageUrls.get(position-1)).into(inner.cover);

            inner.descrpection.setText(list.get(position-1).getIntroduction());
            inner.intro.setText(list.get(position-1).getAuthor().getIntroduction());
            inner.nickename.setText(list.get(position-1).getAuthor().getNickname());
            inner.title.setText(list.get(position-1).getTitle());
        }
        else {
            if (imageUrls!= null){

                Glide.with(context).load(imageUrls.get(position)).into(inner.cover);

            }
            inner.descrpection.setText(list.get(position).getIntroduction());
            inner.intro.setText(list.get(position).getAuthor().getIntroduction());
            inner.nickename.setText(list.get(position).getAuthor().getNickname());
            inner.title.setText(list.get(position).getTitle());

        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (idType==0){
            if (position==0){
                return RUNNING;
            }else {
                return NORMAL;
            }
        }else {
            return NORMAL;
        }

    }


    class Inner extends RecyclerView.ViewHolder{

        TextView nickename,intro,title,descrpection;
        ImageView cover,icon;
        Banner banner;
        public Inner(View itemView) {
            super(itemView);
            nickename= (TextView) itemView.findViewById(R.id.tv_author_fragmentchild_line);
            intro= (TextView) itemView.findViewById(R.id.tv_interduction_fragmentchild_line);
            title= (TextView) itemView.findViewById(R.id.tv_title_fragmentchild_line);
            descrpection= (TextView) itemView.findViewById(R.id.tv_description_fragmentchild_line);
            cover= (ImageView) itemView.findViewById(R.id.iv_cover_fragmentchild_line);
            banner= (Banner) itemView.findViewById(R.id.banner_headview);
            icon= (ImageView) itemView.findViewById(R.id.iv_fragmentchild_line);
        }
    }

    class InnerImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
