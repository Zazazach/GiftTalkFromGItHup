package lanou.com.gifttalk.adaptergroup.storepage;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.storepage.StoreDownerBean;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;

/**
 * Created by dllo on 17/2/16.
 */

public class StoreRvAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private StoreUpperBean storeUpperBean;

    private StoreDownerBean storeDownerBean;
    private Context context;
    private RecyclerView upperRecycler;
    private StoreUpperRvAdapter upperRvAdapter;
    private final int PIC=0;
    private final int HRV=1;
    private final int GRV=2;
    int itemPostion=0;
    private int picPositon = 0;


    public void setStoreDownerBean(StoreDownerBean storeDownerBean) {
        this.storeDownerBean = storeDownerBean;
        notifyDataSetChanged();
    }

    public void setStoreUpperBean(StoreUpperBean storeUpperBean) {
        this.storeUpperBean = storeUpperBean;
        notifyDataSetChanged();
    }

    public StoreRvAdapter(Context context) {

        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==PIC){

            return MyViewHolder.createViewHolder(context, parent, R.layout.store_upper_typeone);

        }else if (viewType==HRV){

            return MyViewHolder.createViewHolder(context, parent, R.layout.store_upper_typr_two);
        }
        else {
            return MyViewHolder.createViewHolder(context, parent, R.layout.store_downer_line);
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int type=getItemViewType(position);

        switch (type){
            case PIC:

                holder.drawImage(storeUpperBean.getData().getItems().get(position/2).getCover_image_url(),R.id.iv_store_type_one);
                holder.writeText(storeUpperBean.getData().getItems().get(position/2).getTitle(),R.id.tv_store_type_one);

                break;
            case HRV:

                upperRecycler= (RecyclerView) holder.getLineView().findViewById(R.id.rv_store_upper_type_two);
                upperRvAdapter=new StoreUpperRvAdapter(context);
                upperRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                upperRecycler.setAdapter(upperRvAdapter);

                upperRvAdapter.setStoreUpperBean(storeUpperBean.getData().getItems().get(position/2));

                break;
            case GRV:

                StoreDownerBean.DataBean.ItemsBean itemsBean = storeDownerBean.getData().getItems().get(position - 8);
                holder.drawImage(itemsBean.getCover_image_url(),R.id.iv_storedowner_cover);
                holder.writeText(itemsBean.getShort_description(),R.id.tv_storedowner_title);
                holder.writeText(itemsBean.getTitle(),R.id.tv_storedowner_shortdescription);

                break;
        }


    }

    @Override
    public int getItemCount() {

        return storeDownerBean.getData().getItems()!=null?storeDownerBean.getData().getItems().size()+8:0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0||position==2||position==4||position==6){
            return PIC;
        }else if (position==1||position==3||position==5||position==7){
            return HRV;
        }else
            return GRV;
    }

}
