package com.haopintui.extend.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wu on 2017/12/29.
 */

public class BaichuanActivity extends Activity {

    private WebView webView;
    private Map<String, String> exParams = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initParams();

    }

    private void initView() {

        LinearLayout linearLayout = new LinearLayout(this, null);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.addView(webView, params);
        setContentView(linearLayout);

    }

    private void initParams(){
//        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");
//        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
    }

    //登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //调用了AlibcTrade.show方法的Activity都需要调用AlibcTradeSDK.destory()
        AlibcTradeSDK.destory();
        super.onDestroy();
    }

}
