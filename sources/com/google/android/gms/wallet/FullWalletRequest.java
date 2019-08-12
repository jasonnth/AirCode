package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class FullWalletRequest extends zza implements ReflectedParcelable {
    public static final Creator<FullWalletRequest> CREATOR = new zzh();
    String zzbPV;
    String zzbPW;
    Cart zzbQg;

    public final class Builder {
        private Builder() {
        }

        public FullWalletRequest build() {
            return FullWalletRequest.this;
        }

        public Builder setCart(Cart cart) {
            FullWalletRequest.this.zzbQg = cart;
            return this;
        }

        public Builder setGoogleTransactionId(String str) {
            FullWalletRequest.this.zzbPV = str;
            return this;
        }
    }

    FullWalletRequest() {
    }

    FullWalletRequest(String str, String str2, Cart cart) {
        this.zzbPV = str;
        this.zzbPW = str2;
        this.zzbQg = cart;
    }

    public static Builder newBuilder() {
        FullWalletRequest fullWalletRequest = new FullWalletRequest();
        fullWalletRequest.getClass();
        return new Builder();
    }

    public Cart getCart() {
        return this.zzbQg;
    }

    public String getGoogleTransactionId() {
        return this.zzbPV;
    }

    public String getMerchantTransactionId() {
        return this.zzbPW;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}
