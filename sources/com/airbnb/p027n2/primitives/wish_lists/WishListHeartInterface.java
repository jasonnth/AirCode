package com.airbnb.p027n2.primitives.wish_lists;

import java.util.HashSet;
import java.util.Set;

/* renamed from: com.airbnb.n2.primitives.wish_lists.WishListHeartInterface */
public abstract class WishListHeartInterface {
    private boolean isWishListed;
    protected final Set<OnWishListedStatusSetListener> statusListeners = new HashSet();

    /* renamed from: com.airbnb.n2.primitives.wish_lists.WishListHeartInterface$OnWishListedStatusSetListener */
    public interface OnWishListedStatusSetListener {
        void onWishListedStatusSet(boolean z);
    }

    public abstract String getUnWishListedAnimationAsset();

    public abstract long getUnWishListedAnimationStartDelay();

    public abstract int getUnWishListedDrawableRes();

    public abstract String getWishListedAnimationAsset();

    public abstract long getWishListedAnimationStartDelay();

    public abstract int getWishListedDrawableRes();

    public abstract void onHeartClicked();

    /* access modifiers changed from: protected */
    public final void setIsWishListed(boolean isWishListed2) {
        this.isWishListed = isWishListed2;
        notifyWishListedStatus();
    }

    public final boolean isWishListed() {
        return this.isWishListed;
    }

    public void addStatusListener(OnWishListedStatusSetListener statusListener) {
        this.statusListeners.add(statusListener);
        statusListener.onWishListedStatusSet(isWishListed());
    }

    public void removeStatusListener(OnWishListedStatusSetListener statusSetListener) {
        this.statusListeners.remove(statusSetListener);
    }

    private void notifyWishListedStatus() {
        for (OnWishListedStatusSetListener listener : this.statusListeners) {
            listener.onWishListedStatusSet(isWishListed());
        }
    }

    public final int getDrawableRes() {
        return isWishListed() ? getWishListedDrawableRes() : getUnWishListedDrawableRes();
    }

    public final String getAnimationAsset() {
        return isWishListed() ? getWishListedAnimationAsset() : getUnWishListedAnimationAsset();
    }

    public final long getAnimationStartDelay() {
        return isWishListed() ? getWishListedAnimationStartDelay() : getUnWishListedAnimationStartDelay();
    }
}
