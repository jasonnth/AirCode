package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NestedListingChildRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NestedListingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.Typed4AirEpoxyController;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class NestedListingsOverviewEpoxyController extends Typed4AirEpoxyController<HashMap<Long, NestedListing>, List<NestedListing>, Boolean, Boolean> {
    private static final int CAPTION_MAX_LINES = 10;
    StandardRowEpoxyModel_ captionModel;
    private final Context context;
    DocumentMarqueeEpoxyModel_ headerModel;
    LinkActionRowEpoxyModel_ learnMoreModel;
    StandardRowEpoxyModel_ linkMoreModel;
    private final NestedListingsOverviewListener listener;
    EpoxyControllerLoadingModel_ loadingModel_;

    public interface NestedListingsOverviewListener {
        void onEditExistingParent(NestedListing nestedListing);

        void onLearnMore();

        void onLinkMore();
    }

    public NestedListingsOverviewEpoxyController(Context context2, HashMap<Long, NestedListing> nestedListingsById, List<NestedListing> parents, NestedListingsOverviewListener listener2, boolean canLinkMore, boolean showLoading) {
        this.context = context2;
        this.listener = listener2;
        setData(nestedListingsById, parents, Boolean.valueOf(canLinkMore), Boolean.valueOf(showLoading));
    }

    /* access modifiers changed from: protected */
    public void buildModels(HashMap<Long, NestedListing> nestedListingsById, List<NestedListing> parents, Boolean canLinkMore, Boolean showLoading) {
        String title;
        String subtitle;
        boolean z;
        if (ListUtils.isEmpty((Collection<?>) parents)) {
            title = this.context.getString(C7496R.string.nested_listings_title);
            subtitle = this.context.getString(C7496R.string.nested_listings_subtitle);
        } else {
            title = this.context.getString(C7496R.string.nested_listings_overview_title);
            subtitle = this.context.getString(C7496R.string.nested_listings_overview_subtitle);
        }
        String actionText = this.context.getString(C7496R.string.nested_listings_action_row);
        this.headerModel.titleText((CharSequence) title).addTo(this);
        this.captionModel.title((CharSequence) subtitle).titleMaxLine(10).showDivider(false).addTo(this);
        this.learnMoreModel.textRes(C7496R.string.nested_listings_learn_more).clickListener(NestedListingsOverviewEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.loadingModel_.addIf(showLoading.booleanValue(), (EpoxyController) this);
        if (!showLoading.booleanValue()) {
            for (int parentIndex = 0; parentIndex < parents.size(); parentIndex++) {
                if (parentIndex == parents.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                addParentChildSet(nestedListingsById, (NestedListing) parents.get(parentIndex), this.listener, z & (!canLinkMore.booleanValue()));
            }
            this.linkMoreModel.title((CharSequence) actionText).clickListener(NestedListingsOverviewEpoxyController$$Lambda$2.lambdaFactory$(this)).rowDrawableRes(C7496R.C7497drawable.ic_action_new).showDivider(true).addIf(canLinkMore.booleanValue(), (EpoxyController) this);
        }
    }

    private void addParentChildSet(HashMap<Long, NestedListing> nestedListingsById, NestedListing parent, NestedListingsOverviewListener listener2, boolean isLastSet) {
        boolean z;
        NestedListingRowEpoxyModel_ parentModel = new NestedListingRowEpoxyModel_().m5218id(parent.getId()).title(parent.getName()).subtitle(parent.getRoomTypeForSubtitle(this.context)).clickListener(NestedListingsOverviewEpoxyController$$Lambda$3.lambdaFactory$(listener2, parent)).showDivider(false).isActiveListing(parent.isActive()).disclosure();
        String imageUrl = parent.getThumbnailUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            parentModel.imageUrl(imageUrl);
        } else {
            parentModel.imageDrawableRes(C7496R.C7497drawable.camera_icon_bg);
        }
        parentModel.addTo(this);
        for (int childiIndex = 0; childiIndex < parent.getNumberOfChildren(); childiIndex++) {
            NestedListing child = (NestedListing) nestedListingsById.get(parent.getChildListingIds().get(childiIndex));
            NestedListingChildRowEpoxyModel_ isActiveListing = new NestedListingChildRowEpoxyModel_().m5194id(child.getId()).title(child.getName()).subtitle(child.getRoomTypeForSubtitle(this.context)).isActiveListing(child.isActive());
            if (isLastSet || childiIndex != parent.getNumberOfChildren() - 1) {
                z = false;
            } else {
                z = true;
            }
            NestedListingChildRowEpoxyModel_ childModel = isActiveListing.showDivider(z);
            String childImageUrl = child.getThumbnailUrl();
            if (!TextUtils.isEmpty(childImageUrl)) {
                childModel.imageUrl(childImageUrl);
            } else {
                childModel.imageDrawableRes(C7496R.C7497drawable.camera_icon_bg);
            }
            childModel.addTo(this);
        }
    }
}
