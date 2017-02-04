package app.m15.cn.goshopping.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;

/**
 * Created by liueg on 2017/2/3.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mMainRgControl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mMainRgControl = (RadioGroup)findViewById(R.id.main_rg_control);
        mMainRgControl.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
