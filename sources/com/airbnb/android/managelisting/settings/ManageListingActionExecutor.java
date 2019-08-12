package com.airbnb.android.managelisting.settings;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.utils.TextSetting;

public interface ManageListingActionExecutor {
    void aboutEarlyBirdDiscounts();

    void aboutLastMinuteDiscounts();

    void aboutLengthOfStayDiscounts();

    void additionalGuestRequirements();

    void address();

    void amenities();

    void amenityGroupDetail(int i, AmenityGroup amenityGroup);

    void availabilityRules();

    void bedDetails();

    void calendarSettings();

    void calendarTip();

    void cancellationPolicy();

    void checkInGuide();

    void checkInMethod();

    void checkInOut();

    void checkInStep(int i, long j);

    void cityRegistration();

    void cityRegistrationApplication();

    void cityRegistrationExemption();

    void cityRegistrationInputGroup(int i);

    void cityRegistrationNextSteps();

    void cohosting();

    void collectionsLanding();

    void currency();

    void description();

    void discountsExample();

    void earlyBirdDiscounts();

    void editCheckinType(CheckInInformation checkInInformation);

    void editCodeForCheckinType(CheckInInformation checkInInformation);

    void exactLocation();

    void extraCharges();

    void extraService();

    void guestExpectations();

    void guestRequirements();

    void hostingFrequencyInfo(DesiredHostingFrequencyVersion desiredHostingFrequencyVersion);

    void houseRules();

    void houseRulesLegalInfo();

    void instantBook();

    void instantBookTip();

    void instantBookUpsell();

    void invalidateData();

    void lastMinuteDiscounts();

    void licenseOrRegistrationNumber();

    void listingDeleted();

    void listingStatus();

    void localLaws();

    void location();

    void longTermDiscounts();

    void nestedListings();

    void nightlyPrice();

    void photo(long j);

    void photos();

    void popToFragment(Class<? extends Fragment> cls);

    void popToHome();

    void preBookingAddQuestions();

    void preBookingMessagePreview();

    void preBookingQuestions();

    void previewListing();

    void priceTipsDisclaimer();

    void publishedGuideConfirmation();

    void reorderCheckInSteps();

    void roomsAndGuests();

    void selfCheckin();

    void singleRoomBedDetails(int i);

    void smartCheckInInformation();

    void smartPricingTip(boolean z);

    void snoozeListing(int i);

    void textSetting(TextSetting textSetting);

    void tripLength();

    void tripLengthSeasonalRequirement(SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting);

    void unlistReason(int i);

    void unlistReasons();

    void wifi();
}
