package com.airbnb.android.identity;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.identity.IdentityComponent.Builder;
import javax.inject.Provider;

public interface IdentityBindings extends GraphBindings {
    Provider<Builder> identityComponentProvider();
}
