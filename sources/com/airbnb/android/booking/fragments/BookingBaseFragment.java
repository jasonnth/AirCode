package com.airbnb.android.booking.fragments;

import android.support.design.widget.Snackbar;
import com.airbnb.android.booking.activities.BookingActivityFacade;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.Check;
import java.util.List;

public class BookingBaseFragment extends AirFragment implements UpdateRequestListener {
    protected static final String ARG_RESERVATION = "arg_reservation";
    protected Snackbar snackbar;

    /* access modifiers changed from: protected */
    public Reservation getReservation() {
        return getBookingActivity().getReservation();
    }

    /* access modifiers changed from: protected */
    public boolean hasReservation() {
        return getBookingActivity().hasReservation();
    }

    /* access modifiers changed from: protected */
    public Listing getListing() {
        return getBookingActivity().getListing();
    }

    /* access modifiers changed from: protected */
    public ReservationDetails getReservationDetails() {
        return getBookingActivity().getReservationDetails();
    }

    /* access modifiers changed from: protected */
    public List<OldPaymentInstrument> getPaymentInstruments() {
        return getBookingActivity().getPaymentInstruments();
    }

    public BookingActivityFacade getBookingActivity() {
        return (BookingActivityFacade) Check.notNull(getActivity());
    }

    /* access modifiers changed from: protected */
    public PaymentManagerFragment getPaymentManagerFragment() {
        return getBookingActivity().getPaymentManagerFragment();
    }

    public void onDetach() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        super.onDetach();
    }

    public void onReservationUpdate() {
    }

    public void onUpdateError(String errorMessage) {
    }

    public void onUpdateStarted() {
    }

    public void onUpdated() {
        onReservationUpdate();
    }

    public String getMobileSearchSessionId() {
        return getBookingActivity().getMobileSearchSessionId();
    }
}
