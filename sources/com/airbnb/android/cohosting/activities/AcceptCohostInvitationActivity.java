package com.airbnb.android.cohosting.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostInvitationDataController;
import com.airbnb.android.cohosting.executors.CohostInvitationActionExecutor;
import com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationErrorFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationExpiredFragment;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.constants.NetworkConstants;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.requests.CohostInvitationRequest;
import com.airbnb.android.core.responses.CohostInvitationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;
import icepick.State;
import p032rx.Observer;

public class AcceptCohostInvitationActivity extends CohostingBaseActivity {
    private final CohostInvitationActionExecutor actionExecutor = AcceptCohostInvitationActivity$$Lambda$3.lambdaFactory$(this);
    final RequestListener<CohostInvitationResponse> cohostInvitationListener = new C0699RL().onResponse(AcceptCohostInvitationActivity$$Lambda$1.lambdaFactory$(this)).onError(AcceptCohostInvitationActivity$$Lambda$2.lambdaFactory$(this)).build();
    private CohostInvitationDataController controller;
    @BindView
    RefreshLoader fullLoader;
    @State
    String invitationCode;
    @State
    long invitationId;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(AcceptCohostInvitationActivity acceptCohostInvitationActivity, CohostInvitationResponse response) {
        acceptCohostInvitationActivity.toolbar.setVisibility(8);
        acceptCohostInvitationActivity.fullLoader.setVisibility(8);
        CohostInvitation invitation = response.cohostInvitation;
        acceptCohostInvitationActivity.controller.setCohostInvitation(invitation);
        if (invitation.isIsExpired().booleanValue()) {
            acceptCohostInvitationActivity.showFragment(CohostingInvitationExpiredFragment.newInstance());
        } else if (invitation.getInviter().equals(acceptCohostInvitationActivity.accountManager.getCurrentUser())) {
            acceptCohostInvitationActivity.showFragment(CohostingInvitationErrorFragment.newInstanceForSelfInvitation());
        } else {
            acceptCohostInvitationActivity.showFragment(AcceptCohostInvitationFragment.create());
        }
    }

    static /* synthetic */ void lambda$new$1(AcceptCohostInvitationActivity acceptCohostInvitationActivity, AirRequestNetworkException e) {
        acceptCohostInvitationActivity.toolbar.setVisibility(8);
        acceptCohostInvitationActivity.fullLoader.setVisibility(8);
        switch (e.statusCode()) {
            case 403:
                acceptCohostInvitationActivity.showFragment(CohostingInvitationErrorFragment.newInstanceForEmailMismatch());
                return;
            case NetworkConstants.STATUS_CODE_NOT_FOUND /*404*/:
                acceptCohostInvitationActivity.showFragment(CohostingInvitationErrorFragment.newInstanceForInvalidInvitation());
                return;
            default:
                NetworkUtil.tryShowErrorWithSnackbar(acceptCohostInvitationActivity.findViewById(C5658R.C5660id.root_container), e);
                return;
        }
    }

    public static Intent intentForInvitationCode(Context context, String code) {
        return new Intent(context, AcceptCohostInvitationActivity.class).putExtra("invite_code", code);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.invitationCode = getIntent().getStringExtra("invite_code");
        this.controller = new CohostInvitationDataController(this, this.actionExecutor, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_simple_fragment_with_toolbar);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(AcceptCohostInvitationActivity$$Lambda$4.lambdaFactory$(this));
        ((CohostingGraph) CoreApplication.instance(this).component()).inject(this);
        if (savedInstanceState == null) {
            fetchData();
        }
    }

    public CohostInvitationDataController getCohostInvitationDataController() {
        return this.controller;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.controller = null;
    }

    private void fetchData() {
        this.controller.setLoading(true);
        this.fullLoader.setVisibility(0);
        CohostInvitationRequest.forInvitationCode(this.invitationCode).withListener((Observer) this.cohostInvitationListener).execute(this.requestManager);
    }
}
