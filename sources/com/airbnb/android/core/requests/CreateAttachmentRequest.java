package com.airbnb.android.core.requests;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.android.core.responses.AttachmentResponse;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;

public class CreateAttachmentRequest extends MultipartRequestV2<AttachmentResponse> {
    protected static final String TYPE_HELP_THREADS = "help_threads";
    private final List<Part> parts = new ArrayList(2);

    static class Body {
        @JsonProperty("attachable_id")
        long attachableId;
        @JsonProperty("attachable_type")
        String attachableType;

        Body(long attachableId2, String attachableType2) {
            this.attachableId = attachableId2;
            this.attachableType = attachableType2;
        }
    }

    protected CreateAttachmentRequest(long attachableId, File file, String attachableType) {
        this.parts.add(new Part("json_root_body", new Body(attachableId, attachableType)));
        this.parts.add(new Part("data", RequestBody.create(IOUtils.getContentType(file.getName()), file), "binary", file.getName()));
    }

    public Type successResponseType() {
        return AttachmentResponse.class;
    }

    public String getPath() {
        return "attachments";
    }

    public List<Part> getParts() {
        return this.parts;
    }
}
