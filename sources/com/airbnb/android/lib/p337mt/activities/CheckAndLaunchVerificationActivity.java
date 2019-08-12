package com.airbnb.android.lib.p337mt.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.Listener;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import java.util.ArrayList;

/* renamed from: com.airbnb.android.lib.mt.activities.CheckAndLaunchVerificationActivity */
public class CheckAndLaunchVerificationActivity extends AirActivity implements Listener {
    private static final String ARG_FLOW = "verificationFlow";
    private FetchIdentityController fetchIdentityController;
    @BindView
    LoaderFrame loaderFrame;
    private VerificationFlow verificationFlow;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_mt_check_verification);
        ButterKnife.bind((Activity) this);
        this.verificationFlow = VerificationFlow.getVerificationFlowFromString(getIntent().getStringExtra(ARG_FLOW));
        this.fetchIdentityController = new FetchIdentityController(this.requestManager, (Listener) this, this.verificationFlow, savedInstanceState);
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
        this.loaderFrame.startAnimation();
    }

    public void onVerificationsFetchComplete(ArrayList<AccountVerification> incompleteVerifications) {
        this.loaderFrame.finishImmediate();
        if (incompleteVerifications.isEmpty()) {
            setResult(-1);
            finish();
            return;
        }
        Intent intent = AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this, AccountVerificationStartFragmentArguments.builder().verificationFlow(this.verificationFlow).incompleteVerifications(incompleteVerifications).build());
        intent.setFlags(33554432);
        startActivity(intent);
        finish();
    }

    public void onVerificationsFetchError(NetworkException e) {
        this.loaderFrame.finishImmediate();
        Toast.makeText(this, C0880R.string.error_request, 1).show();
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.fetchIdentityController.onSaveInstanceState(outState);
    }
}
