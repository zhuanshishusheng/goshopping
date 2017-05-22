package app.m15.cn.goshopping.fragment.me;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;
import app.m15.cn.goshopping.view.CustomImageView;
import app.m15.cn.goshopping.view.MeSelectView;

/**
 * Created by liueg on 2017/2/3.
 */

public class MeFragment extends Fragment implements MeContact.View, View.OnClickListener {
    private static MeFragment sInstance;
    private View mView;
    private MePresenter mPresenter;
    private MeSelectView mSelectLove;
    private MeSelectView mSelectHelp;
    private CustomImageView mLogin;
    private TextView mLoginUsername;
    private TextView mUserCar;
    private TextView mOrder;
    private TextView mMessage;
    private MeSelectView mAddress;

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
        mSelectHelp =(MeSelectView)mView.findViewById(R.id.me_select_help);
        mLogin = (CustomImageView)mView.findViewById(R.id.me_login_register);
        mLoginUsername = (TextView)mView.findViewById(R.id.me_user_username);
        mUserCar =(TextView)mView.findViewById(R.id.me_user_car);
        mOrder =(TextView)mView.findViewById(R.id.me_order);
        mMessage =(TextView)mView.findViewById(R.id.me_message);
        mAddress =(MeSelectView)mView.findViewById(R.id.me_select_address);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mSelectLove.setOnClickListener(this);
        mSelectHelp.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mUserCar.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_select_love:
                CommonUtil.startActivity(getActivity(),MyLoveActivity.class);
                break;
            case R.id.me_select_help:
                CommonUtil.startActivity(getActivity(),HelpActivity.class);
                break;
            case R.id.me_login_register:
                CommonUtil.startActivity(getActivity(),LoginActivity.class);
                break;
            case R.id.me_user_car:
                enterCar();
                break;
            case R.id.me_order:
                enterOrder();
                break;
            case R.id.me_message:
                enterMessage();
                break;
            case R.id.me_select_address:
                enterAddress();
                break;
        }
    }

    private void enterAddress() {
        if(GSApplication.getsUserinfo()!=null){
            CommonUtil.startActivity(getActivity(),AddressActivity.class);
        }else {
            Toast.makeText(getActivity(), "用户未登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void enterMessage() {
        if(GSApplication.getsUserinfo()!=null){
            CommonUtil.startActivity(getActivity(),MessageActivity.class);
        }else {
            Toast.makeText(getActivity(), "用户未登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void enterOrder() {
        if(GSApplication.getsUserinfo()!=null){
            CommonUtil.startActivity(getActivity(),OrderActivity.class);
        }else {
            Toast.makeText(getActivity(), "用户未登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void enterCar() {
        if(GSApplication.getsUserinfo()!=null){
            CommonUtil.startActivity(getActivity(),CarActivity.class);
        }else {
            Toast.makeText(getActivity(), "用户未登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showUserinfo();
    }

    private void showUserinfo() {
        if(GSApplication.getsUserinfo()!=null){
            mLoginUsername.setText("用户:"+GSApplication.getsUserinfo().getUsername());
            //网络加载图片
            Glide.with(getActivity()).load(RequestUtil.REQUEST_HEAD+GSApplication.getsUserinfo().getImageUrl())
                    .placeholder(R.mipmap.me_no_login)
                    .into(mLogin);
            //Log.i("ceshi",RequestUtil.REQUEST_HEAD+GSApplication.getsUserinfo().getImageUrl());
        }
    }
}
