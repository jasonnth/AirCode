package com.airbnb.android.listyourspacedls.fragments;

import android.app.Activity;
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
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.BasePriceAdapter;
import com.airbnb.android.listing.adapters.BasePriceAdapter.OnCurrencyRowClickListener;
import com.airbnb.android.listing.adapters.BasePriceAdapter.ValidSettingsListener;
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
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class LYSBasePriceFragment extends LYSBaseFragment {
    private BasePriceAdapter adapter;
    final RequestListener<SimpleListingResponse> basePriceListener = new C0699RL().onResponse(LYSBasePriceFragment$$Lambda$2.lambdaFactory$(this)).onError(LYSBasePriceFragment$$Lambda$3.lambdaFactory$(this)).onComplete(LYSBasePriceFragment$$Lambda$4.lambdaFactory$(this)).build();
    private final OnCurrencyRowClickListener currencyRowClickListener = LYSBasePriceFragment$$Lambda$8.lambdaFactory$(this);
    LoggingContextFactory loggingContextFactory;
    final RequestListener<SimpleListingResponse> priceTipsListener = new C0699RL().onResponse(LYSBasePriceFragment$$Lambda$5.lambdaFactory$(this)).onComplete(LYSBasePriceFragment$$Lambda$6.lambdaFactory$(this)).onError(LYSBasePriceFragment$$Lambda$7.lambdaFactory$(this)).build();
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    private final ValidSettingsListener validSettingsListener = LYSBasePriceFragment$$Lambda$1.lambdaFactory$(this);

    public static LYSBasePriceFragment newInstance(boolean isStandalone) {
        return (LYSBasePriceFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSBasePriceFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.adapter = new BasePriceAdapter(ListingDisplayMode.LYS, getContext(), this.controller.getListing(), this.validSettingsListener, this.currencyRowClickListener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(v);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ListYourSpace, C2586PricingSettingsSectionType.PricingSettings, this.controller.getListing().getId());
        if (this.comingFromBackstack) {
            this.adapter = new BasePriceAdapter(ListingDisplayMode.LYS, getContext(), this.controller.getListing(), this.validSettingsListener, this.currencyRowClickListener, null);
        }
        this.recyclerView.setAdapter(this.adapter);
        return v;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.controller.setCurrencyCode(this.controller.getListing().getListingCurrency());
    }

    public void dataUpdated() {
        super.dataUpdated();
        if (!this.adapter.getCurrentCurrencyCode().equals(this.controller.getCurrencyCode())) {
            fetchNewPriceTips();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (canSaveChanges()) {
            updateBasePrice();
        } else {
            navigateInFlow(LYSStep.SetPrice);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClicked() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        Listing listing = this.controller.getListing();
        return (listing.getListingPrice() != this.adapter.getPrice() || !listing.getListingCurrency().equals(this.adapter.getCurrentCurrencyCode())) && ((AirButton) Check.notNull(this.nextButton)).isEnabled();
    }

    private void updateBasePrice() {
        setLoading(this.adapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), Strap.make().mo11637kv(ListingRequestConstants.JSON_PRICE_KEY, this.adapter.getPrice()).mo11639kv(ListingRequestConstants.JSON_CURRENCY_KEY, this.adapter.getCurrentCurrencyCode())).withListener((Observer) this.basePriceListener).execute(this.requestManager);
    }

    private void fetchNewPriceTips() {
        this.userAction = UserAction.UpdateOnScreen;
        setLoading(this.adapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), Strap.make().mo11639kv(ListingRequestConstants.JSON_CURRENCY_KEY, this.controller.getCurrencyCode())).withListener((Observer) this.priceTipsListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSBasePrice;
    }

    static /* synthetic */ void lambda$null$0(LYSBasePriceFragment lYSBasePriceFragment, boolean valid) {
        if (lYSBasePriceFragment.isResumed() && lYSBasePriceFragment.nextButton != null) {
            lYSBasePriceFragment.nextButton.setEnabled(valid);
        }
    }

    static /* synthetic */ void lambda$new$2(LYSBasePriceFragment lYSBasePriceFragment, SimpleListingResponse response) {
        lYSBasePriceFragment.logBasePriceChangeIfNeeded();
        lYSBasePriceFragment.controller.setListing(response.listing);
        lYSBasePriceFragment.navigateInFlow(LYSStep.SetPrice);
    }

    static /* synthetic */ void lambda$new$5(LYSBasePriceFragment lYSBasePriceFragment, SimpleListingResponse response) {
        lYSBasePriceFragment.adapter.updateCurrencyCode(lYSBasePriceFragment.getContext(), response.listing, lYSBasePriceFragment.controller.getCurrencyCode());
        lYSBasePriceFragment.controller.setListing(response.listing);
    }

    static /* synthetic */ void lambda$new$8(LYSBasePriceFragment lYSBasePriceFragment, String currentCurrency) {
        KeyboardUtils.dismissSoftKeyboard((Activity) lYSBasePriceFragment.getActivity());
        lYSBasePriceFragment.controller.showCurrencyModal(currentCurrency);
    }

    private void logBasePriceChangeIfNeeded() {
        Listing listing = this.controller.getListing();
        if (listing.getListingPrice() != this.adapter.getPrice()) {
            this.pricingJitneyLogger.changeBasePrice(this.adapter.getCurrentCurrencyCode(), (long) listing.getListingPrice(), (long) listing.getAutoPricingDaily(), (long) this.adapter.getPrice());
        }
    }
}
