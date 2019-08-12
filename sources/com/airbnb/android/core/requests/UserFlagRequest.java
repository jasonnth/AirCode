package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.responses.UserFlagResponse;
import java.lang.reflect.Type;

public abstract class UserFlagRequest extends BaseRequestV2<UserFlagResponse> {
    protected final FlagContent content;
    protected final long flaggableId;
    protected final long flaggingUserId;

    public UserFlagRequest(FlagContent content2, long flaggableId2, long flaggingUserId2) {
        this.content = content2;
        this.flaggableId = flaggableId2;
        this.flaggingUserId = flaggingUserId2;
    }

    public Type successResponseType() {
        return UserFlagResponse.class;
    }

    public String getPath() {
        return String.format("user_flags/%s/%d/%d", new Object[]{this.content.getServerIdKey(), Long.valueOf(this.flaggableId), Long.valueOf(this.flaggingUserId)});
    }
}
