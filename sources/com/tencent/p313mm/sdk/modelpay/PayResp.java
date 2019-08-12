package com.tencent.p313mm.sdk.modelpay;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelpay.PayResp */
public class PayResp extends BaseResp {
    public String extData;
    public String prepayId;
    public String returnKey;

    public PayResp() {
    }

    public PayResp(Bundle data) {
        fromBundle(data);
    }

    public void fromBundle(Bundle data) {
        super.fromBundle(data);
        this.prepayId = data.getString("_wxapi_payresp_prepayid");
        this.returnKey = data.getString("_wxapi_payresp_returnkey");
        this.extData = data.getString("_wxapi_payresp_extdata");
    }
}
