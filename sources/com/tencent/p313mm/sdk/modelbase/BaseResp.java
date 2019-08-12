package com.tencent.p313mm.sdk.modelbase;

import android.os.Bundle;

/* renamed from: com.tencent.mm.sdk.modelbase.BaseResp */
public abstract class BaseResp {
    public int errCode;
    public String errStr;
    public String openId;
    public String transaction;

    public void fromBundle(Bundle data) {
        this.errCode = data.getInt("_wxapi_baseresp_errcode");
        this.errStr = data.getString("_wxapi_baseresp_errstr");
        this.transaction = data.getString("_wxapi_baseresp_transaction");
        this.openId = data.getString("_wxapi_baseresp_openId");
    }
}
