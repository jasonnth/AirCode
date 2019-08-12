package com.airbnb.android.cohosting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity;
import com.airbnb.android.cohosting.controllers.CohostInvitationDataController;
import com.airbnb.android.cohosting.controllers.CohostInvitationDataController.UpdateListener;
import com.airbnb.android.core.fragments.AirFragment;

public abstract class CohostInvitationBaseFragment extends AirFragment implements UpdateListener {
    protected CohostInvitationDataController controller;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = ((AcceptCohostInvitationActivity) getActivity()).getCohostInvitationDataController();
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
