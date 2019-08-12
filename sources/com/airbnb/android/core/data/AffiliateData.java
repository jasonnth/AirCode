package com.airbnb.android.core.data;

public abstract class AffiliateData {
    public abstract int affiliateId();

    public abstract String campaign();

    public abstract String localAfClick();

    public static AffiliateData create(String campaign, int affiliateId, String localAfClick) {
        return new AutoValue_AffiliateData(campaign, affiliateId, localAfClick);
    }
}
