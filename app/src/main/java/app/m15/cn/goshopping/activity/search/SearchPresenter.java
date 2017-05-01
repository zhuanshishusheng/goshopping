package app.m15.cn.goshopping.activity.search;

/**
 * Created by Administrator on 2017/4/30 0030.
 */

public class SearchPresenter implements SearchContact.Presenter {
    private SearchContact.View view;

    public SearchPresenter(SearchContact.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.initView();
        view.initData();
        view.initListener();
    }

    @Override
    public void destroyView() {
        if (view != null) {
            view = null;
        }
    }
}
