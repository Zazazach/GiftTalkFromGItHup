package lanou.com.gifttalk.adaptergroup.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.inter.RvItemClick;


/**
 * Created by dllo on 17/2/20.
 */

public class SearchRvAdapter  extends RecyclerView.Adapter<MyViewHolder>{

    private List<String> list;
    private int DEFAULT=0;
    private Context context;
    private RvItemClick click;

    public void setClick(RvItemClick click) {
        this.click = click;
        notifyDataSetChanged();
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public SearchRvAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==DEFAULT){
            return MyViewHolder.createViewHolder(context,parent,R.layout.search_default_line);
        }else {
            return MyViewHolder.createViewHolder(context,parent,R.layout.search_line);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        int type=getItemViewType(position);
        if (type!=DEFAULT){
            holder.writeText(list.get(position-1),R.id.tv_seach_line);
        }else {
            ImageView imageView = holder.getView(R.id.iv_deleteall_search_default_line);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.clickMe(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size()+1:0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return DEFAULT;
        }else {
            return position;
        }
    }
}
