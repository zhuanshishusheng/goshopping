package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class HelpActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        init();
    }

    private void init() {
        mReturn =(ImageView)findViewById(R.id.me_select_help_return);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_select_help_return:
                finish();
                break;
        }
    }
}
