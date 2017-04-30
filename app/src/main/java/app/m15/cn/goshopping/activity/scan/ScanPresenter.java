package app.m15.cn.goshopping.activity.scan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ScanPresenter implements ScanContact.Presenter {

    private ScanContact.View mScanView;

    public ScanPresenter(ScanContact.View view) {
        mScanView = view;
    }

    @Override
    public void init() {
        mScanView.initView();
        mScanView.initData();
        mScanView.initListener();
    }

    @Override
    public void openBrowser(Context context, String url) {
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri=Uri.parse(url);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
