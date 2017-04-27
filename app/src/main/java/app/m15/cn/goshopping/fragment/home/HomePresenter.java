package app.m15.cn.goshopping.fragment.home;

/**
 * Created by 赵鹏 on 2017/4/23.
 */

public class HomePresenter implements HomeContact.Presenter {
    private HomeContact.View mHomeView;

    public HomePresenter(HomeContact.View view) {
        mHomeView = view;
    }

    @Override
    public void init() {
        mHomeView.initView();
        mHomeView.initData();
        mHomeView.initListener();
    }
}
