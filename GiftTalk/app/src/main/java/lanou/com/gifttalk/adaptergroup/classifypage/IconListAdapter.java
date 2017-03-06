package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.bean.classifypage.IndividualityBean;

/**
 * Created by dllo on 17/2/18.
 */

public class IconListAdapter extends BaseAdapter {
    private List<String> iconlist,iconName;
    private int linkagePos;

    public void setLinkagePos(int linkagePos) {
        this.linkagePos = linkagePos;
    }

    private IndividualityBean bean;
    private Context context;
    private GridViewAdapter gridViewAdapter;
    private InnerMyHolder holder;

    public void setBean(IndividualityBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }


    public void setIconlist(List<String> iconlist) {
        this.iconlist = iconlist;
        notifyDataSetChanged();
    }

    public IconListAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return iconlist.size()>0? iconlist.size():0;
    }

    @Override
    public Object getItem(int position) {
        if (linkagePos==0) {
            return iconlist.get(position);
        }else {
            return iconlist.get(linkagePos);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.lv_right_line,parent,false);
            holder=new InnerMyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (InnerMyHolder) convertView.getTag();
        }

        gridViewAdapter=new GridViewAdapter(context);
        gridViewAdapter.setBean(bean.getData().getCategories().get(position));
        holder.gridView.setAdapter(gridViewAdapter);
        holder.gridView.setNumColumns(3);
        holder.title.setText(bean.getData().getCategories().get(position).getName());



        return convertView;
    }

    class  InnerMyHolder {
        GridView gridView;
        View item;
        TextView title;

        public InnerMyHolder(View item) {
            this.item = item;
            gridView= (GridView) item.findViewById(R.id.gv_right_line);
            title= (TextView) item.findViewById(R.id.tv_lv_right);
        }
    }
}
