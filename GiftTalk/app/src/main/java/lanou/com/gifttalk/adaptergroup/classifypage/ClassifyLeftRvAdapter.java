package lanou.com.gifttalk.adaptergroup.classifypage;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.classifypage.LeftTopBean;
import lanou.com.gifttalk.bean.classifypage.LeftBottomBean;

/**
 * Created by dllo on 17/2/18.
 */

public class ClassifyLeftRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private LeftBottomBean leftBottomBean;
    private LeftTopBean leftTopBean;
    private final int HRV =0;
    private final int VRV =1;
    int botTimes =0;
    int topTimes =0;

    private RecyclerView topRv,botRv;
    private LeftTopAdapter topAdapter;
    private LeftBottomAdapter bottomAdapter;

    public void setLeftBottomBean(LeftBottomBean leftBottomBean) {
        this.leftBottomBean = leftBottomBean;
        notifyDataSetChanged();
    }

    public void setLeftTopBean(LeftTopBean leftTopBean) {
        this.leftTopBean = leftTopBean;
        notifyDataSetChanged();
    }

    public ClassifyLeftRvAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==HRV){
            return MyViewHolder.createViewHolder(context,parent, R.layout.class_lefttop);
        }else {
            return MyViewHolder.createViewHolder(context,parent,R.layout.class_leftdown);
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int type=getItemViewType(position);


        if (type==HRV){
            topRv= (RecyclerView) holder.getLineView().findViewById(R.id.rv_lefttop);
            topAdapter=new LeftTopAdapter(context);
            topRv.setLayoutManager(new GridLayoutManager(context,3, LinearLayoutManager.HORIZONTAL,false));;
            topRv.setAdapter(topAdapter);
            topAdapter.setDataBean(leftTopBean.getData());

            if (topTimes<=leftTopBean.getData().getColumns().size()){
                topAdapter.setTopBean(leftTopBean.getData().getColumns().get(topTimes));
            }else {
                topTimes=0;
                topAdapter.setTopBean(leftTopBean.getData().getColumns().get(topTimes));
            }
            topTimes++;

        }else {



            botRv= (RecyclerView) holder.getLineView().findViewById(R.id.rv_class_leftdown);
            botRv.setSaveEnabled(false);
            bottomAdapter=new LeftBottomAdapter(context);



//            if (botTimes <=leftBottomBean.getData().getChannel_groups().size()){
//                bottomAdapter.setBottomBean(leftBottomBean.getData().getChannel_groups().get(botTimes));
//                Log.d("ClassifyLeftRvAdapter", "botTimes:1" + botTimes);
//                botTimes++;
//            }else {
//                botTimes =0;
//           //     bottomAdapter.setBottomBean(leftBottomBean.getData().getChannel_groups().get(botTimes));
//                Log.d("ClassifyLeftRvAdapter", "botTimes:2" + botTimes);
//            }

            botRv.setLayoutManager(new GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false));
            botRv.setAdapter(bottomAdapter);

            bottomAdapter.setBottomBean(leftBottomBean.getData().getChannel_groups().get(position-1).getChannels());

            holder.writeText(leftBottomBean.getData().getChannel_groups().get(position-1).getName(),R.id.tv_class_leftdown);
        }



    }

    @Override
    public int getItemCount() {
        return leftBottomBean.getData().getChannel_groups()==null ? 1 : leftBottomBean.getData().getChannel_groups().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return HRV;
        }else {
            return VRV;
        }
    }
}
