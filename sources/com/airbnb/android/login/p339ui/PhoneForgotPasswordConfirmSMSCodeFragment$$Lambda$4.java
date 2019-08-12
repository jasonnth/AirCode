package com.airbnb.android.login.p339ui;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$4 */
final /* synthetic */ class PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$4 implements Runnable {
    private final PhoneForgotPasswordConfirmSMSCodeFragment arg$1;
    private final Fragment arg$2;

    private PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$4(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment, Fragment fragment) {
        this.arg$1 = phoneForgotPasswordConfirmSMSCodeFragment;
        this.arg$2 = fragment;
    }

    public static Runnable lambdaFactory$(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment, Fragment fragment) {
        return new PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$4(phoneForgotPasswordConfirmSMSCodeFragment, fragment);
    }

    public void run() {
        ((TransparentActionBarActivity) this.arg$1.getActivity()).showFragment(this.arg$2, FragmentTransitionType.FadeInAndOut, true);
    }
}
