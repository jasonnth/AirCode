package com.airbnb.android.core;

import com.airbnb.android.utils.Strap;

public final class AnalyticsRegistry {
    private final Strap registry = Strap.make();

    public Strap getRegistry() {
        return this.registry;
    }
}
