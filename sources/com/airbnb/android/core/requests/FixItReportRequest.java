package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.FixItReportResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class FixItReportRequest extends BaseRequestV2<FixItReportResponse> {
    private static final String KEY_HOST_ID = "host_id";
    private static final String KEY_LISTING_ID = "listing_id";
    private Strap params;
    private Long reportId;

    public static FixItReportRequest forListing(long listingId, long hostId) {
        return new FixItReportRequest(listingId, hostId);
    }

    public static FixItReportRequest forReportId(long reportId2) {
        return new FixItReportRequest(reportId2);
    }

    private FixItReportRequest(long listingId, long hostId) {
        this.params = Strap.make().mo11638kv("listing_id", listingId).mo11638kv(KEY_HOST_ID, hostId);
    }

    private FixItReportRequest(long reportId2) {
        this.reportId = Long.valueOf(reportId2);
    }

    public Type successResponseType() {
        return FixItReportResponse.class;
    }

    public String getPath() {
        return this.reportId != null ? "fixit_reports/" + this.reportId : "fixit_reports";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
