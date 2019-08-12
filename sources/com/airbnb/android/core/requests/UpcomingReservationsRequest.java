package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.responses.UpcomingReservationsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.fragments.SeasonalCalendarRuleRangeSelectorDialog;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import p032rx.Observer;
import retrofit2.Query;

public class UpcomingReservationsRequest extends BaseRequestV2<UpcomingReservationsResponse> {
    public static final String FOR_CALENDAR_THUMBNAIL = "for_calendar_thumbnail";
    public static final int ITEMS_PER_FETCH = 30;
    private final Strap params;

    public static UpcomingReservationsRequest forHostDashboard(int offset, Observer<AirResponse<UpcomingReservationsResponse>> listener) {
        UpcomingReservationsRequest request = new UpcomingReservationsRequest(offset, 30, null);
        request.withListener((Observer) listener);
        return request;
    }

    public static UpcomingReservationsRequest forCalendarThumbnail(int offset, int limit, AirDate startDate, AirDate endDate) {
        Strap params2 = buildBaseParams(offset, limit, startDate);
        if (endDate != null) {
            params2.mo11639kv(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE, endDate.getIsoDateString());
        }
        params2.mo11640kv("include_accept", true);
        params2.mo11640kv("include_pending", true);
        params2.mo11640kv("include_checkpoint", true);
        params2.mo11640kv("include_canceled", true);
        params2.mo11640kv(FOR_CALENDAR_THUMBNAIL, true);
        return new UpcomingReservationsRequest(params2);
    }

    public static UpcomingReservationsRequest forCalendarAgenda(int offset) {
        return new UpcomingReservationsRequest(buildBaseParams(offset, 30, null));
    }

    UpcomingReservationsRequest(int offset, int limit, AirDate startDate) {
        this.params = buildBaseParams(offset, limit, startDate);
    }

    UpcomingReservationsRequest(Strap params2) {
        this.params = params2;
    }

    private static Strap buildBaseParams(int offset, int limit, AirDate startDate) {
        String startDateString;
        Strap params2 = Strap.make().mo11638kv("host_id", AirbnbAccountManager.currentUserId()).mo11639kv("_order", SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE).mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_list").mo11637kv(TimelineRequest.ARG_LIMIT, limit).mo11637kv(TimelineRequest.ARG_OFFSET, offset);
        if (startDate != null) {
            startDateString = startDate.getIsoDateString();
        } else {
            startDateString = AirDate.today().getIsoDateString();
        }
        params2.mo11639kv(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE, startDateString);
        return params2;
    }

    public String getPath() {
        return NotificationPreferencesGroups.RESERVATIONS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public Type successResponseType() {
        return UpcomingReservationsResponse.class;
    }
}
