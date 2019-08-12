package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet extends zza implements ReflectedParcelable {
    public static final Creator<FullWallet> CREATOR = new zzg();
    String zzbPV;
    String zzbPW;
    ProxyCard zzbPX;
    String zzbPY;
    zza zzbPZ;
    zza zzbQa;
    String[] zzbQb;
    UserAddress zzbQc;
    UserAddress zzbQd;
    InstrumentInfo[] zzbQe;
    PaymentMethodToken zzbQf;

    private FullWallet() {
    }

    FullWallet(String str, String str2, ProxyCard proxyCard, String str3, zza zza, zza zza2, String[] strArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr, PaymentMethodToken paymentMethodToken) {
        this.zzbPV = str;
        this.zzbPW = str2;
        this.zzbPX = proxyCard;
        this.zzbPY = str3;
        this.zzbPZ = zza;
        this.zzbQa = zza2;
        this.zzbQb = strArr;
        this.zzbQc = userAddress;
        this.zzbQd = userAddress2;
        this.zzbQe = instrumentInfoArr;
        this.zzbQf = paymentMethodToken;
    }

    public UserAddress getBuyerBillingAddress() {
        return this.zzbQc;
    }

    public UserAddress getBuyerShippingAddress() {
        return this.zzbQd;
    }

    public String getEmail() {
        return this.zzbPY;
    }

    public String getGoogleTransactionId() {
        return this.zzbPV;
    }

    public InstrumentInfo[] getInstrumentInfos() {
        return this.zzbQe;
    }

    public String getMerchantTransactionId() {
        return this.zzbPW;
    }

    public String[] getPaymentDescriptions() {
        return this.zzbQb;
    }

    public PaymentMethodToken getPaymentMethodToken() {
        return this.zzbQf;
    }

    public ProxyCard getProxyCard() {
        return this.zzbPX;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }
}
