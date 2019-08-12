package com.airbnb.android.identity;

import android.content.Intent;
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
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountrySelectedListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.android.identity.IdentityComponent.Builder;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import dagger.Lazy;
import icepick.State;
import p032rx.Subscription;

public class AccountVerificationPhoneNumberInputFragment extends BaseAccountVerificationFragment implements CountrySelectedListener, PhoneNumberInputViewDelegate {
    private static final int FB_PHONE_VERIFICATION_RC = 978;
    @State
    AirPhone airPhone;
    @BindView
    AirButton bookingNextButton;
    @State
    CountryCodeItem countryCodeItem;
    private final OnClickListener countryCodeSelectionButtonClickListener = AccountVerificationPhoneNumberInputFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    private Subscription permissionSubscription;
    @BindView
    SheetMarquee phoneConfirmationSheetMarquee;
    @BindView
    PhoneNumberInputSheet phoneNumberInputSheet;
    final RequestListener<Object> requestConfirmationCodeListener = new C0699RL().onResponse(AccountVerificationPhoneNumberInputFragment$$Lambda$2.lambdaFactory$(this)).onError(AccountVerificationPhoneNumberInputFragment$$Lambda$3.lambdaFactory$(this)).build();
    Lazy<SMSMonitor> smsMonitor;
    private Snackbar snackbar;

    static /* synthetic */ void lambda$new$0(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment, View v) {
        CountryCodeSelectionFragment fragment = CountryCodeSelectionFragment.newInstance(IdentityStyle.getStyle(accountVerificationPhoneNumberInputFragment.getVerificationFlow()).countryCodeSelectionStyle);
        fragment.setOnCountryCodeSelectedListener(accountVerificationPhoneNumberInputFragment);
        accountVerificationPhoneNumberInputFragment.showModal(fragment, C6533R.C6535id.content_container, C6533R.C6535id.modal_container, true);
        AccountVerificationAnalytics.trackButtonClick(accountVerificationPhoneNumberInputFragment.getNavigationTrackingTag(), "country_code_button");
        accountVerificationPhoneNumberInputFragment.controller.getIdentityJitneyLogger().logClick(accountVerificationPhoneNumberInputFragment.getVerificationFlow(), accountVerificationPhoneNumberInputFragment.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry, Element.button_change_country);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment, Object response) {
        AccountVerificationAnalytics.trackRequestSuccess(accountVerificationPhoneNumberInputFragment.getNavigationTrackingTag(), "confirm_phone_request");
        accountVerificationPhoneNumberInputFragment.nextButton.setState(AirButton.State.Success);
        accountVerificationPhoneNumberInputFragment.bookingNextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberInputFragment.controller.showFragmentForStep(AccountVerificationPhoneNumberConfirmationFragment.newInstance(accountVerificationPhoneNumberInputFragment.airPhone, accountVerificationPhoneNumberInputFragment.getVerificationFlow()), AccountVerificationStep.Phone);
    }

    static /* synthetic */ void lambda$new$2(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment, AirRequestNetworkException e) {
        AccountVerificationAnalytics.trackRequestFailure(accountVerificationPhoneNumberInputFragment.getNavigationTrackingTag(), "confirm_phone_request");
        accountVerificationPhoneNumberInputFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberInputFragment.bookingNextButton.setState(AirButton.State.Normal);
        if (IdentityStyle.getStyle(accountVerificationPhoneNumberInputFragment.getVerificationFlow()).hasSheetStates) {
            accountVerificationPhoneNumberInputFragment.setSheetState(SheetState.Error);
        }
        accountVerificationPhoneNumberInputFragment.snackbar = new SnackbarWrapper().view(accountVerificationPhoneNumberInputFragment.getView()).body(accountVerificationPhoneNumberInputFragment.getString(C6533R.string.sms_number_error_please_try_again)).duration(0).buildAndShow();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Experiments.enableFBAccountKit()) {
            startActivityForResult(FBAccountKitPhoneNumberVerificationActivity.newIntent(getContext()), FB_PHONE_VERIFICATION_RC);
        } else {
            ((Builder) ((IdentityBindings) CoreApplication.instance().componentProvider()).identityComponentProvider().get()).build().inject(this);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != FB_PHONE_VERIFICATION_RC) {
            return;
        }
        if (resultCode == -1) {
            this.controller.onStepFinished(AccountVerificationStep.Phone, false);
            AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("final_verification_success");
            return;
        }
        AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("final_verification_cancel_or_failed");
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry);
        if (Experiments.enableFBAccountKit()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_phone_number_input, container, false);
        bindViews(view);
        this.phoneConfirmationSheetMarquee.setSubtitle(getVerificationFlow().getText().getPhoneSubtitle());
        this.phoneNumberInputSheet.initPhoneNumberInputView(this);
        this.phoneNumberInputSheet.setCountryCodeSelectionButtonClickListener(this.countryCodeSelectionButtonClickListener);
        this.phoneNumberInputSheet.onNewCountryCodeSelected(this.countryCodeItem);
        setIdentityStyle(view);
        return view;
    }

    private void setIdentityStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.phoneConfirmationSheetMarquee);
        style.phoneInputStyle.setStyle(this.phoneNumberInputSheet);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.nextButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.bookingNextButton, style.whiteNextButtonVisible);
    }

    public void onResume() {
        super.onResume();
        if (this.phoneNumberInputSheet != null) {
            this.phoneNumberInputSheet.showSoftKeyboard();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        if (this.permissionSubscription != null || !ChinaUtils.enableVerificationCodeAutofill()) {
            doSubmit();
        } else {
            this.permissionSubscription = ((SMSMonitor) this.smsMonitor.get()).permission(this).subscribe(AccountVerificationPhoneNumberInputFragment$$Lambda$4.lambdaFactory$(this));
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNext() {
        onNext();
    }

    /* access modifiers changed from: private */
    public void doSubmit() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "confirm_phone_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry, Element.navigation_button_continue);
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        this.bookingNextButton.setState(AirButton.State.Loading);
        this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry, null);
        UpdatePhoneNumberRequest.requestConfirmationCode(this.airPhone.formattedPhone()).withListener(this.requestConfirmationCodeListener).execute(this.requestManager);
    }

    public void afterPhoneNumberTextChanged(AirPhone airPhone2) {
        if (IdentityStyle.getStyle(getVerificationFlow()).hasSheetStates) {
            setSheetState(SheetState.Normal);
        }
        this.airPhone = airPhone2;
        this.nextButton.setEnabled(this.phoneNumberInputSheet.isPhoneNumberValid());
        this.bookingNextButton.setEnabled(this.phoneNumberInputSheet.isPhoneNumberValid());
    }

    public void afterPhoneNumberTextChanged(String formattedPhoneNumber, String displayPhoneNumber) {
    }

    public void onCountrySelected(CountryCodeItem item) {
        this.countryCodeItem = item;
        this.phoneNumberInputSheet.onNewCountryCodeSelected(this.countryCodeItem);
    }

    public void onDestroyView() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        if (this.permissionSubscription != null) {
            this.permissionSubscription.unsubscribe();
        }
        super.onDestroyView();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ((SMSMonitor) this.smsMonitor.get()).onRequestPermissionsResult(requestCode, permissions2, grantResults, this);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationConfirmPhone;
    }
}
