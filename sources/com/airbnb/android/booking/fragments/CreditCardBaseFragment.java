package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.payments.CreditCardDetails;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class CreditCardBaseFragment extends AirFragment {
    QuickPayJitneyLogger quickPayJitneyLogger;
    protected Snackbar snackbar;

    public CreditCardActivity getCreditCardActivity() {
        return (CreditCardActivity) Check.notNull(getActivity());
    }

    public CreditCardDetails getPaymentDetails() {
        return getCreditCardActivity().getCreditCardDetails();
    }

    public ParcelStrap getAnalyticsData() {
        return getCreditCardActivity().getAnalyticsData();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookingGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public void displayError(String errorMessage) {
        if (getAnalyticsData() != null) {
            KonaBookingAnalytics.trackView("payment_options", "errors", getAnalyticsData().mo9945kv("key", -1).mo9946kv("message", errorMessage));
        }
        this.snackbar = new SnackbarWrapper().view(getView()).body(errorMessage).buildAndShow();
    }

    /* access modifiers changed from: protected */
    public void dismissError() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }
}
