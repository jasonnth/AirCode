package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CohostingContractResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class CohostingContractRequest extends BaseRequestV2<CohostingContractResponse> {
    private final long contractId;

    public CohostingContractRequest(long contractId2) {
        this.contractId = contractId2;
    }

    public String getPath() {
        return "cohosting_contracts/" + this.contractId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_manage_listing");
    }

    public Type successResponseType() {
        return CohostingContractResponse.class;
    }
}
