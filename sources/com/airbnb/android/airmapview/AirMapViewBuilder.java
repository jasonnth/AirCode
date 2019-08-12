package com.airbnb.android.airmapview;

import com.airbnb.android.airmapview.AirMapInterface;

public interface AirMapViewBuilder<T extends AirMapInterface, Q> {
    T build();

    AirMapViewBuilder<T, Q> withOptions(Q q);
}
