package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.CarBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class CarActivity extends BaseActivity implements View.OnClickListener {
    private int allPrice;

    private OKHttpManager okhttpManager;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 1:
                    Toast.makeText(CarActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    adapter = new CarListAdapter();
                    mCarList.setAdapter(adapter);
                    for(CarBean.DataBean bean:mCarBean.getData()){
                        allPrice+=bean.getMarketPrice();
                    }
                    mAllPrice.setText("合计:￥"+allPrice+".00");
                    break;
                case 3:
                    Toast.makeText(CarActivity.this, "已结算", Toast.LENGTH_SHORT).show();
                    CarActivity.this.finish();
                    break;

            }
        }
    };
    private Gson gson;
    private CarBean mCarBean;
    private ListView mCarList;
    private CarListAdapter adapter;
    private TextView mAllPrice;
    private Button mCarBalance;
    private ImageView mReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void init() {
        initView();
        initData();
        initListener();
    }
    private void initView() {
        mCarList =(ListView)findViewById(R.id.me_car_list);
        mAllPrice =(TextView)findViewById(R.id.me_car_allprice);
        mCarBalance =(Button)findViewById(R.id.me_car_balance);
        mReturn =(ImageView)findViewById(R.id.me_car_return);

    }
    private void initListener() {
        mCarBalance.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        okhttpManager = OKHttpManager.getInstance();
        String url = RequestUtil.REQUEST_HEAD + "/carlist" + "?userid=" + GSApplication.getsUserinfo().getUserid();
        okhttpManager.getString(url, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                   sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
                sendSuccessMessage(string,2);
            }
        });
    }

    private void sendErrorMessage() {

        Message message = new Message();
        message.arg1 = 1;
        handler.sendMessage(message);
    }

    private void sendSuccessMessage(String string,int type) {
        mCarBean = gson.fromJson(string, CarBean.class);
        Message message = new Message();

        message.arg1 = type;
        handler.sendMessage(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_car_balance:
                if(mCarBean.getData()==null||mCarBean.getData().size()==0){
                    Toast.makeText(this, "购物车没有商品", Toast.LENGTH_SHORT).show();
                }else{
                    balanceGoods();
                }
                break;
            case R.id.me_car_return:
                finish();
                break;
        }
    }

    private void balanceGoods() {

        String url=RequestUtil.REQUEST_HEAD+"/submit";

        Map<String,String> map=new HashMap<>();
        map.put("userid",GSApplication.getsUserinfo().getUserid()+"");
        StringBuffer sku=new StringBuffer();
        for(CarBean.DataBean bean:mCarBean.getData()){
            sku.append(bean.getId()+":"+1+"|");
        }
        sku.deleteCharAt(sku.length()-1);
        map.put("sku",sku.toString());
        map.put("seller","seller");
        map.put("addressid",GSApplication.getsUserinfo().getArea()+"");

        okhttpManager.postMap(url, map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();
            }

            @Override
            public void onSuccess(String string) {
                    sendSuccessMessage(string,3);
            }
        });

    }

    class CarListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mCarBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder=null;
            if(view==null){
                view= LayoutInflater.from(CarActivity.this).inflate(R.layout.item_car_list,null);
                holder=new ViewHolder();
                holder.mImageView=(ImageView) view.findViewById(R.id.item_car_list_img);
                holder.mDescribe=(TextView)view.findViewById(R.id.item_car_list_describe);
                holder.mPrice=(TextView)view.findViewById(R.id.item_car_list_price);
                view.setTag(holder);
            }
            holder= (ViewHolder) view.getTag();
            //网络加载图片
            Glide.with(CarActivity.this).load(RequestUtil.REQUEST_HEAD+mCarBean.getData().get(i).getImageUrl1())
                    .placeholder(R.mipmap.sort_gooddetail_error_img)
                    .into(holder.mImageView);

            holder.mDescribe.setText(mCarBean.getData().get(i).getDescribe());

            holder.mPrice.setText("￥ "+mCarBean.getData().get(i).getMarketPrice()+".00");

            return view;
        }

        class ViewHolder{
            ImageView mImageView;
            TextView mDescribe;
            TextView mPrice;
        }
    }
}
