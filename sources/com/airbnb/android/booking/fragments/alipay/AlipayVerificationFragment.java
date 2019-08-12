package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.AlipayActivity;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.utils.PaymentUtils;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.LegacyPaymentOptionRequest;
import com.airbnb.android.core.requests.payments.UpdatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AlipayBody.Builder;
import com.airbnb.android.core.requests.payments.requestbodies.UpdatePaymentInstrumentRequestBody.AlipayVerificationBody;
import com.airbnb.android.core.responses.LegacyPaymentOptionResponse;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class AlipayVerificationFragment extends BaseAlipayFragment {
    private static final int VERIFICATION_CODE_LENGTH = 6;
    @BindView
    SheetInputText inputText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    final RequestListener<LegacyPaymentOptionResponse> paymentOptionResponseRequestListener = new RequestListener<LegacyPaymentOptionResponse>() {
        public void onResponse(LegacyPaymentOptionResponse data) {
            AlipayVerificationFragment.this.nextButton.setState(State.Success);
            KeyboardUtils.dismissSoftKeyboard(AlipayVerificationFragment.this.getView());
            AlipayActivity alipayActivity = AlipayVerificationFragment.this.getAlipayActivity();
            if (alipayActivity.isQuickPay()) {
                alipayActivity.finishWithPaymentInstrument(data.paymentOption.toPaymentInstrumentWithGibraltarId());
            } else {
                alipayActivity.finishWithPaymentInstrument(data.paymentOption.toLegacyPaymentInstrument());
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AlipayVerificationFragment.this.nextButton.setState(State.Normal);
            ErrorUtils.showErrorUsingSnackbar(AlipayVerificationFragment.this.getView(), C0704R.string.alipay_error_sending_verification).show();
        }
    };
    final RequestListener<PaymentInstrumentResponse> resendCodeRequestListener = new RequestListener<PaymentInstrumentResponse>() {
        public void onResponse(PaymentInstrumentResponse data) {
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            ErrorUtils.showErrorUsingSnackbar(AlipayVerificationFragment.this.getView(), C0704R.string.alipay_error_sending_code).show();
        }
    };
    @BindView
    AirToolbar toolbar;
    @icepick.State
    long verificationCodeTimestamp = 0;
    final RequestListener<PaymentInstrumentResponse> verificationRequestListener = new RequestListener<PaymentInstrumentResponse>() {
        public void onResponse(PaymentInstrumentResponse data) {
            LegacyPaymentOptionRequest.forGibraltarId(data.paymentInstrument.getId()).withListener((Observer) AlipayVerificationFragment.this.paymentOptionResponseRequestListener).execute(AlipayVerificationFragment.this.requestManager);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AlipayVerificationFragment.this.nextButton.setState(State.Normal);
            KonaBookingAnalytics.trackError("payment_options", "eeror_alipay_verify", AlipayVerificationFragment.this.getAnalyticsData());
            ErrorUtils.showErrorUsingSnackbar(AlipayVerificationFragment.this.getView(), C0704R.string.alipay_error_sending_verification).show();
        }
    };
    private final SimpleTextWatcher verificationTextWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            AlipayVerificationFragment.this.nextButton.setEnabled(AlipayVerificationFragment.this.inputText.getText().length() == 6);
        }
    };

    public static AlipayVerificationFragment newInstance() {
        return new AlipayVerificationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_alipay_verification, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.inputText.setActionOnClickListener(AlipayVerificationFragment$$Lambda$1.lambdaFactory$(this));
        this.inputText.setMaxLength(6);
        this.inputText.addTextChangedListener(this.verificationTextWatcher);
        if (this.verificationCodeTimestamp == 0) {
            this.verificationCodeTimestamp = System.currentTimeMillis();
        }
        return view;
    }

    public void onDestroyView() {
        this.inputText.removeTextChangedListener(this.verificationTextWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void resendCode() {
        alipayVerificationLogging(true);
        this.nextButton.setState(State.Normal);
        Toast.makeText(getActivity(), C0704R.string.alipay_resent_code, 0).show();
        if (PaymentUtils.isAlipayVerificationRetryAllowed(this.verificationCodeTimestamp)) {
            Builder alipayBodyBuilder = new Builder().alipayLoginId(getAlipayActivity().getAlipayId());
            if (!TextUtils.isEmpty(getAlipayActivity().getNationalId())) {
                alipayBodyBuilder.nationalIdLastFiveDigits(getAlipayActivity().getNationalId());
            } else {
                alipayBodyBuilder.mobilePhoneNumber(getAlipayActivity().getPhoneNumber()).mobilePhoneCountry(getAlipayActivity().getCountryCode());
            }
            this.verificationCodeTimestamp = System.currentTimeMillis();
            CreatePaymentInstrumentRequest.forAlipay(alipayBodyBuilder.build()).withListener((Observer) this.resendCodeRequestListener).execute(this.requestManager);
        }
    }

    private void alipayVerificationLogging(boolean isResend) {
        if (getAlipayActivity().isQuickPay()) {
            this.quickPayJitneyLogger.alipayVerificationSubmit();
        }
        if (isResend) {
            KonaBookingAnalytics.trackClick("payment_options", "alipay_verification_resend", getAnalyticsData());
        } else {
            KonaBookingAnalytics.trackClick("payment_options", "alipay_verification_submit", getAnalyticsData());
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNext() {
        alipayVerificationLogging(false);
        UpdatePaymentInstrumentRequest.forAlipayVerification(getAlipayActivity().getGibraltarInstrumentId(), new AlipayVerificationBody(this.inputText.getText().toString())).withListener((Observer) this.verificationRequestListener).execute(this.requestManager);
        this.nextButton.setState(State.Loading);
    }
}
