package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController.Listener;
import com.airbnb.android.cohosting.shared.CohostingPaymentSettings;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.SetCohostingContractRequest;
import com.airbnb.android.core.responses.SetCohostingContractResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class CohostingShareEarningsWithFeeOptionFragment extends CohostManagementBaseFragment {
    private CohostingShareEarningsEpoxyController epoxyController;
    private final Listener listener = CohostingShareEarningsWithFeeOptionFragment$$Lambda$1.lambdaFactory$(this);
    @State
    ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<SetCohostingContractResponse> setCohostingContractListener = new C0699RL().onResponse(CohostingShareEarningsWithFeeOptionFragment$$Lambda$2.lambdaFactory$(this)).onError(CohostingShareEarningsWithFeeOptionFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirButton shareButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment, SetCohostingContractResponse response) {
        cohostingShareEarningsWithFeeOptionFragment.shareButton.setState(AirButton.State.Success);
        cohostingShareEarningsWithFeeOptionFragment.controller.setCohostingContract(response.cohostingContract, cohostingShareEarningsWithFeeOptionFragment.listingManager.getId());
        cohostingShareEarningsWithFeeOptionFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment, AirRequestNetworkException e) {
        cohostingShareEarningsWithFeeOptionFragment.shareButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cohostingShareEarningsWithFeeOptionFragment.getView(), e);
    }

    public static CohostingShareEarningsWithFeeOptionFragment create(String listingManagerId) {
        return (CohostingShareEarningsWithFeeOptionFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingShareEarningsWithFeeOptionFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, listingManagerId)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
        }
        Check.state(this.listingManager != null, "Listing manager can not be null");
        this.epoxyController = new CohostingShareEarningsEpoxyController(getContext(), this.listener, this.controller, this.listingManager, this.mCurrencyHelper, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_share_earnings, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController.changeFeeTypeIfPossible();
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.logger.logShareEarningsModalImpression(this.controller.getCohostingContext(), this.listingManager);
        return view;
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    @OnClick
    public void setContract() {
        this.shareButton.setState(AirButton.State.Loading);
        CohostingPaymentSettings cohostingPaymentSettings = this.epoxyController.getCohostingPaymentSettings();
        int percentage = cohostingPaymentSettings.actualPercentage();
        int minimumFee = cohostingPaymentSettings.actualMinimumFee();
        int fixedFee = cohostingPaymentSettings.actualFixedAmount();
        String amountCurrency = cohostingPaymentSettings.amountCurrency();
        boolean includeCleaningFee = cohostingPaymentSettings.includeCleaningFee();
        new SetCohostingContractRequest(this.controller.getListing().getUserId(), this.listingManager.getUser().getId(), this.controller.getListingId(), percentage, minimumFee, fixedFee, amountCurrency, includeCleaningFee).withListener((Observer) this.setCohostingContractListener).execute(this.requestManager);
        logShareEarningsButtonClicked(percentage, minimumFee, fixedFee, amountCurrency, includeCleaningFee);
    }

    /* access modifiers changed from: protected */
    public void enableShareButton(boolean enableShareButton) {
        this.shareButton.setEnabled(enableShareButton);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (!canSaveChanges()) {
            logDismissEvent();
        }
        return super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onUnsavedChangesDiscarded() {
        super.onUnsavedChangesDiscarded();
        logDismissEvent();
    }

    public boolean canSaveChanges() {
        return this.epoxyController.hasChanged();
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.epoxyController.changeFeeTypeIfPossible();
    }

    private void logDismissEvent() {
        this.logger.logShareEarningsModalDismissed(this.controller.getCohostingContext(), this.listingManager);
    }

    private void logShareEarningsButtonClicked(int percentage, int minimumFee, int fixedAmount, String amountCurrency, boolean includeCleaningFee) {
        ArrayMap<String, Object> loggingParams = new ArrayMap<>();
        loggingParams.put("percent_of_shared_earnings", Integer.valueOf(percentage));
        loggingParams.put("minimum_fee", Integer.valueOf(minimumFee));
        loggingParams.put("fixed_fee", Integer.valueOf(fixedAmount));
        loggingParams.put("amount_currency", amountCurrency);
        loggingParams.put("pay_cleaning_fees", Boolean.valueOf(includeCleaningFee));
        this.logger.logShareEarningsButtonClicked(loggingParams, this.controller.getCohostingContext());
    }
}
