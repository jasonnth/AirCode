package com.airbnb.android.login.p339ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.utils.TextUtil;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.EmailForgotPasswordFragment */
public class EmailForgotPasswordFragment extends AirFragment {
    private static final String ARG_EMAIL = "arg_email";
    @BindView
    SheetInputText email;
    private final Handler handler = new Handler();
    @BindView
    JellyfishView jellyfishView;
    final RequestListener<ForgotPasswordResponse> listener = new C0699RL().onResponse(EmailForgotPasswordFragment$$Lambda$1.lambdaFactory$(this)).onError(EmailForgotPasswordFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton submitButton;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            EmailForgotPasswordFragment.this.submitButton.setEnabled(TextUtil.isValidEmail(EmailForgotPasswordFragment.this.email.getText()));
        }
    };

    public static Intent newIntent(Context context, String email2) {
        return TransparentActionBarActivity.intentForFragmentWithoutRequiringAccount(context, EmailForgotPasswordFragment.class, ((BundleBuilder) new BundleBuilder().putString(ARG_EMAIL, email2)).toBundle());
    }

    static /* synthetic */ void lambda$new$1(EmailForgotPasswordFragment emailForgotPasswordFragment, ForgotPasswordResponse data) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.FORGOT_PASSWORD_EMAIL_RESPONSE, "email", emailForgotPasswordFragment.getNavigationTrackingTag(), emailForgotPasswordFragment.getContext());
        if (data.isSuccess()) {
            emailForgotPasswordFragment.submitButton.setState(State.Success);
            Toast.makeText(emailForgotPasswordFragment.getContext(), emailForgotPasswordFragment.getString(C7331R.string.forgot_password_reset_successful, emailForgotPasswordFragment.email.getText().toString()), 1).show();
            emailForgotPasswordFragment.handler.postDelayed(EmailForgotPasswordFragment$$Lambda$3.lambdaFactory$(emailForgotPasswordFragment), 700);
            return;
        }
        emailForgotPasswordFragment.submitButton.setState(State.Normal);
        NetworkUtil.toastNetworkError(emailForgotPasswordFragment.getContext(), data.getMessage());
    }

    static /* synthetic */ void lambda$new$2(EmailForgotPasswordFragment emailForgotPasswordFragment, AirRequestNetworkException e) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.FORGOT_PASSWORD_EMAIL_RESPONSE, "email", emailForgotPasswordFragment.getNavigationTrackingTag(), (NetworkException) e);
        emailForgotPasswordFragment.submitButton.setState(State.Normal);
        NetworkUtil.toastNetworkError(emailForgotPasswordFragment.getContext(), (NetworkException) e);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_email_forgot_password, container, false);
        bindViews(view);
        this.email.addTextChangedListener(this.textWatcher);
        String emailString = getArguments().getString(ARG_EMAIL);
        if (!TextUtils.isEmpty(emailString)) {
            this.email.setText(emailString);
            this.email.setSelection(this.email.getText().length());
        }
        return view;
    }

    public void onPause() {
        super.onPause();
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onDestroyView() {
        this.email.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void submit() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.FORGOT_PASSWORD_REQUEST_EMAIL_BUTTON, getNavigationTrackingTag());
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.submitButton.setState(State.Loading);
        ForgotPasswordRequest.forEmail(this.email.getText().toString()).withListener((Observer) this.listener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationForgotPasswordEmail;
    }
}
