package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.responses.CalendarResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.fragments.SeasonalCalendarRuleRangeSelectorDialog;
import com.google.common.base.Joiner;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import retrofit2.Query;

public class CalendarRequest extends BaseRequestV2<CalendarResponse> {
    private final AirDate endDate;
    private final String format;
    private final boolean includingNeverPublishedListings;
    private final Set<Long> listingIds;
    private final AirDate startDate;

    private CalendarRequest(Set<Long> listingIds2, AirDate startDate2, AirDate endDate2, String format2, boolean includingNeverPublishedListings2) {
        this.listingIds = listingIds2;
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.format = format2;
        this.includingNeverPublishedListings = includingNeverPublishedListings2;
    }

    public static CalendarRequest forMultiCalendar(Set<Long> listingIds2, AirDate startDate2, AirDate endDate2, boolean includingNeverPublishedListings2) {
        return new CalendarRequest(listingIds2, startDate2, endDate2, "host_calendar_detailed", includingNeverPublishedListings2);
    }

    public String getPath() {
        return "calendars";
    }

    public Collection<Query> getQueryParams() {
        Collection<Query> queries = new ArrayList<>();
        queries.add(new Query(TimelineRequest.ARG_FORMAT, this.format));
        queries.add(new Query(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE, this.startDate.getIsoDateString()));
        queries.add(new Query(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE, this.endDate.getIsoDateString()));
        if (!this.listingIds.isEmpty()) {
            queries.add(new Query("listing_ids", Joiner.m1896on(",").join((Iterable<?>) this.listingIds)));
        }
        if (this.includingNeverPublishedListings) {
            queries.add(new Query("filter", "all"));
        }
        return queries;
    }

    public AirResponse<CalendarResponse> transformResponse(AirResponse<CalendarResponse> response) {
        ((CalendarResponse) response.body()).updateServerSyncTime();
        return response;
    }

    public Type successResponseType() {
        return CalendarResponse.class;
    }

    public boolean isSameOrSuperset(CalendarRequest other) {
        return other.startDate.isBetweenInclusive(this.startDate, this.endDate) && other.endDate.isBetweenInclusive(this.startDate, this.endDate) && (this.listingIds.isEmpty() || (!other.listingIds.isEmpty() && this.listingIds.containsAll(other.listingIds)));
    }
}
