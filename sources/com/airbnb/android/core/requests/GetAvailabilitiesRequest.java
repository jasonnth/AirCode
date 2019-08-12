package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.contentframework.imagepicker.MediaLoader;
import com.airbnb.android.core.responses.CalendarAvailabilityResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetAvailabilitiesRequest extends BaseRequestV2<CalendarAvailabilityResponse> {
    private final long listingId;
    private final int numberOfMonthsToFetch;
    private final int startMonth;
    private final int startYear;

    public GetAvailabilitiesRequest(long listingId2, AirDate startDate, int numberOfMonthsToFetch2) {
        this.listingId = listingId2;
        this.numberOfMonthsToFetch = numberOfMonthsToFetch2;
        this.startYear = startDate.getYear();
        this.startMonth = startDate.getMonthOfYear();
    }

    public String getPath() {
        return "calendar_months";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "with_conditions").mo7894kv("listing_id", this.listingId).mo7893kv("year", this.startYear).mo7893kv("month", this.startMonth).mo7893kv(MediaLoader.COLUMN_COUNT, this.numberOfMonthsToFetch);
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public long getCacheTimeoutMs() {
        return 60000;
    }

    public Type successResponseType() {
        return CalendarAvailabilityResponse.class;
    }
}
