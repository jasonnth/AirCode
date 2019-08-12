package com.airbnb.android.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;

public class AnimationUtils {
    private static final int DEFAULT_FADE_DURATION_MILLIS = 500;

    public static void fadeInOut(View fadeIn, View fadeOut) {
        fadeInOut(fadeIn, fadeOut, 500, false);
    }

    private static void fadeInOut(View fadeIn, final View fadeOut, int duration, final boolean setOutViewToGone) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(fadeOut, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration((long) duration);
        set.playTogether(new Animator[]{fadeOutAnimator, ObjectAnimator.ofFloat(fadeIn, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration((long) duration)});
        fadeOutAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                fadeOut.setVisibility(setOutViewToGone ? 8 : 4);
            }
        });
        fadeIn.setVisibility(0);
        set.start();
    }

    public static void fadeIn(View fadeIn) {
        fadeIn(fadeIn, 500);
    }

    public static void fadeIn(View fadeIn, int duration) {
        fadeIn.setAlpha(0.0f);
        fadeIn.setVisibility(0);
        ObjectAnimator.ofFloat(fadeIn, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration((long) duration).start();
    }

    public static void fadeOut(final View fadeOut, int duration) {
        fadeOut.setAlpha(1.0f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(fadeOut, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration((long) duration);
        animator.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                fadeOut.setVisibility(8);
            }
        });
        animator.start();
    }

    public static void expand(final View view, int duration) {
        if (!isExpanded(view)) {
            if (duration == 0) {
                setExpanded(view);
                return;
            }
            view.measure(-1, -2);
            final int targetHeight = view.getMeasuredHeight();
            if (targetHeight != 0) {
                view.getLayoutParams().height = 1;
                view.setVisibility(0);
                Animation animation = new Animation() {
                    /* access modifiers changed from: protected */
                    public void applyTransformation(float interpolatedTime, Transformation transformation) {
                        if (interpolatedTime == 1.0f) {
                            AnimationUtils.setExpanded(view);
                            return;
                        }
                        view.getLayoutParams().height = (int) (((float) targetHeight) * interpolatedTime);
                        view.requestLayout();
                    }

                    public boolean willChangeBounds() {
                        return true;
                    }
                };
                animation.setDuration((long) duration);
                view.startAnimation(animation);
            }
        }
    }

    public static void collapse(final View view, int duration) {
        if (!isCollapsed(view)) {
            if (duration == 0) {
                setCollapsed(view);
                return;
            }
            final int initialHeight = view.getMeasuredHeight();
            Animation animation = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float interpolatedTime, Transformation transformation) {
                    if (interpolatedTime == 1.0f) {
                        AnimationUtils.setCollapsed(view);
                        return;
                    }
                    view.getLayoutParams().height = initialHeight - ((int) (((float) initialHeight) * interpolatedTime));
                    view.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setDuration((long) duration);
            view.startAnimation(animation);
        }
    }

    /* access modifiers changed from: private */
    public static void setExpanded(View view) {
        view.setVisibility(0);
        view.getLayoutParams().height = -2;
        view.requestLayout();
    }

    /* access modifiers changed from: private */
    public static void setCollapsed(View view) {
        view.setVisibility(8);
    }

    private static boolean isExpanded(View view) {
        return view.getVisibility() == 0 && view.getLayoutParams().height == -2;
    }

    private static boolean isCollapsed(View view) {
        return view.getVisibility() == 8;
    }
}
