package com.airbnb.android.hostcalendar.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.interfaces.Scrollable;

public class AboutSmartPricingFragment extends AirFragment {
    private static final String ARG_FROM_INTENT = "from_intent";
    private static final String ARG_LISTING_ID = "listing_id";
    private boolean fromIntent;
    private long listingId;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;

    public static AboutSmartPricingFragment newInstance(long listingId2) {
        return (AboutSmartPricingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AboutSmartPricingFragment()).putLong("listing_id", listingId2)).putBoolean(ARG_FROM_INTENT, false)).build();
    }

    public static Intent newIntent(Activity activity, long listingId2) {
        return TransparentActionBarActivity.intentForFragment(activity, (AboutSmartPricingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AboutSmartPricingFragment()).putLong("listing_id", listingId2)).putBoolean(ARG_FROM_INTENT, true)).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_about_smart_pricing, container, false);
        bindViews(view);
        this.fromIntent = getArguments().getBoolean(ARG_FROM_INTENT);
        if (this.fromIntent) {
            this.toolbar.setVisibility(8);
            this.toolbar = ((TransparentActionBarActivity) getActivity()).getAirToolbar();
        } else {
            setToolbar(this.toolbar);
        }
        this.toolbar.scrollWith((Scrollable<?>) this.scrollView);
        this.toolbar.setTheme(3);
        this.listingId = getArguments().getLong("listing_id");
        return view;
    }

    @OnClick
    public void onClickPriceSettingsButton() {
        startActivity(ManageListingIntents.intentForExistingListingSetting(getContext(), this.listingId, SettingDeepLink.Price));
    }
}
