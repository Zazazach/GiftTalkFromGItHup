package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.bean.classifypage.IndividualityBean;

/**
 * Created by dllo on 17/2/18.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private IndividualityBean.DataBean.CategoriesBean bean;

    public void setBean(IndividualityBean.DataBean.CategoriesBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }


    public GridViewAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {

        return bean.getSubcategories().size();

    }

    @Override
    public Object getItem(int position) {
        return bean.getSubcategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.class_gridview_line,parent,false);
            holder=new GridHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (GridHolder) convertView.getTag();
        }
        holder.iconName.setText(bean.getSubcategories().get(position).getName());
        Glide.with(context).load(bean.getSubcategories().get(position).getIcon_url()).into(holder.iconPic);

        return convertView;
    }



    class GridHolder{
        TextView iconName;
        ImageView iconPic;
        View item;

        public GridHolder(View item) {
            this.item = item;
            iconName= (TextView) item.findViewById(R.id.tv_gird_line);
            iconPic= (ImageView) item.findViewById(R.id.iv_gird_line);
        }
    }
}
