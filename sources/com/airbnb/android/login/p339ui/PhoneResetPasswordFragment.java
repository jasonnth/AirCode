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
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.PasswordUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment */
public class PhoneResetPasswordFragment extends AirFragment {
    private static final String ARG_AIRPHONE = "airphone";
    @State
    AirPhone airPhone;
    private final Handler handler = new Handler();
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @BindView
    SheetInputText passwordInputText;
    final RequestListener<ForgotPasswordResponse> requestlistener = new C0699RL().onResponse(PhoneResetPasswordFragment$$Lambda$1.lambdaFactory$(this)).onError(PhoneResetPasswordFragment$$Lambda$2.lambdaFactory$(this)).build();
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            PhoneResetPasswordFragment.this.passwordInputText.setState(SheetInputText.State.Normal);
            PhoneResetPasswordFragment.this.nextButton.setEnabled(PasswordUtils.isValidPassword(s.toString()));
        }
    };

    public static PhoneResetPasswordFragment newInstance(AirPhone airPhone2) {
        return (PhoneResetPasswordFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PhoneResetPasswordFragment()).putParcelable(ARG_AIRPHONE, airPhone2)).build();
    }

    static /* synthetic */ void lambda$new$1(PhoneResetPasswordFragment phoneResetPasswordFragment, ForgotPasswordResponse data) {
        if (data.isSuccess()) {
            phoneResetPasswordFragment.nextButton.setState(AirButton.State.Success);
            phoneResetPasswordFragment.handler.postDelayed(PhoneResetPasswordFragment$$Lambda$4.lambdaFactory$(phoneResetPasswordFragment), 700);
            return;
        }
        phoneResetPasswordFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(phoneResetPasswordFragment.getContext(), data.getMessage());
    }

    static /* synthetic */ void lambda$new$2(PhoneResetPasswordFragment phoneResetPasswordFragment, AirRequestNetworkException e) {
        phoneResetPasswordFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.toastNetworkError(phoneResetPasswordFragment.getContext(), (NetworkException) e);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_phone_reset_password, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.airPhone = (AirPhone) getArguments().getParcelable(ARG_AIRPHONE);
        }
        this.passwordInputText.addTextChangedListener(this.textWatcher);
        this.passwordInputText.setOnShowPasswordToggleListener(PhoneResetPasswordFragment$$Lambda$3.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$3(PhoneResetPasswordFragment phoneResetPasswordFragment, boolean showPassword) {
        RegistrationAnalytics.trackClickEvent(showPassword ? RegistrationAnalytics.SHOW_PASSWORD_BUTTON : RegistrationAnalytics.HIDE_PASSWORD_BUTTON, phoneResetPasswordFragment.getNavigationTrackingTag());
    }

    public void onDestroyView() {
        this.passwordInputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNext() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.nextButton.setState(AirButton.State.Loading);
        String newPassword = this.passwordInputText.getText().toString();
        ForgotPasswordRequest.forPhoneResetPassword(this.airPhone, newPassword, newPassword).withListener((Observer) this.requestlistener).execute(this.requestManager);
    }

    public void onDetach() {
        this.handler.removeCallbacksAndMessages(null);
        super.onDetach();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationResetPasswordPhone;
    }
}
