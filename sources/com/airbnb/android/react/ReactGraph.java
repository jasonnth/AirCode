package com.airbnb.android.react;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.react.AirReactInstanceManager;

public interface ReactGraph extends BaseGraph {
    void inject(ReactNativeActivity reactNativeActivity);

    void inject(ReactNativeFragment reactNativeFragment);

    AirReactInstanceManager reactInstanceManager();
}
