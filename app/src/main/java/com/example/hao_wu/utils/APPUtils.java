package com.example.hao_wu.utils;

import android.content.Context;

/**
 * Created by hao_wu on 2018/7/29.
 */

public class APPUtils {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void regContext(Context mContext) {
        context = mContext;
    }
}
