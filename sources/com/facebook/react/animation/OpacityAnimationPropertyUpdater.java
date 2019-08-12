package com.facebook.react.animation;

import android.view.View;

public class OpacityAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public OpacityAnimationPropertyUpdater(float toOpacity) {
        super(toOpacity);
    }

    public OpacityAnimationPropertyUpdater(float fromOpacity, float toOpacity) {
        super(fromOpacity, toOpacity);
    }

    /* access modifiers changed from: protected */
    public float getProperty(View view) {
        return view.getAlpha();
    }

    /* access modifiers changed from: protected */
    public void setProperty(View view, float propertyValue) {
        view.setAlpha(propertyValue);
    }
}
