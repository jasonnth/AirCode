package com.airbnb.lottie;

import java.util.List;

public abstract class KeyframeAnimation<T> extends BaseKeyframeAnimation<T, T> {
    public /* bridge */ /* synthetic */ Object getValue() {
        return super.getValue();
    }

    KeyframeAnimation(List<? extends Keyframe<T>> keyframes) {
        super(keyframes);
    }
}
