package com.airbnb.android.login.p339ui;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Lambda$4 */
final /* synthetic */ class PhoneResetPasswordFragment$$Lambda$4 implements Runnable {
    private final PhoneResetPasswordFragment arg$1;

    private PhoneResetPasswordFragment$$Lambda$4(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        this.arg$1 = phoneResetPasswordFragment;
    }

    public static Runnable lambdaFactory$(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        return new PhoneResetPasswordFragment$$Lambda$4(phoneResetPasswordFragment);
    }

    public void run() {
        this.arg$1.getActivity().finish();
    }
}
