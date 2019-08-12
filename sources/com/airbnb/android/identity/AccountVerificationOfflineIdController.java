package com.airbnb.android.identity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.JumioCredential;
import com.airbnb.android.core.requests.GovernmentIdUploadRequest;
import com.airbnb.android.core.requests.GovernmentIdUploadRequest.Vendor;
import com.airbnb.android.core.requests.JumioCredentialsRequest;
import com.airbnb.android.core.requests.JumioScanReferenceUploadRequest;
import com.airbnb.android.core.requests.OfficialIdUploadRequest;
import com.airbnb.android.core.responses.GovernmentIdUploadResponse;
import com.airbnb.android.core.responses.JumioCredentialsResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.jumio.MobileSDK;
import com.jumio.core.enums.JumioDataCenter;
import com.jumio.core.exceptions.MissingPermissionException;
import com.jumio.core.exceptions.PlatformNotSupportedException;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.NetverifySDK;
import icepick.State;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import org.spongycastle.asn1.eac.CertificateBody;
import p032rx.Observer;

public class AccountVerificationOfflineIdController {
    private static final int DATE_LENGTH = 8;
    private static final String DEFAULT_COUNTRY_CODE = "USA";
    public static final byte PERMISSION_REQUEST_CODE_NETVERIFY = Byte.MAX_VALUE;
    /* access modifiers changed from: private */
    public static final String TAG = AccountVerificationOfflineIdController.class.getSimpleName();
    /* access modifiers changed from: private */
    public AccountVerificationController controller;
    @State
    String countryCode;
    private final VerificationFlow flow;
    /* access modifiers changed from: private */
    public AirFragment fragment;
    @State
    GovernmentIdType governmentIdType;
    private final Handler handler;
    @State
    JumioCredential jumioCredential;
    final RequestListener<JumioCredentialsResponse> jumioCredentialsResponseRequestListener = new C0699RL().onResponse(AccountVerificationOfflineIdController$$Lambda$3.lambdaFactory$(this)).onError(AccountVerificationOfflineIdController$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<BaseResponse> jumioScanReferenceUploadRequestListener = new C0699RL().onResponse(AccountVerificationOfflineIdController$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationOfflineIdController$$Lambda$2.lambdaFactory$(this)).build();
    @State
    File misnapCapturedBackPhoto;
    @State
    String misnapCapturedBarCode;
    @State
    File misnapCapturedPhoto;
    final RequestListener<GovernmentIdUploadResponse> misnapResultRequestListener = new RequestListener<GovernmentIdUploadResponse>() {
        public void onResponse(GovernmentIdUploadResponse response) {
            C0715L.m1189d(AccountVerificationOfflineIdController.TAG, "Successful upload: " + AccountVerificationOfflineIdController.this.governmentIdType);
            AccountVerificationOfflineIdController.this.misnapUploadListener.uploadComplete();
            cleanupMiSnap(true);
            AccountVerificationOfflineIdController.this.controller.onStepFinished(AccountVerificationStep.OfflineId, false);
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            C0715L.m1189d(AccountVerificationOfflineIdController.TAG, "Did not upload successfully: " + error.getMessage());
            NetworkUtil.toastGenericNetworkError(AccountVerificationOfflineIdController.this.fragment.getActivity());
            cleanupMiSnap(false);
            ErrorUtils.showErrorUsingSnackbar(AccountVerificationOfflineIdController.this.view, C6533R.string.error_request);
        }

        private void cleanupMiSnap(boolean isSuccess) {
            if (AccountVerificationOfflineIdController.this.misnapUploadProgressDialog != null) {
                AccountVerificationOfflineIdController.this.fragment.getActivity().getSupportFragmentManager().executePendingTransactions();
                AccountVerificationOfflineIdController.this.misnapUploadProgressDialog.dismiss();
            }
            if (isSuccess) {
                if (AccountVerificationOfflineIdController.this.misnapCapturedPhoto != null) {
                    AccountVerificationOfflineIdController.this.misnapCapturedPhoto.delete();
                }
                if (AccountVerificationOfflineIdController.this.misnapCapturedBackPhoto != null) {
                    AccountVerificationOfflineIdController.this.misnapCapturedBackPhoto.delete();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public MisnapUploadListener misnapUploadListener;
    /* access modifiers changed from: private */
    public ProgressDialogFragment misnapUploadProgressDialog;
    private final NavigationLogging navigationAnalytics;
    private NetverifySDK netverifySDK;
    @State
    boolean pendingStartIdScanForDocumentType;
    private final RequestManager requestManager;
    @State
    String scanReference;
    /* access modifiers changed from: private */
    public View view;

    public interface MisnapUploadListener {
        void uploadComplete();
    }

    public void setMisnapUploadListener(MisnapUploadListener misnapUploadListener2) {
        this.misnapUploadListener = misnapUploadListener2;
    }

    static /* synthetic */ void lambda$new$2(AccountVerificationOfflineIdController accountVerificationOfflineIdController, JumioCredentialsResponse data) {
        accountVerificationOfflineIdController.jumioCredential = data.getJumioCredential();
        accountVerificationOfflineIdController.fragment.showLoader(false);
        if (accountVerificationOfflineIdController.pendingStartIdScanForDocumentType) {
            accountVerificationOfflineIdController.pendingStartIdScanForDocumentType = false;
            accountVerificationOfflineIdController.startIdScanForDocumentType();
        }
    }

    static /* synthetic */ void lambda$new$3(AccountVerificationOfflineIdController accountVerificationOfflineIdController, AirRequestNetworkException e) {
        accountVerificationOfflineIdController.fragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(accountVerificationOfflineIdController.fragment.getActivity());
        accountVerificationOfflineIdController.fragment.getActivity().finish();
    }

    public AccountVerificationOfflineIdController(Bundle savedState, AccountVerificationController controller2, AirFragment fragment2, RequestManager requestManager2, Handler handler2, NavigationLogging navigationAnalytics2, VerificationFlow flow2) {
        this.controller = controller2;
        this.fragment = fragment2;
        this.requestManager = requestManager2;
        this.handler = handler2;
        this.navigationAnalytics = navigationAnalytics2;
        this.flow = flow2;
        init(savedState);
        Fragment dialogFragment = fragment2.getActivity().getSupportFragmentManager().findFragmentByTag(TAG);
        if (dialogFragment instanceof ProgressDialogFragment) {
            this.misnapUploadProgressDialog = (ProgressDialogFragment) dialogFragment;
        }
    }

    private void init(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.requestManager.subscribe(this);
        if (savedState == null) {
            this.countryCode = DEFAULT_COUNTRY_CODE;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void setView(View view2) {
        this.view = view2;
    }

    public void onResume() {
        if (this.jumioCredential == null) {
            this.fragment.showLoader(true);
            new JumioCredentialsRequest().withListener((Observer) this.jumioCredentialsResponseRequestListener).execute(this.requestManager);
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NetverifySDK.REQUEST_CODE) {
            boolean cancel = false;
            if (data == null) {
                return false;
            }
            if (resultCode == -1) {
                handleNetverifySuccess(data);
            } else if (resultCode == 0) {
                this.controller.getIdentityJitneyLogger().logClick(this.flow, this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.id_upload, Element.jumio_button_back);
                handleNetverifyCancel(data);
                cancel = true;
            }
            if (this.netverifySDK == null) {
                return cancel;
            }
            this.netverifySDK.destroy();
            this.netverifySDK = null;
            return cancel;
        }
        if (requestCode == 3) {
            if (resultCode == 0) {
                return true;
            }
            handleMiSnapResults(data.getStringExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_RESULT_FILE), null, null);
        }
        return false;
    }

    public void startIdCaptureFlow(GovernmentIdType governmentIdType2) {
        this.governmentIdType = governmentIdType2;
        if (FeatureToggles.isInMiTekVerificationFlow()) {
            startMiSnapIdScanForDocumentType();
            trackSDKStart(governmentIdType2, CaptureMode.Mitek.name());
            this.controller.getIdentityJitneyLogger().logImpression(this.flow, this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.mitek_id_scan);
            return;
        }
        checkPermissionsAndStartSdk();
        trackSDKStart(governmentIdType2, CaptureMode.Jumio.name());
        this.controller.getIdentityJitneyLogger().logImpression(this.flow, this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.jumio_id_scan);
    }

    public void startIdScanForDocumentType() {
        if (this.jumioCredential == null) {
            this.pendingStartIdScanForDocumentType = true;
        } else if (initializeNetverifySDK()) {
            startSdk();
        }
    }

    public void setCountryCode(String countryCode2) {
        this.countryCode = countryCode2;
    }

    public void setGovernmentIdType(GovernmentIdType governmentIdType2) {
        this.governmentIdType = governmentIdType2;
    }

    private boolean initializeNetverifySDK() {
        try {
            this.netverifySDK = NetverifySDK.create(this.fragment.getActivity(), this.jumioCredential.getApiToken(), this.jumioCredential.getApiSecret(), JumioDataCenter.US);
            this.netverifySDK.setMerchantScanReference(this.jumioCredential.getMerchantScanReference());
            this.netverifySDK.setRequireVerification(true);
            this.netverifySDK.setRequireFaceMatch(false);
            this.netverifySDK.setShowHelpBeforeScan(true);
            this.netverifySDK.setEnableEpassport(true);
            if (!this.flow.isIdentityRedesign() && FeatureToggles.showJumioIdTypeCountrySelector()) {
                return true;
            }
            this.netverifySDK.setPreselectedCountry(this.countryCode);
            this.netverifySDK.setPreselectedDocumentTypes(new ArrayList(Collections.singletonList(this.governmentIdType.documentType)));
            return true;
        } catch (PlatformNotSupportedException | NullPointerException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException(e));
            ErrorUtils.showErrorUsingSnackbar(this.view, C6533R.string.error_request);
            return false;
        }
    }

    private void checkPermissionsAndStartSdk() {
        if (!MobileSDK.hasAllRequiredPermissions(this.fragment.getActivity())) {
            this.fragment.requestPermissions(MobileSDK.getMissingPermissions(this.fragment.getActivity()), CertificateBody.profileType);
            return;
        }
        startIdScanForDocumentType();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        if (requestCode == 127) {
            Context context = this.fragment.getContext();
            if (MobileSDK.hasAllRequiredPermissions(context)) {
                startIdScanForDocumentType();
            } else {
                Toast.makeText(context, context.getString(C6533R.string.account_verification_jumio_permission), 1).show();
            }
        }
    }

    private void startSdk() {
        try {
            this.netverifySDK.start();
            if (this.navigationAnalytics != null) {
                this.navigationAnalytics.trackImpressionExplicitly(NavigationTag.VerificationScanId, null);
            } else {
                BugsnagWrapper.throwOrNotify((RuntimeException) new NullPointerException("NavigationLogging unexpectedly null."));
            }
        } catch (MissingPermissionException e) {
            ErrorUtils.showErrorUsingSnackbar(this.view, e.getMessage());
        }
    }

    private void handleNetverifySuccess(Intent data) {
        this.scanReference = data.getStringExtra(NetverifySDK.EXTRA_SCAN_REFERENCE);
        NetverifyDocumentData documentData = (NetverifyDocumentData) data.getParcelableExtra(NetverifySDK.EXTRA_SCAN_DATA);
        this.governmentIdType = GovernmentIdType.getGovernmentIdType(documentData.getSelectedDocumentType());
        this.countryCode = documentData.getSelectedCountry();
        Date expirationDate = documentData.getExpiryDate();
        if (expirationDate != null && expirationDate.before(new Date())) {
            this.controller.setState(SheetState.Error);
            this.handler.post(AccountVerificationOfflineIdController$$Lambda$5.lambdaFactory$(this, expirationDate));
            trackRequestFailure(this.governmentIdType, CaptureMode.Jumio.name(), "Expired ID", this.countryCode);
        } else if (!TextUtils.isEmpty(this.scanReference)) {
            new JumioScanReferenceUploadRequest(this.scanReference).withListener((Observer) this.jumioScanReferenceUploadRequestListener).execute(this.requestManager);
            trackRequestSuccess(this.governmentIdType, CaptureMode.Jumio.name(), this.scanReference, this.countryCode);
            this.controller.getIdentityJitneyLogger().logAttemptedGovernmentIdUpload(this.flow, this.controller.getUser(), "Jumio");
        } else {
            new SnackbarWrapper().view(this.view).body(this.fragment.getActivity().getString(C6533R.string.error_request)).duration(-1).buildAndShow();
            trackRequestFailure(this.governmentIdType, CaptureMode.Jumio.name(), "Empty scan reference", this.countryCode);
        }
    }

    private void handleNetverifyCancel(Intent data) {
        this.scanReference = null;
        trackRequestFailure(this.governmentIdType, CaptureMode.Jumio.name(), "Cancelled", this.countryCode);
    }

    public void handleMiSnapResults(String frontPhoto, String backPhoto, String barCode) {
        if (!TextUtils.isEmpty(frontPhoto)) {
            this.misnapCapturedPhoto = new File(frontPhoto);
            if (!TextUtils.isEmpty(backPhoto)) {
                this.misnapCapturedBackPhoto = new File(backPhoto);
                if (!this.misnapCapturedBackPhoto.exists()) {
                    this.misnapCapturedBackPhoto = null;
                }
            }
            this.misnapCapturedBarCode = barCode;
            boolean failedExpiration = false;
            if (this.misnapCapturedBarCode != null) {
                Date expirationDate = null;
                try {
                    expirationDate = new SimpleDateFormat("MMddyyyy").parse(barCode.substring(barCode.indexOf("DBA") + "DBA".length(), barCode.indexOf("DBA") + "DBA".length() + 8));
                } catch (ParseException e) {
                    BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Expiry date" + expirationDate + "failed to parse with exception" + e.toString()));
                }
                if (expirationDate != null && expirationDate.before(new Date())) {
                    failedExpiration = true;
                    this.controller.setState(SheetState.Error);
                    this.handler.post(AccountVerificationOfflineIdController$$Lambda$6.lambdaFactory$(this, expirationDate));
                    trackRequestFailure(this.governmentIdType, CaptureMode.Mitek.name(), "Expired ID", this.countryCode);
                }
            }
            if (!failedExpiration && this.misnapCapturedPhoto.exists()) {
                uploadMiSnapResults();
                trackRequestSuccess(this.governmentIdType, CaptureMode.Mitek.name(), null, this.countryCode);
                return;
            }
        }
        ErrorUtils.showErrorUsingSnackbar(this.view, C6533R.string.account_verification_take_selfie_error);
        trackRequestFailure(this.governmentIdType, CaptureMode.Mitek.name(), "Missing front photo", this.countryCode);
    }

    private void uploadMiSnapResults() {
        Check.notNull(this.misnapCapturedPhoto, "misnapCapturedPhoto can't be null");
        this.misnapUploadProgressDialog = ProgressDialogFragment.newInstance(this.fragment.getActivity().getString(C6533R.string.verified_id_offline_verifying), null);
        new GovernmentIdUploadRequest(this.misnapCapturedPhoto, Locale.getDefault().getCountry(), this.governmentIdType.name().toUpperCase(), Vendor.Mitek, this.misnapResultRequestListener).setBackIdFile(this.misnapCapturedBackPhoto).setBarcode(this.misnapCapturedBarCode).execute(this.requestManager);
        this.misnapUploadProgressDialog.show(this.fragment.getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null), TAG);
        VerifiedIdAnalytics.trackOfflineUploadPhotoView(Strap.make().mo11639kv(VerifiedIdAnalytics.ID_CAPTURED_METHOD, OfficialIdUploadRequest.MISNAP_CALLER));
        this.controller.getIdentityJitneyLogger().logAttemptedGovernmentIdUpload(this.flow, this.controller.getUser(), "Mitek");
    }

    private void trackSDKStart(GovernmentIdType idType, String captureMode) {
        AccountVerificationAnalytics.trackRequestStart(NavigationTag.VerificationScanId, "sdk_start", Strap.make().mo11639kv("document_type", idType.trackingEventData).mo11639kv("capture_mode", captureMode));
    }

    private void trackRequestSuccess(GovernmentIdType idType, String captureMode, String reference, String countryCode2) {
        AccountVerificationAnalytics.trackRequestSuccess(NavigationTag.VerificationScanId, "sdk_success", Strap.make().mo11639kv("document_type", idType.trackingEventData).mo11639kv("capture_mode", captureMode).mo11639kv("reference", reference).mo11639kv("country_code", countryCode2));
    }

    private void trackRequestFailure(GovernmentIdType idType, String captureMode, String reason, String countryCode2) {
        AccountVerificationAnalytics.trackRequestFailure(NavigationTag.VerificationScanId, "sdk_failure", Strap.make().mo11639kv("document_type", idType.trackingEventData).mo11639kv("capture_mode", captureMode).mo11639kv(CancellationAnalytics.VALUE_PAGE_REASON, reason).mo11639kv("country_code", countryCode2));
    }

    private void startMiSnapIdScanForDocumentType() {
        this.controller.showFragmentForStep(AccountVerificationMiSnapIDCaptureFragment.newInstance(this.countryCode, this.governmentIdType), AccountVerificationStep.OfflineId);
    }
}
