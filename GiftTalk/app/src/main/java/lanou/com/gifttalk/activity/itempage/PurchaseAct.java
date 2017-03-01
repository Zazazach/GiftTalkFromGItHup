package lanou.com.gifttalk.activity.itempage;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;

/**
 * Created by dllo on 17/2/25.
 */

public class PurchaseAct extends BaseActivity {
    private WebView webView;
    private String url;
    @Override
    public int bindLayout() {
        return R.layout.secondlevel_purchase;
    }

    @Override
    public void initView() {
        webView= (WebView) findViewById(R.id.wv_second_purchase);

    }

    @Override
    public void initData() {

        findViewById(R.id.tv_secondlevel_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        url=intent.getStringExtra("purchase");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (url!=null) {
            webView.loadUrl(url);
        }else {
            Toast.makeText(this, "抱歉,商品已经下架", Toast.LENGTH_SHORT).show();
        }
    }
}
