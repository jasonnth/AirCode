package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.HomeStarRatingBreakdown;
import com.airbnb.p027n2.components.HomeStarRatingBreakdown.StarRatingData;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;

public abstract class HomeStarRatingBreakdownEpoxyModel extends AirEpoxyModel<HomeStarRatingBreakdown> {
    float reviewStarRatingAccuracy;
    float reviewStarRatingCheckin;
    float reviewStarRatingCleanliness;
    float reviewStarRatingCommunication;
    float reviewStarRatingLocation;
    float reviewStarRatingValue;

    public void bind(HomeStarRatingBreakdown view) {
        super.bind(view);
        ArrayList<StarRatingData> list = new ArrayList<>(5);
        Resources res = view.getResources();
        list.add(new StarRatingData(this.reviewStarRatingAccuracy, res.getString(C0716R.string.review_accuracy_title)));
        list.add(new StarRatingData(this.reviewStarRatingCheckin, res.getString(C0716R.string.review_check_in_title)));
        list.add(new StarRatingData(this.reviewStarRatingCleanliness, res.getString(C0716R.string.review_cleanliness_title)));
        list.add(new StarRatingData(this.reviewStarRatingCommunication, res.getString(C0716R.string.review_communication_title)));
        list.add(new StarRatingData(this.reviewStarRatingLocation, res.getString(C0716R.string.review_location_title)));
        list.add(new StarRatingData(this.reviewStarRatingValue, res.getString(C0716R.string.review_value_title)));
        view.setData(list);
    }

    public int getDividerViewType() {
        return 0;
    }
}
