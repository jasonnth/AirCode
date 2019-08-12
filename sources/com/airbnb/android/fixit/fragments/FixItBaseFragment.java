package com.airbnb.android.fixit.fragments;

import android.content.Context;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.fixit.activities.FixItReportActivity;

public class FixItBaseFragment extends AirFragment implements UpdateListener {
    protected FixItReportActivity activity;
    protected FixItDataController dataController;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FixItReportActivity) context;
        this.dataController = this.activity.getDataController();
    }

    public void onDetach() {
        this.activity = null;
        this.dataController = null;
        super.onDetach();
    }

    public void onResume() {
        super.onResume();
        this.dataController.addListener(this);
    }

    public void onPause() {
        this.dataController.removeListener(this);
        super.onPause();
    }

    public void dataUpdated() {
    }
}
