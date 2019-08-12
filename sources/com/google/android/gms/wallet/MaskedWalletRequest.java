package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest extends zza implements ReflectedParcelable {
    public static final Creator<MaskedWalletRequest> CREATOR = new zzo();
    String zzUI;
    String zzbPP;
    String zzbPW;
    boolean zzbQV;
    boolean zzbQW;
    boolean zzbQX;
    String zzbQY;
    String zzbQZ;
    Cart zzbQg;
    boolean zzbRa;
    boolean zzbRb;
    CountrySpecification[] zzbRc;
    boolean zzbRd;
    boolean zzbRe;
    ArrayList<CountrySpecification> zzbRf;
    PaymentMethodTokenizationParameters zzbRg;
    ArrayList<Integer> zzbRh;

    public final class Builder {
        private Builder() {
        }

        public Builder addAllowedCardNetworks(Collection<Integer> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzbRh == null) {
                    MaskedWalletRequest.this.zzbRh = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzbRh.addAll(collection);
            }
            return this;
        }

        public Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzbRf == null) {
                    MaskedWalletRequest.this.zzbRf = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzbRf.addAll(collection);
            }
            return this;
        }

        public MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public Builder setCart(Cart cart) {
            MaskedWalletRequest.this.zzbQg = cart;
            return this;
        }

        public Builder setCurrencyCode(String str) {
            MaskedWalletRequest.this.zzbPP = str;
            return this;
        }

        public Builder setEstimatedTotalPrice(String str) {
            MaskedWalletRequest.this.zzbQY = str;
            return this;
        }

        public Builder setMerchantName(String str) {
            MaskedWalletRequest.this.zzbQZ = str;
            return this;
        }

        public Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            MaskedWalletRequest.this.zzbRg = paymentMethodTokenizationParameters;
            return this;
        }

        public Builder setPhoneNumberRequired(boolean z) {
            MaskedWalletRequest.this.zzbQV = z;
            return this;
        }

        public Builder setShippingAddressRequired(boolean z) {
            MaskedWalletRequest.this.zzbQW = z;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.zzbRd = true;
        this.zzbRe = true;
    }

    MaskedWalletRequest(String str, boolean z, boolean z2, boolean z3, String str2, String str3, String str4, Cart cart, boolean z4, boolean z5, CountrySpecification[] countrySpecificationArr, boolean z6, boolean z7, ArrayList<CountrySpecification> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, ArrayList<Integer> arrayList2, String str5) {
        this.zzbPW = str;
        this.zzbQV = z;
        this.zzbQW = z2;
        this.zzbQX = z3;
        this.zzbQY = str2;
        this.zzbPP = str3;
        this.zzbQZ = str4;
        this.zzbQg = cart;
        this.zzbRa = z4;
        this.zzbRb = z5;
        this.zzbRc = countrySpecificationArr;
        this.zzbRd = z6;
        this.zzbRe = z7;
        this.zzbRf = arrayList;
        this.zzbRg = paymentMethodTokenizationParameters;
        this.zzbRh = arrayList2;
        this.zzUI = str5;
    }

    public static Builder newBuilder() {
        MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new Builder();
    }

    public boolean allowDebitCard() {
        return this.zzbRe;
    }

    public boolean allowPrepaidCard() {
        return this.zzbRd;
    }

    public ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzbRh;
    }

    public ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.zzbRf;
    }

    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzbRc;
    }

    public Cart getCart() {
        return this.zzbQg;
    }

    public String getCountryCode() {
        return this.zzUI;
    }

    public String getCurrencyCode() {
        return this.zzbPP;
    }

    public String getEstimatedTotalPrice() {
        return this.zzbQY;
    }

    public String getMerchantName() {
        return this.zzbQZ;
    }

    public String getMerchantTransactionId() {
        return this.zzbPW;
    }

    public PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzbRg;
    }

    @Deprecated
    public boolean isBillingAgreement() {
        return this.zzbRb;
    }

    public boolean isPhoneNumberRequired() {
        return this.zzbQV;
    }

    public boolean isShippingAddressRequired() {
        return this.zzbQW;
    }

    @Deprecated
    public boolean useMinimalBillingAddress() {
        return this.zzbQX;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.zza(this, parcel, i);
    }
}
