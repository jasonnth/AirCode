package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class UpdateListingRequest extends BaseRequestV2<SimpleListingResponse> {
    private static final String LYS_DLS_FORMAT = "for_lys_mobile";
    private static final String PARAM_LICENSE = "license";
    private final String format;
    private final long listingId;
    private final Object requestBody;

    private static final class AmenitiesIdsLYSRequestBody {
        @JsonProperty("amenities_ids")
        final int[] amenitiesIds;
        @JsonProperty("list_your_space_last_finished_step_id")
        final String lastStepId;

        AmenitiesIdsLYSRequestBody(int[] amenitiesIds2, String lastStepId2) {
            this.amenitiesIds = amenitiesIds2;
            this.lastStepId = lastStepId2;
        }
    }

    private static final class AmenitiesIdsRequestBody {
        @JsonProperty("amenities_ids")
        final int[] amenitiesIds;

        AmenitiesIdsRequestBody(int[] amenitiesIds2) {
            this.amenitiesIds = amenitiesIds2;
        }
    }

    private static final class PricingModeLYSRequestBody {
        @JsonProperty("list_your_space_pricing_mode")
        final int pricingMode;

        PricingModeLYSRequestBody(int pricingMode2) {
            this.pricingMode = pricingMode2;
        }
    }

    static class SnoozeRequestBody {
        @JsonProperty("snooze_mode_options")
        SnoozeDates snoozeDates = new SnoozeDates();

        static class SnoozeDates {
            @JsonProperty("snooze_end_date")
            AirDate endDate;
            @JsonProperty("snooze_start_date")
            AirDate startDate;

            SnoozeDates() {
            }
        }

        public SnoozeRequestBody(AirDate startDate, AirDate endDate) {
            this.snoozeDates.startDate = startDate;
            this.snoozeDates.endDate = endDate;
        }
    }

    public static UpdateListingRequest forFields(long listingId2, Strap fieldsToUpdate) {
        return new UpdateListingRequest(listingId2, fieldsToUpdate);
    }

    @Deprecated
    public static UpdateListingRequest forLegacySnooze(long listingId2, AirDate start, AirDate end) {
        JSONObject snoozeOptionsJson = new JSONObject();
        try {
            JSONObject datesJson = new JSONObject();
            datesJson.put("snooze_start_date", start.getIsoDateString());
            datesJson.put("snooze_end_date", end.getIsoDateString());
            snoozeOptionsJson.put(ListingRequestConstants.JSON_SNOOZE_OPTIONS, datesJson);
            return new UpdateListingRequest(listingId2, snoozeOptionsJson.toString());
        } catch (JSONException e) {
            throw new IllegalStateException("Error building snooze date options json");
        }
    }

    public static UpdateListingRequest forSnoozeMode(long listingId2, AirDate startDate, AirDate endDate) {
        return new UpdateListingRequest(listingId2, new SnoozeRequestBody(startDate, endDate));
    }

    public static UpdateListingRequest forField(long listingId2, String field, Object value) {
        return new UpdateListingRequest(listingId2, Strap.make().mo11639kv(field, value.toString()));
    }

    public static UpdateListingRequest forFieldsLYS(long listingId2, Strap fieldsToUpdate) {
        return new UpdateListingRequest(listingId2, fieldsToUpdate, LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forFieldLYS(long listingId2, String field, Object value) {
        return new UpdateListingRequest(listingId2, Strap.make().mo11639kv(field, value.toString()), LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forFieldLYSWithStepId(long listingId2, String field, Object value, String stepId) {
        return new UpdateListingRequest(listingId2, Strap.make().mo11639kv(field, value.toString()).mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, stepId), LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forStepIdLYS(long listingId2, String stepId) {
        return new UpdateListingRequest(listingId2, Strap.make().mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, stepId), LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forLicenseField(long listingId2, String license) {
        return new UpdateListingRequest(listingId2, Strap.make().mo11639kv("license", license));
    }

    public static UpdateListingRequest forUserDefinedLatLng(long listingId2, LatLng latLng) {
        return forFields(listingId2, createStrapForLatLng(latLng));
    }

    public static UpdateListingRequest forUserDefinedLatLngLYS(long listingId2, LatLng latLng, String stepId) {
        return forFieldsLYS(listingId2, createStrapForLatLng(latLng).mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, stepId));
    }

    private static Strap createStrapForLatLng(LatLng latLng) {
        return Strap.make().mo11635kv("lat", latLng.latitude).mo11635kv("lng", latLng.longitude).mo11640kv(ListingRequestConstants.JSON_USER_DEFINED_LOCATION, true);
    }

    public static UpdateListingRequest forAmenitiesIds(long listingId2, int[] amenitiesIds) {
        return new UpdateListingRequest(listingId2, new AmenitiesIdsRequestBody(amenitiesIds));
    }

    public static UpdateListingRequest forAmenitiesIdsLYS(long listingId2, int[] amenitiesIds, String lastStepId) {
        return new UpdateListingRequest(listingId2, new AmenitiesIdsLYSRequestBody(amenitiesIds, lastStepId), LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forPriceModeLYS(long listingId2, ListYourSpacePricingMode pricingMode) {
        return new UpdateListingRequest(listingId2, new PricingModeLYSRequestBody(pricingMode.getServerKey()), LYS_DLS_FORMAT);
    }

    public static UpdateListingRequest forPhotoSortOrderDeprecated(long listingId2, List<Photo> photos) {
        return forPhotoSortOrder(listingId2, FluentIterable.from((Iterable<E>) FluentIterable.from((Iterable<E>) photos).toSortedList(Photo.ORDER_COMPARATOR)).transform(UpdateListingRequest$$Lambda$1.lambdaFactory$()).toList());
    }

    public static UpdateListingRequest forPhotoSortOrder(long listingId2, List<Long> photoIds) {
        try {
            JSONArray photosArray = new JSONArray();
            for (int i = 0; i < photoIds.size(); i++) {
                long photoId = ((Long) photoIds.get(i)).longValue();
                JSONObject photo = new JSONObject();
                photo.put("id", photoId);
                photo.put(UpdateListingPhotoRequest.KEY_SORT_ORDER, i + 1);
                photosArray.put(photo);
            }
            return new UpdateListingRequest(listingId2, new JSONObject().put(PlaceFields.PHOTOS_PROFILE, photosArray).toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static UpdateListingRequest forPhotoSortOrderLYS(long listingId2, List<Long> photoIds, String stepId) {
        try {
            JSONArray photosArray = new JSONArray();
            for (int i = 0; i < photoIds.size(); i++) {
                long photoId = ((Long) photoIds.get(i)).longValue();
                JSONObject photo = new JSONObject();
                photo.put("id", photoId);
                photo.put(UpdateListingPhotoRequest.KEY_SORT_ORDER, i + 1);
                photosArray.put(photo);
            }
            return new UpdateListingRequest(listingId2, new JSONObject().put(PlaceFields.PHOTOS_PROFILE, photosArray).put(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, stepId).toString(), LYS_DLS_FORMAT);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static UpdateListingRequest forMinMaxNights(long listingId2, Integer minNights, Integer maxNights) {
        Strap strap = Strap.make();
        if (minNights != null) {
            strap.mo11639kv(ListingRequestConstants.JSON_MIN_NIGHTS_KEY, String.valueOf(Math.max(1, minNights.intValue())));
        }
        if (maxNights != null) {
            strap.mo11639kv(ListingRequestConstants.JSON_MAX_NIGHTS_KEY, String.valueOf(maxNights.intValue() > 0 ? Integer.valueOf(Math.max(minNights.intValue(), maxNights.intValue())) : null));
        }
        return forFields(listingId2, strap);
    }

    private UpdateListingRequest(long listingId2, Object requestBody2) {
        this.listingId = listingId2;
        this.requestBody = requestBody2;
        this.format = "v1_legacy_long_manage_listing";
    }

    private UpdateListingRequest(long listingId2, Object requestBody2, String format2) {
        this.listingId = listingId2;
        this.requestBody = requestBody2;
        this.format = format2;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
    }

    public String getPath() {
        return "listings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return SimpleListingResponse.class;
    }
}
