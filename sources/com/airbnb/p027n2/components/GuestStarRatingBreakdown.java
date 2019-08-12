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
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.airbnb.n2.components.GuestStarRatingBreakdown */
public class GuestStarRatingBreakdown extends BaseDividerComponent {
    @BindView
    LinearLayout starsContainer;

    /* renamed from: com.airbnb.n2.components.GuestStarRatingBreakdown$StarRatingData */
    public static class StarRatingData {
        final int numRating;
        final float rating;
        final String title;

        public StarRatingData(float rating2, int numRating2, String title2) {
            this.rating = rating2;
            this.numRating = numRating2;
            this.title = title2;
        }
    }

    public GuestStarRatingBreakdown(Context context) {
        super(context);
        init();
    }

    public GuestStarRatingBreakdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuestStarRatingBreakdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_guest_star_rating_breakdown, this);
        ButterKnife.bind((View) this);
        setLayoutParams(new LayoutParams(-1, -2));
    }

    public void setData(List<StarRatingData> listStarRating) {
        View view;
        this.starsContainer.removeAllViews();
        for (StarRatingData data : listStarRating) {
            if (data.numRating != 0) {
                view = inflate(getContext(), R.layout.n2_guest_rating_breakdown_item, null);
                ((RatingBar) ViewLibUtils.findById(view, R.id.rating_stars)).setRating(data.rating);
            } else {
                view = inflate(getContext(), R.layout.n2_guest_rating_breakdown_not_available, null);
            }
            ((TextView) ViewLibUtils.findById(view, R.id.title_text)).setText(data.title);
            this.starsContainer.addView(view);
        }
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_guest_star_rating_breakdown;
    }

    public static void mock(GuestStarRatingBreakdown view) {
        view.setData(Arrays.asList(new StarRatingData[]{new StarRatingData(5.0f, 1, "Cleanliness"), new StarRatingData(5.0f, 1, "Communication"), new StarRatingData(0.0f, 0, "Observance of house rules")}));
    }
}
