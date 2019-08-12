package com.airbnb.android.core.requests;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.responses.CreateMessageResponse;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;
import retrofit2.Query;

public class CreateMessageWithImageAttachmentRequest extends MultipartRequestV2<CreateMessageResponse> {
    private final File file;
    private final List<Part> parts = new ArrayList(2);
    protected long threadId;

    static class Body {
        @JsonProperty("content_type")
        String contentType;
        @JsonProperty("filename")
        String fileName;
        @JsonProperty("thread_id")
        long threadId;

        Body(long threadId2, String contentType2, String fileName2) {
            this.threadId = threadId2;
            this.contentType = contentType2;
            this.fileName = fileName2;
        }
    }

    public static CreateMessageWithImageAttachmentRequest create(long threadId2, Post post) {
        return new CreateMessageWithImageAttachmentRequest(threadId2, post);
    }

    public CreateMessageWithImageAttachmentRequest(long threadId2, Post post) {
        String imagePath = post.getImageAttachmentUrl();
        this.threadId = threadId2;
        this.file = new File(imagePath);
        this.parts.add(new Part("json_root_body", new Body(threadId2, ImageUtils.getContentTypeFromFilePath(imagePath), this.file.getName())));
        this.parts.add(new Part(ContentFrameworkAnalytics.IMAGE, RequestBody.create(IOUtils.getContentType(this.file.getName()), this.file), "binary", this.file.getPath()));
    }

    public String getPath() {
        return "messages";
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public Type successResponseType() {
        return CreateMessageResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_threads");
    }
}
