package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.responses.SpecialOfferResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateSpecialOfferRequest extends BaseRequestV2<SpecialOfferResponse> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("block_instant_booking")
        final boolean blockInstantBooking;
        @JsonProperty("check_in")
        final String checkIn;
        @JsonProperty("native_currency")
        final String currency;
        @JsonProperty("guests")
        final int guests;
        @JsonProperty("host_agreed_korean_booking")
        final boolean hostAgreedSouthKoreanPreapproval;
        @JsonProperty("listing_id")
        final long listingId;
        @JsonProperty("nights")
        final int nights;
        @JsonProperty("price")
        final int priceTotal;
        @JsonProperty("thread_id")
        final long threadId;

        RequestBody(String checkIn2, int guests2, long listingId2, int nights2, int priceTotal2, String currency2, long threadId2, boolean blockInstantBooking2, boolean hostAgreedSouthKoreanPreapproval2) {
            this.checkIn = checkIn2;
            this.guests = guests2;
            this.listingId = listingId2;
            this.nights = nights2;
            this.priceTotal = priceTotal2;
            this.threadId = threadId2;
            this.blockInstantBooking = blockInstantBooking2;
            this.currency = currency2;
            this.hostAgreedSouthKoreanPreapproval = hostAgreedSouthKoreanPreapproval2;
        }
    }

    public CreateSpecialOfferRequest(AirDate checkIn, int guests, long listingId, int nights, int priceTotal, String currency, long threadId, boolean blockInstantBooking) {
        this.requestBody = new RequestBody(checkIn.getIsoDateString(), guests, listingId, nights, priceTotal, currency, threadId, blockInstantBooking, false);
    }

    public CreateSpecialOfferRequest(AirDate checkIn, int guests, long listingId, int nights, int priceTotal, String currency, long threadId, boolean blockInstantBooking, boolean hostAgreedSouthKoreanPreapproval) {
        this.requestBody = new RequestBody(checkIn.getIsoDateString(), guests, listingId, nights, priceTotal, currency, threadId, blockInstantBooking, hostAgreedSouthKoreanPreapproval);
    }

    public CreateSpecialOfferRequest(TripInformationProvider provider) {
        this(provider.getStartDate(), provider.getGuestCount(), provider.getListing().getId(), provider.getReservedNightsCount(), provider.getHostSubtotalAmount(), provider.getHostSubtotalCurrency(), provider.getThreadId(), provider.getListing().isInstantBookEnabled() || provider.getListing().isInstantBookable(), provider.isKoreanStrictBooking());
    }

    public String getPath() {
        return "special_offers";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return SpecialOfferResponse.class;
    }
}
