package com.airbnb.android.explore.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ExplorePlaylistResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class ExplorePlaylistRequest extends BaseRequestV2<ExplorePlaylistResponse> {
    private final long collectionId;

    public static ExplorePlaylistRequest newInstance(long id) {
        return new ExplorePlaylistRequest(id);
    }

    private ExplorePlaylistRequest(long collectionId2) {
        this.collectionId = collectionId2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11638kv("campaign_id", this.collectionId));
    }

    public Type successResponseType() {
        return ExplorePlaylistResponse.class;
    }

    public String getPath() {
        return "mt_collections";
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
