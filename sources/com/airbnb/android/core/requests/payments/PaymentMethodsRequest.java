package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.payments.PaymentMethodsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PaymentMethodsRequest extends BaseRequestV2<PaymentMethodsResponse> {
    private static final String PARAM_BILL_PRODUCT_TYPE = "bill_product_type";
    private static final String PARAM_COUNTRY_CODE = "country";
    private static final String PARAM_PAYMENT_ID = "payment_id";
    private static final String PARAM_RESERVATION_CONFIRMATION_CODE = "reservation_confirmation_code";
    private static final String PARAM_USER_ID = "user_id";
    private final String billProductType;
    private final String countryCode;
    private final String paymentId;
    private final String reservationConfirmationCode;

    public static class Builder {
        /* access modifiers changed from: private */
        public String billProductType;
        /* access modifiers changed from: private */
        public final String countryCode;
        /* access modifiers changed from: private */
        public String paymentId;
        /* access modifiers changed from: private */
        public String reservationConfirmationCode;

        public Builder(String countryCode2) {
            this.countryCode = countryCode2;
        }

        public Builder withReservationConfirmationCode(String reservationConfirmationCode2) {
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        public Builder withPaymentId(String paymentId2) {
            this.paymentId = paymentId2;
            return this;
        }

        public Builder withBillProductType(String billProductType2) {
            this.billProductType = billProductType2;
            return this;
        }

        public PaymentMethodsRequest build() {
            return new PaymentMethodsRequest(this);
        }
    }

    private PaymentMethodsRequest(Builder builder) {
        this.countryCode = builder.countryCode;
        this.reservationConfirmationCode = builder.reservationConfirmationCode;
        this.paymentId = builder.paymentId;
        this.billProductType = builder.billProductType;
    }

    public Type successResponseType() {
        return PaymentMethodsResponse.class;
    }

    public String getPath() {
        return "payment_methods";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("country", this.countryCode).mo7894kv("user_id", AirbnbAccountManager.currentUserId()).mo7895kv(PARAM_RESERVATION_CONFIRMATION_CODE, this.reservationConfirmationCode).mo7895kv(PARAM_PAYMENT_ID, this.paymentId).mo7895kv(PARAM_BILL_PRODUCT_TYPE, this.billProductType);
    }
}
