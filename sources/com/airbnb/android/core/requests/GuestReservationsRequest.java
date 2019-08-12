package com.airbnb.android.core.requests;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationMetadata;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.responses.GuestReservationsResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import retrofit2.Query;

public class GuestReservationsRequest extends BaseRequestV2<GuestReservationsResponse> {
    public static final int ITEMS_PER_FETCH = 25;
    private static final String START_DATE_DEFAULT_VALUE = "2008-01-01";
    private static final String START_DATE_PARAM_NAME = "start_date";
    private final AirbnbApi airbnbApi;
    private final Handler handler;
    private boolean isOnAppStart;
    private final boolean isUpcoming;
    private final Strap strap;

    public static GuestReservationsRequest forGuestHome(AirbnbApi airbnbApi2, long currentUserId) {
        return new GuestReservationsRequest(airbnbApi2, true, getBaseProperties(0, currentUserId).mo11639kv("include_pending", InternalLogger.EVENT_PARAM_EXTRAS_FALSE).mo11639kv("include_checkpoint", "true").mo11639kv("_order", "start_date ASC").mo11639kv(TimelineRequest.ARG_FORMAT, "for_guidebooks").mo11640kv("include_shared_itinerary", true));
    }

    public static GuestReservationsRequest forUpcoming(AirbnbApi airbnbApi2, int offset, long currentUserId, boolean isOnAppStart2) {
        boolean z = true;
        Strap strap2 = getBaseProperties(offset, currentUserId).mo11639kv("include_pending", "true").mo11639kv("include_checkpoint", "true").mo11639kv("_order", "start_date ASC").mo11640kv("include_shared_itinerary", true).mo11640kv("is_on_app_start", isOnAppStart2);
        if (offset != 0) {
            z = false;
        }
        return new GuestReservationsRequest(airbnbApi2, z, strap2, isOnAppStart2);
    }

    public static GuestReservationsRequest forStoriesCreationTripPicker(AirbnbApi airbnbApi2, int offset, int itemsPerFetch, long currentUserId) {
        return new GuestReservationsRequest(airbnbApi2, false, getBaseProperties(offset, currentUserId, itemsPerFetch).mo11639kv("include_pending", InternalLogger.EVENT_PARAM_EXTRAS_FALSE).mo11639kv("include_checkpoint", InternalLogger.EVENT_PARAM_EXTRAS_FALSE).mo11640kv("include_shared_itinerary", true).mo11639kv("_order", "start_date DESC").mo11639kv("start_date", START_DATE_DEFAULT_VALUE).mo11639kv(TimelineRequest.ARG_FORMAT, "for_stories_trip_picker"));
    }

    public static GuestReservationsRequest forUpcomingAndPast(AirbnbApi airbnbApi2, int offset, int itemsPerFetch, long currentUserId) {
        return new GuestReservationsRequest(airbnbApi2, false, getBaseProperties(offset, currentUserId, itemsPerFetch).mo11639kv("include_pending", "true").mo11639kv("include_checkpoint", "true").mo11639kv("start_date", START_DATE_DEFAULT_VALUE));
    }

    private GuestReservationsRequest(AirbnbApi airbnbApi2, boolean isUpcoming2, Strap strap2) {
        this(airbnbApi2, isUpcoming2, strap2, false);
    }

    private GuestReservationsRequest(AirbnbApi airbnbApi2, boolean isUpcoming2, Strap strap2, boolean isOnAppStart2) {
        this.handler = new Handler(Looper.getMainLooper());
        this.airbnbApi = airbnbApi2;
        this.strap = strap2;
        this.isUpcoming = isUpcoming2;
        this.isOnAppStart = isOnAppStart2;
    }

    public String getPath() {
        return NotificationPreferencesGroups.RESERVATIONS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.strap);
    }

    public AirResponse<GuestReservationsResponse> transformResponse(AirResponse<GuestReservationsResponse> response) {
        if (this.isUpcoming) {
            this.handler.post(GuestReservationsRequest$$Lambda$1.lambdaFactory$(this, response));
        }
        return response;
    }

    static /* synthetic */ void lambda$transformResponse$0(GuestReservationsRequest guestReservationsRequest, AirResponse response) {
        List<Reservation> reservations = ((GuestReservationsResponse) response.body()).reservations;
        ReservationMetadata metadata = ((GuestReservationsResponse) response.body()).metadata;
        guestReservationsRequest.airbnbApi.setLandingTabId(metadata != null ? metadata.getLandingAppTabId() : null);
        guestReservationsRequest.airbnbApi.setHasUpcomingTrips(!reservations.isEmpty());
        guestReservationsRequest.checkForActiveTrip(reservations);
    }

    private void checkForActiveTrip(List<Reservation> reservations) {
        this.airbnbApi.setHasActiveTrip(FluentIterable.from((Iterable<E>) reservations).anyMatch(GuestReservationsRequest$$Lambda$2.lambdaFactory$()));
    }

    static /* synthetic */ boolean lambda$checkForActiveTrip$1(Reservation input) {
        boolean isCurrentlyOnTrip;
        boolean isTripWithin2Days;
        AirDate today = AirDate.today();
        if (today.compareTo(input.getCheckinDate()) < 0 || today.compareTo(input.getCheckoutDate()) > 0) {
            isCurrentlyOnTrip = false;
        } else {
            isCurrentlyOnTrip = true;
        }
        if (today.plusDays(2).compareTo(input.getCheckinDate()) >= 0) {
            isTripWithin2Days = true;
        } else {
            isTripWithin2Days = false;
        }
        if (isCurrentlyOnTrip || isTripWithin2Days) {
            return true;
        }
        return false;
    }

    private static Strap getBaseProperties(int offset, long currentUserId) {
        return getBaseProperties(offset, currentUserId, 25);
    }

    private static Strap getBaseProperties(int offset, long currentUserId, int itemsPerFetch) {
        return Strap.make().mo11637kv(TimelineRequest.ARG_OFFSET, offset).mo11637kv(TimelineRequest.ARG_LIMIT, itemsPerFetch).mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_list").mo11638kv("guest_id", currentUserId);
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_YEAR_MILLIS;
    }

    public Type successResponseType() {
        return GuestReservationsResponse.class;
    }
}
