package com.facebook.react.flat;

import android.content.Context;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.react.bridge.ReadableArray;

interface DrawImage extends AttachDetachListener {
    int getBorderColor();

    float getBorderRadius();

    float getBorderWidth();

    ScaleType getScaleType();

    boolean hasImageRequest();

    void setBorderColor(int i);

    void setBorderRadius(float f);

    void setBorderWidth(float f);

    void setFadeDuration(int i);

    void setProgressiveRenderingEnabled(boolean z);

    void setReactTag(int i);

    void setScaleType(ScaleType scaleType);

    void setSource(Context context, ReadableArray readableArray);

    void setTintColor(int i);
}
