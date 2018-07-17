package com.haopintui.extend.model;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;

/**
 * Created by wu on 2017/12/31.
 */

public class BaichuanTradeCallback implements AlibcTradeCallback {

    @Override
    public void onTradeSuccess(AlibcTradeResult tradeResult) {
        //当addCartPage加购成功和其他page支付成功的时候会回调

//        if(tradeResult.resultType.equals(AlibcResultType.TYPECART)){
//            //加购成功
//            Toast.makeText(AliSdkApplication.application, "加购成功", Toast.LENGTH_SHORT).show();
//        }else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)){
//            //支付成功
//            Toast.makeText(AliSdkApplication.application, "支付成功,成功订单号为"+tradeResult.payResult.paySuccessOrders, Toast.LENGTH_SHORT).show();
//        }
        AlibcTradeSDK.destory();
    }

    @Override
    public void onFailure(int errCode, String errMsg) {
//        Toast.makeText(AliSdkApplication.application, "电商SDK出错,错误码="+errCode+" / 错误消息="+errMsg, Toast.LENGTH_SHORT).show();
        AlibcTradeSDK.destory();
    }

}
