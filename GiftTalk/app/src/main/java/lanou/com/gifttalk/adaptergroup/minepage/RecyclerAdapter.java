package lanou.com.gifttalk.adaptergroup.minepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.greendao.CollectTool;

/**
 * Created by dllo on 17/3/3.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.createViewHolder(context,parent,R.layout.load_rv_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView textView= holder.getView(R.id.tv_line);
        textView.setText(CollectTool.getInstance().queryData().get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return CollectTool.getInstance()==null?0:CollectTool.getInstance().queryData().size();
    }
}
