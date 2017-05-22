package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.LoginBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private EditText mName;
    private EditText mPhone;
    private EditText mProvince;
    private EditText mCity;
    private ImageView mReturn;
    private TextView mAdd;
    private EditText mDetail;
    private CheckBox mDefault;
    private OKHttpManager okHttpManager;
    private Gson gson;
    private EditText mArea;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(AddAddressActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    GSApplication.setsUserinfo(mLoginBean.getData());
                    Toast.makeText(AddAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    CommonUtil.startActivity(AddAddressActivity.this,AddressActivity.class);
                    AddAddressActivity.this.finish();
                    break;

            }
        }
    };
    private LoginBean mLoginBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();

    }

    private void initListener() {
        mReturn.setOnClickListener(this);
        mAdd.setOnClickListener(this);

    }

    private void initData() {
        okHttpManager = OKHttpManager.getInstance();
        gson = new Gson();


    }

    private void initView() {
        mName =(EditText)findViewById(R.id.add_address_name);
        mPhone =(EditText)findViewById(R.id.add_address_phone);
        mProvince =(EditText)findViewById(R.id.add_address_province);
        mCity =(EditText)findViewById(R.id.add_address_city);
        mDetail =(EditText)findViewById(R.id.add_address_detail);
        mArea =(EditText)findViewById(R.id.add_address_area);
        mReturn =(ImageView)findViewById(R.id.me_new_address_return);
        mDefault =(CheckBox)findViewById(R.id.add_address_default);
        mAdd =(TextView)findViewById(R.id.me_new_address);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_new_address_return:
                finish();
                break;
            case R.id.me_new_address:
                addAddress();
                break;
        }
    }

    private void addAddress() {
        Map<String,String> map=new HashMap<>();
        map.put("type","0");
        map.put("userid", GSApplication.getsUserinfo().getUserid()+"");
        map.put("name",mName.getText().toString());
        map.put("phone",mPhone.getText().toString());
        map.put("province",mProvince.getText().toString());
        map.put("city",mCity.getText().toString());
        map.put("detail",mDetail.getText().toString());
        map.put("area",mArea.getText().toString());
        map.put("isDefault",mDefault.isChecked()+"");
        okHttpManager.postMap(RequestUtil.REQUEST_ADDRESS_UPDATE, map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
               sendSuccess(string,2);
            }
        });

    }
    private void sendErrorMessage(){
        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccess(String string,int type){

        mLoginBean =gson.fromJson(string,LoginBean.class);
        Message message=new Message();
        message.arg1=type;
        handler.sendMessage(message);

    }
}
