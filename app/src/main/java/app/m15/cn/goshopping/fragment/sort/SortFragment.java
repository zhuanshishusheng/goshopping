package app.m15.cn.goshopping.fragment.sort;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.m15.cn.goshopping.R;

/**
 * Created by liueg on 2017/2/4.
 */

public class SortFragment extends Fragment {
    private static SortFragment sInstance;
    private View mView;

    public static SortFragment getInstance() {
        if (sInstance == null) {
            sInstance = new SortFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sort, null);
        return mView;
    }
}