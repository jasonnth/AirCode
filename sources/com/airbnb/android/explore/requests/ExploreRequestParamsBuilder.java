package com.airbnb.android.explore.requests;

import android.location.Location;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.MapBounds;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.core.p008mt.models.ExplorePlacesTopCategory;
import com.airbnb.android.core.p008mt.models.GuidebookItemType;
import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExploreRequestParamsBuilder {
    private static final int DEFAULT_NUM_PICTURES = 1;
    private final QueryStrap props = QueryStrap.make();

    public static ExploreRequestParamsBuilder from(TopLevelSearchParams topLevelSearchParams, ExperienceFilters experienceSearchFilters, SearchFilters searchFilters, PlaceFilters placesSearchFilters, String federatedSearchSessionId, String searchSessionId) {
        return new ExploreRequestParamsBuilder(topLevelSearchParams, experienceSearchFilters, searchFilters, placesSearchFilters, federatedSearchSessionId, searchSessionId);
    }

    private ExploreRequestParamsBuilder(TopLevelSearchParams topLevelSearchParams, ExperienceFilters experienceSearchFilters, SearchFilters searchFilters, PlaceFilters placesSearchFilters, String federatedSearchSessionId, String searchSessionId) {
        buildForExperiences(experienceSearchFilters);
        buildForHomes(topLevelSearchParams, searchFilters);
        buildForPlaces(placesSearchFilters);
        SearchUtil.addSupportedUrgencyTypesToRequestStrap(this.props);
        this.props.mo7895kv("federated_search_session_id", federatedSearchSessionId);
        this.props.mo7895kv("mobile_session_id", searchSessionId);
    }

    private void buildForExperiences(ExperienceFilters experienceSearchFilters) {
        int index = 0;
        for (C6213ProductType productType : experienceSearchFilters.getExperienceProductTypes()) {
            int index2 = index + 1;
            this.props.mo7893kv("experience_product_types[" + index + "]", productType.f8481id);
            index = index2;
        }
        int index3 = 0;
        for (PrimaryCategory category : experienceSearchFilters.getExperienceCategories()) {
            int index4 = index3 + 1;
            this.props.mo7893kv("experience_categories[" + index3 + "]", category.f1083id);
            index3 = index4;
        }
        if (experienceSearchFilters.isExperienceSocialGoodOnly().booleanValue()) {
            this.props.mo7893kv("experience_social_good_only", 1);
        }
    }

    private void buildForHomes(TopLevelSearchParams topLevelSearchParams, SearchFilters searchFilters) {
        if (SearchPricingUtil.isTotalPricingEnabled() && topLevelSearchParams.hasDates()) {
            this.props.mo7897kv("_filter_by_total_price", true);
        }
        this.props.mo7897kv("from_explore_search", true);
        if (searchFilters.hasSetInstantBookOnly()) {
            this.props.mo7897kv("ib", searchFilters.isInstantBookOnly());
        }
        if (searchFilters.isBusinessTravelReady()) {
            this.props.mo7897kv("btsr", searchFilters.isBusinessTravelReady());
        }
        Location location = LocationUtil.getLastKnownLocation(CoreApplication.appContext());
        if (location != null) {
            this.props.mo7891kv("user_lat", location.getLatitude());
            this.props.mo7891kv("user_lng", location.getLongitude());
        }
        int idx = 0;
        for (C6120RoomType roomType : searchFilters.getRoomTypes()) {
            int idx2 = idx + 1;
            this.props.mo7895kv("room_types[" + idx + "]", roomType.key);
            idx = idx2;
        }
        addIfNotDefault(searchFilters.getMinPrice(), 0, "price_min");
        addIfNotDefault(searchFilters.getMaxPrice(), 0, "price_max");
        addIfNotDefault(searchFilters.getNumBeds(), 0, "min_beds");
        addIfNotDefault(searchFilters.getNumBedrooms(), 0, "min_bedrooms");
        addIfNotDefault(searchFilters.getNumBathrooms(), 0, "min_bathrooms");
        List<Amenity> amenities = searchFilters.getAmenities();
        for (int i = 0; i < amenities.size(); i++) {
            this.props.mo7893kv("amenities[" + i + "]", ((Amenity) amenities.get(i)).f8471id);
        }
        List<Amenity> amenitiesToFilterOut = searchFilters.getAmenitiesToFilterOut();
        for (int i2 = 0; i2 < amenitiesToFilterOut.size(); i2++) {
            this.props.mo7893kv("amenities_to_filter_out[" + i2 + "]", ((Amenity) amenitiesToFilterOut.get(i2)).f8471id);
        }
        this.props.mo7897kv("ib_add_photo_flow", true);
        this.props.mo7893kv("min_num_pic_urls", 1);
        if (searchFilters.hasTripPurpose()) {
            this.props.mo7895kv("trip_purpose", searchFilters.getTripPurpose().serverKey);
        }
        if (searchFilters.hasPropertyTypes()) {
            this.props.mo7895kv("listing_types", FluentIterable.from((Iterable<E>) searchFilters.getPropertyTypes()).transform(ExploreRequestParamsBuilder$$Lambda$1.lambdaFactory$()).join(Joiner.m1895on(',')));
        }
    }

    private void buildForPlaces(PlaceFilters placesSearchFilters) {
        int i = 0;
        for (ExplorePlacesTopCategory item : placesSearchFilters.getGuidebookCategories()) {
            int i2 = i + 1;
            this.props.mo7895kv("guidebook_top_categories[" + i + "]", item.getCategory());
            i = i2;
        }
        int i3 = 0;
        for (GuidebookItemType itemType : placesSearchFilters.getGuidebookItemTypes()) {
            int i4 = i3 + 1;
            this.props.mo7893kv("guidebook_item_types[" + i3 + "]", itemType.getId());
            i3 = i4;
        }
    }

    private void addIfNotDefault(int value, int defaultValue, String key) {
        if (value != defaultValue) {
            this.props.mo7893kv(key, value);
        }
    }

    public ExploreRequestParamsBuilder withFormat(String format) {
        this.props.mo7895kv(TimelineRequest.ARG_FORMAT, format);
        return this;
    }

    public ExploreRequestParamsBuilder limit(int limit) {
        this.props.mo7893kv(TimelineRequest.ARG_LIMIT, limit);
        return this;
    }

    public ExploreRequestParamsBuilder offset(int offset) {
        this.props.mo7893kv(TimelineRequest.ARG_OFFSET, offset);
        return this;
    }

    public ExploreRequestParamsBuilder withFacets(boolean includeFacets) {
        this.props.mo7897kv("fetch_facets", includeFacets);
        return this;
    }

    public ExploreRequestParamsBuilder withMapBounds(MapBounds mapBounds) {
        this.props.mo7891kv("sw_lat", mapBounds.latLngSW().latitude);
        this.props.mo7891kv("sw_lng", mapBounds.latLngSW().longitude);
        this.props.mo7891kv("ne_lat", mapBounds.latLngNE().latitude);
        this.props.mo7891kv("ne_lng", mapBounds.latLngNE().longitude);
        return this;
    }

    public ExploreRequestParamsBuilder mix(Map<String, String> params) {
        for (Entry<String, String> key : params.entrySet()) {
            this.props.mo7895kv((String) key.getKey(), (String) key.getValue());
        }
        return this;
    }

    public ExploreRequestParamsBuilder withPredictiveFilters() {
        this.props.mo7897kv("fetch_predictive_filters", true);
        return this;
    }

    public ExploreRequestParamsBuilder tagAsStandardSearch(boolean isStandardSearch) {
        this.props.mo7897kv("is_standard_search", isStandardSearch);
        return this;
    }

    /* renamed from: kv */
    public ExploreRequestParamsBuilder mo69483kv(String key, String value) {
        this.props.mo7895kv(key, value);
        return this;
    }

    public ExploreRequestParamsBuilder metaDataOnly() {
        this.props.mo7897kv("metadata_only", true);
        return this;
    }

    public QueryStrap build() {
        return this.props;
    }
}
