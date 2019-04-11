package com.smeiling.learning.hexagon;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

@Route(path = "/app/hexagon")
public class HexagonActivity extends AppCompatActivity {


    private ImageView shapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexagon);

        shapeView = findViewById(R.id.shape_view);


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(30);
        gradientDrawable.setColor(Color.parseColor("#3399ff"));

        shapeView.setImageDrawable(gradientDrawable);
    }
}
