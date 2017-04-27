package app.m15.cn.goshopping.fragment.sort;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import app.m15.cn.goshopping.R;

/**
 * Created by liueg on 2017/2/4.
 */

public class SortFragment extends Fragment {
    private static SortFragment sInstance;
    private String[] jacketNames={"蕾丝衫","牛仔衬衫","衬衫","雪纺衫","短袖t","背心","防晒衫",
            "透视衫","针织衫","长袖t","宽松上衣","开衫","小西装"};
    private int[] jacketIds={R.mipmap.sort_jacket_leisishan_img,R.mipmap.sort_jacket_niuzaichenshan_img
    ,R.mipmap.sort_jacket_chenshan_img,R.mipmap.sort_jacket_xuefangshan_img
    ,R.mipmap.sort_jacket_duanxiut_img,R.mipmap.sort_jacket_beixin_img
    ,R.mipmap.sort_jacket_fangshaishan_img,R.mipmap.sort_jacket_toushishan_img
    ,R.mipmap.sort_jacket_zhenzhishan_img,R.mipmap.sort_jacket_changxiut_img
    ,R.mipmap.sort_jacket_kuansongshangyi_img,R.mipmap.sort_jacket_kaishan_img
    ,R.mipmap.sort_jacket_xiaoxizhuang_img};
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
        mGridView.setAdapter(new GridViewAdapter(getActivity()));
    }

    class GridViewAdapter extends BaseAdapter{
        private Context mContext;
        public GridViewAdapter(Context context){
            mContext=context;
        }
        @Override
        public int getCount() {
            return jacketNames.length;
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
            ViewHolder viewHolder=null;
            if(convertView==null){
                convertView=LayoutInflater.from(mContext).inflate(R.layout.item_sort_gridview,null);
                viewHolder=new ViewHolder();
                viewHolder.mImageView=(ImageView) convertView.findViewById(R.id.item_sort_gridview_imageview);
                viewHolder.mTextView=(TextView)convertView.findViewById(R.id.item_sort_gridview_textview);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(jacketNames[position]);
            viewHolder.mImageView.setImageResource(jacketIds[position]);
            return convertView;
        }

        class ViewHolder{
            TextView mTextView;
            ImageView mImageView;
        }
    }
}
