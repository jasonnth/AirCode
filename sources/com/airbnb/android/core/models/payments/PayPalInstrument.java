package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.braintreepayments.api.models.PayPalAccountNonce;

public class PayPalInstrument extends BraintreePaymentInstrument {
    private String email;
    private PostalAddress postalAddress;

    public PayPalInstrument() {
    }

    public PayPalInstrument(PayPalAccountNonce account) {
        setNonce(account.getNonce());
        this.email = account.getEmail();
        this.postalAddress = new PostalAddress(account.getBillingAddress());
    }

    public boolean isEditable() {
        return false;
    }

    public int getIcon() {
        return C0716R.C0717drawable.icon_paypal_logo;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.email.equals(((PayPalInstrument) o).email);
    }

    public int hashCode() {
        return this.email.hashCode();
    }

    public InstrumentType getType() {
        return InstrumentType.PayPal;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public PostalAddress getPostalAddress() {
        return this.postalAddress;
    }

    public String getDisplayName(Context context) {
        if (getId() > 0) {
            return getEmail();
        }
        return context.getString(C0716R.string.space_separated, new Object[]{context.getString(C0716R.string.payment_type_paypal), getEmail()});
    }
}
