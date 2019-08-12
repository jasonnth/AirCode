package com.alipay.sdk.app;

import android.text.TextUtils;
import p005cn.jpush.android.JPushConstants.PushService;

public class AuthResult {
    private String alipayOpenId;
    private String authCode;
    private String memo;
    private String result;
    private String resultCode;
    private String resultStatus;

    public AuthResult(String rawResult) {
        String[] resultParams;
        String[] resultValue;
        if (!TextUtils.isEmpty(rawResult)) {
            for (String resultParam : rawResult.split(";")) {
                if (resultParam.startsWith("resultStatus")) {
                    this.resultStatus = getResultParam(resultParam, "resultStatus");
                }
                if (resultParam.startsWith(PushService.PARAM_RESULT)) {
                    this.result = getResultParam(resultParam, PushService.PARAM_RESULT);
                }
                if (resultParam.startsWith("memo")) {
                    this.memo = getResultParam(resultParam, "memo");
                }
            }
            for (String value : this.result.split("&")) {
                if (value.startsWith("alipay_open_id")) {
                    this.alipayOpenId = getValue(value);
                }
                if (value.startsWith("auth_code")) {
                    this.authCode = getValue(value);
                }
                if (value.startsWith("result_code")) {
                    this.resultCode = getValue(value);
                }
            }
        }
    }

    private String getResultParam(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(), content.lastIndexOf("}"));
    }

    private String getValue(String data) {
        String value = data.split("=\"")[1];
        return value.substring(0, value.lastIndexOf("\""));
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getAuthCode() {
        return this.authCode;
    }
}
