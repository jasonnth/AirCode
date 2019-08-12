package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AlipayBody.Builder;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class AlipayNationalIdFragment extends BaseAlipayFragment {
    private static final int NATIONAL_ID_INPUT_LENGTH = 5;
    @BindView
    SheetInputText inputText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    final RequestListener<PaymentInstrumentResponse> requestListener = new C0699RL().onResponse(AlipayNationalIdFragment$$Lambda$1.lambdaFactory$(this)).onError(AlipayNationalIdFragment$$Lambda$2.lambdaFactory$(this)).build();
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            AlipayNationalIdFragment.this.nextButton.setEnabled(AlipayNationalIdFragment.this.inputText.getText().toString().length() == 5);
        }
    };
    @BindView
    AirToolbar toolbar;

    public static AlipayNationalIdFragment newInstance() {
        return new AlipayNationalIdFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_alipay_national_id, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.inputText.addTextChangedListener(this.textWatcher);
        return view;
    }

    public void onDestroyView() {
        this.inputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNext() {
        alipayNationalIdLogging();
        String nationalId = this.inputText.getText().toString();
        getAlipayActivity().setNationalId(nationalId);
        CreatePaymentInstrumentRequest.forAlipay(new Builder().alipayLoginId(getAlipayActivity().getAlipayId()).nationalIdLastFiveDigits(nationalId).build()).withListener((Observer) this.requestListener).execute(this.requestManager);
        this.nextButton.setState(State.Loading);
    }

    private void alipayNationalIdLogging() {
        if (getAlipayActivity().isQuickPay()) {
            this.quickPayJitneyLogger.alipayNationalId();
        } else {
            KonaBookingAnalytics.trackClick("payment_options", "alipay_national_id", getAnalyticsData());
        }
    }

    static /* synthetic */ void lambda$new$0(AlipayNationalIdFragment alipayNationalIdFragment, PaymentInstrumentResponse data) {
        alipayNationalIdFragment.getAlipayActivity().setGibraltarInstrumentId(data.paymentInstrument.getId());
        alipayNationalIdFragment.nextButton.setState(State.Success);
        alipayNationalIdFragment.getNavigationController().doneWithNationalId();
    }

    static /* synthetic */ void lambda$new$1(AlipayNationalIdFragment alipayNationalIdFragment, AirRequestNetworkException e) {
        alipayNationalIdFragment.nextButton.setState(State.Normal);
        ErrorUtils.showErrorUsingSnackbar(alipayNationalIdFragment.getView(), C0704R.string.alipay_error_sending_verification).show();
    }
}
