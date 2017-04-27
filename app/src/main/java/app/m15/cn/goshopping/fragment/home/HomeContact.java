package app.m15.cn.goshopping.fragment.home;

/**
 * Created by 赵鹏 on 2017/4/23.
 */

public class HomeContact {
    interface View{
        void initView();
        void initData();
        void initListener();
        void initBanner();
    }
    interface Presenter{
        void init();
    }
    interface Support{

    }
}
