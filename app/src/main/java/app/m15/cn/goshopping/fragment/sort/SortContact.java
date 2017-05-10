package app.m15.cn.goshopping.fragment.sort;

import app.m15.cn.goshopping.net.OKHttpManager;

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
    interface Support{
        void changSortList(String string,OKHttpManager.HttpCallBack callBack);
    }
}
