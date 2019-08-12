package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.responses.StoryCreationImageUploadResponse;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;

public class StoryCreationImageUploadRequest extends MultipartRequestV2<StoryCreationImageUploadResponse> {
    private final List<Part> parts = new ArrayList(2);

    static class Body {
        @JsonProperty("story_id")
        long storyId;

        Body(long storyId2) {
            this.storyId = storyId2;
        }
    }

    public StoryCreationImageUploadRequest(long storyId, String photoPath) {
        this.parts.add(new Part("json_root_body", new Body(storyId)));
        File file = new File(photoPath);
        this.parts.add(new Part(ContentFrameworkAnalytics.IMAGE, RequestBody.create(IOUtils.getContentType(file.getName()), file), "binary", file.getPath()));
    }

    public Type successResponseType() {
        return StoryCreationImageUploadResponse.class;
    }

    public String getPath() {
        return "story_photos";
    }

    public List<Part> getParts() {
        return this.parts;
    }
}
