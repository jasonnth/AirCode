package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalLegacyPayoutBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalLegacyPayoutBody.Builder;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.utils.TextUtil;
import p032rx.Observer;

public class PayPalPayoutFragment extends BasePaymentInfoFragment {
    final RequestListener<PaymentInstrumentResponse> createPayoutRequestListener = new C0699RL().onResponse(PayPalPayoutFragment$$Lambda$1.lambdaFactory$(this)).onError(PayPalPayoutFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SheetInputText emailSheetInput;
    @BindView
    AirButton submitButton;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            PayPalPayoutFragment.this.submitButton.setEnabled(TextUtil.isValidEmail(PayPalPayoutFragment.this.emailSheetInput.getText().toString()));
        }
    };
    @BindView
    AirToolbar toolbar;

    public static PayPalPayoutFragment newInstance() {
        return new PayPalPayoutFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_paypal_payout, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.submitButton.setEnabled(false);
        this.emailSheetInput.addTextChangedListener(this.textWatcher);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSubmitButtonClick() {
        PayPalLegacyPayoutBody body = new Builder().address(getPaymentInfoActivity().getAddress()).paypalEmail(this.emailSheetInput.getText().toString()).targetCurrency(getPaymentInfoActivity().getPayoutCurrency()).userId(String.valueOf(this.mAccountManager.getCurrentUserId())).build();
        this.submitButton.setState(State.Loading);
        CreatePaymentInstrumentRequest.forLegacyPayPalPayout(body).withListener((Observer) this.createPayoutRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(PayPalPayoutFragment payPalPayoutFragment, PaymentInstrumentResponse response) {
        payPalPayoutFragment.submitButton.setState(State.Success);
        payPalPayoutFragment.getNavigationController().doneWithPayPalInfo();
    }

    static /* synthetic */ void lambda$new$1(PayPalPayoutFragment payPalPayoutFragment, AirRequestNetworkException error) {
        payPalPayoutFragment.submitButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(payPalPayoutFragment.getView(), error);
    }
}
