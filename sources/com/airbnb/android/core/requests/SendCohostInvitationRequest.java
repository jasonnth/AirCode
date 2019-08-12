package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SendCohostInvitationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class SendCohostInvitationRequest extends BaseRequestV2<SendCohostInvitationResponse> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("amount_currency")
        final String amountCurrency;
        @JsonProperty("by_code")
        final boolean byCode = true;
        @JsonProperty("fixed_amount")
        final int fixedFee;
        @JsonProperty("include_cleaning_fee")
        final boolean includeCleaningFee;
        @JsonProperty("invitee_key")
        final String inviteeIdentifierType;
        @JsonProperty("invitee_value")
        final String inviteeIdentifierValue;
        @JsonProperty("listing_id")
        final long listingId;
        @JsonProperty("message")
        final String message;
        @JsonProperty("minimum_fee")
        final int minimumFee;
        @JsonProperty("percentage")
        final int percentage;

        RequestBody(long listingId2, String inviteeIdentifierType2, String inviteeIdentifierValue2, int percentage2, int minimumFee2, int fixedFee2, String amountCurrency2, boolean includeCleaningFee2, String message2) {
            this.listingId = listingId2;
            this.inviteeIdentifierType = inviteeIdentifierType2;
            this.inviteeIdentifierValue = inviteeIdentifierValue2;
            this.percentage = percentage2;
            this.minimumFee = minimumFee2;
            this.fixedFee = fixedFee2;
            this.amountCurrency = amountCurrency2;
            this.includeCleaningFee = includeCleaningFee2;
            this.message = message2;
        }
    }

    public SendCohostInvitationRequest(long listingId, String inviteeIdentifierType, String inviteeIdentifier, int percentage, int minimumFee, int fixedFee, String amountCurrency, boolean includeCleaningFee, String message) {
        this.requestBody = new RequestBody(listingId, inviteeIdentifierType, inviteeIdentifier, percentage, minimumFee, fixedFee, amountCurrency, includeCleaningFee, message);
    }

    public String getPath() {
        return "cohost_invitations";
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
        return SendCohostInvitationResponse.class;
    }
}
