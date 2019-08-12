package com.airbnb.android.lib.activities.booking;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.lib.activities.booking.BookingActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity$$Icepick<T extends BookingActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9486H = new Helper("com.airbnb.android.lib.activities.booking.BookingActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9486H.getParcelable(state, "reservation");
            target.listing = (Listing) f9486H.getParcelable(state, "listing");
            target.reservationDetails = (ReservationDetails) f9486H.getParcelable(state, "reservationDetails");
            target.paymentInstruments = (ArrayList) f9486H.getSerializable(state, "paymentInstruments");
            target.mobileSearchSessionId = f9486H.getString(state, "mobileSearchSessionId");
            target.hostMessage = f9486H.getString(state, "hostMessage");
            target.inquiryMessage = f9486H.getString(state, "inquiryMessage");
            target.changeListeners = (ArrayList) f9486H.getSerializable(state, "changeListeners");
            target.verificationFlow = (VerificationFlow) f9486H.getSerializable(state, "verificationFlow");
            target.firstVerificationStep = f9486H.getString(state, "firstVerificationStep");
            target.incompleteVerifications = f9486H.getParcelableArrayList(state, "incompleteVerifications");
            target.phoneOrEmailVerification = (AccountVerification) f9486H.getParcelable(state, "phoneOrEmailVerification");
            target.governmentIdResult = (GovernmentIdResult) f9486H.getParcelable(state, "governmentIdResult");
            target.shouldSkipMessageHostFragment = f9486H.getBoolean(state, "shouldSkipMessageHostFragment");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9486H.putParcelable(state, "reservation", target.reservation);
        f9486H.putParcelable(state, "listing", target.listing);
        f9486H.putParcelable(state, "reservationDetails", target.reservationDetails);
        f9486H.putSerializable(state, "paymentInstruments", target.paymentInstruments);
        f9486H.putString(state, "mobileSearchSessionId", target.mobileSearchSessionId);
        f9486H.putString(state, "hostMessage", target.hostMessage);
        f9486H.putString(state, "inquiryMessage", target.inquiryMessage);
        f9486H.putSerializable(state, "changeListeners", target.changeListeners);
        f9486H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9486H.putString(state, "firstVerificationStep", target.firstVerificationStep);
        f9486H.putParcelableArrayList(state, "incompleteVerifications", target.incompleteVerifications);
        f9486H.putParcelable(state, "phoneOrEmailVerification", target.phoneOrEmailVerification);
        f9486H.putParcelable(state, "governmentIdResult", target.governmentIdResult);
        f9486H.putBoolean(state, "shouldSkipMessageHostFragment", target.shouldSkipMessageHostFragment);
    }
}
