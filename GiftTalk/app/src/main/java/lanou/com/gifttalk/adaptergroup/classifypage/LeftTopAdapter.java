package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.classifypage.LeftTopBean;

/**
 * Created by dllo on 17/2/18.
 */

public class LeftTopAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private LeftTopBean.DataBean.ColumnsBean topBean;


    private LeftTopBean.DataBean dataBean;

    public void setDataBean(LeftTopBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }
    public void setTopBean(LeftTopBean.DataBean.ColumnsBean topBean) {
        this.topBean = topBean;
        notifyDataSetChanged();
    }

    public LeftTopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.createViewHolder(context,parent, R.layout.class_lefttop_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.drawImage(dataBean.getColumns().get(position).getBanner_image_url(),R.id.iv_lefttop_line);
        holder.writeText(dataBean.getColumns().get(position).getTitle(),R.id.tv_lefttop);
        holder.writeText(dataBean.getColumns().get(position).getAuthor(),R.id.tv_lefttop_nickname);
    }

    @Override
    public int getItemCount() {
        return dataBean.getColumns().size()>0?dataBean.getColumns().size():0;

    }
}
