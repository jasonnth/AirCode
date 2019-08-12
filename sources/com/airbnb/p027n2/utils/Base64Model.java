package com.airbnb.p027n2.utils;

import android.text.TextUtils;

/* renamed from: com.airbnb.n2.utils.Base64Model */
public class Base64Model {
    private final String base64;

    public Base64Model(String base642) {
        this.base64 = base642;
    }

    public String getBase64() {
        return this.base64;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.base64);
    }
}
