package com.airbnb.android.lib.payments.networking.createbill;

import android.os.Parcelable;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

public abstract class CreateBillParameters implements Parcelable {

    public static abstract class Builder {
        public abstract Builder billPriceQuote(BillPriceQuote billPriceQuote);

        public abstract Builder billProductType(BillProductType billProductType);

        public abstract CreateBillParameters build();

        public abstract Builder currency(String str);

        public abstract Builder cvvNonce(String str);

        public abstract Builder postalCode(String str);

        public abstract Builder quickPayParameters(QuickPayParameters quickPayParameters);

        public abstract Builder selectedPaymentOption(PaymentOption paymentOption);

        public abstract Builder shouldIncludeAirbnbCredit(Boolean bool);

        public abstract Builder userId(long j);
    }

    public abstract BillPriceQuote billPriceQuote();

    public abstract BillProductType billProductType();

    public abstract String currency();

    public abstract String cvvNonce();

    public abstract String postalCode();

    public abstract QuickPayParameters quickPayParameters();

    public abstract PaymentOption selectedPaymentOption();

    public abstract Boolean shouldIncludeAirbnbCredit();

    public abstract long userId();

    public static Builder builder() {
        return new Builder();
    }
}
