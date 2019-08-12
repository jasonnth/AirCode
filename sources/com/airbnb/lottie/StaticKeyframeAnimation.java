package com.airbnb.lottie;

import java.util.Collections;

class StaticKeyframeAnimation<T> extends KeyframeAnimation<T> {
    private final T initialValue;

    StaticKeyframeAnimation(T initialValue2) {
        super(Collections.emptyList());
        this.initialValue = initialValue2;
    }

    public void setProgress(float progress) {
    }

    public T getValue() {
        return this.initialValue;
    }

    public T getValue(Keyframe<T> keyframe, float keyframeProgress) {
        return this.initialValue;
    }
}
