package com.airbnb.android.core.fragments;

import com.airbnb.android.utils.Strap;

public interface NavigationLoggingElement {
    Strap getNavigationTrackingParams();

    NavigationTag getNavigationTrackingTag();
}
