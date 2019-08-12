package com.airbnb.android.cohosting.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.cohosting.activities.CohostLeadsCenterActivity;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController.UpdateListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.utils.KeyboardUtils;

public class CohostLeadsCenterBaseFragment extends AirFragment implements UpdateListener {
    protected CohostLeadsCenterDataController controller;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = ((CohostLeadsCenterActivity) getActivity()).getCohostLeadsCenterController();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        this.controller.addListener(this);
    }

    public void onPause() {
        super.onPause();
        this.controller.removeListener(this);
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

    public void dataLoading(boolean loading) {
    }

    public void dataUpdated() {
    }
}
