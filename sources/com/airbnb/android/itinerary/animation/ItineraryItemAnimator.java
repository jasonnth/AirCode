package com.airbnb.android.itinerary.animation;

import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.animation.Interpolator;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.p027n2.epoxy.EpoxyItemAnimator;

public class ItineraryItemAnimator extends EpoxyItemAnimator {
    private static final int RETURN_DELAY_MS = 100;
    private static final float SCALE_MIN = 0.8f;
    private static final int START_DELAY_MS = 200;
    private static final Interpolator interpolator = new LinearOutSlowInInterpolator();
    private ItineraryNavigationController itineraryNavigationController;
    private boolean useCustomAnimator;

    public void setUseCustomAnimator(boolean useCustomAnimator2) {
        this.useCustomAnimator = useCustomAnimator2;
    }

    public void setItineraryNavigationController(ItineraryNavigationController itineraryNavigationController2) {
        this.itineraryNavigationController = itineraryNavigationController2;
    }

    public boolean animateAdd(ViewHolder holder) {
        if (!(holder.itemView instanceof ItineraryItemView) || !this.useCustomAnimator || !startCustomAnimation(holder.itemView, holder, true)) {
            return super.animateAdd(holder);
        }
        return true;
    }

    public boolean animateRemove(ViewHolder holder) {
        if (!(holder.itemView instanceof ItineraryItemView) || !this.useCustomAnimator || !startCustomAnimation(holder.itemView, holder, false)) {
            return super.animateRemove(holder);
        }
        return true;
    }

    private boolean startCustomAnimation(View itineraryItemView, ViewHolder holder, boolean isAdd) {
        View cardContainer = itineraryItemView.findViewById(C5755R.C5757id.itinerary_card_container);
        View header = itineraryItemView.findViewById(C5755R.C5757id.header);
        View subheader = itineraryItemView.findViewById(C5755R.C5757id.subheader);
        View timelineTop = itineraryItemView.findViewById(C5755R.C5757id.timeline_line_top);
        View timelineBottom = itineraryItemView.findViewById(C5755R.C5757id.timeline_line_bottom);
        View indicatorContainer = itineraryItemView.findViewById(C5755R.C5757id.indicator_container);
        if (cardContainer == null || header == null || subheader == null || timelineTop == null || timelineBottom == null || indicatorContainer == null) {
            return false;
        }
        float scaleStart = isAdd ? SCALE_MIN : 1.0f;
        float scaleEnd = isAdd ? 1.0f : SCALE_MIN;
        float alphaStart = isAdd ? 0.0f : 1.0f;
        float alphaEnd = isAdd ? 1.0f : 0.0f;
        int startDelay = isAdd ? 200 : 0;
        cardContainer.setAlpha(alphaStart);
        cardContainer.setScaleX(scaleStart);
        cardContainer.setScaleY(scaleStart);
        cardContainer.animate().scaleX(scaleEnd).scaleY(scaleEnd).alpha(alphaEnd).setInterpolator(interpolator).setStartDelay((long) startDelay).withStartAction(ItineraryItemAnimator$$Lambda$1.lambdaFactory$(this, isAdd, cardContainer)).withEndAction(ItineraryItemAnimator$$Lambda$2.lambdaFactory$(this, holder)).start();
        header.setAlpha(alphaStart);
        header.animate().alpha(alphaEnd).setStartDelay((long) startDelay).start();
        subheader.setAlpha(alphaStart);
        subheader.animate().alpha(alphaEnd).setStartDelay((long) startDelay).start();
        timelineTop.setAlpha(alphaStart);
        timelineTop.animate().alpha(alphaEnd).setStartDelay((long) startDelay).start();
        timelineBottom.setAlpha(alphaStart);
        timelineBottom.animate().alpha(alphaEnd).setStartDelay((long) startDelay).start();
        indicatorContainer.setScaleX(isAdd ? 0.0f : 1.0f);
        indicatorContainer.setScaleY(isAdd ? 0.0f : 1.0f);
        indicatorContainer.animate().scaleX(isAdd ? 1.0f : 0.0f).scaleY(isAdd ? 1.0f : 0.0f).setStartDelay((long) startDelay).start();
        return true;
    }

    static /* synthetic */ void lambda$startCustomAnimation$1(ItineraryItemAnimator itineraryItemAnimator, boolean isAdd, View cardContainer) {
        if (!isAdd && itineraryItemAnimator.itineraryNavigationController != null) {
            cardContainer.postDelayed(ItineraryItemAnimator$$Lambda$3.lambdaFactory$(itineraryItemAnimator), 100);
        }
    }

    static /* synthetic */ void lambda$startCustomAnimation$2(ItineraryItemAnimator itineraryItemAnimator, ViewHolder holder) {
        itineraryItemAnimator.dispatchAnimationFinished(holder);
        itineraryItemAnimator.useCustomAnimator = false;
    }
}
