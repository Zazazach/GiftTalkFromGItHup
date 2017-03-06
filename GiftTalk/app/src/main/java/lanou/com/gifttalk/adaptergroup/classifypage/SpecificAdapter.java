package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.classifypage.SpecificBean;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

/**
 * Created by dllo on 17/3/4.
 */

public class SpecificAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private SpecificBean bean;

    public int getPOSITION() {
        return POSITION;
    }

    private int POSITION;


    public void setBean(SpecificBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public SpecificAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return MyViewHolder.createViewHolder(context,parent, R.layout.fragmentitem_rv_line);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        POSITION = position;
        holder.drawImage(bean.getData().getItems().get(position).getCover_image_url(),R.id.iv_fragmentitem_cover,context);
        holder.writeText(bean.getData().getItems().get(position).getPrice(),R.id.tv_fragmentitem_price);
        holder.writeText(bean.getData().getItems().get(position).getName(),R.id.tv_fragmentitem_name);
        holder.getView(R.id.tv_fragmentitem_top).setVisibility(View.GONE);
        holder.getView(R.id.tv_fragmentitem_description).setVisibility(View.GONE);

        if (position+1==getItemCount()){

        }
    }

    @Override
    public int getItemCount() {
        return bean!=null?bean.getData().getItems().size():0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
