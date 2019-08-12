package com.google.ads;

import com.airbnb.android.core.intents.BusinessTravelIntents;

@Deprecated
public final class AdSize {
    public static final AdSize BANNER = new AdSize(320, 50, "mb");
    public static final AdSize IAB_BANNER = new AdSize(BusinessTravelIntents.REQUEST_CODE_ADD_EMAIL, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_MRECT = new AdSize(300, 250, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    private final com.google.android.gms.ads.AdSize zzaJ;

    private AdSize(int i, int i2, String str) {
        this(new com.google.android.gms.ads.AdSize(i, i2));
    }

    public AdSize(com.google.android.gms.ads.AdSize adSize) {
        this.zzaJ = adSize;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdSize)) {
            return false;
        }
        return this.zzaJ.equals(((AdSize) obj).zzaJ);
    }

    public int getHeight() {
        return this.zzaJ.getHeight();
    }

    public int getWidth() {
        return this.zzaJ.getWidth();
    }

    public int hashCode() {
        return this.zzaJ.hashCode();
    }

    public String toString() {
        return this.zzaJ.toString();
    }
}
