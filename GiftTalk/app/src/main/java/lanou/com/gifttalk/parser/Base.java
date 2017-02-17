package lanou.com.gifttalk.parser;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/13.
 */

public class Base implements RunMethod {
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Handler handler=new Handler(Looper.getMainLooper());


    public Base() {
        gson=new Gson();

        okHttpClient=new OkHttpClient.Builder().retryOnConnectionFailure(true)
                .retryOnConnectionFailure(true).connectTimeout(2,TimeUnit.SECONDS)
                .build();

    }



    @Override
    public <T> void praser(String url, final Class<T> tClass, final ParseMethod<T> parseMethod) {
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String str=response.body().string();
                final T content=gson.fromJson(str,tClass);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        parseMethod.onSucceed(content);
                    }
                });
            }
        });
    }
}
