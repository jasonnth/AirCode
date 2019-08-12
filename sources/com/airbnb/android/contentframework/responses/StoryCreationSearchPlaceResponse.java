package com.airbnb.android.contentframework.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class StoryCreationSearchPlaceResponse extends BaseResponse {
    @JsonProperty("google_places")
    public List<StoryCreationPlaceTag> placeTagList;
}
