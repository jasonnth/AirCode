package com.airbnb.android.lib.paidamenities.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.controllers.NavigationController;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.TextEntryFragment;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityDetailsFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityPriceFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.CreateAmenityLandingFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.CreatedAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectAmenityTypeFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectListingFragment;
import com.google.common.collect.Lists;
import java.util.List;

public class CreateAmenityNavigationController extends NavigationController {
    private CreateAmenityActivity createAmenityActivity;

    public CreateAmenityNavigationController(Activity activity, FragmentManager fragmentManager, Bundle savedState) {
        super(activity, fragmentManager);
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.createAmenityActivity = (CreateAmenityActivity) activity;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void initializeFlow() {
        transitionTo(CreateAmenityLandingFragment.newInstance());
    }

    public void displayAddDescription(String defaultText, String hintText) {
        transitionTo(TextEntryFragment.instanceForText(Integer.valueOf(C0880R.string.paid_amenities_add_details_add_description), defaultText, hintText));
    }

    public void doneWithAddDescription() {
        this.fragmentManager.popBackStackImmediate();
    }

    public void doneWithAddAmenityPrice() {
        transitionTo(CreatedAmenityFragment.newInstance());
    }

    public void doneCreatingAmenity() {
        this.createAmenityActivity.doneWithAmenityCreation();
    }

    public void doneWithSelectListing() {
        transitionTo(SelectAmenityTypeFragment.newInstance());
    }

    public void doneWithFetchListings(List<Listing> listings) {
        transitionTo(SelectListingFragment.instanceForListings(Lists.newArrayList((Iterable<? extends E>) listings)));
    }

    public void doneWithAddAmenityDetails() {
        if (this.createAmenityActivity.getPaidAmenityDetails().paidAmenityType().isFreeOnly()) {
            doneWithAddAmenityPrice();
        } else {
            transitionTo(AddAmenityPriceFragment.newInstance());
        }
    }

    public void doneWithSelectAmenityType(PaidAmenityCategory seletedCategory) {
        transitionTo(AddAmenityDetailsFragment.newInstance(seletedCategory));
    }
}
