package p316jp.wasabeef.recyclerview.animators.holder;

import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p002v7.widget.RecyclerView.ViewHolder;

/* renamed from: jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder */
public abstract class AnimateViewHolder extends ViewHolder {
    public abstract void animateAddImpl(ViewPropertyAnimatorListener viewPropertyAnimatorListener);

    public abstract void animateRemoveImpl(ViewPropertyAnimatorListener viewPropertyAnimatorListener);

    public void preAnimateAddImpl() {
    }

    public void preAnimateRemoveImpl() {
    }
}
