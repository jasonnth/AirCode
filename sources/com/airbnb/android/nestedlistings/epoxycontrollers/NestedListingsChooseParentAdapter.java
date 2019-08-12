package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ArticleDocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NestedListingRowEpoxyModel_;
import com.airbnb.android.nestedlistings.C7496R;
import java.util.List;

public class NestedListingsChooseParentAdapter extends AirEpoxyAdapter {
    private Context context;
    private NestedListingsChooseParentListener listener;

    public interface NestedListingsChooseParentListener {
        void onChooseParent(NestedListing nestedListing);
    }

    public NestedListingsChooseParentAdapter(Context context2, List<NestedListing> candidates, NestedListingsChooseParentListener listener2) {
        super(true);
        this.context = context2;
        this.listener = listener2;
        addHeader();
        for (NestedListing listing : NestedListing.sortByStatusAndType(candidates, true)) {
            addRow(listing);
        }
    }

    private void addHeader() {
        addModel(new ArticleDocumentMarqueeEpoxyModel_().kickerRes(C7496R.string.nested_listings_kicker_1).titleRes(C7496R.string.nested_listings_title).captionRes(C7496R.string.nested_listings_choose_parent_subtitle).withTopPadding(false));
    }

    private void addRow(NestedListing listing) {
        NestedListingRowEpoxyModel_ model = new NestedListingRowEpoxyModel_().title(listing.getName()).subtitle(listing.getRoomTypeForSubtitle(this.context)).isActiveListing(listing.isActive()).clickListener(NestedListingsChooseParentAdapter$$Lambda$1.lambdaFactory$(this, listing)).showDivider(true);
        String imageURL = listing.getThumbnailUrl();
        if (!TextUtils.isEmpty(imageURL)) {
            model.imageUrl(imageURL);
        } else {
            model.imageDrawableRes(C7496R.C7497drawable.camera_icon_bg);
        }
        addModel(model);
    }
}
