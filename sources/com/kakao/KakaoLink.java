package com.kakao;

import android.content.Context;
import android.text.TextUtils;
import com.kakao.helper.Logger;
import com.kakao.helper.SystemInfo;
import com.kakao.helper.Utility;
import org.json.JSONException;
import org.json.JSONObject;

public class KakaoLink {
    private static String appKey;
    private static String appKeyHash = "";
    private static String appPackageName = "";
    private static String appVer = "";
    private static KakaoLink singltonKakaoLink;

    public static KakaoLink getKakaoLink(Context context) throws KakaoParameterException {
        if (singltonKakaoLink != null) {
            return singltonKakaoLink;
        }
        if (appKey == null) {
            appKey = context.getString(R.string.kakao_app_key);
        }
        if (TextUtils.isEmpty(appKey)) {
            throw new KakaoParameterException("missing kakao app key");
        }
        appVer = String.valueOf(Utility.getAppVersion(context));
        appPackageName = Utility.getAppPackageName(context);
        appKeyHash = Utility.getKeyHash(context);
        singltonKakaoLink = new KakaoLink();
        return singltonKakaoLink;
    }

    public KakaoTalkLinkMessageBuilder createKakaoTalkLinkMessageBuilder() {
        return new KakaoTalkLinkMessageBuilder(appKey, appVer, makeExtra());
    }

    private String makeExtra() {
        JSONObject json = new JSONObject();
        try {
            json.put("KA", SystemInfo.getKAHeader());
            json.put("appPkg", appPackageName);
            json.put("keyHash", appKeyHash);
            return json.toString();
        } catch (JSONException e) {
            Logger.getInstance().mo44170w(e.getMessage());
            return "";
        }
    }
}
