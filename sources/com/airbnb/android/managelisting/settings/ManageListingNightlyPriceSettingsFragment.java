package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.PricingJitneyHelper;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ManageListingNightlyPriceSettingsFragment extends ManageListingBaseFragment {
    private final int HOSTING_FREQUENCY_SNACKBAR_DURATION = 8000;
    private final NightlyPriceActionListener actionListener = new NightlyPriceActionListener() {
        public void smartPricingTip() {
            ManageListingNightlyPriceSettingsFragment.this.controller.actionExecutor.smartPricingTip(true);
        }

        public void hostingFrequencyInfo(DesiredHostingFrequencyVersion version) {
            ManageListingNightlyPriceSettingsFragment.this.controller.actionExecutor.hostingFrequencyInfo(version);
        }

        public void showCurrencyOptions(String currentCurrency) {
        }

        public void showUpdateAppSnackbar() {
            new Handler().post(ManageListingNightlyPriceSettingsFragment$1$$Lambda$1.lambdaFactory$(this));
        }

        static /* synthetic */ void lambda$showUpdateAppSnackbar$1(C74351 r3) {
            if (ManageListingNightlyPriceSettingsFragment.this.isResumed()) {
                ManageListingNightlyPriceSettingsFragment.this.snackbarWrapper.view(ManageListingNightlyPriceSettingsFragment.this.getView()).title(C7368R.string.smart_pricing_unhandled_frequency_version_title, true).body(C7368R.string.smart_pricing_unhandled_frequency_version_body).action(C7368R.string.smart_pricing_unhandled_frequency_version_action, ManageListingNightlyPriceSettingsFragment$1$$Lambda$2.lambdaFactory$(r3)).duration(8000).buildAndShow();
            }
        }

        public void startEditingValues() {
        }
    };
    private NightlyPriceAdapter adapter;
    final RequestListener<SimpleListingResponse> basePriceListener = new C0699RL().onResponse(ManageListingNightlyPriceSettingsFragment$$Lambda$5.lambdaFactory$(this)).onError(ManageListingNightlyPriceSettingsFragment$$Lambda$6.lambdaFactory$(this)).build();
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(ManageListingNightlyPriceSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingNightlyPriceSettingsFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    CalendarStore calendarStore;
    LoggingContextFactory loggingContextFactory;
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    final RequestListener<DemandBasedPricingResponse> smartPricingListener = new C0699RL().onResponse(ManageListingNightlyPriceSettingsFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingNightlyPriceSettingsFragment$$Lambda$4.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public final SnackbarWrapper snackbarWrapper = new SnackbarWrapper();
    @BindView
    AirToolbar toolbar;

    static ManageListingNightlyPriceSettingsFragment create() {
        return new ManageListingNightlyPriceSettingsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        DynamicPricingControl priceSettings = this.controller.getListing().getDynamicPricingControls();
        this.adapter = new NightlyPriceAdapter(getContext(), this.controller.getListing(), priceSettings, priceSettings.isEnabled(), this.actionListener, ListingDisplayMode.ML, savedInstanceState);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, this.controller.getListingId());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing(), this.controller.getListing().getDynamicPricingControls());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setInputEnabled(false);
        this.adapter.markErrors(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        makeUpdateListingRequest();
    }

    private void makeUpdateListingRequest() {
        UpdateListingRequest basePriceRequest = UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_PRICE_KEY, Integer.valueOf(this.adapter.getPrice()));
        DemandBasedPricingRequest smartPricingRequest = DemandBasedPricingRequest.setEnableSmartPricing(this.adapter.getNewPricingSettings());
        if (this.adapter.isSmartPricingEnabled()) {
            smartPricingRequest.withListener((Observer) this.smartPricingListener).execute(this.requestManager);
        } else if (this.controller.getListing().getDynamicPricingControls().isEnabled()) {
            List<BaseRequestV2<?>> requests = new ArrayList<>();
            basePriceRequest.skipCache();
            smartPricingRequest.skipCache();
            requests.add(basePriceRequest);
            requests.add(smartPricingRequest);
            new AirBatchRequest(requests, this.batchListener).execute(this.requestManager);
        } else {
            basePriceRequest.withListener((Observer) this.basePriceListener).execute(this.requestManager);
        }
        this.calendarStore.setCacheResetTime(AirDateTime.now());
    }

    static /* synthetic */ void lambda$new$0(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment, AirBatchResponse response) {
        manageListingNightlyPriceSettingsFragment.logBasePriceChangeIfNeeded();
        manageListingNightlyPriceSettingsFragment.logSmartPricingChangesIfNeeded();
        manageListingNightlyPriceSettingsFragment.responseFinished();
    }

    static /* synthetic */ void lambda$new$1(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment, DemandBasedPricingResponse response) {
        manageListingNightlyPriceSettingsFragment.logSmartPricingChangesIfNeeded();
        manageListingNightlyPriceSettingsFragment.responseFinished();
    }

    static /* synthetic */ void lambda$new$2(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment, SimpleListingResponse response) {
        manageListingNightlyPriceSettingsFragment.logBasePriceChangeIfNeeded();
        manageListingNightlyPriceSettingsFragment.responseFinished();
    }

    private void responseFinished() {
        this.saveButton.setState(State.Success);
        this.controller.actionExecutor.invalidateData();
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        this.adapter.setInputEnabled(true);
        this.adapter.markErrors(true);
        this.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    private void logBasePriceChangeIfNeeded() {
        PricingJitneyHelper.logBasePriceChangesIfNeeded(this.pricingJitneyLogger, this.controller.getListing(), this.adapter.getPrice(), this.adapter.getCurrentCurrencyCode());
    }

    private void logSmartPricingChangesIfNeeded() {
        Listing listing = this.controller.getListing();
        PricingJitneyHelper.logSmartPricingChangesIfNeeded(this.pricingJitneyLogger, this.adapter.getNewPricingSettings(), listing.getDynamicPricingControls(), listing);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingSmartPricing;
    }
}
