package com.airbnb.p027n2.utils;

import android.os.Parcelable;

/* renamed from: com.airbnb.n2.utils.AccessibleDrawableResource */
public abstract class AccessibleDrawableResource implements Parcelable {
    public abstract String contentDescription();

    public abstract int drawableResource();

    public static AccessibleDrawableResource create(int drawableRes, String contentDescription) {
        return new AutoValue_AccessibleDrawableResource(drawableRes, contentDescription);
    }
}
