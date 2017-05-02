package app.m15.cn.goshopping.fragment.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.activity.scan.ScanActivity;
import app.m15.cn.goshopping.activity.search.SearchActivity;
import app.m15.cn.goshopping.util.CommonUtil;
import app.m15.cn.goshopping.util.GlideImageLoader;

/**
 * Created by liueg on 2017/2/4.
 */

public class HomeFragment extends Fragment implements HomeContact.View, View.OnClickListener {

    private static HomeFragment sInstance;
    private View mView;
    private Banner mBanner;
    private HomePresenter mPresenter;
    private List<Integer> images;
    private TextView mScanView;
    private TextView mSearchView;

    public static HomeFragment getInstance() {
        if (sInstance == null) {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new HomePresenter(this);
        mPresenter.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        initBanner();
    }

    @Override
    public void initView() {
        mBanner = (Banner)mView.findViewById(R.id.home_banner_view);
        mScanView = (TextView)mView.findViewById(R.id.home_scan_view);
        mSearchView = (TextView)mView.findViewById(R.id.home_search_textview);
    }

    @Override
    public void initData() {
        images = new ArrayList<>();
        images.add(R.mipmap.home_banner1);
        images.add(R.mipmap.home_banner2);
    }

    @Override
    public void initListener() {
        mScanView.setOnClickListener(this);
        mSearchView.setOnClickListener(this);
    }

    @Override
    public void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //开始
        mBanner.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_scan_view:
                CommonUtil.startActivity(getActivity(),ScanActivity.class);
                break;
            case R.id.home_search_textview:
                CommonUtil.startActivity(getActivity(), SearchActivity.class);
                break;
        }
    }
}
