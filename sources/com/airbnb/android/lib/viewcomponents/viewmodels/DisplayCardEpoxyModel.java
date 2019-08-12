package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.DisplayCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class DisplayCardEpoxyModel extends AirEpoxyModel<DisplayCard> {
    int backgroundDrawableRes;
    String imageUrl;
    OnClickListener onClickListener;
    String text;
    int textRes;

    public void bind(DisplayCard displayCard) {
        super.bind(displayCard);
        if (this.backgroundDrawableRes != 0) {
            displayCard.setImage(this.backgroundDrawableRes);
        }
        if (this.imageUrl != null) {
            displayCard.setImageUrl(this.imageUrl);
        }
        if (this.textRes != 0) {
            displayCard.setText(this.textRes);
        } else {
            displayCard.setText(this.text);
        }
        displayCard.setOnClickListener(this.onClickListener);
    }

    public void unbind(DisplayCard displayCard) {
        super.unbind(displayCard);
        displayCard.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
