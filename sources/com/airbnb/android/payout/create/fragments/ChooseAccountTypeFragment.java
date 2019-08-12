package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.controllers.ChooseAccountTypeEpoxyController;
import com.airbnb.android.payout.create.controllers.ChooseAccountTypeEpoxyController.Listener;
import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;

public class ChooseAccountTypeFragment extends BaseAddPayoutMethodFragment implements Listener {
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    private ChooseAccountTypeEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ChooseAccountTypeFragment newInstance() {
        return new ChooseAccountTypeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar_and_advance_button, container, false);
        bindViews(view);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setToolbar(this.toolbar);
        this.advanceFooter.setButtonEnabled(false);
        this.epoxyController = new ChooseAccountTypeEpoxyController(this, savedInstanceState);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.advanceFooter.setButtonOnClickListener(ChooseAccountTypeFragment$$Lambda$1.lambdaFactory$(this));
        if (this.dataController.hasBankAccountType()) {
            onAccountTypeUpdated(this.dataController.getBankAccountType());
        }
        this.epoxyController.requestModelBuild();
    }

    static /* synthetic */ void lambda$onActivityCreated$0(ChooseAccountTypeFragment chooseAccountTypeFragment, View v) {
        chooseAccountTypeFragment.logPayoutSetup(C2548PayoutMethodSetupPage.BankDepositAccountType, chooseAccountTypeFragment.dataController.getBankAccountType() == BankDepositAccountType.Checking ? C2545PayoutMethodAction.CheckingAccountNext : C2545PayoutMethodAction.SavingsAccountNext);
        chooseAccountTypeFragment.navigationController.navigateToAddDetailedAccountInfo();
    }

    public void onAccountTypeUpdated(BankDepositAccountType seletecAccountType) {
        this.dataController.setBankAccountType(seletecAccountType);
        this.advanceFooter.setButtonEnabled(true);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }
}
