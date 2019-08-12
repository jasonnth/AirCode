package com.airbnb.android.core.requests.booking.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.BookingRequestOperation;
import com.airbnb.android.core.models.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponse extends BaseResponse {
    @JsonProperty("booking_request_operation")
    public BookingRequestOperation bookingRequestOperation;
    public Reservation reservation;
}
