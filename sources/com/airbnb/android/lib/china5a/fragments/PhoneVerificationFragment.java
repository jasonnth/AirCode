package com.airbnb.android.lib.china5a.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountryCodeSelectionStyle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.LibBindings;
import com.airbnb.android.lib.LibComponent.Builder;
import com.airbnb.android.lib.china5a.BaseVerificationFragment;
import com.airbnb.android.lib.china5a.FiveAxiomRepository;
import com.airbnb.android.lib.china5a.phone.PhoneVerificationPresenter;
import com.airbnb.android.lib.china5a.phone.PhoneVerificationView;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.sms.SMSMonitor.Type;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SheetMarquee.Style;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import dagger.Lazy;
import icepick.State;
import java.util.concurrent.TimeUnit;
import p032rx.Observable;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;

public class PhoneVerificationFragment extends BaseVerificationFragment<PhoneVerificationPresenter> implements PhoneVerificationView {
    private static final String ARG_PLAY_ANIM = "play_anim";
    private static final long ENTER_ANIM_DURATION_MS = 300;
    private static final long RESEND_INTERVAL_MS = 60000;
    @State
    AirPhone airPhone;
    @BindView
    View codeInputContainer;
    @BindView
    SheetInputText codeInputSheet;
    @State
    String confirmationCode;
    private Subscription countdownSubscription;
    @State
    CountryCodeItem countryCodeItem;
    @State
    long lastRequestTime = 0;
    @State
    boolean listeningToSMS;
    @BindView
    AirButton nextButton;
    private Subscription permissionSubscription;
    @BindView
    SheetMarquee phoneConfirmationSheetMarquee;
    @BindView
    PhoneNumberInputSheet phoneNumberInputSheet;
    @BindView
    AirButton sendCodeBtn;
    Lazy<SMSMonitor> smsMonitor;
    private Subscription smsSubscription;
    private Snackbar snackbar;

    public static Fragment newInstance(boolean playAnim) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new PhoneVerificationFragment()).putBoolean(ARG_PLAY_ANIM, playAnim)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((LibBindings) AirbnbApplication.instance().componentProvider()).libComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C0880R.layout.fragment_5a_china_phone_verification, container, false);
        bindViews(view);
        Style.WHITE.setStyle(this.phoneConfirmationSheetMarquee);
        PhoneNumberInputSheet.Style.WHITE.setStyle(this.phoneNumberInputSheet);
        SheetInputText.Style.WHITE.setStyle(this.codeInputSheet);
        this.phoneNumberInputSheet.initPhoneNumberInputView(new PhoneNumberInputViewDelegate() {
            public FragmentManager getFragmentManager() {
                return PhoneVerificationFragment.this.getFragmentManager();
            }

            public void afterPhoneNumberTextChanged(AirPhone airPhone) {
                PhoneVerificationFragment.this.airPhone = airPhone;
                PhoneVerificationFragment.this.enableSendButtion(PhoneVerificationFragment.this.phoneNumberInputSheet.isPhoneNumberValid() && !PhoneVerificationFragment.this.isResendOnCooldown());
                if (PhoneVerificationFragment.this.phoneNumberInputSheet.isPhoneNumberValid()) {
                    KeyboardUtils.dismissSoftKeyboard((Activity) PhoneVerificationFragment.this.getActivity());
                }
            }

            public void afterPhoneNumberTextChanged(String phoneNumberText, String phoneNumberTextDisplay) {
            }
        });
        this.phoneNumberInputSheet.setCountryCodeSelectionButtonClickListener(PhoneVerificationFragment$$Lambda$1.lambdaFactory$(this));
        this.codeInputSheet.addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                PhoneVerificationFragment.this.confirmationCode = s.toString();
                PhoneVerificationFragment.this.nextButton.setEnabled(PhoneVerificationFragment.this.confirmationCode.length() == 4);
            }
        });
        this.phoneNumberInputSheet.onNewCountryCodeSelected(this.countryCodeItem);
        this.phoneNumberInputSheet.setPhoneNumberEditText(this.airPhone);
        this.codeInputSheet.setText(this.confirmationCode);
        if (isResendOnCooldown()) {
            startCountdown();
        }
        if (this.phoneNumberInputSheet.isPhoneNumberValid() && !isResendOnCooldown()) {
            z = true;
        }
        enableSendButtion(z);
        if (savedInstanceState == null && getArguments().getBoolean(ARG_PLAY_ANIM)) {
            playEnterAnimation();
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(PhoneVerificationFragment phoneVerificationFragment, View v) {
        FiveAxiomAnalytics.trackClick("phone_country_code");
        CountryCodeSelectionFragment fragment = CountryCodeSelectionFragment.newInstance(CountryCodeSelectionStyle.WHITE);
        fragment.setOnCountryCodeSelectedListener(PhoneVerificationFragment$$Lambda$9.lambdaFactory$(phoneVerificationFragment));
        phoneVerificationFragment.showModal(fragment, C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    static /* synthetic */ void lambda$null$0(PhoneVerificationFragment phoneVerificationFragment, CountryCodeItem item) {
        phoneVerificationFragment.countryCodeItem = item;
        phoneVerificationFragment.phoneNumberInputSheet.onNewCountryCodeSelected(phoneVerificationFragment.countryCodeItem);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.listeningToSMS) {
            startListeningToSMS();
        }
    }

    public void onDestroyView() {
        if (this.countdownSubscription != null) {
            this.countdownSubscription.unsubscribe();
        }
        if (this.smsSubscription != null) {
            this.smsSubscription.unsubscribe();
        }
        if (this.permissionSubscription != null) {
            this.permissionSubscription.unsubscribe();
        }
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        super.onDestroyView();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ((SMSMonitor) this.smsMonitor.get()).onRequestPermissionsResult(requestCode, permissions2, grantResults, this);
    }

    private void startListeningToSMS() {
        this.smsSubscription = ((SMSMonitor) this.smsMonitor.get()).listen(Type.Verification_Code, this).subscribe(PhoneVerificationFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$startListeningToSMS$2(PhoneVerificationFragment phoneVerificationFragment, String code) {
        phoneVerificationFragment.codeInputSheet.setText(code);
        phoneVerificationFragment.nextButton.performClick();
    }

    /* access modifiers changed from: protected */
    public PhoneVerificationPresenter createPresenter(FiveAxiomRepository repo) {
        return new PhoneVerificationPresenter(repo.getPhoneModel(), this);
    }

    public void showLoading(boolean show) {
        this.nextButton.setState(show ? AirButton.State.Loading : AirButton.State.Normal);
    }

    public void showRequestConfirmationCodeResult(boolean success) {
        if (success) {
            this.lastRequestTime = System.currentTimeMillis();
            startCountdown();
            return;
        }
        showErrorTip(C0880R.string.china_sms_send_fail_tip);
        enableSendButtion(true);
    }

    public void showVerifyConfirmationCodeResult(boolean success) {
        if (success) {
            this.nextButton.setState(AirButton.State.Success);
            ((PhoneVerificationPresenter) this.presenter).finish();
            return;
        }
        showErrorTip(C0880R.string.china_code_verify_fail_tip);
    }

    @OnClick
    public void onSendCodeClicked() {
        enableSendButtion(false);
        FiveAxiomAnalytics.trackClick("phone_send");
        if (this.permissionSubscription != null || !ChinaUtils.enableVerificationCodeAutofill()) {
            requestConfirmationCode();
        } else {
            this.permissionSubscription = ((SMSMonitor) this.smsMonitor.get()).permission(this).subscribe(PhoneVerificationFragment$$Lambda$3.lambdaFactory$(this));
        }
    }

    /* access modifiers changed from: private */
    public void requestConfirmationCode() {
        if (this.smsSubscription == null && ChinaUtils.enableVerificationCodeAutofill()) {
            startListeningToSMS();
            this.listeningToSMS = true;
        }
        ((PhoneVerificationPresenter) this.presenter).requestConfirmationCode(this.airPhone);
    }

    @OnClick
    public void onNextClicked() {
        ((PhoneVerificationPresenter) this.presenter).verifyConfirmationCode(this.confirmationCode);
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
        FiveAxiomAnalytics.trackClick("phone_next");
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        return super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void enableSendButtion(boolean enable) {
        this.sendCodeBtn.setEnabled(enable);
        this.sendCodeBtn.setAlpha(enable ? 1.0f : 0.5f);
    }

    private void startCountdown() {
        if (this.countdownSubscription != null) {
            this.countdownSubscription.unsubscribe();
        }
        this.countdownSubscription = Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(PhoneVerificationFragment$$Lambda$4.lambdaFactory$(this)).takeWhile(PhoneVerificationFragment$$Lambda$5.lambdaFactory$()).subscribe(PhoneVerificationFragment$$Lambda$6.lambdaFactory$(this), PhoneVerificationFragment$$Lambda$7.lambdaFactory$(), PhoneVerificationFragment$$Lambda$8.lambdaFactory$(this));
    }

    static /* synthetic */ Boolean lambda$startCountdown$5(Integer aInt) {
        return Boolean.valueOf(aInt.intValue() > 0);
    }

    static /* synthetic */ void lambda$startCountdown$7(Throwable e) {
    }

    static /* synthetic */ void lambda$startCountdown$8(PhoneVerificationFragment phoneVerificationFragment) {
        phoneVerificationFragment.sendCodeBtn.setText(C0880R.string.phone_code_verification_resend);
        phoneVerificationFragment.enableSendButtion(true);
    }

    private void showErrorTip(int errorMsg) {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        this.snackbar = new SnackbarWrapper().view(getView()).title(getString(errorMsg), true).duration(0).buildAndShow();
    }

    /* access modifiers changed from: private */
    public boolean isResendOnCooldown() {
        long curTime = System.currentTimeMillis();
        return this.lastRequestTime < curTime && curTime < this.lastRequestTime + 60000;
    }

    private void playEnterAnimation() {
        this.phoneNumberInputSheet.setTranslationX((float) ViewLibUtils.getScreenWidth(getContext()));
        this.phoneNumberInputSheet.animate().setDuration(300).translationX(0.0f);
        this.codeInputContainer.setTranslationX((float) ViewLibUtils.getScreenWidth(getContext()));
        this.codeInputContainer.animate().setDuration(300).translationX(0.0f);
    }
}
