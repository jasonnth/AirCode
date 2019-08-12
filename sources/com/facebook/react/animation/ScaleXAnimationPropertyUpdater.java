package com.facebook.react.animation;

import android.view.View;

public class ScaleXAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleXAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    public ScaleXAnimationPropertyUpdater(float fromValue, float toValue) {
        super(fromValue, toValue);
    }

    /* access modifiers changed from: protected */
    public float getProperty(View view) {
        return view.getScaleX();
    }

    /* access modifiers changed from: protected */
    public void setProperty(View view, float propertyValue) {
        view.setScaleX(propertyValue);
    }
}
