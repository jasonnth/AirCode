package com.airbnb.android.p011p3;

import android.content.Context;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.ReviewKeyword;
import com.airbnb.android.core.models.ReviewSearchResult;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.List;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchController */
public class P3ReviewSearchController extends AirEpoxyController {
    private final Context context;
    StandardRowEpoxyModel_ filterHeaderModel;
    private final ReviewSearchListener listener;
    EpoxyControllerLoadingModel_ loaderModel;
    StandardRowEpoxyModel_ noResultsModel;

    /* renamed from: com.airbnb.android.p3.P3ReviewSearchController$ReviewSearchListener */
    public interface ReviewSearchListener {
        List<ReviewKeyword> getKeywords();

        List<ReviewSearchResult> getResults();

        boolean isLoading();

        void onClickKeyword(ReviewKeyword reviewKeyword);
    }

    public P3ReviewSearchController(Context context2, ReviewSearchListener listener2) {
        this.context = context2;
        this.listener = listener2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        boolean z;
        if (this.listener.isLoading()) {
            add((EpoxyModel<?>) this.loaderModel);
        } else if (this.listener.getResults() != null) {
            this.noResultsModel.title(C7532R.string.review_search_no_results).addIf(this.listener.getResults().isEmpty(), (EpoxyController) this);
            for (ReviewSearchResult result : this.listener.getResults()) {
                Review review = result.getReview();
                new HomeReviewRowEpoxyModel_().m4762id(review.getId()).review(review).attributedTextRangeList(result.getAttributedTextsMatchRanges()).addTo(this);
            }
        } else if (this.listener.getKeywords() != null) {
            StandardRowEpoxyModel_ showDivider = this.filterHeaderModel.title(C7532R.string.review_search_filter_by_keyword).showDivider(false);
            if (!this.listener.getKeywords().isEmpty()) {
                z = true;
            } else {
                z = false;
            }
            showDivider.addIf(z, (EpoxyController) this);
            for (ReviewKeyword keyword : this.listener.getKeywords()) {
                new StandardRowEpoxyModel_().m5604id((CharSequence) keyword.getTerm()).title((CharSequence) TextUtil.makeColored(this.context, C7532R.color.n2_text_color_actionable, keyword.getTerm() + " " + this.context.getString(C7532R.string.review_search_filter_keyword_number, new Object[]{keyword.getHits()}))).clickListener(P3ReviewSearchController$$Lambda$1.lambdaFactory$(this, keyword)).addTo(this);
            }
        }
    }
}
