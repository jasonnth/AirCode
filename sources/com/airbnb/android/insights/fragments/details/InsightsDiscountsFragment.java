package com.airbnb.android.insights.fragments.details;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter.Listener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class InsightsDiscountsFragment extends AirFragment {
    public static final String ARG_AVERAGE_PRICES = "average_prices";
    public static final String ARG_LISTING = "listing";
    public static final String ARG_PRICING_CONTROLS = "pricing_control";
    private LongTermDiscountsAdapter adapter;
    private final Listener listener = new Listener() {
        public void validStateUpdated(boolean valid) {
            InsightsDiscountsFragment.this.saveButton.setEnabled(valid);
        }

        public void showLengthOfStayDiscountLearnMore() {
            if (InsightsDiscountsFragment.this.losListener != null) {
                InsightsDiscountsFragment.this.losListener.showLengthOfStayDiscountLearnMore();
            }
        }
    };
    /* access modifiers changed from: private */
    public LengthOfStayListener losListener;
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;

    public interface LengthOfStayListener {
        void showLengthOfStayDiscountLearnMore();
    }

    public static InsightsDiscountsFragment newInstance(Listing listing, DynamicPricingControl pricingControl, ListingLongTermDiscountValues averagePrices) {
        return (InsightsDiscountsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsDiscountsFragment()).putParcelable("listing", listing)).putParcelable(ARG_PRICING_CONTROLS, pricingControl)).putParcelable(ARG_AVERAGE_PRICES, averagePrices)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6552R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof LengthOfStayListener) {
            this.losListener = (LengthOfStayListener) parentFragment;
        }
        ViewLibUtils.setVisibleIf(this.saveButton, false);
        DynamicPricingControl dynamicPricingControl = (DynamicPricingControl) getArguments().getParcelable(ARG_PRICING_CONTROLS);
        Listing listing = (Listing) getArguments().getParcelable("listing");
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, listing.getId());
        this.adapter = new LongTermDiscountsAdapter(getContext(), ListingDisplayMode.Insights, listing, (ListingLongTermDiscountValues) getArguments().getParcelable(ARG_AVERAGE_PRICES), this.listener, this.pricingJitneyLogger, savedInstanceState);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.losListener = null;
    }
}
