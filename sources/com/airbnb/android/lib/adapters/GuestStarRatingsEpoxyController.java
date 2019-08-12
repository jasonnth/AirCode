package com.airbnb.android.lib.adapters;

import android.content.Context;
import com.airbnb.android.core.models.ReviewRatingCategoryAsGuest;
import com.airbnb.android.core.models.ReviewRatingsAsGuest;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.GuestRatingsMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GuestStarRatingBreakdownEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;

public class GuestStarRatingsEpoxyController extends TypedAirEpoxyController<User> {
    private final Context context;
    GuestRatingsMarqueeEpoxyModel_ guestRatingsMarquee;
    GuestStarRatingBreakdownEpoxyModel_ guestStarRatingBreakdown;
    LinkActionRowEpoxyModel_ linkRow;
    private final GuestStarRatingsListener listener;

    public interface GuestStarRatingsListener {
        void onSeeAllReviews();
    }

    public GuestStarRatingsEpoxyController(Context context2, User guest, GuestStarRatingsListener listener2) {
        this.context = context2;
        this.listener = listener2;
        setData(guest);
    }

    /* access modifiers changed from: protected */
    public void buildModels(User guest) {
        ReviewRatingsAsGuest reviewRatingsAsGuest = guest.getReviewRatingsAsGuest();
        this.guestRatingsMarquee.guestName(guest.getFirstName()).numStars(reviewRatingsAsGuest.getOverall().getAverageRating()).addTo(this);
        ReviewRatingCategoryAsGuest cleanliness = reviewRatingsAsGuest.getCleanliness();
        ReviewRatingCategoryAsGuest communication = reviewRatingsAsGuest.getCommunication();
        ReviewRatingCategoryAsGuest houseRules = reviewRatingsAsGuest.getRespectHouseRules();
        this.guestStarRatingBreakdown.ratingCleanliness(cleanliness.getAverageRating()).numCleanliness(cleanliness.getReviewsCount()).ratingCommunication(communication.getAverageRating()).numCommunication(communication.getReviewsCount()).ratingHouseRules(houseRules.getAverageRating()).numHouseRules(houseRules.getReviewsCount()).showDivider(true).addTo(this);
        this.linkRow.text(this.context.getString(C0880R.string.see_all_reviews)).clickListener(GuestStarRatingsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
    }
}
