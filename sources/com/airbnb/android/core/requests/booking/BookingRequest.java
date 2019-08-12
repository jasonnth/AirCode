package com.airbnb.android.core.requests.booking;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.booking.requestbodies.BookingRequestBody.Builder;
import com.airbnb.android.core.requests.booking.responses.BookingResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class BookingRequest extends BaseRequestV2<BookingResponse> {
    private final ReservationDetails reservationDetails;
    private final String searchSessionId;

    public BookingRequest(ReservationDetails reservationDetails2, String searchSessionId2) {
        this.reservationDetails = reservationDetails2;
        this.searchSessionId = searchSessionId2;
    }

    public String getPath() {
        return "booking_request_operations";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return BookingResponse.class;
    }

    public Collection<Query> getQueryParams() {
        QueryStrap params = QueryStrap.make().mo7895kv("_intents", "make_reservation");
        if (this.searchSessionId != null) {
            params.mo7895kv("search_ranking_id", this.searchSessionId);
        }
        return params;
    }

    public Object getBody() {
        return new Builder().confirmationCode(this.reservationDetails.confirmationCode()).paymentInstrument(this.reservationDetails.paymentInstrument()).currency(this.reservationDetails.currency()).messageToHost(this.reservationDetails.messageToHost()).guestIdentities(this.reservationDetails.identifications()).businessTripDetails(Boolean.valueOf(this.reservationDetails.isVerifiedBusinessTrip()), this.reservationDetails.isBusinessTravelPaymentMethod(), this.reservationDetails.businessTripNote()).build();
    }

    public AirResponse<BookingResponse> transformResponse(AirResponse<BookingResponse> response) {
        BookingResponse bookingResponse = (BookingResponse) response.body();
        bookingResponse.reservation = bookingResponse.bookingRequestOperation.getReservation();
        return response;
    }
}
