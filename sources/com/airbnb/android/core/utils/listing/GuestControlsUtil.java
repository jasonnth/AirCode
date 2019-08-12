package com.airbnb.android.core.utils.listing;

import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.core.models.GuestControls;
import com.google.common.collect.FluentIterable;

public class GuestControlsUtil {
    public static boolean controlsHaveBeenUpdated(GuestControls updatedControls, GuestControls previousControls) {
        if (updatedControls == null || previousControls == null) {
            return false;
        }
        return FluentIterable.from((E[]) GuestControlType.values()).firstMatch(GuestControlsUtil$$Lambda$1.lambdaFactory$(updatedControls, previousControls)).isPresent();
    }

    static /* synthetic */ boolean lambda$controlsHaveBeenUpdated$0(GuestControls updatedControls, GuestControls previousControls, GuestControlType type) {
        return updatedControls.isGuestControlTypeAllowed(type) != previousControls.isGuestControlTypeAllowed(type);
    }
}
