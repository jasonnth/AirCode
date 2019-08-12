package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.Listing;

public interface MapMarkerable {
    long getId();

    Listing getListing();

    String getPrice();

    boolean isInstantBookable();
}
