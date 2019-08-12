package com.airbnb.android.identity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.requests.CallPhoneVerificationRequest;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.identity.IdentityComponent.Builder;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.sms.SMSMonitor.Type;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetBuilder;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import dagger.Lazy;
import icepick.State;
import p032rx.Subscription;

public class AccountVerificationPhoneNumberConfirmationFragment extends BaseAccountVerificationFragment {
    private static final String ARG_AIRPHONE = "airphone";
    @State
    String SMSConfirmationCode;
    @State
    AirPhone airPhone;
    @BindView
    AirButton bookingNextButton;
    private BottomSheetDialog bottomSheetDialog;
    @BindView
    SheetInputText inputText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    final RequestListener<Object> requestCallListener = new C0699RL().onResponse(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$5.lambdaFactory$(this)).onError(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<Object> requestConfirmationCodeListener = new C0699RL().onResponse(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$3.lambdaFactory$(this)).onError(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    SheetMarquee sheetMarquee;
    Lazy<SMSMonitor> smsMonitor;
    private Subscription smsSubscription;
    private Snackbar snackbar;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            if (IdentityStyle.getStyle(AccountVerificationPhoneNumberConfirmationFragment.this.getVerificationFlow()).hasSheetStates) {
                AccountVerificationPhoneNumberConfirmationFragment.this.inputText.setState(SheetInputText.State.Normal);
                AccountVerificationPhoneNumberConfirmationFragment.this.setSheetState(SheetState.Normal);
            }
            AccountVerificationPhoneNumberConfirmationFragment.this.nextButton.setState(AirButton.State.Normal);
            AccountVerificationPhoneNumberConfirmationFragment.this.bookingNextButton.setState(AirButton.State.Normal);
            AccountVerificationPhoneNumberConfirmationFragment.this.SMSConfirmationCode = s.toString();
            boolean isSMSCodeLengthValid = AccountVerificationPhoneNumberConfirmationFragment.this.SMSConfirmationCode.length() == 4;
            AccountVerificationPhoneNumberConfirmationFragment.this.nextButton.setEnabled(isSMSCodeLengthValid);
            AccountVerificationPhoneNumberConfirmationFragment.this.bookingNextButton.setEnabled(isSMSCodeLengthValid);
            if (isSMSCodeLengthValid) {
                AccountVerificationPhoneNumberConfirmationFragment.this.onBookingNext();
            }
        }
    };
    final RequestListener<Object> veirfyConfirmationCodeListener = new C0699RL().onResponse(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, Object response) {
        AccountVerificationAnalytics.trackRequestSuccess(accountVerificationPhoneNumberConfirmationFragment.getNavigationTrackingTag(), "confirm_code_request");
        accountVerificationPhoneNumberConfirmationFragment.controller.getIdentityJitneyLogger().logApproveReject(accountVerificationPhoneNumberConfirmationFragment.getVerificationFlow(), accountVerificationPhoneNumberConfirmationFragment.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry, null, true);
        accountVerificationPhoneNumberConfirmationFragment.inputText.setState(SheetInputText.State.Valid);
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Success);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.controller.onStepFinished(AccountVerificationStep.Phone, false);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, AirRequestNetworkException e) {
        accountVerificationPhoneNumberConfirmationFragment.controller.getIdentityJitneyLogger().logApproveReject(accountVerificationPhoneNumberConfirmationFragment.getVerificationFlow(), accountVerificationPhoneNumberConfirmationFragment.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_entry, null, false);
        AccountVerificationAnalytics.trackRequestFailure(accountVerificationPhoneNumberConfirmationFragment.getNavigationTrackingTag(), "confirm_code_request");
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
        if (IdentityStyle.getStyle(accountVerificationPhoneNumberConfirmationFragment.getVerificationFlow()).hasSheetStates) {
            accountVerificationPhoneNumberConfirmationFragment.inputText.setState(SheetInputText.State.Error);
            accountVerificationPhoneNumberConfirmationFragment.setSheetState(SheetState.Error);
        }
        accountVerificationPhoneNumberConfirmationFragment.snackbar = new SnackbarWrapper().view(accountVerificationPhoneNumberConfirmationFragment.getView()).body(accountVerificationPhoneNumberConfirmationFragment.getString(C6533R.string.sms_verification_error_invalid_code)).duration(0).buildAndShow();
    }

    static /* synthetic */ void lambda$new$2(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, Object response) {
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.snackbar = new SnackbarWrapper().view(accountVerificationPhoneNumberConfirmationFragment.getView()).body(accountVerificationPhoneNumberConfirmationFragment.getString(C6533R.string.sms_code_just_sent_again, accountVerificationPhoneNumberConfirmationFragment.airPhone.phoneDisplayText())).duration(-1).buildAndShow();
    }

    static /* synthetic */ void lambda$new$3(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, AirRequestNetworkException e) {
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.snackbar = new SnackbarWrapper().view(accountVerificationPhoneNumberConfirmationFragment.getView()).body(accountVerificationPhoneNumberConfirmationFragment.getString(C6533R.string.sms_number_error_please_try_again)).duration(-1).buildAndShow();
    }

    static /* synthetic */ void lambda$new$4(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, Object response) {
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
    }

    static /* synthetic */ void lambda$new$5(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, AirRequestNetworkException e) {
        accountVerificationPhoneNumberConfirmationFragment.nextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.bookingNextButton.setState(AirButton.State.Normal);
        accountVerificationPhoneNumberConfirmationFragment.snackbar = new SnackbarWrapper().view(accountVerificationPhoneNumberConfirmationFragment.getView()).body(accountVerificationPhoneNumberConfirmationFragment.getString(C6533R.string.verifications_phone_call_error)).duration(-1).buildAndShow();
    }

    public static AccountVerificationPhoneNumberConfirmationFragment newInstance(AirPhone airPhone2, VerificationFlow verificationFlow) {
        return (AccountVerificationPhoneNumberConfirmationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationPhoneNumberConfirmationFragment()).putParcelable(ARG_AIRPHONE, airPhone2)).putSerializable("arg_verification_flow", verificationFlow)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((IdentityBindings) CoreApplication.instance().componentProvider()).identityComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_phone_number_confirmation, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.airPhone = (AirPhone) getArguments().getParcelable(ARG_AIRPHONE);
        }
        this.sheetMarquee.setSubtitle(String.format(getString(C6533R.string.verifications_phone_instructions), new Object[]{this.airPhone.phoneDisplayText()}));
        this.inputText.addTextChangedListener(this.textWatcher);
        setHasOptionsMenu(true);
        setIdentityStyle(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ChinaUtils.enableVerificationCodeAutofill()) {
            this.smsSubscription = ((SMSMonitor) this.smsMonitor.get()).listen(Type.Verification_Code, this).subscribe(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$7.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$onViewCreated$6(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, String code) {
        accountVerificationPhoneNumberConfirmationFragment.inputText.setText(code);
        accountVerificationPhoneNumberConfirmationFragment.nextButton.performClick();
    }

    public void onDestroyView() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        this.inputText.removeTextChangedListener(this.textWatcher);
        if (this.smsSubscription != null) {
            this.smsSubscription.unsubscribe();
        }
        super.onDestroyView();
    }

    private void setIdentityStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        style.marqueeStyle.setStyle(this.sheetMarquee);
        style.inputStyle.setStyle(this.inputText);
        ViewUtils.setVisibleIf((View) this.nextButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.bookingNextButton, style.whiteNextButtonVisible);
    }

    public void onResume() {
        super.onResume();
        this.inputText.showSoftKeyboard();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "confirm_code_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_confirmation, Element.confirmation_code_text);
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        this.bookingNextButton.setState(AirButton.State.Loading);
        UpdatePhoneNumberRequest.verifyPhoneNumber(this.SMSConfirmationCode).withListener(this.veirfyConfirmationCodeListener).execute(this.requestManager);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNext() {
        onNext();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C6533R.C6536menu.help, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6533R.C6535id.menu_help) {
            return super.onOptionsItemSelected(item);
        }
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_confirmation, Element.button_help);
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.bottomSheetDialog = createDialog(C6533R.C6536menu.menu_bottom_sheet_phone_number_verification);
        this.bottomSheetDialog.show();
        return true;
    }

    private BottomSheetDialog createDialog(int menuRes) {
        return new BottomSheetBuilder(getContext(), menuRes).setItemClickListener(AccountVerificationPhoneNumberConfirmationFragment$$Lambda$8.lambdaFactory$(this)).build();
    }

    static /* synthetic */ void lambda$createDialog$7(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment, BottomSheetMenuItem item) {
        accountVerificationPhoneNumberConfirmationFragment.handleItemClick(item);
        accountVerificationPhoneNumberConfirmationFragment.bottomSheetDialog.dismiss();
    }

    private void handleItemClick(BottomSheetMenuItem item) {
        int itemId = item.getId();
        if (itemId == C6533R.C6535id.bottom_sheet_send_code_again) {
            this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_confirmation, Element.option_send_code_again);
            this.nextButton.setState(AirButton.State.Loading);
            this.bookingNextButton.setState(AirButton.State.Loading);
            UpdatePhoneNumberRequest.requestConfirmationCode(this.airPhone.formattedPhone()).withListener(this.requestConfirmationCodeListener).execute(this.requestManager);
        } else if (itemId == C6533R.C6535id.bottom_sheet_change_my_number) {
            this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_confirmation, Element.option_change_number);
            getFragmentManager().popBackStack();
        } else if (itemId == C6533R.C6535id.bottom_sheet_call_me_instead) {
            this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHONE_NUMBER, Page.phone_confirmation, Element.option_call_instead);
            this.nextButton.setState(AirButton.State.Loading);
            this.bookingNextButton.setState(AirButton.State.Loading);
            new CallPhoneVerificationRequest(this.airPhone.formattedPhone()).withListener(this.requestCallListener).execute(this.requestManager);
        }
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationConfirmPhoneCode;
    }
}
