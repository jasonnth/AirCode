package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelbase.BaseReq;

/* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX */
public class LaunchFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;
        public String messageAction;
        public String messageExt;

        public Req() {
        }

        public Req(Bundle data) {
            fromBundle(data);
        }

        public void toBundle(Bundle data) {
            super.toBundle(data);
            data.putString("_wxobject_message_action", this.messageAction);
            data.putString("_wxobject_message_ext", this.messageExt);
            data.putString("_wxapi_launch_req_lang", this.lang);
            data.putString("_wxapi_launch_req_country", this.country);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.messageAction = data.getString("_wxobject_message_action");
            this.messageExt = data.getString("_wxobject_message_ext");
            this.lang = data.getString("_wxapi_launch_req_lang");
            this.country = data.getString("_wxapi_launch_req_country");
        }

        public int getType() {
            return 6;
        }

        public boolean checkArgs() {
            if (this.messageAction != null && this.messageAction.length() > 2048) {
                Log.e("LaunchFromWX.Req", "checkArgs fail, messageAction is too long");
                return false;
            } else if (this.messageExt == null || this.messageExt.length() <= 2048) {
                return true;
            } else {
                Log.e("LaunchFromWX.Req", "checkArgs fail, messageExt is too long");
                return false;
            }
        }
    }
}
