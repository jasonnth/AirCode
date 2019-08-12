package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import java.lang.reflect.Type;

public class BookingSettingsRequest extends BaseRequestV2<BookingSettingsResponse> {
    private final long listingId;

    public BookingSettingsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "booking_settings/" + this.listingId;
    }

    public Type successResponseType() {
        return BookingSettingsResponse.class;
    }
}
