package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ListingRegistrationProcessesRequest extends BaseRequestV2<ListingRegistrationProcessesResponse> {
    private static final String FORMAT_LYS = "for_lys_v3";
    private static final String FORMAT_ML = "for_ml_v3";
    private static final String PARAM_FORMAT = "_format";
    private static final String PARAM_LISTING_ID = "listing_id";
    private final String format;
    private final long listingId;

    public static ListingRegistrationProcessesRequest forLYS(long listingId2) {
        return new ListingRegistrationProcessesRequest(listingId2, FORMAT_LYS);
    }

    public static ListingRegistrationProcessesRequest forML(long listingId2) {
        return new ListingRegistrationProcessesRequest(listingId2, FORMAT_ML);
    }

    private ListingRegistrationProcessesRequest(long listingId2, String format2) {
        this.listingId = listingId2;
        this.format = format2;
    }

    public Type successResponseType() {
        return ListingRegistrationProcessesResponse.class;
    }

    public String getPath() {
        return "listing_registration_processes";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7895kv("_format", this.format);
    }
}
