package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.GuestStarRatingBreakdown;
import com.airbnb.p027n2.components.GuestStarRatingBreakdown.StarRatingData;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;

public abstract class GuestStarRatingBreakdownEpoxyModel extends AirEpoxyModel<GuestStarRatingBreakdown> {
    int numCleanliness;
    int numCommunication;
    int numHouseRules;
    float ratingCleanliness;
    float ratingCommunication;
    float ratingHouseRules;

    public void bind(GuestStarRatingBreakdown view) {
        super.bind(view);
        final Resources res = view.getResources();
        view.setData(new ArrayList<StarRatingData>() {
            {
                add(new StarRatingData(GuestStarRatingBreakdownEpoxyModel.this.ratingCleanliness, GuestStarRatingBreakdownEpoxyModel.this.numCleanliness, res.getString(C0716R.string.guest_ratings_cleanliness)));
                add(new StarRatingData(GuestStarRatingBreakdownEpoxyModel.this.ratingCommunication, GuestStarRatingBreakdownEpoxyModel.this.numCommunication, res.getString(C0716R.string.guest_ratings_communication)));
                add(new StarRatingData(GuestStarRatingBreakdownEpoxyModel.this.ratingHouseRules, GuestStarRatingBreakdownEpoxyModel.this.numHouseRules, res.getString(C0716R.string.guest_ratings_house_rules)));
            }
        });
    }

    public int getDividerViewType() {
        return 0;
    }
}
