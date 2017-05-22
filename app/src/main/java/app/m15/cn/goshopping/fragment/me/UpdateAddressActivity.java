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
import app.m15.cn.goshopping.bean.AddressBean;
import app.m15.cn.goshopping.bean.LoginBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class UpdateAddressActivity extends BaseActivity implements View.OnClickListener {
    private EditText mName;
    private EditText mPhone;
    private EditText mProvince;
    private EditText mCity;
    private EditText mDetail;
    private EditText mArea;
    private ImageView mReturn;
    private TextView mDelete;
    private TextView mSave;
    private OKHttpManager okHttpManager;
    private Gson gson;
    private CheckBox mDefault;

    private LoginBean mLoginBean;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(UpdateAddressActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    GSApplication.setsUserinfo(mLoginBean.getData());
                    Toast.makeText(UpdateAddressActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    CommonUtil.startActivity(UpdateAddressActivity.this,AddressActivity.class);
                    UpdateAddressActivity.this.finish();
                    break;
                case 3:
                    Toast.makeText(UpdateAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    CommonUtil.startActivity(UpdateAddressActivity.this,AddressActivity.class);
                    UpdateAddressActivity.this.finish();
                    break;

            }
        }
    };
    private AddressBean.AddressListBean mAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateaddress);

        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mReturn.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mSave.setOnClickListener(this);

    }

    private void initData() {
        okHttpManager = OKHttpManager.getInstance();
        gson = new Gson();

        mAddress =(AddressBean.AddressListBean) getIntent().getExtras().get("address");
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.update_address_name);
        mPhone = (EditText) findViewById(R.id.update_address_phone);
        mProvince = (EditText) findViewById(R.id.update_address_province);
        mCity = (EditText) findViewById(R.id.update_address_city);
        mDetail = (EditText) findViewById(R.id.update_address_detail);
        mArea = (EditText) findViewById(R.id.update_address_area);
        mReturn = (ImageView) findViewById(R.id.me_update_address_return);
        mDelete = (TextView) findViewById(R.id.me_address_delete);
        mSave = (TextView) findViewById(R.id.me_address_save);
        mDefault = (CheckBox) findViewById(R.id.update_address_default);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_update_address_return:
                finish();
                break;
            case R.id.me_address_delete:
                deleteAddress();
                break;
            case R.id.me_address_save:
                updateAddress();
                break;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAdressInfo();
    }

    private void showAdressInfo() {
        mName.setText(mAddress.getName());
        mPhone.setText(mAddress.getPhone());
        mProvince.setText(mAddress.getProvince());
        mCity.setText(mAddress.getCity());
        mArea.setText(mAddress.getArea());
        mDetail.setText(mAddress.getDetail());
        if(GSApplication.getsUserinfo().getArea()==mAddress.getId()){
            mDefault.setChecked(true);
        }else{
            mDefault.setChecked(false);
        }
    }

    private void updateAddress() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        map.put("userid", GSApplication.getsUserinfo().getUserid() + "");
        map.put("name", mName.getText().toString());
        map.put("phone", mPhone.getText().toString());
        map.put("province", mProvince.getText().toString());
        map.put("city", mCity.getText().toString());
        map.put("detail", mDetail.getText().toString());
        map.put("area", mArea.getText().toString());
        map.put("id",mAddress.getId()+"");
        map.put("isDefault", mDefault.isChecked() + "");
        okHttpManager.postMap(RequestUtil.REQUEST_ADDRESS_UPDATE, map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
                sendSuccess(string, 2);
            }
        });


    }

    private void sendErrorMessage() {
        Message message = new Message();
        message.arg1 = 1;
        handler.sendMessage(message);
    }

    private void sendSuccess(String string, int type) {

        switch (type){
            case 2:
                mLoginBean = gson.fromJson(string, LoginBean.class);
                break;
            case 3:
                break;
        }


        Message message = new Message();
        message.arg1 = type;
        handler.sendMessage(message);

    }

    private void deleteAddress() {
        Map<String, String> map = new HashMap<>();
        map.put("id",mAddress.getId()+"");

        okHttpManager.postMap(RequestUtil.REQUEST_ADDRESS_DELETE, map,new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
               sendSuccess(string,3);
            }
        });

    }
}
