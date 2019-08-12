package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.responses.GetUnavailabilitiesResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.fragments.SeasonalCalendarRuleRangeSelectorDialog;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

@Deprecated
public class GetUnavailabilitiesRequest extends BaseRequestV2<GetUnavailabilitiesResponse> {
    private final AirDate endDate;
    private final long listingId;
    private final AirDate startDate;

    public GetUnavailabilitiesRequest(long listingId2, AirDate startDate2, AirDate endDate2, BaseRequestListener<GetUnavailabilitiesResponse> listener) {
        withListener((Observer) listener);
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.listingId = listingId2;
    }

    public String getPath() {
        return ArgumentConstants.ARG_CALENDAR_DAYS;
    }

    public Collection<Query> getQueryParams() {
        String str;
        String str2 = null;
        QueryStrap kv = QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7895kv(TimelineRequest.ARG_FORMAT, "for_unavailabilities");
        String str3 = SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE;
        if (this.startDate != null) {
            str = this.startDate.getIsoDateString();
        } else {
            str = null;
        }
        QueryStrap kv2 = kv.mo7895kv(str3, str);
        String str4 = SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE;
        if (this.endDate != null) {
            str2 = this.endDate.getIsoDateString();
        }
        return kv2.mo7895kv(str4, str2);
    }

    public long getCacheTimeoutMs() {
        return 60000;
    }

    public Type successResponseType() {
        return GetUnavailabilitiesResponse.class;
    }
}
