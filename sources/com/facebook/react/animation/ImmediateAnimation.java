package com.facebook.react.animation;

public class ImmediateAnimation extends Animation {
    public ImmediateAnimation(int animationID, AnimationPropertyUpdater propertyUpdater) {
        super(animationID, propertyUpdater);
    }

    public void run() {
        onUpdate(1.0f);
        finish();
    }
}
