package lanou.com.gifttalk.activity.homepage;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;
import lanou.com.gifttalk.adaptergroup.homepage.HomeUseless;
import lanou.com.gifttalk.bean.homepage.ChildBean;

/**
 * Created by dllo on 17/2/23.
 */

public class DetailAct extends BaseActivity {
    private static final String TAG = "DetailAct";
    private String detailUrl;
    private int urlPostion;
    private ChildBean bean;

    private PullToRefreshWebView webView;


    @Override
    public int bindLayout() {
        return R.layout.detail_home_layout;
    }

    @Override
    public void initView() {

       // swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sr_detail_home);
        webView= (PullToRefreshWebView) findViewById(R.id.pull_webview_detail_home);
       // recyclerView= (RecyclerView) findViewById(R.id.rv_swipe);
    }

    @Override
    public void initData() {
        findViewById(R.id.iv_detail_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent=getIntent();
        bean=  intent.getParcelableExtra("url");
        urlPostion = intent.getIntExtra("pos",0);
        Log.d(TAG, "urlPostion:" + urlPostion);


//        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);


        //    adapter.setDetailUrl(detailUrl);

        //用swipe来下拉加载上一篇文章
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                if (urlPostion>0){
//                    detailUrl= bean.getData().getItems().get(urlPostion-1).getUrl();
//    //                adapter.setDetailUrl(detailUrl);
//                }else {
//                    Toast.makeText(DetailAct.this, " 已经是第一页啦 ㄟ(▔,▔)ㄏ ", Toast.LENGTH_SHORT).show();
//                }
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });

        webView.setMode(PullToRefreshBase.Mode.BOTH);

        webView.getRefreshableView().setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        WebSettings webSettings=webView.getRefreshableView().getSettings();
        webSettings.setJavaScriptEnabled(true);

        detailUrl= bean.getData().getItems().get(urlPostion).getUrl();
        webView.getRefreshableView().loadUrl(detailUrl);



        //使用PUlltoWebView来上拉下拉
        webView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<WebView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<WebView> pullToRefreshBase) {
                final ILoadingLayout layRefresh=webView.getLoadingLayoutProxy(true,false);

                if (urlPostion>0) {

                    detailUrl = bean.getData().getItems().get(urlPostion--).getUrl();
                    webView.getRefreshableView().loadUrl(detailUrl);

                    layRefresh.setReleaseLabel(" 松手加载上一篇 ");
                    layRefresh.setRefreshingLabel(" 正在努力加载 (〃＞皿＜) ");

                }else {

                    layRefresh.setReleaseLabel(" 已经是第一篇了 (〃＞皿＜) ");
                    layRefresh.setRefreshingLabel(" 已经是第一篇了 (〃＞皿＜) ");
                }

                Log.e(TAG, "onPullUpToRefresh: ----"+urlPostion );
                webView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<WebView> pullToRefreshBase) {

                final ILoadingLayout layLoading=webView.getLoadingLayoutProxy(false,true);
                detailUrl = bean.getData().getItems().get(urlPostion ++).getUrl();
                webView.getRefreshableView().loadUrl(detailUrl);
                Log.e(TAG, "onPullUpToRefresh: ++++"+urlPostion );
                layLoading.setReleaseLabel(" 松手加载下一篇 (〃＞皿＜) ");
                layLoading.setRefreshingLabel(" 正在努力加载 (〃＞皿＜) ");
                webView.onRefreshComplete();
            }
        });





        //通过位置的变化 来加载下一篇文章
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (newState==RecyclerView.SCROLL_INDICATOR_BOTTOM&& recyclerState+1==adapter.getItemCount()){
//
//
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                recyclerState =layoutManager.findLastVisibleItemPosition();
//                Log.d(TAG, "recyclerState:" + recyclerState);
//                Log.d(TAG, "dy:" + dy);
//                if (dy > 200){
//                    detailUrl= bean.getData().getItems().get(urlPostion+1).getUrl();
//                    adapter.setDetailUrl(detailUrl);
//                    Toast.makeText(DetailAct.this, " 努力加载下一篇 (〃＞皿＜) ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });










    }
}
