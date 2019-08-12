package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import java.lang.reflect.Type;
import p032rx.Observer;

public class DeleteReservationUserRequest extends BaseRequestV2<Object> {
    private final long reservationUserId;

    public DeleteReservationUserRequest(long reservationUserId2, BaseRequestListener<Object> listener) {
        withListener((Observer) listener);
        this.reservationUserId = reservationUserId2;
    }

    public String getPath() {
        return "reservation_users/" + this.reservationUserId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
