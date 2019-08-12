package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.responses.TripTemplatesResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.mparticle.commerce.Product;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class TripTemplatesRequest extends BaseRequestV2<TripTemplatesResponse> {
    private final AirDate checkIn;
    private final AirDate checkOut;
    private final GuestDetails guestDetails;
    private int limit;
    private final String location;

    public static TripTemplatesRequest forReservation(Reservation reservation) {
        return new TripTemplatesRequest(reservation.getListing().getCity(), reservation.getCheckIn(), reservation.getCheckOut(), reservation.getGuestDetails());
    }

    public TripTemplatesRequest(String location2, AirDate checkIn2, AirDate checkOut2, GuestDetails guestDetails2) {
        this.checkIn = checkIn2;
        this.checkOut = checkOut2;
        this.location = location2;
        this.guestDetails = guestDetails2;
    }

    public TripTemplatesRequest withLimit(int limit2) {
        this.limit = limit2;
        return this;
    }

    public Type successResponseType() {
        return TripTemplatesResponse.class;
    }

    public String getPath() {
        return "trip_templates";
    }

    public Collection<Query> getQueryParams() {
        String str;
        String str2;
        Integer num = null;
        QueryStrap kv = QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_explore");
        String str3 = UpdateReviewRequest.KEY_CHECKIN;
        if (this.checkIn != null) {
            str = this.checkIn.getIsoDateString();
        } else {
            str = null;
        }
        QueryStrap kv2 = kv.mo7895kv(str3, str);
        String str4 = Product.CHECKOUT;
        if (this.checkOut != null) {
            str2 = this.checkOut.getIsoDateString();
        } else {
            str2 = null;
        }
        QueryStrap kv3 = kv2.mo7895kv(str4, str2);
        String str5 = FindTweenAnalytics.GUESTS;
        if (this.guestDetails != null) {
            num = Integer.valueOf(this.guestDetails.totalGuestCount());
        }
        QueryStrap strap = kv3.mo7893kv(str5, num.intValue()).mo7895kv("location", this.location);
        if (this.limit > 0) {
            strap.mo7893kv("limit", this.limit);
        }
        return strap;
    }

    public long getCacheTimeoutMs() {
        return 1209600000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 600000;
    }
}
