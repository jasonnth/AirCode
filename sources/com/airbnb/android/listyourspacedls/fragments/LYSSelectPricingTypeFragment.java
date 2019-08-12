package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.LYSSelectPricingTypeAdapter;
import com.airbnb.android.listyourspacedls.adapters.LYSSelectPricingTypeAdapter.SelectPricingTypeListener;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.Lists;
import p032rx.Observer;

public class LYSSelectPricingTypeFragment extends LYSBaseFragment {
    private LYSSelectPricingTypeAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(LYSSelectPricingTypeFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSSelectPricingTypeFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSSelectPricingTypeFragment$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();
    LYSJitneyLogger jitneyLogger;
    private final SelectPricingTypeListener listener = new SelectPricingTypeListener() {
        public void isValid(boolean valid) {
            if (LYSSelectPricingTypeFragment.this.nextButton != null) {
                ((AirButton) Check.notNull(LYSSelectPricingTypeFragment.this.nextButton)).setEnabled(valid);
            }
        }

        public void logChoosePricingMode(boolean isSmartPricingSelected) {
            LYSSelectPricingTypeFragment.this.jitneyLogger.logChoosePricingMode(Long.valueOf(LYSSelectPricingTypeFragment.this.controller.getListing().getId()), isSmartPricingSelected);
        }
    };
    LoggingContextFactory loggingContextFactory;
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updatePricingModeListener = new C0699RL().onResponse(LYSSelectPricingTypeFragment$$Lambda$4.lambdaFactory$(this)).onError(LYSSelectPricingTypeFragment$$Lambda$5.lambdaFactory$(this)).onComplete(LYSSelectPricingTypeFragment$$Lambda$6.lambdaFactory$(this)).build();

    public static LYSSelectPricingTypeFragment newInstance() {
        return new LYSSelectPricingTypeFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment, AirBatchResponse response) {
        DemandBasedPricingResponse pricingControlResponse = (DemandBasedPricingResponse) response.singleResponse(DemandBasedPricingResponse.class);
        lYSSelectPricingTypeFragment.controller.setListing(((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing);
        DynamicPricingControl pricingControl = pricingControlResponse.getPricingControl();
        if (lYSSelectPricingTypeFragment.controller.getDynamicPricingControls().isEnabled() != pricingControl.isEnabled()) {
            lYSSelectPricingTypeFragment.pricingJitneyLogger.enableSmartPricing(lYSSelectPricingTypeFragment.controller.getListing().getListingCurrency(), pricingControl);
        }
        lYSSelectPricingTypeFragment.controller.setDynamicPricingControls(pricingControl);
        lYSSelectPricingTypeFragment.navigateInFlow(LYSStep.SelectPricingType);
    }

    static /* synthetic */ void lambda$new$3(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment, SimpleListingResponse response) {
        lYSSelectPricingTypeFragment.controller.setListing(response.listing);
        lYSSelectPricingTypeFragment.navigateInFlow(LYSStep.SelectPricingType);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.adapter = new LYSSelectPricingTypeAdapter(this.controller.getListing(), this.listener, savedInstanceState);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ListYourSpace, C2586PricingSettingsSectionType.PricingSettings, this.controller.getListing().getId());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        showTip(C7251R.string.manage_listing_pricing_disclaimer_call_to_action, LYSSelectPricingTypeFragment$$Lambda$7.lambdaFactory$(this));
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.checkValidity();
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.controller.getListing().getListYourSpacePricingMode() != this.adapter.getPricingMode() && ((AirButton) Check.notNull(this.nextButton)).isEnabled();
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (canSaveChanges()) {
            updatePricingType();
        } else {
            navigateInFlow(LYSStep.SelectPricingType);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    private void updatePricingType() {
        setLoading(this.adapter);
        UpdateListingRequest updatePricingModeRequest = (UpdateListingRequest) UpdateListingRequest.forPriceModeLYS(this.controller.getListing().getId(), this.adapter.getPricingMode()).skipCache();
        if (shouldUpdateDynamicPricingControls()) {
            new AirBatchRequest(Lists.newArrayList((E[]) new BaseRequestV2[]{DemandBasedPricingRequest.setEnableSmartPricing(this.adapter.getNewDynamicPricingControl(this.controller.getDynamicPricingControls())), updatePricingModeRequest}), this.batchListener).execute(this.requestManager);
            return;
        }
        updatePricingModeRequest.withListener((Observer) this.updatePricingModeListener).execute(this.requestManager);
    }

    private boolean shouldUpdateDynamicPricingControls() {
        DynamicPricingControl settings = this.controller.getDynamicPricingControls();
        return !this.adapter.isSmartPricingSelected() || (settings.getMinPrice() > 0 && settings.getMaxPrice() > 0);
    }

    /* access modifiers changed from: private */
    public void showDisclaimer() {
        this.controller.showTipModal(C7251R.string.manage_listing_pricing_disclaimer_title, ListingTextUtils.createPricingDisclaimer(getContext(), !PricingFeatureToggles.showSmartPricing(this.controller.getListing())), NavigationTag.LYSChoosePricingModeTip);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSChoosePricingMode;
    }
}
