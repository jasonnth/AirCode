package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.p011p3.P3Component.Builder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

/* renamed from: com.airbnb.android.p3.P3AdditionalPriceFragment */
public class P3AdditionalPriceFragment extends P3BaseFragment {
    CurrencyFormatter currencyFormatter;
    private PriceBreakdownEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    /* renamed from: com.airbnb.android.p3.P3AdditionalPriceFragment$PriceBreakdownEpoxyController */
    private class PriceBreakdownEpoxyController extends AirEpoxyController {
        private PriceBreakdownEpoxyController() {
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            new EntryMarqueeEpoxyModel_().m4572id((CharSequence) "marquee").title(C7532R.string.additional_prices).addTo(this);
            Listing listing = P3AdditionalPriceFragment.this.controller.getState().listing();
            if (listing == null) {
                new EpoxyControllerLoadingModel_().m4584id((CharSequence) "loader").addTo(this);
                return;
            }
            addRow(C7532R.string.p3_additional_prices_extra_people, P3AdditionalPriceFragment.this.getExtraGuestFeeText(listing, P3AdditionalPriceFragment.this.currencyFormatter));
            if (listing.getWeeklyPriceFactor().hasDiscount()) {
                addRow(C7532R.string.p3_additional_prices_weekly_discount, P3AdditionalPriceFragment.this.getDiscountText(listing.getWeeklyPriceFactor()));
            }
            if (listing.getMonthlyPriceFactor().hasDiscount()) {
                addRow(C7532R.string.p3_additional_prices_monthly_discount, P3AdditionalPriceFragment.this.getDiscountText(listing.getMonthlyPriceFactor()));
            }
            if (listing.getCleaningFee() > 0) {
                addRow(C7532R.string.p3_additional_prices_cleaning_fee, P3AdditionalPriceFragment.this.currencyFormatter.formatNativeCurrency((double) listing.getCleaningFee(), true));
            }
            if (listing.getSecurityDeposit() > 0) {
                addRow(C7532R.string.p3_additional_prices_security_deposit, P3AdditionalPriceFragment.this.currencyFormatter.formatNativeCurrency((double) listing.getSecurityDeposit(), true));
            }
        }

        private void addRow(int titleRes, String value) {
            new StandardRowEpoxyModel_().title(titleRes).subtitle((CharSequence) value).m5602id((long) titleRes).addTo(this);
        }
    }

    public static P3AdditionalPriceFragment newInstance() {
        return new P3AdditionalPriceFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.p3_generic_recyclerview_with_toolbar, container, false);
        bindViews(view);
        ((Builder) ((P3Bindings) CoreApplication.instance(getActivity()).componentProvider()).p3ComponentProvider().get()).build().inject(this);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.epoxyController = new PriceBreakdownEpoxyController();
        this.epoxyController.requestModelBuild();
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    /* access modifiers changed from: private */
    public String getExtraGuestFeeText(Listing listing, CurrencyFormatter currencyFormatter2) {
        if (!listing.hasExtraGuestFee()) {
            return getString(C7532R.string.p3_additional_prices_extra_people_no_charge);
        }
        String extraGuestPrice = currencyFormatter2.formatNativeCurrency((double) listing.getExtraGuestPrice(), true);
        int guestsIncluded = listing.getGuestsIncluded();
        return getResources().getQuantityString(C7532R.plurals.price_per_night_after_x_guests, guestsIncluded, new Object[]{extraGuestPrice, Integer.valueOf(guestsIncluded)});
    }

    /* access modifiers changed from: private */
    public String getDiscountText(PriceFactor priceFactor) {
        return PercentageUtils.formatDiscountPercentage(priceFactor.getDiscountPercentage());
    }

    public void onListingLoaded() {
        this.epoxyController.requestModelBuild();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingAdditionalPrices;
    }
}
