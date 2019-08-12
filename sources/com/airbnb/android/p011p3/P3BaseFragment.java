package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.p009p3.interfaces.OnP3DataChangedListener;

/* renamed from: com.airbnb.android.p3.P3BaseFragment */
public class P3BaseFragment extends AirDialogFragment implements OnP3DataChangedListener {
    protected ListingDetailsController controller;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = (ListingDetailsController) context;
    }

    public void onDetach() {
        this.controller = null;
        super.onDetach();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.controller.registerP3DataChangedListener(this);
    }

    public void onDestroyView() {
        this.controller.unregisterP3DataChangedListener(this);
        if (isRemoving()) {
            onFragmentExited();
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: protected */
    public void onFragmentExited() {
    }

    public void onListingLoaded() {
    }

    public void onPricingQuoteLoaded() {
    }

    public void onStateChanged() {
    }

    public View getViewToUseForSnackbar() {
        return getView();
    }

    public void onIdentityForBookingCompleted() {
    }

    public void onPicturePositionChanged(int position) {
    }

    public boolean isLeafDialog() {
        return true;
    }
}
