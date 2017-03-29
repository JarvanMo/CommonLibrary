package com.example.mo.commonutil;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.mo.commonutil.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    TestAdapter adapter = new TestAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






        binding.content.rc.setLayoutManager(new LinearLayoutManager(this));
        binding.content.rc.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();

        List<Test> data = new ArrayList<>();
        Test test;
        for (int i = 0; i < 50; i++) {
            test = new Test();
            test.name = "a" + i;
            test.time = System.currentTimeMillis() + "->"+i;
            data.add(test);
        }

        adapter.setData(data);

    }
}