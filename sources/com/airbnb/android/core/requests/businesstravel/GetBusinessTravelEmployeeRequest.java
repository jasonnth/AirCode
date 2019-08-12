package com.airbnb.android.core.requests.businesstravel;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.airbnb.android.core.responses.businesstravel.BusinessTravelEmployeeResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetBusinessTravelEmployeeRequest extends BaseRequestV2<BusinessTravelEmployeeResponse> {
    private final long userId;

    public GetBusinessTravelEmployeeRequest(long userId2) {
        this.userId = userId2;
    }

    public Type successResponseType() {
        return BusinessTravelEmployeeResponse.class;
    }

    public String getPath() {
        return "business_travel_employees";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId).mo7895kv(TimelineRequest.ARG_FORMAT, "for_enrollment");
    }

    public AirResponse<BusinessTravelEmployeeResponse> transformResponse(AirResponse<BusinessTravelEmployeeResponse> response) {
        BusinessTravelEmployeeResponse businessTravelEmployeeResponse = (BusinessTravelEmployeeResponse) response.body();
        Optional<BusinessTravelEmployee> firstResult = FluentIterable.from((Iterable<E>) businessTravelEmployeeResponse.businessTravelEmployees).first();
        if (firstResult.isPresent()) {
            businessTravelEmployeeResponse.businessTravelEmployee = (BusinessTravelEmployee) firstResult.get();
        }
        return response;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }
}
