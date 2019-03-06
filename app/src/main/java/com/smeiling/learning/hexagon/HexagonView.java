package com.smeiling.learning.hexagon;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.smeiling.learning.R;

/**
 * @Author: Smeiling
 * @Date: 2019-03-05 14-34
 * @Description: 圆角六边形ImageView
 */
public class HexagonView extends ImageView {


    private Paint paint;
    private Path path;


    private int padding = 0;

    private float cornerRadius = 30;

    private int perAngle = 360 / 6;


    private float viewWidth = 400;

    private int startAngle = 0;


    private float cosOffset = (1 - cos()) * (viewWidth / 2);

    private float borderWidth = 5;
    private int borderColor = Color.WHITE;


    private float horizontalOffset;
    private float verticalOffset;


    private float w;

    public HexagonView(Context context) {
        this(context, null);
    }

    public HexagonView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HexagonView);
        borderWidth = typedArray.getDimension(R.styleable.HexagonView_hv_border_width, 5) * 2;
        borderColor = typedArray.getColor(R.styleable.HexagonView_hv_border_color, Color.WHITE);
        cornerRadius = typedArray.getDimension(R.styleable.HexagonView_hv_corner_radius, 10);
        initPaint();
    }

    private void initPaint() {
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(borderWidth);
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//宽的测量大小，模式
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//高的测量大小，模式
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int ww = widthSpecSize;   //定义测量宽，高(不包含测量模式),并设置默认值，查看View#getDefaultSize可知
        int h = heightSpecSize;

        //处理wrap_content的几种特殊情况
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            h = 0;
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            //只要宽度布局参数为wrap_content， 宽度给固定值200dp(处理方式不一，按照需求来)
            ww = 400;
            //按照View处理的方法，查看View#getDefaultSize可知
            h = heightSpecSize;
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            ww = widthSpecSize;
            h = 0;
        }
        //给两个字段设置值，完成最终测量
        setMeasuredDimension(ww, h);
        if (ww > h) {
            viewWidth = h;
            horizontalOffset = (ww - h) / 2;
            verticalOffset = 0;
        } else {
            viewWidth = ww;
            verticalOffset = (h - ww) / 2;
            horizontalOffset = 0;
        }
        w = (int) (viewWidth / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.clipPath(drawHexagonPath(canvas));
        super.onDraw(canvas);
//        drawHexagonPath(canvas);
    }

    /**
     * 绘制圆角六边形路径
     *
     * @param canvas 画布
     */
    public Path drawHexagonPath(Canvas canvas) {

        Log.e("HexagonView", "drawHexagonPath: startAngle=" + startAngle);

        startAngle = 0;

        float[] coordinateSets = {
                viewWidth - (w - w * cos()) - cornerRadius, (3 * w - (cornerRadius / cos())) / 2,

                w, viewWidth - (cornerRadius / cos()),

                w - w * cos() + cornerRadius, (3 * w - (cornerRadius / cos())) / 2,

                w - w * cos() + cornerRadius, (w + (cornerRadius / cos())) / 2,

                w, (cornerRadius / cos()),

                2 * w - (w - w * cos()) - cornerRadius, (w + (cornerRadius / cos())) / 2
        };

        path.addArc(createRectF(coordinateSets[0], coordinateSets[1]), startAngle, perAngle);
        for (int i = 2; i < 12; i += 2) {
            startAngle += perAngle;
            path.arcTo(createRectF(coordinateSets[i], coordinateSets[i + 1]), startAngle, perAngle);
        }
        path.close();
        canvas.drawPath(path, paint);

        return path;
    }

    /**
     * 创建圆角形状
     *
     * @param x
     * @return
     */
    private RectF createRectF(float x, float y) {
        return new RectF(horizontalOffset + padding + x - cornerRadius + borderWidth / 2,
                verticalOffset + padding + y - cornerRadius + borderWidth / 2,
                horizontalOffset + padding + x + cornerRadius - borderWidth / 2,
                verticalOffset + padding + y + cornerRadius - borderWidth / 2);
    }

    private float cos() {
        return (float) Math.cos(30 * Math.PI / 180);
    }
}
