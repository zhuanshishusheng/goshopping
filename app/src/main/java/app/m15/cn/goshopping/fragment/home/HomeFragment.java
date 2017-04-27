package app.m15.cn.goshopping.fragment.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.util.GlideImageLoader;

/**
 * Created by liueg on 2017/2/4.
 */

public class HomeFragment extends Fragment implements HomeContact.View{
    private static HomeFragment sInstance;
    private View mView;
    private Banner mBanner;
    private HomePresenter mPresenter;
    private List<Integer> images;

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
    }

    @Override
    public void initData() {
        images = new ArrayList<>();
        images.add(R.mipmap.home_banner1);
        images.add(R.mipmap.home_banner2);
    }

    @Override
    public void initListener() {

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
}
