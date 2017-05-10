package app.m15.cn.goshopping.net;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liueg on 2017/2/7.
 */

public class OKHttpManager {
    //超时时间
    public static final int CONNECT_OUT_TIME = 1000 * 60;
    private static OKHttpManager sInstance = null;
    private OkHttpClient mOkHttpClient;

    private OKHttpManager() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OKHttpManager getInstance() {
        if (sInstance == null) {
            sInstance = new OKHttpManager();
        }
        return sInstance;
    }

    /**
     * 在Application里获取client可以配置
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * get 请求
     * 无body
     * 获取proto字符串
     *
     * @param url
     * @param httpCallBack
     */
    public void getString(String url, final HttpCallBack httpCallBack) {
        //创建okHttp请求
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpCallBack.onSuccess(response.body().string());
            }
        });
    }

    /**
     * post请求
     * map为body
     *
     * @param url          请求地址
     * @param map          请求body
     * @param httpCallBack 接口回调
     */
    public void postMap(String url, Map<String, String> map, final HttpCallBack httpCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        //遍历map
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody body = builder.build();
        final Request request = new Request.Builder().url(url).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpCallBack.onSuccess(response.body().string());
            }
        });
    }


    public interface HttpCallBack {
        void onError(Exception e);

        void onSuccess(String string);
    }
}
