package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public abstract class OldPaymentInstrument implements Serializable {
    public static final int INVALID_ID = 0;
    private boolean currencyMismatchApproved;

    /* renamed from: id */
    protected long f1081id = 0;
    private String name;

    public enum InstrumentType {
        BraintreeCreditCard,
        DigitalRiverCreditCard,
        GoogleWallet,
        AndroidPay,
        PayPal,
        PayU,
        Alipay,
        BusinessTravelInvoice,
        BusinessTravelCentralizedBilling,
        Other,
        Sofort,
        iDEAL,
        AlipayRedirect
    }

    public abstract boolean equals(Object obj);

    public abstract String getDisplayName(Context context);

    public abstract int getIcon();

    public abstract InstrumentType getType();

    public abstract int hashCode();

    public abstract boolean isEditable();

    public long getId() {
        return this.f1081id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.f1081id = id;
    }

    public boolean hasValidId() {
        return this.f1081id > 0;
    }

    public boolean isCurrencyMismatchApproved() {
        return this.currencyMismatchApproved;
    }

    public void setCurrencyMismatchApproved(boolean currencyMismatchApproved2) {
        this.currencyMismatchApproved = currencyMismatchApproved2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }
}
