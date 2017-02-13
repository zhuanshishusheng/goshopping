package app.m15.cn.goshopping.activity.main;

import android.app.Fragment;

/**
 * Created by liueg on 2017/2/10.
 */

public class MainContact {
    interface View{
        void initView();
        void initData();
        void initListener();
        void replaceFragment(int i,Fragment fragment);
    }
    interface Presenter{
        void init();
        void destroyView();
    }
    //获取网络数据的接口
    interface Support{

    }
}
