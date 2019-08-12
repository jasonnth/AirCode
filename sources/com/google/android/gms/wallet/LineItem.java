package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class LineItem extends zza {
    public static final Creator<LineItem> CREATOR = new zzl();
    String description;
    String zzbPO;
    String zzbPP;
    String zzbQv;
    String zzbQw;
    int zzbQx;

    public final class Builder {
        private Builder() {
        }
    }

    LineItem() {
        this.zzbQx = 0;
    }

    LineItem(String str, String str2, String str3, String str4, int i, String str5) {
        this.description = str;
        this.zzbQv = str2;
        this.zzbQw = str3;
        this.zzbPO = str4;
        this.zzbQx = i;
        this.zzbPP = str5;
    }

    public static Builder newBuilder() {
        LineItem lineItem = new LineItem();
        lineItem.getClass();
        return new Builder();
    }

    public String getCurrencyCode() {
        return this.zzbPP;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuantity() {
        return this.zzbQv;
    }

    public int getRole() {
        return this.zzbQx;
    }

    public String getTotalPrice() {
        return this.zzbPO;
    }

    public String getUnitPrice() {
        return this.zzbQw;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.zza(this, parcel, i);
    }
}
