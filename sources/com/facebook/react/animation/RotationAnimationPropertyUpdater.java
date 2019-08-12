package com.facebook.react.animation;

import android.view.View;

public class RotationAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public RotationAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    /* access modifiers changed from: protected */
    public float getProperty(View view) {
        return view.getRotation();
    }

    /* access modifiers changed from: protected */
    public void setProperty(View view, float propertyValue) {
        view.setRotation((float) Math.toDegrees((double) propertyValue));
    }
}
