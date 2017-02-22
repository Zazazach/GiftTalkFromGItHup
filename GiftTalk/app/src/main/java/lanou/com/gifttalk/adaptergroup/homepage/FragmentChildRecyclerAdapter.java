package lanou.com.gifttalk.adaptergroup.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.homepage.FRChildBean;

import static lanou.com.gifttalk.finaldata.Http.RUNNING_PIC;

/**
 * Created by dllo on 17/2/13.
 */

public class FragmentChildRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = "FragmentChildRecyclerAd";
    private List<FRChildBean.DataBean.ItemsBean> list;
    private Context context;
    private int idType;
    private ArrayList<String> imageUrls;
    private ArrayList<String> picesList;
    private ImageView oneIv,twoIv,threeIv,fourIv,fiveIv,sixIv;

    private int RUNNING=1;
    private int NORMAL =2;



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

    public void setList(List<FRChildBean.DataBean.ItemsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public FragmentChildRecyclerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RUNNING && idType == 0) {
            Log.d(TAG, "aaaa");
            return MyViewHolder.createViewHolder(context, parent, R.layout.fragmenthome_rv_headview);
        } else {

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
            holder.drawImage(list.get(position-1).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line);

        }else {
            holder.writeText(list.get(position).getAuthor().getNickname(), R.id.tv_author_fragmentchild_line);
            holder.writeText(list.get(position).getAuthor().getIntroduction(), R.id.tv_interduction_fragmentchild_line);
            holder.writeText(list.get(position).getIntroduction(), R.id.tv_description_fragmentchild_line);
            holder.writeText(list.get(position).getTitle(), R.id.tv_title_fragmentchild_line);
            holder.drawImage(list.get(position).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line);
            holder.drawImage(list.get(position).getCover_image_url(), R.id.iv_cover_fragmentchild_line);

        }
//      holder.drawImage(list.get(position).getAuthor().getAvatar_url(),R.id.iv_fragmentchild_line);

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

    @Override
    public int getItemCount() {
        //Log.e(TAG, "getItemCount: " + list.size());
        return list == null ? 0 : list.size();
    }



    }





