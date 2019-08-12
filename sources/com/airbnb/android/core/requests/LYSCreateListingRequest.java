package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class LYSCreateListingRequest extends BaseRequestV2<SimpleListingResponse> {
    private static final String SKIP_REQUIREMENTS = "skip_create_requirements";
    private final Object requestBody;

    private static class RequestBodyForLYSDLS {
        @JsonProperty("amenities_ids")
        final int[] amenityIds;
        @JsonProperty("bathroom_type")
        final String bathroomType;
        @JsonProperty("bathrooms")
        final float bathrooms;
        @JsonProperty("bed_type_category")
        final String bedType;
        @JsonProperty("bedrooms")
        final int bedrooms;
        @JsonProperty("beds")
        final int beds;
        @JsonProperty("person_capacity")
        final int capacity;
        @JsonProperty("city")
        final String city;
        @JsonProperty("country_code")
        final String countryCode;
        @JsonProperty("instant_booking_allowed_category")
        final String ibCategory;
        @JsonProperty("property_type_id")
        final int propertyType;
        @JsonProperty("room_type_category")
        final String roomType;
        @JsonProperty("skip_create_requirements")
        final boolean skipCreate = true;
        @JsonProperty("state")
        final String state;

        RequestBodyForLYSDLS(Listing listing) {
            this.propertyType = listing.getPropertyTypeId();
            this.roomType = listing.getRoomTypeKey();
            this.bathrooms = listing.getBathrooms();
            this.bedrooms = listing.getBedrooms();
            this.beds = listing.getBedCount();
            this.capacity = listing.getPersonCapacity();
            this.bedType = listing.getBedTypeCategory();
            this.amenityIds = listing.getAmenityIdsArray();
            this.bathroomType = listing.getBathroomType().serverKey;
            this.ibCategory = InstantBookingAllowedCategory.EveryOne.serverKey;
            this.city = listing.getCity();
            this.countryCode = listing.getCountryCode();
            this.state = listing.getState();
        }
    }

    public static LYSCreateListingRequest forLYSDLS(Listing listing) {
        return new LYSCreateListingRequest(new RequestBodyForLYSDLS(listing));
    }

    private LYSCreateListingRequest(Object requestBody2) {
        this.requestBody = requestBody2;
    }

    public String getPath() {
        return "listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_lys_mobile");
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
