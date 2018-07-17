package com.haopintui.extend.update;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.vector.update_app.UpdateAppManager;

import java.util.HashMap;

public class Update {

    /**
     * 最简方式
     *
     * @param view
     */
    public static void updateApp(Activity activity, String mUpdateUrl, HashMap<String,String> params) {

        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(activity)
                .setParams(params)
                //更新地址
                .setUpdateUrl(mUpdateUrl)
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .update();
    }

}
