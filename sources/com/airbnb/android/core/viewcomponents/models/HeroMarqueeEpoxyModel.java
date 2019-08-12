package com.airbnb.android.core.viewcomponents.models;

import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class HeroMarqueeEpoxyModel extends AirEpoxyModel<HeroMarquee> {
    int backgroundDrawableRes;
    int buttonBackgroundRes;
    int buttonTextColorRes;
    CharSequence caption;
    OnClickListener firstButtonClickListener;
    CharSequence firstButtonText;
    int firstButtonTextRes;
    boolean gradientEnabled;
    int iconDrawableRes;
    String iconUrl;
    int imageRes;
    String imageUrl;
    boolean scrimEnabled;
    OnClickListener secondButtonClickListener;
    CharSequence secondButtonText;
    int secondButtonTextRes;
    int themeColor;
    int themeColorRes;
    CharSequence title;

    public void bind(HeroMarquee marquee) {
        super.bind(marquee);
        if (this.themeColorRes != 0) {
            marquee.setThemeColor(ContextCompat.getColor(marquee.getContext(), this.themeColorRes));
        } else if (this.themeColor != 0) {
            marquee.setThemeColor(this.themeColor);
        } else if (this.imageUrl != null) {
            marquee.setImageUrl(this.imageUrl);
        } else if (this.imageRes != 0) {
            marquee.setImageResource(this.imageRes);
        } else if (this.backgroundDrawableRes != 0) {
            marquee.setBackgroundResource(this.backgroundDrawableRes);
        } else {
            marquee.setThemeColor(ContextCompat.getColor(marquee.getContext(), C0716R.color.n2_rausch));
        }
        if (this.iconUrl != null) {
            marquee.setIconUrl(this.iconUrl);
        } else if (this.iconDrawableRes != 0) {
            marquee.setIcon(this.iconDrawableRes);
        } else {
            marquee.setIcon((Drawable) null);
        }
        marquee.setTitle(this.title);
        marquee.setCaption(this.caption);
        if (this.firstButtonTextRes != 0) {
            marquee.setFirstButtonText(this.firstButtonTextRes);
        } else {
            marquee.setFirstButtonText(this.firstButtonText);
        }
        if (this.buttonTextColorRes != 0) {
            marquee.setFirstButtonTextColor(ContextCompat.getColor(marquee.getContext(), this.buttonTextColorRes));
            marquee.setSecondButtonTextColor(ContextCompat.getColor(marquee.getContext(), this.buttonTextColorRes));
        }
        if (this.buttonBackgroundRes != 0) {
            marquee.setFirstButtonBackground(this.buttonBackgroundRes);
            marquee.setSecondButtonBackground(this.buttonBackgroundRes);
        }
        marquee.setFirstButtonClickListener(this.firstButtonClickListener);
        if (this.secondButtonTextRes != 0) {
            marquee.setSecondButtonText(this.secondButtonTextRes);
        } else {
            marquee.setSecondButtonText(this.secondButtonText);
        }
        marquee.setSecondButtonClickListener(this.secondButtonClickListener);
        marquee.setGradientEnabled(this.gradientEnabled);
        marquee.setScrimEnabled(this.scrimEnabled);
    }

    public void unbind(HeroMarquee marquee) {
        super.unbind(marquee);
        marquee.setFirstButtonClickListener(null);
        marquee.setSecondButtonClickListener(null);
    }

    public int getDividerViewType() {
        return 3;
    }
}
