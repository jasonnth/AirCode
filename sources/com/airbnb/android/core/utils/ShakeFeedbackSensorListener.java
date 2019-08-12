package com.airbnb.android.core.utils;

import android.support.p000v4.app.FragmentActivity;

public interface ShakeFeedbackSensorListener {
    boolean isEnabled();

    void onPause();

    void onResume(FragmentActivity fragmentActivity);

    void setEnabled(boolean z);

    void showFeedbackDialog(FragmentActivity fragmentActivity);
}
