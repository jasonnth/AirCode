package com.facebook.react.animation;

import android.view.View;

public class ScaleYAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleYAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    public ScaleYAnimationPropertyUpdater(float fromValue, float toValue) {
        super(fromValue, toValue);
    }

    /* access modifiers changed from: protected */
    public float getProperty(View view) {
        return view.getScaleY();
    }

    /* access modifiers changed from: protected */
    public void setProperty(View view, float propertyValue) {
        view.setScaleY(propertyValue);
    }
}
