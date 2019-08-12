package com.airbnb.android.listing.constants;

import com.airbnb.android.listing.LYSStep;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class LYSStepOrderUtil {
    private static final List<LYSStep> ORDERED_STEPS_SUPERSET = ImmutableList.m1293of(LYSStep.SpaceType, LYSStep.RoomsAndGuests, LYSStep.BedDetails, LYSStep.Bathrooms, LYSStep.Address, LYSStep.ExactLocation, LYSStep.Amenities, LYSStep.Spaces, LYSStep.Photos, LYSStep.PhotoManager, LYSStep.DescriptionStep, LYSStep.TitleStep, LYSStep.VerificationSteps, LYSStep.GuestRequirementsStep, LYSStep.HouseRules, LYSStep.HowGuestsBookStep, LYSStep.RentHistoryStep, LYSStep.HostingFrequencyStep, LYSStep.AvailabilityStep, LYSStep.CalendarStep, LYSStep.SelectPricingType, LYSStep.SetPrice, LYSStep.NewHostDiscount, LYSStep.Discounts, LYSStep.ReviewSettings, LYSStep.LocalLaws, LYSStep.CityRegistration, LYSStep.Identity, LYSStep.Completion);

    public static List<LYSStep> getOrderedSteps() {
        return ORDERED_STEPS_SUPERSET;
    }

    public static List<LYSStep> getOrderedStepsWithoutCompletion() {
        return FluentIterable.from((Iterable<E>) getOrderedSteps()).filter(LYSStepOrderUtil$$Lambda$1.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getOrderedStepsWithoutCompletion$0(LYSStep step) {
        return step != LYSStep.Completion;
    }
}
