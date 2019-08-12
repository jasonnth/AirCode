package com.airbnb.android.core.responses;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class GuestReservationsResponse extends BaseResponse {
    @JsonProperty("metadata")
    public ReservationMetadata metadata;
    @JsonProperty("reservations")
    public List<Reservation> reservations;

    public boolean shouldLoadMore() {
        return this.reservations.size() == 25;
    }

    public boolean findReservationByLocation(String location) {
        if (!TextUtils.isEmpty(location) && FluentIterable.from((Iterable<E>) this.reservations).firstMatch(GuestReservationsResponse$$Lambda$1.lambdaFactory$(location)).orNull() != null) {
            return true;
        }
        return false;
    }

    public boolean findReservationById(long listingId) {
        if (listingId > 0 && FluentIterable.from((Iterable<E>) this.reservations).firstMatch(GuestReservationsResponse$$Lambda$2.lambdaFactory$(listingId)).orNull() != null) {
            return true;
        }
        return false;
    }

    static /* synthetic */ boolean lambda$findReservationById$1(long listingId, Reservation reservation) {
        return reservation.getListing().getId() == listingId;
    }
}
