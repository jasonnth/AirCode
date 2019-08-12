package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.TextEntryFragment.TextEntryCallback;
import com.airbnb.android.lib.paidamenities.controllers.CreateAmenityNavigationController;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityDetailsFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityPriceFragment.Listener;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectAmenityTypeFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectListingFragment;
import com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails;
import icepick.State;

public class CreateAmenityActivity extends AirActivity implements TextEntryCallback, Listener, SelectAmenityTypeFragment.Listener, SelectListingFragment.Listener {
    @State
    Flow flow;
    private CreateAmenityNavigationController navigationController;
    @State
    PaidAmenityDetails paidAmenityDetails;

    public enum Flow {
        Normal,
        WithListingSelected
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        this.navigationController = new CreateAmenityNavigationController(this, getSupportFragmentManager(), savedInstanceState);
        if (savedInstanceState == null) {
            if (getIntent().hasExtra("listing_id")) {
                long listingId = getIntent().getLongExtra("listing_id", -1);
                Check.state(listingId != -1);
                this.paidAmenityDetails = PaidAmenityDetails.builder().setListingId(Long.valueOf(listingId)).setCurrency(this.currencyFormatter.getLocalCurrencyString()).build();
                this.flow = Flow.WithListingSelected;
            } else {
                this.paidAmenityDetails = PaidAmenityDetails.builder().setCurrency(this.currencyFormatter.getLocalCurrencyString()).build();
                this.flow = Flow.Normal;
            }
            this.navigationController.initializeFlow();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.navigationController.onSaveInstanceState(outState);
    }

    public CreateAmenityNavigationController getNavigationController() {
        return this.navigationController;
    }

    public void onSaveText(String savedText) {
        this.paidAmenityDetails = this.paidAmenityDetails.toBuilder().setDescription(savedText).build();
        this.navigationController.doneWithAddDescription();
        ((AddAmenityDetailsFragment) this.navigationController.getCurrentFragment()).updateDescription(this.paidAmenityDetails.description());
    }

    private void setPaidAmenityPrice(boolean isComplimentary, int price) {
        this.paidAmenityDetails = this.paidAmenityDetails.toBuilder().setIsComplementary(Boolean.valueOf(isComplimentary)).setPrice(Integer.valueOf(price)).build();
    }

    public void onPriceEntered(boolean isComplimentary, int price) {
        setPaidAmenityPrice(isComplimentary, price);
        this.navigationController.doneWithAddAmenityPrice();
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onSelectAmenityType(PaidAmenityCategory paidAmenityCategory) {
        this.paidAmenityDetails = this.paidAmenityDetails.toBuilder().setPaidAmenityType(paidAmenityCategory).setTitle(getResources().getString(paidAmenityCategory.getAmenityServerType().getTitleRes())).build();
        if (paidAmenityCategory.isFreeOnly()) {
            setPaidAmenityPrice(true, 0);
        }
        this.navigationController.doneWithSelectAmenityType(paidAmenityCategory);
    }

    public void onSelectListing(Listing listing) {
        this.paidAmenityDetails = this.paidAmenityDetails.toBuilder().setListingId(Long.valueOf(listing.getId())).build();
        this.navigationController.doneWithSelectListing();
    }

    public void doneWithAmenityCreation() {
        setResult(-1);
        finish();
    }

    public PaidAmenityDetails getPaidAmenityDetails() {
        return this.paidAmenityDetails;
    }

    public Flow getFlow() {
        return this.flow;
    }
}
