package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.events.ProfileUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.EditProfileRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.UserProfileUtils;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.login.utils.LoginUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.utils.TextUtil;

public class ConfirmProfileInfoFragment extends AirFragment {
    private static final String TAG_ERROR_DIALOG = "error_dialog";
    @BindView
    AirEditTextView mEmail;
    @BindView
    AirEditTextView mFirstName;
    @BindView
    AirEditTextView mLastName;
    @BindView
    StickyButton mStickyButton;
    private final TextWatcher mTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ConfirmProfileInfoFragment.this.updateStickyButtonState();
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_confirm_profile, container, false);
        bindViews(view);
        User user = this.mAccountManager.getCurrentUser();
        if (user == null) {
            throw new IllegalStateException("User does not exist.");
        }
        this.mFirstName.setText(user.getFirstName());
        this.mLastName.setText(user.getLastName());
        if (!LoginUtils.isDefaultWeiboEmail(user.getEmailAddress())) {
            this.mEmail.setText(user.getEmailAddress());
        }
        this.mFirstName.addTextChangedListener(this.mTextWatcher);
        this.mLastName.addTextChangedListener(this.mTextWatcher);
        this.mEmail.addTextChangedListener(this.mTextWatcher);
        this.mStickyButton.setOnClickListener(ConfirmProfileInfoFragment$$Lambda$1.lambdaFactory$(this, user));
        updateStickyButtonState();
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(ConfirmProfileInfoFragment confirmProfileInfoFragment, User user, View v) {
        String newEmail = confirmProfileInfoFragment.mEmail.getText().toString();
        Strap emailStrap = null;
        if (!newEmail.equals(user.getEmailAddress())) {
            emailStrap = EditProfileRequest.makeStrap(ProfileSection.Email, newEmail);
        }
        confirmProfileInfoFragment.showLoader(true);
        new EditProfileRequest(confirmProfileInfoFragment.mFirstName.getText().toString(), confirmProfileInfoFragment.mLastName.getText().toString(), emailStrap, new NonResubscribableRequestListener<UserResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                ConfirmProfileInfoFragment.this.showLoader(false);
                ZenDialog.createSingleButtonDialog(C0880R.string.error, NetworkUtil.errorMessage(error), C0880R.string.okay).show(ConfirmProfileInfoFragment.this.getFragmentManager(), ConfirmProfileInfoFragment.TAG_ERROR_DIALOG);
            }

            public void onResponse(UserResponse response) {
                ConfirmProfileInfoFragment.this.showLoader(false);
                if (UserProfileUtils.updateCurrentUser(ConfirmProfileInfoFragment.this.mAccountManager.getCurrentUser(), response)) {
                    ConfirmProfileInfoFragment.this.mBus.post(new ProfileUpdatedEvent(ProfileSection.Name));
                }
                ConfirmProfileInfoFragment.this.getActivity().finish();
            }
        }).execute(confirmProfileInfoFragment.requestManager);
    }

    public void onDestroyView() {
        this.mFirstName.removeTextChangedListener(this.mTextWatcher);
        this.mLastName.removeTextChangedListener(this.mTextWatcher);
        this.mEmail.removeTextChangedListener(this.mTextWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void updateStickyButtonState() {
        this.mStickyButton.setEnabled(!TextUtils.isEmpty(this.mFirstName.getText()) && !TextUtils.isEmpty(this.mLastName.getText()) && !TextUtils.isEmpty(this.mEmail.getText()) && TextUtil.isValidEmail(this.mEmail.getText()));
    }
}
