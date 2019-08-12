package com.airbnb.android.lib.reviews.fragments;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class ReviewStarFragment_ViewBinding implements Unbinder {
    private ReviewStarFragment target;

    public ReviewStarFragment_ViewBinding(ReviewStarFragment target2, View source) {
        this.target = target2;
        target2.marqee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marqee'", SheetMarquee.class);
        target2.fab = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.fab, "field 'fab'", FloatingActionButton.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.review_rating_stars, "field 'ratingBar'", RatingBar.class);
        target2.ratingDescription = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.description, "field 'ratingDescription'", TextView.class);
    }

    public void unbind() {
        ReviewStarFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marqee = null;
        target2.fab = null;
        target2.ratingBar = null;
        target2.ratingDescription = null;
    }
}
