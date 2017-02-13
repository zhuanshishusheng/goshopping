package app.m15.cn.goshopping.activity.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RadioGroup;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.fragment.home.HomeFragment;
import app.m15.cn.goshopping.fragment.me.MeFragment;
import app.m15.cn.goshopping.fragment.sort.SortFragment;

/**
 * Created by liueg on 2017/2/3.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,MainContact.View {
    private MainPresenter mMainPresenter;
    private RadioGroup mMainRgControl;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter=new MainPresenter(this);
        mMainPresenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.destroyView();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
         switch (i){
             case R.id.main_rb_home:
                 replaceFragment(R.id.main_frame_content, HomeFragment.getInstance());
                 break;
             case R.id.main_rb_sort:
                 replaceFragment(R.id.main_frame_content, SortFragment.getInstance());
                 break;
             case R.id.main_rb_me:
                 replaceFragment(R.id.main_frame_content, MeFragment.getInstance());
                 break;
         }
    }

    @Override
    public void initView() {
        mMainRgControl = (RadioGroup)findViewById(R.id.main_rg_control);
    }

    @Override
    public void initData() {
        mFragmentManager = getFragmentManager();
        replaceFragment(R.id.main_frame_content,HomeFragment.getInstance());
    }

    @Override
    public void initListener() {
        mMainRgControl.setOnCheckedChangeListener(this);
    }

    @Override
    public void replaceFragment(int i,Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(i,fragment)
                .commit();
    }

}
