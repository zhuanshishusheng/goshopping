package app.m15.cn.goshopping.activity.main;

import android.app.Fragment;

/**
 * Created by liueg on 2017/2/10.
 */

public class MainContact {
    interface View{
        void replaceFragment(int i,Fragment fragment);
    }
    interface Presenter{
        void replaceFragment(int i,Fragment fragment);
    }
}
