package com.airbnb.android.lib.cancellation;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.fragments.AirFragment;
import icepick.State;

public class DLSCancelReservationBaseFragment extends AirFragment {
    public static final String ARG_CANCELLATION_DATA = "arg_cancellation_data";
    protected DLSCancelReservationActivity cancelActivity;
    @State
    protected CancellationData cancellationData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cancellationData = (CancellationData) getArguments().getParcelable(ARG_CANCELLATION_DATA);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DLSCancelReservationActivity) {
            this.cancelActivity = (DLSCancelReservationActivity) context;
            return;
        }
        throw new IllegalStateException("DLSCancelReservationBaseFragment must attach to a DLSCancelReservationActivity");
    }

    public void onDetach() {
        super.onDetach();
        this.cancelActivity = null;
    }
}
