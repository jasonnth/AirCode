package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.Listener;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.verifications.VerificationsState;
import com.airbnb.android.core.utils.NetworkUtil;
import icepick.State;
import java.util.ArrayList;

public class VerificationsLoaderActivity extends SolitAirActivity implements Listener {
    private static final String ARGS_SHOW_PROGRESS = "args_show_progress";
    private static final String ARGS_VERIFICATION_FLOW = "args_verification_flow";
    public static final String RESULT_EXTRA_USING_IDENTITY_FLOW = "result_extra_using_identity_flow";
    public static final String RESULT_EXTRA_VERIFICATIONS_STATE = "result_extra_verifications_state";
    public static final String RESULT_EXTRA_VERIFICATION_FLOW = "result_extra_verification_flow";
    private FetchIdentityController fetchIdentityController;
    @State
    VerificationFlow verificationFlow;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.verificationFlow = VerificationFlow.values()[getIntent().getIntExtra(ARGS_VERIFICATION_FLOW, -1)];
        }
        this.fetchIdentityController = new FetchIdentityController(this.requestManager, (Listener) this, this.verificationFlow, savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        showLoader(getIntent().getBooleanExtra(ARGS_SHOW_PROGRESS, true));
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
    }

    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(17432576, 17432577);
    }

    public void onVerificationsFetchComplete(ArrayList<AccountVerification> incompleteVerifications) {
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA_VERIFICATIONS_STATE, VerificationsState.initializeFromIncompleteAccountVerifications(incompleteVerifications));
        data.putExtra(RESULT_EXTRA_USING_IDENTITY_FLOW, false);
        data.putExtra(RESULT_EXTRA_VERIFICATION_FLOW, this.verificationFlow.ordinal());
        setResult(-1, data);
        finish();
    }

    public void onVerificationsFetchError(NetworkException e) {
        NetworkUtil.toastNetworkError((Context) this, e);
        finish();
    }
}
