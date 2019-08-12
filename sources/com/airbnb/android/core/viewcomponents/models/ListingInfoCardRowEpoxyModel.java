package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.ListingInfoCardRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ListingInfoCardRowEpoxyModel extends AirEpoxyModel<ListingInfoCardRow> {
    OnClickListener clickListener;
    int imageDrawableRes;
    String imageUrl;
    CharSequence subTitle;
    int subTitleRes;
    CharSequence title;
    int titleRes;

    public void bind(ListingInfoCardRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubTitle = this.subTitleRes != 0 ? context.getString(this.subTitleRes) : this.subTitle;
        view.setTitleText(actualTitle);
        view.setSubTitleText(actualSubTitle);
        view.setOnClickListener(this.clickListener);
        if (this.imageDrawableRes != 0) {
            view.setImageDrawable(this.imageDrawableRes);
        } else if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        } else {
            view.clearImage();
        }
    }

    public void unbind(ListingInfoCardRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
