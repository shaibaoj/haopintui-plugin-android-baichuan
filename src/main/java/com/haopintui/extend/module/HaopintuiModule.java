package com.haopintui.extend.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.BMWXEnvironment;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

@WeexModule(name = "haopintui", lazyLoad = true)
public class HaopintuiModule  extends WXModule {

    //同步存取
    @JSMethod(uiThread = false)
    public Object getAppName() {
        String appName = BMWXEnvironment.mPlatformConfig.getAppName();
        return appName;
    }

}
