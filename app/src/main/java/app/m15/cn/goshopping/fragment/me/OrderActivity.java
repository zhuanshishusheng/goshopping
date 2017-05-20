package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.OrderBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class OrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private OKHttpManager okHttpManager;
    private Gson gson;
    private RadioGroup mOrderRg;
    private RadioButton mOrderAllRb;
    private ListView mOrderListLv;
    private List<OrderBean.OrderListBean> mOrderList=new ArrayList<>();

    private boolean isFirst=true;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(OrderActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    if(isFirst){
                        adapter = new OrderListAdapter();
                        mOrderListLv.setAdapter(adapter);
                        isFirst=false;
                    }else{
                        mOrderListLv.setAdapter(adapter);
                       adapter.notifyDataSetChanged();
                    }

                    break;

            }
        }
    };
    private OrderListAdapter adapter;
    private ImageView mReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mOrderRg.setOnCheckedChangeListener(this);
        mReturn.setOnClickListener(this);
    }

    private void initData() {
        okHttpManager = OKHttpManager.getInstance();
        gson = new Gson();

        sendType(0);


    }
    private void sendErrorMessage(){
        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccess(String string,int type){
        OrderBean orderBean=gson.fromJson(string,OrderBean.class);
        mOrderList=orderBean.getOrderList();
        Message message=new Message();
        message.arg1=type;
        handler.sendMessage(message);

    }

    private void initView() {
        mOrderRg = (RadioGroup) findViewById(R.id.order_rg);
        mOrderRg.check(R.id.order_all_rb);
        mOrderListLv =(ListView)findViewById(R.id.order_list);
        mReturn =(ImageView)findViewById(R.id.me_order_return);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.order_all_rb:
                sendType(0);
                break;
            case R.id.order_daifukuan_rb:
                sendType(1);
                break;
            case R.id.order_daifahuo_rb:
                sendType(2);
                break;
            case R.id.order_daishouhuo:
                sendType(3);
                break;
            case R.id.order_daipingjia_rb:
                sendType(4);
                break;
        }
    }

    private void sendType(int type) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", GSApplication.getsUserinfo().getUserid() + "");
        map.put("type", type + "");
        okHttpManager.postMap(RequestUtil.REQUEST_ORDER_LIST, map, new OKHttpManager.HttpCallBack() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_order_return:
                finish();
                break;
        }
    }

    class OrderListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mOrderList.size();
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

          OrderListAdapter.ViewHolder holder=null;
            if(view==null){
                view=LayoutInflater.from(OrderActivity.this).inflate(R.layout.item_order_list,null);
                holder=new OrderListAdapter.ViewHolder();
                holder.mOrderid=(TextView)view.findViewById(R.id.item_order_orderid);
                holder.mPrice=(TextView)view.findViewById(R.id.item_order_price);
                holder.mStatus=(TextView)view.findViewById(R.id.item_order_status);
                holder.mTime=(TextView)view.findViewById(R.id.item_order_time);
                view.setTag(holder);
            }
            holder= (OrderListAdapter.ViewHolder) view.getTag();
            holder.mOrderid.setText(mOrderList.get(i).getOrderid());
           holder.mStatus.setText(mOrderList.get(i).getType());
            holder.mPrice.setText("￥"+mOrderList.get(i).getPrice()+".00");
            holder.mTime.setText(CommonUtil.longToString(Long.parseLong(mOrderList.get(i).getTime())));
            return view;
        }

        class ViewHolder{
            TextView mOrderid;
            TextView mStatus;
            TextView mPrice;
            TextView mTime;
        }
    }
}
