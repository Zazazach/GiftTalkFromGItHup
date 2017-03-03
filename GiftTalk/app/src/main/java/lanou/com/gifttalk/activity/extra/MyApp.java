package lanou.com.gifttalk.activity.extra;

import android.app.Application;
import android.content.Context;

import lanou.com.gifttalk.greendao.DaoMaster;
import lanou.com.gifttalk.greendao.DaoSession;

/**
 * Created by dllo on 17/2/23.
 */

public class MyApp extends Application {

    public static Context context;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }


    public static DaoMaster getDaoMaster(){
        if (daoMaster==null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "build.db", null);
            daoMaster = new DaoMaster(helper.getWritableDb());
            return daoMaster;
        }
        return daoMaster;
    }


    public static DaoSession getDaoSession() {

        daoSession = getDaoMaster().newSession();
        return daoSession;


    }
}
