package com.airbnb.android.core.enums;

public enum FetchPricingInteractionType {
    Pageload("pageload"),
    GuestChanged("guestChanged"),
    DateChanged("dateChanged");
    
    private final String serverKey;

    private FetchPricingInteractionType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public static FetchPricingInteractionType fromServerKey(String serverKey2) {
        FetchPricingInteractionType[] values;
        for (FetchPricingInteractionType type : values()) {
            if (type.serverKey.equals(serverKey2)) {
                return type;
            }
        }
        return null;
    }
}
