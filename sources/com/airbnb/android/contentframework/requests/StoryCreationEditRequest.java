package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.contentframework.responses.StoryCreationPublishResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.List;

public class StoryCreationEditRequest extends BaseRequestV2<StoryCreationPublishResponse> {
    private static final String SOURCE = "update_model_from_mobile_creation_moment_v1";
    private final long articleId;
    private final Body body;

    static class Body {
        @JsonProperty("google_place_id")
        final String googlePlaceId;
        @JsonProperty("image_names")
        final List<String> imageNames;
        @JsonProperty("listing_id")
        final long listingId;
        @JsonProperty("source")
        final String source;
        @JsonProperty("text")
        final String text;
        @JsonProperty("title")
        final String title;

        public Body(String source2, String title2, String text2, List<String> imageNames2, long listingId2, String googlePlaceId2) {
            this.source = source2;
            this.title = title2;
            this.text = text2;
            this.imageNames = imageNames2;
            this.listingId = listingId2;
            this.googlePlaceId = googlePlaceId2;
        }
    }

    public StoryCreationEditRequest(long articleId2, long listingId, String title, String bodyText, List<String> imageNames, String googlePlaceId) {
        this.articleId = articleId2;
        this.body = new Body(SOURCE, title, bodyText, imageNames, listingId, googlePlaceId);
    }

    public Type successResponseType() {
        return StoryCreationPublishResponse.class;
    }

    public String getPath() {
        return "content_framework_articles/" + this.articleId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.body;
    }
}
