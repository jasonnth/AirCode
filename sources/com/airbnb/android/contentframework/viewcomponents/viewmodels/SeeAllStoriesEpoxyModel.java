package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.views.SeeAllStoriesCard;
import com.airbnb.android.core.models.StorySeeAllTile;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SeeAllStoriesEpoxyModel extends AirEpoxyModel<SeeAllStoriesCard> {
    OnClickListener onClickListener;
    StorySeeAllTile storySeeAllTile;

    public void bind(SeeAllStoriesCard view) {
        super.bind(view);
        view.bindData(this.storySeeAllTile);
        view.setOnClickListener(this.onClickListener);
    }

    public void unbind(SeeAllStoriesCard view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
