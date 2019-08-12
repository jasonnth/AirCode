package com.airbnb.android.core.viewcomponents.models;

import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.GuideImageMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class GuideImageMarqueeEpoxyModel extends AirEpoxyModel<GuideImageMarquee> {
    OnClickListener clickListener;
    int imageDrawableRes;
    String imageUrl;

    public void bind(GuideImageMarquee view) {
        super.bind(view);
        if (this.imageDrawableRes != 0) {
            view.setImage(this.imageDrawableRes);
        } else if (!TextUtils.isEmpty(this.imageUrl)) {
            view.setImageUrl(this.imageUrl);
        }
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(GuideImageMarquee view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
