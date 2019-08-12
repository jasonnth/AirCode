package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.responses.GuestControlsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GuestControlsRequest extends BaseRequestV2<GuestControlsResponse> {
    private final Object body;
    private final long listingId;
    private final RequestMethod method;

    private static class RequestBody {
        @JsonProperty("allows_children_as_host")
        Boolean allowsChildren;
        @JsonProperty("allows_events_as_host")
        Boolean allowsEvents;
        @JsonProperty("allows_infants_as_host")
        Boolean allowsInfants;
        @JsonProperty("allows_pets_as_host")
        Boolean allowsPets;
        @JsonProperty("allows_smoking_as_host")
        Boolean allowsSmoking;

        private RequestBody(GuestControls controls) {
            this.allowsChildren = controls.isAllowsChildrenAsHost();
            this.allowsInfants = controls.isAllowsInfantsAsHost();
            this.allowsPets = controls.isAllowsPetsAsHost();
            this.allowsSmoking = controls.isAllowsSmokingAsHost();
            this.allowsEvents = controls.isAllowsEventsAsHost();
        }
    }

    private GuestControlsRequest(long listingId2, Object body2, RequestMethod method2) {
        this.listingId = listingId2;
        this.body = body2;
        this.method = method2;
    }

    public static GuestControlsRequest forListingId(long listingId2) {
        return new GuestControlsRequest(listingId2, null, RequestMethod.GET);
    }

    public static GuestControlsRequest updateGuestControls(long listingId2, GuestControls guestControls) {
        return new GuestControlsRequest(listingId2, new RequestBody(guestControls), RequestMethod.PUT);
    }

    public String getPath() {
        return "guest_controls/" + this.listingId;
    }

    public Object getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_host");
    }

    public Type successResponseType() {
        return GuestControlsResponse.class;
    }
}
