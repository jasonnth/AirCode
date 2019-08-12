package com.airbnb.android.registration;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.requests.PhoneNumberVerificationRequest;
import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.registration.RegistrationComponent.Builder;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.sms.SMSMonitor.Type;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import dagger.Lazy;
import icepick.State;
import p032rx.Observer;
import p032rx.Subscription;

public class PhoneNumberRegistrationConfirmationFragment extends BaseRegistrationFragment {
    private static final String ARG_AIRPHONE = "airphone";
    @State
    AirPhone airPhone;
    @BindView
    SheetInputText inputText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    final RequestListener<PhoneNumberVerificationResponse> phoneSMScodeMatchingRequestListener = new C0699RL().onResponse(PhoneNumberRegistrationConfirmationFragment$$Lambda$1.lambdaFactory$(this)).onError(PhoneNumberRegistrationConfirmationFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    View rootView;
    @BindView
    SheetMarquee sheetMarquee;
    @State
    SheetState sheetState;
    Lazy<SMSMonitor> smsMonitor;
    private Subscription smsSubscription;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            PhoneNumberRegistrationConfirmationFragment.this.inputText.setState(SheetInputText.State.Normal);
            if (PhoneNumberRegistrationConfirmationFragment.this.sheetState != SheetState.Normal) {
                PhoneNumberRegistrationConfirmationFragment.this.setSheetState(SheetState.Normal);
            }
            String SMSConfirmationCode = s.toString();
            PhoneNumberRegistrationConfirmationFragment.this.airPhone = AirPhone.withSMSCode(PhoneNumberRegistrationConfirmationFragment.this.airPhone, SMSConfirmationCode);
            PhoneNumberRegistrationConfirmationFragment.this.nextButton.setEnabled(SMSConfirmationCode.length() == 4);
        }
    };

    static /* synthetic */ void lambda$new$1(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment, PhoneNumberVerificationResponse data) {
        phoneNumberRegistrationConfirmationFragment.nextButton.setState(AirButton.State.Success);
        phoneNumberRegistrationConfirmationFragment.handler.postDelayed(PhoneNumberRegistrationConfirmationFragment$$Lambda$6.lambdaFactory$(phoneNumberRegistrationConfirmationFragment), 700);
    }

    static /* synthetic */ void lambda$new$2(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment, AirRequestNetworkException e) {
        phoneNumberRegistrationConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        phoneNumberRegistrationConfirmationFragment.setSheetState(SheetState.Error);
        NetworkUtil.tryShowErrorWithSnackbar(phoneNumberRegistrationConfirmationFragment.getView(), e);
    }

    public static PhoneNumberRegistrationConfirmationFragment newInstance(AirPhone airPhone2, AccountRegistrationData data) {
        return (PhoneNumberRegistrationConfirmationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PhoneNumberRegistrationConfirmationFragment()).putParcelable(ARG_AIRPHONE, airPhone2)).putParcelable(BaseRegistrationFragment.ARG_ACCOUNT_REG_DATA, data)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((RegistrationBindings) CoreApplication.instance().componentProvider()).registrationComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_phone_sms_confirmation, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.airPhone = (AirPhone) getArguments().getParcelable(ARG_AIRPHONE);
        }
        this.sheetMarquee.setSubtitle(String.format(getString(C1562R.string.verifications_phone_instructions), new Object[]{this.airPhone.phoneDisplayText()}));
        this.inputText.addTextChangedListener(this.textWatcher);
        setHasOptionsMenu(true);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ChinaUtils.enableVerificationCodeAutofill()) {
            this.smsSubscription = ((SMSMonitor) this.smsMonitor.get()).listen(Type.Verification_Code, this).subscribe(PhoneNumberRegistrationConfirmationFragment$$Lambda$5.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$onViewCreated$3(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment, String code) {
        phoneNumberRegistrationConfirmationFragment.inputText.setText(code);
        phoneNumberRegistrationConfirmationFragment.nextButton.performClick();
    }

    public void onDestroyView() {
        if (this.smsSubscription != null) {
            this.smsSubscription.unsubscribe();
        }
        this.inputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationConfirmPhone;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.NEXT_BUTTON, this.dataPassedIn.getRegistrationServiceForAnalytics(), getNavigationTrackingTag());
        PhoneNumberVerificationRequest.forPhoneNumberSMSCodeMatching(AirPhone.withSMSCode(this.airPhone, this.inputText.getText().toString())).withListener((Observer) this.phoneSMScodeMatchingRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void setSheetState(SheetState sheetState2) {
        this.sheetState = sheetState2;
        this.rootView.setBackgroundColor(ContextCompat.getColor(getContext(), sheetState2.backgroundColor));
    }
}
