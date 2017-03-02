package lanou.com.gifttalk.adaptergroup.storepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.inter.ItemClicker;

/**
 * Created by dllo on 17/3/1.
 */

public class PopRvAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private ArrayList<String> list;
    private ItemClicker itemClicker;

    public void setItemClicker(ItemClicker itemClicker) {
        this.itemClicker = itemClicker;
        notifyDataSetChanged();
    }

    public PopRvAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return MyViewHolder.createViewHolder(context,parent, R.layout.popinner_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.writeText(list.get(position),R.id.tv_popinner);
        holder.getView(R.id.tv_popinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicker.itemClicker(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
