package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.classifypage.LeftBottomBean;

/**
 * Created by dllo on 17/2/18.
 */

public class LeftBottomAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;

    private List<LeftBottomBean.DataBean.ChannelGroupsBean.ChannelsBean> bottomBeanList;



    public void setBottomBean(List<LeftBottomBean.DataBean.ChannelGroupsBean.ChannelsBean>  bottomBeanList) {
        this.bottomBeanList = bottomBeanList;
        notifyDataSetChanged();
    }

    public LeftBottomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.createViewHolder(context,parent, R.layout.class_leftbottom_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.drawImage(bottomBeanList.get(position).getCover_image_url(),R.id.iv_class_leftbottom_line);
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
