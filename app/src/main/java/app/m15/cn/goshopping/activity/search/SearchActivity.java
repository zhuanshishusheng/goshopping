package app.m15.cn.goshopping.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.base.BaseActivity;
import app.m15.cn.goshopping.fragment.sort.GoodsActivity;
import app.m15.cn.goshopping.net.OKHttpManager;


/**
 * Created by Administrator on 2017/5/1 0001.
 * http://www.jianshu.com/p/3682f6536e49
 */

public class SearchActivity extends BaseActivity implements SearchView.SearchClick {

    private SearchView mSearchView;
    private OKHttpManager manager;
    private Gson gson;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case 1:
                    Toast.makeText(SearchActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:

                    break;

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

    }

    private void init() {
        mSearchView=(SearchView)findViewById(R.id.search_view);
        mSearchView.setOnSearchListener(this);
        manager = OKHttpManager.getInstance();
        gson = new Gson();
    }

    @Override
    public void onSearchClick(String string) {

        Intent intent=new Intent();
        intent.setClass(this,GoodsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("name",string);
        bundle.putString("type","search");
        bundle.putString("text","搜索结果");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
