package com.example.hao_wu.androidacomponent;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hao_wu.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    UserViewModel viewModel;

    private Button btn_insert;
    private Button btn_delete;
    private Button btn_query_all;
    private TextView tv_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindView();
        initLiveData();
    }


    private void initFindView() {
        btn_insert = this.findViewById(R.id.btn_insert);
        btn_delete = this.findViewById(R.id.btn_delete);
        btn_query_all = this.findViewById(R.id.btn_query_all);
        tv_values = this.findViewById(R.id.tv_values);
        btn_insert.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_query_all.setOnClickListener(this);
    }

    private void initLiveData() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.userLiveData.observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String users) {
                if (users == null) {
                    return;
                }
                tv_values.setText(users);

            }
        });
        viewModel.loadFiveUsers(MainActivity.this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                viewModel.insertOneData();
                break;
            case R.id.btn_delete:
                viewModel.deleteAllDatas();
                break;
            case R.id.btn_query_all:
                viewModel.queryAllDatas();
                break;
        }
    }
}
