package com.airbnb.android.login.p339ui;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Lambda$5 */
final /* synthetic */ class PhoneForgotPasswordFragment$$Lambda$5 implements Runnable {
    private final PhoneForgotPasswordFragment arg$1;
    private final Fragment arg$2;

    private PhoneForgotPasswordFragment$$Lambda$5(PhoneForgotPasswordFragment phoneForgotPasswordFragment, Fragment fragment) {
        this.arg$1 = phoneForgotPasswordFragment;
        this.arg$2 = fragment;
    }

    public static Runnable lambdaFactory$(PhoneForgotPasswordFragment phoneForgotPasswordFragment, Fragment fragment) {
        return new PhoneForgotPasswordFragment$$Lambda$5(phoneForgotPasswordFragment, fragment);
    }

    public void run() {
        ((TransparentActionBarActivity) this.arg$1.getActivity()).showFragment(this.arg$2, FragmentTransitionType.FadeInAndOut, true);
    }
}
