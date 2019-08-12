package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.adapters.CarouselAdapter;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingDemandDetails;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.HeaderCarouselContainer;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.LargeTitleRowEpoxyModel;
import com.airbnb.android.lib.viewcomponents.viewmodels.LargeTitleRowEpoxyModel.TitleFormatter;
import com.airbnb.android.lib.viewcomponents.viewmodels.LargeTitleRowEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class HostDemandDetailsAdapter extends AirEpoxyAdapter {
    private static final int DAYS_TO_FETCH = 30;
    private final LargeTitleRowEpoxyModel bookingRateModel = createRowModel(C0880R.string.host_stats_booking_rate_cell_subtitle, C0880R.string.host_stats_booking_rate_cell_description, false);
    /* access modifiers changed from: private */
    public final Context context;
    private ListingDemandDetails demandDetails;
    /* access modifiers changed from: private */
    public final DecimalFormat listingViewsAndBookingsFormatter = new DecimalFormat("###,###,###");
    private final LargeTitleRowEpoxyModel listingViewsModel = createRowModel(C0880R.string.host_stats_listing_views_cell_subtitle, 0, true);
    private final LoadingRowEpoxyModel loaderModel = new LoadingRowEpoxyModel_();
    private final LargeTitleRowEpoxyModel newReservationsModel = createRowModel(C0880R.string.host_stats_new_reservations_cell_subtitle, 0, true);
    private final RecycledViewPool recycledViewPool = new RecycledViewPool();
    private final SectionHeaderEpoxyModel_ similarListingSectionHeaderEpoxyModel = new SectionHeaderEpoxyModel_().titleRes(C0880R.string.similar_listings).hide();

    public HostDemandDetailsAdapter(Context context2) {
        super(false);
        this.context = context2;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new SectionHeaderEpoxyModel_().titleRes(C0880R.string.host_stats_listing_views_page_title).description(context2.getString(C0880R.string.host_stats_listing_views_past_x_days, new Object[]{Integer.valueOf(30)})).showDivider(false), this.listingViewsModel, this.newReservationsModel, this.bookingRateModel, this.similarListingSectionHeaderEpoxyModel, this.loaderModel});
    }

    public void setSimilarListings(HashMap<Listing, List<SimilarListing>> similarListingsMap) {
        boolean z;
        removeAllAfterModel(this.similarListingSectionHeaderEpoxyModel);
        Iterator<Entry<Listing, List<SimilarListing>>> iterator = similarListingsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (ListUtils.isEmpty((Collection<?>) (List) ((Entry) iterator.next()).getValue())) {
                iterator.remove();
            }
        }
        if (similarListingsMap.isEmpty()) {
            this.similarListingSectionHeaderEpoxyModel.hide();
        } else if (similarListingsMap.size() == 1) {
            this.similarListingSectionHeaderEpoxyModel.hide();
            Entry<Listing, List<SimilarListing>> entry = (Entry) similarListingsMap.entrySet().iterator().next();
            insertSimilarListingsRow((Listing) entry.getKey(), (List) entry.getValue(), true, this.context.getString(C0880R.string.similar_listings));
        } else {
            this.similarListingSectionHeaderEpoxyModel.show();
            boolean hideBorder = true;
            for (Entry<Listing, List<SimilarListing>> entry2 : similarListingsMap.entrySet()) {
                Listing listing = (Listing) entry2.getKey();
                List list = (List) entry2.getValue();
                if (!hideBorder) {
                    z = true;
                } else {
                    z = false;
                }
                insertSimilarListingsRow(listing, list, z, this.context.getString(C0880R.string.host_stats_similar_listing_cell_title, new Object[]{listing.getName()}));
                hideBorder = false;
            }
        }
    }

    private void insertSimilarListingsRow(Listing listing, List<SimilarListing> similarListings, boolean withDivider, String cellSectionHeaderTitle) {
        CarouselAdapter adapter = new CarouselAdapter((EpoxyModel<?>) new HomeCardEpoxyModel_().displayOptions(DisplayOptions.forHomeCard(this.context, DisplayType.Horizontal)));
        adapter.setItems(FluentIterable.from((Iterable<E>) similarListings).transform(HostDemandDetailsAdapter$$Lambda$1.lambdaFactory$(this)).toList());
        HeaderCarouselContainer<CarouselAdapter> similarListingsContainer = new HeaderCarouselContainer().withDivider(withDivider);
        similarListingsContainer.viewPool(this.recycledViewPool).adapter(adapter).carouselId(listing.getId());
        similarListingsContainer.title((CharSequence) cellSectionHeaderTitle);
        this.models.addAll(similarListingsContainer.getEpoxyModels());
    }

    public void setDemandDetails(ListingDemandDetails newDemandDetails) {
        this.listingViewsModel.show();
        this.newReservationsModel.show();
        this.bookingRateModel.show();
        this.loaderModel.hide();
        int oldPageViews = this.demandDetails == null ? 0 : newDemandDetails.getPageViews();
        int oldBookings = this.demandDetails == null ? 0 : newDemandDetails.getBookings();
        float oldBookingRate = this.demandDetails == null ? 0.0f : newDemandDetails.getBookingRate();
        int newPageViews = newDemandDetails.getPageViews();
        int newBookings = newDemandDetails.getBookings();
        float newBookingRate = newDemandDetails.getBookingRate();
        this.demandDetails = newDemandDetails;
        notifyModelChanged(this.listingViewsModel);
        notifyModelChanged(this.newReservationsModel);
        notifyModelChanged(this.bookingRateModel);
        notifyModelChanged(this.loaderModel);
        this.listingViewsModel.animateTitleFrom(oldPageViews, newPageViews, (TitleFormatter<Integer>) new TitleFormatter<Integer>() {
            public CharSequence format(Integer value) {
                return HostDemandDetailsAdapter.this.listingViewsAndBookingsFormatter.format(value);
            }
        });
        this.newReservationsModel.animateTitleFrom(oldBookings, newBookings, (TitleFormatter<Integer>) new TitleFormatter<Integer>() {
            public CharSequence format(Integer value) {
                return HostDemandDetailsAdapter.this.listingViewsAndBookingsFormatter.format(value);
            }
        });
        this.bookingRateModel.animateTitleFrom(oldBookingRate, newBookingRate, (TitleFormatter<Float>) new TitleFormatter<Float>() {
            public String format(Float value) {
                return HostDemandDetailsAdapter.this.context.getString(C0880R.string.n2_percentage, new Object[]{String.valueOf(value)});
            }
        });
    }

    /* access modifiers changed from: private */
    public EpoxyModel<?> createModel(SimilarListing similarListing) {
        if (similarListing == null) {
            return null;
        }
        Listing listing = similarListing.getListing();
        return new HomeCardEpoxyModel_().listing(listing).pricingQuote(similarListing.getPricingQuote()).displayOptions(DisplayOptions.forHomeCard(this.context, DisplayType.Horizontal)).clickListener(HostDemandDetailsAdapter$$Lambda$2.lambdaFactory$(listing));
    }

    private static LargeTitleRowEpoxyModel createRowModel(int subtitleResId, int secondarySubtitleResId, boolean withDivider) {
        return new LargeTitleRowEpoxyModel_().primarySubtitleRes(subtitleResId).secondarySubtitleRes(secondarySubtitleResId).showDivider(withDivider).show(false);
    }

    public void setSimilarListingsLoading() {
        if (getModelPosition(this.loaderModel) == -1) {
            addModel(this.loaderModel);
        }
        showModel(this.loaderModel);
    }
}
