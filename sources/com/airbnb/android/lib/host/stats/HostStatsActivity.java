package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class HostStatsActivity extends AutoFragmentActivity implements HostListingSelectorCallback, HostStatsInterface {
    static final String ARG_LISTINGS = "listings";
    private static final String ARG_LISTING_SELECTOR_BUNDLE = "listing_selector";
    @State
    Bundle bundleForListingSelector;
    private HostListingSelectorCallback callback;
    @State
    ArrayList<Listing> listings;

    public static Intent getIntentForFragment(Context context, Class<? extends Fragment> fragmentClass, ArrayList<Listing> listings2, Bundle bundleForFragment, Bundle bundleForListingSelector2) {
        if (bundleForFragment == null) {
            bundleForFragment = new Bundle();
        }
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, fragmentClass).putParcelableArrayList("listings", listings2)).putAll(bundleForFragment)).build().putExtra("listings", listings2).putExtra(ARG_LISTING_SELECTOR_BUNDLE, bundleForListingSelector2).setClass(context, HostStatsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.listings = getIntent().getParcelableArrayListExtra("listings");
            this.bundleForListingSelector = getIntent().getBundleExtra(ARG_LISTING_SELECTOR_BUNDLE);
        }
    }

    public void onListingSelected(Listing listing) {
        Check.notNull(this.callback, "callback must not be null");
        if (this.callback.isListingSelectable(listing)) {
            onBackPressed();
            this.callback.onListingSelected(listing);
        }
    }

    public void onAllListingsSelected() {
        Check.notNull(this.callback, "callback must not be null");
        onBackPressed();
        this.callback.onAllListingsSelected();
    }

    public boolean isListingSelectable(Listing listing) {
        return this.callback.isListingSelectable(listing);
    }

    public void addListingsToCache(List<Listing> listings2) {
        this.listings.removeAll(listings2);
        this.listings.addAll(listings2);
    }

    public void showListingSelector(HostListingSelectorCallback callback2, boolean showAllListings) {
        this.callback = callback2;
        showModal(HostListingSelectorFragment.newInstance(showAllListings, this.bundleForListingSelector), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    static /* synthetic */ boolean lambda$userHasOnlyOneListedListing$0(Listing listing) {
        return listing != null && listing.isListed();
    }

    public boolean userHasOnlyOneListedListing() {
        return FluentIterable.from((Iterable<E>) this.listings).filter(HostStatsActivity$$Lambda$1.lambdaFactory$()).size() == 1;
    }

    static /* synthetic */ boolean lambda$getFirstListedListing$1(Listing listing) {
        return listing != null && listing.isListed();
    }

    public Listing getFirstListedListing() {
        Optional<Listing> listingOptional = FluentIterable.from((Iterable<E>) this.listings).filter(HostStatsActivity$$Lambda$2.lambdaFactory$()).first();
        if (listingOptional.isPresent()) {
            return (Listing) listingOptional.get();
        }
        throw new IllegalStateException("no listed listings");
    }

    public boolean shouldEnableListingSelector() {
        return this.listings.size() > 1;
    }

    public Bundle getListingSelectorBundle() {
        return this.bundleForListingSelector;
    }
}
