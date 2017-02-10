package app.m15.cn.goshopping.activity.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.activity.main.MainActivity;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by liueg on 2017/2/3.
 */

public class WelcomeActivity extends BaseActivity {
    class SkipRunnable implements Runnable{
        private WelcomeActivity mWelcomeActivity;
        public SkipRunnable(WelcomeActivity welcomeActivity){
            this.mWelcomeActivity=welcomeActivity;
        }
        @Override
        public void run() {
            CommonUtil.startActivity(this.mWelcomeActivity,MainActivity.class);
            this.mWelcomeActivity.finish();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMainActivity();
    }

    private void startMainActivity() {
        new Handler().postDelayed(new SkipRunnable(this),3000);
    }
}
