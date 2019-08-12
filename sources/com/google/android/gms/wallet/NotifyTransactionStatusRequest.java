package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

@Deprecated
public final class NotifyTransactionStatusRequest extends zza {
    public static final Creator<NotifyTransactionStatusRequest> CREATOR = new zzp();
    int status;
    String zzbPV;
    String zzbRj;

    public final class Builder {
        private Builder() {
        }
    }

    NotifyTransactionStatusRequest() {
    }

    NotifyTransactionStatusRequest(String str, int i, String str2) {
        this.zzbPV = str;
        this.status = i;
        this.zzbRj = str2;
    }

    public static Builder newBuilder() {
        NotifyTransactionStatusRequest notifyTransactionStatusRequest = new NotifyTransactionStatusRequest();
        notifyTransactionStatusRequest.getClass();
        return new Builder();
    }

    public String getDetailedReason() {
        return this.zzbRj;
    }

    public String getGoogleTransactionId() {
        return this.zzbPV;
    }

    public int getStatus() {
        return this.status;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzp.zza(this, parcel, i);
    }
}
