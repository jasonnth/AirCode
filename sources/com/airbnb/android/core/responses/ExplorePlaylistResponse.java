package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ExplorePlaylist;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExplorePlaylistResponse extends BaseResponse {
    @JsonProperty("mt_collections")
    public List<ExplorePlaylist> playlists;
}
