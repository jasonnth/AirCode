package com.airbnb.android.registration;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.PasswordUtils;
import com.airbnb.android.utils.PasswordUtils.PasswordResult;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetInputText.State;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class EditPasswordRegistrationFragment extends BaseRegistrationFragment {
    @BindView
    SheetInputText editPassword;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    private SnackbarWrapper snackbar;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            EditPasswordRegistrationFragment.this.editPassword.setState(EditPasswordRegistrationFragment.this.checkPasswordValidity() == PasswordResult.VALID ? State.Valid : State.Normal);
            EditPasswordRegistrationFragment.this.enableNextButtonIfNecessary();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_edit_password_registration, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.editPassword.setText(this.dataPassedIn.password());
        }
        setupViews();
        return view;
    }

    public void onPause() {
        super.onPause();
        dismissSnackbar();
    }

    public void onDestroyView() {
        this.editPassword.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationPassword;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void next() {
        PasswordResult passwordValidity = checkPasswordValidity();
        RegistrationAnalytics.trackRegistrationPasswordPageClickEvent(RegistrationAnalytics.NEXT_BUTTON, this.dataPassedIn.getRegistrationServiceForAnalytics(), getNavigationTrackingTag(), passwordValidity == PasswordResult.VALID);
        if (passwordValidity == PasswordResult.VALID) {
            KeyboardUtils.dismissSoftKeyboard((View) this.editPassword);
            this.registrationController.onStepFinished(AccountRegistrationStep.Password, AccountRegistrationData.builder().password(this.editPassword.getText().toString()).build());
            return;
        }
        this.editPassword.setState(State.Error);
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(passwordValidity.messageRes)).duration(0);
        this.snackbar.buildAndShow();
    }

    private void setupViews() {
        this.editPassword.addTextChangedListener(this.textWatcher);
        this.editPassword.setOnShowPasswordToggleListener(EditPasswordRegistrationFragment$$Lambda$1.lambdaFactory$(this));
        this.editPassword.setOnEditorActionListener(EditPasswordRegistrationFragment$$Lambda$2.lambdaFactory$(this));
        enableNextButtonIfNecessary();
    }

    static /* synthetic */ void lambda$setupViews$0(EditPasswordRegistrationFragment editPasswordRegistrationFragment, boolean showPassword) {
        RegistrationAnalytics.trackClickEvent(showPassword ? RegistrationAnalytics.SHOW_PASSWORD_BUTTON : RegistrationAnalytics.HIDE_PASSWORD_BUTTON, editPasswordRegistrationFragment.getNavigationTrackingTag());
    }

    /* access modifiers changed from: private */
    public PasswordResult checkPasswordValidity() {
        String password = this.editPassword.getText().toString();
        String email = this.dataPassedIn.email();
        if (TextUtils.isEmpty(email)) {
            return PasswordUtils.checkPasswordWithForbiddenContent(password, this.dataPassedIn.firstName(), this.dataPassedIn.lastName());
        }
        return PasswordUtils.checkPasswordWithForbiddenContent(password, this.dataPassedIn.firstName(), this.dataPassedIn.lastName(), email);
    }

    /* access modifiers changed from: private */
    public void enableNextButtonIfNecessary() {
        this.nextButton.setEnabled(this.editPassword.getText().toString().length() >= 8);
    }

    private void dismissSnackbar() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
    }
}
