package app.m15.cn.goshopping.fragment.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.base.GSApplication;
import app.m15.cn.goshopping.bean.AddressBean;
import app.m15.cn.goshopping.net.OKHttpManager;
import app.m15.cn.goshopping.net.RequestUtil;
import app.m15.cn.goshopping.util.CommonUtil;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class AddressActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mAddressListLv;
    private ImageView mReturn;
    private TextView mAdd;
    private List<AddressBean.AddressListBean> mAddressList;
    private AddressListAdapter adapter;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(AddressActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
//
                        adapter = new AddressListAdapter();
                        mAddressListLv.setAdapter(adapter);
//

                    break;

            }
        }
    };
    private OKHttpManager okHttpManager;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mReturn.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mAddressListLv.setOnItemClickListener(this);

    }

    private void initData() {
        okHttpManager = OKHttpManager.getInstance();
        gson = new Gson();

        Map<String,String> map=new HashMap<>();
        map.put("userid", GSApplication.getsUserinfo().getUserid()+"");
        okHttpManager.postMap(RequestUtil.REQUEST_ADDRESS_LIST, map, new OKHttpManager.HttpCallBack() {
            @Override
            public void onError(Exception e) {
                sendErrorMessage();

            }

            @Override
            public void onSuccess(String string) {
                sendSuccess(string);

            }
        });

    }
    private void sendErrorMessage(){
        Message message=new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
    private void sendSuccess(String string){
        AddressBean addressBean=gson.fromJson(string,AddressBean.class);
        mAddressList=addressBean.getAddressList();
        Message message=new Message();
        message.arg1=2;
        handler.sendMessage(message);

    }
    private void initView() {
        mAddressListLv =(ListView)findViewById(R.id.me_address_list);
        mReturn =(ImageView)findViewById(R.id.me_address_return);
        mAdd =(TextView)findViewById(R.id.me_new_address);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_address_return:
                finish();
                break;
            case R.id.me_new_address:
                CommonUtil.startActivity(this,AddAddressActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(this,UpdateAddressActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("address",mAddressList.get(i));
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    class AddressListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mAddressList.size();
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
                view= LayoutInflater.from(AddressActivity.this).inflate(R.layout.item_address_list,null);
                holder=new ViewHolder();
                holder.mName =(TextView)view.findViewById(R.id.item_address_name);
                holder.mPhone=(TextView)view.findViewById(R.id.item_address_phone);
                holder.mDefault=(TextView)view.findViewById(R.id.item_address_isDefault);
                holder.mAddress=(TextView)view.findViewById(R.id.item_address_address);
                view.setTag(holder);
            }
            holder= (ViewHolder) view.getTag();
            holder.mName.setText(mAddressList.get(i).getName());
            holder.mPhone.setText(mAddressList.get(i).getPhone());
            if(GSApplication.getsUserinfo().getArea()==mAddressList.get(i).getId()){
                holder.mDefault.setVisibility(View.VISIBLE);
            }else{
                holder.mDefault.setVisibility(View.GONE);
            }
            holder.mAddress.setText(mAddressList.get(i).getProvince()+mAddressList.get(i).getCity()
            +mAddressList.get(i).getArea()+mAddressList.get(i).getDetail());
            return view;
        }

        class ViewHolder{
            TextView mName;
            TextView mPhone;
            TextView mDefault;
            TextView mAddress;
        }
    }
}
