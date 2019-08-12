package com.google.android.gms.search;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class GoogleNowAuthState extends zza {
    public static final Creator<GoogleNowAuthState> CREATOR = new zza();
    String zzbDV;
    String zzbDW;
    long zzbDX;

    GoogleNowAuthState(String str, String str2, long j) {
        this.zzbDV = str;
        this.zzbDW = str2;
        this.zzbDX = j;
    }

    public String getAccessToken() {
        return this.zzbDW;
    }

    public String getAuthCode() {
        return this.zzbDV;
    }

    public long getNextAllowedTimeMillis() {
        return this.zzbDX;
    }

    public String toString() {
        String str = this.zzbDV;
        String str2 = this.zzbDW;
        return new StringBuilder(String.valueOf(str).length() + 74 + String.valueOf(str2).length()).append("mAuthCode = ").append(str).append("\nmAccessToken = ").append(str2).append("\nmNextAllowedTimeMillis = ").append(this.zzbDX).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
