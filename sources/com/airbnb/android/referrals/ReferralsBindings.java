package com.airbnb.android.referrals;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.referrals.ReferralsComponent.Builder;
import javax.inject.Provider;

public interface ReferralsBindings extends GraphBindings {
    Provider<Builder> referralsComponentProvider();
}
