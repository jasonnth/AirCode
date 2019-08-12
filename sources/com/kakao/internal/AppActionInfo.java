package com.kakao.internal;

import android.text.TextUtils;
import com.kakao.AppActionBuilder.DEVICE_TYPE;
import org.json.JSONException;
import org.json.JSONObject;

public class AppActionInfo {
    private final DEVICE_TYPE deviceType;
    private final String executeParam;
    private final String marketParam;

    /* renamed from: os */
    private final ACTION_INFO_OS f3555os;

    public enum ACTION_INFO_OS {
        ANDROID("android"),
        IOS("ios");
        
        /* access modifiers changed from: private */
        public final String value;

        private ACTION_INFO_OS(String value2) {
            this.value = value2;
        }
    }

    public JSONObject createJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("os", this.f3555os.value);
        if (this.deviceType != null) {
            json.put("devicetype", this.deviceType.getValue());
        }
        if (!TextUtils.isEmpty(this.executeParam)) {
            json.put("execparam", this.executeParam);
        }
        if (!TextUtils.isEmpty(this.marketParam)) {
            json.put("marketparam", this.marketParam);
        }
        return json;
    }
}
