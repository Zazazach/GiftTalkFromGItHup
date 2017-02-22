package lanou.com.gifttalk.adaptergroup.storepage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;

/**
 * Created by dllo on 17/2/17.
 */

public class StoreUpperRvAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private StoreUpperBean.DataBean.ItemsBeanX storeUpperBean;
    private StoreUpperBean.DataBean.ItemsBeanX xBean;

    public void setxBean(StoreUpperBean.DataBean.ItemsBeanX xBean) {
        this.xBean = xBean;
        notifyDataSetChanged();
    }

    public void setStoreUpperBean(StoreUpperBean.DataBean.ItemsBeanX storeUpperBean) {
        this.storeUpperBean = storeUpperBean;
        notifyDataSetChanged();
    }

    public StoreUpperRvAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return MyViewHolder.createViewHolder(context,parent,R.layout.store_upper_rv_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.drawImage(storeUpperBean.getItems().get(position).getCover_image_url(),R.id.iv_store_coverupper_line);
        holder.writeText(storeUpperBean.getItems().get(position).getShort_description(),R.id.tv_store_shortdescriptionupper_line);
        holder.writeText(storeUpperBean.getItems().get(position).getSkus().get(0).getPrice(),R.id.tv_store_fixedpriceupper_line);
    }

    @Override
    public int getItemCount() {
        return storeUpperBean.getItems().size()>0?storeUpperBean.getItems().size():0;

    }


}
