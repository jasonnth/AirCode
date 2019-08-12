package com.airbnb.android.lib.fragments.completeprofile;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.widget.ScrollView;
import com.airbnb.android.core.enums.CompleteProfileType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.fragments.verifiedid.ConfirmedVerificationFragment;
import com.airbnb.android.lib.views.CircleBadgeView;

public class CompleteProfileBaseFragment extends AirFragment {
    private static final int SCROLL_DURATION = 500;
    private static final int SCROLL_INTERVALS = 10;
    private static final int SHOW_BADGE_BEFORE_TRANSITION_DURATION = 3000;

    /* access modifiers changed from: protected */
    public CompleteProfileActivity getCompleteProfileActivity() {
        return (CompleteProfileActivity) getActivity();
    }

    /* access modifiers changed from: protected */
    public boolean isVerifiedIdFlow() {
        return getCompleteProfileActivity().isVerifiedIdFlow();
    }

    /* access modifiers changed from: protected */
    public boolean isEditProfileFlow() {
        CompleteProfileType type = getCompleteProfileActivity().getType();
        return type != null && type.isEditProfileType();
    }

    public void animateCompletion(String headerText, String descriptionText) {
        final ScrollView scrollView = (ScrollView) getView().findViewById(C0880R.C0882id.complete_profile_scrollView);
        if (scrollView == null || scrollView.getScrollY() <= 0) {
            showConfirmation(headerText, descriptionText);
            return;
        }
        final String str = headerText;
        final String str2 = descriptionText;
        new CountDownTimer(500, 10) {
            public void onTick(long millisUntilFinished) {
                scrollView.scrollBy(0, -(scrollView.getScrollY() / ((int) (millisUntilFinished / 10))));
            }

            public void onFinish() {
                scrollView.scrollTo(0, 0);
                CompleteProfileBaseFragment.this.showConfirmation(str, str2);
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public void showChildFragment(Fragment fragment) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
        ft.replace(C0880R.C0882id.complete_profile_child_fragment, fragment);
        ft.addToBackStack(getTag());
        ft.commit();
    }

    /* access modifiers changed from: private */
    public void showConfirmation(String headerText, String descriptionText) {
        ((CircleBadgeView) getView().findViewById(C0880R.C0882id.circle_badge_view)).animateActivation(true);
        if (isResumed()) {
            showChildFragment(ConfirmedVerificationFragment.newInstance(headerText, descriptionText));
        }
        new Handler().postDelayed(CompleteProfileBaseFragment$$Lambda$1.lambdaFactory$(this), 3000);
    }

    static /* synthetic */ void lambda$showConfirmation$0(CompleteProfileBaseFragment completeProfileBaseFragment) {
        CompleteProfileActivity completeProfileActivity = completeProfileBaseFragment.getCompleteProfileActivity();
        if (completeProfileActivity != null && completeProfileBaseFragment.isResumed()) {
            completeProfileActivity.updateVerificationState();
            completeProfileActivity.requestVerification();
        }
    }
}
