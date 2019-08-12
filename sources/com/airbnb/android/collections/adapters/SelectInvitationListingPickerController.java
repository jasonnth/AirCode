package com.airbnb.android.collections.adapters;

import android.content.Context;
import com.airbnb.android.collections.C5698R;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.HomesCollectionsApplicationsListing;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingInfoCardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PromotionMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import java.util.List;

public class SelectInvitationListingPickerController extends Typed2AirEpoxyController<List<HomeCollectionApplication>, Boolean> {
    private static final int ONE_LISTING = 1;
    SimpleTextRowEpoxyModel_ actionRow;
    private final Context context;
    private final String firstName;
    private final OnListingClickedListener listener;
    EpoxyControllerLoadingModel_ loaderRow;
    PromotionMarqueeEpoxyModel_ marquee;
    ListSpacerEpoxyModel_ optionalSpace;
    ToolbarSpacerEpoxyModel_ spacer;

    public interface OnListingClickedListener {
        void onListingClicked(HomeCollectionApplication homeCollectionApplication);
    }

    public SelectInvitationListingPickerController(Context context2, String firstName2, OnListingClickedListener listener2) {
        this.context = context2;
        this.firstName = firstName2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<HomeCollectionApplication> data, Boolean closeToPassSelect) {
        boolean z;
        boolean z2;
        this.spacer.addTo(this);
        if (data == null) {
            this.loaderRow.withInverseLayout();
            return;
        }
        this.marquee.title(getTitleTextRes(closeToPassSelect.booleanValue())).caption(getCaptionTextRes(closeToPassSelect.booleanValue())).addTo(this);
        SimpleTextRowEpoxyModel_ showDivider = this.actionRow.largeAndInverse().textRes(C5698R.string.select_listing_title).showDivider(false);
        if (data.size() > 1) {
            z = true;
        } else {
            z = false;
        }
        showDivider.addIf(z, (EpoxyController) this);
        ListSpacerEpoxyModel_ spaceHeightRes = this.optionalSpace.spaceHeightRes(C5698R.dimen.n2_vertical_padding_small);
        if (data.size() <= 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        spaceHeightRes.addIf(z2, (EpoxyController) this);
        for (HomeCollectionApplication application : data) {
            HomesCollectionsApplicationsListing listing = application.getListing();
            new ListingInfoCardRowEpoxyModel_().m5086id(listing.getId()).title(listing.getName()).subTitle(this.context.getString(C5698R.string.comma_separated, new Object[]{listing.getCity(), listing.getState()})).imageUrl(listing.getThumbnailUrl()).clickListener(SelectInvitationListingPickerController$$Lambda$1.lambdaFactory$(this, application)).addTo(this);
        }
    }

    private String getTitleTextRes(boolean closeToPassSelect) {
        if (closeToPassSelect) {
            return this.context.getString(C5698R.string.select_almost_ready_to_join_title);
        }
        return this.context.getString(C5698R.string.collections_listing_select_invitation_title, new Object[]{this.firstName});
    }

    private String getCaptionTextRes(boolean closeToPassSelect) {
        if (closeToPassSelect) {
            return this.context.getString(C5698R.string.select_almost_ready_to_join_caption);
        }
        return this.context.getString(C5698R.string.collections_listing_select_invitation_description);
    }
}
