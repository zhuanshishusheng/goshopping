package app.m15.cn.goshopping.util;

import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liueg on 2017/2/3.
 */

public class CommonUtil {
    private static final String FORMAT_TYPE="yyyy-MM-dd HH:mm:ss";

    public static void startActivity(Context context,Class cla){
        context.startActivity(new Intent(context,cla));
    }
    public static String dateToString(Date data) {
        return new SimpleDateFormat(FORMAT_TYPE).format(data);
    }
    public static String longToString(long currentTime)
           {
               Date date = null; // long类型转成Date类型
               try {
                   date = longToDate(currentTime);
               } catch (ParseException e) {
                   e.printStackTrace();
               }
               String strTime = dateToString(date); // date类型转成String
        return strTime;
    }
    public static Date stringToDate(String strTime)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TYPE);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    public static Date longToDate(long currentTime)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime); // 把String类型转换为Date类型
        return date;
    }
}
