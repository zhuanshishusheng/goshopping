package app.m15.cn.goshopping.activity.main;

import android.app.Fragment;

/**
 * Created by liueg on 2017/2/4.
 */

public class MainPresenter implements MainContact.Presenter {
    private MainContact.View mMainView;

    public MainPresenter(MainContact.View mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void init() {
        mMainView.initView();
        mMainView.initData();
        mMainView.initListener();
    }

    @Override
    public void destroyView() {
        if (mMainView != null) {
            mMainView = null;
        }
    }
}
