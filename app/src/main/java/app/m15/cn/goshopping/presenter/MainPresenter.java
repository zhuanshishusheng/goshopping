package app.m15.cn.goshopping.presenter;

import android.app.Fragment;

import app.m15.cn.goshopping.view.MainView;

/**
 * Created by liueg on 2017/2/4.
 */

public class MainPresenter implements MainPresenterIble {
    private MainView mMainView;

    public MainPresenter(MainView mainView){
        this.mMainView=mainView;
    }
    @Override
    public void replaceFragment(int i, Fragment fragment) {
        mMainView.replaceFragment(i,fragment);
    }
}
