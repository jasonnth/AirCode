package com.airbnb.android.core.viewcomponents.models;

import android.support.p000v4.content.ContextCompat;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.p027n2.components.RecommendationCardSquare;
import com.airbnb.p027n2.components.RecommendationRow.CardType;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class RecommendationCardEpoxyModel extends AirEpoxyModel<RecommendationCardSquare> {
    String actionKicker;
    OnClickListener clickListener;
    String description;
    DisplayOptions displayOptions;
    String imageUrl;
    boolean loading;
    RecommendationItem recommendationItem;
    String title;

    public void bind(RecommendationCardSquare view) {
        String str = null;
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
        }
        if (this.recommendationItem != null) {
            Photo picture = this.recommendationItem.getPicture();
            if (!this.loading && picture != null) {
                str = picture.getLargeUrl();
            }
            view.setImageUrl(str);
            view.setupTitle(this.recommendationItem.getTitle(), CardType.Small);
            view.setupSubtext(this.recommendationItem.getSubText(), picture.getDominantSaturatedColor());
            view.setDescriptionText(this.recommendationItem.getDescription());
        } else {
            view.setImageUrl(this.imageUrl);
            view.setupTitle(this.title, CardType.Small);
            view.setDescriptionText(this.description);
            view.setupSubtext(this.actionKicker, ContextCompat.getColor(view.getContext(), C0716R.color.n2_babu));
        }
        view.setOnClickListener(this.clickListener);
        super.bind(view);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0716R.layout.view_holder_recommendation_card_carousel;
        }
        if (isGrid()) {
            return C0716R.layout.view_holder_recommendation_card_grid;
        }
        return C0716R.layout.view_holder_recommendation_card;
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }
}
