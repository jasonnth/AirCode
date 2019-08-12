package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.controllers.ChoosePayoutMethodEpoxyController;
import com.airbnb.android.payout.create.controllers.ChoosePayoutMethodEpoxyController.Listener;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.jitney.event.logging.PayoutMethodSelectAction.p192v1.C2547PayoutMethodSelectAction;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import java.util.List;

public class ChoosePayoutMethodFragment extends BaseAddPayoutMethodFragment implements Listener {
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    private ChoosePayoutMethodEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ChoosePayoutMethodFragment newInstance() {
        return new ChoosePayoutMethodFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_choose_payout_method, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.advanceFooter.setVisibility(8);
        this.epoxyController = new ChoosePayoutMethodEpoxyController(getActivity(), this);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.dataController.hasAvailablePayoutMethods()) {
            refreshUI();
        } else {
            fetchAvailablePayoutMethodTypes(this.dataController.getPayoutCountryCode());
        }
        this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.ChooseMethodImpression);
    }

    public void onFetchedAvailablePayoutMethods(List<PayoutInfoForm> fetchedPayoutInfoForms) {
        super.onFetchedAvailablePayoutMethods(fetchedPayoutInfoForms);
        refreshUI();
    }

    private void refreshUI() {
        List<PayoutInfoForm> payoutInfoForms = this.dataController.getPayoutInfoForms();
        this.epoxyController.updateAvailablePayoutMethods(LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), this.dataController.getPayoutCountryCode()), payoutInfoForms);
        if (this.advanceFooter == null) {
            return;
        }
        if (isSingleMethod(payoutInfoForms)) {
            PayoutInfoForm selectedPayoutInfoForm = (PayoutInfoForm) payoutInfoForms.get(0);
            this.advanceFooter.setVisibility(0);
            this.advanceFooter.setButtonOnClickListener(ChoosePayoutMethodFragment$$Lambda$1.lambdaFactory$(this, selectedPayoutInfoForm));
            return;
        }
        this.advanceFooter.setVisibility(8);
    }

    private boolean isSingleMethod(List<PayoutInfoForm> payoutInfoForms) {
        return payoutInfoForms.size() == 1;
    }

    private void fetchAvailablePayoutMethodTypes(String country) {
        this.advanceFooter.setVisibility(8);
        this.epoxyController.setLoadingState();
        this.dataController.fetchPayoutInfoForms(country);
    }

    public void onClickPaymentMethodTypeRow(PayoutInfoForm payoutInfoForm, String selectedCurrency) {
        this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.MethodSelect);
        this.dataController.setSelectedPayoutInfoForm(payoutInfoForm);
        this.dataController.setPayoutCurrency(selectedCurrency);
        this.navigationController.navigateToPayoutMethodInfo(payoutInfoForm);
    }

    public void onClickChangeCountry() {
        this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.ChangeCountry);
        this.navigationController.showCountrySelectionFragment(SelectPayoutCountryFragment.newInstanceWithDefaultCountry(this.dataController.getPayoutCountryCode()));
    }
}
