package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.p027n2.components.DestinationCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class DestinationCardEpoxyModel extends AirEpoxyModel<DestinationCard> {
    OnClickListener cardClickListener;
    DisplayOptions displayOptions;
    boolean loading;
    String photoUrl;
    String titleText;

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return isCarousel() ? C0716R.layout.view_holder_destination_card_carousel : C0716R.layout.n2_view_holder_destination_card;
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    public void bind(DestinationCard view) {
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
        }
        if (this.loading) {
            view.clearImage();
            view.setTitleText(null);
            return;
        }
        view.setImageUrl(this.photoUrl);
        view.setTitleText(this.titleText);
        view.setOnClickListener(this.cardClickListener);
    }

    public void unbind(DestinationCard view) {
        super.unbind(view);
        view.clearImage();
        view.setOnClickListener(null);
    }
}
