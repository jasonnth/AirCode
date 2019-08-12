package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.MicroDisplayCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class MicroDisplayCardEpoxyModel extends AirEpoxyModel<MicroDisplayCard> {
    String imageUrl;
    OnClickListener onClickListener;
    CharSequence title;

    public void bind(MicroDisplayCard card) {
        super.bind(card);
        if (this.imageUrl != null) {
            card.setImageUrl(this.imageUrl);
        }
        card.setTitleText(this.title);
        card.setOnClickListener(this.onClickListener);
    }

    public void unbind(MicroDisplayCard card) {
        card.clearImage();
        card.setOnClickListener(null);
    }
}
