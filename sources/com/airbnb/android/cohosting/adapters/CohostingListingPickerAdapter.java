package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View.OnClickListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.CohostingDeepLink;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.ListingRatingUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class CohostingListingPickerAdapter extends AirEpoxyAdapter {
    private final Context context;
    DebugSettings debugSettings;
    private final long otherUserId;

    public CohostingListingPickerAdapter(Context context2, String firstName, List<Listing> listings, long otherUserId2) {
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.otherUserId = otherUserId2;
        addHeader(firstName);
        addAllListings(listings);
    }

    private void addHeader(String firstName) {
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C5658R.string.cohosting_pick_listing_title).captionText((CharSequence) this.context.getString(C5658R.string.cohosting_pick_listing_description, new Object[]{firstName})));
    }

    private void addAllListings(List<Listing> listings) {
        addListingsSection(listings, ListingStatus.InProgress, C5658R.string.manage_listings_in_progress_title);
        addListingsSection(listings, ListingStatus.Listed, C5658R.string.manage_listings_listed_title);
        addListingsSection(listings, ListingStatus.Unlisted, C5658R.string.manage_listings_unlisted_title);
    }

    private void addListingsSection(List<Listing> allListings, ListingStatus status, int titleString) {
        addListings(titleString, FluentIterable.from((Iterable<E>) allListings).filter(CohostingListingPickerAdapter$$Lambda$1.lambdaFactory$(status)).transform(CohostingListingPickerAdapter$$Lambda$2.lambdaFactory$(this)).toList());
    }

    static /* synthetic */ boolean lambda$addListingsSection$0(ListingStatus status, Listing listing) {
        return listing.getStatus() == status;
    }

    private void addListings(int title, List<ListingInfoRowModel_> listingModels) {
        if (!listingModels.isEmpty()) {
            addModel(new SectionHeaderEpoxyModel_().titleRes(title));
            ((ListingInfoRowModel_) listingModels.get(0)).showDivider(false);
            addModels((Collection<? extends EpoxyModel<?>>) listingModels);
        }
    }

    /* access modifiers changed from: private */
    public ListingInfoRowModel_ createListingModel(Listing listing) {
        OnClickListener listener = CohostingListingPickerAdapter$$Lambda$3.lambdaFactory$(this, listing);
        String price = null;
        String currency = listing.getListingCurrency();
        if (!Strings.isNullOrEmpty(currency)) {
            price = CurrencyUtils.formatCurrencyAmount((double) listing.getListingPrice(), currency);
        }
        return new ListingInfoRowModel_().title(listing.getName()).subtitleText(ListingRatingUtils.getRatingText(this.context, (double) listing.getReviewRatingOverall(), price)).image(listing.getThumbnailUrl()).onClickListener(listener).showDivider(true);
    }

    private Intent createIntent(long listingId) {
        return CohostingIntents.intentForListingManagerDeepLink(this.context, listingId, this.otherUserId, CohostingDeepLink.ListingManager);
    }
}
