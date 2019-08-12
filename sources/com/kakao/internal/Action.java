package com.kakao.internal;

import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoParameterException.ERROR_CODE;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.PushService;

public class Action {
    private AppActionInfo[] appActionInfos;
    private final ACTION_TYPE type;
    private String url;

    public enum ACTION_TYPE {
        WEB(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB),
        APP(PushService.PARAM_APP);
        
        /* access modifiers changed from: private */
        public final String value;

        private ACTION_TYPE(String value2) {
            this.value = value2;
        }
    }

    private Action(ACTION_TYPE type2, String url2, AppActionInfo[] appActionInfos2) throws KakaoParameterException {
        if (type2 == null) {
            throw new KakaoParameterException(ERROR_CODE.CORE_PARAMETER_MISSING, "action needs type.");
        }
        this.type = type2;
        if (type2 == ACTION_TYPE.WEB && !TextUtils.isEmpty(url2)) {
            this.url = url2;
        }
        if (type2 == ACTION_TYPE.APP && appActionInfos2 != null && appActionInfos2.length != 0) {
            this.appActionInfos = appActionInfos2;
        }
    }

    public static Action newActionWeb(String url2) throws KakaoParameterException {
        return new Action(ACTION_TYPE.WEB, url2, null);
    }

    public JSONObject createJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", this.type.value);
        if (this.url != null) {
            json.put("url", this.url);
        }
        if (this.appActionInfos != null) {
            JSONArray jsonObjs = new JSONArray();
            for (AppActionInfo obj : this.appActionInfos) {
                jsonObjs.put(obj.createJSONObject());
            }
            json.put("actioninfo", jsonObjs);
        }
        return json;
    }
}
