package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.FragmentBundler;
import icepick.State;
import java.util.ArrayList;

public class AccountVerificationNonBookingActivity extends AirActivity implements ProvideIdListener {
    private static final String EXTRA_ENTRY_POINT = "extra_entry_point";
    private static final String EXTRA_VERIFICATIONS = "extra_verifications";
    private static final String EXTRA_VERIFICATION_USER = "extra_verification_user";
    private static final int RC_PROVIDE_ID = 763;
    @State
    EntryPoint entryPoint;
    @State
    ArrayList<AccountVerification> incompleteVerifications;
    @State
    User verificationUser;

    public enum EntryPoint {
        SIGN_UP(AccountVerificationSignUpFragment.class.getCanonicalName());
        
        /* access modifiers changed from: private */
        public final String fragmentClassName;

        private EntryPoint(String fragmentClassName2) {
            this.fragmentClassName = fragmentClassName2;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6533R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.entryPoint = (EntryPoint) getIntent().getSerializableExtra(EXTRA_ENTRY_POINT);
            this.incompleteVerifications = getIntent().getParcelableArrayListExtra(EXTRA_VERIFICATIONS);
            this.verificationUser = (User) getIntent().getParcelableExtra("extra_verification_user");
            showFragment(FragmentBundler.make(Fragment.instantiate(this, this.entryPoint.fragmentClassName)).build(), C6533R.C6535id.content_container, FragmentTransitionType.SlideInFromSidePop, false);
        }
    }

    public void onProvideIdClick() {
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.NonBooking).incompleteVerifications(this.incompleteVerifications).verificationUser(this.verificationUser).build()), RC_PROVIDE_ID);
    }

    public void onCancelClick() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PROVIDE_ID) {
            finish();
        }
    }
}
