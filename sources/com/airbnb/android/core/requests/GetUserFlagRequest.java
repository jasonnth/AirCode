package com.airbnb.android.core.requests;

import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.FlagContent;

public class GetUserFlagRequest extends UserFlagRequest {
    public GetUserFlagRequest(FlagContent content, long flaggableId, long flaggingUserId) {
        super(content, flaggableId, flaggingUserId);
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }
}
