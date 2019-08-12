package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.responses.SetPrimaryHostResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import retrofit2.Query;

public class SetPrimaryHostRequest extends BaseRequestV2<SetPrimaryHostResponse> {
    private final String listingManagerId;
    private MuteType muteType = null;

    public SetPrimaryHostRequest(String listingManagerId2) {
        this.listingManagerId = listingManagerId2;
    }

    public SetPrimaryHostRequest(String listingManagerId2, MuteType muteType2) {
        this.listingManagerId = listingManagerId2;
        this.muteType = muteType2;
    }

    public String getPath() {
        return "listing_managers/" + this.listingManagerId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_manage_listing");
    }

    public Object getBody() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("is_primary_host", Boolean.valueOf(true));
        if (this.muteType != null) {
            body.put("mute_type", Integer.valueOf(this.muteType.ordinal()));
        }
        return body;
    }

    public Type successResponseType() {
        return SetPrimaryHostResponse.class;
    }
}
