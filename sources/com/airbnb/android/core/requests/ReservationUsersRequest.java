package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ReservationUserResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class ReservationUsersRequest extends BaseRequestV2<ReservationUserResponse> {
    private final String confirmationCode;

    public ReservationUsersRequest(String confirmationCode2, BaseRequestListener<ReservationUserResponse> listener) {
        withListener((Observer) listener);
        this.confirmationCode = confirmationCode2;
    }

    public String getPath() {
        return "reservation_users";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("confirmation_code", this.confirmationCode);
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return ReservationUserResponse.class;
    }
}
