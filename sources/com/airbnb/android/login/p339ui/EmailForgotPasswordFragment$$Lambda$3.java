package com.airbnb.android.login.p339ui;

/* renamed from: com.airbnb.android.login.ui.EmailForgotPasswordFragment$$Lambda$3 */
final /* synthetic */ class EmailForgotPasswordFragment$$Lambda$3 implements Runnable {
    private final EmailForgotPasswordFragment arg$1;

    private EmailForgotPasswordFragment$$Lambda$3(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        this.arg$1 = emailForgotPasswordFragment;
    }

    public static Runnable lambdaFactory$(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        return new EmailForgotPasswordFragment$$Lambda$3(emailForgotPasswordFragment);
    }

    public void run() {
        this.arg$1.getActivity().finish();
    }
}
