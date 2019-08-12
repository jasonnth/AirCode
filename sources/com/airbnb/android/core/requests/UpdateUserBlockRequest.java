package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserBlockResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateUserBlockRequest extends BaseRequestV2<UserBlockResponse> {

    /* renamed from: id */
    private final long f8496id;
    private final UpdateUserBlockBody requestBody;

    private static final class UpdateUserBlockBody {
        @JsonProperty
        final boolean active;

        UpdateUserBlockBody(boolean active2) {
            this.active = active2;
        }
    }

    private UpdateUserBlockRequest(long id, UpdateUserBlockBody requestBody2) {
        this.f8496id = id;
        this.requestBody = requestBody2;
    }

    public static UpdateUserBlockRequest create(long id, boolean active) {
        return new UpdateUserBlockRequest(id, new UpdateUserBlockBody(active));
    }

    public Type successResponseType() {
        return UserBlockResponse.class;
    }

    public String getPath() {
        return "user_blocks/" + this.f8496id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
