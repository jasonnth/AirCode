package com.airbnb.android.listyourspacedls.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSSmartPricingFragment extends LYSBaseFragment {
    private final NightlyPriceActionListener actionListener = new NightlyPriceActionListener() {
        public void smartPricingTip() {
        }

        public void hostingFrequencyInfo(DesiredHostingFrequencyVersion version) {
        }

        public void showCurrencyOptions(String currentCurrency) {
            KeyboardUtils.dismissSoftKeyboard((Activity) LYSSmartPricingFragment.this.getActivity());
            LYSSmartPricingFragment.this.controller.showCurrencyModal(currentCurrency);
        }

        public void showUpdateAppSnackbar() {
        }

        public void startEditingValues() {
        }
    };
    private NightlyPriceAdapter adapter;
    final RequestListener<SimpleListingResponse> basePriceListener = new C0699RL().onResponse(LYSSmartPricingFragment$$Lambda$10.lambdaFactory$(this)).onError(LYSSmartPricingFragment$$Lambda$11.lambdaFactory$(this)).build();
    private boolean fetchingPriceTips;
    LoggingContextFactory loggingContextFactory;
    final RequestListener<SimpleListingResponse> priceTipsListener = new C0699RL().onResponse(LYSSmartPricingFragment$$Lambda$7.lambdaFactory$(this)).onComplete(LYSSmartPricingFragment$$Lambda$8.lambdaFactory$(this)).onError(LYSSmartPricingFragment$$Lambda$9.lambdaFactory$(this)).build();
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<DemandBasedPricingResponse> smartPricingFetchListener = new C0699RL().onResponse(LYSSmartPricingFragment$$Lambda$4.lambdaFactory$(this)).onComplete(LYSSmartPricingFragment$$Lambda$5.lambdaFactory$(this)).onError(LYSSmartPricingFragment$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<DemandBasedPricingResponse> smartPricingUpdateListener = new C0699RL().onResponse(LYSSmartPricingFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSSmartPricingFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSSmartPricingFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public static LYSSmartPricingFragment newInstance(boolean isStandalone) {
        return (LYSSmartPricingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSSmartPricingFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.adapter = new NightlyPriceAdapter(getContext(), this.controller.getListing(), this.controller.getDynamicPricingControls(), true, this.actionListener, ListingDisplayMode.LYS, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ListYourSpace, C2586PricingSettingsSectionType.PricingSettings, this.controller.getListing().getId());
        if (this.comingFromBackstack) {
            this.adapter = new NightlyPriceAdapter(getContext(), this.controller.getListing(), this.controller.getDynamicPricingControls(), true, this.actionListener, ListingDisplayMode.LYS, null);
        }
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.controller.setCurrencyCode(this.controller.getListing().getListingCurrency());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing(), this.controller.getDynamicPricingControls());
    }

    public void dataUpdated() {
        super.dataUpdated();
        if (!this.fetchingPriceTips && !this.adapter.getCurrentCurrencyCode().equals(this.controller.getCurrencyCode())) {
            fetchNewPriceTips();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.SetPrice);
        } else if (this.adapter.getPrice() > 0) {
            updateSmartPricing();
        } else {
            ErrorUtils.showErrorUsingSnackbar(getView(), C7251R.string.lys_dls_base_price_0_error);
        }
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    private void fetchNewPriceTips() {
        this.fetchingPriceTips = true;
        this.userAction = UserAction.UpdateOnScreen;
        setLoading(this.adapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), Strap.make().mo11639kv(ListingRequestConstants.JSON_CURRENCY_KEY, this.controller.getCurrencyCode())).withListener((Observer) this.priceTipsListener).execute(this.requestManager);
    }

    private void updateSmartPricing() {
        this.adapter.markErrors(false);
        setLoading(this.adapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), Strap.make().mo11637kv(ListingRequestConstants.JSON_PRICE_KEY, this.adapter.getPrice()).mo11639kv(ListingRequestConstants.JSON_CURRENCY_KEY, this.adapter.getCurrentCurrencyCode())).withListener((Observer) this.basePriceListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSSmartPricing;
    }

    static /* synthetic */ void lambda$new$0(LYSSmartPricingFragment lYSSmartPricingFragment, DemandBasedPricingResponse response) {
        lYSSmartPricingFragment.logSmartPricingChangesIfNeeded();
        lYSSmartPricingFragment.controller.setDynamicPricingControls(response.getPricingControl());
        lYSSmartPricingFragment.navigateInFlow(LYSStep.SetPrice);
    }

    static /* synthetic */ void lambda$new$1(LYSSmartPricingFragment lYSSmartPricingFragment, AirRequestNetworkException e) {
        lYSSmartPricingFragment.adapter.markErrors(true);
        NetworkUtil.tryShowErrorWithSnackbar(lYSSmartPricingFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$3(LYSSmartPricingFragment lYSSmartPricingFragment, DemandBasedPricingResponse response) {
        lYSSmartPricingFragment.controller.setDynamicPricingControls(response.getPricingControl());
        lYSSmartPricingFragment.adapter.updateCurrency(lYSSmartPricingFragment.getContext(), lYSSmartPricingFragment.controller.getListing(), lYSSmartPricingFragment.controller.getCurrencyCode(), lYSSmartPricingFragment.controller.getDynamicPricingControls());
    }

    static /* synthetic */ void lambda$new$4(LYSSmartPricingFragment lYSSmartPricingFragment, Boolean success) {
        lYSSmartPricingFragment.setLoadingFinished(success.booleanValue(), lYSSmartPricingFragment.adapter);
        lYSSmartPricingFragment.fetchingPriceTips = false;
    }

    static /* synthetic */ void lambda$new$6(LYSSmartPricingFragment lYSSmartPricingFragment, SimpleListingResponse response) {
        lYSSmartPricingFragment.controller.setListing(response.listing);
        DemandBasedPricingRequest.forFetch(lYSSmartPricingFragment.controller.getListing()).withListener((Observer) lYSSmartPricingFragment.smartPricingFetchListener).execute(lYSSmartPricingFragment.requestManager);
    }

    static /* synthetic */ void lambda$new$7(LYSSmartPricingFragment lYSSmartPricingFragment, Boolean success) {
        if (!success.booleanValue()) {
            lYSSmartPricingFragment.setLoadingFinished(false, lYSSmartPricingFragment.adapter);
            lYSSmartPricingFragment.fetchingPriceTips = false;
        }
    }

    static /* synthetic */ void lambda$new$9(LYSSmartPricingFragment lYSSmartPricingFragment, SimpleListingResponse response) {
        lYSSmartPricingFragment.logBasePriceChangeIfNeeded();
        lYSSmartPricingFragment.controller.setListing(response.listing);
        DemandBasedPricingRequest.setEnableSmartPricing(lYSSmartPricingFragment.adapter.getNewPricingSettings()).withListener((Observer) lYSSmartPricingFragment.smartPricingUpdateListener).execute(lYSSmartPricingFragment.requestManager);
    }

    static /* synthetic */ void lambda$new$10(LYSSmartPricingFragment lYSSmartPricingFragment, AirRequestNetworkException e) {
        lYSSmartPricingFragment.adapter.markErrors(true);
        lYSSmartPricingFragment.setLoadingFinished(false, lYSSmartPricingFragment.adapter);
        NetworkUtil.tryShowErrorWithSnackbar(lYSSmartPricingFragment.getView(), e);
    }

    private void logBasePriceChangeIfNeeded() {
        Listing listing = this.controller.getListing();
        if (listing.getListingPrice() != this.adapter.getPrice()) {
            this.pricingJitneyLogger.changeBasePrice(this.adapter.getCurrentCurrencyCode(), (long) listing.getListingPrice(), (long) listing.getAutoPricingDaily(), (long) this.adapter.getPrice());
        }
    }

    private void logSmartPricingChangesIfNeeded() {
        Listing listing = this.controller.getListing();
        DynamicPricingControl smartPricingSettings = this.adapter.getNewPricingSettings();
        DynamicPricingControl oldSmartPricingSettings = this.controller.getDynamicPricingControls();
        if (oldSmartPricingSettings.isEnabled() != smartPricingSettings.isEnabled()) {
            if (smartPricingSettings.isEnabled()) {
                this.pricingJitneyLogger.enableSmartPricing(listing.getListingCurrency(), smartPricingSettings);
            } else {
                this.pricingJitneyLogger.enableSmartPricing(listing.getListingCurrency(), oldSmartPricingSettings);
            }
        }
        if (oldSmartPricingSettings.getMaxPrice() != smartPricingSettings.getMaxPrice()) {
            this.pricingJitneyLogger.changeSmartPricingMaxPrice(listing.getListingCurrency(), (long) oldSmartPricingSettings.getMaxPrice(), (long) oldSmartPricingSettings.getSuggestedMaxPrice(), smartPricingSettings.toSmartPricingSettingsContext());
        }
        if (oldSmartPricingSettings.getMinPrice() != smartPricingSettings.getMinPrice()) {
            this.pricingJitneyLogger.changeSmartPricingMinPrice(listing.getListingCurrency(), (long) oldSmartPricingSettings.getMinPrice(), (long) oldSmartPricingSettings.getSuggestedMinPrice(), smartPricingSettings.toSmartPricingSettingsContext());
        }
    }
}
