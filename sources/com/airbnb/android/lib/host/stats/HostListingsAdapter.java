package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.listing.utils.CollectionsTextUtils;
import com.airbnb.android.utils.ListingRatingUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.components.ListingInfoRow;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import java.util.ArrayList;
import java.util.List;

public class HostListingsAdapter extends AirEpoxyAdapter {
    private final ListingInfoRowModel_ allListingsModel;
    private final Callback callback;
    private final Context context;
    private final SectionHeaderEpoxyModel_ listedSectionHeader = new SectionHeaderEpoxyModel_().titleRes(C0880R.string.host_stats_listing_selector_listed_section_header).showDivider(false);
    private final List<Listing> listings = new ArrayList();
    private final LoadingRowEpoxyModel loaderEpoxyModel = new LoadingRowEpoxyModel_();
    private final boolean showAllListingsCallout;
    private final SectionHeaderEpoxyModel_ unlistedSectionHeader = new SectionHeaderEpoxyModel_().titleRes(C0880R.string.host_stats_listing_selector_unlisted_section_header).showDivider(false);

    public interface Callback {
        void loadMore();

        void onAllListingsClicked();

        void onListingClicked(Listing listing);
    }

    public HostListingsAdapter(Callback callback2, Bundle savedInstanceState, boolean showAllListingsCallout2, Context context2, int totalListingViews, double averageOverallRating) {
        this.callback = callback2;
        this.context = context2;
        this.showAllListingsCallout = showAllListingsCallout2;
        this.allListingsModel = new ListingInfoRowModel_().title(context2.getString(C0880R.string.all_listings)).subtitleText(ListingRatingUtils.getRatingText(context2, averageOverallRating, totalListingViews)).onClickListener(HostListingsAdapter$$Lambda$1.lambdaFactory$(callback2));
        onRestoreInstanceState(savedInstanceState);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.choose_listing), this.allListingsModel, this.listedSectionHeader, this.loaderEpoxyModel});
    }

    /* access modifiers changed from: 0000 */
    public void addListings(List<Listing> newListings, boolean moreToLoad) {
        for (Listing listing : newListings) {
            if (!this.listings.contains(listing)) {
                boolean withDivider = !this.listings.isEmpty();
                this.listings.add(listing);
                if (!listing.isListed() && !this.models.contains(this.unlistedSectionHeader)) {
                    insertModelBefore(this.unlistedSectionHeader, this.loaderEpoxyModel);
                    withDivider = false;
                }
                insertModelBefore(buildModel(listing, withDivider), this.loaderEpoxyModel);
            }
        }
        if (this.listings.size() < 2 || !this.showAllListingsCallout) {
            this.allListingsModel.hide();
        }
        this.loaderEpoxyModel.show(moreToLoad);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position) {
        super.onModelBound(holder, model, position);
        if (model == this.loaderEpoxyModel) {
            this.callback.loadMore();
        }
    }

    public ArrayList<Listing> getListings() {
        return new ArrayList<>(this.listings);
    }

    private EpoxyModel<ListingInfoRow> buildModel(Listing listing, boolean showDivider) {
        return new ListingInfoRowModel_().title(listing.getName()).subtitleText(ListingRatingUtils.getRatingText(this.context, (double) listing.getReviewRatingOverall(), listing.getPageViews())).label(CollectionsTextUtils.getCollectionsStatusLabel(listing.getCollectionsApplication())).image(listing.getThumbnailUrl()).showDivider(showDivider).onClickListener(HostListingsAdapter$$Lambda$2.lambdaFactory$(this, listing));
    }
}
