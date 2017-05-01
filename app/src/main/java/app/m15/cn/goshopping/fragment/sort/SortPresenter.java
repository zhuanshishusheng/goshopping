package app.m15.cn.goshopping.fragment.sort;

import app.m15.cn.goshopping.fragment.sort.SortContact.Presenter;

/**
 * Created by 赵鹏 on 2017/4/8.
 */

public class SortPresenter implements Presenter {

    private SortContact.View mSortView;

    public SortPresenter(SortContact.View view) {
        mSortView = view;
    }

    @Override
    public void init() {
        mSortView.initView();
        mSortView.initData();
        mSortView.initListener();
    }

    @Override
    public void destroyView() {
        if (mSortView != null) {
            mSortView = null;
        }
    }
}
