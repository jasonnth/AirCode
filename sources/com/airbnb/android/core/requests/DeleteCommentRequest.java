package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.DeleteCommentResponse;
import java.lang.reflect.Type;

public class DeleteCommentRequest extends BaseRequestV2<DeleteCommentResponse> {
    private final long commentId;

    public static DeleteCommentRequest requestForCommentId(long commentId2) {
        return new DeleteCommentRequest(commentId2);
    }

    private DeleteCommentRequest(long commentId2) {
        this.commentId = commentId2;
    }

    public Type successResponseType() {
        return DeleteCommentResponse.class;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append("content_framework_comments/");
        sb.append(this.commentId);
        return sb.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
