package com.tencent.p313mm.sdk.openapi;

import android.content.Context;
import android.util.Log;

/* renamed from: com.tencent.mm.sdk.openapi.WXAPIFactory */
public class WXAPIFactory {
    public static IWXAPI createWXAPI(Context context, String appId, boolean checkSignature) {
        Log.d("WXAPIFactory", "createWXAPI, appId = " + appId + ", checkSignature = " + checkSignature);
        return new WXApiImplV10(context, appId, checkSignature);
    }
}
