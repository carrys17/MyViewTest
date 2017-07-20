package com.example.shang.myviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shang on 2017/7/20.
 */



// 自定义View 重写onDraw 方法，此时需要自己支持wrap_content和padding
public class CircleView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);  // 记得修改这里
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载自定义属性CircleView
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        // 解析CircleView属性集合中的circle_color
        // 如果xml中circle_color没有设置，则为绿色
        mColor = array.getColor(R.styleable.CircleView_circle_color,Color.GREEN);
        array.recycle();
        init();
    }

    private void init(){
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置支持padding属性
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;
        int radius = Math.min(width,height)/2;
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);
    }

    // 设置支持wrap_content
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        // 设置默认的wrap_content为400
        if (heightSpecMode==MeasureSpec.AT_MOST&&widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(400,400);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,400);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(400,heightSpecSize);
        }

    }
}
