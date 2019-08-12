package com.airbnb.android.listing.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class ListingSmartPricingTipFragment extends AirFragment {
    private static final String PARAM_FROM_INSIGHTS = "from_insights";
    private static final String PARAM_FROM_SMART_PRICING = "from_smart_pricing";
    @BindView
    AirButton button;
    @BindView
    DocumentMarquee marquee;
    @BindView
    AirToolbar toolbar;

    public interface ListingSmartPriceTipListener {
        void showNightlyPrice();
    }

    public static ListingSmartPricingTipFragment create(boolean fromSmartPricing, boolean fromInsights) {
        return (ListingSmartPricingTipFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ListingSmartPricingTipFragment()).putBoolean(PARAM_FROM_SMART_PRICING, fromSmartPricing)).putBoolean("from_insights", fromInsights)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_listing_smart_pricing_tip, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (getArguments().getBoolean("from_insights")) {
            this.toolbar.setNavigationOnClickListener(ListingSmartPricingTipFragment$$Lambda$1.lambdaFactory$(this));
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onTryClicked() {
        getFragmentManager().popBackStack();
        if (!getArguments().getBoolean(PARAM_FROM_SMART_PRICING, false)) {
            ((ListingSmartPriceTipListener) getActivity()).showNightlyPrice();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingSmartPricingTip;
    }
}
