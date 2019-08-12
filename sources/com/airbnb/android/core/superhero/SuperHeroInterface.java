package com.airbnb.android.core.superhero;

import android.support.p000v4.app.Fragment;

public interface SuperHeroInterface {
    void dismiss();

    SuperHeroInterfaceState getState();

    void presentFull();

    void show(Fragment fragment);
}
