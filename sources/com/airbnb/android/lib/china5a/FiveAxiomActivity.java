package com.airbnb.android.lib.china5a;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.intents.FiveAxiomIntents;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.china5a.fragments.EmailVerificationFragment;
import com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment;
import com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment;
import com.airbnb.android.lib.china5a.fragments.VerificationCompleteFragment;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetProgressBar;
import icepick.State;
import java.util.ArrayList;

public class FiveAxiomActivity extends AirActivity {
    @State
    int currentStepIndex = -1;
    @State
    VerificationFlow flow;
    @State
    User host;
    @State
    boolean isCompleted;
    private boolean isOnRestore;
    @BindView
    SheetProgressBar progressBar;
    private FiveAxiomRepository repository;
    @State
    ArrayList<AccountVerificationStep> steps;
    @BindView
    AirToolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_five_axiom);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.host = (User) getIntent().getParcelableExtra("extra_host");
            this.flow = (VerificationFlow) getIntent().getSerializableExtra(FiveAxiomIntents.EXTRA_FLOW);
            this.steps = getIntent().getParcelableArrayListExtra(FiveAxiomIntents.EXTRA_STEPS);
        }
        this.isOnRestore = savedInstanceState != null;
        this.repository = new FiveAxiomRepoImpl(this, this.requestManager, this.steps, this.flow, savedInstanceState);
        this.repository.getFlowModel().getCurrentStep().doOnSubscribe(FiveAxiomActivity$$Lambda$1.lambdaFactory$(this)).subscribe(FiveAxiomActivity$$Lambda$2.lambdaFactory$(this), FiveAxiomActivity$$Lambda$3.lambdaFactory$(this), FiveAxiomActivity$$Lambda$4.lambdaFactory$(this));
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setNavigationOnClickListener(FiveAxiomActivity$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: 0000 */
    public FiveAxiomRepository getRepository() {
        return this.repository;
    }

    /* access modifiers changed from: private */
    public void onSubscribe() {
        if (!this.isOnRestore) {
            FiveAxiomAnalytics.trackFlowStart();
        }
    }

    /* access modifiers changed from: private */
    public void onNext(AccountVerificationStep step) {
        Fragment fragment;
        FragmentTransitionType transitionType;
        if (this.isOnRestore) {
            this.isOnRestore = false;
            updateProgressBar();
            return;
        }
        this.currentStepIndex++;
        updateProgressBar();
        AccountVerificationStep prevStep = this.currentStepIndex > 0 ? (AccountVerificationStep) this.steps.get(this.currentStepIndex - 1) : null;
        switch (step) {
            case ProfilePhoto:
                fragment = new PhotoVerificationFragment();
                transitionType = FragmentTransitionType.SlideInFromSide;
                break;
            case Phone:
                if (prevStep != AccountVerificationStep.Email) {
                    fragment = PhoneVerificationFragment.newInstance(false);
                    transitionType = FragmentTransitionType.SlideInFromSide;
                    break;
                } else {
                    fragment = PhoneVerificationFragment.newInstance(true);
                    transitionType = FragmentTransitionType.None;
                    break;
                }
            case Email:
                if (prevStep != AccountVerificationStep.Phone) {
                    fragment = EmailVerificationFragment.newInstance(false);
                    transitionType = FragmentTransitionType.SlideInFromSide;
                    break;
                } else {
                    fragment = EmailVerificationFragment.newInstance(true);
                    transitionType = FragmentTransitionType.None;
                    break;
                }
            default:
                throw new IllegalArgumentException(step + " is not supported!");
        }
        showFragment(fragment, C0880R.C0882id.content_container, transitionType, false);
    }

    /* access modifiers changed from: private */
    public void onComplete() {
        FiveAxiomAnalytics.trackFlowFinished();
        this.progressBar.setVisibility(8);
        this.toolbar.setVisibility(8);
        this.isCompleted = true;
        if (this.isOnRestore) {
            this.isOnRestore = false;
        } else {
            showFragment(VerificationCompleteFragment.newInstance(this.flow, this.host), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, false);
        }
    }

    /* access modifiers changed from: private */
    public void onError(Throwable error) {
        FiveAxiomAnalytics.trackFlowCancelled();
        finish();
    }

    private void updateProgressBar() {
        if (this.currentStepIndex >= 0) {
            this.progressBar.setProgress(Math.max(((float) this.currentStepIndex) / ((float) this.steps.size()), 0.02f));
        }
    }

    public void finish() {
        setResult(this.isCompleted ? -1 : 0);
        super.finish();
    }
}
