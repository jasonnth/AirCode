package com.airbnb.android.lib.china5a;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.requests.ConfirmEmailRequest;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.lib.china5a.email.EmailVerificationModel;
import com.airbnb.android.lib.china5a.phone.PhoneVerificationModel;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationModel;
import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;
import com.airbnb.rxgroups.RequestSubscription;
import icepick.State;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.subjects.BehaviorSubject;

public final class FiveAxiomRepoImpl implements FiveAxiomRepository {
    private final EmailModel emailModel;
    private final FlowModel flowModel;
    private final PhoneModel phoneModel;
    private final PhotoModel photoModel;

    public static class EmailModel extends StepModel implements EmailVerificationModel {
        private static final long POLLING_INTERVAL_MS = 1000;
        final RequestListener<BaseResponse> confirmEmailRequestListener = new C0699RL().onResponse(FiveAxiomRepoImpl$EmailModel$$Lambda$1.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$EmailModel$$Lambda$2.lambdaFactory$(this)).build();
        private final BehaviorSubject<VerificationResponse<BaseResponse>> confirmEmailSubject = BehaviorSubject.create();
        private final NonResubscribableRequestListener<AccountVerificationsResponse> emailPollingRequestListener = new C0699RL().onResponse(FiveAxiomRepoImpl$EmailModel$$Lambda$3.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$EmailModel$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
        private boolean isPolling;
        private final RequestManager requestManager;
        private RequestSubscription subscription;
        private Subscription timerSubscription;
        private final VerificationFlow verificationFlow;
        private final BehaviorSubject<VerificationResponse<AccountVerificationsResponse>> verificationResultSubject = BehaviorSubject.create();

        EmailModel(RequestManager requestManager2, VerificationFlow verificationFlow2, FlowModel flowModel) {
            super(flowModel);
            this.requestManager = requestManager2;
            this.verificationFlow = verificationFlow2;
            requestManager2.subscribe(this);
        }

        public void confirmEmail(String email) {
            ConfirmEmailRequest.newInstance(email).withListener(this.confirmEmailRequestListener).execute(this.requestManager);
        }

        public void startPollingVerificationStatus() {
            if (!this.isPolling) {
                this.isPolling = true;
                queryVerificationResult();
            }
        }

        public void stopPollingVerificationStatus() {
            if (this.isPolling) {
                this.isPolling = false;
                if (this.subscription != null && !this.subscription.isCancelled()) {
                    this.subscription.cancel();
                }
                if (this.timerSubscription != null && !this.timerSubscription.isUnsubscribed()) {
                    this.timerSubscription.unsubscribe();
                }
            }
        }

        public Observable<VerificationResponse<BaseResponse>> getConfirmEmailResponse() {
            return this.confirmEmailSubject;
        }

        public Observable<VerificationResponse<AccountVerificationsResponse>> getPollingVerificationResultResponse() {
            return this.verificationResultSubject;
        }

        private boolean checkEmailPollingResponse(AccountVerificationsResponse response) {
            AccountVerification emailVerification = response.getAccountVerification("email");
            return emailVerification != null && emailVerification.isComplete();
        }

        /* access modifiers changed from: private */
        public void queryVerificationResult() {
            this.subscription = AccountVerificationsRequest.forFlow(this.verificationFlow).withListener((Observer) this.emailPollingRequestListener).execute(this.requestManager);
        }

        /* access modifiers changed from: private */
        public void queryVerificationResultDelay() {
            this.timerSubscription = Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(FiveAxiomRepoImpl$EmailModel$$Lambda$5.lambdaFactory$(this), FiveAxiomRepoImpl$EmailModel$$Lambda$6.lambdaFactory$());
        }

        static /* synthetic */ void lambda$queryVerificationResultDelay$1(Throwable error) {
        }

        static /* synthetic */ void lambda$new$2(EmailModel emailModel, BaseResponse response) {
            emailModel.confirmEmailSubject.onNext(new VerificationResponse(response, null));
            FiveAxiomAnalytics.trackSuccess("request_verification_mail");
        }

        static /* synthetic */ void lambda$new$3(EmailModel emailModel, AirRequestNetworkException e) {
            emailModel.confirmEmailSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("request_verification_mail");
        }

        static /* synthetic */ void lambda$new$4(EmailModel emailModel, AccountVerificationsResponse response) {
            if (emailModel.checkEmailPollingResponse(response)) {
                FiveAxiomAnalytics.trackSuccess("poll_mail_verification_result");
                emailModel.isPolling = false;
                emailModel.verificationResultSubject.onNext(new VerificationResponse(response, null));
                return;
            }
            emailModel.queryVerificationResultDelay();
        }
    }

    public class EmailModel_ObservableResubscriber extends BaseObservableResubscriber {
        public EmailModel_ObservableResubscriber(EmailModel target, ObservableGroup group) {
            setTag((AutoTaggableObserver) target.confirmEmailRequestListener, "EmailModel_confirmEmailRequestListener");
            group.resubscribeAll(target.confirmEmailRequestListener);
        }
    }

    public final class FlowModel implements VerificationFlowModel {
        @State
        int curStepIdx = 0;
        private final BehaviorSubject<AccountVerificationStep> stepSubject;
        private final List<AccountVerificationStep> steps;

        FlowModel(List<AccountVerificationStep> steps2, Bundle savedState) {
            IcepickWrapper.restoreInstanceState(this, savedState);
            this.steps = steps2;
            this.stepSubject = BehaviorSubject.create(steps2.get(this.curStepIdx));
        }

        public Observable<AccountVerificationStep> getCurrentStep() {
            return this.stepSubject.asObservable();
        }

        /* access modifiers changed from: 0000 */
        public void next() {
            if (!this.stepSubject.hasCompleted()) {
                if (this.curStepIdx == this.steps.size() - 1) {
                    this.stepSubject.onCompleted();
                    return;
                }
                this.curStepIdx++;
                this.stepSubject.onNext(this.steps.get(this.curStepIdx));
            }
        }

        /* access modifiers changed from: 0000 */
        public void exit() {
            if (!this.stepSubject.hasCompleted()) {
                this.stepSubject.onError(new Throwable());
            }
        }
    }

    public static class PhoneModel extends StepModel implements PhoneVerificationModel {
        final RequestListener<Object> requestConfirmationCodeListener = new C0699RL().onResponse(FiveAxiomRepoImpl$PhoneModel$$Lambda$1.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$PhoneModel$$Lambda$2.lambdaFactory$(this)).build();
        private final BehaviorSubject<VerificationResponse<Object>> requestConfirmationCodeSubject = BehaviorSubject.create();
        private final RequestManager requestManager;
        final RequestListener<Object> veirfyConfirmationCodeListener = new C0699RL().onResponse(FiveAxiomRepoImpl$PhoneModel$$Lambda$3.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$PhoneModel$$Lambda$4.lambdaFactory$(this)).build();
        private final BehaviorSubject<VerificationResponse<Object>> verifyConfirmationCodeSubject = BehaviorSubject.create();

        PhoneModel(RequestManager requestManager2, FlowModel flowModel) {
            super(flowModel);
            this.requestManager = requestManager2;
            requestManager2.subscribe(this);
        }

        public void requestConfirmationCode(AirPhone airPhone) {
            UpdatePhoneNumberRequest.requestConfirmationCode(airPhone.formattedPhone()).withListener(this.requestConfirmationCodeListener).execute(this.requestManager);
        }

        public void verifyConfirmationCode(String code) {
            UpdatePhoneNumberRequest.verifyPhoneNumber(code).withListener(this.veirfyConfirmationCodeListener).execute(this.requestManager);
        }

        public Observable<VerificationResponse<Object>> getRequestConfirmationCodeResponse() {
            return this.requestConfirmationCodeSubject.asObservable();
        }

        public Observable<VerificationResponse<Object>> getVerifyConfirmationCodeResponse() {
            return this.verifyConfirmationCodeSubject.asObservable();
        }

        static /* synthetic */ void lambda$new$0(PhoneModel phoneModel, Object response) {
            phoneModel.requestConfirmationCodeSubject.onNext(new VerificationResponse(response, null));
            FiveAxiomAnalytics.trackSuccess("request_sms_code");
        }

        static /* synthetic */ void lambda$new$1(PhoneModel phoneModel, AirRequestNetworkException e) {
            phoneModel.requestConfirmationCodeSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("request_sms_code");
        }

        static /* synthetic */ void lambda$new$2(PhoneModel phoneModel, Object response) {
            phoneModel.verifyConfirmationCodeSubject.onNext(new VerificationResponse(response, null));
            FiveAxiomAnalytics.trackSuccess("verifiy_sms_code");
        }

        static /* synthetic */ void lambda$new$3(PhoneModel phoneModel, AirRequestNetworkException e) {
            phoneModel.verifyConfirmationCodeSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("verifiy_sms_code");
        }
    }

    public class PhoneModel_ObservableResubscriber extends BaseObservableResubscriber {
        public PhoneModel_ObservableResubscriber(PhoneModel target, ObservableGroup group) {
            setTag((AutoTaggableObserver) target.requestConfirmationCodeListener, "PhoneModel_requestConfirmationCodeListener");
            group.resubscribeAll(target.requestConfirmationCodeListener);
            setTag((AutoTaggableObserver) target.veirfyConfirmationCodeListener, "PhoneModel_veirfyConfirmationCodeListener");
            group.resubscribeAll(target.veirfyConfirmationCodeListener);
        }
    }

    public static class PhotoModel extends StepModel implements PhotoVerificationModel {
        private final Context context;
        private final BehaviorSubject<VerificationResponse<UserWrapperResponse>> forceUploadPhotoSubject = BehaviorSubject.create();
        final RequestListener<UserWrapperResponse> forceUploadReuqestListener = new C0699RL().onResponse(FiveAxiomRepoImpl$PhotoModel$$Lambda$3.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$PhotoModel$$Lambda$4.lambdaFactory$(this)).build();
        private final RequestManager requestManager;
        final RequestListener<UserWrapperResponse> uploadPhotoRequestListener = new C0699RL().onResponse(FiveAxiomRepoImpl$PhotoModel$$Lambda$1.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$PhotoModel$$Lambda$2.lambdaFactory$(this)).build();
        private final BehaviorSubject<VerificationResponse<UserWrapperResponse>> uploadPhotoSubject = BehaviorSubject.create();
        private final VerificationFlow verificationFlow;
        final RequestListener<AccountVerificationsResponse> verificationsRequestListener = new C0699RL().onResponse(FiveAxiomRepoImpl$PhotoModel$$Lambda$5.lambdaFactory$(this)).onError(FiveAxiomRepoImpl$PhotoModel$$Lambda$6.lambdaFactory$(this)).build();
        private final BehaviorSubject<VerificationResponse<AccountVerificationsResponse>> verifyPhotoSubject = BehaviorSubject.create();

        PhotoModel(Context context2, RequestManager requestManager2, VerificationFlow verificationFlow2, FlowModel flowModel) {
            super(flowModel);
            this.requestManager = requestManager2;
            this.context = context2;
            this.verificationFlow = verificationFlow2;
            requestManager2.subscribe(this);
        }

        public void uploadPhoto(String filePath, boolean enableFaceDetection) {
            File profilePhotoFile = new File(filePath);
            if (enableFaceDetection) {
                SetProfilePhotoRequest.newRequestWithFaceDetection(this.context, profilePhotoFile).withListener(this.uploadPhotoRequestListener).execute(this.requestManager);
            } else {
                SetProfilePhotoRequest.newRequestWithoutFaceDetection(this.context, profilePhotoFile).withListener(this.forceUploadReuqestListener).execute(this.requestManager);
            }
        }

        public void verifyPhoto() {
            AccountVerificationsRequest.forFlow(this.verificationFlow).withListener((Observer) this.verificationsRequestListener).execute(this.requestManager);
        }

        public Observable<VerificationResponse<UserWrapperResponse>> getUploadPhotoResponse(boolean enableFaceDetection) {
            if (enableFaceDetection) {
                return this.uploadPhotoSubject.asObservable();
            }
            return this.forceUploadPhotoSubject.asObservable();
        }

        public Observable<VerificationResponse<AccountVerificationsResponse>> getVerifyPhotoResponse() {
            return this.verifyPhotoSubject.asObservable();
        }

        static /* synthetic */ void lambda$new$0(PhotoModel photoModel, UserWrapperResponse data) {
            photoModel.uploadPhotoSubject.onNext(new VerificationResponse(data, null));
            FiveAxiomAnalytics.trackSuccess("upload_photo");
        }

        static /* synthetic */ void lambda$new$1(PhotoModel photoModel, AirRequestNetworkException e) {
            photoModel.uploadPhotoSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("upload_photo");
        }

        static /* synthetic */ void lambda$new$2(PhotoModel photoModel, UserWrapperResponse data) {
            photoModel.forceUploadPhotoSubject.onNext(new VerificationResponse(data, null));
            FiveAxiomAnalytics.trackSuccess("force_upload_photo");
        }

        static /* synthetic */ void lambda$new$3(PhotoModel photoModel, AirRequestNetworkException e) {
            photoModel.forceUploadPhotoSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("force_upload_photo");
        }

        static /* synthetic */ void lambda$new$4(PhotoModel photoModel, AccountVerificationsResponse data) {
            photoModel.verifyPhotoSubject.onNext(new VerificationResponse(data, null));
            FiveAxiomAnalytics.trackSuccess("fetch_existing_photo");
        }

        static /* synthetic */ void lambda$new$5(PhotoModel photoModel, AirRequestNetworkException e) {
            photoModel.verifyPhotoSubject.onNext(new VerificationResponse(null, e));
            FiveAxiomAnalytics.trackFailure("fetch_existing_photo");
        }
    }

    public class PhotoModel_ObservableResubscriber extends BaseObservableResubscriber {
        public PhotoModel_ObservableResubscriber(PhotoModel target, ObservableGroup group) {
            setTag((AutoTaggableObserver) target.uploadPhotoRequestListener, "PhotoModel_uploadPhotoRequestListener");
            group.resubscribeAll(target.uploadPhotoRequestListener);
            setTag((AutoTaggableObserver) target.forceUploadReuqestListener, "PhotoModel_forceUploadReuqestListener");
            group.resubscribeAll(target.forceUploadReuqestListener);
            setTag((AutoTaggableObserver) target.verificationsRequestListener, "PhotoModel_verificationsRequestListener");
            group.resubscribeAll(target.verificationsRequestListener);
        }
    }

    public static abstract class StepModel implements VerificationStepModel {
        protected final FlowModel flowModel;

        public StepModel(FlowModel flowModel2) {
            this.flowModel = flowModel2;
        }

        public void finish() {
            this.flowModel.next();
        }

        public void cancel() {
            this.flowModel.exit();
        }
    }

    public FiveAxiomRepoImpl(Context context, RequestManager requestManager, List<AccountVerificationStep> steps, VerificationFlow verificationFlow, Bundle savedState) {
        this.flowModel = new FlowModel(steps, savedState);
        this.photoModel = new PhotoModel(context, requestManager, verificationFlow, this.flowModel);
        this.phoneModel = new PhoneModel(requestManager, this.flowModel);
        this.emailModel = new EmailModel(requestManager, verificationFlow, this.flowModel);
    }

    public VerificationFlowModel getFlowModel() {
        return this.flowModel;
    }

    public PhotoVerificationModel getPhotoModel() {
        return this.photoModel;
    }

    public PhoneVerificationModel getPhoneModel() {
        return this.phoneModel;
    }

    public EmailVerificationModel getEmailModel() {
        return this.emailModel;
    }
}
