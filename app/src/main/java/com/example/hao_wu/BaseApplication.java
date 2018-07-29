package com.example.hao_wu;

import android.app.Application;
import android.content.Context;

import com.example.hao_wu.utils.APPUtils;

/**
 * Created by hao_wu on 2018/7/29.
 */

public class BaseApplication extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        APPUtils.regContext(context);
    }


}
