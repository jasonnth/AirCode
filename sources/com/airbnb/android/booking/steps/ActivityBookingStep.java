package com.airbnb.android.booking.steps;

import android.content.Intent;

public interface ActivityBookingStep extends BookingStep {
    int getRequestCode();

    void onActivityResult(int i, Intent intent);
}
