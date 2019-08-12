package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;

public class AndroidPayInstrument extends BraintreePaymentInstrument {
    private String countryCode;
    private String postalCode;

    public boolean equals(Object o) {
        if (o != null && (o instanceof AndroidPayInstrument)) {
            return ((AndroidPayInstrument) o).getType().equals(getType());
        }
        return false;
    }

    public int hashCode() {
        return getType().ordinal();
    }

    public int getIcon() {
        return C0716R.C0717drawable.icon_creditcard;
    }

    public boolean isEditable() {
        return true;
    }

    public InstrumentType getType() {
        return InstrumentType.AndroidPay;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode2) {
        this.countryCode = countryCode2;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode2) {
        this.postalCode = postalCode2;
    }

    public String getDisplayName(Context context) {
        return context.getString(C0716R.string.payment_type_android_pay);
    }
}
