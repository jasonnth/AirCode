package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.AddCannedMessageResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class AddCannedMessageRequest extends BaseRequestV2<AddCannedMessageResponse> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("message")
        final String message;
        @JsonProperty("thread_id")
        final long threadId;
        @JsonProperty("title")
        final String title;

        RequestBody(String title2, String message2, long threadId2) {
            this.title = title2;
            this.message = message2;
            this.threadId = threadId2;
        }
    }

    public AddCannedMessageRequest(String title, String message, long threadId) {
        this.requestBody = new RequestBody(title, message, threadId);
    }

    public String getPath() {
        return "template_messages";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return AddCannedMessageResponse.class;
    }
}
