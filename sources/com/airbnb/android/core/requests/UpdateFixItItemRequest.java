package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.FixItItemResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateFixItItemRequest extends BaseRequestV2<FixItItemResponse> {
    private final Object body;

    /* renamed from: id */
    private final long f8494id;

    private static final class CommentUpdateBody {
        @JsonProperty("host_comment")
        String hostComment;

        CommentUpdateBody(String hostComment2) {
            this.hostComment = hostComment2;
        }
    }

    private static final class StatusUpdateBody {
        @JsonProperty("status")
        int status;

        StatusUpdateBody(int status2) {
            this.status = status2;
        }
    }

    public static UpdateFixItItemRequest forHostComment(long id, String hostComment) {
        return new UpdateFixItItemRequest(id, new CommentUpdateBody(hostComment));
    }

    public static UpdateFixItItemRequest forStatus(long id, int status) {
        return new UpdateFixItItemRequest(id, new StatusUpdateBody(status));
    }

    private UpdateFixItItemRequest(long id, Object body2) {
        this.f8494id = id;
        this.body = body2;
    }

    public Type successResponseType() {
        return FixItItemResponse.class;
    }

    public String getPath() {
        return "fixit_report_items/" + this.f8494id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.body;
    }
}
