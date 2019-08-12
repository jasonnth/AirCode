package com.airbnb.android.lib.activities.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.ReviewRatingsAdapter;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;
import com.airbnb.android.lib.fragments.reviews.ReviewRatingFragment.RatingUpdatedCallback;
import com.airbnb.android.lib.views.CustomSpeedScroller;
import com.airbnb.android.lib.views.RatingsViewPager;
import com.airbnb.p027n2.primitives.DotsCounter;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.lang.reflect.Field;

public class ReviewRatingsActivity extends AirActivity implements RatingUpdatedCallback {
    private static final String KEY_EDIT_FLAG = "edit";
    private static final String KEY_EDIT_RECOMMEND_FLAG = "edit_recommend";
    private static final String KEY_REVIEW = "review";
    private static final int PAGER_SCROLL_DURATION = 400;
    private static final long SHOW_NEXT_RATING_PAGE_DELAY = 500;
    private static final String TAG = ReviewRatingsActivity.class.getSimpleName();
    private FrameLayout mHeader;
    private RatingsViewPager mViewPager;
    private ReviewRatingsAdapter mViewPagerAdapter;

    public static Intent intentForReview(Context context, Review review) {
        Intent intent = new Intent(context, ReviewRatingsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent intentForEditReview(Context context, Review review) {
        Intent intent = intentForReview(context, review);
        intent.putExtra(KEY_EDIT_FLAG, true);
        return intent;
    }

    public static Intent intentForEditRecommend(Context context, Review review) {
        Intent intent = intentForEditReview(context, review);
        intent.putExtra(KEY_EDIT_RECOMMEND_FLAG, true);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_review_ratings);
        Bundle extras = getIntent().getExtras();
        Review review = (Review) extras.getParcelable("review");
        boolean editMode = extras.getBoolean(KEY_EDIT_FLAG, false);
        User reviewee = review.getRecipient();
        this.mHeader = (FrameLayout) findViewById(C0880R.C0882id.review_ratings_header);
        this.mHeader.setOnClickListener(ReviewRatingsActivity$$Lambda$1.lambdaFactory$(this, reviewee));
        AirImageView mHeaderBackground = (AirImageView) ButterKnife.findById((View) this.mHeader, C0880R.C0882id.review_header_background_image);
        Listing listing = review.getReservation() != null ? review.getReservation().getListing() : null;
        if (listing != null) {
            mHeaderBackground.setImageUrl(listing.getPictureUrl());
        } else {
            mHeaderBackground.setImageResource(C0880R.C0881drawable.hh_default_photo);
        }
        this.mViewPager = (RatingsViewPager) findViewById(C0880R.C0882id.view_pager_ratings);
        setupCustomScroller();
        this.mViewPagerAdapter = new ReviewRatingsAdapter(getSupportFragmentManager(), review, editMode);
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
        final DotsCounter dots = (DotsCounter) findViewById(C0880R.C0882id.dots_counter);
        dots.setNumDots(this.mViewPagerAdapter.getCount());
        this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageSelected(int position) {
                dots.setSelectedDot(position);
            }

            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
        });
        if (extras.getBoolean(KEY_EDIT_RECOMMEND_FLAG, false)) {
            this.mViewPager.setCurrentItem(this.mViewPagerAdapter.getRecommendedIndex());
        }
        TextView guestName = (TextView) ButterKnife.findById((View) this.mHeader, C0880R.C0882id.guest_name);
        TextView listingName = (TextView) ButterKnife.findById((View) this.mHeader, C0880R.C0882id.listing_name);
        TextView reservationDates = (TextView) ButterKnife.findById((View) this.mHeader, C0880R.C0882id.reservation_dates);
        ((HaloImageView) ButterKnife.findById((View) this.mHeader, C0880R.C0882id.guest_image_view)).setImageUrl(reviewee.getPictureUrl());
        String revieweeFirstName = reviewee.getFirstName();
        guestName.setText(revieweeFirstName);
        setupActionBar(C0880R.string.review_ratings_title, revieweeFirstName);
        Reservation reservation = review.getReservation();
        reservationDates.setText(DateHelper.getStringDateSpan((Context) this, reservation.getStartDate(), reservation.getReservedNightsCount()));
        listingName.setText(review.getListingName());
        if (!editMode) {
            ReviewsAnalytics.trackRatingSection(review);
        }
    }

    private void setupCustomScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            CustomSpeedScroller scroller = new CustomSpeedScroller(this.mViewPager.getContext(), new DecelerateInterpolator());
            scroller.setScrollDuration(400);
            scrollerField.set(this.mViewPager, scroller);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            Log.w(TAG, "Unexpected error in setting up Review ViewPager custom scroller", e);
        }
    }

    public void onRatingUpdated() {
        this.mHeader.postDelayed(ReviewRatingsActivity$$Lambda$2.lambdaFactory$(this), SHOW_NEXT_RATING_PAGE_DELAY);
    }

    static /* synthetic */ void lambda$onRatingUpdated$1(ReviewRatingsActivity reviewRatingsActivity) {
        int currItem = reviewRatingsActivity.mViewPager.getCurrentItem();
        if (currItem < reviewRatingsActivity.mViewPagerAdapter.getCount()) {
            reviewRatingsActivity.mViewPager.setCurrentItem(currItem + 1, true);
        }
    }
}
