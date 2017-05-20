package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.bean.RegisterBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private OKHttpManager okhttpManager;
    private RegisterBean mRegisterBean;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(RegisterActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                   Toast.makeText(RegisterActivity.this, mRegisterBean.getError(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

            }
        }
    };
    private Gson gson;
    private Button mRegister;
    private EditText mPasswordAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mRegister.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        okhttpManager = OKHttpManager.getInstance();

    }

    private void initView() {
        mUsername =(EditText)findViewById(R.id.register_username_et);
        mPassword =(EditText)findViewById(R.id.register_password_et);
        mPasswordAgain =(EditText)findViewById(R.id.register_password_again_et);
        mRegister =(Button)findViewById(R.id.register_register);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_register:
                register();
                break;
        }
    }

    private void register() {
        if(!mPassword.getText().toString().equals(mPasswordAgain.getText().toString())){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            mPassword.setText("");
            mPasswordAgain.setText("");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("username",mUsername.getText().toString());
        map.put("password",mPassword.getText().toString());
        okhttpManager.postMap(RequestUtil.REQUEST_REGISTER,map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
                sendSuccessMessage(string);

            }
        });

    }

    private void sendErrorMessage(){

        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccessMessage(String string){
        mRegisterBean=gson.fromJson(string,RegisterBean.class);
        Message message=new Message();

        if("error".equals(mRegisterBean.getResponse())){
            message.arg1=2;
        }else if("register".equals(mRegisterBean.getResponse())){
            message.arg1=3;
        }

        handler.sendMessage(message);
    }
}
