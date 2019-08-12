package com.airbnb.android.lib.payments.factories;

import android.os.Parcelable;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

public abstract class BillPriceQuoteRequestParams implements Parcelable {

    public static abstract class Builder {
        public abstract BillPriceQuoteRequestParams build();

        public abstract Builder clientType(QuickPayClientType quickPayClientType);

        public abstract Builder couponCode(String str);

        public abstract Builder displayCurrency(String str);

        public abstract Builder includeAirbnbCredit(boolean z);

        public abstract Builder paymentOption(PaymentOption paymentOption);

        public abstract Builder quickPayParameters(QuickPayParameters quickPayParameters);

        public abstract Builder userAgreedToCurrencyMismatch(boolean z);

        public abstract Builder zipRetry(String str);
    }

    public abstract QuickPayClientType clientType();

    public abstract String couponCode();

    public abstract String displayCurrency();

    public abstract boolean includeAirbnbCredit();

    public abstract PaymentOption paymentOption();

    public abstract QuickPayParameters quickPayParameters();

    public abstract boolean userAgreedToCurrencyMismatch();

    public abstract String zipRetry();

    public static Builder builder() {
        return new Builder();
    }
}
