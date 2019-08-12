package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserBlockResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateUserBlockRequest extends BaseRequestV2<UserBlockResponse> {
    private final CreateUserBlockBody requestBody;

    private static final class CreateUserBlockBody {
        @JsonProperty
        final long blockedUserId;
        @JsonProperty
        final long threadId;

        CreateUserBlockBody(long threadId2, long blockedUserId2) {
            this.threadId = threadId2;
            this.blockedUserId = blockedUserId2;
        }
    }

    private CreateUserBlockRequest(CreateUserBlockBody requestBody2) {
        this.requestBody = requestBody2;
    }

    public static CreateUserBlockRequest create(long threadId, long blockedUserId) {
        return new CreateUserBlockRequest(new CreateUserBlockBody(threadId, blockedUserId));
    }

    public Type successResponseType() {
        return UserBlockResponse.class;
    }

    public String getPath() {
        return "user_blocks";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
