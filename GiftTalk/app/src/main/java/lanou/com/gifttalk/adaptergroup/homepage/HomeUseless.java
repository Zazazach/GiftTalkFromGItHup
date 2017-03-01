package lanou.com.gifttalk.adaptergroup.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.homepage.DetailAct;
import lanou.com.gifttalk.adaptergroup.MyViewHolder;

/**
 * Created by dllo on 17/2/23.
 */

public class HomeUseless extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private String detailUrl;
    private int LOADING=10;

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
        notifyDataSetChanged();
    }

    public HomeUseless(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==LOADING){
            return MyViewHolder.createViewHolder(context,parent,R.layout.useless_line);
        }
        return MyViewHolder.createViewHolder(context,parent,R.layout.useless_line);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        WebView webView=holder.getView(R.id.wb_detail_home);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(detailUrl);

        Toast.makeText(context, "到最后一行了", Toast.LENGTH_SHORT).show();





    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
       if (position+1==getItemCount()){

           return LOADING;
       }
        return position;
    }
}
