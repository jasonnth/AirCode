package com.airbnb.android.lib.fragments.verifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Verification.Type;
import com.airbnb.android.core.requests.ConfirmEmailRequest;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.rxgroups.TaggedObserver;
import icepick.State;

public class EmailVerificationFragment extends VerificationsFragment {
    private static final int REQUEST_PROCEED_UPDATE_EMAIL = 489;
    @BindView
    View changeEmailContainer;
    @BindView
    View currentEmailContainer;
    @BindView
    TextView currentEmailTextView;
    @State
    String email;
    private final TextWatcher emailTextWatcher = TextWatcherUtils.create(EmailVerificationFragment$$Lambda$1.lambdaFactory$(this));
    @State
    boolean isCurrentEmailContainerDisplayed = true;
    @BindView
    EditText newEmailEditText;
    final RequestListener<BaseResponse> requestListener = new C0699RL().onResponse(EmailVerificationFragment$$Lambda$2.lambdaFactory$(this)).onError(EmailVerificationFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    TextView sendEmailButton;
    @BindView
    View skipButton;

    public static EmailVerificationFragment newInstance() {
        return new EmailVerificationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verifications_email, container, false);
        bindViews(view);
        if (savedInstanceState != null) {
            displayCurrentContainer(false);
        }
        setUpFields();
        setUpVerificationsListener();
        showOrHideSkipButton(this.skipButton);
        return view;
    }

    public void onStart() {
        super.onStart();
        track("activate_account_email", "not_confirmed", "impression");
    }

    public void onResume() {
        super.onResume();
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.requestListener, ConfirmEmailRequest.class)) {
            this.requestManager.resubscribe((TaggedObserver<T>) this.requestListener, ConfirmEmailRequest.class);
        }
    }

    public void onDestroyView() {
        getVerificationsActivity().deregisterVerificationsListener();
        this.newEmailEditText.removeTextChangedListener(this.emailTextWatcher);
        super.onDestroyView();
    }

    private void setUpFields() {
        this.newEmailEditText.addTextChangedListener(this.emailTextWatcher);
        this.newEmailEditText.setText(this.mAccountManager.getCurrentUser().getEmailAddress());
        this.currentEmailTextView.setText(this.email);
    }

    /* access modifiers changed from: private */
    public void validateEmail(String emailToValidate) {
        if (Patterns.EMAIL_ADDRESS.matcher(emailToValidate).matches()) {
            this.sendEmailButton.setEnabled(true);
        } else {
            this.sendEmailButton.setEnabled(false);
        }
    }

    @OnClick
    public void displayChangeEmailView() {
        track("activate_account_email", "waiting", "click", "change_email_button");
        changeCurrentContainer();
    }

    @OnClick
    public void sendNewConfirmationEmail() {
        if (!this.newEmailEditText.getText().toString().equals(this.mAccountManager.getCurrentUser().getEmailAddress())) {
            track("activate_account_email", "not_confirmed", "email_changed", "send_email_button");
            ZenDialog.builder().withBodyText(C0880R.string.edit_profile_email_confirm_prompt).withDualButton(C0880R.string.cancel, 0, C0880R.string.continue_text, (int) REQUEST_PROCEED_UPDATE_EMAIL, (Fragment) this).create().show(getFragmentManager(), (String) null);
            return;
        }
        verifyEmail();
    }

    private void verifyEmail() {
        track("activate_account_email", "not_confirmed", "click", "send_email_button");
        this.email = this.newEmailEditText.getText().toString();
        this.currentEmailTextView.setText(this.email);
        this.requestManager.executeOrResubscribe(new ConfirmEmailRequest(this.email, (BaseRequestListener<BaseResponse>) this.requestListener), this.requestListener);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void skip() {
        if (this.isCurrentEmailContainerDisplayed) {
            track("activate_account_email", "not_confirmed", "click", "skip_button");
        } else {
            track("activate_account_email", "waiting", "click", "skip_button");
        }
        getVerificationsActivity().doneWithVerification(true);
    }

    private void changeCurrentContainer() {
        this.isCurrentEmailContainerDisplayed = !this.isCurrentEmailContainerDisplayed;
        displayCurrentContainer(true);
    }

    private void displayCurrentContainer(boolean showFading) {
        if (showFading) {
            AnimationUtils.fadeInOut(getViewToDisplay(), getViewToHide());
            if (this.isCurrentEmailContainerDisplayed) {
                track("activate_account_email", "not_confirmed", "impression");
            } else {
                track("activate_account_email", "waiting", "impression");
            }
        } else {
            hideView(getViewToHide());
            showView(getViewToDisplay());
        }
    }

    private View getViewToHide() {
        return this.isCurrentEmailContainerDisplayed ? this.changeEmailContainer : this.currentEmailContainer;
    }

    private View getViewToDisplay() {
        return this.isCurrentEmailContainerDisplayed ? this.currentEmailContainer : this.changeEmailContainer;
    }

    private void hideView(View view) {
        view.setVisibility(4);
        view.setAlpha(0.0f);
    }

    private void showView(View view) {
        view.setVisibility(0);
        view.setAlpha(1.0f);
    }

    static /* synthetic */ void lambda$new$0(EmailVerificationFragment emailVerificationFragment, BaseResponse response) {
        if (emailVerificationFragment.changeEmailContainer.getVisibility() == 4) {
            Toast.makeText(emailVerificationFragment.getActivity(), C0880R.string.verifications_email_success, 0).show();
            emailVerificationFragment.changeCurrentContainer();
        }
    }

    private void setUpVerificationsListener() {
        getVerificationsActivity().registerVerificationsListener(EmailVerificationFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpVerificationsListener$2(EmailVerificationFragment emailVerificationFragment, VerificationsResponse response) {
        if (response.getVerification(Type.Email).isCompleted()) {
            emailVerificationFragment.getVerificationsActivity().doneWithVerification(false);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_PROCEED_UPDATE_EMAIL /*489*/:
                    verifyEmail();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
