package com.airbnb.android.itinerary.animation;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.support.p000v4.app.Fragment;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.components.AirToolbar;

public class ItineraryAnimationUtil {
    private static final int TRANSITION_DURATION_MS = 250;
    private static final int TRANSITION_FADE_IN_DURATION_MS = 250;
    private static final Interpolator decelerateInterpolator = new DecelerateInterpolator(1.5f);

    @TargetApi(21)
    public static void setupTransitionToTripFragment(Fragment timelineFragment, Fragment tripFragment, View view) {
        final Rect viewRect = new Rect();
        view.getGlobalVisibleRect(viewRect);
        VerticalExplode verticalExplode = new VerticalExplode();
        verticalExplode.setEpicenterCallback(new EpicenterCallback() {
            public Rect onGetEpicenter(Transition transition) {
                return viewRect;
            }
        });
        verticalExplode.addTarget(C5755R.C5757id.itinerary_item_view_container);
        Fade pendingHeaderFade = new Fade();
        pendingHeaderFade.addTarget(C5755R.C5757id.pending_header);
        pendingHeaderFade.setDuration(125);
        pendingHeaderFade.setInterpolator(decelerateInterpolator);
        TransitionSet timelineTransitionSet = new TransitionSet();
        timelineTransitionSet.addTransition(verticalExplode);
        timelineTransitionSet.addTransition(pendingHeaderFade);
        timelineFragment.setExitTransition(timelineTransitionSet);
        timelineFragment.setReenterTransition(timelineTransitionSet);
        Fade toolbarFadeIn = new Fade();
        toolbarFadeIn.addTarget(AirToolbar.class);
        toolbarFadeIn.setStartDelay(250);
        toolbarFadeIn.setDuration(125);
        toolbarFadeIn.setInterpolator(decelerateInterpolator);
        Fade toolbarFadeOut = new Fade();
        toolbarFadeOut.addTarget(AirToolbar.class);
        toolbarFadeOut.setDuration(125);
        toolbarFadeOut.setInterpolator(decelerateInterpolator);
        tripFragment.setEnterTransition(toolbarFadeIn);
        tripFragment.setReturnTransition(toolbarFadeOut);
    }
}
