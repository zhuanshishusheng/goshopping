package app.m15.cn.goshopping.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import app.m15.cn.goshopping.R;

/**
 * Created by liueg on 2017/2/10.
 */

public class MeSelectView extends View {
    private float mTextSize;
    private String mText;
    private int mImgId;
    private Paint mTextPaint;
    private Bitmap mImg;

    public MeSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MeSelectView,0,0);
        mTextSize=typeArray.getDimension(R.styleable.MeSelectView_viewTextSize,16);
        mText = typeArray.getString(R.styleable.MeSelectView_viewText);
        mImgId = typeArray.getResourceId(R.styleable.MeSelectView_icon,R.mipmap.me_love_icon);
    }

    public MeSelectView(Context context) {
        this(context,null);
    }

    public MeSelectView(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }
    private void init() {
        mImg = BitmapFactory.decodeResource(getResources(), mImgId);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mImg,getHeight()/2-mImg.getWidth()/2,getHeight()/2-mImg.getHeight()/2,new Paint());
        canvas.drawText(mText, getWidth() / 3 -getHeight(), getHeight() / 2+mTextSize/3, mTextPaint);
        //画跳转icon
        Bitmap skipBitmap= BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.skip_icon);
        canvas.drawBitmap(skipBitmap,getWidth()-getHeight()/2,getHeight()/2-skipBitmap.getHeight()/2,new Paint());
    }
}
