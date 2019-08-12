package com.roughike.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

class BottomBarBadge extends View {
    private boolean isVisible = false;
    private BottomBarTab tabToSetOnSizeChange;

    BottomBarBadge(Context context) {
        super(context);
    }

    /* access modifiers changed from: 0000 */
    public void show() {
        this.isVisible = true;
        ViewCompat.animate(this).setDuration(150).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
    }

    /* access modifiers changed from: 0000 */
    public void hide() {
        this.isVisible = false;
        ViewCompat.animate(this).setDuration(150).alpha(0.0f).scaleX(0.0f).scaleY(0.0f).start();
    }

    /* access modifiers changed from: 0000 */
    public void attachToTab(BottomBarTab tab, int backgroundColor) {
        int badgeSizePx = (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics());
        setLayoutParams(new LayoutParams(badgeSizePx, badgeSizePx));
        setColoredCircleBackground(backgroundColor);
        wrapTabAndBadgeInSameContainer(tab);
    }

    /* access modifiers changed from: 0000 */
    public void setColoredCircleBackground(int circleColor) {
        int innerPadding = MiscUtils.dpToPixel(getContext(), 1.0f);
        ShapeDrawable backgroundCircle = BadgeCircle.make(circleColor);
        setPadding(innerPadding, innerPadding, innerPadding, innerPadding);
        setBackgroundCompat(backgroundCircle);
    }

    private void wrapTabAndBadgeInSameContainer(BottomBarTab tab) {
        ViewGroup tabContainer = (ViewGroup) tab.getParent();
        tabContainer.removeView(tab);
        FrameLayout badgeContainer = new FrameLayout(getContext());
        badgeContainer.setLayoutParams(new LayoutParams(-2, -2));
        badgeContainer.addView(tab);
        badgeContainer.addView(this);
        tabContainer.addView(badgeContainer, tab.getIndexInTabContainer());
        if (!isViewMeasured()) {
            this.tabToSetOnSizeChange = tab;
        } else {
            adjustPositionAndSize(tab);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.tabToSetOnSizeChange != null && isViewMeasured()) {
            adjustPositionAndSize(this.tabToSetOnSizeChange);
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeFromTab(BottomBarTab tab) {
        FrameLayout badgeAndTabContainer = (FrameLayout) getParent();
        ViewGroup originalTabContainer = (ViewGroup) badgeAndTabContainer.getParent();
        badgeAndTabContainer.removeView(tab);
        originalTabContainer.removeView(badgeAndTabContainer);
        originalTabContainer.addView(tab, tab.getIndexInTabContainer());
    }

    /* access modifiers changed from: 0000 */
    public void adjustPositionAndSize(BottomBarTab tab) {
        AppCompatImageView iconView = tab.getIconView();
        setX(iconView.getX() + ((float) iconView.getWidth()) + ((float) ((getWidth() * 2) / 3)));
        setY(iconView.getY() + ((float) iconView.getPaddingTop()));
        this.tabToSetOnSizeChange = null;
    }

    private void setBackgroundCompat(Drawable background) {
        if (VERSION.SDK_INT >= 16) {
            setBackground(background);
        } else {
            setBackgroundDrawable(background);
        }
    }

    private boolean isViewMeasured() {
        return (getHeight() == 0 && getWidth() == 0) ? false : true;
    }
}
