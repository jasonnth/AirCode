package com.airbnb.android.contentframework.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.contentframework.responses.StoryCreationSearchPlaceResponse;
import com.airbnb.android.superhero.SuperHeroMessageModel;
import com.airbnb.android.utils.NumberUtils;
import com.google.android.gms.maps.model.LatLng;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class StoryCreationSearchPlaceRequest extends BaseRequestV2<StoryCreationSearchPlaceResponse> {
    private final LatLng location;
    private final String query;

    public static StoryCreationSearchPlaceRequest forQuery(String query2, LatLng location2) {
        return new StoryCreationSearchPlaceRequest(query2, location2);
    }

    public static StoryCreationSearchPlaceRequest forLatLng(LatLng location2) {
        return new StoryCreationSearchPlaceRequest(null, location2);
    }

    private StoryCreationSearchPlaceRequest(String query2, LatLng location2) {
        this.query = query2;
        this.location = location2;
    }

    public Type successResponseType() {
        return StoryCreationSearchPlaceResponse.class;
    }

    public String getPath() {
        return "google_places";
    }

    public Collection<Query> getQueryParams() {
        String str;
        QueryStrap params = QueryStrap.make().mix(super.getQueryParams()).mo7895kv("types", "establishment");
        if (!TextUtils.isEmpty(this.query)) {
            QueryStrap kv = params.mo7895kv("input", this.query);
            String str2 = "location";
            if (this.location == null) {
                str = "0,0";
            } else {
                str = NumberUtils.formatLatLong(this.location.latitude) + "," + NumberUtils.formatLatLong(this.location.longitude);
            }
            kv.mo7895kv(str2, str);
        } else if (this.location != null) {
            params.mo7895kv("lat", NumberUtils.formatLatLong(this.location.latitude)).mo7895kv("lng", NumberUtils.formatLatLong(this.location.longitude)).mo7893kv(SuperHeroMessageModel.RADIUS, 500);
        }
        return params;
    }
}
