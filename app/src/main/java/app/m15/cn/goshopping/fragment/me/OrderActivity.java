package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class OrderActivity extends BaseActivity {

    private OKHttpManager okHttpManager;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
    }

    private void initData() {
        okHttpManager = OKHttpManager.getInstance();
        gson = new Gson();
        Map<String,String> map=new HashMap<>();
        map.put("userid", GSApplication.getsUserinfo().getUserid()+"");
        map.put("type",0+"");
        okHttpManager.postMap(RequestUtil.REQUEST_ORDER_LIST, map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(String string) {
                Log.i("ceshi",string);

            }
        });

    }

    private void initView() {

    }
}
