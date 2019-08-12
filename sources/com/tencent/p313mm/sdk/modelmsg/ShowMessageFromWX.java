package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseReq;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage.Builder;

/* renamed from: com.tencent.mm.sdk.modelmsg.ShowMessageFromWX */
public class ShowMessageFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.ShowMessageFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;
        public WXMediaMessage message;

        public Req() {
        }

        public Req(Bundle data) {
            fromBundle(data);
        }

        public int getType() {
            return 4;
        }

        public void toBundle(Bundle data) {
            Bundle src = Builder.toBundle(this.message);
            super.toBundle(src);
            data.putString("_wxapi_showmessage_req_lang", this.lang);
            data.putString("_wxapi_showmessage_req_country", this.country);
            data.putAll(src);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.lang = data.getString("_wxapi_showmessage_req_lang");
            this.country = data.getString("_wxapi_showmessage_req_country");
            this.message = Builder.fromBundle(data);
        }

        public boolean checkArgs() {
            if (this.message == null) {
                return false;
            }
            return this.message.checkArgs();
        }
    }
}
