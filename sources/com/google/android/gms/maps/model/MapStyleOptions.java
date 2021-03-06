package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzp;
import java.io.IOException;
import p005cn.jpush.android.JPushConstants;

public final class MapStyleOptions extends zza {
    public static final Creator<MapStyleOptions> CREATOR = new zzg();
    private static final String TAG = MapStyleOptions.class.getSimpleName();
    private String zzbpz;

    public MapStyleOptions(String str) {
        this.zzbpz = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws NotFoundException {
        try {
            return new MapStyleOptions(new String(zzp.zzj(context.getResources().openRawResource(i)), JPushConstants.ENCODING_UTF_8));
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            throw new NotFoundException(new StringBuilder(String.valueOf(valueOf).length() + 37).append("Failed to read resource ").append(i).append(": ").append(valueOf).toString());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzJL() {
        return this.zzbpz;
    }
}
