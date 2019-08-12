package com.airbnb.android.lib.paidamenities.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.controllers.NavigationController;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityItemDetailFragment;
import com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityLandingFragment;
import com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenitySuccessFragment;
import com.airbnb.android.lib.paidamenities.fragments.purchase.RequestAmenityFragment;
import com.google.common.collect.Lists;
import java.util.List;

public class PurchaseAmenityNavigationController extends NavigationController {
    public PurchaseAmenityNavigationController(Activity activity, FragmentManager fragmentManager, Bundle savedState) {
        super(activity, fragmentManager);
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void initializeFlow() {
        transitionTo(PurchaseAmenityLandingFragment.newInstance(), PurchaseAmenityLandingFragment.TAG);
    }

    public void doneWithFetchingAmenities(List<PaidAmenity> paidAmenities) {
        transitionTo(RequestAmenityFragment.newInstanceForAmenities(Lists.newArrayList((Iterable<? extends E>) paidAmenities)), RequestAmenityFragment.TAG);
    }

    public void doneWithChooseAmenityToRequest(PaidAmenity paidAmenity) {
        transitionTo(PurchaseAmenityItemDetailFragment.newInstance(paidAmenity), PurchaseAmenityItemDetailFragment.TAG);
    }

    public void displayRequestAnotherService() {
        this.fragmentManager.popBackStackImmediate(PurchaseAmenityItemDetailFragment.TAG, 1);
    }

    public void doneWithEditAmenityDetail() {
        transitionTo(PurchaseAmenitySuccessFragment.newInstance());
    }

    public void doneWithPurchaseAmenity() {
        this.activity.finish();
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }
}
