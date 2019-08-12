package com.tencent.p313mm.sdk.modelmsg;

import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage.IMediaObject;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXWebpageObject */
public class WXWebpageObject implements IMediaObject {
    public String extInfo;
    public String webpageUrl;

    public WXWebpageObject() {
    }

    public WXWebpageObject(String url) {
        this.webpageUrl = url;
    }

    public void serialize(Bundle data) {
        data.putString("_wxwebpageobject_extInfo", this.extInfo);
        data.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
    }

    public void unserialize(Bundle data) {
        this.extInfo = data.getString("_wxwebpageobject_extInfo");
        this.webpageUrl = data.getString("_wxwebpageobject_webpageUrl");
    }

    public int type() {
        return 5;
    }

    public boolean checkArgs() {
        if (this.webpageUrl != null && this.webpageUrl.length() != 0 && this.webpageUrl.length() <= 10240) {
            return true;
        }
        Log.e("WXWebpageObject", "checkArgs fail, webpageUrl is invalid");
        return false;
    }
}
