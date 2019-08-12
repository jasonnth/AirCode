package com.airbnb.android.lib.adapters;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.Review.RatingType;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.fragments.reviews.ReviewRatingFragment;
import com.airbnb.android.lib.fragments.reviews.ReviewRecommendFragment;
import java.util.List;

public class ReviewRatingsAdapter extends BaseFragmentStatePagerAdapter {
    private final boolean mEditMode;
    private final List<RatingType> mRatingTypes = this.mReview.getRatingTypes();
    private final Review mReview;

    public interface CanProgress {
        boolean canProgress();
    }

    public interface SetEditMode {
        void setEditMode(boolean z);
    }

    public ReviewRatingsAdapter(FragmentManager fm, Review review, boolean editMode) {
        super(fm);
        this.mReview = review;
        this.mEditMode = editMode;
    }

    public Fragment getItem(int position) {
        Fragment fragment;
        RatingType type = (RatingType) this.mRatingTypes.get(position);
        switch (type) {
            case Recommend:
                fragment = ReviewRecommendFragment.newInstance(this.mReview);
                break;
            default:
                fragment = ReviewRatingFragment.newInstance(this.mReview, type);
                break;
        }
        if (fragment instanceof SetEditMode) {
            ((SetEditMode) fragment).setEditMode(this.mEditMode);
        }
        return fragment;
    }

    public Fragment getActiveItem(int position) {
        return getCachedFragment(position);
    }

    public int getCount() {
        return this.mRatingTypes.size();
    }

    public boolean canAdvance(int position) {
        Fragment fragment = getActiveItem(position);
        if (fragment instanceof CanProgress) {
            return ((CanProgress) fragment).canProgress();
        }
        if (!BuildHelper.isDevelopmentBuild()) {
            return false;
        }
        throw new IllegalArgumentException(fragment.getClass().getName() + " must implement CanProgress");
    }

    public int getRecommendedIndex() {
        return this.mRatingTypes.size() - 1;
    }
}
