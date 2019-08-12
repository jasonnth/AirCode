package com.paypal.android.sdk.onetouch.core.network;

public class EnvironmentManager {
    public static boolean isMock(String environmentName) {
        return environmentName.equals("mock");
    }

    public static boolean isSandbox(String environmentName) {
        return environmentName.equals("sandbox");
    }

    public static boolean isLive(String environmentName) {
        return environmentName.equals("live");
    }

    public static String getEnvironmentUrl(String environmentName) {
        if (isLive(environmentName)) {
            return "https://api-m.paypal.com/v1/";
        }
        if (isSandbox(environmentName)) {
            return "https://api-m.sandbox.paypal.com/v1/";
        }
        if (isMock(environmentName)) {
            return null;
        }
        return environmentName;
    }
}
