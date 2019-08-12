package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReservationUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReservationUserResponse extends BaseResponse {
    @JsonProperty("reservation_users")
    public List<ReservationUser> reservationUsers;
}
