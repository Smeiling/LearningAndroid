package com.smeiling.learning.draglayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

@Route(path = "/app/drag")
public class DragLayoutActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_layout);

        rv = findViewById(R.id.recycle_view);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        final DragAdapter adapter = new DragAdapter(this);
        rv.setAdapter(adapter);

        /**
         * 实例化ItemTouchHelper对象,然后添加到RecyclerView
         */
        ItemTouchHelper helper = new ItemTouchHelper(new DragAdapter.MyItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(rv);

        findViewById(R.id.output).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String str : adapter.getData()) {
                    Logg.error(str);
                }
            }
        });

    }
}
