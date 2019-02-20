package com.smeiling.learning.dashline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: Smeiling
 * @Date: 2019-01-18 15-24
 * @Description: 竖直虚线
 */
public class VerticalDashLineView extends View {

    private Paint paint;
    /**
     * 线宽
     */
    private float lineWidth = 15f;
    /**
     * 颜色
     */
    private int lineColor = Color.parseColor("#eeeeee");
    /**
     * 间隔距离
     */
    private float gapWidth = 45;

    private DashPathEffect dashPathEffect;

    public VerticalDashLineView(Context context) {
        this(context, null);
    }

    public VerticalDashLineView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VerticalDashLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        dashPathEffect = new DashPathEffect(new float[]{gapWidth, gapWidth}, 0);
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setGapWidth(float gapWidth) {
        this.gapWidth = gapWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//宽的测量大小，模式
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//高的测量大小，模式
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = widthSpecSize;   //定义测量宽，高(不包含测量模式),并设置默认值，查看View#getDefaultSize可知
        int h = heightSpecSize;

        //处理wrap_content的几种特殊情况
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            h = 0;
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            //只要宽度布局参数为wrap_content， 宽度给固定值200dp(处理方式不一，按照需求来)
            w = 400;
            //按照View处理的方法，查看View#getDefaultSize可知
            h = heightSpecSize;
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            w = widthSpecSize;
            h = 0;
        }
        //给两个字段设置值，完成最终测量
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        paint.setColor(lineColor);
        paint.setPathEffect(dashPathEffect);
        canvas.drawLine(0, 0, 0, getHeight(), paint);
    }
}
