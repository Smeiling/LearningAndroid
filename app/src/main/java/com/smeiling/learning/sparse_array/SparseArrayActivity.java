package com.smeiling.learning.sparse_array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

import java.util.Iterator;

import retrofit2.http.Path;

@Route(path = "/app/sparsearray")
public class SparseArrayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparse_array);


        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(20, "Value = 20");
        sparseArray.put(21, "Value = 21");
        sparseArray.put(1, "Value = 1");
        sparseArray.put(9, "Value = 9");
        sparseArray.put(15, "Value = 15");

        StringBuilder outputBuilder = new StringBuilder();

        outputBuilder.append("sparseArray.size() = ").append(sparseArray.size()).append("\n");


        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            outputBuilder.append("sparseArraySample: i = ").append(i)
                    .append("\tkey = ").append(sparseArray.keyAt(i))
                    .append("\tvalue = ").append(sparseArray.get(sparseArray.keyAt(i)))
                    .append("\n");
        }
        ((TextView) findViewById(R.id.tv_output)).setText(outputBuilder.toString());

    }
}
