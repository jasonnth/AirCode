package com.airbnb.android.lib.fragments.reviews;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;

class ReviewAdapter extends AirEpoxyAdapter {
    ReviewAdapter(Context context, Review review, long loggedInUserId) {
        boolean hasPrivateFeedback;
        super(true);
        String privateFeedback = review.getCombinedPrivateFeedback();
        if (!TextUtils.isEmpty(privateFeedback)) {
            hasPrivateFeedback = true;
        } else {
            hasPrivateFeedback = false;
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{createMarquee(context, review, loggedInUserId, hasPrivateFeedback), createReviewRow(review)});
        if (hasPrivateFeedback) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{createPrivateFeedbackHeaderRow(), createPrivateFeedbackContentRow(privateFeedback)});
        }
    }

    private static DocumentMarqueeEpoxyModel createMarquee(Context context, Review review, long loggedInUserId, boolean hasPrivateFeedback) {
        boolean isAuthor;
        String title;
        if (loggedInUserId == review.getAuthorId()) {
            isAuthor = true;
        } else {
            isAuthor = false;
        }
        if (isAuthor) {
            title = context.getString(C0880R.string.ro_review_title_from_you);
        } else {
            title = context.getString(C0880R.string.ro_review_title_from_other, new Object[]{review.getAuthor().getName()});
        }
        DocumentMarqueeEpoxyModel_ model = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) title);
        if (!isAuthor && hasPrivateFeedback) {
            model.captionRes(review.isGuestReviewingHost() ? C0880R.string.private_feedback_review_info_host : C0880R.string.private_feedback_review_info_guest);
        }
        return model;
    }

    private static HomeReviewRowEpoxyModel_ createReviewRow(Review review) {
        return new HomeReviewRowEpoxyModel_().review(review);
    }

    private static MicroSectionHeaderEpoxyModel_ createPrivateFeedbackHeaderRow() {
        return new MicroSectionHeaderEpoxyModel_().titleRes(C0880R.string.private_feedback_title);
    }

    private static TextRowEpoxyModel_ createPrivateFeedbackContentRow(String privateFeedback) {
        return new TextRowEpoxyModel_().text(privateFeedback);
    }
}
