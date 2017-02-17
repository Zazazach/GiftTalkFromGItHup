package lanou.com.gifttalk.adaptergroup.storepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.storepage.StoreBean;

/**
 * Created by dllo on 17/2/16.
 */

public class StoreRvAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private StoreBean storeBean;
    private Context context;


    public void setStoreBean(StoreBean storeBean) {
        this.storeBean = storeBean;
    }

    public StoreRvAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType==0){
           return MyViewHolder.createViewHolder(context,parent, R.layout.store_upper_layout);
       }else {
           return MyViewHolder.createViewHolder(context,parent,R.layout.store_downer_layout);
       }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.whriteText(storeBean.getData().)


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
