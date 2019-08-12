package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.airbnb.n2.components.HomeStarRatingBreakdown */
public class HomeStarRatingBreakdown extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    LinearLayout starsContainer;

    /* renamed from: com.airbnb.n2.components.HomeStarRatingBreakdown$StarRatingData */
    public static class StarRatingData {
        final float rating;
        final String title;

        public StarRatingData(float rating2, String title2) {
            this.rating = rating2;
            this.title = title2;
        }
    }

    public HomeStarRatingBreakdown(Context context) {
        super(context);
        init();
    }

    public HomeStarRatingBreakdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeStarRatingBreakdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_home_star_rating_breakdown, this);
        ButterKnife.bind((View) this);
        setLayoutParams(new LayoutParams(-1, -2));
    }

    public void setData(List<StarRatingData> listStarRating) {
        this.starsContainer.removeAllViews();
        for (StarRatingData data : listStarRating) {
            View view = inflate(getContext(), R.layout.n2_rating_breakdown_item, null);
            RatingBar ratingBar = (RatingBar) ViewLibUtils.findById(view, R.id.rating_stars);
            ((TextView) ViewLibUtils.findById(view, R.id.title_text)).setText(data.title);
            ratingBar.setRating(data.rating);
            this.starsContainer.addView(view);
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(HomeStarRatingBreakdown view) {
        view.setData(Arrays.asList(new StarRatingData[]{new StarRatingData(5.0f, "Accuracy"), new StarRatingData(3.0f, "Check In"), new StarRatingData(4.0f, "Cleanliness"), new StarRatingData(2.5f, "Communication"), new StarRatingData(0.5f, "Location"), new StarRatingData(0.0f, "Value")}));
    }
}
