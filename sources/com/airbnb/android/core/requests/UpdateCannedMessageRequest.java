package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UpdateCannedMessageResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateCannedMessageRequest extends BaseRequestV2<UpdateCannedMessageResponse> {

    /* renamed from: id */
    private final long f8493id;
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("id")

        /* renamed from: id */
        final long f1085id;
        @JsonProperty("message")
        final String message;
        @JsonProperty("thread_id")
        final long threadId;
        @JsonProperty("title")
        final String title;

        RequestBody(long id, String title2, String message2, long threadId2) {
            this.f1085id = id;
            this.title = title2;
            this.message = message2;
            this.threadId = threadId2;
        }
    }

    public UpdateCannedMessageRequest(long id, String title, String message, long threadId) {
        this.requestBody = new RequestBody(id, title, message, threadId);
        this.f8493id = id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return UpdateCannedMessageResponse.class;
    }

    public String getPath() {
        return "template_messages/" + this.f8493id;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
