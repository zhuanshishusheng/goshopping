package app.m15.cn.goshopping.fragment.sort;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.bean.GoodBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class GoodsActivity extends BaseActivity implements View.OnClickListener, MyRecyclerViewAdapter.OnItemClickListener {
    private List<GoodBean.DataBean> mGoodList;

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(GoodsActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    adapter = new MyRecyclerViewAdapter(GoodsActivity.this,mGoodList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(GoodsActivity.this);
                    break;

            }
        }
    };
    private Gson gson;
    private ImageView mReturn;
    private TextView mGoodText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        init();
    }


    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mReturn.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        OKHttpManager okhttpManager=OKHttpManager.getInstance();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String type=bundle.getString("type");
        String request=null;
        if("search".equals(type)){
            request=RequestUtil.REQUEST_SEARCH+bundle.getString("name");
        }else if("sort".equals(type)){
            request=RequestUtil.REQUEST_HEAD + "/good?type=" + bundle.getString("name");
        }
        okhttpManager.getString(request, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {


                sendSuccessMessage(string);

            }
        });
        mGoodText.setText(bundle.getString("text"));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL));

    }
    private void sendErrorMessage(){
        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccessMessage(String string){
       GoodBean gooodBean=gson.fromJson(string,GoodBean.class);
        mGoodList=gooodBean.getData();
        Message message=new Message();
        message.arg1=2;
        handler.sendMessage(message);
    }
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.goods_recylerview);
        mReturn = (ImageView)findViewById(R.id.sort_goods_return);
        mGoodText =(TextView)findViewById(R.id.sort_good_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sort_goods_return:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this,GoodsDetialActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("good",mGoodList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
