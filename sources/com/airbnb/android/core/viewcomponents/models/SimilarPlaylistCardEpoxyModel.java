package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.p027n2.components.SimilarPlaylistCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SimilarPlaylistCardEpoxyModel extends AirEpoxyModel<SimilarPlaylistCard> {
    OnClickListener clickListener;
    DisplayOptions displayOptions;
    RecommendationItem recommendationItem;

    public void bind(SimilarPlaylistCard view) {
        super.bind(view);
        view.setupTitle(this.recommendationItem.getTitle());
        view.setupSubtext(this.recommendationItem.getSubText());
        view.setImageUrl(this.recommendationItem.getPicture().getLargeUrl());
        view.setOnClickListener(this.clickListener);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    public void unbind(SimilarPlaylistCard view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.clearImage();
    }
}
