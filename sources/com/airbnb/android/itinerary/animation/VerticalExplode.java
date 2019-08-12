package com.airbnb.android.itinerary.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.CircularPropagation;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.airbnb.android.itinerary.C5755R;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
public class VerticalExplode extends Visibility {
    private static final int EXPLODE_DURATION_MS = 250;
    private static final int FADE_DURATION_MS = 125;
    private static final int FAST_FADE_DURATION_MS = 50;
    private static final int SCALE_DURATION_MS = 250;
    private static final float SCALE_MAX = 1.25f;
    private static final Interpolator accelerateInterpolator = new FastOutLinearInInterpolator();
    private static final Interpolator decelerateInterpolator = new LinearOutSlowInInterpolator();

    public VerticalExplode() {
        setPropagation(new CircularPropagation());
    }

    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createAnimation(view, getEpicenterY(sceneRoot), decelerateInterpolator, true);
    }

    public Animator onDisappear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        ViewGroup parentViewGroup = (ViewGroup) startValues.view.getParent();
        if (parentViewGroup == null) {
            return null;
        }
        parentViewGroup.removeView(startValues.view);
        return super.onDisappear(sceneRoot, startValues, startVisibility, endValues, endVisibility);
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createAnimation(view, getEpicenterY(sceneRoot), accelerateInterpolator, false);
    }

    private int getEpicenterY(ViewGroup sceneRoot) {
        Rect epicenter = getEpicenter();
        if (epicenter != null) {
            return epicenter.centerY();
        }
        int[] loc = new int[2];
        sceneRoot.getLocationOnScreen(loc);
        return loc[1] + (sceneRoot.getHeight() / 2) + Math.round(sceneRoot.getTranslationY());
    }

    /* access modifiers changed from: 0000 */
    public Animator createAnimation(View view, int epicenterY, Interpolator interpolator, boolean appear) {
        float translateStart;
        float translateEnd;
        float f;
        float f2;
        int i;
        View cardsContainer = view.findViewById(C5755R.C5757id.itinerary_card_container);
        View header = view.findViewById(C5755R.C5757id.header);
        View subheader = view.findViewById(C5755R.C5757id.subheader);
        View indicatorContainer = view.findViewById(C5755R.C5757id.indicator_container);
        if (cardsContainer == null || header == null || subheader == null || indicatorContainer == null) {
            return null;
        }
        int top = view.getTop();
        boolean isEpicenter = top <= epicenterY && view.getBottom() >= epicenterY;
        boolean isAboveEpicenter = top < epicenterY;
        int height = (int) (((float) view.getHeight()) + view.getTranslationY());
        if (appear) {
            if (isAboveEpicenter) {
                i = -height;
            } else {
                i = height;
            }
            translateStart = (float) i;
        } else {
            translateStart = 0.0f;
        }
        if (appear) {
            translateEnd = 0.0f;
        } else {
            if (isAboveEpicenter) {
                height = -height;
            }
            translateEnd = (float) height;
        }
        float alphaStart = appear ? 0.0f : 1.0f;
        float alphaEnd = appear ? 1.0f : 0.0f;
        float scaleStart = appear ? 0.0f : 1.0f;
        float scaleEnd = appear ? 1.0f : 0.0f;
        List<Animator> animators = new ArrayList<>();
        indicatorContainer.setScaleX(scaleStart);
        indicatorContainer.setScaleY(scaleStart);
        ObjectAnimator indicatorScaleAnim = ObjectAnimator.ofPropertyValuesHolder(indicatorContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{scaleStart, scaleEnd}), PropertyValuesHolder.ofFloat("scaleY", new float[]{scaleStart, scaleEnd})});
        indicatorScaleAnim.setDuration(50);
        animators.add(indicatorScaleAnim);
        if (isEpicenter) {
            if (appear) {
                f = translateEnd;
            } else {
                f = translateStart;
            }
            cardsContainer.setTranslationY(f);
            if (appear) {
                f2 = translateEnd;
            } else {
                f2 = translateStart;
            }
            header.setTranslationY(f2);
            if (!appear) {
                translateEnd = translateStart;
            }
            subheader.setTranslationY(translateEnd);
            float scaleStart2 = appear ? SCALE_MAX : 1.0f;
            float scaleEnd2 = appear ? 1.0f : SCALE_MAX;
            ObjectAnimator cardFadeAnim = ObjectAnimator.ofFloat(cardsContainer, View.ALPHA, new float[]{alphaStart, alphaEnd});
            cardFadeAnim.setDuration(125);
            animators.add(cardFadeAnim);
            ObjectAnimator headerFadeAnim = ObjectAnimator.ofFloat(header, View.ALPHA, new float[]{alphaStart, alphaEnd});
            headerFadeAnim.setDuration(appear ? 125 : 50);
            animators.add(headerFadeAnim);
            ObjectAnimator subheaderFadeAnim = ObjectAnimator.ofFloat(subheader, View.ALPHA, new float[]{alphaStart, alphaEnd});
            subheaderFadeAnim.setDuration(appear ? 125 : 50);
            animators.add(subheaderFadeAnim);
            ObjectAnimator cardsScaleAnim = ObjectAnimator.ofPropertyValuesHolder(cardsContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{scaleStart2, scaleEnd2}), PropertyValuesHolder.ofFloat("scaleY", new float[]{scaleStart2, scaleEnd2})});
            cardsScaleAnim.setInterpolator(decelerateInterpolator);
            cardsScaleAnim.setDuration(250);
            animators.add(cardsScaleAnim);
        } else {
            cardsContainer.setTranslationY(translateStart);
            header.setTranslationY(translateStart);
            subheader.setTranslationY(translateStart);
            ObjectAnimator explodeCardsAnim = ObjectAnimator.ofFloat(cardsContainer, View.TRANSLATION_Y, new float[]{translateStart, translateEnd});
            explodeCardsAnim.setInterpolator(interpolator);
            explodeCardsAnim.setDuration(250);
            animators.add(explodeCardsAnim);
            ObjectAnimator explodeHeaderAnim = ObjectAnimator.ofFloat(header, View.TRANSLATION_Y, new float[]{translateStart, translateEnd});
            explodeHeaderAnim.setInterpolator(interpolator);
            explodeHeaderAnim.setDuration(250);
            animators.add(explodeHeaderAnim);
            ObjectAnimator explodeSubheaderAnim = ObjectAnimator.ofFloat(subheader, View.TRANSLATION_Y, new float[]{translateStart, translateEnd});
            explodeSubheaderAnim.setInterpolator(interpolator);
            explodeSubheaderAnim.setDuration(250);
            animators.add(explodeSubheaderAnim);
        }
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        return set;
    }
}
