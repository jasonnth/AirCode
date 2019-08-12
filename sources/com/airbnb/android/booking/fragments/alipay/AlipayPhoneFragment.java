package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
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
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AlipayBody.Builder;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class AlipayPhoneFragment extends BaseAlipayFragment {
    @State
    AirPhone airPhone;
    private final PhoneNumberInputViewDelegate delegate = new PhoneNumberInputViewDelegate() {
        public FragmentManager getFragmentManager() {
            return AlipayPhoneFragment.this.getAlipayActivity().getSupportFragmentManager();
        }

        public void afterPhoneNumberTextChanged(AirPhone newAirPhone) {
            AlipayPhoneFragment.this.airPhone = newAirPhone;
            AlipayPhoneFragment.this.nextButton.setEnabled(AlipayPhoneFragment.this.phoneNumberInput.isPhoneNumberValid());
        }

        public void afterPhoneNumberTextChanged(String phoneNumberText, String phoneNumberTextDisplay) {
        }
    };
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @BindView
    PhoneNumberInputSheet phoneNumberInput;
    final RequestListener<PaymentInstrumentResponse> requestListener = new C0699RL().onResponse(AlipayPhoneFragment$$Lambda$1.lambdaFactory$(this)).onError(AlipayPhoneFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public static AlipayPhoneFragment newInstance() {
        return new AlipayPhoneFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_alipay_phone, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.phoneNumberInput.initPhoneNumberInputView(this.delegate);
        if (this.airPhone != null) {
            this.phoneNumberInput.setPhoneNumberEditText(this.airPhone);
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNext() {
        alipayPhoneLogging();
        String phoneNumber = this.airPhone.phoneInputText();
        String countryCode = this.phoneNumberInput.getCountryCode();
        getAlipayActivity().setPhoneNumber(phoneNumber);
        CreatePaymentInstrumentRequest.forAlipay(new Builder().alipayLoginId(getAlipayActivity().getAlipayId()).mobilePhoneNumber(phoneNumber).mobilePhoneCountry(countryCode).build()).withListener((Observer) this.requestListener).execute(this.requestManager);
        this.nextButton.setState(AirButton.State.Loading);
    }

    private void alipayPhoneLogging() {
        if (getAlipayActivity().isQuickPay()) {
            this.quickPayJitneyLogger.alipayPhoneNumber();
        } else {
            KonaBookingAnalytics.trackClick("payment_options", "alipay_phone_number", getAnalyticsData());
        }
    }

    static /* synthetic */ void lambda$new$0(AlipayPhoneFragment alipayPhoneFragment, PaymentInstrumentResponse data) {
        alipayPhoneFragment.getAlipayActivity().setGibraltarInstrumentId(data.paymentInstrument.getId());
        alipayPhoneFragment.nextButton.setState(AirButton.State.Success);
        alipayPhoneFragment.getNavigationController().doneWithPhone();
    }

    static /* synthetic */ void lambda$new$1(AlipayPhoneFragment alipayPhoneFragment, AirRequestNetworkException e) {
        alipayPhoneFragment.nextButton.setState(AirButton.State.Normal);
        ErrorUtils.showErrorUsingSnackbar(alipayPhoneFragment.getView(), C0704R.string.alipay_error_sending_code).show();
    }
}
