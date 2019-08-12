package com.airbnb.android.core.adapters.find;

import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.airbnb.android.core.views.UrgencyView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class UrgencyViewAnimationHelper {
    private static final int ANIMATION_DELAY_MILLIS = 300;
    private static final int MIN_VISIBLE_ROOM_BEFORE_ANIMATION_DP = 100;
    /* access modifiers changed from: private */
    public final Runnable animationRunnable = new Runnable() {
        public void run() {
            if (!UrgencyViewAnimationHelper.this.urgencyView.hasAnimated() && UrgencyViewAnimationHelper.this.isViewInMiddleOfScreen()) {
                UrgencyViewAnimationHelper.this.urgencyView.startUrgencyAnimation();
            }
        }
    };
    /* access modifiers changed from: private */
    public final Handler handler = new Handler();
    private final int[] locationRect = new int[2];
    private int minVisibleRoomBeforeAnimationPx;
    private final OnScrollListener scrollListener = new OnScrollListener() {
        private int currentScrollState;

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            this.currentScrollState = newState;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (UrgencyViewAnimationHelper.this.urgencyView != null && !UrgencyViewAnimationHelper.this.urgencyView.hasAnimated() && this.currentScrollState == 1 && UrgencyViewAnimationHelper.this.isViewInMiddleOfScreen()) {
                UrgencyViewAnimationHelper.this.handler.removeCallbacks(UrgencyViewAnimationHelper.this.animationRunnable);
                UrgencyViewAnimationHelper.this.urgencyView.startUrgencyAnimation();
            }
        }
    };
    /* access modifiers changed from: private */
    public UrgencyView urgencyView;
    private ViewGroup viewParent;

    public void bindUrgencyView(UrgencyView urgencyView2) {
        this.urgencyView = urgencyView2;
        if (this.minVisibleRoomBeforeAnimationPx == 0) {
            this.minVisibleRoomBeforeAnimationPx = ViewLibUtils.dpToPx(urgencyView2.getContext(), 100.0f);
        }
        if (!urgencyView2.hasAnimated()) {
            attachToParent(urgencyView2.getParent());
            this.handler.removeCallbacks(this.animationRunnable);
            this.handler.postDelayed(this.animationRunnable, 300);
        }
    }

    public void unbindUrgencyView() {
        this.handler.removeCallbacks(this.animationRunnable);
        if (this.urgencyView != null && this.urgencyView.hasAnimated()) {
            this.urgencyView.showWithoutAnimation();
        }
        this.urgencyView = null;
    }

    /* access modifiers changed from: private */
    public boolean isViewInMiddleOfScreen() {
        this.viewParent.getLocationInWindow(this.locationRect);
        int parentTop = this.locationRect[1];
        int parentBottom = parentTop + this.viewParent.getHeight();
        this.urgencyView.getLocationOnScreen(this.locationRect);
        int urgencyPosition = this.locationRect[1];
        if (urgencyPosition < parentTop || urgencyPosition >= parentBottom - this.minVisibleRoomBeforeAnimationPx) {
            return false;
        }
        return true;
    }

    private void attachToParent(ViewParent parent) {
        detachFromCurrentParent();
        this.viewParent = (ViewGroup) parent;
        if (this.viewParent instanceof RecyclerView) {
            ((RecyclerView) this.viewParent).addOnScrollListener(this.scrollListener);
        }
    }

    private void detachFromCurrentParent() {
        if (this.viewParent instanceof RecyclerView) {
            ((RecyclerView) this.viewParent).removeOnScrollListener(this.scrollListener);
        }
        this.viewParent = null;
    }
}
