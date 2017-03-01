package lanou.com.gifttalk.activity.extra;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 17/2/23.
 */

public class MyApp extends Application {

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
