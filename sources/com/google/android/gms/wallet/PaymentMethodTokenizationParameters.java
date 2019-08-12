package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public final class PaymentMethodTokenizationParameters extends zza {
    public static final Creator<PaymentMethodTokenizationParameters> CREATOR = new zzs();
    int zzbRm;
    Bundle zzbRn = new Bundle();

    public final class Builder {
        private Builder() {
        }

        public Builder addParameter(String str, String str2) {
            zzac.zzh(str, "Tokenization parameter name must not be empty");
            zzac.zzh(str2, "Tokenization parameter value must not be empty");
            PaymentMethodTokenizationParameters.this.zzbRn.putString(str, str2);
            return this;
        }

        public PaymentMethodTokenizationParameters build() {
            return PaymentMethodTokenizationParameters.this;
        }

        public Builder setPaymentMethodTokenizationType(int i) {
            PaymentMethodTokenizationParameters.this.zzbRm = i;
            return this;
        }
    }

    private PaymentMethodTokenizationParameters() {
    }

    PaymentMethodTokenizationParameters(int i, Bundle bundle) {
        this.zzbRm = i;
        this.zzbRn = bundle;
    }

    public static Builder newBuilder() {
        PaymentMethodTokenizationParameters paymentMethodTokenizationParameters = new PaymentMethodTokenizationParameters();
        paymentMethodTokenizationParameters.getClass();
        return new Builder();
    }

    public Bundle getParameters() {
        return new Bundle(this.zzbRn);
    }

    public int getPaymentMethodTokenizationType() {
        return this.zzbRm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzs.zza(this, parcel, i);
    }
}
