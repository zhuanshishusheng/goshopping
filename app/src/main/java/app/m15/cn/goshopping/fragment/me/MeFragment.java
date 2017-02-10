package app.m15.cn.goshopping.fragment.me;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.m15.cn.goshopping.R;

/**
 * Created by liueg on 2017/2/3.
 */

public class MeFragment extends Fragment {
    private static MeFragment sInstance;
    private View mView;

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
}
