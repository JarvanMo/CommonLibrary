package com.jarvanmo.common.base;

import android.app.Application;

import com.jarvanmo.common.widget.MToast;

/**
 * Created by mo on 17-3-21.
 * Copyright © 2017, cnyanglao, Co,. Ltd. All Rights Reserve
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MToast.init(getApplicationContext());
    }
}
