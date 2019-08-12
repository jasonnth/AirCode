package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseReq;

/* renamed from: com.tencent.mm.sdk.modelmsg.GetMessageFromWX */
public final class GetMessageFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.GetMessageFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;

        public Req() {
        }

        public Req(Bundle data) {
            fromBundle(data);
        }

        public int getType() {
            return 3;
        }

        public void toBundle(Bundle data) {
            super.toBundle(data);
            data.putString("_wxapi_getmessage_req_lang", this.lang);
            data.putString("_wxapi_getmessage_req_country", this.country);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.lang = data.getString("_wxapi_getmessage_req_lang");
            this.country = data.getString("_wxapi_getmessage_req_country");
        }

        public boolean checkArgs() {
            return true;
        }
    }
}
