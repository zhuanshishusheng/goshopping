package app.m15.cn.goshopping.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RadioGroup;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.fragment.HomeFragment;
import app.m15.cn.goshopping.fragment.MeFragment;
import app.m15.cn.goshopping.fragment.SortFragment;
import app.m15.cn.goshopping.presenter.MainPresenter;
import app.m15.cn.goshopping.view.MainView;

/**
 * Created by liueg on 2017/2/3.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,MainView {
    private MainPresenter mMainPresenter;
    private RadioGroup mMainRgControl;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter=new MainPresenter(this);
        init();
    }

    private void init() {
        mMainRgControl = (RadioGroup)findViewById(R.id.main_rg_control);
        mMainRgControl.setOnCheckedChangeListener(this);
        mFragmentManager = getFragmentManager();
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
    public void replaceFragment(int i,Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(i,fragment)
                .commit();
    }

    @Override
    public void showDefaultFragment() {
        
    }
}
