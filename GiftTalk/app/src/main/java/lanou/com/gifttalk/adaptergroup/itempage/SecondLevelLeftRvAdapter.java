package lanou.com.gifttalk.adaptergroup.itempage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.homepage.ChildBean;
import lanou.com.gifttalk.bean.itempage.ItemChildBean;

/**
 * Created by dllo on 17/2/24.
 */

public class SecondLevelLeftRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private ItemChildBean.DataBean.ItemsBean itemsBean;
    private ItemChildBean bean;

    public void setBean(ItemChildBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setItemsBean(ItemChildBean.DataBean.ItemsBean itemsBean) {
        this.itemsBean = itemsBean;
        notifyDataSetChanged();
    }

    public SecondLevelLeftRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==0){
            return  MyViewHolder.createViewHolder(context,parent,R.layout.secondlevelfragment_left_layout);

        }

        return MyViewHolder.createViewHolder(context,parent,R.layout.fragmentitem_rv_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int type=getItemViewType(position);
        if (type==0){
            holder.writeText(itemsBean.getShort_description(),R.id.tv_second_left_short);
            holder.writeText(itemsBean.getName(),R.id.tv_second_left_name);
            holder.writeText("¥ "+itemsBean.getPrice(),R.id.tv_second_left_price);
            holder.writeText(""+itemsBean.getFavorites_count(),R.id.tv_second_left_like);
            holder.writeText(itemsBean.getDescription(),R.id.tv_second_left_description);
            holder.setBanner(R.id.ban_second_left, (ArrayList<String>) itemsBean.getImage_urls());

        }else {
            holder.getView(R.id.tv_fragmentitem_top).setVisibility(View.GONE);
            holder.drawImage(bean.getData().getItems().get(position-1).getCover_image_url(),R.id.iv_fragmentitem_cover);
            holder.writeText(bean.getData().getItems().get(position-1).getShort_description(),R.id.tv_fragmentitem_description);
            holder.writeText(bean.getData().getItems().get(position-1).getName(),R.id.tv_fragmentitem_name);
            holder.writeText(bean.getData().getItems().get(position-1).getPrice(),R.id.tv_fragmentitem_price);

        }
    }

    @Override
    public int getItemCount() {
        return bean.getData().getItems().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
