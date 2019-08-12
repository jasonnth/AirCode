package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.PayoutFormManager;
import com.airbnb.android.payout.PayoutFormManager.Listener;
import com.airbnb.android.payout.create.PayoutFormRuleType;
import com.airbnb.android.payout.create.controllers.AddPayoutAccountInfoEpoxyController;
import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import com.google.common.collect.FluentIterable;
import java.util.List;
import java.util.Map;

public class AddPayoutAccountInfoFragment extends BaseAddPayoutMethodFragment implements Listener, AddPayoutAccountInfoEpoxyController.Listener {
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    private AddPayoutAccountInfoEpoxyController epoxyController;
    private PayoutFormManager payoutFormManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    private Map<PayoutFormField, PayoutFormRuleType> validationErrors;

    public static AddPayoutAccountInfoFragment newInstance() {
        return new AddPayoutAccountInfoFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar_and_advance_button, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.epoxyController = new AddPayoutAccountInfoEpoxyController(getActivity(), this);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.payoutFormManager = this.dataController.getPayoutFormManager();
        this.payoutFormManager.setListener(this);
        this.advanceFooter.setButtonOnClickListener(AddPayoutAccountInfoFragment$$Lambda$1.lambdaFactory$(this));
        refreshEpoxyController();
        setMenuVisibility(hasHelpContent(this.payoutFormManager.getPayoutInput()));
    }

    static /* synthetic */ void lambda$onActivityCreated$0(AddPayoutAccountInfoFragment addPayoutAccountInfoFragment, View v) {
        KeyboardUtils.dismissSoftKeyboard(addPayoutAccountInfoFragment.getView());
        if (addPayoutAccountInfoFragment.payoutFormManager.isPayoutFormValid()) {
            addPayoutAccountInfoFragment.logPayoutSetup(C2548PayoutMethodSetupPage.AddAccountInfo, C2545PayoutMethodAction.Next);
            addPayoutAccountInfoFragment.navigationController.navigateToChoosePayoutAdress();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7552R.C7555menu.menu_help, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7552R.C7554id.menu_help) {
            return super.onOptionsItemSelected(item);
        }
        logPayoutSetup(C2548PayoutMethodSetupPage.AddAccountInfo, C2545PayoutMethodAction.Help);
        this.navigationController.showHelpContentFragment(AddPayoutMethodHelpFragment.newInstance());
        return true;
    }

    private boolean hasHelpContent(List<PayoutFormFieldInputWrapper> payoutInput) {
        return FluentIterable.from((Iterable<E>) payoutInput).anyMatch(AddPayoutAccountInfoFragment$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ boolean lambda$hasHelpContent$1(PayoutFormFieldInputWrapper input) {
        return input.payoutFormField().helperText() != null;
    }

    public void onInputChanged(PayoutFormField changedField, String changedValue) {
        this.payoutFormManager.updateAccountInfo(changedField, changedValue);
        if (this.payoutFormManager.hasValidationError(changedField)) {
            this.payoutFormManager.clearValidationError(changedField);
            refreshEpoxyController();
        }
    }

    private void refreshEpoxyController() {
        this.epoxyController.setData(this.payoutFormManager.getPayoutInput());
    }

    public void onPayoutFormValidationError() {
        logInputErrors();
        refreshEpoxyController();
    }

    private void logInputErrors() {
        for (PayoutFormFieldInputWrapper wrapper : this.payoutFormManager.getPayoutInput()) {
            if (wrapper.hasValidationError()) {
                this.addPayoutMethodJitneyLogger.payoutMethodError(wrapper.validationErrorType(), wrapper.payoutFormField().label(), this.dataController.getSelectedPayoutInfoForm().payoutMethodType(), this.dataController.getPayoutCountryCode(), this.dataController.getPayoutCurrency());
            }
        }
    }
}
