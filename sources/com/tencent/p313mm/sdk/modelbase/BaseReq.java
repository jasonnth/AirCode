package com.tencent.p313mm.sdk.modelbase;

import android.os.Bundle;
import com.tencent.p313mm.sdk.channel.compatible.IntentUtil;

/* renamed from: com.tencent.mm.sdk.modelbase.BaseReq */
public abstract class BaseReq {
    public String openId;
    public String transaction;

    public abstract boolean checkArgs();

    public abstract int getType();

    public void toBundle(Bundle data) {
        data.putInt("_wxapi_command_type", getType());
        data.putString("_wxapi_basereq_transaction", this.transaction);
        data.putString("_wxapi_basereq_openid", this.openId);
    }

    public void fromBundle(Bundle data) {
        this.transaction = IntentUtil.getString(data, "_wxapi_basereq_transaction");
        this.openId = IntentUtil.getString(data, "_wxapi_basereq_openid");
    }
}
