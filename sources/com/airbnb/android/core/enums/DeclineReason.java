package com.airbnb.android.core.enums;

import android.content.Context;
import com.airbnb.android.core.C0716R;

public enum DeclineReason {
    UnavailableDates("dates_not_available", C0716R.string.ro_response_decline_because_of_unavialable_dates, true, false),
    ListingNotAFit("not_a_good_fit", C0716R.string.ro_response_decline_because_of_listing, false, true),
    ReservationDetailsNotAFit("waiting_for_better_reservation", C0716R.string.ro_response_decline_because_of_request, false, false),
    GuestIsNotAFit("not_comfortable", C0716R.string.ro_response_decline_because_of_guest, false, true);
    
    public final boolean isPrivateMessageNeeded;
    private final int labelRes;
    private final boolean requiresDatesRange;
    public final String serverKey;

    private DeclineReason(String serverKey2, int res, boolean requiresDatesRange2, boolean isPrivateMessageNeeded2) {
        this.labelRes = res;
        this.serverKey = serverKey2;
        this.requiresDatesRange = requiresDatesRange2;
        this.isPrivateMessageNeeded = isPrivateMessageNeeded2;
    }

    public String getConfirmationMessage(Context context, String from, String to, String guestName) {
        switch (this) {
            case UnavailableDates:
                return context.getString(C0716R.string.ro_decline_confirmation_dates, new Object[]{from, to});
            case ReservationDetailsNotAFit:
                return context.getString(C0716R.string.ro_decline_confirmation_reservation, new Object[]{from, to});
            default:
                return context.getString(C0716R.string.ro_decline_confirmation_general, new Object[]{guestName});
        }
    }

    public boolean requiresBlockingDates() {
        return this == UnavailableDates || this == ReservationDetailsNotAFit;
    }

    public String getTitle(Context context, String checkIn, String checkOut) {
        if (!this.requiresDatesRange) {
            return context.getString(this.labelRes);
        }
        return context.getString(this.labelRes, new Object[]{checkIn, checkOut});
    }
}
