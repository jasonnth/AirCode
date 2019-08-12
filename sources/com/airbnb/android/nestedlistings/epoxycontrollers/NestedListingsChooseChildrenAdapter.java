package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ArticleDocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NestedListingToggleRowEpoxyModel_;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.NestedListingToggleRow;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import icepick.State;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NestedListingsChooseChildrenAdapter extends AirEpoxyAdapter {
    private Context context;
    private boolean fromOverview;
    @State
    HashSet<Long> initialSelectedListingIds;
    private NestedListingsChooseChildrenListener listener;
    @State
    HashSet<Long> selectedListingIds;

    public interface NestedListingsChooseChildrenListener {
        void onSelectAnyListing();

        void onSelectEntireHome();

        void onUnselectEntireHome();
    }

    public NestedListingsChooseChildrenAdapter(Context context2, NestedListing parentListing, List<NestedListing> candidates, NestedListingsChooseChildrenListener listener2, boolean fromOverview2, Bundle savedInstanceState) {
        this.context = context2;
        this.listener = listener2;
        this.fromOverview = fromOverview2;
        if (savedInstanceState == null) {
            ImmutableSet<Long> iset = FluentIterable.from((Iterable<E>) candidates).filter(NestedListingsChooseChildrenAdapter$$Lambda$1.lambdaFactory$(parentListing)).transform(NestedListingsChooseChildrenAdapter$$Lambda$4.lambdaFactory$()).toSet();
            this.selectedListingIds = new HashSet<>(iset);
            this.initialSelectedListingIds = new HashSet<>(iset);
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        addHeader(SpannableUtils.makeBoldedSubString(context2.getString(C7496R.string.nested_listings_choose_children_subtitle, new Object[]{parentListing.getName()}), context2, parentListing.getName()));
        for (NestedListing listing : NestedListing.sortByStatusTypeAndZip(candidates, parentListing.getZipCode(), false)) {
            addRow(listing);
        }
    }

    public Set<Long> getSelectedListingIds() {
        return this.selectedListingIds;
    }

    public boolean selectionChanged() {
        return !this.initialSelectedListingIds.equals(this.selectedListingIds);
    }

    public void setRowsEnabled(boolean enabled) {
        for (EpoxyModel<?> model : this.models) {
            if (model instanceof NestedListingToggleRowEpoxyModel_) {
                ((NestedListingToggleRowEpoxyModel_) model).enabled(enabled);
                notifyModelChanged(model);
            }
        }
    }

    private void addHeader(CharSequence subtitle) {
        addModel(new ArticleDocumentMarqueeEpoxyModel_().kickerRes(this.fromOverview ? 0 : C7496R.string.nested_listings_kicker_2).titleRes(C7496R.string.nested_listings_title).captionText(subtitle).withTopPadding(false));
    }

    private void addRow(NestedListing listing) {
        NestedListingToggleRowEpoxyModel_ model = new NestedListingToggleRowEpoxyModel_().m5230id(listing.getId()).title(listing.getName()).subtitle(listing.getRoomTypeForSubtitle(this.context)).checked(this.selectedListingIds.contains(Long.valueOf(listing.getId()))).isActiveListing(listing.isActive()).showDivider(true);
        String imageURL = listing.getThumbnailUrl();
        if (!TextUtils.isEmpty(imageURL)) {
            model.imageUrl(imageURL);
        } else {
            model.imageDrawableRes(C7496R.C7497drawable.camera_icon_bg);
        }
        model.listener(NestedListingsChooseChildrenAdapter$$Lambda$5.lambdaFactory$(this, model, listing));
        addModel(model);
    }

    static /* synthetic */ void lambda$addRow$2(NestedListingsChooseChildrenAdapter nestedListingsChooseChildrenAdapter, NestedListingToggleRowEpoxyModel_ model, NestedListing listing, NestedListingToggleRow row) {
        model.checked(!model.checked());
        nestedListingsChooseChildrenAdapter.notifyModelChanged(model);
        if (model.checked()) {
            nestedListingsChooseChildrenAdapter.selectedListingIds.add(Long.valueOf(listing.getId()));
            nestedListingsChooseChildrenAdapter.listener.onSelectAnyListing();
            if (listing.getSpaceType() == SpaceType.EntireHome) {
                nestedListingsChooseChildrenAdapter.listener.onSelectEntireHome();
                return;
            }
            return;
        }
        nestedListingsChooseChildrenAdapter.selectedListingIds.remove(Long.valueOf(listing.getId()));
        if (listing.getSpaceType() == SpaceType.EntireHome) {
            nestedListingsChooseChildrenAdapter.listener.onUnselectEntireHome();
        }
    }
}
