package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.booking.activities.AlipayActivity;
import com.airbnb.android.booking.controller.AlipayNavigationController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;

public class BaseAlipayFragment extends AirFragment {
    QuickPayJitneyLogger quickPayJitneyLogger;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookingGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public AlipayActivity getAlipayActivity() {
        Check.state(getActivity() instanceof AlipayActivity);
        return (AlipayActivity) getActivity();
    }

    public AlipayNavigationController getNavigationController() {
        return getAlipayActivity().getNavigationController();
    }

    public ParcelStrap getAnalyticsData() {
        return getAlipayActivity().getAnalyticsData();
    }
}
