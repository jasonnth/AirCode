package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.calendar.AvailabilityController.UnavailabilityType;
import com.airbnb.jitney.event.logging.DateType.p078v1.C1977DateType;
import com.airbnb.jitney.event.logging.Search.p247v2.SearchAvailabilityCalendarDateTapEvent.Builder;
import com.airbnb.jitney.event.logging.UnavailableReason.p277v1.C2775UnavailableReason;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class AvailabilityCalendarJitneyLogger extends BaseLogger {
    private static final Map<UnavailabilityType, C2775UnavailableReason> map = ImmutableMap.builder().put(UnavailabilityType.CantSatisfyMinNights, C2775UnavailableReason.MinNightRequirementCannotSatisty).put(UnavailabilityType.ContainsUnavailableDates, C2775UnavailableReason.ContainUnavailableDays).put(UnavailabilityType.ClosedToArrival, C2775UnavailableReason.ClosedToArrival).put(UnavailabilityType.ClosedToDeparture, C2775UnavailableReason.ClosedToDeparture).put(UnavailabilityType.DoesntSatisfyMinNights, C2775UnavailableReason.MinNightRequirementNotMet).put(UnavailabilityType.DoesntSatisfyMaxNights, C2775UnavailableReason.MaxNightRequirementNotMet).put(UnavailabilityType.SpecificCheckInDate, C2775UnavailableReason.SpecificCheckInDayRequirement).put(UnavailabilityType.UnavailableForCheckIn, C2775UnavailableReason.UnavailableForCheckIn).put(UnavailabilityType.UnavailableForCheckOut, C2775UnavailableReason.UnavailableForCheckOut).build();

    public AvailabilityCalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void trackUnavailableCheckInDateClicked(UnavailabilityType unavailabilityType, boolean popUpShown) {
        trackUnavailableDateClicked(C1977DateType.Checkin, unavailabilityType, popUpShown);
    }

    public void trackUnavailableCheckOutDateClicked(UnavailabilityType unavailabilityType, boolean popUpShown) {
        trackUnavailableDateClicked(C1977DateType.Checkout, unavailabilityType, popUpShown);
    }

    private void trackUnavailableDateClicked(C1977DateType dateType, UnavailabilityType unavailabilityType, boolean popUpShown) {
        Builder builder = new Builder(context(), dateType, Boolean.valueOf(popUpShown));
        C2775UnavailableReason unavailableReason = unavailabilityType == null ? null : (C2775UnavailableReason) map.get(unavailabilityType);
        if (unavailableReason != null) {
            builder.unavailable_reason(unavailableReason);
        }
        publish(builder);
    }
}
