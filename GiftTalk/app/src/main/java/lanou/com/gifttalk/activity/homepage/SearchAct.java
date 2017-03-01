package lanou.com.gifttalk.activity.homepage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.com.gifttalk.R;
import lanou.com.gifttalk.activity.extra.BaseActivity;
import lanou.com.gifttalk.adaptergroup.homepage.SearchRvAdapter;
import lanou.com.gifttalk.bean.homepage.SearchBean;
import lanou.com.gifttalk.inter.RvItemClick;
import lanou.com.gifttalk.parser.ParseMethod;
import lanou.com.gifttalk.parser.ParserTool;

import static lanou.com.gifttalk.finaldata.Http.SEARCH;

public class SearchAct extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private SearchRvAdapter adapter;
    private EditText editText;
    private List<String> contenList;
    private SQLiteDatabase db;
    private TextView tvOne,tvTwo,tvThree,tvFour,tvFive,tvSix,tvSeven,tvEight;
    private ImageView delete;



    @Override
    public int bindLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        init();
    }

    @Override
    public void initData() {

        //获取上次数据
        getDataFromLast();

        //清理数据并且设置首行显示或否
        clearHistoryData();

        //Edit与回车的设置 及数据处理
        setOnKey();

        //生成推荐文字
        createText();


        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);
        tvFive.setOnClickListener(this);
        tvSix.setOnClickListener(this);
        tvSeven.setOnClickListener(this);
        tvEight.setOnClickListener(this);

    }





    private void clearHistoryData() {
        adapter.setClick(new RvItemClick() {
            @Override
            public void clickMe(int interPostion) {
                Log.d("SearchAct", "aaa");
                db.execSQL("delete from list");
                contenList=new ArrayList<>();
                adapter.setList(contenList);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.tv_cancel_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataFromLast() {
        db=openOrCreateDatabase("lanou",MODE_PRIVATE,null);
        db.execSQL("create table if not exists list (id integer primary key autoincrement, content text)");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Cursor cursor=db.query("list",null,null,null,null,null,null);
        while (cursor!=null&&cursor.moveToNext()){
            //TODO 从数据库中 拿到键入的值

            String str=cursor.getString(cursor.getColumnIndex("content"));
//            if (str==null){
//                recyclerView.setVisibility(View.INVISIBLE);
//            }
            contenList.add(str);
        }

        cursor.close();

        adapter.setList(contenList);
    }


    private void createText() {
        ParserTool.getInstance().praser(SEARCH, SearchBean.class, new ParseMethod<SearchBean>() {
            @Override
            public void onSucceed(SearchBean something) {
                tvOne.setText(something.getData().getHot_words().get(0));
                tvTwo.setText(something.getData().getHot_words().get(1));
                tvThree.setText(something.getData().getHot_words().get(2));
                tvFour.setText(something.getData().getHot_words().get(3));
                tvFive.setText(something.getData().getHot_words().get(4));
                tvSix.setText(something.getData().getHot_words().get(5));
                tvSeven.setText(something.getData().getHot_words().get(6));
                tvEight.setText(something.getData().getHot_words().get(7));


            }
        });
    }

    private void setOnKey() {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String content=editText.getText().toString();


                if (!contenList.contains(content)) {

                    //与键盘对应 并且判断 按下 还是松手 否则执行两次 MDZZ
                    if (keyCode == KeyEvent.KEYCODE_ENTER && content != null && event.getAction() == KeyEvent.ACTION_DOWN) {
                        contenList.add(content);


                        ContentValues values = new ContentValues();
                        values.put("content", content);

                        db.insert("list", null, values);


                        adapter.setList(contenList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }


                return false;
            }
        });
    }

    private void init() {
        editText= (EditText) findViewById(R.id.et_search_home);
        recyclerView= (RecyclerView) findViewById(R.id.rv_search_home);
        contenList=new ArrayList<>();
        adapter=new SearchRvAdapter(this);

        tvOne=(TextView) findViewById(R.id.tv1_search_home);
        tvTwo=(TextView) findViewById(R.id.tv2_search_home);
        tvThree=(TextView)findViewById(R.id.tv3_search_home);
        tvFour=(TextView) findViewById(R.id.tv4_search_home);
        tvFive=(TextView) findViewById(R.id.tv5_search_home);
        tvSix=(TextView) findViewById(R.id.tv6_search_home);
        tvSeven=(TextView) findViewById(R.id.tv7_search_home);
        tvEight=(TextView) findViewById(R.id.tv8_search_home);

        delete= (ImageView) findViewById(R.id.iv_seach_line);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv1_search_home:
                String contentOne=tvOne.getText().toString();

                if (!contenList.contains(contentOne)) {

                        contenList.add(contentOne);

                        ContentValues values = new ContentValues();
                        values.put("content", contentOne);

                        db.insert("list", null, values);

                        adapter.setList(contenList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);

                }
                break;

            case R.id.tv2_search_home:

                String contentTwo=tvTwo.getText().toString();

                if (!contenList.contains(contentTwo)) {

                    contenList.add(contentTwo);

                    ContentValues values = new ContentValues();
                    values.put("content", contentTwo);

                    db.insert("list", null, values);

                    adapter.setList(contenList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv3_search_home:

                String contentThree=tvThree.getText().toString();

                if (!contenList.contains(contentThree)) {

                    contenList.add(contentThree);

                    ContentValues values = new ContentValues();
                    values.put("content", contentThree);

                    db.insert("list", null, values);

                    adapter.setList(contenList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }

                break;

        }
    }
}
