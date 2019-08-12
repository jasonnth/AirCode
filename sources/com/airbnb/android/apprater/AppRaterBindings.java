package com.airbnb.android.apprater;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.lib.AppRaterComponent.Builder;
import javax.inject.Provider;

public interface AppRaterBindings extends GraphBindings {
    Provider<Builder> appRaterComponentProvider();
}
