package com.airbnb.p027n2.components;

import android.content.Context;
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

/* renamed from: com.airbnb.n2.components.ReviewMarquee */
public class ReviewMarquee extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    RatingBar ratingBar;
    @BindView
    AirTextView titleTextView;

    public ReviewMarquee(Context context) {
        super(context);
        init();
    }

    public ReviewMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReviewMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.n2_review_marquee, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence string) {
        this.titleTextView.setText(string);
    }

    public void setNumStars(float starRating) {
        this.ratingBar.setRating(starRating);
        ViewLibUtils.setVisibleIf(this.ratingBar, starRating != 0.0f);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(ReviewMarquee view) {
        view.setTitle("Title");
        view.setNumStars(4.0f);
    }
}
