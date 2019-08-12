package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.HostRatingBreakdown;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.RatingCell;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ReviewCategoryBreakdownView extends LinearLayout implements DividerView {
    @BindView
    RatingCell accuracyAverage;
    @BindView
    RatingCell checkInAverage;
    @BindView
    RatingCell cleanlinessAverage;
    @BindView
    RatingCell communicationAverage;
    @BindView
    View divider;
    @BindView
    RatingCell locationAverage;
    @BindView
    RatingCell overallAverage;
    @BindView
    RatingCell valueAverage;

    public ReviewCategoryBreakdownView(Context context) {
        super(context);
        init();
    }

    public ReviewCategoryBreakdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReviewCategoryBreakdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(C0880R.layout.review_category_breakdown_view, this);
        ButterKnife.bind((View) this);
    }

    public void setRatingBreakdown(HostRatingBreakdown ratingBreakdown) {
        if (ratingBreakdown != null) {
            this.overallAverage.setRating(ratingBreakdown.getOverall());
            this.accuracyAverage.setRating(ratingBreakdown.getAccuracy());
            this.communicationAverage.setRating(ratingBreakdown.getCommunication());
            this.cleanlinessAverage.setRating(ratingBreakdown.getCleanliness());
            this.locationAverage.setRating(ratingBreakdown.getLocation());
            this.checkInAverage.setRating(ratingBreakdown.getCheckIn());
            this.valueAverage.setRating(ratingBreakdown.getValue());
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
