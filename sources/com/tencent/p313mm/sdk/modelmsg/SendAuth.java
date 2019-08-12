package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelbase.BaseReq;
import com.tencent.p313mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth */
public final class SendAuth {

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth$Req */
    public static class Req extends BaseReq {
        public String scope;
        public String state;

        public int getType() {
            return 1;
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.scope = data.getString("_wxapi_sendauth_req_scope");
            this.state = data.getString("_wxapi_sendauth_req_state");
        }

        public void toBundle(Bundle data) {
            super.toBundle(data);
            data.putString("_wxapi_sendauth_req_scope", this.scope);
            data.putString("_wxapi_sendauth_req_state", this.state);
        }

        public boolean checkArgs() {
            if (this.scope == null || this.scope.length() == 0 || this.scope.length() > 1024) {
                Log.e("SendAuth.Req", "checkArgs fail, scope is invalid");
                return false;
            } else if (this.state == null || this.state.length() <= 1024) {
                return true;
            } else {
                Log.e("SendAuth.Req", "checkArgs fail, state is invalid");
                return false;
            }
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth$Resp */
    public static class Resp extends BaseResp {
        public String code;
        public String country;
        public String lang;
        public String state;
        public String url;

        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.code = data.getString("_wxapi_sendauth_resp_token");
            this.state = data.getString("_wxapi_sendauth_resp_state");
            this.url = data.getString("_wxapi_sendauth_resp_url");
            this.lang = data.getString("_wxapi_sendauth_resp_lang");
            this.country = data.getString("_wxapi_sendauth_resp_country");
        }
    }
}
