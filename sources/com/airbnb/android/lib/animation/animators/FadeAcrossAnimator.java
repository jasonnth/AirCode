package com.airbnb.android.lib.animation.animators;

import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import p316jp.wasabeef.recyclerview.animators.BaseItemAnimator;

public class FadeAcrossAnimator extends BaseItemAnimator {
    private boolean rightToLeft;

    public FadeAcrossAnimator(boolean rightToLeft2) {
        this.rightToLeft = rightToLeft2;
    }

    /* access modifiers changed from: protected */
    public void preAnimateAddImpl(ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, ((float) (holder.itemView.getRootView().getWidth() * (this.rightToLeft ? 1 : -1))) * 0.25f);
        ViewCompat.setAlpha(holder.itemView, 0.0f);
    }

    /* access modifiers changed from: protected */
    public void animateAddImpl(ViewHolder holder) {
        ViewCompat.animate(holder.itemView).translationX(0.0f).alpha(1.0f).setDuration(getAddDuration()).setListener(new DefaultAddVpaListener(holder)).start();
    }

    /* access modifiers changed from: protected */
    public void animateRemoveImpl(ViewHolder holder) {
        ViewCompat.animate(holder.itemView).translationX(((float) (holder.itemView.getRootView().getWidth() * (this.rightToLeft ? -1 : 1))) * 0.25f).alpha(0.0f).setDuration(getRemoveDuration()).setListener(new DefaultRemoveVpaListener(holder)).start();
    }

    public void setRightToLeft(boolean rightToLeft2) {
        this.rightToLeft = rightToLeft2;
    }
}
