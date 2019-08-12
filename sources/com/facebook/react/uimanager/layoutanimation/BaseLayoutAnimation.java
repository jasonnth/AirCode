package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.facebook.react.uimanager.IllegalViewOperationException;

abstract class BaseLayoutAnimation extends AbstractLayoutAnimation {
    /* access modifiers changed from: 0000 */
    public abstract boolean isReverse();

    BaseLayoutAnimation() {
    }

    /* access modifiers changed from: 0000 */
    public boolean isValid() {
        return this.mDurationMs > 0 && this.mAnimatedProperty != null;
    }

    /* access modifiers changed from: 0000 */
    public Animation createAnimationImpl(View view, int x, int y, int width, int height) {
        float fromValue;
        float toValue;
        if (isReverse()) {
            fromValue = 1.0f;
        } else {
            fromValue = 0.0f;
        }
        if (isReverse()) {
            toValue = 0.0f;
        } else {
            toValue = 1.0f;
        }
        if (this.mAnimatedProperty != null) {
            switch (this.mAnimatedProperty) {
                case OPACITY:
                    return new OpacityAnimation(view, fromValue, toValue);
                case SCALE_XY:
                    return new ScaleAnimation(fromValue, toValue, fromValue, toValue, 1, 0.5f, 1, 0.5f);
                default:
                    throw new IllegalViewOperationException("Missing animation for property : " + this.mAnimatedProperty);
            }
        } else {
            throw new IllegalViewOperationException("Missing animated property from animation config");
        }
    }
}
