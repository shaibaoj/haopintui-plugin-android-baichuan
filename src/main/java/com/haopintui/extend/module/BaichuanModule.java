package com.haopintui.extend.module;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcAddCartPage;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.haopintui.extend.util.PidBean;
import com.alibaba.weex.plugin.annotation.WeexModule;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by wu on 2017/12/29.
 */

@WeexModule(name = "bmAuth", lazyLoad = true)
public class BaichuanModule extends WXModule {

    @JSMethod
    public void open(){
        ((Activity) (mWXSDKInstance.getContext())).startActivityForResult(new Intent(Intent.ACTION_PICK),0);
    }

    @JSMethod
    public void open_url(Map<String,Object> data, JSCallback successCallback, JSCallback
            failedCallback){

        AlibcBasePage page = null;
        String page_type = (String)data.get("page_type");
        if(page_type.equals("item")){
            String itemId =  (String)data.get("num_iid");
            page = new AlibcDetailPage(itemId);
        }
        else if(page_type.equals("shop")){
            String shopId =  (String)data.get("shopid");
            page = new AlibcShopPage(shopId);
        }
        else if(page_type.equals("addCart")){
            String itemId =  (String)data.get("num_iid");
            page = new AlibcAddCartPage(itemId);
        }
        else if(page_type.equals("order")){
            boolean allOrder = true;
            int status = 0;
            page = new AlibcMyOrdersPage(status, allOrder);
        }
        else if(page_type.equals("cart")){
            page = new AlibcMyCartsPage();
        }
        else {
            String url =  (String)data.get("url");
            page = new AlibcPage(url);
        }

        //提供给三方传递配置参数
        Map<String, String> exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");

        //设置页面打开方式
        AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);

        AlibcTaokeParams alibcTaokeParams = null; // 若非淘客taokeParams设置为null即可
        String pid = (String)data.get("pid");
        if(pid!=null&&!pid.equals("")){
            PidBean pidBean = new PidBean();
            pidBean.to_pid(pid);

            alibcTaokeParams = new AlibcTaokeParams();

            alibcTaokeParams.unionId = pidBean.getUnionId();
            alibcTaokeParams.adzoneid = pidBean.getAdzoneid();
            alibcTaokeParams.pid = pid;
            alibcTaokeParams.subPid = pid;
            alibcTaokeParams.extraParams = new HashMap<>();

            String taokeAppkey = (String)data.get("taokeAppkey");
            if(taokeAppkey!=null&&!taokeAppkey.equals("")){
                alibcTaokeParams.extraParams.put("taokeAppkey",taokeAppkey);
            }
        }

        Activity context = ((Activity) (mWXSDKInstance.getContext()));
        int ret = AlibcTrade.show(context, page, showParams, alibcTaokeParams, exParams,new BaichuanTradeCallback());

        //使用自己的Activity & webview打开detail
//        AlibcTrade.show(context, webView, webViewClient, webChromeClien, tdetailPage, showParams, null, exParams ,
//                new AlibcTradeCallback() {
//                    @Override
//                    public void onTradeSuccess(TradeResult tradeResult) {
//                        //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
//                    }
//                });

    }

    @JSMethod
    public void startActivity(String url, String cb){
        Log.d("weex", "========" + url);
        boolean error = false;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mWXSDKInstance.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            error = true;
        }
        Map<String, Object> result = new HashMap<>(1);
        result.put("error", error);
        WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), cb, result);
    }

    public void onActivityDestroy(){
        super.onActivityDestroy();
        this.destory();
    }

    private void destory(){
        AlibcTradeSDK.destory();
    }

}
