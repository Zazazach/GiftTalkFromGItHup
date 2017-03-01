package lanou.com.gifttalk.adaptergroup.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.homepage.ChildBean;
import lanou.com.gifttalk.inter.ClickToDetail;

import static lanou.com.gifttalk.finaldata.Http.RUNNING_PIC;

/**
 * Created by dllo on 17/2/13.
 */

public class FragmentChildRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = "FragmentChildRecyclerAd";
    private List<ChildBean.DataBean.ItemsBean> list;
    private Context context;
    private int idType;
    private ArrayList<String> picesList,imageUrls;
    private ClickToDetail toDetail;

    public void setToDetail(ClickToDetail toDetail) {
        this.toDetail = toDetail;
        notifyDataSetChanged();
    }

    private int RUNNING=1;
    private int NORMAL =2;
    private int FOOTVIEW=3;



    public void setPicesList(ArrayList<String> picesList) {
        this.picesList = picesList;
        notifyDataSetChanged();
    }

    public void setIdType(int idType) {
        this.idType = idType;
        notifyDataSetChanged();
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setList(List<ChildBean.DataBean.ItemsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public FragmentChildRecyclerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RUNNING && idType == 0) {

            return MyViewHolder.createViewHolder(context, parent, R.layout.fragmenthome_rv_headview);

        } else if (viewType==FOOTVIEW) {

            return MyViewHolder.createViewHolder(context, parent, R.layout.fragmenthome_child_recyclerline);
        }
            else {

                return MyViewHolder.createViewHolder(context, parent, R.layout.fragmenthome_child_recyclerline);
            }

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        int itemType =getItemViewType(position);

        if (itemType==RUNNING&&idType==0) {

            holder.setBanner(R.id.banner_headview, RUNNING_PIC);

            holder.drawImage(picesList.get(0),R.id.iv_one);
            holder.drawImage(picesList.get(1),R.id.iv_two);
            holder.drawImage(picesList.get(2),R.id.iv_three);
            holder.drawImage(picesList.get(3),R.id.iv_four);
            holder.drawImage(picesList.get(4),R.id.iv_five);
            holder.drawImage(picesList.get(5),R.id.iv_six);


        } else if (itemType== NORMAL &&idType==0){
            holder.writeText(list.get(position-1).getAuthor().getNickname(), R.id.tv_author_fragmentchild_line);
            holder.writeText(list.get(position-1).getAuthor().getIntroduction(), R.id.tv_interduction_fragmentchild_line);
            holder.writeText(list.get(position-1).getIntroduction(), R.id.tv_description_fragmentchild_line);
            holder.writeText(list.get(position-1).getTitle(), R.id.tv_title_fragmentchild_line);
            holder.writeText(""+list.get(position-1).getLikes_count(),R.id.tv_like__fragmentchild_line);
            holder.drawImage(list.get(position-1).getCover_image_url(), R.id.iv_cover_fragmentchild_line);
            holder.drawImage(list.get(position-1).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line,context);

            holder.getView(R.id.rl_child_recyclerline).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDetail.toDetail(position-1);
                }
            });

        }else {//? 胃部不惧 与其他的布局数据来源都一样 是否还需要bind?
            holder.writeText(list.get(position).getAuthor().getNickname(), R.id.tv_author_fragmentchild_line);
            holder.writeText(list.get(position).getAuthor().getIntroduction(), R.id.tv_interduction_fragmentchild_line);
            holder.writeText(list.get(position).getIntroduction(), R.id.tv_description_fragmentchild_line);
            holder.writeText(list.get(position).getTitle(), R.id.tv_title_fragmentchild_line);
            holder.drawImage(list.get(position).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line,context);
            holder.drawImage(list.get(position).getCover_image_url(), R.id.iv_cover_fragmentchild_line);

            holder.getView(R.id.rl_child_recyclerline).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDetail.toDetail(position);
                }
            });
        }
//      holder.drawImage(list.get(position).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line);

    }

    @Override
    public int getItemViewType(int position) {
//        if (idType==0){
            if (position==0){
                return RUNNING;

            }else if(position+1==getItemCount()){

                return FOOTVIEW;
                //如果是最后一位+1 那么就开始加载
            }
            else {
                    return NORMAL;
                }

//        }else {
//            return NORMAL;
//        }

    }

    @Override
    public int getItemCount() {
        //Log.e(TAG, "getItemCount: " + list.size());
        return list == null ? 0 : list.size();
    }



    }





