package lanou.com.gifttalk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.fragment.classifypage.FragmentClassify;
import lanou.com.gifttalk.fragment.homepage.FragmentHome;
import lanou.com.gifttalk.fragment.itempage.FragmentItem;
import lanou.com.gifttalk.fragment.minepage.FragmentMine;
import lanou.com.gifttalk.fragment.storepage.FragmentStore;

/**
 * Created by dllo on 17/2/10.
 */

public class ActMajor extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioButton home,item,store,classify,mine;
    private RadioGroup radioGroup;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager=getSupportFragmentManager();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmajor_layout);
        radioGroup= (RadioGroup) findViewById(R.id.rg_actmajor);
        home= (RadioButton) findViewById(R.id.rb_home);

        radioGroup.setOnCheckedChangeListener(this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_actmajor,new FragmentHome());
        fragmentTransaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        transaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.rb_home:
                transaction.replace(R.id.fl_actmajor,new FragmentHome());
                break;
            case R.id.rb_item:
                transaction.replace(R.id.fl_actmajor,new FragmentItem());
                break;
            case R.id.rb_store:
                transaction.replace(R.id.fl_actmajor,new FragmentStore());
                break;
            case R.id.rb_classify:
                transaction.replace(R.id.fl_actmajor,new FragmentClassify());

                break;
            case R.id.rb_mine:
                transaction.replace(R.id.fl_actmajor,new FragmentMine());

                break;

        }

        transaction.commit();

    }


}