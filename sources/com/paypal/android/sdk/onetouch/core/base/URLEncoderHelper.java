package com.paypal.android.sdk.onetouch.core.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import p005cn.jpush.android.JPushConstants;

public class URLEncoderHelper {
    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            return "unable_to_encode:" + s;
        }
    }
}
