package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;

class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    LayoutUpdateAnimation() {
    }

    /* access modifiers changed from: 0000 */
    public boolean isValid() {
        return this.mDurationMs > 0;
    }

    /* access modifiers changed from: 0000 */
    public Animation createAnimationImpl(View view, int x, int y, int width, int height) {
        boolean animateLocation;
        boolean animateSize;
        if (view.getX() == ((float) x) && view.getY() == ((float) y)) {
            animateLocation = false;
        } else {
            animateLocation = true;
        }
        if (view.getWidth() == width && view.getHeight() == height) {
            animateSize = false;
        } else {
            animateSize = true;
        }
        if (!animateLocation && !animateSize) {
            return null;
        }
        if (!animateLocation || !animateSize) {
        }
        return new PositionAndSizeAnimation(view, x, y, width, height);
    }
}
