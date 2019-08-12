package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.TripSecondaryGuest;
import com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.GiftCreditParams;
import com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;

public class CreateBillRequestBodyV1 implements CreateBillRequestBody {
    @JsonProperty("bill_price_quote_key")
    String billPriceQuoteKey;
    @JsonProperty("idempotence_key")
    String idempotenceKey;
    @JsonProperty("payment_params")
    PaymentParams paymentParams;
    @JsonProperty("products")
    List<Product> products;
    @JsonProperty("user_id")
    String userId;

    private static class AirbnbCredit {
        @JsonProperty("include_airbnb_credit")
        Boolean isAirbnbCreditIncluded;

        private AirbnbCredit(Boolean isAirbnbCreditIncluded2) {
            this.isAirbnbCreditIncluded = isAirbnbCreditIncluded2;
        }
    }

    private static class BraintreeParams {
        @JsonProperty("payment_method_cvv_nonce")
        String cvvNonce;

        private BraintreeParams(Builder builder) {
            this.cvvNonce = builder.cvvNonce;
        }
    }

    private static class ExistingInstrumentParams {
        @JsonProperty("id")

        /* renamed from: id */
        Long f1243id;
        @JsonProperty("zip_retry")
        String zipRetry;

        private ExistingInstrumentParams(Builder builder) {
            if (builder.paymentOption.hasValidGibraltarId()) {
                this.f1243id = Long.valueOf(builder.paymentOption.getGibraltarInstrumentId());
            } else {
                this.f1243id = Long.valueOf(builder.paymentOption.getLegacyInstrumentId());
            }
            this.zipRetry = builder.zipRetry;
        }
    }

    private static class GiftCreditProduct extends Product {
        @JsonProperty("amount_micros")
        long amountMicros;
        @JsonProperty("amount_micros_native")
        long amountMicrosNative;
        @JsonProperty("amount_micros_usd")
        long amountMicrosUsd;
        @JsonProperty("display_currency")
        String displayCurrency;
        @JsonProperty("gift_credit_params")
        GiftCreditParams giftCreditParams;
        @JsonProperty("native_currency")
        String nativeCurrency;
        @JsonProperty("payment2_product_type")
        String payment2ProductType;
        @JsonProperty("user_id")
        long userId;

        private GiftCreditProduct(Builder builder, GiftCardClientParameters giftCardClientParameters) {
            super(giftCardClientParameters);
            this.payment2ProductType = "Gift Credit";
            this.displayCurrency = builder.displayCurrency;
            this.nativeCurrency = builder.displayCurrency;
            this.userId = builder.userId;
            this.amountMicrosNative = giftCardClientParameters.getAmountMicros();
            this.amountMicrosUsd = this.amountMicrosNative;
            this.amountMicros = this.amountMicrosNative;
            this.giftCreditParams = new GiftCreditParams(giftCardClientParameters);
        }
    }

    private static class PaidAmenityProduct extends Product {
        @JsonProperty("number_of_units")
        Integer numberOfUnits;
        @JsonProperty("paid_amenity_id")
        Long paidAmenityId;
        @JsonProperty("reservation_confirmation_code")
        String reservationConfirmationCode;

        private PaidAmenityProduct(PaidAmenityClientParameters paidAmenityClientParameters) {
            super(paidAmenityClientParameters);
            this.paidAmenityId = Long.valueOf(paidAmenityClientParameters.paidAmenityId());
            this.numberOfUnits = Integer.valueOf(paidAmenityClientParameters.numberOfUnits());
            this.reservationConfirmationCode = paidAmenityClientParameters.reservationConfirmationCode();
        }
    }

    private static class PaymentParams {
        @JsonProperty("airbnb_credit")
        AirbnbCredit airbnbCredit;
        @JsonProperty("braintree")
        BraintreeParams braintree;
        @JsonProperty("display_currency")
        String displayCurrency;
        @JsonProperty("existing_gibraltar_instrument")
        ExistingInstrumentParams existingGibraltarInstrumentParams;
        @JsonProperty("existing_payment_instrument")
        ExistingInstrumentParams existingInstrumentParams;
        @JsonProperty("method")
        String paymentMethodServerKey;
        @JsonProperty("threatmetrix_session_id")
        String threatMetrixSessionId;

        private PaymentParams(Builder builder) {
            this.displayCurrency = builder.displayCurrency;
            PaymentOption paymentOption = builder.paymentOption;
            this.paymentMethodServerKey = paymentOption.getPaymentMethodType();
            if (paymentOption.hasValidGibraltarId()) {
                this.existingGibraltarInstrumentParams = new ExistingInstrumentParams(builder);
            } else {
                this.existingInstrumentParams = new ExistingInstrumentParams(builder);
            }
            this.airbnbCredit = builder.airbnbCredit;
            this.braintree = new BraintreeParams(builder);
            this.threatMetrixSessionId = builder.threatMetrixSessionId;
        }
    }

    private static class Product {
        @JsonProperty("product_type")
        String productType;

        private Product(QuickPayParameters qickPayParameters) {
            this.productType = qickPayParameters.productType().getServerKey();
        }
    }

    private static class TripProduct extends Product {
        @JsonProperty("number_of_guests")
        Integer numberOfGuests;
        @JsonProperty("secondary_guest_infos")
        List<TripSecondaryGuest> secondaryGuests;
        @JsonProperty("mt_scheduled_template_id")
        Long templateId;

        private TripProduct(MagicalTripsClientParameters tripParameters) {
            super(tripParameters);
            this.templateId = Long.valueOf(tripParameters.templateId());
            this.numberOfGuests = Integer.valueOf(tripParameters.guestCount());
            this.secondaryGuests = tripParameters.secondaryGuests();
        }
    }

    public static final class Builder implements com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBody.Builder {
        /* access modifiers changed from: private */
        public AirbnbCredit airbnbCredit;
        /* access modifiers changed from: private */
        public String billPriceQuoteKey;
        /* access modifiers changed from: private */
        public String cvvNonce;
        /* access modifiers changed from: private */
        public String displayCurrency;
        /* access modifiers changed from: private */
        public String idempotenceKey;
        /* access modifiers changed from: private */
        public PaymentOption paymentOption;
        /* access modifiers changed from: private */
        public List<QuickPayParameters> quickPayParameters;
        /* access modifiers changed from: private */
        public String threatMetrixSessionId;
        /* access modifiers changed from: private */
        public long userId;
        /* access modifiers changed from: private */
        public String zipRetry;

        private Builder() {
        }

        public Builder userId(long val) {
            this.userId = val;
            return this;
        }

        public Builder quickPayParameters(QuickPayParameters... val) {
            if (this.quickPayParameters == null) {
                this.quickPayParameters = Lists.newArrayList();
            }
            this.quickPayParameters.addAll(Arrays.asList(val));
            return this;
        }

        public Builder paymentOption(PaymentOption val) {
            this.paymentOption = val;
            return this;
        }

        public Builder displayCurrency(String val) {
            this.displayCurrency = val;
            return this;
        }

        public Builder isAirbnbCreditIncluded(boolean val) {
            this.airbnbCredit = new AirbnbCredit(Boolean.valueOf(val));
            return this;
        }

        public Builder cvvNonce(String val) {
            this.cvvNonce = val;
            return this;
        }

        public Builder idempotenceKey(String val) {
            this.idempotenceKey = val;
            return this;
        }

        public Builder billPriceQuoteKey(String val) {
            this.billPriceQuoteKey = val;
            return this;
        }

        public Builder threatMetrixSessionId(String val) {
            this.threatMetrixSessionId = val;
            return this;
        }

        public Builder zipRetry(String val) {
            this.zipRetry = val;
            return this;
        }

        public CreateBillRequestBodyV1 build() {
            return new CreateBillRequestBodyV1(this);
        }
    }

    public static Builder make() {
        return new Builder();
    }

    private CreateBillRequestBodyV1(Builder builder) {
        this.userId = String.valueOf(builder.userId);
        this.paymentParams = new PaymentParams(builder);
        this.products = FluentIterable.from((Iterable<E>) builder.quickPayParameters).transform(CreateBillRequestBodyV1$$Lambda$1.lambdaFactory$(this, builder)).toList();
        this.idempotenceKey = builder.idempotenceKey;
        this.billPriceQuoteKey = builder.billPriceQuoteKey;
    }

    /* access modifiers changed from: private */
    public Product quickPayParameterParameterToProduct(Builder builder, QuickPayParameters quickPayParameters) {
        switch (quickPayParameters.productType()) {
            case Trip:
                return new TripProduct((MagicalTripsClientParameters) quickPayParameters);
            case PaidAmenity:
                return new PaidAmenityProduct((PaidAmenityClientParameters) quickPayParameters);
            case GiftCredit:
                return new GiftCreditProduct(builder, (GiftCardClientParameters) quickPayParameters);
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown product type."));
                return null;
        }
    }
}
