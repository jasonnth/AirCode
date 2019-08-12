package com.airbnb.android.booking.steps;

import android.os.Bundle;

public interface BookingStep {
    boolean exclude();

    boolean initialized();

    void onReservationLoaded();

    void onSaveInstanceState(Bundle bundle);

    void restoreInstanceState(Bundle bundle);

    void show(boolean z);
}
