package com.airbnb.android.core.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.HomeReviewRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class P3ReviewsRow extends LinearLayout implements DividerView {
    private static final int MAX_LINES = 3;
    @BindView
    RatingBar allRatingBar;
    @BindView
    View divider;
    @BindView
    AirTextView inputTextView;
    @BindView
    RefreshLoader refreshLoader;
    @BindViews
    List<HomeReviewRow> reviewRows;
    @BindView
    AirTextView sectionHeader;
    @BindView
    ViewGroup seeAllContainer;

    public P3ReviewsRow(Context context) {
        super(context);
        init();
    }

    public P3ReviewsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public P3ReviewsRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.p3_reviews_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        for (HomeReviewRow reviewRow : this.reviewRows) {
            reviewRow.setMaxLines(3);
        }
    }

    public int getNumReviewsSupported() {
        return this.reviewRows.size();
    }

    public HomeReviewRow getReviewRow(int reviewIndex) {
        if (reviewIndex <= this.reviewRows.size()) {
            return (HomeReviewRow) this.reviewRows.get(reviewIndex);
        }
        throw new IllegalArgumentException("A maximum of " + this.reviewRows.size() + " rows are supported");
    }

    public void setInputText(CharSequence inputText) {
        this.inputTextView.setText(inputText);
        setContentDescription();
    }

    public void setUpRatingBar(int numReviews, float starRating) {
        ViewLibUtils.setVisibleIf(this.allRatingBar, numReviews >= 3);
        this.allRatingBar.setRating(starRating);
        setContentDescription();
    }

    public void setHeaderText(int textRes) {
        this.sectionHeader.setText(textRes);
    }

    public void setLoading(boolean loading) {
        ViewLibUtils.setVisibleIf(this.refreshLoader, loading);
    }

    public void setReadAllReviewsClickListener(OnClickListener clickListener) {
        this.seeAllContainer.setOnClickListener(clickListener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    private void setContentDescription() {
        String reviewDescription;
        if (this.allRatingBar.getRating() == 0.0f) {
            reviewDescription = "";
        } else {
            reviewDescription = getContext().getResources().getQuantityString(C0716R.plurals.rating_bar_stars_content_description, (int) Math.ceil((double) this.allRatingBar.getRating()), new Object[]{Float.valueOf(this.allRatingBar.getRating())});
        }
        this.seeAllContainer.setContentDescription(reviewDescription + (TextUtils.isEmpty(this.inputTextView.getText()) ? "" : this.inputTextView.getText().toString()));
    }
}
