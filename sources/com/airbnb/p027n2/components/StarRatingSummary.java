package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.StarRatingSummary */
public class StarRatingSummary extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    RatingBar ratingBar;
    @BindView
    AirTextView titleText;

    public StarRatingSummary(Context context) {
        super(context);
        init(null);
    }

    public StarRatingSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StarRatingSummary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_star_rating_summary, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_StarRatingSummary, 0, 0);
        String titleText2 = ta.getString(R.styleable.n2_StarRatingSummary_n2_titleText);
        float numStars = ta.getFloat(R.styleable.n2_StarRatingSummary_n2_numStars, 0.0f);
        boolean showDivider = ta.getBoolean(R.styleable.n2_StarRatingSummary_n2_showDivider, true);
        setTitle((CharSequence) titleText2);
        setRating(numStars);
        showDivider(showDivider);
        ta.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setRating(float starRating) {
        this.ratingBar.setRating(starRating);
    }

    public void setUpRatingBar(int numReviews, float starRating) {
        ViewLibUtils.setVisibleIf(this.ratingBar, numReviews >= 3);
        setRating(starRating);
    }

    public void setUpRatingBar(int numReviews, float starRating, int minNumReviewsToShowStars) {
        ViewLibUtils.setVisibleIf(this.ratingBar, numReviews >= minNumReviewsToShowStars);
        setRating(starRating);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(StarRatingSummary summary) {
        summary.setTitle((CharSequence) "Title");
        summary.setRating(5.0f);
    }
}
