package com.haopintui.extend.module;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.BMWXEnvironment;
import com.benmu.widget.utils.BaseCommonUtil;
import com.haopintui.extend.activity.BaichuanActivity;
import com.haopintui.extend.util.ToolsUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.util.Map;

@WeexModule(name = "haopintui", lazyLoad = true)
public class HaopintuiModule  extends WXModule {

    //同步存取
    @JSMethod(uiThread = false)
    public Object getAppName() {
        String appName = BMWXEnvironment.mPlatformConfig.getAppName();
        return appName;
    }

    /**
     * 获取是否安装WeChat
     */
    @JSMethod(uiThread = false)
    public Object isInstall(Map<String,Object> data) {
        String packageName = (String)data.get("packageName");
        return ToolsUtil.isInstall(mWXSDKInstance.getContext(),packageName);

    }

    @JSMethod(uiThread = false)
    public Object open(Map<String,Object> data){
        Activity context = ((Activity) (mWXSDKInstance.getContext()));

        String url = (String)data.get("url");
        String packageName = (String)data.get("packageName");

        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setPackage(packageName);

//        action.setAction(Intent.ACTION_MAIN);
//        action.addCategory(Intent.CATEGORY_LAUNCHER);
        action.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        action.setData(Uri.parse(builder.toString()));
        context.startActivityForResult(action,0);

//        PackageManager packageManager = context.getPackageManager();
//        StringBuilder builder = new StringBuilder();
//        builder.append(url);
//        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
//        intent.setAction("com.xunmeng.pinduoduo");
//
//        intent.setData(Uri.parse(builder.toString()));
//        context.startActivityForResult(intent,0);

//        Intent action = new Intent(context, BaichuanActivity.class);
//        action.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//        action.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
////        context.startActivityForResult(action,0);
//
//        onActivityResult(0,0,action);



//        Intent intent = new Intent(this, SecondActivity.class);

//        new BaichuanActivity().startActivityForResult(action,0);
//        this.onActivityResult(0,0,action);


//        context.startActivity(intent);
//        Intent intent =  new Intent(MainActivity.this,ToStartActivity.class);

        return true;

//        ((Activity) (mWXSDKInstance.getContext())).startActivity(action);

//        ((Activity) (mWXSDKInstance.getContext())).startActivityForResult(new Intent(Intent.ACTION_PICK),0);
    }

}
