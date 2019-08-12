package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.paidamenities.activities.GuestPendingAmenityActivity;
import com.airbnb.android.lib.paidamenities.activities.HostPendingAmenityActivity;
import icepick.State;

public abstract class BasePendingAmenityFragment extends AirFragment {
    protected static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    protected static final String ARG_PAID_AMENITY_ORDER = "paid_amenity_order";
    protected static final String ARG_USER_MODE = "user_mode";
    @State
    protected String confirmationCode;
    PaidAmenityJitneyLogger paidAmenityJitneyLogger;
    @State
    protected UserMode userMode;

    public enum UserMode {
        Host,
        Guest
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public GuestPendingAmenityActivity getGuestPendingAmenityActivity() {
        return (GuestPendingAmenityActivity) getActivity();
    }

    public HostPendingAmenityActivity getHostPendingAmenityActivity() {
        return (HostPendingAmenityActivity) getActivity();
    }

    public void goToPendingAmenityOrderDetail(PaidAmenityOrder paidAmenityOrder) {
        switch (this.userMode) {
            case Guest:
                getGuestPendingAmenityActivity().goToPendingAmenityOrderDetail(paidAmenityOrder);
                return;
            case Host:
                getHostPendingAmenityActivity().goToPendingAmenityOrderDetail(paidAmenityOrder);
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("User mode has to be either Guest or Host"));
                return;
        }
    }

    public void updatePaidAmenityOrderStatus() {
    }
}
