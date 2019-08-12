package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.DeleteCannedMessageResponse;
import java.lang.reflect.Type;

public class DeleteCannedMessageRequest extends BaseRequestV2<DeleteCannedMessageResponse> {
    private final long messageId;

    public DeleteCannedMessageRequest(long messageId2) {
        this.messageId = messageId2;
    }

    public String getPath() {
        return "template_messages/" + this.messageId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return DeleteCannedMessageResponse.class;
    }
}
