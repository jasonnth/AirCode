package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SetCohostingContractResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import retrofit2.Query;

public class SetCohostingContractRequest extends BaseRequestV2<SetCohostingContractResponse> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("cohost_id")
        final long cohostId;
        @JsonProperty("from_additionalhost")
        final boolean fromAdditionalHost;
        @JsonProperty("listing_id")
        final long listingId;
        @JsonProperty("owner_id")
        final long ownerId;
        @JsonProperty("split_rule")
        final HashMap<String, Object> splitRule = new HashMap<>();

        RequestBody(long ownerId2, long cohostId2, long listingId2, int percentage, int minimumFee, int fixedAmount, String amountCurrency, boolean includeCleaningFee) {
            this.ownerId = ownerId2;
            this.cohostId = cohostId2;
            this.listingId = listingId2;
            this.splitRule.put("percentage", Integer.valueOf(percentage));
            this.splitRule.put("minimum_fee", Integer.valueOf(minimumFee));
            this.splitRule.put("fixed_amount", Integer.valueOf(fixedAmount));
            this.splitRule.put("amount_currency", amountCurrency);
            this.splitRule.put("include_cleaning_fee", Boolean.valueOf(includeCleaningFee));
            this.fromAdditionalHost = true;
        }
    }

    public SetCohostingContractRequest(long ownerId, long cohostId, long listingId, int percentage, int minimumFee, int fixedAmount, String amountCurrency, boolean includeCleaningFee) {
        this.requestBody = new RequestBody(ownerId, cohostId, listingId, percentage, minimumFee, fixedAmount, amountCurrency, includeCleaningFee);
    }

    public String getPath() {
        return "cohosting_contracts";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_manage_listing");
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return SetCohostingContractResponse.class;
    }
}
