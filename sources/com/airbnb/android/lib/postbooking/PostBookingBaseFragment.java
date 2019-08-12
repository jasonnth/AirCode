package com.airbnb.android.lib.postbooking;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.fragments.AirFragment;

public abstract class PostBookingBaseFragment extends AirFragment {
    protected PostBookingFlowController postBookingFlowController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PostBookingFlowController) {
            this.postBookingFlowController = (PostBookingFlowController) context;
            return;
        }
        throw new IllegalStateException("PostBookingBaseFragment must attach to an postBookingFlowController");
    }

    public void onDetach() {
        super.onDetach();
        this.postBookingFlowController = null;
    }
}
