package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;
import com.airbnb.android.utils.BundleBuilder;

public class AccountVerificationPhoneCodeFragment extends AirFragment implements FragmentLauncher {
    private static final String ARG_PHONE_ID = "phone_id";
    @BindView
    Button mBackButton;
    @BindView
    EditText mPhoneCodeInput;
    private long mPhoneNumberId;
    @BindView
    Button mSubmitCodeButton;

    public static Fragment newInstance(long phoneNumberId) {
        Fragment f = new AccountVerificationPhoneCodeFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PHONE_ID, phoneNumberId);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_account_verification_phone_code, container, false);
        SecurityCheckAnalytics.trackEnterCodeImpression(null);
        this.mPhoneNumberId = getArguments().getLong(ARG_PHONE_ID);
        bindViews(view);
        this.mBackButton.setOnClickListener(AccountVerificationPhoneCodeFragment$$Lambda$1.lambdaFactory$(this));
        this.mSubmitCodeButton.setOnClickListener(AccountVerificationPhoneCodeFragment$$Lambda$2.lambdaFactory$(this));
        this.mPhoneCodeInput.setOnKeyListener(AccountVerificationPhoneCodeFragment$$Lambda$3.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment, View v) {
        ((OldAccountVerificationActivity) accountVerificationPhoneCodeFragment.getActivity()).submitPhoneCode(accountVerificationPhoneCodeFragment.mPhoneNumberId, accountVerificationPhoneCodeFragment.mPhoneCodeInput.getText().toString());
        SecurityCheckAnalytics.trackSubmitCode(accountVerificationPhoneCodeFragment.mPhoneCodeInput.getText().toString());
    }

    static /* synthetic */ boolean lambda$onCreateView$2(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment, View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0 && event.getKeyCode() == 66) {
            ((OldAccountVerificationActivity) accountVerificationPhoneCodeFragment.getActivity()).submitPhoneCode(accountVerificationPhoneCodeFragment.mPhoneNumberId, accountVerificationPhoneCodeFragment.mPhoneCodeInput.getText().toString());
        }
        return false;
    }

    public Bundle getDummyArguments() {
        return ((BundleBuilder) new BundleBuilder().putLong(ARG_PHONE_ID, 0)).toBundle();
    }
}
