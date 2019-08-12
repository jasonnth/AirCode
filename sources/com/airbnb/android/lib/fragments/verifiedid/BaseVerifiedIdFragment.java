package com.airbnb.android.lib.fragments.verifiedid;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.CircleBadgeView;

public abstract class BaseVerifiedIdFragment extends AirFragment {
    private static final int SHOW_BADGE_BEFORE_TRANSITION_DURATION = 2000;
    private static final int SHOW_BADGE_DELAY = 500;

    public interface AnimationCompletionListener {
        void didCompleteAnimation();
    }

    public abstract CircleBadgeView getCircleBadgeView();

    public abstract int getContentFragmentId();

    public abstract String getVerificationSuccessDescription();

    public abstract void setActiveState();

    public abstract void setPendingState();

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    @SuppressLint({"NewApi"})
    public void animateCompletion(String verificationHeader, String verificationDescription) {
        Handler animationHandler = new Handler();
        animationHandler.postDelayed(BaseVerifiedIdFragment$$Lambda$1.lambdaFactory$(this, verificationHeader, verificationDescription), 500);
        animationHandler.postDelayed(BaseVerifiedIdFragment$$Lambda$2.lambdaFactory$(this), 2000);
    }

    static /* synthetic */ void lambda$animateCompletion$0(BaseVerifiedIdFragment baseVerifiedIdFragment, String verificationHeader, String verificationDescription) {
        baseVerifiedIdFragment.getCircleBadgeView().animateActivation(true);
        if (baseVerifiedIdFragment.isResumed()) {
            baseVerifiedIdFragment.showConfirmedFragment(verificationHeader, verificationDescription);
        }
    }

    static /* synthetic */ void lambda$animateCompletion$1(BaseVerifiedIdFragment baseVerifiedIdFragment) {
        AnimationCompletionListener listener = (AnimationCompletionListener) baseVerifiedIdFragment.getActivity();
        if (listener != null && baseVerifiedIdFragment.isResumed()) {
            listener.didCompleteAnimation();
        }
    }

    private void showConfirmedFragment(String verificationHeader, String verificationDescription) {
        Fragment confirmationFragment = ConfirmedVerificationFragment.newInstance(verificationHeader, verificationDescription);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
        ft.replace(getContentFragmentId(), confirmationFragment);
        ft.commit();
    }
}
