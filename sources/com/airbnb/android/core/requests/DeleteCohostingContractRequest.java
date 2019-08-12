package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.DeleteCohostingContractResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import retrofit2.Query;

public class DeleteCohostingContractRequest extends BaseRequestV2<DeleteCohostingContractResponse> {
    private final int DELETE_CONTRACT = 5;
    private final long contractId;

    public DeleteCohostingContractRequest(long contractId2) {
        this.contractId = contractId2;
    }

    public String getPath() {
        return "cohosting_contracts/" + this.contractId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_manage_listing");
    }

    public Object getBody() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("action", Integer.valueOf(5));
        return payload;
    }

    public Type successResponseType() {
        return DeleteCohostingContractResponse.class;
    }
}
