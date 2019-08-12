package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieAnimationView.CacheStrategy;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface.OnWishListedStatusSetListener;
import com.airbnb.p027n2.utils.DebouncedOnClickListener;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.WishListIconView */
public class WishListIconView extends FrameLayout {
    /* access modifiers changed from: private */
    public boolean heartClickedSinceLastChange;
    private Boolean isWishListed;
    private final Runnable playAnimationRunnable = WishListIconView$$Lambda$1.lambdaFactory$(this);
    @BindView
    LottieAnimationView removedAnimation;
    @BindView
    LottieAnimationView savedAnimation;
    /* access modifiers changed from: private */
    public WishListHeartInterface wishListHeartInterface;
    private final OnWishListedStatusSetListener wishListStatusListener = WishListIconView$$Lambda$2.lambdaFactory$(this);

    public WishListIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.n2_view_wish_list_icon, this, true);
        ButterKnife.bind((View) this);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.wishListHeartInterface != null) {
            this.wishListHeartInterface.addStatusListener(this.wishListStatusListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.wishListHeartInterface != null) {
            this.wishListHeartInterface.removeStatusListener(this.wishListStatusListener);
        }
        clearState();
        super.onDetachedFromWindow();
    }

    public void setWishListInterface(WishListHeartInterface heartInterface) {
        if (!heartInterface.equals(this.wishListHeartInterface)) {
            clearWishListInterface();
            this.wishListHeartInterface = heartInterface;
            setOnClickListener(new DebouncedOnClickListener(500) {
                public void onClickDebounced(View v) {
                    WishListIconView.this.heartClickedSinceLastChange = true;
                    WishListIconView.this.wishListHeartInterface.onHeartClicked();
                }
            });
            if (ViewCompat.isAttachedToWindow(this)) {
                this.wishListHeartInterface.addStatusListener(this.wishListStatusListener);
            }
        }
    }

    public void clearWishListInterface() {
        clearState();
        if (this.wishListHeartInterface != null) {
            setOnClickListener(null);
            this.wishListHeartInterface.removeStatusListener(this.wishListStatusListener);
            this.wishListHeartInterface = null;
        }
    }

    private void clearState() {
        this.isWishListed = null;
        this.heartClickedSinceLastChange = false;
        removeCallbacks(this.playAnimationRunnable);
        this.savedAnimation.cancelAnimation();
        this.removedAnimation.cancelAnimation();
    }

    /* access modifiers changed from: private */
    public void setIsWishListed(boolean isWishListed2) {
        boolean changed;
        if (this.wishListHeartInterface == null) {
            this.savedAnimation.setVisibility(8);
            this.removedAnimation.setVisibility(8);
            return;
        }
        if (this.isWishListed == null || this.isWishListed.booleanValue() != isWishListed2) {
            changed = true;
        } else {
            changed = false;
        }
        boolean animate = this.heartClickedSinceLastChange;
        if (changed) {
            this.isWishListed = Boolean.valueOf(isWishListed2);
            if (!animate) {
                getCurrentAnimation().setProgress(1.0f);
            } else if (this.wishListHeartInterface.getAnimationStartDelay() > 0) {
                postDelayed(this.playAnimationRunnable, this.wishListHeartInterface.getAnimationStartDelay());
            } else {
                playAnimation();
            }
        }
        this.heartClickedSinceLastChange = false;
    }

    /* access modifiers changed from: private */
    public void playAnimation() {
        getCurrentAnimation().playAnimation();
    }

    private LottieAnimationView getCurrentAnimation() {
        if (this.isWishListed == null) {
            throw new IllegalStateException("Cannot get an animation if wishlisted state isn't set");
        }
        LottieAnimationView animationToUse = this.isWishListed.booleanValue() ? this.savedAnimation : this.removedAnimation;
        ViewLibUtils.setVisibleIf(this.savedAnimation, this.isWishListed.booleanValue());
        ViewLibUtils.setVisibleIf(this.removedAnimation, !this.isWishListed.booleanValue());
        animationToUse.setAnimation(this.wishListHeartInterface.getAnimationAsset(), CacheStrategy.Strong);
        return animationToUse;
    }
}
