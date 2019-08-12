package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet extends zza implements ReflectedParcelable {
    public static final Creator<MaskedWallet> CREATOR = new zzn();
    String zzbPV;
    String zzbPW;
    String zzbPY;
    zza zzbPZ;
    LoyaltyWalletObject[] zzbQS;
    OfferWalletObject[] zzbQT;
    zza zzbQa;
    String[] zzbQb;
    UserAddress zzbQc;
    UserAddress zzbQd;
    InstrumentInfo[] zzbQe;

    public final class Builder {
        private Builder() {
        }

        public Builder setBuyerBillingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzbQc = userAddress;
            return this;
        }

        public Builder setBuyerShippingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzbQd = userAddress;
            return this;
        }

        public Builder setEmail(String str) {
            MaskedWallet.this.zzbPY = str;
            return this;
        }

        public Builder setGoogleTransactionId(String str) {
            MaskedWallet.this.zzbPV = str;
            return this;
        }

        public Builder setInstrumentInfos(InstrumentInfo[] instrumentInfoArr) {
            MaskedWallet.this.zzbQe = instrumentInfoArr;
            return this;
        }

        public Builder setMerchantTransactionId(String str) {
            MaskedWallet.this.zzbPW = str;
            return this;
        }

        public Builder setPaymentDescriptions(String[] strArr) {
            MaskedWallet.this.zzbQb = strArr;
            return this;
        }

        @Deprecated
        public Builder zza(LoyaltyWalletObject[] loyaltyWalletObjectArr) {
            MaskedWallet.this.zzbQS = loyaltyWalletObjectArr;
            return this;
        }

        @Deprecated
        public Builder zza(OfferWalletObject[] offerWalletObjectArr) {
            MaskedWallet.this.zzbQT = offerWalletObjectArr;
            return this;
        }
    }

    private MaskedWallet() {
    }

    MaskedWallet(String str, String str2, String[] strArr, String str3, zza zza, zza zza2, LoyaltyWalletObject[] loyaltyWalletObjectArr, OfferWalletObject[] offerWalletObjectArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr) {
        this.zzbPV = str;
        this.zzbPW = str2;
        this.zzbQb = strArr;
        this.zzbPY = str3;
        this.zzbPZ = zza;
        this.zzbQa = zza2;
        this.zzbQS = loyaltyWalletObjectArr;
        this.zzbQT = offerWalletObjectArr;
        this.zzbQc = userAddress;
        this.zzbQd = userAddress2;
        this.zzbQe = instrumentInfoArr;
    }

    public static Builder newBuilderFrom(MaskedWallet maskedWallet) {
        zzac.zzw(maskedWallet);
        return zzTZ().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail()).zza(maskedWallet.zzUa()).zza(maskedWallet.zzUb()).setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
    }

    public static Builder zzTZ() {
        MaskedWallet maskedWallet = new MaskedWallet();
        maskedWallet.getClass();
        return new Builder();
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

    public void writeToParcel(Parcel parcel, int i) {
        zzn.zza(this, parcel, i);
    }

    @Deprecated
    public LoyaltyWalletObject[] zzUa() {
        return this.zzbQS;
    }

    @Deprecated
    public OfferWalletObject[] zzUb() {
        return this.zzbQT;
    }
}
