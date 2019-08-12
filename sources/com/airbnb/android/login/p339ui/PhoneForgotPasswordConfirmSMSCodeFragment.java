package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.LoginBindings;
import com.airbnb.android.login.LoginComponent.Builder;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.requests.ForgotPasswordRequest.PhoneForgotPasswordStep;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.sms.SMSMonitor.Type;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import dagger.Lazy;
import icepick.State;
import p032rx.Observer;
import p032rx.Subscription;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment */
public class PhoneForgotPasswordConfirmSMSCodeFragment extends AirFragment {
    private static final String ARG_AIRPHONE = "airphone";
    @State
    String SMSConfirmationCode;
    @State
    AirPhone airPhone;
    private final Handler handler = new Handler();
    @BindView
    SheetInputText inputText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    final RequestListener<ForgotPasswordResponse> requestListener = new C0699RL().onResponse(PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$1.lambdaFactory$(this)).onError(PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SheetMarquee sheetMarquee;
    Lazy<SMSMonitor> smsMonitor;
    private Subscription smsSubscription;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            PhoneForgotPasswordConfirmSMSCodeFragment.this.inputText.setState(SheetInputText.State.Normal);
            PhoneForgotPasswordConfirmSMSCodeFragment.this.SMSConfirmationCode = s.toString();
            PhoneForgotPasswordConfirmSMSCodeFragment.this.nextButton.setEnabled(PhoneForgotPasswordConfirmSMSCodeFragment.this.SMSConfirmationCode.length() == 4);
        }
    };

    public static PhoneForgotPasswordConfirmSMSCodeFragment newInstance(AirPhone airPhone2) {
        return (PhoneForgotPasswordConfirmSMSCodeFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PhoneForgotPasswordConfirmSMSCodeFragment()).putParcelable(ARG_AIRPHONE, airPhone2)).build();
    }

    static /* synthetic */ void lambda$new$1(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment, ForgotPasswordResponse data) {
        if (data.isSuccess()) {
            phoneForgotPasswordConfirmSMSCodeFragment.nextButton.setState(AirButton.State.Success);
            phoneForgotPasswordConfirmSMSCodeFragment.inputText.setState(SheetInputText.State.Normal);
            phoneForgotPasswordConfirmSMSCodeFragment.handler.postDelayed(PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$4.lambdaFactory$(phoneForgotPasswordConfirmSMSCodeFragment, PhoneResetPasswordFragment.newInstance(phoneForgotPasswordConfirmSMSCodeFragment.airPhone)), 700);
            return;
        }
        phoneForgotPasswordConfirmSMSCodeFragment.nextButton.setState(AirButton.State.Normal);
        phoneForgotPasswordConfirmSMSCodeFragment.inputText.setState(SheetInputText.State.Error);
        NetworkUtil.toastNetworkError(phoneForgotPasswordConfirmSMSCodeFragment.getContext(), data.getMessage());
    }

    static /* synthetic */ void lambda$new$2(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment, AirRequestNetworkException e) {
        phoneForgotPasswordConfirmSMSCodeFragment.inputText.setState(SheetInputText.State.Error);
        phoneForgotPasswordConfirmSMSCodeFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(phoneForgotPasswordConfirmSMSCodeFragment.getContext(), (NetworkException) e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((LoginBindings) CoreApplication.instance().componentProvider()).loginComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_phone_sms_confirmation, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.airPhone = (AirPhone) getArguments().getParcelable(ARG_AIRPHONE);
        }
        this.sheetMarquee.setSubtitle(String.format(getString(C7331R.string.verifications_phone_instructions), new Object[]{this.airPhone.phoneDisplayText()}));
        this.inputText.addTextChangedListener(this.textWatcher);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ChinaUtils.enableVerificationCodeAutofill()) {
            this.smsSubscription = ((SMSMonitor) this.smsMonitor.get()).listen(Type.Verification_Code, this).subscribe(PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$3.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$onViewCreated$3(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment, String code) {
        phoneForgotPasswordConfirmSMSCodeFragment.inputText.setText(code);
        phoneForgotPasswordConfirmSMSCodeFragment.nextButton.performClick();
    }

    public void onDestroyView() {
        if (this.smsSubscription != null) {
            this.smsSubscription.unsubscribe();
        }
        this.inputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        this.inputText.setState(SheetInputText.State.Loading);
        this.airPhone = AirPhone.withSMSCode(this.airPhone, this.SMSConfirmationCode);
        ForgotPasswordRequest.forPhoneForgotPassword(PhoneForgotPasswordStep.VerifySMS, this.airPhone).withListener((Observer) this.requestListener).execute(this.requestManager);
    }

    public void onDetach() {
        this.handler.removeCallbacksAndMessages(null);
        super.onDetach();
    }
}
