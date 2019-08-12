package com.airbnb.android.host_referrals;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.host_referrals.fragments.HostReferralsFragment;

public interface HostReferralsGraph extends BaseGraph {
    void inject(HostReferralsFragment hostReferralsFragment);
}
