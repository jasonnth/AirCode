package com.alipay.sdk.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class BizContext {
    private String packageName = "";
    private String versionName = "";

    public BizContext(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.versionName = info.versionName;
            this.packageName = info.packageName;
        } catch (Exception e) {
        }
    }

    public String format(String orderInfo) {
        if (TextUtils.isEmpty(orderInfo) || orderInfo.startsWith("new_external_info==")) {
            return orderInfo;
        }
        if (isTradeEncoded(orderInfo)) {
            return formatEncoded(orderInfo);
        }
        return formatNoEncoded(orderInfo);
    }

    private boolean isTradeEncoded(String orderInfo) {
        return !orderInfo.contains("\"&");
    }

    private String formatEncoded(String orderInfo) {
        try {
            String bizContextPair = findBizContext(orderInfo, "&", "bizcontext=");
            if (TextUtils.isEmpty(bizContextPair)) {
                return orderInfo + "&" + buildBizContext("bizcontext=", "");
            }
            int index = orderInfo.indexOf(bizContextPair);
            String prefix = orderInfo.substring(0, index);
            return prefix + formatBizContext(bizContextPair, "bizcontext=", "", true) + orderInfo.substring(bizContextPair.length() + index);
        } catch (Throwable th) {
            return orderInfo;
        }
    }

    private String formatNoEncoded(String orderInfo) {
        try {
            String bizContextPair = findBizContext(orderInfo, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(bizContextPair)) {
                return orderInfo + "&" + buildBizContext("bizcontext=\"", "\"");
            }
            if (!bizContextPair.endsWith("\"")) {
                bizContextPair = bizContextPair + "\"";
            }
            int index = orderInfo.indexOf(bizContextPair);
            String prefix = orderInfo.substring(0, index);
            return prefix + formatBizContext(bizContextPair, "bizcontext=\"", "\"", false) + orderInfo.substring(bizContextPair.length() + index);
        } catch (Throwable th) {
            return orderInfo;
        }
    }

    private String findBizContext(String orderInfo, String pairConnection, String pairPrefix) {
        if (TextUtils.isEmpty(orderInfo)) {
            return null;
        }
        String[] pairs = orderInfo.split(pairConnection);
        for (int i = 0; i < pairs.length; i++) {
            if (!TextUtils.isEmpty(pairs[i]) && pairs[i].startsWith(pairPrefix)) {
                return pairs[i];
            }
        }
        return null;
    }

    private String buildBizContext(String header, String tail) throws JSONException, UnsupportedEncodingException {
        return header + buildSimpleBizContext("", "") + tail;
    }

    public String buildSimpleBizContext(String extKey, String extValue) {
        try {
            JSONObject json = new JSONObject();
            json.put("appkey", "2014052600006128");
            json.put("ty", "and_lite");
            json.put("sv", "h.a.3.1.5");
            json.put("an", this.packageName);
            json.put("av", this.versionName);
            json.put("sdk_start_time", System.currentTimeMillis());
            if (!TextUtils.isEmpty(extKey)) {
                json.put(extKey, extValue);
            }
            return json.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    private String formatBizContext(String bizContextPair, String prefix, String suffix, boolean isEncoded) throws JSONException, UnsupportedEncodingException {
        String str = bizContextPair;
        String content = bizContextPair.substring(prefix.length());
        JSONObject json = new JSONObject(content.substring(0, content.length() - suffix.length()));
        if (!json.has("appkey")) {
            json.put("appkey", "2014052600006128");
        }
        if (!json.has("ty")) {
            json.put("ty", "and_lite");
        }
        if (!json.has("sv")) {
            json.put("sv", "h.a.3.1.5");
        }
        if (!json.has("an")) {
            json.put("an", this.packageName);
        }
        if (!json.has("av")) {
            json.put("av", this.versionName);
        }
        if (!json.has("sdk_start_time")) {
            json.put("sdk_start_time", System.currentTimeMillis());
        }
        return prefix + json.toString() + suffix;
    }
}
