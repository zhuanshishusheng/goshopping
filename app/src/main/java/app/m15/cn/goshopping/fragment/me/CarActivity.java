package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.CarBean;
import app.m15.cn.goshopping.bean.GoodInfoBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class CarActivity extends BaseActivity {

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
                    break;

            }
        }
    };
    private Gson gson;
    private CarBean mCarBean;
    private ListView mCarList;
    private CarListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }
    private void initView() {
        mCarList =(ListView)findViewById(R.id.me_car_list);

    }
    private void initListener() {

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
                sendSuccessMessage(string);
            }
        });
    }

    private void sendErrorMessage() {

        Message message = new Message();
        message.arg1 = 1;
        handler.sendMessage(message);
    }

    private void sendSuccessMessage(String string) {
        mCarBean = gson.fromJson(string, CarBean.class);
        Message message = new Message();


        message.arg1 = 2;


        handler.sendMessage(message);
    }

    class CarListAdapter extends BaseAdapter{

        private ViewHolder mHolder;
        private GoodInfoBean mGoodInfo;
        public Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.arg1){
                    case 3:
                    mHolder.mDescribe.setText(mGoodInfo.getProduct().getDescribe());
//                        mHolder.mPrice.setText(mGoodInfo.getProduct().getPrice());
                        break;
                }

            }
        };

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
            mHolder=holder;
            //获取商品信息
            String url=RequestUtil.REQUEST_HEAD+"/goodinfo"+"?goodid="+mCarBean.getData().get(i).getGoodid();
            okhttpManager.getString(url, new OKHttpManager.HttpCallBack() {
                @Override
                public void onError(Exception e) {
                    sendErrorMessage();
                }

                @Override
                public void onSuccess(String string) {
                    mGoodInfo=gson.fromJson(string,GoodInfoBean.class);

                    Message message=new Message();
                    message.arg1=3;
                    handler.sendMessage(message);
                }
            });


            return view;
        }

        class ViewHolder{
            ImageView mImageView;
            TextView mDescribe;
            TextView mPrice;
        }
    }
}
