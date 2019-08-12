package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import java.lang.reflect.Type;

public class DeleteSmartPromotionRequest extends BaseRequestV2<BaseResponse> {
    private final long smartPromoId;

    private DeleteSmartPromotionRequest(long smartPromoId2) {
        this.smartPromoId = smartPromoId2;
    }

    public static DeleteSmartPromotionRequest forInsight(long smartPromoId2) {
        return new DeleteSmartPromotionRequest(smartPromoId2);
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "smart_promotion_host_promotions/" + this.smartPromoId;
    }
}
