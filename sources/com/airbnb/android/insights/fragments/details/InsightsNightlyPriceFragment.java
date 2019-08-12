package com.airbnb.android.insights.fragments.details;

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
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.fragments.AirFragment;
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
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.insights.InsightsGraph;
import com.airbnb.android.insights.fragments.InsightsDetailCardFragment;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.fragments.ListingHostingFrequencyInfoFragment;
import com.airbnb.android.listing.fragments.ListingSmartPricingTipFragment;
import com.airbnb.android.listing.utils.PricingJitneyHelper;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class InsightsNightlyPriceFragment extends AirFragment {
    public static final String ARG_LISTING = "listing";
    public static final String ARG_PRICING_CONTROLS = "pricing_controls";
    private final NightlyPriceActionListener actionListener = new NightlyPriceActionListener() {
        public void smartPricingTip() {
            ((InsightsDetailCardFragment) InsightsNightlyPriceFragment.this.getParentFragment()).showModal(ListingSmartPricingTipFragment.create(true, true));
        }

        public void hostingFrequencyInfo(DesiredHostingFrequencyVersion version) {
            ((InsightsDetailCardFragment) InsightsNightlyPriceFragment.this.getParentFragment()).showModal(ListingHostingFrequencyInfoFragment.newInstance(InsightsNightlyPriceFragment.this.pricingControl.getDesiredHostingFrequencyVersion(), true));
        }

        public void showCurrencyOptions(String currentCurrency) {
        }

        public void showUpdateAppSnackbar() {
            new Handler().post(InsightsNightlyPriceFragment$1$$Lambda$1.lambdaFactory$(this, new SnackbarWrapper()));
        }

        static /* synthetic */ void lambda$showUpdateAppSnackbar$1(C65721 r3, SnackbarWrapper snackbarWrapper) {
            if (InsightsNightlyPriceFragment.this.isResumed()) {
                snackbarWrapper.view(InsightsNightlyPriceFragment.this.getView()).title(C6552R.string.smart_pricing_unhandled_frequency_version_title, true).body(C6552R.string.smart_pricing_unhandled_frequency_version_body).action(C6552R.string.smart_pricing_unhandled_frequency_version_action, InsightsNightlyPriceFragment$1$$Lambda$2.lambdaFactory$(r3)).duration(0).buildAndShow();
            }
        }

        public void startEditingValues() {
            InsightsNightlyPriceFragment.this.saveButton.setVisibility(0);
            InsightsNightlyPriceFragment.this.inEditMode = true;
            ((InsightsDetailCardFragment) InsightsNightlyPriceFragment.this.getParentFragment()).toggleInfoContainer(true);
        }
    };
    private NightlyPriceAdapter adapter;
    final RequestListener<SimpleListingResponse> basePriceListener = new C0699RL().onResponse(InsightsNightlyPriceFragment$$Lambda$7.lambdaFactory$(this)).onError(InsightsNightlyPriceFragment$$Lambda$8.lambdaFactory$(this)).build();
    final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(InsightsNightlyPriceFragment$$Lambda$1.lambdaFactory$(this)).onError(InsightsNightlyPriceFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
    protected CalendarStore calendarStore;
    @State
    boolean inEditMode;
    private Listing listing;
    /* access modifiers changed from: private */
    public DynamicPricingControl pricingControl;
    private PricingJitneyLogger pricingJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    final RequestListener<DemandBasedPricingResponse> smartPricingListener = new C0699RL().onResponse(InsightsNightlyPriceFragment$$Lambda$5.lambdaFactory$(this)).onError(InsightsNightlyPriceFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public static InsightsNightlyPriceFragment newInstance(Listing listing2, DynamicPricingControl pricingControl2) {
        return (InsightsNightlyPriceFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsNightlyPriceFragment()).putParcelable("listing", listing2)).putParcelable(ARG_PRICING_CONTROLS, pricingControl2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InsightsGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6552R.layout.fragment_listing_recycler_view_with_save_and_x, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(InsightsNightlyPriceFragment$$Lambda$9.lambdaFactory$(this));
        this.pricingControl = (DynamicPricingControl) getArguments().getParcelable(ARG_PRICING_CONTROLS);
        this.listing = (Listing) getArguments().getParcelable("listing");
        this.adapter = new NightlyPriceAdapter(getContext(), this.listing, this.pricingControl, this.pricingControl.isEnabled(), this.actionListener, ListingDisplayMode.Insights, savedInstanceState);
        this.recyclerView.setAdapter(this.adapter);
        ViewLibUtils.setVisibleIf(this.saveButton, this.inEditMode);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, this.listing.getId());
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void onSave() {
        this.adapter.markErrors(false);
        if (!this.adapter.hasChanged(this.listing, this.pricingControl)) {
            hideSaveButton();
            this.adapter.setIsEditing(false);
            ((InsightsDetailCardFragment) getParentFragment()).toggleInfoContainer(false);
            return;
        }
        this.adapter.setInputEnabled(false);
        this.saveButton.setState(AirButton.State.Loading);
        makeUpdateListingRequest();
    }

    private void makeUpdateListingRequest() {
        UpdateListingRequest basePriceRequest = UpdateListingRequest.forField(this.listing.getId(), ListingRequestConstants.JSON_PRICE_KEY, Integer.valueOf(this.adapter.getPrice()));
        DemandBasedPricingRequest smartPricingRequest = DemandBasedPricingRequest.setEnableSmartPricing(this.adapter.getNewPricingSettings());
        if (this.adapter.isSmartPricingEnabled()) {
            smartPricingRequest.withListener((Observer) this.smartPricingListener).execute(this.requestManager);
        } else if (this.pricingControl.isEnabled()) {
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

    static /* synthetic */ void lambda$new$1(InsightsNightlyPriceFragment insightsNightlyPriceFragment, AirBatchResponse response) {
        PricingJitneyHelper.logBasePriceChangesIfNeeded(insightsNightlyPriceFragment.pricingJitneyLogger, insightsNightlyPriceFragment.listing, insightsNightlyPriceFragment.adapter.getPrice(), insightsNightlyPriceFragment.adapter.getCurrentCurrencyCode());
        PricingJitneyHelper.logSmartPricingChangesIfNeeded(insightsNightlyPriceFragment.pricingJitneyLogger, insightsNightlyPriceFragment.adapter.getNewPricingSettings(), insightsNightlyPriceFragment.pricingControl, insightsNightlyPriceFragment.listing);
        insightsNightlyPriceFragment.responseFinished();
    }

    static /* synthetic */ void lambda$new$2(InsightsNightlyPriceFragment insightsNightlyPriceFragment, DemandBasedPricingResponse response) {
        PricingJitneyHelper.logSmartPricingChangesIfNeeded(insightsNightlyPriceFragment.pricingJitneyLogger, insightsNightlyPriceFragment.adapter.getNewPricingSettings(), insightsNightlyPriceFragment.pricingControl, insightsNightlyPriceFragment.listing);
        insightsNightlyPriceFragment.responseFinished();
    }

    static /* synthetic */ void lambda$new$3(InsightsNightlyPriceFragment insightsNightlyPriceFragment, SimpleListingResponse response) {
        PricingJitneyHelper.logBasePriceChangesIfNeeded(insightsNightlyPriceFragment.pricingJitneyLogger, insightsNightlyPriceFragment.listing, insightsNightlyPriceFragment.adapter.getPrice(), insightsNightlyPriceFragment.adapter.getCurrentCurrencyCode());
        insightsNightlyPriceFragment.responseFinished();
    }

    private void responseFinished() {
        this.adapter.setInputEnabled(true);
        hideSaveButton();
        this.adapter.setIsEditing(false);
        ((InsightsDetailCardFragment) getParentFragment()).toggleInfoContainer(false);
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        this.adapter.setInputEnabled(true);
        this.adapter.markErrors(true);
        this.saveButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    private void hideSaveButton() {
        this.inEditMode = false;
        this.saveButton.setState(AirButton.State.Normal);
        this.saveButton.setVisibility(8);
    }
}
