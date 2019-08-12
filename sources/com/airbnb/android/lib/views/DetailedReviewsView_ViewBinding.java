package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class DetailedReviewsView_ViewBinding implements Unbinder {
    private DetailedReviewsView target;

    public DetailedReviewsView_ViewBinding(DetailedReviewsView target2) {
        this(target2, target2);
    }

    public DetailedReviewsView_ViewBinding(DetailedReviewsView target2, View source) {
        this.target = target2;
        target2.starRatingAccuracy = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_accuracy, "field 'starRatingAccuracy'", RatingCell.class);
        target2.starRatingCommunication = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_communication, "field 'starRatingCommunication'", RatingCell.class);
        target2.starRatingCleanliness = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_cleanliness, "field 'starRatingCleanliness'", RatingCell.class);
        target2.starRatingLocation = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_location, "field 'starRatingLocation'", RatingCell.class);
        target2.starRatingArrival = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_checkin, "field 'starRatingArrival'", RatingCell.class);
        target2.starRatingValue = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_value, "field 'starRatingValue'", RatingCell.class);
    }

    public void unbind() {
        DetailedReviewsView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.starRatingAccuracy = null;
        target2.starRatingCommunication = null;
        target2.starRatingCleanliness = null;
        target2.starRatingLocation = null;
        target2.starRatingArrival = null;
        target2.starRatingValue = null;
    }
}
