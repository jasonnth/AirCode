package com.roughike.bottombar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p002v7.widget.AppCompatImageView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class BottomBarTab extends LinearLayout {
    private float activeAlpha;
    private int activeColor;
    BottomBarBadge badge;
    private int badgeBackgroundColor;
    private int barColorWhenSelected;
    private boolean hideBadgeWhenSelected = true;
    private int iconResId;
    /* access modifiers changed from: private */
    public AppCompatImageView iconView;
    private float inActiveAlpha;
    private int inActiveColor;
    private int indexInContainer;
    /* access modifiers changed from: private */
    public boolean isActive;
    private final int sixteenDps;
    private final int tenDps;
    private String title;
    private int titleTextAppearanceResId;
    private Typeface titleTypeFace;
    private TextView titleView;
    private Type type = Type.FIXED;

    public static class Config {
        /* access modifiers changed from: private */
        public final float activeTabAlpha;
        /* access modifiers changed from: private */
        public final int activeTabColor;
        /* access modifiers changed from: private */
        public final int badgeBackgroundColor;
        /* access modifiers changed from: private */
        public final int barColorWhenSelected;
        /* access modifiers changed from: private */
        public final float inActiveTabAlpha;
        /* access modifiers changed from: private */
        public final int inActiveTabColor;
        /* access modifiers changed from: private */
        public final int titleTextAppearance;
        /* access modifiers changed from: private */
        public final Typeface titleTypeFace;

        public static class Builder {
            /* access modifiers changed from: private */
            public float activeTabAlpha;
            /* access modifiers changed from: private */
            public int activeTabColor;
            /* access modifiers changed from: private */
            public int badgeBackgroundColor;
            /* access modifiers changed from: private */
            public int barColorWhenSelected;
            /* access modifiers changed from: private */
            public float inActiveTabAlpha;
            /* access modifiers changed from: private */
            public int inActiveTabColor;
            /* access modifiers changed from: private */
            public int titleTextAppearance;
            /* access modifiers changed from: private */
            public Typeface titleTypeFace;

            public Builder inActiveTabAlpha(float alpha) {
                this.inActiveTabAlpha = alpha;
                return this;
            }

            public Builder activeTabAlpha(float alpha) {
                this.activeTabAlpha = alpha;
                return this;
            }

            public Builder inActiveTabColor(int color) {
                this.inActiveTabColor = color;
                return this;
            }

            public Builder activeTabColor(int color) {
                this.activeTabColor = color;
                return this;
            }

            public Builder barColorWhenSelected(int color) {
                this.barColorWhenSelected = color;
                return this;
            }

            public Builder badgeBackgroundColor(int color) {
                this.badgeBackgroundColor = color;
                return this;
            }

            public Builder titleTextAppearance(int titleTextAppearance2) {
                this.titleTextAppearance = titleTextAppearance2;
                return this;
            }

            public Builder titleTypeFace(Typeface titleTypeFace2) {
                this.titleTypeFace = titleTypeFace2;
                return this;
            }

            public Config build() {
                return new Config(this);
            }
        }

        private Config(Builder builder) {
            this.inActiveTabAlpha = builder.inActiveTabAlpha;
            this.activeTabAlpha = builder.activeTabAlpha;
            this.inActiveTabColor = builder.inActiveTabColor;
            this.activeTabColor = builder.activeTabColor;
            this.barColorWhenSelected = builder.barColorWhenSelected;
            this.badgeBackgroundColor = builder.badgeBackgroundColor;
            this.titleTextAppearance = builder.titleTextAppearance;
            this.titleTypeFace = builder.titleTypeFace;
        }
    }

    enum Type {
        FIXED,
        SHIFTING,
        TABLET,
        NO_LABELS
    }

    BottomBarTab(Context context) {
        super(context);
        this.tenDps = MiscUtils.dpToPixel(context, 10.0f);
        this.sixteenDps = MiscUtils.dpToPixel(context, 16.0f);
    }

    /* access modifiers changed from: 0000 */
    public void setConfig(Config config) {
        setInActiveAlpha(config.inActiveTabAlpha);
        setActiveAlpha(config.activeTabAlpha);
        setInActiveColor(config.inActiveTabColor);
        setActiveColor(config.activeTabColor);
        setBarColorWhenSelected(config.barColorWhenSelected);
        setBadgeBackgroundColor(config.badgeBackgroundColor);
        setTitleTextAppearance(config.titleTextAppearance);
        setTitleTypeface(config.titleTypeFace);
    }

    /* access modifiers changed from: 0000 */
    public void prepareLayout() {
        inflate(getContext(), getLayoutResource(), this);
        setOrientation(1);
        if (this.type == Type.NO_LABELS) {
            setGravity(17);
            setLayoutParams(new LayoutParams(-1, -1));
        } else {
            setGravity(1);
            setLayoutParams(new LayoutParams(-2, -2));
        }
        this.iconView = (AppCompatImageView) findViewById(R.id.bb_bottom_bar_icon);
        this.iconView.setImageResource(this.iconResId);
        if (hasTitle()) {
            this.titleView = (TextView) findViewById(R.id.bb_bottom_bar_title);
            this.titleView.setText(this.title);
        }
        updateCustomTextAppearance();
        updateCustomTypeface();
    }

    /* access modifiers changed from: 0000 */
    public int getLayoutResource() {
        switch (this.type) {
            case FIXED:
                return R.layout.bb_bottom_bar_item_fixed;
            case SHIFTING:
                return R.layout.bb_bottom_bar_item_shifting;
            case TABLET:
                return R.layout.bb_bottom_bar_item_fixed_tablet;
            case NO_LABELS:
                return R.layout.bb_bottom_bar_item_no_labels;
            default:
                throw new RuntimeException("Unknown BottomBarTab type.");
        }
    }

    private void updateCustomTextAppearance() {
        if (this.titleView != null && this.titleTextAppearanceResId != 0) {
            if (VERSION.SDK_INT >= 23) {
                this.titleView.setTextAppearance(this.titleTextAppearanceResId);
            } else {
                this.titleView.setTextAppearance(getContext(), this.titleTextAppearanceResId);
            }
            this.titleView.setTag(Integer.valueOf(this.titleTextAppearanceResId));
        }
    }

    private void updateCustomTypeface() {
        if (this.titleTypeFace != null && this.titleView != null) {
            this.titleView.setTypeface(this.titleTypeFace);
        }
    }

    /* access modifiers changed from: 0000 */
    public Type getType() {
        return this.type;
    }

    /* access modifiers changed from: 0000 */
    public void setType(Type type2) {
        this.type = type2;
    }

    public ViewGroup getOuterView() {
        return (ViewGroup) getParent();
    }

    /* access modifiers changed from: 0000 */
    public AppCompatImageView getIconView() {
        return this.iconView;
    }

    /* access modifiers changed from: 0000 */
    public int getIconResId() {
        return this.iconResId;
    }

    /* access modifiers changed from: 0000 */
    public void setIconResId(int iconResId2) {
        this.iconResId = iconResId2;
    }

    /* access modifiers changed from: 0000 */
    public String getTitle() {
        return this.title;
    }

    /* access modifiers changed from: 0000 */
    public TextView getTitleView() {
        return this.titleView;
    }

    /* access modifiers changed from: 0000 */
    public void setTitle(String title2) {
        this.title = title2;
    }

    /* access modifiers changed from: 0000 */
    public float getInActiveAlpha() {
        return this.inActiveAlpha;
    }

    /* access modifiers changed from: 0000 */
    public void setInActiveAlpha(float inActiveAlpha2) {
        this.inActiveAlpha = inActiveAlpha2;
        if (!this.isActive) {
            setAlphas(inActiveAlpha2);
        }
    }

    /* access modifiers changed from: 0000 */
    public float getActiveAlpha() {
        return this.activeAlpha;
    }

    /* access modifiers changed from: 0000 */
    public void setActiveAlpha(float activeAlpha2) {
        this.activeAlpha = activeAlpha2;
        if (this.isActive) {
            setAlphas(activeAlpha2);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getInActiveColor() {
        return this.inActiveColor;
    }

    /* access modifiers changed from: 0000 */
    public void setInActiveColor(int inActiveColor2) {
        this.inActiveColor = inActiveColor2;
        if (!this.isActive) {
            setColors(inActiveColor2);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getActiveColor() {
        return this.activeColor;
    }

    /* access modifiers changed from: 0000 */
    public void setActiveColor(int activeIconColor) {
        this.activeColor = activeIconColor;
        if (this.isActive) {
            setColors(this.activeColor);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getBarColorWhenSelected() {
        return this.barColorWhenSelected;
    }

    /* access modifiers changed from: 0000 */
    public void setBarColorWhenSelected(int barColorWhenSelected2) {
        this.barColorWhenSelected = barColorWhenSelected2;
    }

    /* access modifiers changed from: 0000 */
    public int getBadgeBackgroundColor() {
        return this.badgeBackgroundColor;
    }

    /* access modifiers changed from: 0000 */
    public void setBadgeBackgroundColor(int badgeBackgroundColor2) {
        this.badgeBackgroundColor = badgeBackgroundColor2;
        if (this.badge != null) {
            this.badge.setColoredCircleBackground(badgeBackgroundColor2);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getCurrentDisplayedIconColor() {
        if (this.iconView.getTag() instanceof Integer) {
            return ((Integer) this.iconView.getTag()).intValue();
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getCurrentDisplayedTitleColor() {
        if (this.titleView != null) {
            return this.titleView.getCurrentTextColor();
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getCurrentDisplayedTextAppearance() {
        Object tag = this.titleView.getTag();
        if (this.titleView == null || !(tag instanceof Integer)) {
            return 0;
        }
        return ((Integer) this.titleView.getTag()).intValue();
    }

    public void showBadge(boolean show) {
        if (!show) {
            if (this.badge != null) {
                this.badge.removeFromTab(this);
                this.badge = null;
            }
        } else if (this.badge == null) {
            this.badge = new BottomBarBadge(getContext());
            this.badge.attachToTab(this, this.badgeBackgroundColor);
        }
    }

    public void showBadge() {
        showBadge(true);
    }

    public void hideBadge() {
        showBadge(false);
    }

    /* access modifiers changed from: 0000 */
    public boolean isActive() {
        return this.isActive;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasActiveBadge() {
        return this.badge != null;
    }

    /* access modifiers changed from: 0000 */
    public int getIndexInTabContainer() {
        return this.indexInContainer;
    }

    /* access modifiers changed from: 0000 */
    public void setIndexInContainer(int indexInContainer2) {
        this.indexInContainer = indexInContainer2;
    }

    /* access modifiers changed from: 0000 */
    public void setIconTint(int tint) {
        this.iconView.setColorFilter(tint);
    }

    /* access modifiers changed from: 0000 */
    public void setTitleTextAppearance(int resId) {
        this.titleTextAppearanceResId = resId;
        updateCustomTextAppearance();
    }

    public int getTitleTextAppearance() {
        return this.titleTextAppearanceResId;
    }

    /* access modifiers changed from: 0000 */
    public void setTitleTypeface(Typeface typeface) {
        this.titleTypeFace = typeface;
        updateCustomTypeface();
    }

    public void setHideBadgeWhenSelected(boolean hideBadgeWhenSelected2) {
        this.hideBadgeWhenSelected = hideBadgeWhenSelected2;
    }

    /* access modifiers changed from: 0000 */
    public Typeface getTitleTypeFace() {
        return this.titleTypeFace;
    }

    /* access modifiers changed from: 0000 */
    public void select(boolean animate) {
        this.isActive = true;
        if (animate) {
            setTopPaddingAnimated(this.iconView.getPaddingTop(), this.tenDps);
            animateIcon(this.activeAlpha);
            animateTitle(1.0f, this.activeAlpha);
            animateColors(this.inActiveColor, this.activeColor);
        } else {
            setTitleScale(1.0f);
            setTopPadding(this.tenDps);
            setColors(this.activeColor);
            setAlphas(this.activeAlpha);
        }
        if (this.badge != null && this.hideBadgeWhenSelected) {
            this.badge.hide();
        }
    }

    /* access modifiers changed from: 0000 */
    public void deselect(boolean animate) {
        boolean isShifting = false;
        this.isActive = false;
        if (this.type == Type.SHIFTING) {
            isShifting = true;
        }
        float scale = isShifting ? 0.0f : 1.0f;
        int iconPaddingTop = isShifting ? this.sixteenDps : this.tenDps;
        if (animate) {
            setTopPaddingAnimated(this.iconView.getPaddingTop(), iconPaddingTop);
            animateTitle(scale, this.inActiveAlpha);
            animateIcon(this.inActiveAlpha);
            animateColors(this.activeColor, this.inActiveColor);
        } else {
            setTitleScale(scale);
            setTopPadding(iconPaddingTop);
            setColors(this.inActiveColor);
            setAlphas(this.inActiveAlpha);
        }
        if (!isShifting && this.badge != null) {
            this.badge.show();
        }
    }

    private void animateColors(int previousColor, int color) {
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(new int[]{previousColor, color});
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BottomBarTab.this.setColors(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        anim.setDuration(150);
        anim.start();
    }

    /* access modifiers changed from: private */
    public void setColors(int color) {
        if (this.iconView != null) {
            this.iconView.setColorFilter(color);
            this.iconView.setTag(Integer.valueOf(color));
        }
        if (this.titleView != null) {
            this.titleView.setTextColor(color);
        }
    }

    private void setAlphas(float alpha) {
        if (this.iconView != null) {
            ViewCompat.setAlpha(this.iconView, alpha);
        }
        if (this.titleView != null) {
            ViewCompat.setAlpha(this.titleView, alpha);
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateWidth(float endWidth, boolean animated) {
        if (!animated) {
            getLayoutParams().width = (int) endWidth;
            if (!this.isActive && this.badge != null) {
                this.badge.adjustPositionAndSize(this);
                this.badge.show();
                return;
            }
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{(float) getWidth(), endWidth});
        animator.setDuration(150);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animator) {
                ViewGroup.LayoutParams params = BottomBarTab.this.getLayoutParams();
                if (params != null) {
                    params.width = Math.round(((Float) animator.getAnimatedValue()).floatValue());
                    BottomBarTab.this.setLayoutParams(params);
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (!BottomBarTab.this.isActive && BottomBarTab.this.badge != null) {
                    BottomBarTab.this.badge.adjustPositionAndSize(BottomBarTab.this);
                    BottomBarTab.this.badge.show();
                }
            }
        });
        animator.start();
    }

    private void setTopPaddingAnimated(int start, int end) {
        if (hasTopPadding()) {
            ValueAnimator paddingAnimator = ValueAnimator.ofInt(new int[]{start, end});
            paddingAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    BottomBarTab.this.iconView.setPadding(BottomBarTab.this.iconView.getPaddingLeft(), ((Integer) animation.getAnimatedValue()).intValue(), BottomBarTab.this.iconView.getPaddingRight(), BottomBarTab.this.iconView.getPaddingBottom());
                }
            });
            paddingAnimator.setDuration(150);
            paddingAnimator.start();
        }
    }

    private boolean hasTopPadding() {
        return (this.type == Type.TABLET || this.type == Type.NO_LABELS) ? false : true;
    }

    private void animateTitle(float finalScale, float finalAlpha) {
        if (hasTitle()) {
            ViewPropertyAnimatorCompat titleAnimator = ViewCompat.animate(this.titleView).setDuration(150).scaleX(finalScale).scaleY(finalScale);
            titleAnimator.alpha(finalAlpha);
            titleAnimator.start();
        }
    }

    private boolean hasTitle() {
        return (this.type == Type.TABLET || this.type == Type.NO_LABELS) ? false : true;
    }

    private void animateIcon(float finalAlpha) {
        ViewCompat.animate(this.iconView).setDuration(150).alpha(finalAlpha).start();
    }

    private void setTopPadding(int topPadding) {
        if (hasTopPadding()) {
            this.iconView.setPadding(this.iconView.getPaddingLeft(), topPadding, this.iconView.getPaddingRight(), this.iconView.getPaddingBottom());
        }
    }

    private void setTitleScale(float scale) {
        if (hasTitle()) {
            ViewCompat.setScaleX(this.titleView, scale);
            ViewCompat.setScaleY(this.titleView, scale);
        }
    }
}
