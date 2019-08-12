package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import com.google.common.collect.Lists;
import java.util.List;

public class AddPayoutAddressFragment extends BaseAddPayoutMethodFragment {
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    @BindView
    InlineInputRow cityInputRow;
    @BindView
    InlineInputRow countryInputRow;
    @BindView
    DocumentMarquee documentMarquee;
    private List<InlineInputRow> inputValidationRows;
    @BindView
    InlineInputRow stateInputRow;
    @BindView
    InlineInputRow streetAddressOneInputRow;
    @BindView
    InlineInputRow streetAddressTwoInputRow;
    @BindView
    AirToolbar toolbar;
    @BindView
    InlineInputRow zipCodeInputRow;

    public static AddPayoutAddressFragment newInstance() {
        return new AddPayoutAddressFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_add_payout_address, container, false);
        bindViews(view);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setToolbar(this.toolbar);
        this.inputValidationRows = Lists.newArrayList((E[]) new InlineInputRow[]{this.streetAddressOneInputRow, this.cityInputRow, this.stateInputRow, this.zipCodeInputRow});
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUI();
    }

    public void onFetchRedirectUrlSuccess() {
        this.advanceFooter.setButtonLoading(false);
        this.navigationController.doneWithAddPayoutAddress((String) Check.notNull(this.dataController.getRedirectUrl()));
    }

    public void onFetchRedirectUrlError(AirRequestNetworkException error) {
        this.advanceFooter.setButtonLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }

    private void finishAddPayoutAddress() {
        this.dataController.setPayoutAddress(getInputAddress());
        if (this.dataController.getSelectedPayoutInfoForm().isURLRedirect()) {
            this.dataController.fetchRedirectUrl();
            this.advanceFooter.setButtonLoading(true);
            return;
        }
        this.navigationController.doneWithAddPayoutAddress();
    }

    private void showValidationErrors() {
        for (InlineInputRow row : this.inputValidationRows) {
            if (TextUtils.isEmpty(row.getInputText())) {
                setValidationErrors(row);
            }
        }
    }

    private void setValidationErrors(InlineInputRow inputRow) {
        inputRow.showError(true);
        inputRow.setOnInputChangedListener(AddPayoutAddressFragment$$Lambda$1.lambdaFactory$(this, inputRow));
    }

    /* access modifiers changed from: private */
    public void removeError(InlineInputRow inputRow) {
        inputRow.showError(false);
    }

    private boolean hasValidInput() {
        for (InlineInputRow row : this.inputValidationRows) {
            if (TextUtils.isEmpty(row.getInputText())) {
                return false;
            }
        }
        return true;
    }

    private AirAddress getInputAddress() {
        return AirAddress.builder().streetAddressOne(this.streetAddressOneInputRow.getInputText()).streetAddressTwo(this.streetAddressTwoInputRow.getInputText()).city(this.cityInputRow.getInputText()).state(this.stateInputRow.getInputText()).postalCode(this.zipCodeInputRow.getInputText()).country(this.dataController.getPayoutCountryCode()).build();
    }

    private void setupUI() {
        this.documentMarquee.setTitle(C7552R.string.add_payout_method_choose_address_marquee_title);
        this.streetAddressOneInputRow.setTitle(C7552R.string.add_payout_address_street_address_one);
        this.streetAddressTwoInputRow.setTitle(C7552R.string.add_payout_address_street_address_two);
        this.cityInputRow.setTitle(C7552R.string.city);
        this.stateInputRow.setTitle(C7552R.string.add_payout_address_state);
        this.zipCodeInputRow.setTitle(C7552R.string.add_payout_address_zip_code);
        this.countryInputRow.setTitle(C7552R.string.country);
        this.countryInputRow.setInputText((CharSequence) LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), this.dataController.getPayoutCountryCode()));
        this.countryInputRow.setEnabled(false);
        this.advanceFooter.setButtonOnClickListener(addressNextStepClickListener());
    }

    private OnClickListener addressNextStepClickListener() {
        return AddPayoutAddressFragment$$Lambda$2.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$addressNextStepClickListener$1(AddPayoutAddressFragment addPayoutAddressFragment, View v) {
        KeyboardUtils.dismissSoftKeyboard(addPayoutAddressFragment.getView());
        if (addPayoutAddressFragment.hasValidInput()) {
            addPayoutAddressFragment.logPayoutSetup(C2548PayoutMethodSetupPage.NewAddress, C2545PayoutMethodAction.Next);
            addPayoutAddressFragment.finishAddPayoutAddress();
            return;
        }
        addPayoutAddressFragment.showValidationErrors();
    }
}
