package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class AddWorkEmailRequest extends BaseRequestV2<AddWorkEmailResponse> {
    private final String email;
    private final long userId;

    class AddWorkEmailBody {
        private static final int API_TRACKING_FLOW_PARAM = 8;
        private static final int API_TRACKING_PAGE_PARAM = 6;
        @JsonProperty("email")
        String email;
        @JsonProperty("signup_flow")
        int signupFlow = 8;
        @JsonProperty("signup_page")
        int signupPage = 6;
        @JsonProperty("user_id")
        long userId;

        AddWorkEmailBody(long userId2, String email2) {
            this.userId = userId2;
            this.email = email2;
        }
    }

    public static AddWorkEmailRequest forWorkEmail(long userId2, String email2) {
        return new AddWorkEmailRequest(userId2, email2);
    }

    private AddWorkEmailRequest(long userId2, String email2) {
        this.userId = userId2;
        this.email = email2;
    }

    public String getPath() {
        return "business_travel_employees";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_enrollment");
    }

    public Type successResponseType() {
        return AddWorkEmailResponse.class;
    }

    public Object getBody() {
        return new AddWorkEmailBody(this.userId, this.email);
    }
}
