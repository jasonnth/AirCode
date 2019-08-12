package com.airbnb.android.explore.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.requests.find.SavedSearchResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.mparticle.commerce.Product;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExploreUpdateSavedSearchRequest extends BaseRequestV2<SavedSearchResponse> {
    private final TopLevelSearchParams data;
    private final String savedSearchId;
    private final JSONObject searchParams;
    private final String source;
    private final String userIdHash;

    public ExploreUpdateSavedSearchRequest(long userId, TopLevelSearchParams data2, SearchFilters searchFilters, String savedSearchId2, String source2, String placeId) {
        this.userIdHash = MiscUtils.sha1Hash(String.valueOf(userId));
        this.data = data2;
        try {
            this.searchParams = buildSearchParams(searchFilters, placeId);
            this.savedSearchId = savedSearchId2;
            this.source = source2;
        } catch (JSONException e) {
            throw new IllegalStateException("Unable to create saved search params json.");
        }
    }

    public String getPath() {
        return "saved_searches/" + this.userIdHash + "/" + this.savedSearchId;
    }

    public String getBody() {
        JSONObject params = new JSONObject();
        try {
            params.put("saved_search_id", this.savedSearchId);
            params.put(SearchActivityIntents.EXTRA_SEARCH_PARAMS, this.searchParams);
            params.put("modified_at", System.currentTimeMillis());
            params.put("source", this.source);
            return params.toString();
        } catch (JSONException e) {
            throw new IllegalStateException("Unable to create saved search params json.");
        }
    }

    private JSONObject buildSearchParams(SearchFilters searchFilters, String placeId) throws JSONException {
        JSONObject searchParams2 = new JSONObject();
        if (this.data.hasSearchTerm()) {
            searchParams2.put("location", this.data.searchTerm());
        }
        AirDate checkInDate = this.data.checkInDate();
        if (checkInDate != null) {
            searchParams2.put(UpdateReviewRequest.KEY_CHECKIN, checkInDate.getIsoDateString());
        }
        AirDate checkOutDate = this.data.checkOutDate();
        if (checkOutDate != null) {
            searchParams2.put(Product.CHECKOUT, checkOutDate.getIsoDateString());
        }
        if (placeId != null) {
            searchParams2.put("place_id", placeId);
        }
        GuestDetails guestDetails = this.data.guestDetails();
        if (guestDetails != null) {
            addIfNotDefault(guestDetails.totalGuestCount(), 0, searchParams2, FindTweenAnalytics.GUESTS);
            addIfNotDefault(guestDetails.adultsCount(), 0, searchParams2, FindTweenAnalytics.ADULTS);
            addIfNotDefault(guestDetails.childrenCount(), 0, searchParams2, FindTweenAnalytics.CHILDREN);
            addIfNotDefault(guestDetails.infantsCount(), 0, searchParams2, FindTweenAnalytics.INFANTS);
            searchParams2.put(FindTweenAnalytics.PETS, guestDetails.isBringingPets());
        }
        addIfNotDefault(searchFilters.getMinPrice(), 0, searchParams2, "price_min");
        addIfNotDefault(searchFilters.getMaxPrice(), 0, searchParams2, "price_max");
        searchParams2.put(AirbnbConstants.PREFS_CURRENCY, searchFilters.getCurrencyType());
        if (searchFilters.hasSetInstantBookOnly()) {
            searchParams2.put("ib", searchFilters.isInstantBookOnly());
        }
        addIfNotDefault(searchFilters.getNumBeds(), 0, searchParams2, "min_beds");
        addIfNotDefault(searchFilters.getNumBedrooms(), 0, searchParams2, "min_bedrooms");
        addIfNotDefault(searchFilters.getNumBathrooms(), 0, searchParams2, "min_bathrooms");
        List<String> keywords = searchFilters.getKeywords();
        if (!ListUtils.isEmpty((Collection<?>) keywords)) {
            searchParams2.put("keywords", new JSONArray(keywords));
        }
        if (!ListUtils.isEmpty((Collection<?>) searchFilters.getLanguages())) {
            searchParams2.put("languages", new JSONArray(searchFilters.getLanguages()));
        }
        Set<C6120RoomType> roomTypes = searchFilters.getRoomTypes();
        if (!ListUtils.isEmpty((Collection<?>) roomTypes)) {
            searchParams2.put("room_types", new JSONArray(FluentIterable.from((Iterable<E>) roomTypes).transform(ExploreUpdateSavedSearchRequest$$Lambda$1.lambdaFactory$()).toList()));
        }
        List<Amenity> amenities = searchFilters.getAmenities();
        if (!ListUtils.isEmpty((Collection<?>) amenities)) {
            searchParams2.put("amenities", new JSONArray(FluentIterable.from((Iterable<E>) amenities).transform(ExploreUpdateSavedSearchRequest$$Lambda$2.lambdaFactory$()).toList()));
        }
        List<PropertyType> propertyTypes = searchFilters.getPropertyTypes();
        if (!ListUtils.isEmpty((Collection<?>) propertyTypes)) {
            searchParams2.put(ListingRequestConstants.JSON_PROPERTY_TYPE_KEY, new JSONArray(FluentIterable.from((Iterable<E>) propertyTypes).transform(ExploreUpdateSavedSearchRequest$$Lambda$3.lambdaFactory$()).toList()));
        }
        return searchParams2;
    }

    private void addIfNotDefault(int value, int defaultValue, JSONObject searchParams2, String key) throws JSONException {
        if (value != defaultValue) {
            searchParams2.put(key, value);
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return SavedSearchResponse.class;
    }
}
