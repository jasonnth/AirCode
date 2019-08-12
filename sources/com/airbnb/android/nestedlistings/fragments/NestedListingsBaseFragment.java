package com.airbnb.android.nestedlistings.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.nestedlistings.NestedListingsActivity;
import com.airbnb.android.nestedlistings.NestedListingsController;
import com.airbnb.android.utils.KeyboardUtils;

public abstract class NestedListingsBaseFragment extends AirFragment {
    protected NestedListingsController controller;

    /* access modifiers changed from: protected */
    public abstract boolean canSaveChanges();

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.argument(context instanceof NestedListingsActivity);
        this.controller = (NestedListingsController) context;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
