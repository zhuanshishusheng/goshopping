package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class MessageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mReturn;
    private TextView mGo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mReturn.setOnClickListener(this);
        mGo.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mReturn=(ImageView)findViewById(R.id.me_message_return);
        mGo =(TextView)findViewById(R.id.me_message_go);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_message_return:
                finish();
                break;
            case R.id.me_message_go:
                finish();
                break;
        }

    }
}
