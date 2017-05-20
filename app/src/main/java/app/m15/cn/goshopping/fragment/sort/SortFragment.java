package app.m15.cn.goshopping.fragment.sort;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.activity.scan.ScanActivity;
import app.m15.cn.goshopping.activity.search.SearchActivity;
import app.m15.cn.goshopping.bean.SortBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;
import app.m15.cn.goshopping.util.GlideCacheUtil;

/**
 * Created by liueg on 2017/2/4.
 */

public class SortFragment extends Fragment implements SortContact.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener,SortContact.Support, AdapterView.OnItemClickListener {
    private List<SortBean.DataBean> mSortList;

    private static SortFragment sInstance;
    private View mView;
    private GridView mGridView;
    private SortPresenter mPresenter;
    private TextView mSortScanTv;
    private TextView mSortSearch;
    private RadioGroup mSortRadioGroup;
    private OKHttpManager okHttpManager;
    private Gson gson;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    mGridView.setAdapter(mGridViewAdapter);
                    break;
                case 3:
                    GlideCacheUtil.getInstance().clearImageMemoryCache(getActivity());
                    mGridViewAdapter.notifyDataSetChanged();
                    break;

            }
        }
    };
    private GridViewAdapter mGridViewAdapter;

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
        mSortList=new ArrayList<>();
        gson=new Gson();
        okHttpManager = OKHttpManager.getInstance();
        mGridViewAdapter = new GridViewAdapter(getActivity());
        mSortRadioGroup.check(R.id.sort_select_recommend);
        //获取默认信息
        changSortList("recommend", new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
                sendSuccess(string,2);
            }
        });
    }
    private void sendErrorMessage(){
        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccess(String string,int type){
        SortBean sortBean=gson.fromJson(string,SortBean.class);
        mSortList=sortBean.getData();
        Message message=new Message();
        message.arg1=type;
        handler.sendMessage(message);
    }
    @Override
    public void initListener() {
        mSortScanTv.setOnClickListener(this);
        mSortSearch.setOnClickListener(this);
        mSortRadioGroup.setOnCheckedChangeListener(this);
        mGridView.setOnItemClickListener(this);
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
        switch(i){
            case R.id.sort_select_recommend:
                changSortList("recommend", new OKHttpManager.HttpCallBack() {
                    @Override
                    public void onError(Exception e) {
                        sendErrorMessage();
                    }

                    @Override
                    public void onSuccess(String string) {
                        sendSuccess(string,3);
                    }
                });
                break;
            case R.id.sort_select_jacket:
                changSortList("jacket", new OKHttpManager.HttpCallBack() {
                    @Override
                    public void onError(Exception e) {
                        sendErrorMessage();
                    }

                    @Override
                    public void onSuccess(String string) {
                        sendSuccess(string,3);
                    }
                });
                break;
            default:
                changSortList("recommend", new OKHttpManager.HttpCallBack() {
                    @Override
                    public void onError(Exception e) {
                        sendErrorMessage();
                    }

                    @Override
                    public void onSuccess(String string) {
                        sendSuccess(string,3);
                    }
                });
        }

    }

    @Override
    public void changSortList(String string, OKHttpManager.HttpCallBack callBack) {
        String url=RequestUtil.REQUEST_HEAD + "/sort?type="+string;
        okHttpManager.getString(url,callBack);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          Intent intent=new Intent();
         intent.setClass(getActivity(),GoodsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("name",mSortList.get(i).getName());
        bundle.putString("type","sort");
        bundle.putString("text",mSortList.get(i).getName());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    class GridViewAdapter extends BaseAdapter{
        private Context mContext;
        public GridViewAdapter(Context context){
            mContext=context;
        }
        @Override
        public int getCount() {
            return mSortList.size();
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
            viewHolder.mTextView.setText(mSortList.get(position).getName());
            //网络加载图片
            Glide.with(getActivity()).load(RequestUtil.REQUEST_HEAD+mSortList.get(position).getImageUrl())
                    .placeholder(R.mipmap.sort_error_load)
                    .into(viewHolder.mImageView);

            return convertView;
        }

        class ViewHolder{
            TextView mTextView;
            ImageView mImageView;
        }
    }

    public interface CallBack{
        public void getResult(String string);
    }
}
