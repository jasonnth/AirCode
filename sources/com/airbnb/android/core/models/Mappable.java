package com.airbnb.android.core.models;

import android.os.Parcelable;

public interface Mappable extends Parcelable {
    double getLatitude();

    double getLongitude();

    long getMappableId();
}
