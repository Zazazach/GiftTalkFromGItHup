package lanou.com.gifttalk.adaptergroup.storepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;
import lanou.com.gifttalk.bean.storepage.StoreUpperBean;

/**
 * Created by dllo on 17/2/27.
 */

public class SecStoreRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private StoreUpperBean.DataBean.ItemsBeanX toDetailBean;
    private int interPosition;

    public void setInterPosition(int interPosition) {
        this.interPosition = interPosition;
        notifyDataSetChanged();
    }

    public void setToDetailBean(StoreUpperBean.DataBean.ItemsBeanX toDetailBean) {
        this.toDetailBean = toDetailBean;
        notifyDataSetChanged();
    }

    public SecStoreRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            return MyViewHolder.createViewHolder(context,parent, R.layout.secstorerv_detail_line);
        }else if (viewType==1){
            return MyViewHolder.createViewHolder(context,parent,R.layout.secstorerv_choice_line);
        }else {
            return MyViewHolder.createViewHolder(context,parent,R.layout.secstorerv_rv_line);
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int type=getItemViewType(position);
        if (type==0){

            holder.setBanner(R.id.banner_secstore_detail_line, (ArrayList<String>) toDetailBean.getItems().get(position).getImage_urls());
            holder.writeText(toDetailBean.getItems().get(interPosition).getShort_description(),R.id.tv_secstore_detail_line_shortdescription);
            holder.writeText(toDetailBean.getItems().get(interPosition).getTitle(),R.id.tv_secstore_detail_line_title);
            holder.writeText("¥"+toDetailBean.getItems().get(interPosition).getSkus().get(0).getPrice(),R.id.tv__secstore_detail_line_price);
            holder.writeText(""+toDetailBean.getItems().get(interPosition).getQuota(),R.id.tv__secstore_detail_line_like);
        }else if (type==1){

            holder.writeText(toDetailBean.getItems().get(interPosition).getSpecs_domains().get(0).getTitle()+",1个",R.id.tv_secstore_choice_line_choice);

        }else {

            Log.d("SecStoreRvAdapter", "行布局");
            //TODO webview 转换html
            String detailHtml=toDetailBean.getItems().get(interPosition).getDetail_html();
            WebView webView=holder.getView(R.id.iv_secstorerv_rv_line);
            String html="<html><body>"+detailHtml+"</html></body>";
            webView.loadDataWithBaseURL(null, html, "text/html","utf-8", null);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
            });

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);


        }


    }

    @Override
    public int getItemCount() {
        Log.d("SecStoreRvAdapter", "toDetailBean.getItems().size():" + toDetailBean.getItems().size());
        return toDetailBean.getItems().size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
