package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.p027n2.components.PlaceCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;
import com.airbnb.p027n2.utils.ViewLibUtils;

public abstract class GuidebookPlaceEpoxyModel extends AirEpoxyModel<PlaceCard> {
    String boldText;
    OnClickListener cardClickListener;
    DisplayOptions displayOptions;
    boolean invisible;
    boolean loading;
    Photo photo;
    String regularText;
    boolean selectionHighlight;
    boolean showBottomSpace = true;
    WishListableData wishListableData;

    public void bind(PlaceCard view) {
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
            view.setWishListHeartStyle(WishListHeartStyle.SMALL);
        }
        if (this.loading) {
            view.clearImage();
            view.setupTitle(null, null);
            view.setOnClickListener(null);
            return;
        }
        view.setImage(this.photo);
        view.setupTitle(this.boldText, this.regularText);
        view.setOnClickListener(this.cardClickListener);
        view.showSelectionHighlight(this.selectionHighlight);
        view.showBottomSpace(this.showBottomSpace);
        if (this.loading || this.wishListableData == null) {
            view.clearWishListInterface();
        } else {
            view.setWishListInterface(new WishListHeartController(view.getContext(), this.wishListableData));
        }
        ViewLibUtils.setInvisibleIf(view, this.invisible);
    }

    public void unbind(PlaceCard view) {
        super.unbind(view);
        view.clearImage();
        view.clearWishListInterface();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        if (isGrid()) {
            return C0716R.layout.view_holder_place_card_grid;
        }
        if (isCarousel()) {
            return C0716R.layout.view_holder_place_card_carousel;
        }
        return C0716R.layout.n2_view_holder_place_card;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }
}
