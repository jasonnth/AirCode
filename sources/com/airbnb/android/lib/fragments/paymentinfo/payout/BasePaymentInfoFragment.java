package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.activities.PaymentInfoActivity;
import com.airbnb.android.lib.controller.PaymentInfoNavigationController;

public class BasePaymentInfoFragment extends AirFragment {
    public PaymentInfoActivity getPaymentInfoActivity() {
        Check.state(getActivity() instanceof PaymentInfoActivity);
        return (PaymentInfoActivity) getActivity();
    }

    public PaymentInfoNavigationController getNavigationController() {
        return getPaymentInfoActivity().getNavigationController();
    }
}
