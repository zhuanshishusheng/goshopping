package app.m15.cn.goshopping.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 赵鹏 on 2017/4/10.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         * 常用的图片加载库
         * Universal Image Loader:一个强大的图片加载库，包含各种各样的配置，最老牌，使用最广泛
         * Picasso:Square 出品 和OkHttp搭配更好
         * Volley ImageLoader:Google官方出品，不能加载本地图片
         * Fresco:facebokk出的
         * Glide:Google推荐的图片加载库，专注于流畅的滚动
         */
        //Glide加载图片简单用法

//        if(GSApplication.getsUserinfo()!=null){
//            Glide.with(context).load(RequestUtil.REQUEST_HEAD+ GSApplication.getsUserinfo().getImageUrl())
//                    .placeholder(R.mipmap.me_no_login)
//                    .error(R.mipmap.me_no_login)
//                    .into(imageView);
//
//            return;
//        }
        Glide.with(context).load(path).into(imageView);

//
//        //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView);

        //用fre加载图片简单用法
//        Uri uri=Uri.parse((String)path);
//        imageView.setImageURI(uri);
    }














}
