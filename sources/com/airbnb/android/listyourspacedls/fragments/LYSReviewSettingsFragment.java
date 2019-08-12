package com.airbnb.android.listyourspacedls.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

public class LYSReviewSettingsFragment extends LYSBaseFragment {
    @BindView
    StandardRow additionalPricingRow;
    @BindView
    StandardRow availabilityRow;
    @BindView
    StandardRow guestRequirementsRow;
    @BindView
    StandardRow houseRulesRow;
    @BindView
    StandardRow pricingRow;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new LYSReviewSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(C7251R.layout.lys_dls_review_settings, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        Context context = getContext();
        Listing listing = this.controller.getListing();
        if (!this.controller.getDynamicPricingControls().isEnabled()) {
            this.pricingRow.setTitle(C7251R.string.ml_base_price);
        }
        String houseRules = ListingTextUtils.getHouseRulesReviewText(context, listing, this.controller.getGuestControls());
        this.guestRequirementsRow.setSubtitleText((CharSequence) ListingTextUtils.getGuestRequirementsReviewText(context, listing));
        this.houseRulesRow.setSubtitleText((CharSequence) houseRules);
        this.houseRulesRow.setSubtitleMaxLine(houseRules.split("\n").length + 1);
        this.availabilityRow.setSubtitleText((CharSequence) ListingTextUtils.getAvailabilityReviewText(context, listing, this.controller.getCalendarRule()));
        this.pricingRow.setSubtitleText((CharSequence) ListingTextUtils.getSmartPricingReviewText(context, listing));
        this.additionalPricingRow.setSubtitleText((CharSequence) ListingTextUtils.getAdditionalPricingReviewText(context, listing));
        this.guestRequirementsRow.setOnClickListener(LYSReviewSettingsFragment$$Lambda$1.lambdaFactory$(this));
        this.houseRulesRow.setOnClickListener(LYSReviewSettingsFragment$$Lambda$2.lambdaFactory$(this));
        this.availabilityRow.setOnClickListener(LYSReviewSettingsFragment$$Lambda$3.lambdaFactory$(this));
        this.pricingRow.setOnClickListener(LYSReviewSettingsFragment$$Lambda$4.lambdaFactory$(this));
        this.additionalPricingRow.setOnClickListener(LYSReviewSettingsFragment$$Lambda$5.lambdaFactory$(this));
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.ReviewSettings);
    }

    @OnClick
    public void onClickNext() {
        this.controller.nextStep(LYSStep.ReviewSettings);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSReviewGuestRequirements;
    }
}
