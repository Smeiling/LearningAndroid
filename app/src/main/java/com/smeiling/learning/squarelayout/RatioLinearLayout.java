package com.smeiling.learning.squarelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.smeiling.learning.R;

/**
 * @Author: Smeiling
 * @Date: 2019-03-06 13-54
 * @Description: 自定义宽高比LinearLayout
 */
public class RatioLinearLayout extends LinearLayout {

    private float ratio = 1;

    public RatioLinearLayout(Context context) {
        this(context, null);
    }

    public RatioLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RatioLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLinearLayout);
        ratio = typedArray.getFloat(R.styleable.RatioLinearLayout_rll_ratio, 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, (int) (width * ratio));
    }
}
