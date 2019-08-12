package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.requests.ConfirmEmailRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButtonExpandable;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;

public class AccountVerificationEmailInputFragment extends BaseAccountVerificationFragment {
    private static final String ARG_EMAIL = "email";
    private static final int RC_PROCEED_CONFIRM_EMAIL = 489;
    @BindView
    AirButton bookingNextButton;
    final RequestListener<BaseResponse> confirmEmailRequestListener = new C0699RL().onResponse(AccountVerificationEmailInputFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationEmailInputFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    String email = "";
    @BindView
    SheetMarquee emailConfirmationSheetMarquee;
    @BindView
    SheetInputText emailInputField;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButtonExpandable nextButton;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            boolean shouldEnable = TextUtil.isValidEmail(s.toString());
            String buttonText = shouldEnable ? AccountVerificationEmailInputFragment.this.getString(C6533R.string.verifications_email_send_me_email) : "";
            AccountVerificationEmailInputFragment.this.nextButton.setEnabled(shouldEnable);
            AccountVerificationEmailInputFragment.this.nextButton.setButtonText(buttonText);
            AccountVerificationEmailInputFragment.this.email = s.toString();
        }
    };

    public static AccountVerificationEmailInputFragment newInstanceWithEmail(String email2, VerificationFlow verificationFlow) {
        return (AccountVerificationEmailInputFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationEmailInputFragment()).putString("email", email2)).putSerializable("arg_verification_flow", verificationFlow)).build();
    }

    static /* synthetic */ void lambda$new$0(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment, BaseResponse response) {
        accountVerificationEmailInputFragment.nextButton.setState(AirButton.State.Success);
        accountVerificationEmailInputFragment.controller.showFragmentForStep(AccountVerificationEmailConfirmationFragment.newInstanceWithEmail(accountVerificationEmailInputFragment.email, accountVerificationEmailInputFragment.getVerificationFlow()), AccountVerificationStep.Email);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationEmailInputFragment accountVerificationEmailInputFragment, AirRequestNetworkException e) {
        accountVerificationEmailInputFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(accountVerificationEmailInputFragment.getContext(), (NetworkException) e);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_email_input, container, false);
        bindViews(view);
        Bundle data = getArguments();
        if (data != null) {
            this.email = data.getString("email");
        }
        if (TextUtils.isEmpty(this.email) && this.mAccountManager.getCurrentUser() != null) {
            this.email = this.mAccountManager.getCurrentUser().getEmailAddress();
        }
        this.emailConfirmationSheetMarquee.setSubtitle(getVerificationFlow().getText().getEmailSubtitle());
        this.emailInputField.addTextChangedListener(this.textWatcher);
        this.emailInputField.setText(this.email);
        setStyle(view);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_entry);
        return view;
    }

    private void setStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.emailConfirmationSheetMarquee);
        style.inputStyle.setStyle(this.emailInputField);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.nextButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.bookingNextButton, style.whiteNextButtonVisible);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        this.emailInputField.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "confirm_email_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_entry, Element.navigation_button_continue);
        KeyboardUtils.dismissSoftKeyboard((View) this.emailInputField);
        String oldEmail = this.mAccountManager.getCurrentUser().getEmailAddress();
        if (TextUtils.isEmpty(oldEmail) || this.email.equals(oldEmail)) {
            verifyEmail();
        } else {
            ZenDialog.builder().withBodyText(C6533R.string.edit_profile_email_confirm_prompt).withDualButton(C6533R.string.cancel, 0, C6533R.string.continue_text, (int) RC_PROCEED_CONFIRM_EMAIL, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNext() {
        onNext();
    }

    private void verifyEmail() {
        ConfirmEmailRequest request;
        this.nextButton.setState(AirButton.State.Loading);
        this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_entry, null);
        if (getVerificationFlow() == VerificationFlow.CohostInvitation) {
            request = ConfirmEmailRequest.newInstanceForCohosting(this.email, this.controller.getHost().getFirstName());
        } else {
            request = ConfirmEmailRequest.newInstance(this.email);
        }
        request.withListener(this.confirmEmailRequestListener).execute(this.requestManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case RC_PROCEED_CONFIRM_EMAIL /*489*/:
                    verifyEmail();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationConfirmEmail;
    }
}
