package com.airbnb.android.core.requests.booking.requestbodies;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.payments.AlipayRedirectPaymentInstrument;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.BraintreePaymentInstrument;
import com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.models.payments.PostalAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.EnumSet;
import java.util.List;

public class BookingRequestBody {
    @JsonProperty("business_travel")
    C0833BusinessTravel businessTravel;
    @JsonProperty("guest_identifications")
    GuestIdentificationsWrapper guestIdentifications;
    @JsonProperty("payments")
    C0834Payments payments;
    @JsonProperty("reservation_confirmation_code")
    String reservationConfirmationCode;
    @JsonProperty("reservation_details")
    ReservationDetails reservationDetails;

    /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$BusinessTravel */
    static class C0833BusinessTravel {
        @JsonProperty("is_tracking_only")
        Boolean isTrackingOnly;
        @JsonProperty("trip_notes")
        String tripNotes;

        public C0833BusinessTravel(Boolean isTrackingOnly2, String tripNotes2) {
            this.isTrackingOnly = isTrackingOnly2;
            this.tripNotes = tripNotes2;
        }
    }

    static class GuestIdentification {
        @JsonProperty("id")

        /* renamed from: id */
        Integer f1086id;
        @JsonProperty("id_type")
        String idType;
        @JsonProperty("is_booker")
        boolean isBooker;

        GuestIdentification(GuestIdentity identity) {
            this.f1086id = Integer.valueOf(identity.getId());
            this.isBooker = identity.isBooker();
            switch (C08361.$SwitchMap$com$airbnb$android$core$interfaces$GuestIdentity$Type[identity.getType().ordinal()]) {
                case 1:
                    this.idType = "passport";
                    return;
                case 2:
                    this.idType = "china_resident_identity_card";
                    return;
                default:
                    this.idType = "unknown";
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown identification type: " + identity.getType().name()));
                    return;
            }
        }
    }

    static class GuestIdentificationsWrapper {
        @JsonProperty("identifications")
        List<GuestIdentification> identifications;

        GuestIdentificationsWrapper(List<GuestIdentification> identifications2) {
            this.identifications = identifications2;
        }
    }

    /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments */
    static class C0834Payments {
        @JsonProperty("android_pay")
        AndroidPay androidPay;
        @JsonProperty("braintree")
        Braintree braintree;
        @JsonProperty("business_travel")
        C0835BusinessTravel businessTravel;
        @JsonProperty("credit_card")
        CreditCard creditCard;
        @JsonProperty("display_currency")
        String displayCurrency;
        @JsonProperty("existing_payment_instrument")
        ExistingPaymentInstrument existingPaymentInstrument;
        @JsonProperty("method")
        String method;
        @JsonProperty("paypal")
        PayPal payPal;
        @JsonProperty("user_agreed_to_currency_mismatch")
        Boolean userAgreedToCurrencyMismatch;

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$AndroidPay */
        static class AndroidPay {
            @JsonProperty("country")
            String country;
            @JsonProperty("zip")
            String zip;

            public AndroidPay(AndroidPayInstrument instrument) {
                this.zip = instrument.getPostalCode();
                this.country = instrument.getCountryCode();
            }
        }

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$Braintree */
        static class Braintree {
            @JsonProperty("device_data")
            String deviceData;
            @JsonProperty("payment_method_nonce")
            String paymentMethodNonce;

            public Braintree(BraintreePaymentInstrument braintreePaymentInstrument) {
                this.paymentMethodNonce = braintreePaymentInstrument.getNonce();
                if (braintreePaymentInstrument instanceof PayPalInstrument) {
                    this.deviceData = braintreePaymentInstrument.getDeviceData();
                }
            }
        }

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$BusinessTravel */
        static class C0835BusinessTravel {
            @JsonProperty("business_entity_group_id")
            Long businessEntityGroupId;

            public C0835BusinessTravel(Long businessEntityGroupId2) {
                this.businessEntityGroupId = businessEntityGroupId2;
            }
        }

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$CreditCard */
        static class CreditCard {
            @JsonProperty("country")
            String country;
            @JsonProperty("zip")
            String zip;

            public CreditCard(BraintreeCreditCard creditCardInstrument) {
                this.zip = creditCardInstrument.getPostalCode();
                this.country = creditCardInstrument.getCountry();
            }
        }

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$ExistingPaymentInstrument */
        static class ExistingPaymentInstrument {
            @JsonProperty("id")

            /* renamed from: id */
            Long f1087id;
            @JsonProperty("zip_retry")
            String zipRetry;

            public ExistingPaymentInstrument(OldPaymentInstrument paymentInstrument) {
                this.f1087id = Long.valueOf(paymentInstrument.getId());
                if (BookingRequestBody.shouldAddUpdatedPostalCode(paymentInstrument)) {
                    this.zipRetry = ((BraintreeCreditCard) paymentInstrument).getUpdatedPostalCode();
                }
            }
        }

        /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$Payments$PayPal */
        static class PayPal {
            @JsonProperty("address1")
            String address1;
            @JsonProperty("address2")
            String address2;
            @JsonProperty("city")
            String city;
            @JsonProperty("country")
            String country;
            @JsonProperty("state")
            String state;
            @JsonProperty("zip")
            String zip;

            public PayPal(PayPalInstrument paypalInstrument) {
                PostalAddress address = paypalInstrument.getPostalAddress();
                this.address1 = address.getStreetAddress();
                this.address2 = address.getExtendedAddress();
                this.city = address.getLocality();
                this.state = address.getRegion();
                this.zip = address.getPostalCode();
                this.country = address.getCountryCodeAlpha2();
            }
        }

        /* synthetic */ C0834Payments(PaymentsBuilder x0, C08361 x1) {
            this(x0);
        }

        private C0834Payments(PaymentsBuilder builder) {
            this.androidPay = builder.androidPay;
            this.braintree = builder.braintree;
            this.businessTravel = builder.businessTravel;
            this.creditCard = builder.creditCard;
            this.displayCurrency = builder.displayCurrency;
            this.existingPaymentInstrument = builder.existingPaymentInstrument;
            this.method = builder.method;
            this.payPal = builder.payPal;
            this.userAgreedToCurrencyMismatch = builder.userAgreedToCurrencyMismatch;
        }
    }

    static class ReservationDetails {
        @JsonProperty("message_to_host")
        String messageToHost;

        public ReservationDetails(String messageToHost2) {
            this.messageToHost = messageToHost2;
        }
    }

    /* renamed from: com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody$1 */
    static /* synthetic */ class C08361 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$android$core$interfaces$GuestIdentity$Type = new int[Type.values().length];

        static {
            f1088x2f038da7 = new int[InstrumentType.values().length];
            try {
                f1088x2f038da7[InstrumentType.AndroidPay.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1088x2f038da7[InstrumentType.PayPal.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1088x2f038da7[InstrumentType.BraintreeCreditCard.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1088x2f038da7[InstrumentType.BusinessTravelInvoice.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1088x2f038da7[InstrumentType.BusinessTravelCentralizedBilling.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1088x2f038da7[InstrumentType.Alipay.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1088x2f038da7[InstrumentType.AlipayRedirect.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$interfaces$GuestIdentity$Type[Type.Passport.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$interfaces$GuestIdentity$Type[Type.ChineseNationalID.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public static class Builder {
        C0833BusinessTravel businessTravel;
        String currency;
        List<GuestIdentification> guestIdentifications;
        C0834Payments payments;
        String reservationConfirmationCode;
        ReservationDetails reservationDetails;

        public Builder confirmationCode(String reservationConfirmationCode2) {
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        public Builder paymentInstrument(OldPaymentInstrument paymentInstrument) {
            PaymentsBuilder builder = new PaymentsBuilder();
            if (paymentInstrument.hasValidId()) {
                builder.existingPaymentInstrumentId(new ExistingPaymentInstrument(paymentInstrument));
            } else if (!(paymentInstrument instanceof AlipayRedirectPaymentInstrument)) {
                switch (paymentInstrument.getType()) {
                    case AndroidPay:
                        builder.androidPay(new AndroidPay((AndroidPayInstrument) paymentInstrument));
                        break;
                    case PayPal:
                        builder.paypal(new PayPal((PayPalInstrument) paymentInstrument));
                        break;
                    case BraintreeCreditCard:
                        builder.creditCard(new CreditCard((BraintreeCreditCard) paymentInstrument));
                        break;
                    case BusinessTravelInvoice:
                    case BusinessTravelCentralizedBilling:
                        builder.businessTravel(new C0835BusinessTravel(Long.valueOf(((BusinessTravelPaymentInstruments) paymentInstrument).getBusinessEntityId())));
                        break;
                }
                if (!isBusinessTravelPaymentMethod(paymentInstrument)) {
                    builder.braintree(new Braintree((BraintreePaymentInstrument) paymentInstrument));
                }
            }
            builder.method(getPaymentMethodServerKey(paymentInstrument));
            builder.displayCurrency(this.currency);
            builder.userAgreedToCurrencyMismatch(Boolean.valueOf(paymentInstrument.isCurrencyMismatchApproved()));
            this.payments = builder.build();
            return this;
        }

        public Builder currency(String currency2) {
            this.payments.displayCurrency = currency2;
            return this;
        }

        public Builder messageToHost(String messageToHost) {
            this.reservationDetails = new ReservationDetails(messageToHost);
            return this;
        }

        public Builder guestIdentities(List<GuestIdentity> guestIdentities) {
            if (guestIdentities != null) {
                this.guestIdentifications = FluentIterable.from((Iterable<E>) guestIdentities).transform(BookingRequestBody$Builder$$Lambda$1.lambdaFactory$()).toList();
            }
            return this;
        }

        public Builder businessTripDetails(Boolean isBusinessTrip, Boolean isCentralPay, String tripNote) {
            if (isBusinessTrip != null) {
                this.businessTravel = new C0833BusinessTravel(Boolean.valueOf(isBusinessTrip.booleanValue() && (isCentralPay == null || !isCentralPay.booleanValue())), tripNote);
            }
            return this;
        }

        private boolean isBusinessTravelPaymentMethod(OldPaymentInstrument paymentInstrument) {
            return EnumSet.of(InstrumentType.BusinessTravelCentralizedBilling, InstrumentType.BusinessTravelInvoice).contains(paymentInstrument.getType());
        }

        private String getPaymentMethodServerKey(OldPaymentInstrument paymentInstrument) {
            switch (paymentInstrument.getType()) {
                case AndroidPay:
                    return PaymentMethod.AndroidPay.getServerKey();
                case PayPal:
                    return PaymentMethod.PayPal.getServerKey();
                case BraintreeCreditCard:
                    return PaymentMethod.CreditCard.getServerKey();
                case BusinessTravelInvoice:
                    return PaymentMethod.BusinessInvoice.getServerKey();
                case BusinessTravelCentralizedBilling:
                    return PaymentMethod.BusinessCentralizedBilling.getServerKey();
                case Alipay:
                    return PaymentMethod.Alipay.getServerKey();
                case AlipayRedirect:
                    return PaymentMethod.AliPayRedirect.getServerKey();
                default:
                    return null;
            }
        }

        public BookingRequestBody build() {
            return new BookingRequestBody(this, null);
        }
    }

    private enum PaymentMethod {
        Alipay("alipay_direct"),
        AndroidPay("android_pay"),
        CreditCard("cc"),
        PayPal("braintree_paypal"),
        BusinessInvoice("business_travel_invoice"),
        BusinessCentralizedBilling("business_travel_centralized_billing"),
        ExistingPaymentInstrument("payment_instrument"),
        AliPayRedirect("alipay_redirect");
        
        private final String serverKey;

        private PaymentMethod(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public String getServerKey() {
            return this.serverKey;
        }
    }

    static class PaymentsBuilder {
        /* access modifiers changed from: private */
        public AndroidPay androidPay;
        /* access modifiers changed from: private */
        public Braintree braintree;
        /* access modifiers changed from: private */
        public C0835BusinessTravel businessTravel;
        /* access modifiers changed from: private */
        public CreditCard creditCard;
        /* access modifiers changed from: private */
        public String displayCurrency;
        /* access modifiers changed from: private */
        public ExistingPaymentInstrument existingPaymentInstrument;
        /* access modifiers changed from: private */
        public String method;
        /* access modifiers changed from: private */
        public PayPal payPal;
        /* access modifiers changed from: private */
        public Boolean userAgreedToCurrencyMismatch;

        PaymentsBuilder() {
        }

        public PaymentsBuilder androidPay(AndroidPay androidPay2) {
            this.androidPay = androidPay2;
            return this;
        }

        public PaymentsBuilder braintree(Braintree braintree2) {
            this.braintree = braintree2;
            return this;
        }

        public PaymentsBuilder businessTravel(C0835BusinessTravel businessTravel2) {
            this.businessTravel = businessTravel2;
            return this;
        }

        public PaymentsBuilder creditCard(CreditCard creditCard2) {
            this.creditCard = creditCard2;
            return this;
        }

        public PaymentsBuilder displayCurrency(String displayCurrency2) {
            this.displayCurrency = displayCurrency2;
            return this;
        }

        public PaymentsBuilder existingPaymentInstrumentId(ExistingPaymentInstrument existingPaymentInstrument2) {
            this.existingPaymentInstrument = existingPaymentInstrument2;
            return this;
        }

        public PaymentsBuilder method(String method2) {
            this.method = method2;
            return this;
        }

        public PaymentsBuilder paypal(PayPal payPal2) {
            this.payPal = payPal2;
            return this;
        }

        public PaymentsBuilder userAgreedToCurrencyMismatch(Boolean userAgreedToCurrencyMismatch2) {
            this.userAgreedToCurrencyMismatch = userAgreedToCurrencyMismatch2;
            return this;
        }

        public C0834Payments build() {
            return new C0834Payments(this, null);
        }
    }

    /* synthetic */ BookingRequestBody(Builder x0, C08361 x1) {
        this(x0);
    }

    private BookingRequestBody(Builder builder) {
        this.reservationConfirmationCode = builder.reservationConfirmationCode;
        this.businessTravel = builder.businessTravel;
        this.guestIdentifications = new GuestIdentificationsWrapper(builder.guestIdentifications);
        this.payments = builder.payments;
        this.reservationDetails = builder.reservationDetails;
    }

    /* access modifiers changed from: private */
    public static boolean shouldAddUpdatedPostalCode(OldPaymentInstrument paymentInstrument) {
        return (paymentInstrument instanceof BraintreeCreditCard) && ((BraintreeCreditCard) paymentInstrument).hasUpdatedPostalCode();
    }
}
