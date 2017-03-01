package lanou.com.gifttalk.fragment.itempage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.com.gifttalk.R;

/**
 * Created by dllo on 17/2/25.
 */

public class SencondLevelFragmentMid extends Fragment {

    private String url;
    private WebView webView;
    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.secondleve_mid,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView= (WebView) view.findViewById(R.id.wv_secondlevel);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        WebSettings webSettings=webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        

        if (url==null){
            webView.loadUrl("http://hawaii.liwushuo.com/items/1081032");
        }
        webView.loadUrl(url);
    }
}
