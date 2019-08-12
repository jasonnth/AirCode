package com.airbnb.android.listing;

import android.text.TextUtils;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.listing.constants.LYSStepOrderUtil;
import com.google.common.collect.FluentIterable;
import java.util.List;

public enum LYSStep {
    SpaceType(LYSCollection.Basics, "ROOM", true),
    RoomsAndGuests(LYSCollection.Basics, "BEDROOMS", true),
    BedDetails(LYSCollection.Basics, "BED_DETAILS", true),
    Bathrooms(LYSCollection.Basics, "BATHROOMS", true),
    Address(LYSCollection.Basics, "LOCATION", true),
    ExactLocation(LYSCollection.Basics, "LOCATION", true),
    Amenities(LYSCollection.Basics, "AMENITIES", true),
    Spaces(LYSCollection.Basics, "SPACES", true),
    Photos(LYSCollection.Marketing, "PHOTOS", true),
    PhotoManager(LYSCollection.Marketing, "PHOTOS", true),
    DescriptionStep(LYSCollection.Marketing, "SUMMARY", true),
    TitleStep(LYSCollection.Marketing, "TITLE", true),
    VerificationSteps(LYSCollection.Marketing, "VERIFY_PHONE_NUMBER", true),
    GuestRequirementsStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    HouseRules(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    HowGuestsBookStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    RentHistoryStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    HostingFrequencyStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    AvailabilityStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    CalendarStep(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    SelectPricingType(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    SetPrice(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    NewHostDiscount(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    Discounts(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    ReviewSettings(LYSCollection.Booking, "GUEST_REQUIREMENTS", false),
    LocalLaws(LYSCollection.Booking, "LOCAL_LAWS", true),
    CityRegistration(LYSCollection.Booking, "REGISTRATION", true),
    Identity(LYSCollection.Booking, "IDENTITY", true),
    Completion(LYSCollection.Completion, "GUEST_REQUIREMENTS", false);
    
    public final LYSCollection lysCollection;
    public final boolean shouldUpdateLastFinishedStepId;
    public final String stepId;

    private LYSStep(LYSCollection lysCollection2, String stepId2, boolean shouldUpdateLastFinishedStepId2) {
        this.lysCollection = lysCollection2;
        this.stepId = stepId2;
        this.shouldUpdateLastFinishedStepId = shouldUpdateLastFinishedStepId2;
    }

    public boolean isAfter(LYSStep comparison) {
        return getOrderedSteps().indexOf(this) > getOrderedSteps().indexOf(comparison);
    }

    public boolean isBeforeOrSame(LYSStep comparison) {
        return getOrderedSteps().indexOf(this) <= getOrderedSteps().indexOf(comparison);
    }

    public static LYSStep getNextStep(LYSStep currentStep, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess, boolean isInstantBookable) {
        Check.state(canMoveToNextStep(currentStep, maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess), "Cannot move to next step, step not complete: " + currentStep);
        return getFirstNonskippableStep(currentStep, maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess, isInstantBookable, false);
    }

    public static boolean canMoveToNextStep(LYSStep currentStep, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess) {
        return isStepComplete(currentStep, maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess) || canSkipStep(currentStep);
    }

    public static LYSStep getFirstIncompleteAndNonSkippableStep(LYSStep currentStep, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess, boolean isInstantBookable) {
        return (LYSStep) FluentIterable.from((Iterable<E>) getOrderedSteps()).filter(LYSStep$$Lambda$1.lambdaFactory$(currentStep)).firstMatch(LYSStep$$Lambda$2.lambdaFactory$(maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess, isInstantBookable)).mo41059or(currentStep);
    }

    public static LYSStep getFirstNonskippableStepFromCurrent(LYSStep currentStep, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess, boolean isInstantBookable) {
        return getFirstNonskippableStep(currentStep, maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess, isInstantBookable, true);
    }

    private static LYSStep getFirstNonskippableStep(LYSStep currentStep, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess, boolean isInstantBookable, boolean fromCurrentStep) {
        return (LYSStep) FluentIterable.from((Iterable<E>) getOrderedSteps()).filter(LYSStep$$Lambda$3.lambdaFactory$(fromCurrentStep, currentStep)).firstMatch(LYSStep$$Lambda$4.lambdaFactory$(listing, listingRegistrationProcess, maxReachedStep, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, isInstantBookable)).mo41059or(Completion);
    }

    public static boolean areAllStepsComplete(LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess) {
        return FluentIterable.from((Iterable<E>) getOrderedSteps()).allMatch(LYSStep$$Lambda$5.lambdaFactory$(maxReachedStep, listing, accountVerifications, isAccountVerificationCompletedOnClient, isIdentityCompletedOnClient, listingRegistrationProcess));
    }

    public static boolean isPhotoStepCompleted(Listing listing) {
        return listing.getPhotos().size() > 0;
    }

    public static String[] getIncompleteVerificationSteps(List<AccountVerification> accountVerifications) {
        return (String[]) FluentIterable.from((Iterable<E>) accountVerifications).filter(LYSStep$$Lambda$6.lambdaFactory$()).filter(LYSStep$$Lambda$7.lambdaFactory$()).transform(LYSStep$$Lambda$8.lambdaFactory$()).toArray(String.class);
    }

    public static List<AccountVerification> getIncompleteIdentitySteps(List<AccountVerification> accountVerifications) {
        return FluentIterable.from((Iterable<E>) accountVerifications).filter(LYSStep$$Lambda$9.lambdaFactory$()).filter(LYSStep$$Lambda$10.lambdaFactory$()).toList();
    }

    public static LYSStep getFirstStepForCollection(LYSCollection collection) {
        return (LYSStep) Check.notNull((LYSStep) getStepsForCollection(collection).first().orNull(), "Collection must have steps");
    }

    public static FluentIterable<LYSStep> getStepsForCollection(LYSCollection lysCollection2) {
        return FluentIterable.from((Iterable<E>) getOrderedSteps()).filter(LYSStep$$Lambda$11.lambdaFactory$(lysCollection2));
    }

    /* access modifiers changed from: private */
    public static boolean isStepComplete(LYSStep step, LYSStep maxReachedStep, Listing listing, List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient, boolean isIdentityCompletedOnClient, ListingRegistrationProcess listingRegistrationProcess) {
        switch (step) {
            case VerificationSteps:
                return isVerificationStepCompleted(accountVerifications, isAccountVerificationCompletedOnClient);
            case Identity:
                return isIdentityStepCompleted(accountVerifications, isIdentityCompletedOnClient);
            case PhotoManager:
                return isPhotoStepCompleted(listing);
            case CityRegistration:
                return isCityRegistrationCompleted(listing, listingRegistrationProcess);
            case SpaceType:
                if (TextUtils.isEmpty(listing.getRoomTypeKey()) || listing.getPropertyTypeId() < 0) {
                    return false;
                }
                return true;
            case RoomsAndGuests:
                if (listing.getBedrooms() < 0 || listing.getBedCount() <= 0 || listing.getPersonCapacity() <= 0) {
                    return false;
                }
                return true;
            case Bathrooms:
                if (listing.getBathrooms() < 0.0f || listing.getBathroomType() == null) {
                    return false;
                }
                return true;
            case DescriptionStep:
                if (TextUtils.isEmpty(listing.getUnscrubbedSummary())) {
                    return false;
                }
                return true;
            case TitleStep:
                return !TextUtils.isEmpty(listing.getUnscrubbedName());
            default:
                return isStepSeen(step, maxReachedStep);
        }
    }

    private static boolean isVerificationStepCompleted(List<AccountVerification> accountVerifications, boolean isAccountVerificationCompletedOnClient) {
        return isAccountVerificationCompletedOnClient || getIncompleteVerificationSteps(accountVerifications).length == 0;
    }

    private static boolean isIdentityStepCompleted(List<AccountVerification> accountVerifications, boolean isIdentityCompletedOnClient) {
        if (Trebuchet.launch(TrebuchetKeys.IDENTITY_FOR_LYS) && !isIdentityCompletedOnClient && !getIncompleteIdentitySteps(accountVerifications).isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean isCityRegistrationCompleted(Listing listing, ListingRegistrationProcess listingRegistrationProcess) {
        return shouldSkipCityRegistration(listing, listingRegistrationProcess) || listingRegistrationProcess.getListingRegistration() != null;
    }

    private static boolean shouldSkipCityRegistration(Listing listing, ListingRegistrationProcess listingRegistrationProcess) {
        return listingRegistrationProcess == null || !listingRegistrationProcess.isRegulatoryBodySupported();
    }

    private static boolean isStepSeen(LYSStep step, LYSStep maxStepReached) {
        return maxStepReached == step || maxStepReached.isAfter(step);
    }

    private static boolean canSkipStep(LYSStep step) {
        return step == PhotoManager;
    }

    private static boolean shouldSkipIfComplete(LYSStep step, Listing listing, ListingRegistrationProcess listingRegistrationProcess) {
        switch (step) {
            case VerificationSteps:
            case Identity:
                return true;
            case CityRegistration:
                return shouldSkipCityRegistration(listing, listingRegistrationProcess);
            case Photos:
                return isPhotoStepCompleted(listing);
            default:
                return false;
        }
    }

    private static boolean shouldSkipIfNotApplicable(LYSStep step, Listing listing, boolean isInstantBookable) {
        boolean z = false;
        switch (step) {
            case SelectPricingType:
                if (PricingFeatureToggles.showSmartPricing(listing)) {
                    return false;
                }
                return true;
            case BedDetails:
                if (FeatureToggles.showHostSideBedDetails()) {
                    return false;
                }
                return true;
            case NewHostDiscount:
                if (!isInstantBookable || !PricingFeatureToggles.showNewHostPromoLYS()) {
                    z = true;
                }
                return z;
            default:
                return false;
        }
    }

    private static List<LYSStep> getOrderedSteps() {
        return LYSStepOrderUtil.getOrderedSteps();
    }
}
