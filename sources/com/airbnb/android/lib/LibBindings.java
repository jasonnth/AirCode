package com.airbnb.android.lib;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.lib.LibComponent.Builder;
import com.airbnb.p027n2.N2Component;
import javax.inject.Provider;

public interface LibBindings extends GraphBindings {
    Provider<Builder> libComponentProvider();

    Provider<N2Component.Builder> n2ComponentProvider();
}
