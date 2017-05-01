package app.m15.cn.goshopping.activity.search;

/**
 * Created by Administrator on 2017/4/30 0030.
 */

public class SearchContact {
    interface View{
        void initView();
        void initData();
        void initListener();
    }
    interface Presenter{
        void init();
        void destroyView();
    }
}
