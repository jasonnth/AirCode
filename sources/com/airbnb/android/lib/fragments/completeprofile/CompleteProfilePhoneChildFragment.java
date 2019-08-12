package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
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
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.requests.PhoneNumberVerificationRequest;
import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneFragment.PhoneAccountListener;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import icepick.State;
import java.util.Locale;
import p032rx.Observer;

public class CompleteProfilePhoneChildFragment extends AirFragment implements VerifiedIdStrapper {
    public static final String TAG = CompleteProfilePhoneChildFragment.class.getSimpleName();
    @State
    String formattedPhoneNumber;
    /* access modifiers changed from: private */
    public PhoneNumber phoneNumber;
    private EditText phoneNumberEditText;
    /* access modifiers changed from: private */
    public PhoneNumberUtil phoneNumberUtil;
    PhoneUtil phoneUtil;
    final RequestListener<PhoneNumberVerificationResponse> requestConfirmationCodeListener = new C0699RL().onResponse(CompleteProfilePhoneChildFragment$$Lambda$1.lambdaFactory$(this)).onError(CompleteProfilePhoneChildFragment$$Lambda$2.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public Button sendButton;
    VerifiedIdAnalytics verifiedIdAnalytics;

    public static CompleteProfilePhoneChildFragment newInstance() {
        return new CompleteProfilePhoneChildFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_child_complete_profile_phone, container, false);
        this.phoneNumberEditText = (EditText) view.findViewById(C0880R.C0882id.phone_number_editText);
        this.sendButton = (Button) view.findViewById(C0880R.C0882id.send_phone_number_btn);
        this.phoneNumberUtil = PhoneNumberUtil.getInstance();
        setupPhoneNumberEditText();
        setupSendButton();
        CompleteProfilePhoneChildFragmentPermissionsDispatcher.fetchPhoneNumberWithCheck(this);
        if (this.phoneNumber == null) {
            this.phoneNumberEditText.requestFocus();
        }
        if (getParent().isVerifiedIdFlow() && savedInstanceState == null) {
            VerifiedIdAnalytics.trackPhoneStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth("phone", "start");
        }
        this.phoneNumberEditText.setHint(this.phoneNumberUtil.format(this.phoneNumberUtil.getExampleNumber(getCountryCode()), PhoneNumberFormat.NATIONAL));
        return view;
    }

    private String getCountryCode() {
        String countryCode = this.phoneUtil.getSimCountryCodeUppercase();
        if (!TextUtils.isEmpty(countryCode)) {
            return countryCode;
        }
        String possibleCountry = Locale.getDefault().getCountry();
        return !TextUtils.isEmpty(possibleCountry) ? possibleCountry : "US";
    }

    private void setupPhoneNumberEditText() {
        if (this.phoneNumber != null) {
            this.phoneNumberEditText.setText(this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberFormat.NATIONAL));
        }
        this.phoneNumberEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (CompleteProfilePhoneChildFragment.this.getParent().isVerifiedIdFlow()) {
                    VerifiedIdAnalytics.trackPhoneStartDidType(CompleteProfilePhoneChildFragment.this.getVerifiedIdAnalyticsStrap());
                }
                if (!TextUtils.isEmpty(s.toString())) {
                    try {
                        CompleteProfilePhoneChildFragment.this.phoneNumber = CompleteProfilePhoneChildFragment.this.phoneNumberUtil.parse(s.toString(), Locale.getDefault().getCountry());
                        CompleteProfilePhoneChildFragment.this.sendButton.setEnabled(CompleteProfilePhoneChildFragment.this.phoneNumberUtil.isValidNumber(CompleteProfilePhoneChildFragment.this.phoneNumber));
                    } catch (NumberParseException e) {
                    }
                }
            }
        });
        this.phoneNumberEditText.setOnKeyListener(CompleteProfilePhoneChildFragment$$Lambda$3.lambdaFactory$(this));
        this.phoneNumberEditText.setOnClickListener(CompleteProfilePhoneChildFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupPhoneNumberEditText$0(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment, View view, int keyCode, KeyEvent event) {
        if (event.getAction() == 0 && event.getKeyCode() == 66 && completeProfilePhoneChildFragment.sendButton.isEnabled()) {
            completeProfilePhoneChildFragment.formattedPhoneNumber = completeProfilePhoneChildFragment.phoneNumberUtil.format(completeProfilePhoneChildFragment.phoneNumber, PhoneNumberFormat.E164);
            completeProfilePhoneChildFragment.formattedPhoneNumber = completeProfilePhoneChildFragment.formattedPhoneNumber.replace("+", "");
            if (completeProfilePhoneChildFragment.getParent().isEditPhoneNumber) {
                completeProfilePhoneChildFragment.sendEditPhoneNumber(completeProfilePhoneChildFragment.formattedPhoneNumber);
            } else {
                completeProfilePhoneChildFragment.sendPhoneNumber(completeProfilePhoneChildFragment.formattedPhoneNumber);
            }
        }
        return false;
    }

    static /* synthetic */ void lambda$setupPhoneNumberEditText$1(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment, View view) {
        if (completeProfilePhoneChildFragment.getParent().isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhoneStartSelectField(completeProfilePhoneChildFragment.getVerifiedIdAnalyticsStrap());
        }
    }

    private void setupSendButton() {
        if (this.phoneNumber == null) {
            this.sendButton.setEnabled(false);
        }
        this.sendButton.setOnClickListener(CompleteProfilePhoneChildFragment$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupSendButton$2(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment, View view) {
        completeProfilePhoneChildFragment.formattedPhoneNumber = completeProfilePhoneChildFragment.phoneNumberUtil.format(completeProfilePhoneChildFragment.phoneNumber, PhoneNumberFormat.E164);
        completeProfilePhoneChildFragment.formattedPhoneNumber = completeProfilePhoneChildFragment.formattedPhoneNumber.replace("+", "");
        if (completeProfilePhoneChildFragment.getParent().isEditPhoneNumber) {
            completeProfilePhoneChildFragment.sendEditPhoneNumber(completeProfilePhoneChildFragment.formattedPhoneNumber);
        } else {
            completeProfilePhoneChildFragment.sendPhoneNumber(completeProfilePhoneChildFragment.formattedPhoneNumber);
        }
    }

    /* access modifiers changed from: 0000 */
    public void fetchPhoneNumber() {
        TelephonyManager telephone = (TelephonyManager) getActivity().getSystemService("phone");
        if (telephone != null) {
            String phoneNumber2 = telephone.getLine1Number();
            if (!TextUtils.isEmpty(phoneNumber2)) {
                try {
                    this.phoneNumber = this.phoneNumberUtil.parse(phoneNumber2, Locale.getDefault().getCountry());
                    this.phoneNumberEditText.setText(this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberFormat.NATIONAL));
                } catch (NumberParseException e) {
                    Log.e(TAG, "Error parsing phone number");
                }
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        CompleteProfilePhoneChildFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void sendPhoneNumber(String formattedPhoneNumber2) {
        if (getParent().isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhoneStartSend(getVerifiedIdAnalyticsStrap().mo11639kv("phone_number", formattedPhoneNumber2));
        } else if (getParent().isEditProfileFlow()) {
            EditProfileAnalytics.trackAction("add", "phone_number", null);
        }
        sendPhoneNumberPrep();
        getParent().updateAccountPhoneNumber(formattedPhoneNumber2, new PhoneAccountListener() {
            public void onPhoneAccountUpdateSuccess() {
                if (CompleteProfilePhoneChildFragment.this.isResumed()) {
                    CompleteProfilePhoneChildFragment.this.getParent().displayPhoneVerificationCodeEntry(null);
                    CompleteProfilePhoneChildFragment.this.sendButton.setEnabled(true);
                }
            }

            public void onPhoneAccountUpdateError() {
                Toast.makeText(CompleteProfilePhoneChildFragment.this.getActivity(), C0880R.string.sms_number_error_please_try_again, 0).show();
                CompleteProfilePhoneChildFragment.this.sendButton.setEnabled(true);
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

    private void sendPhoneNumberPrep() {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(true);
        this.sendButton.setEnabled(false);
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        getView().findViewById(C0880R.C0882id.child_container).requestFocus();
        getParent().setPhoneNumber(this.phoneNumber);
    }

    private void sendEditPhoneNumber(String formattedPhoneNumber2) {
        sendPhoneNumberPrep();
        PhoneNumberVerificationRequest.forPhoneNumberVerification(formattedPhoneNumber2).withListener((Observer) this.requestConfirmationCodeListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$3(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment, PhoneNumberVerificationResponse response) {
        ((LoaderFrameDisplay) completeProfilePhoneChildFragment.getActivity()).displayLoaderFrame(false);
        completeProfilePhoneChildFragment.sendButton.setEnabled(true);
        completeProfilePhoneChildFragment.getParent().showChildFragment(CompleteProfilePhoneCodeChildFragment.newInstanceForEditPhoneNumber(completeProfilePhoneChildFragment.formattedPhoneNumber));
    }

    static /* synthetic */ void lambda$new$4(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment, AirRequestNetworkException e) {
        ((LoaderFrameDisplay) completeProfilePhoneChildFragment.getActivity()).displayLoaderFrame(false);
        completeProfilePhoneChildFragment.sendButton.setEnabled(true);
        NetworkUtil.toastNetworkError(completeProfilePhoneChildFragment.getContext(), (NetworkException) e);
    }
}
