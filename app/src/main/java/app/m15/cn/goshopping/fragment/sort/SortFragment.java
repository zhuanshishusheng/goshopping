package app.m15.cn.goshopping.fragment.sort;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.activity.scan.ScanActivity;
import app.m15.cn.goshopping.activity.search.SearchActivity;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by liueg on 2017/2/4.
 */

public class SortFragment extends Fragment implements SortContact.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    /*RadioButton Id*/
    private final String[] rgIds={"推荐","上衣","裙子","裤子","鞋子","男装",
            "女包","配饰","零食","内衣","美妆护肤","日用百货"};

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
    private SortPresenter mPresenter;
    private TextView mSortScanTv;
    private TextView mSortSearch;
    private RadioGroup mSortRadioGroup;

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
        mPresenter = new SortPresenter(this);
        mPresenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.destroyView();
    }


    @Override
    public void initView() {
        mGridView = (GridView) mView.findViewById(R.id.sort_gridview);
        mSortScanTv = (TextView)mView.findViewById(R.id.sort_scan_tv);
        mSortSearch = (TextView) mView.findViewById(R.id.sort_search);
        mSortRadioGroup = (RadioGroup)mView.findViewById(R.id.sort_select_rg);
    }

    @Override
    public void initData() {
        mGridView.setAdapter(new GridViewAdapter(getActivity()));
    }

    @Override
    public void initListener() {
        mSortScanTv.setOnClickListener(this);
        mSortSearch.setOnClickListener(this);
        mSortRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sort_scan_tv:
                CommonUtil.startActivity(getActivity(), ScanActivity.class);
                break;
            case R.id.sort_search:
                CommonUtil.startActivity(getActivity(), SearchActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

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
