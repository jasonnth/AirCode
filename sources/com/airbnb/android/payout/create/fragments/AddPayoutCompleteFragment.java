package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.payout.C7552R;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.primitives.AirButton;

public class AddPayoutCompleteFragment extends BaseAddPayoutMethodFragment {
    @BindView
    AirButton addMorePayoutButton;
    @BindView
    AirButton managePaymentsButton;

    public static AddPayoutCompleteFragment newInstance() {
        return new AddPayoutCompleteFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_add_payout_complete, container, false);
        bindViews(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.managePaymentsButton.setOnClickListener(AddPayoutCompleteFragment$$Lambda$1.lambdaFactory$(this));
        this.addMorePayoutButton.setOnClickListener(AddPayoutCompleteFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onActivityCreated$0(AddPayoutCompleteFragment addPayoutCompleteFragment, View v) {
        addPayoutCompleteFragment.logPayoutSetup(C2548PayoutMethodSetupPage.MethodSubmitted, C2545PayoutMethodAction.ManagePayments);
        addPayoutCompleteFragment.startActivity(PayoutActivityIntents.forManagePayoutMethods(addPayoutCompleteFragment.getActivity()));
        addPayoutCompleteFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$onActivityCreated$1(AddPayoutCompleteFragment addPayoutCompleteFragment, View v) {
        addPayoutCompleteFragment.logPayoutSetup(C2548PayoutMethodSetupPage.MethodSubmitted, C2545PayoutMethodAction.AddAnother);
        addPayoutCompleteFragment.startActivity(PayoutActivityIntents.forAddPayoutMethod(addPayoutCompleteFragment.getActivity(), addPayoutCompleteFragment.dataController.getPayoutCountryCode()));
        addPayoutCompleteFragment.getActivity().finish();
    }
}
