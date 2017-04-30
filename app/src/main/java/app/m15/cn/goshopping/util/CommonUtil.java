package app.m15.cn.goshopping.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by liueg on 2017/2/3.
 */

public class CommonUtil {

    public static void startActivity(Context context,Class cla){
        context.startActivity(new Intent(context,cla));
    }

}
