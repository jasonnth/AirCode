package com.airbnb.android.lib.china5a.email;

import com.airbnb.android.lib.china5a.BaseVerificationPresenter;
import com.airbnb.android.lib.china5a.VerificationResponse;

public class EmailVerificationPresenter extends BaseVerificationPresenter<EmailVerificationModel, EmailVerificationView> {
    public EmailVerificationPresenter(EmailVerificationModel model, EmailVerificationView view) {
        super(model, view);
    }

    public void confirmEmail(String email) {
        ((EmailVerificationModel) this.model).confirmEmail(email);
    }

    public void startPollingVerificationStatus() {
        ((EmailVerificationModel) this.model).startPollingVerificationStatus();
    }

    public void stopPollingVerificationStatus() {
        ((EmailVerificationModel) this.model).stopPollingVerificationStatus();
    }

    /* access modifiers changed from: protected */
    public void start() {
        super.start();
        this.f9513cs.add(((EmailVerificationModel) this.model).getConfirmEmailResponse().subscribe(EmailVerificationPresenter$$Lambda$1.lambdaFactory$(this)));
        this.f9513cs.add(((EmailVerificationModel) this.model).getPollingVerificationResultResponse().subscribe(EmailVerificationPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$start$0(EmailVerificationPresenter emailVerificationPresenter, VerificationResponse resp) {
        ((EmailVerificationView) emailVerificationPresenter.view).showConfirmEmailResult(resp.data != null);
    }

    static /* synthetic */ void lambda$start$1(EmailVerificationPresenter emailVerificationPresenter, VerificationResponse resp) {
        ((EmailVerificationView) emailVerificationPresenter.view).showVerificationResult(resp.data != null);
    }

    /* access modifiers changed from: protected */
    public String getStepName() {
        return "mail";
    }
}
