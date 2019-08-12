package com.airbnb.android.p011p3;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.p011p3.P3Component.Builder;
import javax.inject.Provider;

/* renamed from: com.airbnb.android.p3.P3Bindings */
public interface P3Bindings extends GraphBindings {
    Provider<Builder> p3ComponentProvider();
}
