package com.airbnb.android.utils.animation;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.ViewAnimator;
import com.airbnb.android.utils.animation.FlipAnimation.ScaleUpDownEnum;

public class AnimationFactory {

    public enum FlipDirection {
        LEFT_RIGHT,
        RIGHT_LEFT;

        public float getStartDegreeForFirstView() {
            return 0.0f;
        }

        public float getStartDegreeForSecondView() {
            switch (this) {
                case LEFT_RIGHT:
                    return -90.0f;
                case RIGHT_LEFT:
                    return 90.0f;
                default:
                    return 0.0f;
            }
        }

        public float getEndDegreeForFirstView() {
            switch (this) {
                case LEFT_RIGHT:
                    return 90.0f;
                case RIGHT_LEFT:
                    return -90.0f;
                default:
                    return 0.0f;
            }
        }

        public float getEndDegreeForSecondView() {
            return 0.0f;
        }

        public FlipDirection theOtherDirection() {
            switch (this) {
                case LEFT_RIGHT:
                    return RIGHT_LEFT;
                case RIGHT_LEFT:
                    return LEFT_RIGHT;
                default:
                    return null;
            }
        }
    }

    public static Animation[] flipAnimation(View fromView, FlipDirection dir, long duration, Interpolator interpolator) {
        Interpolator interpolator2;
        Animation[] result = new Animation[2];
        float centerX = ((float) fromView.getWidth()) / 2.0f;
        float centerY = ((float) fromView.getHeight()) / 2.0f;
        Animation outFlip = new FlipAnimation(dir.getStartDegreeForFirstView(), dir.getEndDegreeForFirstView(), centerX, centerY, 0.65f, ScaleUpDownEnum.SCALE_DOWN);
        outFlip.setDuration(duration);
        outFlip.setFillAfter(true);
        if (interpolator == null) {
            interpolator2 = new AccelerateInterpolator();
        } else {
            interpolator2 = interpolator;
        }
        outFlip.setInterpolator(interpolator2);
        AnimationSet outAnimation = new AnimationSet(true);
        outAnimation.addAnimation(outFlip);
        result[0] = outAnimation;
        Animation inFlip = new FlipAnimation(dir.getStartDegreeForSecondView(), dir.getEndDegreeForSecondView(), centerX, centerY, 0.65f, ScaleUpDownEnum.SCALE_UP);
        inFlip.setDuration(duration);
        inFlip.setFillAfter(true);
        if (interpolator == null) {
            interpolator = new AccelerateInterpolator();
        }
        inFlip.setInterpolator(interpolator);
        inFlip.setStartOffset(duration);
        AnimationSet inAnimation = new AnimationSet(true);
        inAnimation.addAnimation(inFlip);
        result[1] = inAnimation;
        return result;
    }

    public static void flipTransition(ViewAnimator viewAnimator, FlipDirection dir, long duration) {
        View fromView = viewAnimator.getCurrentView();
        int currentIndex = viewAnimator.getDisplayedChild();
        if ((currentIndex + 1) % viewAnimator.getChildCount() < currentIndex) {
            dir = dir.theOtherDirection();
        }
        Animation[] animc = flipAnimation(fromView, dir, duration, null);
        viewAnimator.setOutAnimation(animc[0]);
        viewAnimator.setInAnimation(animc[1]);
        viewAnimator.showNext();
    }
}
