package app.m15.cn.goshopping.activity.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/5/1 0001.
 */

public class SearchListView extends ListView {
    public SearchListView(Context context) {
        super(context);
    }

    public SearchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
       通过复写onMeasure方法，达到对ScrollView适配的效果
        */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expanSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expanSpec);
    }
}
