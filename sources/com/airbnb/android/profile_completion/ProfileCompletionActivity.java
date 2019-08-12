package com.airbnb.android.profile_completion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.Listener;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.profile_completion.ProfileCompletionEpoxyController.OnClickIncompleteStepListener;
import com.airbnb.android.profile_completion.ProfileCompletionManager.ProfileCompletionListener;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import com.airbnb.android.profile_completion.edit_about_me.EditAboutMeActivity;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import java.util.ArrayList;

public class ProfileCompletionActivity extends AirActivity implements Listener, OnClickIncompleteStepListener, ProfileCompletionListener {
    private static final int RC_ADD_PAYMENT_METHOD = 102;
    private static final int RC_EDIT_ABOUT_ME = 101;
    private static final int RC_VERIFICATION = 103;
    private FetchIdentityController fetchIdentityController;
    @BindView
    LoaderFrame loaderFrame;
    private ProfileCompletionEpoxyController profileCompletionEpoxyController;
    ProfileCompletionJitneyLogger profileCompletionJitneyLogger;
    ProfileCompletionManager profileCompletionManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, ProfileCompletionActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ProfileCompletionGraph) CoreApplication.instance(this).component()).inject(this);
        setContentView(C7646R.layout.activity_profile_completion);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.profileCompletionManager.addUpdateListener(this);
        this.fetchIdentityController = new FetchIdentityController(this.requestManager, (Listener) this, VerificationFlow.Booking, savedInstanceState);
        this.profileCompletionEpoxyController = new ProfileCompletionEpoxyController(this, this.profileCompletionManager, this);
        this.recyclerView.setAdapter(this.profileCompletionEpoxyController.getAdapter());
        this.profileCompletionEpoxyController.requestModelBuild();
        if (savedInstanceState == null) {
            this.profileCompletionJitneyLogger.logProfileCompletionPageImpression(this.profileCompletionManager);
        }
    }

    public void onClick(CompletionStep step) {
        if (NetworkUtil.isConnected(this)) {
            this.profileCompletionJitneyLogger.logStepStart(step, this.profileCompletionManager);
            switch (step) {
                case Verificaton:
                    launchVerification();
                    return;
                case AddPaymentMethod:
                    launchAddPaymentMethod();
                    return;
                case CompleteAboutMe:
                    launchEditAboutMe();
                    return;
                default:
                    return;
            }
        } else {
            new SnackbarWrapper().view(this.recyclerView).title(C0716R.string.error, true).body(NetworkUtil.getGenericNetworkError(this)).duration(-2).action(C0716R.string.retry, ProfileCompletionActivity$$Lambda$1.lambdaFactory$(this, step)).buildAndShow();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.fetchIdentityController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                this.profileCompletionJitneyLogger.fetchStatusAndLogStepResult(CompletionStep.CompleteAboutMe, this.profileCompletionManager);
                break;
            case 102:
                this.profileCompletionJitneyLogger.fetchStatusAndLogStepResult(CompletionStep.AddPaymentMethod, this.profileCompletionManager);
                break;
            case 103:
                this.profileCompletionJitneyLogger.fetchStatusAndLogStepResult(CompletionStep.Verificaton, this.profileCompletionManager);
                break;
        }
        this.loaderFrame.startAnimation();
    }

    private void launchVerification() {
        this.loaderFrame.startAnimation();
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
    }

    private void launchAddPaymentMethod() {
        startActivityForResult(LegacyAddPaymentMethodActivity.intentForProfileCompletion(this), 102);
    }

    private void launchEditAboutMe() {
        startActivityForResult(EditAboutMeActivity.newIntent(this), 101);
    }

    public void onVerificationsFetchComplete(ArrayList<AccountVerification> incompleteVerifications) {
        this.loaderFrame.finish();
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this, AccountVerificationStartFragmentArguments.builder().verificationUser(this.fetchIdentityController.getVerificationUser()).verificationFlow(VerificationFlow.ProfileCompletion).incompleteVerifications(incompleteVerifications).build()), 103);
    }

    public void onVerificationsFetchError(NetworkException e) {
        this.loaderFrame.finish();
        NetworkUtil.tryShowErrorWithSnackbar(this.recyclerView, e);
    }

    public void onFetchStatusSuccess(boolean completedStepsChanged) {
        if (completedStepsChanged) {
            this.profileCompletionEpoxyController.requestModelBuild();
        }
        this.loaderFrame.finish();
    }

    public void onFetchStatusError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.recyclerView, e);
        this.loaderFrame.finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.profileCompletionManager.removeUpdateListener(this);
        super.onDestroy();
    }
}
