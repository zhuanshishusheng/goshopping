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

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.LoginBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private OKHttpManager okhttpManager;
    private LoginBean mLoginBean;


    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(LoginActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this, mLoginBean.getError(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_SHORT).show();
                    GSApplication.setsUserinfo(mLoginBean.getData());
                    finish();
                    break;

            }
        }
    };
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mLogin.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        okhttpManager = OKHttpManager.getInstance();

    }

    private void initView() {
        mUsername =(EditText)findViewById(R.id.login_username_et);
        mPassword =(EditText)findViewById(R.id.login_password_et);
        mLogin =(Button)findViewById(R.id.login_login);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_login:
                login();
                break;
        }
    }

    private void login() {
        String username=mUsername.getText().toString();
        String password=mPassword.getText().toString();
        String url= RequestUtil.REQUEST_HEAD+"/login"+"?username="+username+"&"+"password="+password;
        okhttpManager.getString(url, new OKHttpManager.HttpCallBack() {
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
        mLoginBean=gson.fromJson(string,LoginBean.class);
        Message message=new Message();

        if("error".equals(mLoginBean.getResponse())){
            message.arg1=2;
        }else if("login".equals(mLoginBean.getResponse())){
            message.arg1=3;
        }

        handler.sendMessage(message);
    }
}
