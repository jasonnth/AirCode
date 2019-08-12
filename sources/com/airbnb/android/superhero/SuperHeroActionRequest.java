package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class SuperHeroActionRequest extends BaseRequestV2<SuperHeroActionResponse> {

    /* renamed from: id */
    private final long f2442id;

    public static SuperHeroActionRequest forMessageId(long id) {
        return new SuperHeroActionRequest(id);
    }

    private SuperHeroActionRequest(long id) {
        this.f2442id = id;
    }

    public Type successResponseType() {
        return SuperHeroActionResponse.class;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getPath() {
        return "hero_actions/" + this.f2442id;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_post_update"));
    }
}
