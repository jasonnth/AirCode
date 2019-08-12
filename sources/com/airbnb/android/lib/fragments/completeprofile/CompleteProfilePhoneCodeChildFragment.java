package com.airbnb.android.lib.fragments.completeprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.ProfileUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.ProfilePhoneNumber;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneFragment.PhoneAccountListener;
import com.airbnb.android.registration.requests.AccountCreationRequest;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.squareup.otto.Bus;
import icepick.State;
import java.util.Locale;
import p032rx.Observer;

public class CompleteProfilePhoneCodeChildFragment extends AirFragment implements VerifiedIdStrapper {
    private static final String ARG_PHONE_NUMBER_TO_EDIT = "phone_number_to_edit";
    private static final String ARG_PHONE_NUMBER_TO_VERIFY = "phone_number_to_verify";
    private static final String DIALOG_ERROR_TAG = "error_dialog_tag";
    private static final int REQUEST_CODE_CANCEL = 908;
    private static final int REQUEST_CODE_TRY_AGAIN = 909;
    private static final String TAG = CompleteProfilePhoneCodeChildFragment.class.getSimpleName();
    final RequestListener<AccountResponse> editPhoneNumberRequestListener = new C0699RL().onResponse(CompleteProfilePhoneCodeChildFragment$$Lambda$2.lambdaFactory$(this)).onError(CompleteProfilePhoneCodeChildFragment$$Lambda$3.lambdaFactory$(this)).build();
    final RequestListener<AccountResponse> getActiveAccountRequestListener = new C0699RL().onResponse(CompleteProfilePhoneCodeChildFragment$$Lambda$1.lambdaFactory$(this)).build();
    AirbnbAccountManager mAccountManager;
    Bus mBus;
    private EditText mCodeEditText;
    /* access modifiers changed from: private */
    public Button mEnterButton;
    @State
    String phoneNumberToEdit;

    public static CompleteProfilePhoneCodeChildFragment newInstance(String phoneNumberToVerify) {
        CompleteProfilePhoneCodeChildFragment fragment = new CompleteProfilePhoneCodeChildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHONE_NUMBER_TO_VERIFY, phoneNumberToVerify);
        fragment.setArguments(args);
        return fragment;
    }

    public static CompleteProfilePhoneCodeChildFragment newInstanceForEditPhoneNumber(String phoneNumberToEdit2) {
        return (CompleteProfilePhoneCodeChildFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CompleteProfilePhoneCodeChildFragment()).putString(ARG_PHONE_NUMBER_TO_EDIT, phoneNumberToEdit2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_child_complete_profile_phone_code, container, false);
        requestPhoneCodeIfNeeded();
        this.mCodeEditText = (EditText) view.findViewById(C0880R.C0882id.phone_code_edit_text);
        Button backButton = (Button) view.findViewById(C0880R.C0882id.back_btn);
        this.mEnterButton = (Button) view.findViewById(C0880R.C0882id.enter_code_btn);
        setupCodeEditText();
        backButton.setOnClickListener(CompleteProfilePhoneCodeChildFragment$$Lambda$4.lambdaFactory$(this));
        this.mEnterButton.setEnabled(false);
        this.mEnterButton.setOnClickListener(CompleteProfilePhoneCodeChildFragment$$Lambda$5.lambdaFactory$(this));
        if (getParent().isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhonePendingView(getVerifiedIdAnalyticsStrap());
        }
        this.phoneNumberToEdit = getArguments().getString(ARG_PHONE_NUMBER_TO_EDIT);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, View v) {
        if (completeProfilePhoneCodeChildFragment.getParent().isEditProfileFlow()) {
            completeProfilePhoneCodeChildFragment.getActivity().finish();
        } else {
            completeProfilePhoneCodeChildFragment.getParent().displayPhoneNumberEntry();
        }
    }

    static /* synthetic */ void lambda$onCreateView$1(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, View v) {
        String code = completeProfilePhoneCodeChildFragment.mCodeEditText.getText().toString();
        if (completeProfilePhoneCodeChildFragment.getParent().isEditPhoneNumber) {
            completeProfilePhoneCodeChildFragment.sendEditPhoneNumberRequest(code);
        } else {
            completeProfilePhoneCodeChildFragment.sendPhoneVerificationCode(code);
        }
        if (completeProfilePhoneCodeChildFragment.getParent().isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhonePendingSend(Strap.make().mo11639kv("code", code));
        } else if (completeProfilePhoneCodeChildFragment.getParent().isEditProfileFlow()) {
            EditProfileAnalytics.trackAction("verify", "phone_number", null);
        }
    }

    private void requestPhoneCodeIfNeeded() {
        String phoneNumberToVerify = getArguments().getString(ARG_PHONE_NUMBER_TO_VERIFY, null);
        if (!TextUtils.isEmpty(phoneNumberToVerify)) {
            try {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                PhoneNumber phoneNum = phoneUtil.parse(phoneNumberToVerify, Locale.getDefault().getCountry());
                getParent().setPhoneNumber(phoneNum);
                final String formattedPhoneNum = phoneUtil.format(phoneNum, PhoneNumberFormat.E164);
                getParent().updateAccountPhoneNumber(formattedPhoneNum, new PhoneAccountListener() {
                    public void onPhoneAccountUpdateSuccess() {
                        Toast.makeText(CompleteProfilePhoneCodeChildFragment.this.getActivity(), CompleteProfilePhoneCodeChildFragment.this.getString(C0880R.string.edit_profile_resend_phone_verification_code_success, formattedPhoneNum), 0).show();
                    }

                    public void onPhoneAccountUpdateError() {
                        if (CompleteProfilePhoneCodeChildFragment.this.isResumed()) {
                            ZenDialog.builder().withTitle(C0880R.string.error).withBodyText(C0880R.string.edit_profile_resend_phone_verification_code_fail).withDualButton(C0880R.string.cancel, (int) CompleteProfilePhoneCodeChildFragment.REQUEST_CODE_CANCEL, C0880R.string.ro_try_again, (int) CompleteProfilePhoneCodeChildFragment.REQUEST_CODE_TRY_AGAIN, (Fragment) CompleteProfilePhoneCodeChildFragment.this).create().show(CompleteProfilePhoneCodeChildFragment.this.getFragmentManager(), CompleteProfilePhoneCodeChildFragment.DIALOG_ERROR_TAG);
                        }
                    }
                });
            } catch (NumberParseException e) {
                Log.w(TAG, "Cannot parse phone number to verify: " + phoneNumberToVerify);
            }
        }
    }

    private void setupCodeEditText() {
        this.mCodeEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable view) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                if (CompleteProfilePhoneCodeChildFragment.this.getParent().isVerifiedIdFlow()) {
                    VerifiedIdAnalytics.trackPhonePendingDidType(CompleteProfilePhoneCodeChildFragment.this.getVerifiedIdAnalyticsStrap());
                }
                if (TextUtils.isEmpty(s) || s.length() != CompleteProfilePhoneCodeChildFragment.this.getResources().getInteger(C0880R.integer.num_sms_code_characters)) {
                    CompleteProfilePhoneCodeChildFragment.this.mEnterButton.setEnabled(false);
                    return;
                }
                CompleteProfilePhoneCodeChildFragment.this.mEnterButton.setEnabled(true);
                KeyboardUtils.dismissSoftKeyboard(CompleteProfilePhoneCodeChildFragment.this.getActivity(), CompleteProfilePhoneCodeChildFragment.this.getView());
            }
        });
        this.mCodeEditText.setOnKeyListener(CompleteProfilePhoneCodeChildFragment$$Lambda$6.lambdaFactory$(this));
        this.mCodeEditText.setOnFocusChangeListener(CompleteProfilePhoneCodeChildFragment$$Lambda$7.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupCodeEditText$2(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, View view, int keyCode, KeyEvent event) {
        if (event.getAction() == 0 && event.getKeyCode() == 66) {
            completeProfilePhoneCodeChildFragment.sendPhoneVerificationCode(completeProfilePhoneCodeChildFragment.mCodeEditText.getText().toString());
        } else if (event.getAction() == 0 && event.getKeyCode() == 4) {
            completeProfilePhoneCodeChildFragment.getParent().displayPhoneNumberEntry();
        }
        return false;
    }

    static /* synthetic */ void lambda$setupCodeEditText$3(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, View v, boolean hasFocus) {
        if (hasFocus) {
            if (completeProfilePhoneCodeChildFragment.getParent().isVerifiedIdFlow()) {
                VerifiedIdAnalytics.trackPhonePendingDidSelectField(completeProfilePhoneCodeChildFragment.getVerifiedIdAnalyticsStrap());
            }
            KeyboardUtils.showSoftKeyboard(completeProfilePhoneCodeChildFragment.getActivity(), completeProfilePhoneCodeChildFragment.getView());
        }
    }

    private void sendPhoneVerificationCode(String code) {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(true);
        this.mEnterButton.setEnabled(false);
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        getParent().updateAccountPhoneCode(code, new PhoneAccountListener() {
            public void onPhoneAccountUpdateSuccess() {
                CompleteProfileActivity activity = (CompleteProfileActivity) CompleteProfilePhoneCodeChildFragment.this.getActivity();
                new GetActiveAccountRequest(CompleteProfilePhoneCodeChildFragment.this.getContext()).withListener((Observer) new SimpleRequestListener<AccountResponse>() {
                    public void onResponse(AccountResponse response) {
                        CompleteProfilePhoneCodeChildFragment.this.mBus.post(new ProfileUpdatedEvent(ProfileSection.Phone));
                    }
                }).skipCache().execute(NetworkUtil.singleFireExecutor());
                CompleteProfilePhoneCodeChildFragment.this.updateUserPhone(PhoneNumberUtil.getInstance().format(CompleteProfilePhoneCodeChildFragment.this.getParent().getPhoneNumber(), PhoneNumberFormat.E164));
                activity.completeVerification();
                CompleteProfilePhoneCodeChildFragment.this.mEnterButton.setEnabled(true);
            }

            public void onPhoneAccountUpdateError() {
                Toast.makeText(CompleteProfilePhoneCodeChildFragment.this.getActivity(), C0880R.string.sms_verification_error_invalid_code, 0).show();
                CompleteProfilePhoneCodeChildFragment.this.mEnterButton.setEnabled(true);
            }
        });
    }

    /* access modifiers changed from: private */
    public CompleteProfilePhoneFragment getParent() {
        return (CompleteProfilePhoneFragment) getParentFragment();
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }

    /* access modifiers changed from: private */
    public void updateUserPhone(String phoneNumber) {
        User currentUser = this.mAccountManager.getCurrentUser();
        currentUser.setPhone(phoneNumber);
        currentUser.getVerifications().add("phone");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_CODE_CANCEL /*908*/:
                    getActivity().finish();
                    return;
                case REQUEST_CODE_TRY_AGAIN /*909*/:
                    requestPhoneCodeIfNeeded();
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        }
    }

    private void sendEditPhoneNumberRequest(String code) {
        showLoader(true);
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(true);
        this.mEnterButton.setEnabled(false);
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        AccountCreationRequest.forEditPhoneNumber(((ProfilePhoneNumber) this.mAccountManager.getCurrentUser().getPhoneNumbers().get(0)).getNumber(), this.phoneNumberToEdit, code, this.mAccountManager.getCurrentUser().getId()).withListener((Observer) this.editPhoneNumberRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$4(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, AccountResponse response) {
        completeProfilePhoneCodeChildFragment.mBus.post(new ProfileUpdatedEvent(ProfileSection.Phone));
        completeProfilePhoneCodeChildFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$5(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, AccountResponse response) {
        ((LoaderFrameDisplay) completeProfilePhoneCodeChildFragment.getActivity()).displayLoaderFrame(false);
        new GetActiveAccountRequest(completeProfilePhoneCodeChildFragment.getContext()).withListener((Observer) completeProfilePhoneCodeChildFragment.getActiveAccountRequestListener).execute(completeProfilePhoneCodeChildFragment.requestManager);
    }

    static /* synthetic */ void lambda$new$6(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment, AirRequestNetworkException e) {
        ((LoaderFrameDisplay) completeProfilePhoneCodeChildFragment.getActivity()).displayLoaderFrame(false);
        NetworkUtil.toastNetworkError(completeProfilePhoneCodeChildFragment.getContext(), (NetworkException) e);
    }
}
