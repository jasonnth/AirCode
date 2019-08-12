package com.airbnb.android.core.businesstravel;

public enum WorkEmailLaunchSource {
    MobileP5Promo("P5"),
    AccountPage("AccountProfile"),
    ViewProfile("ViewProfile"),
    DeepLink("DeepLink"),
    EditProfile("EditProfile");
    
    private final String serverKey;

    private WorkEmailLaunchSource(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public String getServerKey() {
        return this.serverKey;
    }
}
