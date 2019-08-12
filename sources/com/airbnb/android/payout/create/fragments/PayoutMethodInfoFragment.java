package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.controllers.PayoutMethodInfoEpoxyController;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import icepick.State;

public class PayoutMethodInfoFragment extends BaseAddPayoutMethodFragment {
    private static final String ARG_PAYOUT_METHOD = "arg_payout_method";
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    private PayoutMethodInfoEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @State
    PayoutInfoForm selectedPayoutInfoForm;
    @BindView
    AirToolbar toolbar;

    public static PayoutMethodInfoFragment withSelectedPayoutMethod(PayoutInfoForm selectedPayoutInfoForm2) {
        return (PayoutMethodInfoFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PayoutMethodInfoFragment()).putParcelable(ARG_PAYOUT_METHOD, selectedPayoutInfoForm2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.selectedPayoutInfoForm = (PayoutInfoForm) getArguments().getParcelable(ARG_PAYOUT_METHOD);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar_and_advance_button, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController = new PayoutMethodInfoEpoxyController(getActivity(), this.selectedPayoutInfoForm);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.advanceFooter.setButtonOnClickListener(PayoutMethodInfoFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onActivityCreated$0(PayoutMethodInfoFragment payoutMethodInfoFragment, View v) {
        payoutMethodInfoFragment.logPayoutSetup(C2548PayoutMethodSetupPage.InfoPage, C2545PayoutMethodAction.Next);
        payoutMethodInfoFragment.navigationController.doneWithPayoutMethodInfo(payoutMethodInfoFragment.selectedPayoutInfoForm);
    }
}
