package app.m15.cn.goshopping.activity.search;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import app.m15.cn.goshopping.R;


/**
 * Created by Administrator on 2017/5/1 0001.
 */

public class SearchView extends LinearLayout {
    private Context context;

    /*UI组件*/
    private TextView tv_clear;
    private ClearEditText et_search;
    private TextView tv_tip;
    private TextView iv_search;

    /*列表及其适配器*/
    private SearchListView listView;
    private BaseAdapter adapter;

    /*数据库变量*/
    private RecordSQLiteOpenHelper helper ;
    private SQLiteDatabase db;
    private ImageView iv_reurn;


    /*三个构造函数*/
    //在构造函数里直接对搜索框进行初始化 - init()
    public SearchView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    /*初始化搜索框*/
    private void init(){

        //初始化UI组件
        initView();


        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);

        // 第一次进入时查询所有的历史记录
        queryData("");

        //"清空搜索历史"按钮
        tv_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //清空数据库
                deleteData();
                queryData("");
            }
        });

        //搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
                    tv_tip.setText("搜索历史");
                } else {
                    tv_tip.setText("搜索结果");
                }

                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = et_search.getText().toString();
                queryData(tempName);

            }
        });


        // 搜索框的键盘搜索键
        // 点击回调
        et_search.setOnKeyListener(new OnKeyListener() {// 输入完后按键盘上的搜索键


            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 隐藏键盘，这里getCurrentFocus()需要传入Activity对象，如果实际不需要的话就不用隐藏键盘了，免得传入Activity对象，这里就先不实现了
//                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
//                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(et_search.getText().toString().trim());
                    if (!hasData) {
                        insertData(et_search.getText().toString().trim());

                        queryData("");
                    }
                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    Toast.makeText(context, "点击搜索", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });



        //列表监听
        //即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //获取到用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                et_search.setText(name);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            }
        });

//        // 插入数据，便于测试，否则第一次进入没有数据怎么测试呀？
//        Date date = new Date();
//        long time = date.getTime();
//        insertData("Leo" + time);

        //点击搜索按钮后的事件
        iv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasData = hasData(et_search.getText().toString().trim());
                if (!hasData) {
                    insertData(et_search.getText().toString().trim());

                    queryData("");
                }else if(onSearchListener!=null){
                    onSearchListener.onSearchClick(et_search.getText().toString().trim());
                }

            }
        });
       iv_reurn.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               if(getContext() instanceof Activity){
                   SearchView.this.destroyActivity((Activity) getContext());
               }
           }
       });


    }



    /**
     * 封装的函数
     */

    /*初始化组件*/
    private void initView(){
        LayoutInflater.from(context).inflate(R.layout.search_layout,this);
        et_search = (ClearEditText) findViewById(R.id.et_search);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        listView = (SearchListView) findViewById(R.id.listView);
        iv_search = (TextView) findViewById(R.id.iv_search_tv);
        iv_reurn = (ImageView)findViewById(R.id.search_return);
    }

    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {

        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象,装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    private void destroyActivity(Activity activity){
         activity.finish();
    }
    public SearchClick onSearchListener;
    public void setOnSearchListener(SearchClick onSearchListener){
        if(this.onSearchListener==null){
            this.onSearchListener=onSearchListener;
        }
    }

    public static interface SearchClick{
        void onSearchClick(String string);
    }
}
