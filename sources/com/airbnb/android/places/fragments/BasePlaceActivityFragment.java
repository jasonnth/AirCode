package com.airbnb.android.places.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController.ResyControllerProvider;
import com.airbnb.android.places.activities.PlaceActivityPDPActivity;
import com.airbnb.android.places.activities.PlaceActivityPDPActivity.PlaceActivityPDPController;

public class BasePlaceActivityFragment extends AirFragment {
    protected PlaceActivityPDPController activityController;
    protected ResyController resyController;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activityController = ((PlaceActivityPDPActivity) getAirActivity()).getActivityController();
        this.resyController = ((ResyControllerProvider) getActivity()).getResyController();
    }
}
