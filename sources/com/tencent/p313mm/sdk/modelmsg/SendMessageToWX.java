package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelbase.BaseReq;
import com.tencent.p313mm.sdk.modelbase.BaseResp;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage.Builder;

/* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX */
public class SendMessageToWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX$Req */
    public static class Req extends BaseReq {
        public WXMediaMessage message;
        public int scene;

        public int getType() {
            return 2;
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.message = Builder.fromBundle(data);
            this.scene = data.getInt("_wxapi_sendmessagetowx_req_scene");
        }

        public void toBundle(Bundle data) {
            super.toBundle(data);
            data.putAll(Builder.toBundle(this.message));
            data.putInt("_wxapi_sendmessagetowx_req_scene", this.scene);
        }

        public boolean checkArgs() {
            if (this.message == null) {
                Log.e("SendMessageToWX.Req", "checkArgs fail ,message is null");
                return false;
            }
            if (this.message.mediaObject.type() == 6 && this.scene == 2) {
                ((WXFileObject) this.message.mediaObject).setContentLengthLimit(26214400);
            }
            return this.message.checkArgs();
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX$Resp */
    public static class Resp extends BaseResp {
        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
        }
    }
}
