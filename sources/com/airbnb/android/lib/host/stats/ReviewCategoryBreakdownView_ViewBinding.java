package com.airbnb.android.lib.host.stats;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.RatingCell;

public class ReviewCategoryBreakdownView_ViewBinding implements Unbinder {
    private ReviewCategoryBreakdownView target;

    public ReviewCategoryBreakdownView_ViewBinding(ReviewCategoryBreakdownView target2) {
        this(target2, target2);
    }

    public ReviewCategoryBreakdownView_ViewBinding(ReviewCategoryBreakdownView target2, View source) {
        this.target = target2;
        target2.overallAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.overall_rating, "field 'overallAverage'", RatingCell.class);
        target2.accuracyAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.accuracy_rating, "field 'accuracyAverage'", RatingCell.class);
        target2.communicationAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.communication_rating, "field 'communicationAverage'", RatingCell.class);
        target2.cleanlinessAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.cleanliness_rating, "field 'cleanlinessAverage'", RatingCell.class);
        target2.locationAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.location_rating, "field 'locationAverage'", RatingCell.class);
        target2.checkInAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.check_in_rating, "field 'checkInAverage'", RatingCell.class);
        target2.valueAverage = (RatingCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.value_rating, "field 'valueAverage'", RatingCell.class);
        target2.divider = Utils.findRequiredView(source, C0880R.C0882id.category_breakdown_divider, "field 'divider'");
    }

    public void unbind() {
        ReviewCategoryBreakdownView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.overallAverage = null;
        target2.accuracyAverage = null;
        target2.communicationAverage = null;
        target2.cleanlinessAverage = null;
        target2.locationAverage = null;
        target2.checkInAverage = null;
        target2.valueAverage = null;
        target2.divider = null;
    }
}
