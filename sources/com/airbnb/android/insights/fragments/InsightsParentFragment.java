package com.airbnb.android.insights.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.hostcalendar.fragments.PriceTipsDisclaimerFragment;
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.insights.InsightEpoxyModel_;
import com.airbnb.android.insights.InsightsActivity;
import com.airbnb.android.insights.InsightsAnalytics;
import com.airbnb.android.insights.InsightsDataController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class InsightsParentFragment extends AirFragment implements OnBackListener {
    private static final String ARG_STORY_ID = "story_id";
    public static final String TAG_DETAIL_CARD = "detail_card";
    private InsightsDataController dataController;
    private FragmentManager fragmentManager;
    private InsightsActivity insightsActivity;
    private InsightsAnalytics insightsAnalytics;

    public static InsightsParentFragment newInstance() {
        return (InsightsParentFragment) FragmentBundler.make(new InsightsParentFragment()).build();
    }

    public static InsightsParentFragment newInstance(String storyId) {
        return (InsightsParentFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsParentFragment()).putString(ARG_STORY_ID, storyId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C6552R.layout.fragment_insights_parent, container, false);
        bindViews(v);
        this.dataController = ((InsightsActivity) getActivity()).getDataController();
        this.insightsActivity = (InsightsActivity) getActivity();
        this.insightsActivity.setOnBackPressedListener(this);
        this.fragmentManager = getChildFragmentManager();
        this.insightsAnalytics = ((InsightsActivity) getActivity()).getInsightsAnalytics();
        if (savedInstanceState == null) {
            showInsightFragment();
        }
        return v;
    }

    private void showInsightFragment() {
        showFragment(InsightsFragment.newInstance((Listing) this.dataController.getAllListings().get(this.dataController.getFirstListingPosition()), getArguments().getString(ARG_STORY_ID)), C6552R.C6554id.container, FragmentTransitionType.None, true);
    }

    public void onDestroyView() {
        this.insightsActivity.setOnBackPressedListener(null);
        super.onDestroyView();
    }

    public InsightsDataController getDataController() {
        return this.dataController;
    }

    public void returnToCarousel() {
        this.insightsActivity.getToolbar().setVisibility(8);
        this.fragmentManager.popBackStack();
    }

    public void showDetailsFragment(InsightEpoxyModel_ insightModel) {
        this.insightsAnalytics.trackPreview(insightModel.insight());
        NavigationUtils.showModal(getChildFragmentManager(), getActivity(), InsightsDetailCardFragment.newInstance(insightModel.insight()), 0, C6552R.C6554id.details_container, true, TAG_DETAIL_CARD);
    }

    public Listing getListing() {
        return ((InsightsDetailCardFragment) getChildFragmentManager().findFragmentByTag(TAG_DETAIL_CARD)).getListing();
    }

    public Insight getInsight() {
        return ((InsightsDetailCardFragment) getChildFragmentManager().findFragmentByTag(TAG_DETAIL_CARD)).getInsight();
    }

    public void showPricingDisclaimer() {
        NavigationUtils.showModal(getChildFragmentManager(), getActivity(), PricingDisclaimerFragment.newInstance(), 0, C6552R.C6554id.details_container, true);
    }

    public void showPriceTipsDisclaimer() {
        PriceTipsDisclaimerFragment disclaimerFragment = new PriceTipsDisclaimerFragment();
        disclaimerFragment.setOnBackListener(this);
        showModal(disclaimerFragment);
    }

    public void showModal(Fragment fragment) {
        NavigationUtils.showModal(getChildFragmentManager(), getActivity(), fragment, 0, C6552R.C6554id.modal_container, true);
    }

    public void popBackStackOrFinish() {
        if (this.fragmentManager.getBackStackEntryCount() > 0) {
            this.insightsActivity.getToolbar().setVisibility(8);
            this.fragmentManager.popBackStack();
            return;
        }
        this.insightsActivity.finish();
    }

    public boolean onBackPressed() {
        popBackStackOrFinish();
        return true;
    }
}
