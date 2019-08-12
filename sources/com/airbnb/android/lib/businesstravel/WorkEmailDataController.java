package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;

public interface WorkEmailDataController {
    WorkEmailLaunchSource getLaunchSource();

    String getReservationConfirmationCode();
}
