package com.airbnb.android.core.views;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.HomeReviewRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.primitives.AirTextView;

public class P3ReviewsRow_ViewBinding implements Unbinder {
    private P3ReviewsRow target;

    public P3ReviewsRow_ViewBinding(P3ReviewsRow target2) {
        this(target2, target2);
    }

    public P3ReviewsRow_ViewBinding(P3ReviewsRow target2, View source) {
        this.target = target2;
        target2.sectionHeader = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.p3_reviews_header, "field 'sectionHeader'", AirTextView.class);
        target2.inputTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.user_input, "field 'inputTextView'", AirTextView.class);
        target2.seeAllContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.see_all_container, "field 'seeAllContainer'", ViewGroup.class);
        target2.allRatingBar = (RatingBar) Utils.findRequiredViewAsType(source, C0716R.C0718id.all_rating_bar, "field 'allRatingBar'", RatingBar.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C0716R.C0718id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
        target2.divider = Utils.findRequiredView(source, C0716R.C0718id.p3_reviews_divider, "field 'divider'");
        target2.reviewRows = Utils.listOf((HomeReviewRow) Utils.findRequiredViewAsType(source, C0716R.C0718id.review_row_1, "field 'reviewRows'", HomeReviewRow.class), (HomeReviewRow) Utils.findRequiredViewAsType(source, C0716R.C0718id.review_row_2, "field 'reviewRows'", HomeReviewRow.class), (HomeReviewRow) Utils.findRequiredViewAsType(source, C0716R.C0718id.review_row_3, "field 'reviewRows'", HomeReviewRow.class));
    }

    public void unbind() {
        P3ReviewsRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sectionHeader = null;
        target2.inputTextView = null;
        target2.seeAllContainer = null;
        target2.allRatingBar = null;
        target2.refreshLoader = null;
        target2.divider = null;
        target2.reviewRows = null;
    }
}
