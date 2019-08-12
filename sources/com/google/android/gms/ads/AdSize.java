package com.google.android.gms.ads;

import android.content.Context;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;

public final class AdSize {
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(BusinessTravelIntents.REQUEST_CODE_ADD_EMAIL, 60, "468x60_as");
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    public static final AdSize zzrB = new AdSize(50, 50, "50x50_mb");
    private final int zzrC;
    private final int zzrD;
    private final String zzrE;

    public AdSize(int i, int i2) {
        String valueOf = i == -1 ? "FULL" : String.valueOf(i);
        String valueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        String valueOf3 = String.valueOf("_as");
        this(i, i2, new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append(valueOf).append("x").append(valueOf2).append(valueOf3).toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzrC = i;
            this.zzrD = i2;
            this.zzrE = str;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzrC == adSize.zzrC && this.zzrD == adSize.zzrD && this.zzrE.equals(adSize.zzrE);
    }

    public int getHeight() {
        return this.zzrD;
    }

    public int getHeightInPixels(Context context) {
        switch (this.zzrD) {
            case -4:
            case -3:
                return -1;
            case -2:
                return zzeg.zzb(context.getResources().getDisplayMetrics());
            default:
                return zzel.zzeT().zzb(context, this.zzrD);
        }
    }

    public int getWidth() {
        return this.zzrC;
    }

    public int getWidthInPixels(Context context) {
        switch (this.zzrC) {
            case -4:
            case -3:
                return -1;
            case -1:
                return zzeg.zza(context.getResources().getDisplayMetrics());
            default:
                return zzel.zzeT().zzb(context, this.zzrC);
        }
    }

    public int hashCode() {
        return this.zzrE.hashCode();
    }

    public boolean isFluid() {
        return this.zzrC == -3 && this.zzrD == -4;
    }

    public String toString() {
        return this.zzrE;
    }
}
