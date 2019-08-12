package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.BankTransferLegacyPayoutBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.BankTransferLegacyPayoutBody.Builder;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class BankTransferInfoFragment extends BasePaymentInfoFragment {
    final RequestListener<PaymentInstrumentResponse> createPayoutRequestListener = new C0699RL().onResponse(BankTransferInfoFragment$$Lambda$1.lambdaFactory$(this)).onError(BankTransferInfoFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SheetInputText ibanSheetInputText;
    @BindView
    AirButton submitButton;
    @BindView
    SheetInputText swiftCodeSheetInputText;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BankTransferInfoFragment.this.submitButton.setEnabled(!TextUtils.isEmpty(BankTransferInfoFragment.this.swiftCodeSheetInputText.getText().toString()) && !TextUtils.isEmpty(BankTransferInfoFragment.this.ibanSheetInputText.getText().toString()));
        }
    };
    @BindView
    AirToolbar toolbar;

    public static BankTransferInfoFragment newInstance() {
        return new BankTransferInfoFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_bank_transfer_info, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.submitButton.setEnabled(false);
        this.swiftCodeSheetInputText.addTextChangedListener(this.textWatcher);
        this.ibanSheetInputText.addTextChangedListener(this.textWatcher);
        return view;
    }

    public void onDestroyView() {
        this.swiftCodeSheetInputText.removeTextChangedListener(this.textWatcher);
        this.ibanSheetInputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSubmitButtonClick() {
        BankTransferLegacyPayoutBody body = new Builder().address(getPaymentInfoActivity().getAddress()).fullName(getPaymentInfoActivity().getName()).swiftCode(this.swiftCodeSheetInputText.getText().toString()).iban(this.ibanSheetInputText.getText().toString()).targetCurrency(getPaymentInfoActivity().getPayoutCurrency()).userId(String.valueOf(this.mAccountManager.getCurrentUserId())).build();
        this.submitButton.setState(State.Loading);
        CreatePaymentInstrumentRequest.forLegacyBankTransferPayout(body).withListener((Observer) this.createPayoutRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(BankTransferInfoFragment bankTransferInfoFragment, PaymentInstrumentResponse response) {
        bankTransferInfoFragment.submitButton.setState(State.Success);
        bankTransferInfoFragment.getNavigationController().doneWithBankTransferInfo();
    }

    static /* synthetic */ void lambda$new$1(BankTransferInfoFragment bankTransferInfoFragment, AirRequestNetworkException error) {
        bankTransferInfoFragment.submitButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(bankTransferInfoFragment.getView(), error);
    }
}
