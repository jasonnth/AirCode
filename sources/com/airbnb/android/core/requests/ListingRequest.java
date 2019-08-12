package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import p032rx.Observer;
import retrofit2.Query;

public class ListingRequest extends BaseRequestV2<ListingResponse> {
    public static final int FETCH_SIZE_HOST_DASHBOARD = 5;
    public static final int FETCH_SIZE_MANAGE_LISTING_PICKER = 50;
    public static final int FETCH_SIZE_MY_SPACES = 10;
    private final Long listingId;
    private boolean mCache;
    private final Strap params;

    private ListingRequest(Long listingId2, Strap params2, BaseRequestListener<ListingResponse> listener) {
        this.mCache = true;
        withListener((Observer) listener);
        this.listingId = listingId2;
        this.params = params2;
    }

    private ListingRequest(Long listingId2, Strap params2) {
        this.mCache = true;
        this.listingId = listingId2;
        this.params = params2;
    }

    private ListingRequest(Strap params2, BaseRequestListener<ListingResponse> listener) {
        this(null, params2, listener);
    }

    private ListingRequest(Strap params2) {
        this(null, params2, null);
    }

    public String getPath() {
        return this.listingId != null ? "listings/" + this.listingId : "listings";
    }

    public static ListingRequest forListingId(long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_reservations"));
    }

    public static ListingRequest forMySpaces(long userId, boolean showUnavailable, int count, int offset, BaseRequestListener<ListingResponse> listener) {
        return new ListingRequest(Strap.make().mo11638kv("user_id", userId).mo11637kv(TimelineRequest.ARG_OFFSET, offset).mo11639kv(TimelineRequest.ARG_FORMAT, "v1_legacy_long").mo11637kv(TimelineRequest.ARG_LIMIT, count).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, !showUnavailable), listener);
    }

    public static ListingRequest forListingName(long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "id_name"));
    }

    public static ListingRequest forV1LegacyManageListing(long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "v1_legacy_long_manage_listing"));
    }

    public static ListingRequest forMySpaces(long userId, boolean showUnavailable, BaseRequestListener<ListingResponse> listener) {
        return forMySpaces(userId, showUnavailable, 10, 0, listener);
    }

    public static ListingRequest forHostStatsPicker(long userId, int offset, int limit, BaseRequestListener<ListingResponse> listener) {
        return new ListingRequest(Strap.make().mo11638kv("user_id", userId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_picker").mo11639kv("_order", "has_availability DESC, name ASC").mo11637kv(TimelineRequest.ARG_OFFSET, offset).mo11637kv(TimelineRequest.ARG_LIMIT, limit).mo11640kv("published", true).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, false), listener);
    }

    public static ListingRequest forSingleInsight(long userId, long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11638kv("user_id", userId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_picker").mo11639kv("_order", "has_availability DESC, name ASC").mo11640kv("published", true).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, false));
    }

    public static ListingRequest forPhotographyStatus(long userId, boolean showUnavailable, BaseRequestListener<ListingResponse> listener) {
        boolean z = false;
        Strap kv = Strap.make().mo11638kv("user_id", userId).mo11637kv(TimelineRequest.ARG_OFFSET, 0).mo11639kv(TimelineRequest.ARG_FORMAT, "v1_legacy_with_photography").mo11637kv(TimelineRequest.ARG_LIMIT, 10);
        String str = ListingRequestConstants.JSON_HAS_AVAILABILITY;
        if (!showUnavailable) {
            z = true;
        }
        return new ListingRequest(kv.mo11640kv(str, z), listener);
    }

    public static ListingRequest forTemplateMessage(long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_template_message"));
    }

    public static ListingRequest forActionCards(long userId, int offset, BaseRequestListener<ListingResponse> listener) {
        boolean z = true;
        ListingRequest listingRequest = new ListingRequest(Strap.make().mo11638kv("user_id", userId).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, true).mo11639kv(TimelineRequest.ARG_FORMAT, "v1_legacy_long_manage_listing"), listener);
        if (offset != 0) {
            z = false;
        }
        return listingRequest.enableCache(z);
    }

    public static ListingRequest forCurrentUserListings() {
        return new ListingRequest(Strap.make().mo11638kv("user_id", AirbnbAccountManager.currentUserId()).mo11639kv(TimelineRequest.ARG_FORMAT, "v1_legacy_short"));
    }

    public static ListingRequest forCalendar(int offset, int limit) {
        return new ListingRequest(Strap.make().mo11638kv("user_id", AirbnbAccountManager.currentUserId()).mo11639kv(TimelineRequest.ARG_FORMAT, "for_manage_listing_app").mo11639kv("_order", "has_availability DESC, name ASC").mo11637kv(TimelineRequest.ARG_OFFSET, offset).mo11637kv(TimelineRequest.ARG_LIMIT, limit).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, false).mo11640kv("published", true));
    }

    public static ListingRequest forListYourSpaceDLS(long listingId2) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_lys_mobile"));
    }

    public static BaseRequestV2<ListingResponse> forAvailabilityDefaults(long listingId2, BaseRequestListener<ListingResponse> listener) {
        return new ListingRequest(Long.valueOf(listingId2), Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "availability_defaults"), listener);
    }

    private ListingRequest enableCache(boolean enableCache) {
        this.mCache = enableCache;
        return this;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public long getCacheTimeoutMs() {
        return this.mCache ? 604800000 : 0;
    }

    public long getCacheOnlyTimeoutMs() {
        return this.mCache ? 1200000 : 0;
    }

    public Type successResponseType() {
        return ListingResponse.class;
    }
}
