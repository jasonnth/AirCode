package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams extends zza implements ReflectedParcelable {
    public static final Creator<WalletFragmentInitParams> CREATOR = new zza();
    /* access modifiers changed from: private */
    public String zzaiu;
    /* access modifiers changed from: private */
    public MaskedWalletRequest zzbRW;
    /* access modifiers changed from: private */
    public MaskedWallet zzbRX;
    /* access modifiers changed from: private */
    public int zzbSk;

    public final class Builder {
        private Builder() {
        }
    }

    private WalletFragmentInitParams() {
        this.zzbSk = -1;
    }

    WalletFragmentInitParams(String str, MaskedWalletRequest maskedWalletRequest, int i, MaskedWallet maskedWallet) {
        this.zzaiu = str;
        this.zzbRW = maskedWalletRequest;
        this.zzbSk = i;
        this.zzbRX = maskedWallet;
    }

    public static Builder newBuilder() {
        WalletFragmentInitParams walletFragmentInitParams = new WalletFragmentInitParams();
        walletFragmentInitParams.getClass();
        return new Builder();
    }

    public String getAccountName() {
        return this.zzaiu;
    }

    public MaskedWallet getMaskedWallet() {
        return this.zzbRX;
    }

    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzbRW;
    }

    public int getMaskedWalletRequestCode() {
        return this.zzbSk;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
