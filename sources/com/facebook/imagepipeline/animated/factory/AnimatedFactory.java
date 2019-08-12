package com.facebook.imagepipeline.animated.factory;

import android.content.Context;

public interface AnimatedFactory {
    AnimatedDrawableFactory getAnimatedDrawableFactory(Context context);

    AnimatedImageFactory getAnimatedImageFactory();
}
