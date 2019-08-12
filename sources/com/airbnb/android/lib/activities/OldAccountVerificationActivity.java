package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.requests.PutSecurityCheckRequest;
import com.airbnb.android.core.responses.PutSecurityCheckResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.AccountVerificationPhoneCodeFragment;
import com.airbnb.android.lib.fragments.AccountVerificationPhonePickerFragment;
import com.airbnb.android.lib.fragments.AccountVerificationWelcomeFragment;

public class OldAccountVerificationActivity extends SolitAirActivity {
    public static final String EXTRA_VERIFICATION_TYPE = "verification_type";
    private int mVerificationType = -1;

    public enum VerificationType {
        Listing(C0880R.string.security_check_contact_body_listing),
        Payout(C0880R.string.security_check_contact_body_add_payout);
        
        public final int mBody;

        private VerificationType(int body) {
            this.mBody = body;
        }
    }

    public static Intent intentForVerificationType(Context context, int verificationType) {
        Intent intent = new Intent(context, OldAccountVerificationActivity.class);
        intent.putExtra(EXTRA_VERIFICATION_TYPE, verificationType);
        return intent;
    }

    public static Intent intentForPayout(Context context) {
        Intent intent = new Intent(context, OldAccountVerificationActivity.class);
        intent.putExtra(EXTRA_VERIFICATION_TYPE, VerificationType.Payout.ordinal());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar(C0880R.string.title_account_verification, new Object[0]);
        this.mVerificationType = getIntent().getIntExtra(EXTRA_VERIFICATION_TYPE, 0);
        if (savedInstanceState == null) {
            showFragment(AccountVerificationWelcomeFragment.newInstance(), false);
        }
    }

    public void onBeginClick() {
        showFragment(AccountVerificationPhonePickerFragment.newInstance(this.mVerificationType), true);
    }

    public void phoneNumberSelected(long phoneNumberId) {
        showFragment(AccountVerificationPhoneCodeFragment.newInstance(phoneNumberId), true);
    }

    public void submitPhoneCode(long phoneNumberId, String code) {
        showLoader(true);
        new PutSecurityCheckRequest(phoneNumberId, code, new NonResubscribableRequestListener<PutSecurityCheckResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                OldAccountVerificationActivity.this.showLoader(false);
                OldAccountVerificationActivity.this.showErrorDialog();
            }

            public void onResponse(PutSecurityCheckResponse response) {
                OldAccountVerificationActivity.this.showLoader(false);
                boolean isSatisfied = response.securityCheck.isSatisfied();
                SecurityCheckAnalytics.trackCodeResponse(isSatisfied);
                if (isSatisfied) {
                    OldAccountVerificationActivity.this.setResult(-1);
                    OldAccountVerificationActivity.this.finish();
                    return;
                }
                Toast.makeText(OldAccountVerificationActivity.this, C0880R.string.account_verification_code_error, 0).show();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void showErrorDialog() {
        ZenDialog.builder().withTitle(C0880R.string.error).withBodyText(C0880R.string.security_check_network_error_dialog).withSingleButton(C0880R.string.okay, 0, (Fragment) null).create().show(getSupportFragmentManager(), (String) null);
    }
}
