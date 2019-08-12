package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.checkin.CheckInGuideDataModel;
import com.airbnb.android.core.responses.CheckInGuideListResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetCheckInGuideListRequest extends BaseRequestV2<CheckInGuideListResponse> {
    private static final String PARAM_FORMAT = "_format";
    private static final String PARAM_OFFLINE_SYNC = "offline_sync";

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return CheckInGuideListResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("_format", PARAM_OFFLINE_SYNC).mix(super.getQueryParams());
    }

    public String getPath() {
        return CheckInGuideDataModel.TABLE_NAME;
    }
}
