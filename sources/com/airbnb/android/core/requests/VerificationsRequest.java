package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.responses.VerificationsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class VerificationsRequest extends BaseRequest<VerificationsResponse> {
    private final Filter filter;

    public enum Filter {
        None(""),
        SignUp("sign_up"),
        ContactHost("contact_host"),
        Booking(BookingAnalytics.EVENT_NAME),
        AcceptCohostInvitation("accept_cohosting_invitation");
        
        private final String paramName;

        private Filter(String paramName2) {
            this.paramName = paramName2;
        }

        public String getParamName() {
            return this.paramName;
        }
    }

    public VerificationsRequest(BaseRequestListener<VerificationsResponse> listener) {
        this(Filter.None, listener);
    }

    public VerificationsRequest(Filter filter2, BaseRequestListener<VerificationsResponse> listener) {
        withListener(listener);
        this.filter = filter2;
    }

    public String getPath() {
        return VerifiedIdActivityIntents.VERIFICATIONS_EXTRA;
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make();
        if (this.filter != Filter.None) {
            strap.mo7895kv("account_activation_flow", this.filter.getParamName());
        }
        return strap;
    }

    public Type successResponseType() {
        return VerificationsResponse.class;
    }
}
