package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.responses.AttachmentResponse;
import java.lang.reflect.Type;

public class DeleteAttachmentRequest extends BaseRequestV2<AttachmentResponse> {
    private final Attachment attachment;

    public DeleteAttachmentRequest(Attachment attachment2) {
        this.attachment = attachment2;
    }

    public Type successResponseType() {
        return AttachmentResponse.class;
    }

    public String getPath() {
        return "attachments/" + this.attachment.getId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
