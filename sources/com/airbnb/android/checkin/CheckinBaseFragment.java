package com.airbnb.android.checkin;

import android.content.Context;
import com.airbnb.android.core.fragments.AirFragment;

public class CheckinBaseFragment extends AirFragment {
    protected ViewCheckinActivity controller;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = (ViewCheckinActivity) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        this.controller = null;
    }
}
