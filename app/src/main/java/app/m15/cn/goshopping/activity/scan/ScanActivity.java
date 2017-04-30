package app.m15.cn.goshopping.activity.scan;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate, ScanContact.View, View.OnClickListener {
    private String uri=null;
    private ScanPresenter mScanPresenter;
    private ZXingView mQRCodeView;
    private ImageView mScanResultImageView;
    private LinearLayout mScanResult;
    private TextView mScanResultTextView;
    private TextView mScanResultOpenButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mScanPresenter=new ScanPresenter(this);
        mScanPresenter.init();
    }

    @Override
    public void initView() {
        mQRCodeView = (ZXingView)findViewById(R.id.zxingview);
        mScanResult = (LinearLayout)findViewById(R.id.scan_result_ll);
        mScanResultImageView = (ImageView)findViewById(R.id.scan_result_iv);
        mScanResultTextView = (TextView)findViewById(R.id.scan_result_tv);
        mScanResultOpenButton = (TextView)findViewById(R.id.scan_result_open);
    }

    @Override
    public void initData() {
        mQRCodeView.setDelegate(this);
    }

    @Override
    public void initListener() {
        mScanResultImageView.setOnClickListener(this);
        mScanResultOpenButton.setOnClickListener(this);
    }

    @Override
    public void showScanResult(String result) {
        uri=result;
        mScanResult.setVisibility(View.VISIBLE);
        mScanResultTextView.setText("您将打开一个链接地址:"+result);
    }

    @Override
    public void deleteScanResult() {
        mScanResult.setVisibility(View.GONE);
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mScanResult.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQRCodeView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();
        showScanResult(result);

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "打开相机出错", Toast.LENGTH_SHORT).show();
    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scan_result_iv:
                deleteScanResult();
                break;
            case R.id.scan_result_open:
                mScanPresenter.openBrowser(this,uri);
                break;
        }
    }
}
