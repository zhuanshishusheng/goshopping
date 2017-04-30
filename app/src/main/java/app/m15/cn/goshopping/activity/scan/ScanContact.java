package app.m15.cn.goshopping.activity.scan;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ScanContact {
    interface View{
        void initView();
        void initData();
        void initListener();
        void showScanResult(String result);
        void deleteScanResult();
    }

    interface Presenter{
        void init();
        //调用默认浏览器
        void openBrowser(Context context, String url);
    }
}
