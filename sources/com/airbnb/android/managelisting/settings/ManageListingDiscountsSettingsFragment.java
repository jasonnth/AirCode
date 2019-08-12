package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
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
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.requests.CalendarPricingSettingsRequest;
import com.airbnb.android.core.requests.UpdateCalendarPricingSettingsRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.CalendarPricingSettingsResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.listing.adapters.LengthOfStayDiscountsEpoxyController;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter.Listener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.listing.utils.PricingJitneyHelper;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.jitney.event.logging.LongTermPriceDiscountTypes.p139v1.C2376LongTermPriceDiscountTypes;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingDiscountsSettingsFragment extends ManageListingBaseFragment {
    private LongTermDiscountsAdapter adapter;
    private LengthOfStayDiscountsEpoxyController epoxyController;
    final RequestListener<CalendarPricingSettingsResponse> getPriceSettingsListener = new C0699RL().onResponse(ManageListingDiscountsSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingDiscountsSettingsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(ManageListingDiscountsSettingsFragment$$Lambda$3.lambdaFactory$(this)).build();
    private final Listener listener = new Listener() {
        public void validStateUpdated(boolean valid) {
            if (ManageListingDiscountsSettingsFragment.this.saveButton != null) {
                ManageListingDiscountsSettingsFragment.this.saveButton.setEnabled(valid);
            }
        }

        public void showLengthOfStayDiscountLearnMore() {
            ManageListingDiscountsSettingsFragment.this.controller.actionExecutor.aboutLengthOfStayDiscounts();
        }
    };
    LoggingContextFactory loggingContextFactory;
    private final LengthOfStayDiscountsEpoxyController.Listener losListener = new LengthOfStayDiscountsEpoxyController.Listener() {
        public void showLengthOfStayDiscountLearnMore() {
            ManageListingDiscountsSettingsFragment.this.controller.actionExecutor.aboutLengthOfStayDiscounts();
        }

        public void onTipClicked(int lengthOfStayNights) {
            switch (lengthOfStayNights) {
                case 7:
                    ManageListingDiscountsSettingsFragment.this.pricingJitneyLogger.adoptLongTermDiscountTip(ManageListingDiscountsSettingsFragment.this.controller.getListing().getAutoWeeklyFactor(), ManageListingDiscountsSettingsFragment.this.controller.getListing().getAutoWeeklyFactor(), ManageListingDiscountsSettingsFragment.this.controller.getListing().getWeeklyPriceFactor().getFactorValue().doubleValue(), C2376LongTermPriceDiscountTypes.Weekly);
                    return;
                case 28:
                    ManageListingDiscountsSettingsFragment.this.pricingJitneyLogger.adoptLongTermDiscountTip(ManageListingDiscountsSettingsFragment.this.controller.getListing().getAutoMonthlyFactor(), ManageListingDiscountsSettingsFragment.this.controller.getListing().getAutoMonthlyFactor(), ManageListingDiscountsSettingsFragment.this.controller.getListing().getMonthlyPriceFactor().getFactorValue().doubleValue(), C2376LongTermPriceDiscountTypes.Monthly);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public PricingJitneyLogger pricingJitneyLogger;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    TipView tipView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingDiscountsSettingsFragment$$Lambda$7.lambdaFactory$(this)).onError(ManageListingDiscountsSettingsFragment$$Lambda$8.lambdaFactory$(this)).onComplete(ManageListingDiscountsSettingsFragment$$Lambda$9.lambdaFactory$(this)).build();
    final RequestListener<CalendarPricingSettingsResponse> updatePriceSettingsListener = new C0699RL().onResponse(ManageListingDiscountsSettingsFragment$$Lambda$4.lambdaFactory$(this)).onError(ManageListingDiscountsSettingsFragment$$Lambda$5.lambdaFactory$(this)).onComplete(ManageListingDiscountsSettingsFragment$$Lambda$6.lambdaFactory$(this)).build();

    public static ManageListingDiscountsSettingsFragment create() {
        return new ManageListingDiscountsSettingsFragment();
    }

    static /* synthetic */ void lambda$new$2(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, Boolean success) {
        manageListingDiscountsSettingsFragment.saveButton.setEnabled(true);
        manageListingDiscountsSettingsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$3(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, CalendarPricingSettingsResponse response) {
        manageListingDiscountsSettingsFragment.saveButton.setState(State.Success);
        manageListingDiscountsSettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$4(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingDiscountsSettingsFragment.getView(), e);
        manageListingDiscountsSettingsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$5(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, Boolean success) {
        manageListingDiscountsSettingsFragment.epoxyController.setInputEnabled(true, false);
        manageListingDiscountsSettingsFragment.tipView.setEnabled(false);
    }

    static /* synthetic */ void lambda$new$6(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, SimpleListingResponse response) {
        manageListingDiscountsSettingsFragment.saveButton.setState(State.Success);
        PricingJitneyHelper.logDiscountsChange(manageListingDiscountsSettingsFragment.pricingJitneyLogger, manageListingDiscountsSettingsFragment.controller.getListing(), response.listing);
        manageListingDiscountsSettingsFragment.controller.setListing(response.listing);
        manageListingDiscountsSettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$7(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingDiscountsSettingsFragment.getView(), e);
        manageListingDiscountsSettingsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$8(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment, Boolean success) {
        if (FeatureToggles.showHostPricingRules()) {
            manageListingDiscountsSettingsFragment.epoxyController.setInputEnabled(true, true);
        } else {
            manageListingDiscountsSettingsFragment.adapter.setInputEnabled(true);
        }
        manageListingDiscountsSettingsFragment.tipView.setEnabled(false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        Listing listing = this.controller.getListing();
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, listing.getId());
        if (FeatureToggles.showHostPricingRules()) {
            ListingLongTermDiscountValues averagePrices = this.controller.getAveragePrices();
            this.epoxyController = new LengthOfStayDiscountsEpoxyController(getContext(), this.losListener, ListingTextUtils.getListingCurrency(listing), SanitizeUtils.zeroIfNull(Integer.valueOf(averagePrices.getWeeklyDiscountValueNative())), SanitizeUtils.zeroIfNull(Integer.valueOf(averagePrices.getMonthlyDiscountValueNative())), savedInstanceState);
            return;
        }
        this.adapter = new LongTermDiscountsAdapter(getContext(), ListingDisplayMode.ML, listing, this.controller.getAveragePrices(), this.listener, this.pricingJitneyLogger, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (FeatureToggles.showHostPricingRules()) {
            this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
            if (savedInstanceState == null) {
                initialLoad();
            }
        } else {
            this.recyclerView.setAdapter(this.adapter);
        }
        this.tipView.setTipTextRes(C7368R.string.manage_listing_long_term_discounts_how_calculated);
        this.tipView.setTipClickListener(ManageListingDiscountsSettingsFragment$$Lambda$10.lambdaFactory$(this));
        this.tipView.initKeyboardHiding(getAirActivity(), view);
        this.tipView.setVisibility(0);
        return view;
    }

    private void initialLoad() {
        this.saveButton.setEnabled(false);
        CalendarPricingSettingsRequest.forListing(this.controller.getListing().getId()).withListener((Observer) this.getPriceSettingsListener).execute(this.requestManager);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (FeatureToggles.showHostPricingRules()) {
            this.epoxyController.onSaveInstanceState(outState);
        } else {
            this.adapter.onSaveInstanceState(outState);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        if (FeatureToggles.showHostPricingRules()) {
            if (!this.epoxyController.hasValueChanged() || !this.saveButton.isEnabled()) {
                return false;
            }
            return true;
        } else if (!this.adapter.hasChanged(this.controller.getListing()) || !this.saveButton.isEnabled()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (FeatureToggles.showHostPricingRules()) {
            newSaveClicked();
        } else {
            origSaveClicked();
        }
    }

    private void newSaveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            this.epoxyController.markErrors(false, false);
            getFragmentManager().popBackStack();
        } else if (this.epoxyController.showWeeklyPriceHigherError()) {
            KeyboardUtils.dismissSoftKeyboard(getView());
            this.epoxyController.markErrors(true, true);
            ErrorUtils.showErrorUsingSnackbar(getView(), C7368R.string.ml_discounts_change_discounts, C7368R.string.ml_discounts_change_discounts_explanation, 0);
        } else {
            this.epoxyController.setInputEnabled(false, false);
            this.epoxyController.markErrors(false, true);
            this.tipView.setEnabled(false);
            this.saveButton.setState(State.Loading);
            UpdateCalendarPricingSettingsRequest.forLengthOfStayRules(this.controller.getListing().getId(), this.epoxyController.getRules()).withListener((Observer) this.updatePriceSettingsListener).execute(this.requestManager);
        }
    }

    private void origSaveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            this.adapter.markErrors(false);
            getFragmentManager().popBackStack();
        } else if (this.adapter.showWeeklyPriceHigherError()) {
            KeyboardUtils.dismissSoftKeyboard(getView());
            this.adapter.markErrors(true);
            ErrorUtils.showErrorUsingSnackbar(getView(), C7368R.string.ml_discounts_change_discounts, C7368R.string.ml_discounts_change_discounts_explanation, 0);
        } else {
            this.adapter.setInputEnabled(false);
            this.tipView.setEnabled(false);
            this.saveButton.setState(State.Loading);
            this.adapter.markErrors(false);
            UpdateListingRequest.forFields(this.controller.getListing().getId(), this.adapter.getDiscountsSettings()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingDiscounts;
    }
}
