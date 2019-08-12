package com.tencent.p313mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p313mm.sdk.modelbase.BaseResp;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage */
public class AddCardToWXCardPackage {

    /* renamed from: com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage$Resp */
    public static class Resp extends BaseResp {
        public List<WXCardItem> cardArrary;

        public Resp() {
        }

        public Resp(Bundle data) {
            fromBundle(data);
        }

        public void fromBundle(Bundle data) {
            super.fromBundle(data);
            if (this.cardArrary == null) {
                this.cardArrary = new LinkedList();
            }
            String jsonData = data.getString("_wxapi_add_card_to_wx_card_list");
            if (jsonData != null && jsonData.length() > 0) {
                try {
                    JSONArray array = ((JSONObject) new JSONTokener(jsonData).nextValue()).getJSONArray("card_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        WXCardItem item = new WXCardItem();
                        item.cardId = o.optString("card_id");
                        item.cardExtMsg = o.optString("card_ext");
                        item.cardState = o.optInt("is_succ");
                        this.cardArrary.add(item);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage$WXCardItem */
    public static final class WXCardItem {
        public String cardExtMsg;
        public String cardId;
        public int cardState;
    }
}
