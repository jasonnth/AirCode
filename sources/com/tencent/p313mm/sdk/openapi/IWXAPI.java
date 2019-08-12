package com.tencent.p313mm.sdk.openapi;

import android.content.Intent;
import com.tencent.p313mm.sdk.modelbase.BaseReq;

/* renamed from: com.tencent.mm.sdk.openapi.IWXAPI */
public interface IWXAPI {
    boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler);

    boolean registerApp(String str);

    boolean sendReq(BaseReq baseReq);
}
