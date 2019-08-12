package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.requests.CouponRequest.CouponAction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReservationResponse extends BaseResponse {
    public CouponAction couponAction;
    public List<OldPaymentInstrument> paymentInstruments;
    @JsonProperty("reservation")
    public Reservation reservation;
}
