package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;

public final class VerifiedIdActivityIntents {
    public static final String RESERVATION_EXTRA = "reservation";
    public static final String VERIFICATIONS_EXTRA = "verifications";

    private VerifiedIdActivityIntents() {
    }

    public static Intent intentForVerifiedId(Context context, VerificationRequirements verifications, Reservation reservation) {
        if (Trebuchet.launch(TrebuchetKeys.LAUNCH_IDENTITY_FLOW)) {
            return AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(context, AccountVerificationStartFragmentArguments.builder().listingId(reservation.getListing().getId()).host(reservation.getHost()).verificationFlow(VerificationFlow.VerifiedIDBookingCheckpoint).build());
        }
        Intent putExtra = new Intent(context, Activities.verifiedId()).putExtra(VERIFICATIONS_EXTRA, verifications).putExtra("reservation", reservation);
        validateReservation(reservation);
        return putExtra;
    }

    public static void validateReservation(Reservation reservation) {
        if (reservation != null && reservation.getListing() == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Reservation does not have a listing: " + reservation.getId()));
        }
        if (reservation != null && reservation.getStartDate() == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Reservation does not have a start date: " + reservation.getId()));
        }
    }

    public static Intent intentForVerifiedId(Context context) {
        if (Trebuchet.launch(TrebuchetKeys.LAUNCH_IDENTITY_FLOW)) {
            return AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(context, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.VerifiedIDNonBooking).build());
        }
        return new Intent(context, Activities.verifiedId());
    }
}
