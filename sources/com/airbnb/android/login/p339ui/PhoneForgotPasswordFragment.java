package com.airbnb.android.login.p339ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountrySelectedListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.LoginBindings;
import com.airbnb.android.login.LoginComponent.Builder;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.requests.ForgotPasswordRequest.PhoneForgotPasswordStep;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import dagger.Lazy;
import icepick.State;
import p032rx.Observer;
import p032rx.Subscription;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment */
public class PhoneForgotPasswordFragment extends AirFragment implements CountrySelectedListener, PhoneNumberInputViewDelegate {
    private static final String ARG_AIRPHONE = "arg_airphone";
    @State
    AirPhone airPhone;
    @State
    CountryCodeItem countryCodeItem;
    private final OnClickListener countryCodeSelectionButtonClickListener = PhoneForgotPasswordFragment$$Lambda$1.lambdaFactory$(this);
    private final Handler handler = new Handler();
    @BindView
    JellyfishView jellyfishView;
    final RequestListener<ForgotPasswordResponse> listener = new C0699RL().onResponse(PhoneForgotPasswordFragment$$Lambda$2.lambdaFactory$(this)).onError(PhoneForgotPasswordFragment$$Lambda$3.lambdaFactory$(this)).build();
    private Subscription permissionSubscription;
    @BindView
    PhoneNumberInputSheet phoneNumberInputSheet;
    Lazy<SMSMonitor> smsMonitor;
    @BindView
    AirButton submitButton;

    public static Intent newIntent(Context context, AirPhone airPhone2) {
        return TransparentActionBarActivity.intentForFragmentWithoutRequiringAccount(context, PhoneForgotPasswordFragment.class, ((BundleBuilder) new BundleBuilder().putParcelable(ARG_AIRPHONE, airPhone2)).toBundle());
    }

    static /* synthetic */ void lambda$new$0(PhoneForgotPasswordFragment phoneForgotPasswordFragment, View v) {
        CountryCodeSelectionFragment fragment = CountryCodeSelectionFragment.newInstance();
        fragment.setOnCountryCodeSelectedListener(phoneForgotPasswordFragment);
        ((TransparentActionBarActivity) phoneForgotPasswordFragment.getActivity()).showFragment(fragment, FragmentTransitionType.FadeInAndOut, true);
    }

    static /* synthetic */ void lambda$new$2(PhoneForgotPasswordFragment phoneForgotPasswordFragment, ForgotPasswordResponse data) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.FORGOT_PASSWORD_PHONE_RESPONSE, "phone", phoneForgotPasswordFragment.getNavigationTrackingTag(), phoneForgotPasswordFragment.getContext());
        if (data.isSuccess()) {
            phoneForgotPasswordFragment.submitButton.setState(AirButton.State.Success);
            phoneForgotPasswordFragment.handler.postDelayed(PhoneForgotPasswordFragment$$Lambda$5.lambdaFactory$(phoneForgotPasswordFragment, PhoneForgotPasswordConfirmSMSCodeFragment.newInstance(phoneForgotPasswordFragment.airPhone)), 700);
            return;
        }
        phoneForgotPasswordFragment.submitButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(phoneForgotPasswordFragment.getContext(), data.getMessage());
    }

    static /* synthetic */ void lambda$new$3(PhoneForgotPasswordFragment phoneForgotPasswordFragment, AirRequestNetworkException e) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.FORGOT_PASSWORD_PHONE_RESPONSE, "phone", phoneForgotPasswordFragment.getNavigationTrackingTag(), (NetworkException) e);
        phoneForgotPasswordFragment.submitButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(phoneForgotPasswordFragment.getContext(), (NetworkException) e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((LoginBindings) CoreApplication.instance().componentProvider()).loginComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_phone_forgot_password, container, false);
        bindViews(view);
        if (savedInstanceState == null && this.airPhone == null) {
            this.airPhone = (AirPhone) getArguments().getParcelable(ARG_AIRPHONE);
            this.countryCodeItem = this.airPhone.countryCodeItem();
        }
        setupViews();
        return view;
    }

    private void setupViews() {
        this.phoneNumberInputSheet.initPhoneNumberInputView(this);
        this.phoneNumberInputSheet.setCountryCodeSelectionButtonClickListener(this.countryCodeSelectionButtonClickListener);
        this.phoneNumberInputSheet.onNewCountryCodeSelected(this.countryCodeItem);
        this.phoneNumberInputSheet.setPhoneNumberEditText((CharSequence) this.airPhone.phoneInputText());
    }

    public void onDestroyView() {
        this.phoneNumberInputSheet.setCountryCodeSelectionButtonClickListener(null);
        if (this.permissionSubscription != null) {
            this.permissionSubscription.unsubscribe();
        }
        super.onDestroyView();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ((SMSMonitor) this.smsMonitor.get()).onRequestPermissionsResult(requestCode, permissions2, grantResults, this);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void submit() {
        if (this.permissionSubscription != null || !ChinaUtils.enableVerificationCodeAutofill()) {
            doSumbmit();
        } else {
            this.permissionSubscription = ((SMSMonitor) this.smsMonitor.get()).permission(this).subscribe(PhoneForgotPasswordFragment$$Lambda$4.lambdaFactory$(this));
        }
    }

    /* access modifiers changed from: private */
    public void doSumbmit() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.FORGOT_PASSWORD_REQUEST_PHONE_BUTTON, getNavigationTrackingTag());
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.submitButton.setState(AirButton.State.Loading);
        ForgotPasswordRequest.forPhoneForgotPassword(PhoneForgotPasswordStep.RequestSMS, this.airPhone).withListener((Observer) this.listener).execute(this.requestManager);
    }

    public void afterPhoneNumberTextChanged(AirPhone airPhone2) {
        this.airPhone = airPhone2;
        this.submitButton.setEnabled(this.phoneNumberInputSheet.isPhoneNumberValid());
    }

    public void afterPhoneNumberTextChanged(String phoneNumberText, String phoneNumberTextDisplay) {
    }

    public void onCountrySelected(CountryCodeItem item) {
        this.countryCodeItem = item;
    }

    public void onDetach() {
        this.handler.removeCallbacksAndMessages(null);
        super.onDetach();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationForgotPasswordPhone;
    }
}
