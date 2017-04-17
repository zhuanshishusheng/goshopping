package app.m15.cn.goshopping.fragment.sort;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import app.m15.cn.goshopping.R;

/**
 * Created by liueg on 2017/2/4.
 */

public class SortFragment extends Fragment {
    private static SortFragment sInstance;
    private View mView;
    private GridView mGridView;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mGridView = (GridView) mView.findViewById(R.id.sort_gridview);
    }

    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
