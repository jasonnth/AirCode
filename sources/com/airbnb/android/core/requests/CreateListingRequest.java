package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class CreateListingRequest extends BaseRequestV2<SimpleListingResponse> {
    private static final String SKIP_CREATE = "skip_create_requirements";
    private final Object requestBody;

    private static class RequestBodyForOldLYS {
        @JsonProperty("bathrooms")
        final float bathrooms;
        @JsonProperty("bedrooms")
        final int bedrooms;
        @JsonProperty("beds")
        final int beds;
        @JsonProperty("person_capacity")
        final int capacity;
        @JsonProperty("city")
        final String city;
        @JsonProperty("property_type_id")
        final int propertyType;
        @JsonProperty("room_type_category")
        final String roomType;
        @JsonProperty("skip_create_requirements")
        final boolean skipCreate = true;

        RequestBodyForOldLYS(Listing listing) {
            this.bathrooms = listing.getBathrooms();
            this.bedrooms = listing.getBedrooms();
            this.beds = listing.getBedCount();
            this.city = listing.getCity();
            this.capacity = listing.getPersonCapacity();
            this.propertyType = listing.getPropertyTypeId();
            this.roomType = listing.getRoomTypeKey();
        }
    }

    public static CreateListingRequest newInstance(Strap requestBody2, BaseRequestListener<SimpleListingResponse> createListingRequestListener) {
        return new CreateListingRequest(requestBody2.mo11640kv(SKIP_CREATE, true), createListingRequestListener);
    }

    public static CreateListingRequest forOldLys(Listing listing, BaseRequestListener<SimpleListingResponse> listener) {
        return new CreateListingRequest(new RequestBodyForOldLYS(listing), listener);
    }

    private CreateListingRequest(Object requestBody2, BaseRequestListener<SimpleListingResponse> listener) {
        withListener((Observer) listener);
        this.requestBody = requestBody2;
    }

    public String getPath() {
        return "listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "v1_legacy_long_manage_listing");
    }

    public Object getBody() {
        return this.requestBody;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return SimpleListingResponse.class;
    }
}
