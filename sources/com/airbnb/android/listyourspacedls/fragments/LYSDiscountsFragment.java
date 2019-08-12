package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter.Listener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.listing.utils.PricingJitneyHelper;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSDiscountsFragment extends LYSBaseFragment {
    private LongTermDiscountsAdapter adapter;
    private final Listener listener = new Listener() {
        public void validStateUpdated(boolean valid) {
            if (LYSDiscountsFragment.this.nextButton != null) {
                LYSDiscountsFragment.this.nextButton.setEnabled(valid);
            }
        }

        public void showLengthOfStayDiscountLearnMore() {
            LYSDiscountsFragment.this.controller.showTipModal(C7251R.string.manage_listing_about_length_of_stay_discount_title, C7251R.string.manage_listing_about_length_of_stay_discount_info, NavigationTag.ManageListingAboutLengthOfStayDiscounts);
        }
    };
    LoggingContextFactory loggingContextFactory;
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSDiscountsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSDiscountsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSDiscountsFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static LYSDiscountsFragment newInstance(boolean isStandalone) {
        return (LYSDiscountsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSDiscountsFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSDiscountsFragment lYSDiscountsFragment, SimpleListingResponse response) {
        PricingJitneyHelper.logDiscountsChange(lYSDiscountsFragment.pricingJitneyLogger, lYSDiscountsFragment.controller.getListing(), response.listing);
        lYSDiscountsFragment.controller.setListing(response.listing);
        lYSDiscountsFragment.navigateInFlow(LYSStep.Discounts);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        Listing listing = this.controller.getListing();
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ListYourSpace, C2586PricingSettingsSectionType.PricingSettings, listing.getId());
        this.adapter = new LongTermDiscountsAdapter(getContext(), ListingDisplayMode.LYS, listing, this.controller.getAveragePrices(), this.listener, this.pricingJitneyLogger, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        showTip(C7251R.string.manage_listing_pricing_disclaimer_call_to_action, LYSDiscountsFragment$$Lambda$4.lambdaFactory$(this));
        if (this.comingFromBackstack) {
            this.adapter = new LongTermDiscountsAdapter(getContext(), ListingDisplayMode.LYS, this.controller.getListing(), this.controller.getAveragePrices(), this.listener, this.pricingJitneyLogger, null);
        }
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateDiscounts();
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        updateDiscounts();
    }

    private void updateDiscounts() {
        if (!canSaveChanges()) {
            this.adapter.markErrors(false);
            navigateInFlow(LYSStep.Discounts);
        } else if (this.adapter.showWeeklyPriceHigherError()) {
            this.adapter.markErrors(true);
            ErrorUtils.showErrorUsingSnackbar(getView(), C7251R.string.ml_discounts_change_discounts, C7251R.string.ml_discounts_change_discounts_explanation, 0);
        } else {
            this.adapter.markErrors(false);
            setLoading(this.adapter);
            UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), this.adapter.getDiscountsSettings()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing()) && this.nextButton != null && this.nextButton.isEnabled();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSDiscounts;
    }

    /* access modifiers changed from: private */
    public void showDisclaimer() {
        this.controller.showTipModal(C7251R.string.manage_listing_pricing_disclaimer_title, ListingTextUtils.createPricingDisclaimer(getContext(), true), NavigationTag.LYSDiscountsTip);
    }
}
