package com.airbnb.android.core.controllers;

import dagger.internal.Factory;

public final class BottomBarController_Factory implements Factory<BottomBarController> {
    private static final BottomBarController_Factory INSTANCE = new BottomBarController_Factory();

    public BottomBarController get() {
        return new BottomBarController();
    }

    public static Factory<BottomBarController> create() {
        return INSTANCE;
    }
}
