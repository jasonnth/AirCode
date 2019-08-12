package com.airbnb.android.lib.china5a.phone;

import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.lib.china5a.BaseVerificationPresenter;
import com.airbnb.android.lib.china5a.VerificationResponse;

public class PhoneVerificationPresenter extends BaseVerificationPresenter<PhoneVerificationModel, PhoneVerificationView> {
    public PhoneVerificationPresenter(PhoneVerificationModel model, PhoneVerificationView view) {
        super(model, view);
    }

    public void requestConfirmationCode(AirPhone airPhone) {
        ((PhoneVerificationView) this.view).showLoading(true);
        ((PhoneVerificationModel) this.model).requestConfirmationCode(airPhone);
    }

    public void verifyConfirmationCode(String code) {
        ((PhoneVerificationView) this.view).showLoading(true);
        ((PhoneVerificationModel) this.model).verifyConfirmationCode(code);
    }

    /* access modifiers changed from: protected */
    public void start() {
        super.start();
        this.f9513cs.add(((PhoneVerificationModel) this.model).getRequestConfirmationCodeResponse().subscribe(PhoneVerificationPresenter$$Lambda$1.lambdaFactory$(this)));
        this.f9513cs.add(((PhoneVerificationModel) this.model).getVerifyConfirmationCodeResponse().subscribe(PhoneVerificationPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$start$0(PhoneVerificationPresenter phoneVerificationPresenter, VerificationResponse resp) {
        boolean z = false;
        ((PhoneVerificationView) phoneVerificationPresenter.view).showLoading(false);
        PhoneVerificationView phoneVerificationView = (PhoneVerificationView) phoneVerificationPresenter.view;
        if (resp.data != null) {
            z = true;
        }
        phoneVerificationView.showRequestConfirmationCodeResult(z);
    }

    static /* synthetic */ void lambda$start$1(PhoneVerificationPresenter phoneVerificationPresenter, VerificationResponse resp) {
        boolean z;
        PhoneVerificationView phoneVerificationView = (PhoneVerificationView) phoneVerificationPresenter.view;
        if (resp.data != null) {
            z = true;
        } else {
            z = false;
        }
        phoneVerificationView.showVerifyConfirmationCodeResult(z);
        ((PhoneVerificationView) phoneVerificationPresenter.view).showLoading(false);
    }

    /* access modifiers changed from: protected */
    public String getStepName() {
        return "phone";
    }
}
