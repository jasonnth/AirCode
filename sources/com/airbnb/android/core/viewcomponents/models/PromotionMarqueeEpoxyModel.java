package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.p027n2.components.PromotionMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PromotionMarqueeEpoxyModel extends AirEpoxyModel<PromotionMarquee> {
    CharSequence banner;
    int bannerRes;
    CharSequence caption;
    int captionRes;
    CharSequence title;
    int titleRes;

    public void bind(PromotionMarquee view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualBanner = this.bannerRes != 0 ? context.getString(this.bannerRes) : this.banner;
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualCaption = this.captionRes != 0 ? context.getString(this.captionRes) : this.caption;
        view.setBannerText(actualBanner);
        view.setTitleText(actualTitle);
        view.setCaptionText(actualCaption);
    }
}
