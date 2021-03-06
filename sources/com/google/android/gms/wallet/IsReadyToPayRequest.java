package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;

public final class IsReadyToPayRequest extends zza {
    public static final Creator<IsReadyToPayRequest> CREATOR = new zzk();
    ArrayList<Integer> zzbQr;
    String zzbQs;
    String zzbQt;

    public final class Builder {
        private Builder() {
        }

        public IsReadyToPayRequest build() {
            return IsReadyToPayRequest.this;
        }
    }

    IsReadyToPayRequest() {
    }

    IsReadyToPayRequest(ArrayList<Integer> arrayList, String str, String str2) {
        this.zzbQr = arrayList;
    }

    public static Builder newBuilder() {
        IsReadyToPayRequest isReadyToPayRequest = new IsReadyToPayRequest();
        isReadyToPayRequest.getClass();
        return new Builder();
    }

    public ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzbQr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }
}
