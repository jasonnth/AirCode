package com.airbnb.android.lib.adapters;

import android.content.Context;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import java.util.List;

public class ReservationReviewsController extends Typed2AirEpoxyController<List<Review>, Boolean> {
    private final Context context;
    DocumentMarqueeEpoxyModel_ headerModel;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loadingModel;
    private final int reviewsCount;

    public interface Listener {
        void loadMoreReviews();

        boolean shouldShowTranslation(Review review);

        void toggleTranslationState(Review review);
    }

    public ReservationReviewsController(Context context2, Listener listener2, int reviewsCount2) {
        this.context = context2;
        this.listener = listener2;
        this.reviewsCount = reviewsCount2;
        setFilterDuplicates(true);
    }

    public void setData(List<Review> reviews, Boolean showLoading) {
        super.setData(reviews, Check.notNull(showLoading));
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<Review> reviews, Boolean showLoading) {
        this.headerModel.titleText((CharSequence) this.context.getResources().getQuantityString(C0880R.plurals.reviews, this.reviewsCount, new Object[]{Integer.valueOf(this.reviewsCount)}));
        for (Review review : reviews) {
            new HomeReviewRowEpoxyModel_().m4762id(review.getId()).review(review).showTranslation(this.listener.shouldShowTranslation(review)).translateClickListener(ReservationReviewsController$$Lambda$1.lambdaFactory$(this, review)).addTo(this);
        }
        this.loadingModel.onBind(ReservationReviewsController$$Lambda$2.lambdaFactory$(this)).addIf(showLoading.booleanValue(), (EpoxyController) this);
    }
}
