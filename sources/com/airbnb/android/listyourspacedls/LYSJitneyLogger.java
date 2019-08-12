package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.jitney.event.logging.Address.p037v1.C1797Address;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBathroomsSelectNumEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBathroomsSelectTypeEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBedroomsNumBedsEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBedroomsNumRoomsEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBedroomsSelectBedTypeEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSBedroomsSelectNumGuestsEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSChoosePricingModeEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSClickToAddPhotosNowEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSClickToSkipPhotosEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSConfirmAllWithIbOffEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSDeletePhotoEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSHostingFrequencySelectOptionEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSLocalLawNextClickEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSPreviewPhotosEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSPublishBackButtonEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSPublishListingEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSPublishMakeChangesEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSRentHistorySelectOptionEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSReorderPhotosEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSRoomSelectPlaceTypeEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSRoomSelectPropertyTypeEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSSetToCoverPhotoEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSSwitchBetweenIbAndRtbEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSUpdatePhotoCaptionEvent;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSCreateListingEvent;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSLocationAddressFormEvent;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSUpdateLysLastFinishedStepIdEvent.Builder;
import com.airbnb.jitney.event.logging.LYS.p131v3.LYSLandingPageViewLysEvent;
import com.airbnb.jitney.event.logging.LysLandingPagesType.p140v1.C2377LysLandingPagesType;

public class LYSJitneyLogger extends BaseLogger {
    public LYSJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logUpdateLastFinishedStepIdEvent(Long listingId, String stepId) {
        publish(new Builder(context(), listingId, stepId));
    }

    public void logBedroomsSelectBedTypeEvent(String bedType, Long userId, Long listingId) {
        publish(new LYSBedroomsSelectBedTypeEvent.Builder(context(), bedType, userId, listingId));
    }

    public void logBedroomsSelectNumGuestsEvent(String numGuests, Long userId, Long listingId) {
        publish(new LYSBedroomsSelectNumGuestsEvent.Builder(context(), numGuests, userId, listingId));
    }

    public void logBedroomsNumRoomsEvent(String numRooms, Long userId, Long listingId) {
        publish(new LYSBedroomsNumRoomsEvent.Builder(context(), numRooms, userId, listingId));
    }

    public void logBedroomsNumBedsEvent(String numBeds, Long userId, Long listingId) {
        publish(new LYSBedroomsNumBedsEvent.Builder(context(), numBeds, userId, listingId));
    }

    public void logBathroomsSelectNumEvent(String numBathrooms, Long userId, Long listingId) {
        publish(new LYSBathroomsSelectNumEvent.Builder(context(), numBathrooms, userId, listingId));
    }

    public void logBathroomsSelectTypeEvent(String bathroomType, Long userId, Long listingId) {
        publish(new LYSBathroomsSelectTypeEvent.Builder(context(), bathroomType, userId, listingId));
    }

    public void logRoomSelectPropertyTypeEvent(String propertyType, Long userId, Long listingId) {
        publish(new LYSRoomSelectPropertyTypeEvent.Builder(context(), propertyType, userId, listingId));
    }

    public void logRoomSelectPlaceTypeEvent(String placeType, Long userId, Long listingId) {
        publish(new LYSRoomSelectPlaceTypeEvent.Builder(context(), placeType, userId, listingId));
    }

    public void logLocationAddressFormEvent(AirAddress airAddress, Long userId, Long listingId) {
        publish(new LYSLocationAddressFormEvent.Builder(context(), new C1797Address.Builder().street_address(airAddress.streetAddressOne()).apt_num(airAddress.streetAddressTwo()).city(airAddress.city()).state(airAddress.state()).country_code(airAddress.countryCode()).zip(airAddress.postalCode()).build(), userId, listingId));
    }

    public void logHostingFrequencySelectOptionEvent(String hostingFrequencyType, Long listingId) {
        publish(new LYSHostingFrequencySelectOptionEvent.Builder(context(), hostingFrequencyType, listingId));
    }

    public void logRentHistorySelectOptionEvent(String rentHistoryType, Long listingId) {
        publish(new LYSRentHistorySelectOptionEvent.Builder(context(), rentHistoryType, listingId));
    }

    public void logCreateListingEvent(boolean createSucceeded, Long listingId, String sessionId) {
        publish(new LYSCreateListingEvent.Builder(context(), Boolean.valueOf(createSucceeded), listingId, sessionId));
    }

    public void logPublishListingEvent(boolean publishSucceeded, Long listingId) {
        publish(new LYSPublishListingEvent.Builder(context(), Boolean.valueOf(publishSucceeded), listingId));
    }

    public void logClickToAddPhotos(String target, Long listingId) {
        publish(new LYSClickToAddPhotosNowEvent.Builder(context(), target, listingId));
    }

    public void logClickToSkipPhotos(Long listingId) {
        publish(new LYSClickToSkipPhotosEvent.Builder(context(), listingId));
    }

    public void logReorderPhotos(Long listingId) {
        publish(new LYSReorderPhotosEvent.Builder(context(), listingId));
    }

    public void logPreviewPhotos(Long listingId) {
        publish(new LYSPreviewPhotosEvent.Builder(context(), listingId));
    }

    public void logUpdatePhotoCaption(Long listingId) {
        publish(new LYSUpdatePhotoCaptionEvent.Builder(context(), listingId));
    }

    public void logDeletePhoto(Long listingId) {
        publish(new LYSDeletePhotoEvent.Builder(context(), listingId));
    }

    public void logSetToCoverPhoto(Long listingId) {
        publish(new LYSSetToCoverPhotoEvent.Builder(context(), listingId));
    }

    public void logSwitchBookingSettings(Long listingId, boolean isIbOn) {
        publish(new LYSSwitchBetweenIbAndRtbEvent.Builder(context(), isIbOn ? "ib" : "rtb", listingId));
    }

    public void logConfirmAllWithIbOff(Long listingId) {
        publish(new LYSConfirmAllWithIbOffEvent.Builder(context(), listingId));
    }

    public void logChoosePricingMode(Long listingId, boolean isSmartPricing) {
        publish(new LYSChoosePricingModeEvent.Builder(context(), isSmartPricing ? "smart" : "fixed", listingId));
    }

    public void logLandingPageView(Long userId, Long listingId, C2377LysLandingPagesType page) {
        publish(new LYSLandingPageViewLysEvent.Builder(context(), page, userId, listingId));
    }

    public void logLocalLawNextClick(Long listingId, boolean hasPhoto) {
        publish(new LYSLocalLawNextClickEvent.Builder(context(), Boolean.valueOf(hasPhoto), listingId));
    }

    public void logPublishMakeChanges(Long listingId) {
        publish(new LYSPublishMakeChangesEvent.Builder(context(), listingId));
    }

    public void logPublishBackButton(Long listingId) {
        publish(new LYSPublishBackButtonEvent.Builder(context(), listingId));
    }
}
