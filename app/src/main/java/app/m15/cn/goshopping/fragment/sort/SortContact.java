package app.m15.cn.goshopping.fragment.sort;

public class SortContact {

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
