package com.airbnb.android.registration;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.content.ContextCompat;
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
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountrySelectedListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.requests.PhoneNumberVerificationRequest;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.android.registration.RegistrationComponent.Builder;
import com.airbnb.android.registration.requests.AccountCreationRequest;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import dagger.Lazy;
import icepick.State;
import p032rx.Observer;
import p032rx.Subscription;

public class PhoneNumberRegistrationFragment extends BaseRegistrationFragment implements CountrySelectedListener, PhoneNumberInputViewDelegate {
    @State
    AirPhone airPhone;
    @State
    CountryCodeItem countryCodeItem;
    private final OnClickListener countryCodeSelectionButtonClickListener = PhoneNumberRegistrationFragment$$Lambda$5.lambdaFactory$(this);
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    private Subscription permissionSubscription;
    final RequestListener<AccountResponse> phoneNumberExistValidationRequestListener = new C0699RL().onResponse(PhoneNumberRegistrationFragment$$Lambda$3.lambdaFactory$(this)).onError(PhoneNumberRegistrationFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    PhoneNumberInputSheet phoneNumberInputSheet;
    final RequestListener<PhoneNumberVerificationResponse> phoneNumberVerificationRequestListener = new C0699RL().onResponse(PhoneNumberRegistrationFragment$$Lambda$1.lambdaFactory$(this)).onError(PhoneNumberRegistrationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    View rootView;
    @State
    SheetState sheetState;
    Lazy<SMSMonitor> smsMonitor;
    private Snackbar snackbar;

    static /* synthetic */ void lambda$new$0(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment, PhoneNumberVerificationResponse data) {
        phoneNumberRegistrationFragment.nextButton.setState(AirButton.State.Success);
        phoneNumberRegistrationFragment.handler.postDelayed(PhoneNumberRegistrationFragment$$Lambda$8.lambdaFactory$(phoneNumberRegistrationFragment), 700);
    }

    static /* synthetic */ void lambda$new$1(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment, AirRequestNetworkException error) {
        phoneNumberRegistrationFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(phoneNumberRegistrationFragment.getView(), error);
    }

    static /* synthetic */ void lambda$new$4(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment, AccountResponse data) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.VERIFY_PHONE_RESPONSE, "phone", phoneNumberRegistrationFragment.getNavigationTrackingTag(), phoneNumberRegistrationFragment.getContext());
        if (data.account.isAccountExists()) {
            phoneNumberRegistrationFragment.setSheetState(SheetState.Error);
            phoneNumberRegistrationFragment.nextButton.setState(AirButton.State.Normal);
            phoneNumberRegistrationFragment.snackbar = new SnackbarWrapper().view(phoneNumberRegistrationFragment.getView()).body(phoneNumberRegistrationFragment.getString(C1562R.string.registration_phone_in_use_desc)).action(phoneNumberRegistrationFragment.getString(C1562R.string.sign_in), PhoneNumberRegistrationFragment$$Lambda$6.lambdaFactory$(phoneNumberRegistrationFragment)).buildAndShow();
        } else if (phoneNumberRegistrationFragment.permissionSubscription != null || !ChinaUtils.enableVerificationCodeAutofill()) {
            phoneNumberRegistrationFragment.requestConfirmationCode();
        } else {
            phoneNumberRegistrationFragment.permissionSubscription = ((SMSMonitor) phoneNumberRegistrationFragment.smsMonitor.get()).permission(phoneNumberRegistrationFragment).subscribe(PhoneNumberRegistrationFragment$$Lambda$7.lambdaFactory$(phoneNumberRegistrationFragment));
        }
    }

    static /* synthetic */ void lambda$new$5(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment, AirRequestNetworkException error) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.VERIFY_PHONE_RESPONSE, "phone", phoneNumberRegistrationFragment.getNavigationTrackingTag(), (NetworkException) error);
        phoneNumberRegistrationFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(phoneNumberRegistrationFragment.getView(), error);
    }

    static /* synthetic */ void lambda$new$6(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment, View v) {
        CountryCodeSelectionFragment fragment = CountryCodeSelectionFragment.newInstance();
        fragment.setOnCountryCodeSelectedListener(phoneNumberRegistrationFragment);
        ((AccountIdentifierRegistrationFragment) phoneNumberRegistrationFragment.getParentFragment()).showChildFragment(fragment, true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((RegistrationBindings) CoreApplication.instance().componentProvider()).registrationComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_phone_registration, container, false);
        bindViews(view);
        this.phoneNumberInputSheet.initPhoneNumberInputView(this);
        this.phoneNumberInputSheet.setCountryCodeSelectionButtonClickListener(this.countryCodeSelectionButtonClickListener);
        this.phoneNumberInputSheet.onNewCountryCodeSelected(this.countryCodeItem);
        if (savedInstanceState == null) {
            this.phoneNumberInputSheet.setPhoneNumberEditText(this.dataPassedIn.airPhone());
        }
        return view;
    }

    public void onDestroyView() {
        if (this.permissionSubscription != null) {
            this.permissionSubscription.unsubscribe();
        }
        super.onDestroyView();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ((SMSMonitor) this.smsMonitor.get()).onRequestPermissionsResult(requestCode, permissions2, grantResults, this);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationPhone;
    }

    /* access modifiers changed from: private */
    public void requestConfirmationCode() {
        PhoneNumberVerificationRequest.forPhoneNumberVerification(this.airPhone.formattedPhone()).withListener((Observer) this.phoneNumberVerificationRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.NEXT_BUTTON, "phone", getNavigationTrackingTag());
        AccountCreationRequest.forValidatingPhone(this.airPhone.formattedPhone()).withListener((Observer) this.phoneNumberExistValidationRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void swapToEmail() {
        RegistrationAnalytics.trackEmailPhoneToggleEvent(getNavigationTrackingTag(), "email");
        ((AccountIdentifierRegistrationFragment) getParentFragment()).swapToEmail();
    }

    public void afterPhoneNumberTextChanged(AirPhone airPhone2) {
        this.nextButton.setEnabled(this.phoneNumberInputSheet.isPhoneNumberValid());
        this.airPhone = airPhone2;
        dismissSnackbar();
        if (this.sheetState != SheetState.Normal) {
            setSheetState(SheetState.Normal);
        }
    }

    private void dismissSnackbar() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
    }

    public void afterPhoneNumberTextChanged(String formattedPhoneNumber, String displayPhoneNumber) {
    }

    public void onCountrySelected(CountryCodeItem item) {
        this.countryCodeItem = item;
        this.airPhone = AirPhone.withCountryCodeItem(this.airPhone, this.countryCodeItem);
    }

    /* access modifiers changed from: private */
    public void goToConfirmation() {
        ((AccountIdentifierRegistrationFragment) getParentFragment()).showChildFragment(PhoneNumberRegistrationConfirmationFragment.newInstance(this.airPhone, this.dataPassedIn.toBuilder().accountSource(AccountSource.Phone).build()), true);
    }

    private void setSheetState(SheetState sheetState2) {
        this.sheetState = sheetState2;
        this.rootView.setBackgroundColor(ContextCompat.getColor(getContext(), sheetState2.backgroundColor));
    }

    public void onPause() {
        dismissSnackbar();
        super.onPause();
    }
}
