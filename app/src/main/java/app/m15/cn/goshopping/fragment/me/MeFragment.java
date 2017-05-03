package app.m15.cn.goshopping.fragment.me;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.util.CommonUtil;
import app.m15.cn.goshopping.view.MeSelectView;

/**
 * Created by liueg on 2017/2/3.
 */

public class MeFragment extends Fragment implements MeContact.View, View.OnClickListener {
    private static MeFragment sInstance;
    private View mView;
    private MePresenter mPresenter;
    private MeSelectView mSelectLove;

    public static MeFragment getInstance() {
        if (sInstance == null) {
            sInstance = new MeFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_me,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new MePresenter(this);
        mPresenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.destroyView();
    }

    @Override
    public void initView() {
        mSelectLove = (MeSelectView)mView.findViewById(R.id.me_select_love);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mSelectLove.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_select_love:
                CommonUtil.startActivity(getActivity(),MyLoveActivity.class);
                break;
        }
    }
}
