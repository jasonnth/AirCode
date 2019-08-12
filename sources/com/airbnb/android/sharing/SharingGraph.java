package com.airbnb.android.sharing;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.sharing.shareables.Shareable;

public interface SharingGraph extends BaseGraph {
    void inject(SharingManager sharingManager);

    void inject(Shareable shareable);
}
