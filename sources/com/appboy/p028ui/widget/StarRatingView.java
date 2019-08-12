package com.appboy.p028ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.appboy.ui.widget.StarRatingView */
public class StarRatingView extends LinearLayout {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, StarRatingView.class.getName()});
    private float mRating = 0.0f;
    private List<ImageView> mStarRating;

    public StarRatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(0);
        this.mStarRating = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            ImageView star = new ImageView(context);
            star.setTag(Integer.valueOf(0));
            addView(star, new LayoutParams(-2, -2));
            this.mStarRating.add(star);
        }
        setRating(this.mRating);
    }

    public float getRating() {
        return this.mRating;
    }

    public boolean setRating(float rating) {
        if (rating < 0.0f || rating > 5.0f) {
            AppboyLogger.m1735e(TAG, String.format("Unable to set rating to %f. Rating must be between 0 and %d", new Object[]{Float.valueOf(rating), Integer.valueOf(5)}));
            return false;
        }
        this.mRating = rating;
        int ratingRoundedDown = (int) Math.floor((double) this.mRating);
        int ratingRoundedUp = (int) Math.ceil((double) this.mRating);
        for (int starIndex = 0; starIndex < ratingRoundedDown; starIndex++) {
            ImageView star = (ImageView) this.mStarRating.get(starIndex);
            star.setTag(Integer.valueOf(R.drawable.com_appboy_rating_full_star));
            star.setImageResource(R.drawable.com_appboy_rating_full_star);
        }
        for (int starIndex2 = ratingRoundedUp; starIndex2 < this.mStarRating.size(); starIndex2++) {
            ImageView star2 = (ImageView) this.mStarRating.get(starIndex2);
            star2.setTag(Integer.valueOf(R.drawable.com_appboy_rating_empty_star));
            star2.setImageResource(R.drawable.com_appboy_rating_empty_star);
        }
        float remainder = rating - ((float) ratingRoundedDown);
        if (remainder <= 0.0f) {
            return true;
        }
        ImageView star3 = (ImageView) this.mStarRating.get(ratingRoundedDown);
        if (remainder < 0.25f) {
            star3.setTag(Integer.valueOf(R.drawable.com_appboy_rating_empty_star));
            star3.setImageResource(R.drawable.com_appboy_rating_empty_star);
            return true;
        } else if (remainder < 0.75f) {
            star3.setTag(Integer.valueOf(R.drawable.com_appboy_rating_half_star));
            star3.setImageResource(R.drawable.com_appboy_rating_half_star);
            return true;
        } else {
            star3.setTag(Integer.valueOf(R.drawable.com_appboy_rating_full_star));
            star3.setImageResource(R.drawable.com_appboy_rating_full_star);
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<ImageView> getImageViewList() {
        return this.mStarRating;
    }
}
