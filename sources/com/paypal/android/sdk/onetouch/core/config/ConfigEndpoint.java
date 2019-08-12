package com.paypal.android.sdk.onetouch.core.config;

public class ConfigEndpoint {
    public final String certificate;
    public final String name;
    public final String url;

    public ConfigEndpoint(String name2, String url2, String certificate2) {
        this.name = name2;
        this.url = url2;
        this.certificate = certificate2;
    }
}
