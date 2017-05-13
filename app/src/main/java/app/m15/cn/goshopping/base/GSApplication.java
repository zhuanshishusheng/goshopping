package app.m15.cn.goshopping.base;

import android.app.Application;

import app.m15.cn.goshopping.bean.LoginBean;

/**
 * Created by liueg on 2017/2/3.
 */

public class GSApplication extends Application {
    private static LoginBean.DataBean sUserinfo=null;

    public static LoginBean.DataBean getsUserinfo(){
        return sUserinfo;
    }
    public static void setsUserinfo(LoginBean.DataBean dataBean){
        sUserinfo=dataBean;
    }

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
