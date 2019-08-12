package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.p027n2.components.MosaicCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class MosaicCardEpoxyModel extends AirEpoxyModel<MosaicCard> {
    String boldText;
    OnClickListener cardClickListener;
    DisplayOptions displayOptions;
    int emptyStateDrawableRes;
    boolean loading;
    List<String> photoUrls;
    String regularText;

    public void bind(MosaicCard view) {
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
        }
        view.showAccessory(false);
        if (this.loading) {
            view.setupTitle(null, null);
            view.clearImages();
            return;
        }
        view.setupTitle(this.boldText, this.regularText);
        view.setImages(this.photoUrls);
        view.setOnClickListener(this.cardClickListener);
        view.setEmptyStateDrawableRes(this.emptyStateDrawableRes);
        if (isCarousel()) {
            view.setIsMiniCard();
        }
    }

    public void unbind(MosaicCard view) {
        super.unbind(view);
        view.clearImages();
        view.setOnClickListener(null);
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0716R.layout.model_mosaic_card_carousel;
        }
        if (isGrid()) {
            return C0716R.layout.model_mosaic_card_grid;
        }
        return C0716R.layout.model_mosaic_card;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    public int getDividerViewType() {
        return 0;
    }
}
