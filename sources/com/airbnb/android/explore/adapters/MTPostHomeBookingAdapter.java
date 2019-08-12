package com.airbnb.android.explore.adapters;

import android.app.Activity;
import android.text.TextUtils;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.intents.ExploreIntents;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RecommendationRowEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.RecommendationRow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MTPostHomeBookingAdapter extends AirEpoxyAdapter {
    private final Activity activity;

    public MTPostHomeBookingAdapter(Activity activity2) {
        this.activity = activity2;
        enableDiffing();
        setFilterDuplicates(true);
    }

    public void refreshData(List<ExploreSection> items, String title, String subtitle) {
        this.models.clear();
        if (ListUtils.isEmpty((Collection<?>) items)) {
            notifyModelsChanged();
            return;
        }
        this.models.add(new DocumentMarqueeEpoxyModel_().captionText((CharSequence) subtitle).titleText((CharSequence) title).m4536id((CharSequence) title));
        for (int i = 0; i < items.size(); i++) {
            this.models.addAll(buildModelsForSection((ExploreSection) items.get(i), i));
        }
        this.models.add(new ListSpacerEpoxyModel_().spaceHeightRes(C0857R.dimen.post_home_booking_list_bottom_padding));
        notifyModelsChanged();
    }

    private List<EpoxyModel<?>> buildModelsForSection(ExploreSection section, int sectionIndex) {
        boolean z;
        if (!ResultType.RecommendationItems.equals(section.getResultType())) {
            return new ArrayList();
        }
        List<List<RecommendationItem>> listOfGroupings = RecommendationItem.collectRecommendationItemRowGroupings(section.getRecommendationItems(), section.getDisplayLayout());
        List<EpoxyModel<?>> recommendationItems = new ArrayList<>(listOfGroupings.size());
        for (int i = 0; i < listOfGroupings.size(); i++) {
            List<RecommendationItem> grouping = (List) listOfGroupings.get(i);
            DisplayType displayType = section.getDisplayType();
            if (i == listOfGroupings.size() - 1) {
                z = true;
            } else {
                z = false;
            }
            recommendationItems.add(buildRecommendationRowModel(grouping, displayType, sectionIndex, z));
        }
        if (TextUtils.isEmpty(section.getTitle())) {
            return recommendationItems;
        }
        recommendationItems.add(0, getHeaderModel(section));
        return recommendationItems;
    }

    private EpoxyModel<RecommendationRow> buildRecommendationRowModel(List<RecommendationItem> recommendationItems, DisplayType displayType, int sectionIndex, boolean showBottomSpace) {
        return new RecommendationRowEpoxyModel_().recommendationItems(recommendationItems).displayOptions(DisplayOptions.forRecommendationRow(this.activity, displayType)).clickListener(MTPostHomeBookingAdapter$$Lambda$1.lambdaFactory$(this, recommendationItems)).showBottomSpace(showBottomSpace).m5424id((CharSequence) String.valueOf(((RecommendationItem) recommendationItems.get(0)).getId()) + sectionIndex);
    }

    private EpoxyModel getHeaderModel(ExploreSection section) {
        return new EditorialSectionHeaderEpoxyModel_().title(section.getTitle()).description(section.getSubtitle()).sectionId(section.getSectionId()).showDivider(false).m4560id((CharSequence) section.getSectionId());
    }

    /* access modifiers changed from: private */
    public void handleRecommendationItemClick(RecommendationItem item) {
        this.activity.startActivity(ExploreIntents.forType(this.activity, item));
    }
}
