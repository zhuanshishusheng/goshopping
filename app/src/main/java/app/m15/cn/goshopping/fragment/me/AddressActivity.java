package app.m15.cn.goshopping.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ListView;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class AddressActivity extends BaseActivity {

    private ListView mAddressListLv;
    private ImageView mReturn;

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

    }

    private void initData() {

    }

    private void initView() {
        mAddressListLv =(ListView)findViewById(R.id.me_address_list);
        mReturn =(ImageView)findViewById(R.id.me_message_return);

    }
}
