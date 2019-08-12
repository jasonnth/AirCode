package com.tencent.p313mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelbiz.JoinChatroom */
public class JoinChatroom {

    /* renamed from: com.tencent.mm.sdk.modelbiz.JoinChatroom$Resp */
    public static class Resp extends BaseResp {
        public String extMsg;

        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            this.extMsg = data.getString("_wxapi_join_chatroom_ext_msg");
        }
    }
}
