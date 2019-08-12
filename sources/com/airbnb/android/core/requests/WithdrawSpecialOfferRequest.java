package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class WithdrawSpecialOfferRequest extends BaseRequestV2<BaseResponse> {
    private final RequestBody requestBody = new RequestBody("withdraw");
    private final long specialOfferId;

    private static class RequestBody {
        @JsonProperty("update_type")
        final String updateType;

        RequestBody(String updateType2) {
            this.updateType = updateType2;
        }
    }

    public static WithdrawSpecialOfferRequest create(long specialOfferId2) {
        return new WithdrawSpecialOfferRequest(specialOfferId2);
    }

    private WithdrawSpecialOfferRequest(long specialOfferId2) {
        this.specialOfferId = specialOfferId2;
    }

    public String getPath() {
        return "special_offers/" + this.specialOfferId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
