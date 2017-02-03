package app.m15.cn.goshopping.base;

import android.app.Application;

/**
 * Created by liueg on 2017/2/3.
 */

public class GSApplication extends Application {
    private static GSApplication mApplication;

    public static GSApplication getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
