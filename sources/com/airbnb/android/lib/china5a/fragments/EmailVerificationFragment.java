package com.airbnb.android.lib.china5a.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.china5a.BaseVerificationFragment;
import com.airbnb.android.lib.china5a.FiveAxiomRepository;
import com.airbnb.android.lib.china5a.email.EmailVerificationPresenter;
import com.airbnb.android.lib.china5a.email.EmailVerificationView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SheetMarquee.Style;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class EmailVerificationFragment extends BaseVerificationFragment<EmailVerificationPresenter> implements EmailVerificationView {
    private static final String ARG_PLAY_ANIM = "play_anim";
    private static final long ENTER_ANIM_DURATION = 300;
    @State
    String emailAddress;
    @BindView
    SheetInputText emailInputSheet;
    @State
    boolean emailSent;
    @BindView
    AirButton resendButton;
    @BindView
    AirButton sendButton;
    @State
    boolean sendClicked;
    private Snackbar snackbar;
    @BindView
    SheetMarquee titleMarquee;

    public static Fragment newInstance(boolean playAnim) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new EmailVerificationFragment()).putBoolean(ARG_PLAY_ANIM, playAnim)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C0880R.layout.fragment_5a_china_email_verification, container, false);
        bindViews(view);
        Style.WHITE.setStyle(this.titleMarquee);
        SheetInputText.Style.WHITE.setStyle(this.emailInputSheet);
        if (TextUtils.isEmpty(this.emailAddress) && this.mAccountManager.getCurrentUser() != null) {
            this.emailAddress = this.mAccountManager.getCurrentUser().getEmailAddress();
        }
        this.emailInputSheet.setText(this.emailAddress);
        if (!TextUtils.isEmpty(this.emailAddress)) {
            z = true;
        }
        enableSendButtons(z);
        this.emailInputSheet.addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                EmailVerificationFragment.this.emailInputSheet.setState(SheetInputText.State.Normal);
                EmailVerificationFragment.this.emailAddress = s.toString();
                EmailVerificationFragment.this.enableSendButtons(!TextUtils.isEmpty(EmailVerificationFragment.this.emailAddress));
                EmailVerificationFragment.this.dismissSnackbar();
            }
        });
        setupSendButtons();
        if (savedInstanceState == null && getArguments().getBoolean(ARG_PLAY_ANIM)) {
            playEnterAnimation();
        }
        return view;
    }

    public void onDestroyView() {
        dismissSnackbar();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        if (this.emailSent) {
            ((EmailVerificationPresenter) this.presenter).startPollingVerificationStatus();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.emailSent) {
            ((EmailVerificationPresenter) this.presenter).stopPollingVerificationStatus();
        }
    }

    /* access modifiers changed from: protected */
    public EmailVerificationPresenter createPresenter(FiveAxiomRepository repo) {
        return new EmailVerificationPresenter(repo.getEmailModel(), this);
    }

    public void showConfirmEmailResult(boolean success) {
        enableSendButtons(true);
        if (success) {
            showSnackbar(C0880R.string.china_email_verification_success_tip, false);
            this.emailSent = true;
            ((EmailVerificationPresenter) this.presenter).startPollingVerificationStatus();
        } else {
            showSnackbar(C0880R.string.china_email_verification_error_tip, true);
        }
        setupSendButtons();
    }

    public void showVerificationResult(boolean success) {
        if (success) {
            ((EmailVerificationPresenter) this.presenter).finish();
        }
    }

    @OnClick
    public void onSendButtonClicked() {
        this.sendClicked = true;
        checkFormatAndRequestSending();
        FiveAxiomAnalytics.trackClick("mail_send");
    }

    @OnClick
    public void onResendButtonClicked() {
        checkFormatAndRequestSending();
        FiveAxiomAnalytics.trackClick("mail_resend");
    }

    private void checkFormatAndRequestSending() {
        if (TextUtil.isValidEmail(this.emailAddress)) {
            ((EmailVerificationPresenter) this.presenter).confirmEmail(this.emailAddress);
            enableSendButtons(false);
            KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
            return;
        }
        this.emailInputSheet.setState(SheetInputText.State.Error);
        showSnackbar(C0880R.string.registration_error_email_invalid, true);
    }

    private void setupSendButtons() {
        if (this.sendClicked) {
            this.sendButton.setVisibility(8);
            this.resendButton.setVisibility(0);
            return;
        }
        this.sendButton.setVisibility(0);
        this.resendButton.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void enableSendButtons(boolean enable) {
        float f;
        float f2 = 1.0f;
        this.sendButton.setEnabled(enable);
        AirButton airButton = this.sendButton;
        if (enable) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        airButton.setAlpha(f);
        this.resendButton.setEnabled(enable);
        AirButton airButton2 = this.resendButton;
        if (!enable) {
            f2 = 0.5f;
        }
        airButton2.setAlpha(f2);
    }

    private void showSnackbar(int stringId, boolean error) {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        if (error) {
            this.snackbar = new SnackbarWrapper().view(getView()).title(stringId, true).duration(0).buildAndShow(1);
        } else {
            this.snackbar = new SnackbarWrapper().view(getView()).title(stringId, false).action(C0880R.string.okay, EmailVerificationFragment$$Lambda$1.lambdaFactory$(this)).duration(-2).buildAndShow(1);
        }
    }

    static /* synthetic */ void lambda$showSnackbar$0(EmailVerificationFragment emailVerificationFragment, View v) {
        emailVerificationFragment.dismissSnackbar();
        FiveAxiomAnalytics.trackClick("mail_snackbar_ok");
    }

    /* access modifiers changed from: private */
    public void dismissSnackbar() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }

    private void playEnterAnimation() {
        this.emailInputSheet.setTranslationX((float) ViewLibUtils.getScreenWidth(getContext()));
        this.emailInputSheet.animate().setDuration(300).translationX(0.0f);
    }
}
