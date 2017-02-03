package app.m15.cn.goshopping.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by liueg on 2017/2/3.
 */

public class ActivityTaskUtil {
    public static ActivityTaskUtil sInstance;
    private Stack<Activity> mActivityStack;

    static {
        sInstance = new ActivityTaskUtil();
    }

    private ActivityTaskUtil() {
        mActivityStack = new Stack<>();
    }

    public static ActivityTaskUtil getInstance() {
        return sInstance;
    }

    public void addActivity(Activity activity){
        mActivityStack.add(activity);
    }

    public void removeActivity(Activity activity){
        mActivityStack.remove(activity);
    }

    public void removeAllActivity(){
         while (!mActivityStack.empty()){
             mActivityStack.pop().finish();
         }
    }
}
