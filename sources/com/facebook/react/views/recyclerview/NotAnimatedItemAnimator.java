package com.facebook.react.views.recyclerview;

import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.support.p002v7.widget.SimpleItemAnimator;

class NotAnimatedItemAnimator extends SimpleItemAnimator {
    NotAnimatedItemAnimator() {
    }

    public void runPendingAnimations() {
    }

    public boolean animateRemove(ViewHolder holder) {
        dispatchRemoveStarting(holder);
        dispatchRemoveFinished(holder);
        return true;
    }

    public boolean animateAdd(ViewHolder holder) {
        dispatchAddStarting(holder);
        dispatchAddFinished(holder);
        return true;
    }

    public boolean animateMove(ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        dispatchMoveStarting(holder);
        dispatchMoveFinished(holder);
        return true;
    }

    public boolean animateChange(ViewHolder oldHolder, ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        dispatchChangeStarting(oldHolder, true);
        dispatchChangeFinished(oldHolder, true);
        dispatchChangeStarting(newHolder, false);
        dispatchChangeFinished(newHolder, false);
        return true;
    }

    public void endAnimation(ViewHolder item) {
    }

    public void endAnimations() {
    }

    public boolean isRunning() {
        return false;
    }
}
