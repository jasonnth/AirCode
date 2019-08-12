package com.airbnb.android.cityregistration.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import icepick.State;

public abstract class CityRegistrationBaseFragment extends AirFragment {
    protected CityRegistrationController controller;
    @State
    Listing listing;
    @State
    ListingRegistrationProcess listingRegistrationProcess;
    MenuItem saveAndExitMenuItem;

    /* access modifiers changed from: protected */
    public abstract boolean canSaveChanges();

    /* access modifiers changed from: protected */
    public abstract boolean onSaveActionPressed();

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = (CityRegistrationController) getActivity();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(this.controller.isLYS());
        if (savedInstanceState == null) {
            this.listingRegistrationProcess = this.controller.getListingRegistrationProcess();
            this.listing = this.controller.getListing();
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroyView() {
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
        getAirActivity().setOnBackPressedListener(null);
        super.onDestroyView();
    }

    public void onDetach() {
        super.onDetach();
        this.controller = null;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (canSaveChanges()) {
            inflater.inflate(C5630R.C5633menu.fragment_save_and_exit, menu);
            this.saveAndExitMenuItem = menu.findItem(C5630R.C5632id.done);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5630R.C5632id.done) {
            return super.onOptionsItemSelected(item);
        }
        if (onSaveActionPressed()) {
            this.controller.finishSaveAndExit();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public long getListingId() {
        return this.listing.getId();
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11638kv("listing_id", getListingId());
    }
}
