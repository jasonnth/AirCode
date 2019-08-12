package com.airbnb.paris;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

/* compiled from: TypedArrayWrapper.kt */
public interface TypedArrayWrapper {
    boolean getBoolean(int i, boolean z);

    int getColor(int i, int i2);

    ColorStateList getColorStateList(int i);

    int getDimensionPixelSize(int i, int i2);

    Drawable getDrawable(int i);

    float getFloat(int i, float f);

    int getInt(int i, int i2);

    int getLayoutDimension(int i, int i2);

    int getResourceId(int i, int i2);

    String getString(int i);

    CharSequence getText(int i);

    boolean hasValue(int i);

    boolean isNull(int i);

    void recycle();
}
