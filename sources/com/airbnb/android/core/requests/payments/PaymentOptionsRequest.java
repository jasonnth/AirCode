package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.payments.PaymentOptionsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PaymentOptionsRequest extends BaseRequestV2<PaymentOptionsResponse> {
    private static final String PARAM_BILL_PRODUCT_TYPE = "bill_product_type";
    private static final String PARAM_INCLUDE_BUSINESS_TRAVEL = "include_business_travel";
    private static final String PARAM_PAYMENT_COUNTRY = "country";
    private static final String PARAM_USER_ID = "user_id";
    private final String billProductType;
    private final String countryCode;
    private final boolean includeBusinessTravel;

    public static PaymentOptionsRequest make(String billProductType2, String countryCode2, boolean includeBusinessTravel2) {
        return new PaymentOptionsRequest(billProductType2, countryCode2, includeBusinessTravel2);
    }

    private PaymentOptionsRequest(String billProductType2, String countryCode2, boolean includeBusinessTravel2) {
        this.billProductType = billProductType2;
        this.countryCode = countryCode2;
        this.includeBusinessTravel = includeBusinessTravel2;
    }

    public Type successResponseType() {
        return PaymentOptionsResponse.class;
    }

    public String getPath() {
        return "payment_options";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", AirbnbAccountManager.currentUserId()).mo7895kv("country", this.countryCode).mo7895kv(PARAM_BILL_PRODUCT_TYPE, this.billProductType).mo7897kv(PARAM_INCLUDE_BUSINESS_TRAVEL, this.includeBusinessTravel);
    }
}
