package com.tencent.p313mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview */
public class OpenWebview {

    /* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview$Resp */
    public static class Resp extends BaseResp {
        public String result;

        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.result = data.getString("_wxapi_open_webview_result");
        }
    }
}
