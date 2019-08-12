package com.airbnb.android.core.requests.payments.requestbodies;

import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePaymentInstrumentRequestBody {
    @JsonProperty("type")
    String type;

    public static class AlipayBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("alipay_login_id")
        String alipayLoginId;
        @JsonProperty("mobile_phone_country")
        String mobilePhoneCountry;
        @JsonProperty("mobile_phone_number")
        String mobilePhoneNumber;
        @JsonProperty("national_id_last_five_digits")
        String nationalIdLastFiveDigits;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String alipayLoginId;
            /* access modifiers changed from: private */
            public String mobilePhoneCountry;
            /* access modifiers changed from: private */
            public String mobilePhoneNumber;
            /* access modifiers changed from: private */
            public String nationalIdLastFiveDigits;

            public Builder alipayLoginId(String val) {
                this.alipayLoginId = val;
                return this;
            }

            public Builder mobilePhoneNumber(String val) {
                this.mobilePhoneNumber = val;
                return this;
            }

            public Builder mobilePhoneCountry(String val) {
                this.mobilePhoneCountry = val;
                return this;
            }

            public Builder nationalIdLastFiveDigits(String val) {
                this.nationalIdLastFiveDigits = val;
                return this;
            }

            public AlipayBody build() {
                return new AlipayBody(this);
            }
        }

        private AlipayBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.Alipay.getServerKey();
            this.alipayLoginId = builder.alipayLoginId;
            this.mobilePhoneNumber = builder.mobilePhoneNumber;
            this.mobilePhoneCountry = builder.mobilePhoneCountry;
            this.nationalIdLastFiveDigits = builder.nationalIdLastFiveDigits;
        }
    }

    public static class AlipayV2Body extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("auth_flow")
        String authFlow;

        public AlipayV2Body() {
            this.type = C6200PaymentInstrumentType.Alipay.getServerKey();
            this.authFlow = "MOBILE_DEEPLINK";
        }
    }

    public static class AndroidPayBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("country")
        String country;
        @JsonProperty("payment_method_nonce")
        String paymentMethodNonce;
        @JsonProperty("postal_code")
        String postalCode;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String country;
            /* access modifiers changed from: private */
            public String paymentMethodNonce;
            /* access modifiers changed from: private */
            public String postalCode;

            public Builder paymentMethodNonce(String val) {
                this.paymentMethodNonce = val;
                return this;
            }

            public Builder postalCode(String val) {
                this.postalCode = val;
                return this;
            }

            public Builder country(String val) {
                this.country = val;
                return this;
            }

            public AndroidPayBody build() {
                return new AndroidPayBody(this);
            }
        }

        private AndroidPayBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.AndroidPay.getServerKey();
            this.paymentMethodNonce = builder.paymentMethodNonce;
            this.postalCode = builder.postalCode;
            this.country = builder.country;
        }

        public static Builder make() {
            return new Builder();
        }
    }

    public static class BankTransferLegacyPayoutBody extends LegacyPayoutBody {
        @JsonProperty("full_name")
        String fullName;
        @JsonProperty("iban")
        String iban;
        @JsonProperty("swift")
        String swiftCode;

        public static final class Builder {
            /* access modifiers changed from: private */
            public AirAddress address;
            /* access modifiers changed from: private */
            public String fullName;
            /* access modifiers changed from: private */
            public String iban;
            /* access modifiers changed from: private */
            public String swiftCode;
            /* access modifiers changed from: private */
            public String targetCurrency;
            /* access modifiers changed from: private */
            public String userId;

            public Builder address(AirAddress val) {
                this.address = val;
                return this;
            }

            public Builder fullName(String val) {
                this.fullName = val;
                return this;
            }

            public Builder swiftCode(String val) {
                this.swiftCode = val;
                return this;
            }

            public Builder iban(String val) {
                this.iban = val;
                return this;
            }

            public Builder targetCurrency(String val) {
                this.targetCurrency = val;
                return this;
            }

            public Builder userId(String val) {
                this.userId = val;
                return this;
            }

            public BankTransferLegacyPayoutBody build() {
                return new BankTransferLegacyPayoutBody(this);
            }
        }

        private BankTransferLegacyPayoutBody(Builder builder) {
            super(builder.address, C6200PaymentInstrumentType.BankTransfer, builder.targetCurrency, builder.userId);
            this.fullName = builder.fullName;
            this.swiftCode = builder.swiftCode;
            this.iban = builder.iban;
        }
    }

    public static class BusinessTravelBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("business_entity_group_id")
        long businessEntityGroupId;

        public static final class Builder {
            /* access modifiers changed from: private */
            public long businessEntityGroupId;

            public Builder businessEntityGroupId(long businessEntityGroupId2) {
                this.businessEntityGroupId = businessEntityGroupId2;
                return this;
            }

            public BusinessTravelBody build() {
                return new BusinessTravelBody(this);
            }
        }

        private BusinessTravelBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.BusinessTravelInvoice.getServerKey();
            this.businessEntityGroupId = builder.businessEntityGroupId;
        }
    }

    public static class CreditCardBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("country")
        String country;
        @JsonProperty("payment_method_nonce")
        String paymentMethodNonce;
        @JsonProperty("postal_code")
        String postalCode;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String country;
            /* access modifiers changed from: private */
            public String paymentMethodNonce;
            /* access modifiers changed from: private */
            public String postalCode;

            public Builder paymentMethodNonce(String val) {
                this.paymentMethodNonce = val;
                return this;
            }

            public Builder postalCode(String val) {
                this.postalCode = val;
                return this;
            }

            public Builder country(String val) {
                this.country = val;
                return this;
            }

            public CreditCardBody build() {
                return new CreditCardBody(this);
            }
        }

        private CreditCardBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.CreditCard.getServerKey();
            this.paymentMethodNonce = builder.paymentMethodNonce;
            this.postalCode = builder.postalCode;
            this.country = builder.country;
        }

        public static Builder make() {
            return new Builder();
        }
    }

    public static class DigitalRiverCreditCardBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("birthdate")
        String birthdate;
        @JsonProperty("brazil_cpf")
        String brazilCpf;
        @JsonProperty("country")
        String countryCode;
        @JsonProperty("first_name")
        String firstName;
        @JsonProperty("last_name")
        String lastName;
        @JsonProperty("locality")
        String locality;
        @JsonProperty("mobile_phone_number")
        String mobilePhoneNumber;
        @JsonProperty("payment_method_cse_payload")
        String paymentMethodCsePayload;
        @JsonProperty("postal_code")
        String postalCode;
        @JsonProperty("region")
        String region;
        @JsonProperty("street_address1")
        String streetAddress1;
        @JsonProperty("street_address2")
        String streetAddress2;
        @JsonProperty("street_address3")
        String streetAddress3;
        @JsonProperty("user_id")
        String userId;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String birthdate;
            /* access modifiers changed from: private */
            public String brazilCpf;
            /* access modifiers changed from: private */
            public String countryCode;
            /* access modifiers changed from: private */
            public String firstName;
            /* access modifiers changed from: private */
            public String lastName;
            /* access modifiers changed from: private */
            public String locality;
            /* access modifiers changed from: private */
            public String mobilePhoneNumber;
            /* access modifiers changed from: private */
            public String paymentMethodCsePayload;
            /* access modifiers changed from: private */
            public String postalCode;
            /* access modifiers changed from: private */
            public String region;
            /* access modifiers changed from: private */
            public String streetAddress1;
            /* access modifiers changed from: private */
            public String streetAddress2;
            /* access modifiers changed from: private */
            public String streetAddress3;
            /* access modifiers changed from: private */
            public String userId;

            public Builder paymentMethodCsePayload(String val) {
                this.paymentMethodCsePayload = val;
                return this;
            }

            public Builder firstName(String val) {
                this.firstName = val;
                return this;
            }

            public Builder lastName(String val) {
                this.lastName = val;
                return this;
            }

            public Builder userId(String val) {
                this.userId = val;
                return this;
            }

            public Builder birthdate(String val) {
                this.birthdate = val;
                return this;
            }

            public Builder mobilePhoneNumber(String val) {
                this.mobilePhoneNumber = val;
                return this;
            }

            public Builder brazilCpf(String val) {
                this.brazilCpf = val;
                return this;
            }

            public Builder streetAddress1(String val) {
                this.streetAddress1 = val;
                return this;
            }

            public Builder streetAddress2(String val) {
                this.streetAddress2 = val;
                return this;
            }

            public Builder streetAddress3(String val) {
                this.streetAddress3 = val;
                return this;
            }

            public Builder locality(String val) {
                this.locality = val;
                return this;
            }

            public Builder region(String val) {
                this.region = val;
                return this;
            }

            public Builder countryCode(String val) {
                this.countryCode = val;
                return this;
            }

            public Builder postalCode(String val) {
                this.postalCode = val;
                return this;
            }

            public DigitalRiverCreditCardBody build() {
                return new DigitalRiverCreditCardBody(this);
            }
        }

        private DigitalRiverCreditCardBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.DigitalRiverCreditCard.getServerKey();
            this.paymentMethodCsePayload = builder.paymentMethodCsePayload;
            this.firstName = builder.firstName;
            this.lastName = builder.lastName;
            this.userId = builder.userId;
            this.birthdate = builder.birthdate;
            this.mobilePhoneNumber = builder.mobilePhoneNumber;
            this.brazilCpf = builder.brazilCpf;
            this.streetAddress1 = builder.streetAddress1;
            this.streetAddress2 = builder.streetAddress2;
            this.streetAddress3 = builder.streetAddress3;
            this.locality = builder.locality;
            this.region = builder.region;
            this.countryCode = builder.countryCode;
            this.postalCode = builder.postalCode;
        }
    }

    public static class LegacyPayoutBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("country")
        String country;
        @JsonProperty("locality")
        String locality;
        @JsonProperty("postal_code")
        String postalCode;
        @JsonProperty("region")
        String region;
        @JsonProperty("street_address1")
        String streetAddress1;
        @JsonProperty("street_address2")
        String streetAddress2;
        @JsonProperty("target_currency")
        String targetCurrency;
        @JsonProperty("type")
        String type;
        @JsonProperty("user_id")
        String userId;

        protected LegacyPayoutBody(AirAddress address, C6200PaymentInstrumentType payoutType, String targetCurrency2, String userId2) {
            this.streetAddress1 = address.streetAddressOne();
            this.streetAddress2 = address.streetAddressTwo();
            this.locality = address.city();
            this.region = address.state();
            this.country = address.countryCode();
            this.postalCode = address.postalCode();
            this.type = payoutType.getServerKey();
            this.targetCurrency = targetCurrency2;
            this.userId = userId2;
        }
    }

    public static class PayPalBody extends CreatePaymentInstrumentRequestBody {
        @JsonProperty("device_data")
        String deviceData;
        @JsonProperty("payment_method_nonce")
        String paymentMethodNonce;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String deviceData;
            /* access modifiers changed from: private */
            public String paymentMethodNonce;

            public Builder paymentMethodNonce(String val) {
                this.paymentMethodNonce = val;
                return this;
            }

            public Builder deviceData(String val) {
                this.deviceData = val;
                return this;
            }

            public PayPalBody build() {
                return new PayPalBody(this);
            }
        }

        private PayPalBody(Builder builder) {
            this.type = C6200PaymentInstrumentType.BraintreePaypal.getServerKey();
            this.paymentMethodNonce = builder.paymentMethodNonce;
            this.deviceData = builder.deviceData;
        }

        public static Builder make() {
            return new Builder();
        }
    }

    public static class PayPalLegacyPayoutBody extends LegacyPayoutBody {
        @JsonProperty("paypal_email")
        String paypalEmail;

        public static final class Builder {
            /* access modifiers changed from: private */
            public AirAddress address;
            /* access modifiers changed from: private */
            public String paypalEmail;
            /* access modifiers changed from: private */
            public String targetCurrency;
            /* access modifiers changed from: private */
            public String userId;

            public Builder address(AirAddress val) {
                this.address = val;
                return this;
            }

            public Builder paypalEmail(String val) {
                this.paypalEmail = val;
                return this;
            }

            public Builder targetCurrency(String val) {
                this.targetCurrency = val;
                return this;
            }

            public Builder userId(String val) {
                this.userId = val;
                return this;
            }

            public PayPalLegacyPayoutBody build() {
                return new PayPalLegacyPayoutBody(this);
            }
        }

        private PayPalLegacyPayoutBody(Builder builder) {
            super(builder.address, C6200PaymentInstrumentType.PayPal, builder.targetCurrency, builder.userId);
            this.paypalEmail = builder.paypalEmail;
        }
    }
}
