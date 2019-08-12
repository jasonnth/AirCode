package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.paidamenities.activities.PurchaseAmenityActivity;
import com.airbnb.android.lib.paidamenities.controllers.PurchaseAmenityNavigationController;
import icepick.State;

public abstract class BasePurchaseAmenityFragment extends AirFragment {
    @State
    String confirmationCode;
    @State
    long listingId;
    protected PurchaseAmenityNavigationController navigationController;
    PaidAmenityJitneyLogger paidAmenityJitneyLogger;
    @State
    long threadId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        syncDataWithActivity();
    }

    private void syncDataWithActivity() {
        this.navigationController = getPurchaseAmenityActivity().getNavigationController();
        this.confirmationCode = getPurchaseAmenityActivity().getConfirmationCode();
        this.threadId = getPurchaseAmenityActivity().getThreadId();
        this.listingId = getPurchaseAmenityActivity().getListingId();
    }

    private PurchaseAmenityActivity getPurchaseAmenityActivity() {
        return (PurchaseAmenityActivity) getActivity();
    }
}
