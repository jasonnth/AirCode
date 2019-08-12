package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.p027n2.components.PosterCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;

public abstract class PosterCardEpoxyModel extends AirEpoxyModel<PosterCard> {
    OnClickListener clickListener;
    DisplayOptions displayOptions;
    boolean loading;
    TripTemplate tripTemplate;
    WishListableData wishListableData;

    public void bind(PosterCard view) {
        String str = null;
        super.bind(view);
        if (!this.loading) {
            Check.notNull(this.tripTemplate, "TripTemplate must be set");
        }
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
            view.setWishListHeartStyle(WishListHeartStyle.SMALL);
        }
        view.setTransitionNames("tripTemplate", this.tripTemplate.getId());
        view.setPriceAndDescriptionText(this.tripTemplate.getFormattedPrice(), this.tripTemplate.getDisplayText(), this.tripTemplate.isSocialGood());
        if (this.loading) {
            view.setRating(0.0f, 0, null);
        } else {
            view.setRating(this.tripTemplate.getStarRating(), this.tripTemplate.getReviewCount(), this.tripTemplate.getNumReviewsText(view.getContext()));
        }
        view.setImage(this.loading ? null : this.tripTemplate.getPhoto());
        if (this.tripTemplate.isSoldOut()) {
            str = view.getContext().getString(C0716R.string.sold_out);
        }
        view.setPosterTag(str);
        view.setOnClickListener(this.clickListener);
        if (this.loading || this.wishListableData == null) {
            view.clearWishListHeartInterface();
        } else {
            view.setWishListHeartInterface(new WishListHeartController(view.getContext(), this.wishListableData));
        }
    }

    public void unbind(PosterCard view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.clearImages();
        view.clearWishListHeartInterface();
    }

    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0716R.layout.view_holder_poster_card_carousel;
        }
        if (isGrid()) {
            return C0716R.layout.view_holder_poster_card_grid;
        }
        return C0716R.layout.n2_view_holder_home_card;
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }
}
