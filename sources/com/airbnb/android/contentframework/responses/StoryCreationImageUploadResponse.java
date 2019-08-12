package com.airbnb.android.contentframework.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.StoryPhotoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StoryCreationImageUploadResponse extends BaseResponse {
    @JsonProperty("story_photo")
    public StoryPhotoResponse photoResponse;
}
