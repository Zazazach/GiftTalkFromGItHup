package lanou.com.gifttalk.activity.extra;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/2/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());

        initView();

        initData();
    }

    public abstract int bindLayout();

    public abstract void initView();

    public abstract void initData();

    public <T extends View> T bindView(int resId){
        return (T) findViewById(resId);
    }
}
