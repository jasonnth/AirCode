package com.airbnb.android.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.airbnb.android.utils.animation.NullAnimation;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.utils.R;

public class FragmentUtils {
    private static final int ENTER_ANIMATION_DURATION_MS = 300;
    private static final int ENTER_FADE_DURATION_MS = 175;
    private static final int EXIT_ANIMATION_DURATION_MS = 400;
    private static final int EXIT_FADE_DURATION_MS = 100;
    private static final float FRACTION_WIDENING = 0.4f;

    public static Animation animateSheetExpansion(Fragment fragment, boolean enter, Rect originatingRect) {
        Interpolator interpolator;
        if (originatingRect == null) {
            return null;
        }
        View view = fragment.getView();
        if (view == null) {
            throw new IllegalArgumentException("Fragment view should not be null!");
        }
        if (enter) {
            interpolator = new AccelerateInterpolator();
        } else {
            interpolator = new DecelerateInterpolator();
        }
        long fragmentFadeDuration = fadeFragment(view, enter);
        animateContent(fragment, enter);
        if (AndroidVersion.isAtLeastJellyBeanMR2()) {
            clipContent(view, interpolator, enter, originatingRect);
        }
        Animation nullAnimation = new NullAnimation();
        nullAnimation.setDuration(fragmentFadeDuration);
        return nullAnimation;
    }

    @TargetApi(18)
    private static void clipContent(View view, Interpolator interpolator, boolean enter, Rect originatingRect) {
        final Rect startRect;
        final Rect endRect;
        final RectEvaluator rectEvaluator = new RectEvaluator();
        Rect smallRect = originatingRect;
        final Rect wideRect = new Rect(originatingRect);
        final Rect bigRect = new Rect();
        if (enter) {
            startRect = smallRect;
        } else {
            startRect = bigRect;
        }
        if (enter) {
            endRect = bigRect;
        } else {
            endRect = smallRect;
        }
        int duration = enter ? 300 : 400;
        ValueAnimator clipBoundsAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        clipBoundsAnimator.setDuration((long) (((double) duration) * 0.6d));
        if (enter) {
            clipBoundsAnimator.setStartDelay(100);
        }
        clipBoundsAnimator.setInterpolator(interpolator);
        final View view2 = view;
        final boolean z = enter;
        clipBoundsAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Rect clipBounds;
                if (bigRect.height() == 0 && view2.getHeight() != 0) {
                    bigRect.right = view2.getWidth();
                    wideRect.left = 0;
                    wideRect.right = view2.getWidth();
                }
                bigRect.bottom = view2.getBottom();
                float f = animation.getAnimatedFraction();
                if (z) {
                    if (f < FragmentUtils.FRACTION_WIDENING) {
                        clipBounds = rectEvaluator.evaluate(f / FragmentUtils.FRACTION_WIDENING, startRect, wideRect);
                    } else {
                        clipBounds = rectEvaluator.evaluate((f - FragmentUtils.FRACTION_WIDENING) / 0.6f, wideRect, endRect);
                    }
                } else if (f < 0.6f) {
                    clipBounds = rectEvaluator.evaluate(f / 0.6f, startRect, wideRect);
                } else {
                    clipBounds = rectEvaluator.evaluate((f - 0.6f) / FragmentUtils.FRACTION_WIDENING, wideRect, endRect);
                }
                view2.setClipBounds(clipBounds);
            }
        });
        if (enter) {
            final View view3 = view;
            clipBoundsAnimator.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    view3.setClipBounds(null);
                }

                public void onAnimationEnd(Animator animator) {
                    view3.setClipBounds(null);
                }
            });
        }
        view.setClipBounds(startRect);
        clipBoundsAnimator.start();
    }

    private static long fadeFragment(View view, boolean enter) {
        float startAlpha;
        float endAlpha = 1.0f;
        if (enter) {
            startAlpha = 0.0f;
        } else {
            startAlpha = 1.0f;
        }
        if (!enter) {
            endAlpha = 0.0f;
        }
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", new float[]{startAlpha, endAlpha});
        if (enter) {
            anim.setInterpolator(new AccelerateInterpolator());
        } else {
            anim.setInterpolator(new DecelerateInterpolator());
        }
        anim.setDuration(enter ? 175 : 100);
        if (!enter) {
            anim.setStartDelay(140);
        }
        anim.start();
        return anim.getStartDelay() + anim.getDuration();
    }

    private static void animateContent(Fragment fragment, boolean enter) {
        int endTranslationY;
        ViewGroup view = (ViewGroup) fragment.getView();
        if (view == null) {
            throw new IllegalArgumentException("Fragment view should not be null!");
        }
        int startDelay = (int) (enter ? 240.0d : 0.0d);
        int fadeDuration = enter ? 450 : 200;
        float endAlpha = enter ? 1.0f : 0.0f;
        int translationAmount = fragment.getResources().getDimensionPixelSize(R.dimen.find_tween_sheet_anim_translation_y);
        int startTranslationY = enter ? translationAmount : 0;
        if (enter) {
            endTranslationY = 0;
        } else {
            endTranslationY = translationAmount;
        }
        DecelerateInterpolator interpolator = new DecelerateInterpolator();
        for (int i = view.getChildCount() - 1; i >= 0; i--) {
            View child = view.getChildAt(i);
            if (enter) {
                child.setAlpha(0.0f);
            }
            ObjectAnimator anim = ObjectAnimator.ofFloat(child, "alpha", new float[]{child.getAlpha(), endAlpha});
            anim.setInterpolator(interpolator);
            anim.setStartDelay((long) startDelay);
            anim.setDuration((long) fadeDuration);
            anim.start();
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(child, "translationY", new float[]{(float) startTranslationY, (float) endTranslationY});
            anim2.setInterpolator(interpolator);
            anim2.setStartDelay((long) startDelay);
            anim2.setDuration((long) (((double) fadeDuration) * 0.5d));
            anim2.start();
        }
    }
}
