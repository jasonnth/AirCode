package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.CohostingStandardResponse;
import java.lang.reflect.Type;

public class CohostingStandardRequest extends BaseRequestV2<CohostingStandardResponse> {
    private final long userId;

    private CohostingStandardRequest(long userId2) {
        this.userId = userId2;
    }

    public static CohostingStandardRequest forUser(long userId2) {
        return new CohostingStandardRequest(userId2);
    }

    public String getPath() {
        return "cohosting_stats/" + this.userId;
    }

    public Type successResponseType() {
        return CohostingStandardResponse.class;
    }
}
