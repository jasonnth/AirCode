package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.P3ReviewsRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.List;

/* renamed from: com.airbnb.android.p3.BaseP3EpoxyController */
public abstract class BaseP3EpoxyController extends AirEpoxyController {
    public static final int NUM_REVIEWS_TO_SHOW = 1;
    protected final BusinessTravelAccountManager businessTravelAccountManager;
    protected final Context context;
    protected final ListingDetailsController controller;
    private long scrollToModelId;
    private int scrollToModelIndex = -1;

    /* access modifiers changed from: protected */
    public abstract String getTagForModelId(long j);

    public BaseP3EpoxyController(Context context2, ListingDetailsController controller2, BusinessTravelAccountManager businessTravelAccountManager2, Bundle savedInstanceState) {
        this.context = context2;
        this.controller = controller2;
        this.businessTravelAccountManager = businessTravelAccountManager2;
        onRestoreInstanceState(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void setUpAndAddReviewRow(P3ReviewsRowEpoxyModel_ reviewsRowEpoxyModel) {
        boolean loadingReviews;
        boolean z = false;
        ReviewsController reviewsController = this.controller.getReviewsController();
        List<HomeReviewRowEpoxyModel_> reviewsToShow = reviewsController.getReviews().limit(1).transform(BaseP3EpoxyController$$Lambda$1.lambdaFactory$(this)).toList();
        if (!reviewsToShow.isEmpty() || !reviewsController.hasMoreToLoad()) {
            loadingReviews = false;
        } else {
            loadingReviews = true;
        }
        P3ReviewsRowEpoxyModel_ clickListener = reviewsRowEpoxyModel.reviewModels(reviewsToShow).loading(loadingReviews).reviewsCount(reviewsController.getTotalReviewsCount()).starRating(reviewsController.getStarRating()).clickListener(BaseP3EpoxyController$$Lambda$2.lambdaFactory$(this));
        if (loadingReviews || !reviewsToShow.isEmpty()) {
            z = true;
        }
        clickListener.addIf(z, (EpoxyController) this);
    }

    /* access modifiers changed from: private */
    public HomeReviewRowEpoxyModel_ buildReviewRowModel(Review review) {
        return new HomeReviewRowEpoxyModel_().m4762id(review.getId()).review(review).showTranslation(this.controller.getReviewsController().shouldShowTranslation(review)).translateClickListener(BaseP3EpoxyController$$Lambda$3.lambdaFactory$(this, review));
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> boundModel, int position, EpoxyModel<?> epoxyModel) {
        if (position > this.scrollToModelIndex) {
            this.scrollToModelIndex = position;
            this.scrollToModelId = boundModel.mo11715id();
        }
    }

    public String getScrollToModelTag() {
        if (this.scrollToModelId == 0) {
            return "listing_not_loaded";
        }
        String tag = getTagForModelId(this.scrollToModelId);
        if (tag != null) {
            return tag;
        }
        EpoxyModel<?> model = getAdapter().getModelById(this.scrollToModelId);
        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unknown scroll to section on P3: " + (model != null ? model.getClass().getSimpleName() : String.valueOf(this.scrollToModelId))));
        return "unknown_section";
    }
}
