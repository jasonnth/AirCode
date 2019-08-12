package com.airbnb.android.identity;

import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.requests.ConfirmEmailRequest;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class AccountVerificationEmailConfirmationFragment extends BaseAccountVerificationFragment {
    private static final String ARG_EMAIL = "email";
    private static final int EMAIL_POLLING_DELAY_MILLIS = 5000;
    @State
    String email = "";
    private final Handler emailPollerHandler = new Handler();
    final RequestListener<AccountVerificationsResponse> emailPollingRequestListener = new C0699RL().onResponse(AccountVerificationEmailConfirmationFragment$$Lambda$3.lambdaFactory$(this)).onError(AccountVerificationEmailConfirmationFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @BindView
    AirButton primaryButton;
    final RequestListener<BaseResponse> resendEmailRequestListener = new C0699RL().onResponse(AccountVerificationEmailConfirmationFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationEmailConfirmationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton secondaryButton;
    @BindView
    AirButton secondaryButtonWhite;
    @BindView
    SheetMarquee sheetMarquee;

    static /* synthetic */ void lambda$new$0(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment, BaseResponse response) {
        new SnackbarWrapper().view(accountVerificationEmailConfirmationFragment.sheetMarquee).body(String.format(accountVerificationEmailConfirmationFragment.getString(C6533R.string.account_verification_email_resend), new Object[]{accountVerificationEmailConfirmationFragment.email})).duration(-1).buildAndShow();
        accountVerificationEmailConfirmationFragment.secondaryButton.setEnabled(true);
        accountVerificationEmailConfirmationFragment.secondaryButtonWhite.setEnabled(true);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment, AirRequestNetworkException e) {
        NetworkUtil.toastNetworkError(accountVerificationEmailConfirmationFragment.getContext(), (NetworkException) e);
        accountVerificationEmailConfirmationFragment.secondaryButton.setEnabled(true);
        accountVerificationEmailConfirmationFragment.secondaryButtonWhite.setEnabled(true);
    }

    static /* synthetic */ void lambda$new$2(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment, AccountVerificationsResponse response) {
        accountVerificationEmailConfirmationFragment.checkEmailPollingResponse(response);
        accountVerificationEmailConfirmationFragment.delayStartPolling();
    }

    public static AccountVerificationEmailConfirmationFragment newInstanceWithEmail(String email2, VerificationFlow verificationFlow) {
        return (AccountVerificationEmailConfirmationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationEmailConfirmationFragment()).putString("email", email2)).putSerializable("arg_verification_flow", verificationFlow)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_email_confirmation, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        this.email = getArguments().getString("email");
        this.sheetMarquee.setSubtitle(String.format(getString(C6533R.string.verifications_email_tap_link_with_email), new Object[]{this.email}));
        this.secondaryButton.setText(C6533R.string.verified_id_resend_email_button);
        this.secondaryButtonWhite.setText(C6533R.string.verified_id_resend_email_button);
        this.primaryButton.setState(AirButton.State.Loading);
        this.nextButton.setState(AirButton.State.Loading);
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.sheetMarquee);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.secondaryButton, style.secondaryButtonVisible);
        ViewUtils.setVisibleIf((View) this.secondaryButtonWhite, style.secondaryButtonWhiteVisible);
        ViewUtils.setVisibleIf((View) this.primaryButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.nextButton, style.whiteNextButtonVisible);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onEmailResendClicked() {
        ConfirmEmailRequest request;
        this.secondaryButton.setEnabled(false);
        this.secondaryButtonWhite.setEnabled(false);
        if (getVerificationFlow() == VerificationFlow.CohostInvitation) {
            request = ConfirmEmailRequest.newInstanceForCohosting(this.email, this.controller.getHost().getFirstName());
        } else {
            request = ConfirmEmailRequest.newInstance(this.email);
        }
        request.withListener(this.resendEmailRequestListener).execute(this.requestManager);
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "resend_email_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_check, Element.button_resend_email);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onEmailResendWhiteClicked() {
        onEmailResendClicked();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C6533R.C6536menu.change_email, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6533R.C6535id.menu_change_email) {
            return super.onOptionsItemSelected(item);
        }
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_check, Element.button_change_email);
        this.controller.showFragmentForStepPerformingPopBackStackAnimation(AccountVerificationEmailInputFragment.newInstanceWithEmail(this.email, getVerificationFlow()), AccountVerificationStep.Email);
        return true;
    }

    public void onResume() {
        super.onResume();
        startPolling();
    }

    public void onPause() {
        this.emailPollerHandler.removeCallbacksAndMessages(null);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void delayStartPolling() {
        this.emailPollerHandler.postDelayed(AccountVerificationEmailConfirmationFragment$$Lambda$5.lambdaFactory$(this), 5000);
    }

    /* access modifiers changed from: private */
    public void startPolling() {
        AccountVerificationsRequest.forFlow(getVerificationFlow()).withListener((Observer) this.emailPollingRequestListener).execute(this.requestManager);
    }

    private void checkEmailPollingResponse(AccountVerificationsResponse response) {
        if (response.getAccountVerification("email").isComplete()) {
            this.controller.getIdentityJitneyLogger().logApproveReject(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.EMAIL, Page.email_check, null, true);
            AccountVerificationAnalytics.trackRequestSuccess(getNavigationTrackingTag(), "confirm_email_request");
            onEmailVerificationCompleted();
        }
    }

    private void onEmailVerificationCompleted() {
        this.primaryButton.setState(AirButton.State.Success);
        this.controller.onStepFinished(AccountVerificationStep.Email, false);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationCheckEmail;
    }
}
