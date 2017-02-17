package lanou.com.gifttalk.adaptergroup.itempage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.itempage.FRItemChildBean;

/**
 * Created by dllo on 17/2/16.
 */

public class FragmentItemRvAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private FRItemChildBean bean;
    private boolean head;
    private TextView textView;

    private int vpPos;


    public void setVpPos(int vpPos) {
        this.vpPos = vpPos;
        notifyDataSetChanged();
    }

    public void setBean(FRItemChildBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public FragmentItemRvAdapter(Context context) {

        this.context = context;
    }

    public boolean isHeadView(int pos){
        if (pos==0){
            head=true;
        }
        else {
            head=false;
        }
        return head;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("FragmentItemRvAdapter", "xxx");
        if (viewType==0){

            return MyViewHolder.createViewHolder(context,parent, R.layout.fragmentitem_head_line);

        }else {
            return MyViewHolder.createViewHolder(context,parent,R.layout.fragmentitem_rv_line);
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        isHeadView(position);
        textView = (TextView) MyViewHolder.getmView().findViewById(R.id.tv_fragmentitem_top);

        if (position==0){
            holder.whriteImage(bean.getData().getCover_image(),R.id.iv_fragmentitem_head_line);
        }else if (vpPos ==0){
            textView.setVisibility(View.GONE);
            holder.whriteImage(bean.getData().getItems().get(position-1).getCover_image_url(),R.id.iv_fragmentitem_cover);
            holder.whriteText(bean.getData().getItems().get(position-1).getDescription(),R.id.tv_fragmentitem_description);
            holder.whriteText(bean.getData().getItems().get(position-1).getName(),R.id.tv_fragmentitem_name);
            holder.whriteText(bean.getData().getItems().get(position-1).getPrice(),R.id.tv_fragmentitem_price);
        }else {
            textView.setVisibility(View.VISIBLE);
            holder.whriteText("TOP"+position,R.id.tv_fragmentitem_top);
            holder.whriteImage(bean.getData().getItems().get(position-1).getCover_image_url(),R.id.iv_fragmentitem_cover);
            holder.whriteText(bean.getData().getItems().get(position-1).getDescription(),R.id.tv_fragmentitem_description);
            holder.whriteText(bean.getData().getItems().get(position-1).getName(),R.id.tv_fragmentitem_name);
            holder.whriteText(bean.getData().getItems().get(position-1).getPrice(),R.id.tv_fragmentitem_price);
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
