package com.airbnb.android.listyourspacedls.utils;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.BookingSettingsRequest;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.CheckInTermsRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.GuestControlsRequest;
import com.airbnb.android.core.requests.ListingRegistrationProcessesRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.ListingRoomsRequest;
import com.airbnb.android.core.requests.LongTermDiscountsConversionRequest;
import com.airbnb.android.core.requests.NewHostPromoRequest;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.GuestControlsResponse;
import com.airbnb.android.core.responses.ListingCheckInOptionsResponse;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.ListingRoomsResponse;
import com.airbnb.android.core.responses.LongTermDiscountsConversionResponse;
import com.airbnb.android.core.responses.NewHostPromoResponse;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.google.common.collect.Lists;
import java.util.List;

public class LYSBatchRequestUtil {
    public static AirBatchRequest getListingBatchRequest(long listingId, NonResubscribableRequestListener<AirBatchResponse> requestListener) {
        List<BaseRequestV2<?>> requests = Lists.newArrayList();
        requests.add(ListingRequest.forListYourSpaceDLS(listingId));
        requests.add(new BookingSettingsRequest(listingId));
        requests.add(DemandBasedPricingRequest.forFetch(listingId));
        requests.add(CheckInTermsRequest.forCheckInTerms(listingId));
        requests.add(AccountVerificationsRequest.forFlow(VerificationFlow.ListYourSpaceDLS));
        requests.add(CalendarRulesRequest.forListingId(listingId));
        requests.add(GuestControlsRequest.forListingId(listingId));
        requests.add(new LongTermDiscountsConversionRequest(listingId, 1.0d, 1.0d));
        requests.add(new ListingRoomsRequest(listingId));
        requests.add(NewHostPromoRequest.forLYSFetch(listingId));
        if (FeatureToggles.isListingRegistrationEnabled()) {
            requests.add(ListingRegistrationProcessesRequest.forLYS(listingId));
        }
        return new AirBatchRequest(requests, requestListener);
    }

    public static LYSDataController setListingBatchResponse(LYSDataController controller, AirBatchResponse response) {
        ListingResponse listingResponse = (ListingResponse) response.singleResponse(ListingResponse.class);
        Listing listing = listingResponse.listing;
        listing.setListingExpectations(((BookingSettingsResponse) response.singleResponse(BookingSettingsResponse.class)).bookingSettings.getListingExpectations());
        controller.setListing(listing);
        controller.setCurrencyCode(listing.getListingCurrency());
        controller.setDynamicPricingControls(((DemandBasedPricingResponse) response.singleResponse(DemandBasedPricingResponse.class)).getPricingControl());
        controller.setCheckInTimeOptions(((ListingCheckInOptionsResponse) response.singleResponse(ListingCheckInOptionsResponse.class)).checkInTimeOptions);
        controller.setAccountVerifications(((AccountVerificationsResponse) response.singleResponse(AccountVerificationsResponse.class)).accountActivationVerifications);
        controller.setCalendarRule(((CalendarRulesResponse) response.singleResponse(CalendarRulesResponse.class)).calendarRule);
        controller.setGuestControls(((GuestControlsResponse) response.singleResponse(GuestControlsResponse.class)).guestControls);
        controller.setAveragePrices(((LongTermDiscountsConversionResponse) response.singleResponse(LongTermDiscountsConversionResponse.class)).values);
        ListingRegistrationProcessesResponse registrationResponse = (ListingRegistrationProcessesResponse) response.singleResponseOrNull(ListingRegistrationProcessesResponse.class);
        if (registrationResponse != null) {
            controller.setListingRegistrationProcess(registrationResponse.getFirstListingRegistrationProcess());
        }
        controller.setBedDetails(((ListingRoomsResponse) response.singleResponse(ListingRoomsResponse.class)).listingRooms);
        controller.setNewHostPromoEnabled(((NewHostPromoResponse) response.singleResponse(NewHostPromoResponse.class)).isEnabled());
        controller.setMaxStepReachedFromInProgressListing(listingResponse.listing.getListYourSpaceLastFinishedStepId());
        return controller;
    }
}
