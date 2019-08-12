package com.airbnb.android.checkin.data;

import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.core.apprater.GraphBindings;
import javax.inject.Provider;

public interface CheckInDataBindings extends GraphBindings {
    Provider<Builder> checkInComponentProvider();
}
