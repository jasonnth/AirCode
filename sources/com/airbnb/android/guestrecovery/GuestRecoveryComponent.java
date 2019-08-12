package com.airbnb.android.guestrecovery;

import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment;

@ChildScope
public interface GuestRecoveryComponent {

    public interface Builder {
        GuestRecoveryComponent build();
    }

    void inject(GuestRecoveryFragment guestRecoveryFragment);
}
