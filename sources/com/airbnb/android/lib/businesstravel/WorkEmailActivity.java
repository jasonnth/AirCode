package com.airbnb.android.lib.businesstravel;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment.VerificationStatus;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment.VerifyWorkEmailListener;
import com.airbnb.android.lib.businesstravel.WorkEmailFragment.WorkEmailListener;
import icepick.State;

public class WorkEmailActivity extends AirActivity implements VerifyWorkEmailListener, WorkEmailDataController, WorkEmailListener {
    BusinessTravelAccountManager businessTravelAccountManager;
    @State
    String confirmationCode;
    @State
    WorkEmailLaunchSource launchSource;
    @State
    String workEmail;
    @State
    WorkEmailStatus workEmailStatus;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            showWorkEmailFragment();
        }
    }

    public void doneWithAddWorkEmail(BusinessTravelEmployee businessTravelEmployee) {
        showFragment(VerifyWorkEmailFragment.forWorkEmailStatus(businessTravelEmployee.getEmail(), VerificationStatus.NOT_VERIFIED), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, C0880R.anim.exit_bottom);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    private void showWorkEmailFragment() {
        Intent intent = getIntent();
        this.launchSource = (WorkEmailLaunchSource) intent.getSerializableExtra(BusinessTravelIntents.EXTRA_LAUNCH_SOURCE);
        this.confirmationCode = intent.getStringExtra("extra_confirmation_code");
        this.workEmail = this.businessTravelAccountManager.getWorkEmail();
        this.workEmailStatus = this.businessTravelAccountManager.getWorkEmailStatus();
        switch (this.workEmailStatus) {
            case Pending:
                showFragment(VerifyWorkEmailFragment.forWorkEmailStatus(this.workEmail, VerificationStatus.PENDING_VERIFICATION), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
                return;
            default:
                showFragment(WorkEmailFragment.instanceForEmail(this.workEmail), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
                return;
        }
    }

    public String getReservationConfirmationCode() {
        return this.confirmationCode;
    }

    public WorkEmailLaunchSource getLaunchSource() {
        return this.launchSource;
    }

    public void goToAddWorkEmail() {
        showFragment(WorkEmailFragment.instanceForEmail(this.workEmail), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }
}
