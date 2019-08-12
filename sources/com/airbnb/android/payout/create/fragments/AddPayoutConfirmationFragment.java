package com.airbnb.android.payout.create.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ValueRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.List;

public class AddPayoutConfirmationFragment extends BaseAddPayoutMethodFragment {
    private static final int REQUEST_CODE_CANCEL = 0;
    private static final int REQUEST_CODE_CLOSE = 222;
    @BindView
    FixedActionFooter confirmButton;
    private AddPayoutConfirmationEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static class AddPayoutConfirmationEpoxyController extends AirEpoxyController {
        private BankDepositAccountType accountType;
        private AirAddress airAddress;
        private final Context context;
        SimpleTextRowEpoxyModel_ disclosureRowModel;
        DocumentMarqueeEpoxyModel_ documentMarqueeModel;
        private List<PayoutFormFieldInputWrapper> formFieldInputWrappers;
        private String payoutCountry;
        private String payoutCurrencySymbol;

        public AddPayoutConfirmationEpoxyController(Context context2) {
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            boolean z = true;
            this.documentMarqueeModel.titleRes(C7552R.string.add_payout_review_account_detail);
            if (this.formFieldInputWrappers != null) {
                for (PayoutFormFieldInputWrapper wrapper : this.formFieldInputWrappers) {
                    if (!wrapper.payoutFormField().confirmField()) {
                        new ValueRowEpoxyModel_().m5770id((long) wrapper.hashCode()).titleText(wrapper.payoutFormField().label()).valueText(wrapper.inputValue()).addTo(this);
                    }
                }
            }
            if (this.accountType != null) {
                new ValueRowEpoxyModel_().m5770id((long) this.accountType.hashCode()).titleRes(C7552R.string.payout_method_account_type).valueRes(this.accountType.getServerKey()).addTo(this);
            }
            if (this.airAddress != null) {
                new ValueRowEpoxyModel_().m5770id((long) this.airAddress.hashCode()).titleRes(C7552R.string.address).valueText(this.airAddress.streetAddressOne()).addTo(this);
            }
            SimpleTextRowEpoxyModel_ small = this.disclosureRowModel.text(this.context.getResources().getString(C7552R.string.add_payout_method_confirm_disclosure_text, new Object[]{this.payoutCurrencySymbol, this.payoutCountry})).small();
            if (this.payoutCountry == null || this.payoutCurrencySymbol == null) {
                z = false;
            }
            small.addIf(z, (EpoxyController) this);
        }

        public void setFormInput(List<PayoutFormFieldInputWrapper> fieldInputWrappers) {
            this.formFieldInputWrappers = fieldInputWrappers;
        }

        public void setAddress(AirAddress selectedPayoutAddress) {
            this.airAddress = selectedPayoutAddress;
        }

        public void setAccountType(BankDepositAccountType accountType2) {
            this.accountType = accountType2;
        }

        public void setPayoutCountry(String payoutCountry2, String payoutCurrencySymbol2) {
            this.payoutCountry = payoutCountry2;
            this.payoutCurrencySymbol = payoutCurrencySymbol2;
        }
    }

    public class AddPayoutConfirmationEpoxyController_EpoxyHelper extends ControllerHelper<AddPayoutConfirmationEpoxyController> {
        private final AddPayoutConfirmationEpoxyController controller;

        public AddPayoutConfirmationEpoxyController_EpoxyHelper(AddPayoutConfirmationEpoxyController controller2) {
            this.controller = controller2;
        }

        public void resetAutoModels() {
            this.controller.disclosureRowModel = new SimpleTextRowEpoxyModel_();
            this.controller.disclosureRowModel.m5578id(-1);
            setControllerToStageTo(this.controller.disclosureRowModel, this.controller);
            this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
            this.controller.documentMarqueeModel.m4534id(-2);
            setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
        }
    }

    public static AddPayoutConfirmationFragment newInstance() {
        return new AddPayoutConfirmationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_add_payout_confirmation, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.confirmButton.setButtonText(C7552R.string.add_payout_confirm_account_button);
        this.epoxyController = new AddPayoutConfirmationEpoxyController(getActivity());
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.epoxyController.setFormInput(this.dataController.getPayoutFormManager().getPayoutInput());
        this.epoxyController.setAddress(this.dataController.getSelectedPayoutAddress());
        this.epoxyController.setAccountType(this.dataController.getBankAccountType());
        this.epoxyController.setPayoutCountry(LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), this.dataController.getPayoutCountryCode()), this.dataController.getPayoutCurrency());
        this.epoxyController.requestModelBuild();
        this.confirmButton.setButtonOnClickListener(AddPayoutConfirmationFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onActivityCreated$0(AddPayoutConfirmationFragment addPayoutConfirmationFragment, View v) {
        addPayoutConfirmationFragment.logPayoutSetup(C2548PayoutMethodSetupPage.ReviewDetails, C2545PayoutMethodAction.ConfirmAccount);
        addPayoutConfirmationFragment.confirmButton.setButtonLoading(true);
        addPayoutConfirmationFragment.dataController.createPayoutMethod();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7552R.C7555menu.menu_close, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7552R.C7554id.menu_close) {
            return super.onOptionsItemSelected(item);
        }
        logPayoutSetup(C2548PayoutMethodSetupPage.ReviewDetails, C2545PayoutMethodAction.Close);
        ZenDialog.builder().withBodyText(C7552R.string.add_payout_review_confirm_close).withDualButton(C7552R.string.cancel, 0, C7552R.string.yes, (int) REQUEST_CODE_CLOSE, (Fragment) this).create().show(getFragmentManager(), getClass().getCanonicalName());
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == REQUEST_CODE_CLOSE) {
            getActivity().finish();
        }
    }

    public void onCreatePayoutMethodSuccess() {
        super.onCreatePayoutMethodSuccess();
        this.confirmButton.setButtonLoading(false);
        this.navigationController.navigateToAddPayoutFinish();
    }

    public void onCreatePayoutMethodError(AirRequestNetworkException error) {
        super.onCreatePayoutMethodError(error);
        this.confirmButton.setButtonLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }
}
