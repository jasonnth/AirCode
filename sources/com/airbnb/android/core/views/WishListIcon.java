package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.p027n2.components.AirToolbar.TintableMenuItem;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface.OnWishListedStatusSetListener;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.DebouncedOnClickListener;

public class WishListIcon extends AppCompatImageView implements TintableMenuItem {
    /* access modifiers changed from: private */
    public WishListHeartController controller;
    private int toolbarForegroundColor;
    private final OnWishListedStatusSetListener wishListStatusListener;

    public WishListIcon(Context context) {
        super(context);
        this.wishListStatusListener = WishListIcon$$Lambda$1.lambdaFactory$(this);
    }

    public WishListIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.wishListStatusListener = WishListIcon$$Lambda$2.lambdaFactory$(this);
    }

    public WishListIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.wishListStatusListener = WishListIcon$$Lambda$3.lambdaFactory$(this);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.controller != null) {
            this.controller.addStatusListener(this.wishListStatusListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.controller != null) {
            this.controller.removeStatusListener(this.wishListStatusListener);
        }
        super.onDetachedFromWindow();
    }

    public void initIfNeeded(WishListableData data) {
        if (this.controller == null) {
            this.controller = new WishListHeartController(getContext(), data) {
                public int getUnWishListedDrawableRes() {
                    return C0716R.C0717drawable.n2_heart_dark_outline;
                }

                public int getWishListedDrawableRes() {
                    return C0716R.C0717drawable.n2_heart_red_fill;
                }
            };
            setOnClickListener(new DebouncedOnClickListener(500) {
                public void onClickDebounced(View v) {
                    WishListIcon.this.controller.onHeartClicked();
                }
            });
            if (ViewCompat.isAttachedToWindow(this)) {
                this.controller.addStatusListener(this.wishListStatusListener);
            }
        }
    }

    public void setForegroundColor(int color) {
        if (color != this.toolbarForegroundColor) {
            this.toolbarForegroundColor = color;
            if (this.controller != null) {
                updateHeartDrawable(this.controller.isWishListed());
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateHeartDrawable(boolean wishListed) {
        if (wishListed) {
            setImageResource(this.controller.getWishListedDrawableRes());
        } else {
            setImageDrawable(ColorizedDrawable.mutateDrawableWithColor(AppCompatResources.getDrawable(getContext(), this.controller.getDrawableRes()), this.toolbarForegroundColor));
        }
    }
}
