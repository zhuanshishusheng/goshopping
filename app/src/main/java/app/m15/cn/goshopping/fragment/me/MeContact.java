package app.m15.cn.goshopping.fragment.me;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class MeContact {
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
