package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.p002v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;

public class DetailedReviewsView extends GridLayout {
    @BindView
    RatingCell starRatingAccuracy;
    @BindView
    RatingCell starRatingArrival;
    @BindView
    RatingCell starRatingCleanliness;
    @BindView
    RatingCell starRatingCommunication;
    @BindView
    RatingCell starRatingLocation;
    @BindView
    RatingCell starRatingValue;

    public DetailedReviewsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public DetailedReviewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DetailedReviewsView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0880R.layout.details_review_grid, this));
        setColumnCount(context.obtainStyledAttributes(attrs, C0880R.styleable.DetailedReviewsView).getInt(C0880R.styleable.DetailedReviewsView_columnCount, 1));
        setRowCount((int) Math.ceil(((double) getChildCount()) / ((double) getColumnCount())));
    }

    public void bindViewAndSetupDividers(Listing mListing) {
        this.starRatingAccuracy.setRating(mListing.getReviewStarRatingAccuracy());
        this.starRatingArrival.setRating(mListing.getReviewStarRatingCheckin());
        this.starRatingCleanliness.setRating(mListing.getReviewStarRatingCleanliness());
        this.starRatingCommunication.setRating(mListing.getReviewStarRatingCommunication());
        this.starRatingValue.setRating(mListing.getReviewStarRatingValue());
        this.starRatingLocation.setRating(mListing.getReviewStarRatingLocation());
        for (int i = 0; i < getColumnCount(); i++) {
            ((RatingCell) getChildAt((getChildCount() - i) - 1)).setDividerEnabled(false);
        }
    }

    public void setHorizontalPaddingOnCells(int dimen) {
        this.starRatingAccuracy.setHorizontalPadding(dimen);
        this.starRatingArrival.setHorizontalPadding(dimen);
        this.starRatingCleanliness.setHorizontalPadding(dimen);
        this.starRatingCommunication.setHorizontalPadding(dimen);
        this.starRatingValue.setHorizontalPadding(dimen);
        this.starRatingLocation.setHorizontalPadding(dimen);
    }

    public void setRightPaddingOnRatingBars(int dimen) {
        int padding = getResources().getDimensionPixelOffset(dimen);
        for (int i = 0; i < getColumnCount() - 1; i++) {
            for (int j = 0; j < getRowCount(); j++) {
                ((RatingCell) getChildAt((getColumnCount() * j) + i)).ratingBar.setPadding(0, 0, padding, 0);
            }
        }
    }
}
