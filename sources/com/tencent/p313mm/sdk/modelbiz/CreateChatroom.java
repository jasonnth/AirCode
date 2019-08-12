package com.tencent.p313mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom */
public class CreateChatroom {

    /* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom$Resp */
    public static class Resp extends BaseResp {
        public String extMsg;

        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.extMsg = data.getString("_wxapi_create_chatroom_ext_msg");
        }
    }
}
