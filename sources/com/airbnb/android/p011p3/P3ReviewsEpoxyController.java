package com.airbnb.android.p011p3;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeStarRatingBreakdownEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ReviewMarqueeEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.Iterator;

/* renamed from: com.airbnb.android.p3.P3ReviewsEpoxyController */
public class P3ReviewsEpoxyController extends AirEpoxyController {
    private final ReviewsController controller;
    private final P3ReviewFragment fragment;
    EpoxyControllerLoadingModel_ loaderEpoxyModel;
    ReviewMarqueeEpoxyModel_ reviewMarqueeEpoxyModel;
    HomeStarRatingBreakdownEpoxyModel_ starBreakdownEpoxyModel;

    public P3ReviewsEpoxyController(P3ReviewFragment fragment2, ReviewsController controller2) {
        this.fragment = fragment2;
        this.controller = controller2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.reviewMarqueeEpoxyModel.numStars(this.controller.getStarRating()).numReviews(this.controller.getTotalReviewsCount());
        this.starBreakdownEpoxyModel.reviewStarRatingAccuracy(this.controller.getReviewStarRatingAccuracy()).reviewStarRatingCheckin(this.controller.getReviewStarRatingCheckin()).reviewStarRatingCleanliness(this.controller.getReviewStarRatingCleanliness()).reviewStarRatingCommunication(this.controller.getReviewStarRatingCommunication()).reviewStarRatingLocation(this.controller.getReviewStarRatingLocation()).reviewStarRatingValue(this.controller.getReviewStarRatingValue()).addIf(this.controller.getTotalReviewsCount() >= 3, (EpoxyController) this);
        Iterator it = this.controller.getReviews().iterator();
        while (it.hasNext()) {
            addReviewModel((Review) it.next());
        }
        this.loaderEpoxyModel.onBind(P3ReviewsEpoxyController$$Lambda$1.lambdaFactory$(this)).addIf(this.controller.hasMoreToLoad(), (EpoxyController) this);
    }

    static /* synthetic */ void lambda$buildModels$0(P3ReviewsEpoxyController p3ReviewsEpoxyController, EpoxyControllerLoadingModel_ model, RefreshLoader view, int position) {
        if (p3ReviewsEpoxyController.controller.hasMoreToLoad()) {
            p3ReviewsEpoxyController.controller.fetchNextPage();
        }
    }

    private void addReviewModel(Review review) {
        boolean flagged = review.shouldShowFlagged();
        new HomeReviewRowEpoxyModel_().m4762id(review.getId()).review(review).showTranslation(this.controller.shouldShowTranslation(review)).translateClickListener(P3ReviewsEpoxyController$$Lambda$2.lambdaFactory$(this, review)).reportString(flagged ? C7532R.string.reported_review_text : C7532R.string.report_review_link_text).reportClickListener(flagged ? null : P3ReviewsEpoxyController$$Lambda$3.lambdaFactory$(this, review)).addTo(this);
    }

    static /* synthetic */ void lambda$addReviewModel$2(P3ReviewsEpoxyController p3ReviewsEpoxyController, Review review, View view) {
        Long valueOf;
        UserFlag flag = review.getUserFlag();
        P3ReviewFragment p3ReviewFragment = p3ReviewsEpoxyController.fragment;
        Context context = p3ReviewsEpoxyController.fragment.getContext();
        long id = review.getId();
        if (flag == null) {
            valueOf = null;
        } else {
            valueOf = Long.valueOf(flag.getId());
        }
        p3ReviewFragment.startActivityForResult(ReactNativeIntents.intentForFlagContent(context, id, valueOf, FlagContent.Review), 42);
    }
}
