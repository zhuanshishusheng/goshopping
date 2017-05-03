package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class MyLoveActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mReturnIv;
    private LinearLayout mLoadingAnim;
    private ImageView mLoadingFail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_mylove);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingAnim.setVisibility(View.GONE);
                mLoadingFail.setVisibility(View.VISIBLE);
            }
        },2000);
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mReturnIv.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mReturnIv = (ImageView)findViewById(R.id.me_select_love_return);
        mLoadingAnim = (LinearLayout)findViewById(R.id.loading_animation);
        mLoadingFail = (ImageView)findViewById(R.id.me_mylove_fail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_select_love_return:
                finish();
                break;
        }
    }
}
