package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import lanou.com.gifttalk.R;

/**
 * Created by dllo on 17/2/18.
 */

public class TitleListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private MyHolder holder;
    private int selectedPos ;
    private int redPlace;

    public void setRedPlace(int redPlace) {
        this.redPlace = redPlace;
        notifyDataSetChanged();
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public TitleListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder=null;

        if (convertView==null){
        convertView= LayoutInflater.from(context).inflate(R.layout.class_right_title,parent,false);
            holder= new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }


        holder.textView.setText(list.get(position));

        if (position==redPlace){
            holder.textView.setTextColor(Color.RED);
        }else {
            holder.textView.setTextColor(Color.BLACK);
        }


        return convertView;

    }

    class MyHolder {
        TextView textView;
        View item;
      //  RadioButton radioButton;

        public MyHolder(View item) {
            this.item = item;
         //   radioButton= (RadioButton) item.findViewById(R.id.tv_title_right);
            textView= (TextView) item.findViewById(R.id.tv_title_right);

        }
    }
}
