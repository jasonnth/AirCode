package com.airbnb.android.guestrecovery;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder;
import javax.inject.Provider;

public interface GuestRecoveryBindings extends GraphBindings {
    Provider<Builder> guestRecoveryComponentProvider();
}
