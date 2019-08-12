package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

@zzme
public class zzds extends zza {
    public static final Creator<zzds> CREATOR = new zzdt();
    public final String url;
    public final long zzyL;
    public final String zzyM;
    public final String zzyN;
    public final String zzyO;
    public final Bundle zzyP;
    public final boolean zzyQ;

    zzds(String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z) {
        this.url = str;
        this.zzyL = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzyM = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzyN = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzyO = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzyP = bundle;
        this.zzyQ = z;
    }

    public static zzds zzJ(String str) {
        return zze(Uri.parse(str));
    }

    public static zzds zze(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzpk.zzbh("Expected 2 path parts for namespace and id, found :" + pathSegments.size());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String str3 : zzw.zzcO().zzh(uri)) {
                if (str3.startsWith("tag.")) {
                    bundle.putString(str3.substring("tag.".length()), uri.getQueryParameter(str3));
                }
            }
            return new zzds(queryParameter, parseLong, host, str, str2, bundle, equals);
        } catch (NullPointerException | NumberFormatException e) {
            zzpk.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdt.zza(this, parcel, i);
    }
}
