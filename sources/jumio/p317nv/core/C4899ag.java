package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.camera.ImageData;
import com.jumio.commons.enums.ScreenAngle;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.Log.LogLevel;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.environment.Environment;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.plugins.Plugin;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.core.plugins.PluginRegistry.PluginMode;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.api.helpers.UploadManager;
import com.jumio.p311nv.benchmark.Benchmark;
import com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.enums.NVExtractionMethod;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.LivenessModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.models.TemplateModel;
import com.jumio.p311nv.utils.NetverifyLogUtils;
import com.jumio.p311nv.view.interactors.NVScanView;
import com.jumio.p311nv.view.interactors.NVScanView.GuiState;
import com.jumio.p311nv.view.interactors.NVScanView.NVHelpConfiguration;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import com.jumio.sdk.models.BaseScanPartModel;
import com.jumio.sdk.presentation.BaseScanPresenter;
import com.jumio.sdk.presentation.BaseScanPresenter.BaseScanControl;
import java.io.File;
import java.io.IOException;

/* renamed from: jumio.nv.core.ag */
/* compiled from: NVScanPresenter */
public class C4899ag extends BaseScanPresenter<NVScanView, ExtractionUpdate, DocumentDataModel> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public NVScanPartModel f4743a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public NVScanPartModel f4744b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public NVHelpConfiguration f4745c;

    /* renamed from: d */
    private ViewGroup f4746d;

    /* renamed from: e */
    private boolean f4747e;

    /* renamed from: f */
    private GuiState f4748f = GuiState.SCAN;

    /* renamed from: g */
    private Runnable f4749g = new Runnable() {
        public void run() {
            boolean z;
            boolean z2 = true;
            if (C4899ag.this.isActive()) {
                SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) C4899ag.this.getView()).getContext(), SelectionModel.class);
                if (selectionModel != null) {
                    C4899ag.this.f4745c = new NVHelpConfiguration();
                    C4899ag.this.f4745c.documentType = selectionModel.getSelectedDoctype().getId();
                    C4899ag.this.f4745c.documentVariant = selectionModel.getSelectedVariant();
                    C4899ag.this.f4745c.scanMode = C4899ag.this.f4743a.getScanMode();
                    C4899ag.this.f4745c.side = C4899ag.this.f4743a.getSideToScan();
                    C4899ag.this.f4745c.part = C4899ag.this.f4743a.getPartIndex() + 1;
                    C4899ag.this.f4745c.totalParts = selectionModel.getUploadModel().getScans().size();
                    NVHelpConfiguration b = C4899ag.this.f4745c;
                    if (C4899ag.this.f4744b != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    b.isUSDLFallback = z;
                    NVHelpConfiguration b2 = C4899ag.this.f4745c;
                    if (!C4899ag.this.mo46829i() || C4899ag.this.f4743a.getScanMode() == DocumentScanMode.CSSN || C4899ag.this.f4743a.getScanMode() == DocumentScanMode.LINEFINDER || selectionModel.getSelectedDoctype().getDocumentScanSide() != C4899ag.this.f4743a.getSideToScan()) {
                        z2 = false;
                    }
                    b2.showFallback = z2;
                    ((NVScanView) C4899ag.this.getView()).showSnackbar(C4899ag.this.f4745c);
                }
            }
        }
    };

    /* renamed from: h */
    private Handler f4750h = new Handler(Looper.getMainLooper());

    /* renamed from: i */
    private UploadManager f4751i;

    /* renamed from: j */
    private MerchantSettingsModel f4752j;

    /* renamed from: jumio.nv.core.ag$a */
    /* compiled from: NVScanPresenter */
    public enum C4905a {
        TITLE,
        HELP,
        CONFIRMATION,
        BRANDING
    }

    /* renamed from: jumio.nv.core.ag$b */
    /* compiled from: NVScanPresenter */
    class C4906b implements OnClickListener {
        private C4906b() {
        }

        public void onClick(View view) {
            if (C4899ag.this.mExtractionClient.takePictureManually()) {
                C4899ag.this.mExtractionClient.takePicture();
            }
        }
    }

    /* access modifiers changed from: protected */
    public BaseScanPartModel getScanPartModel() {
        if (this.f4743a == null) {
            this.f4743a = (NVScanPartModel) DataAccess.load(((NVScanView) getView()).getContext(), NVScanPartModel.class);
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
            if (selectionModel != null && selectionModel.getSelectedDoctype() != null && selectionModel.getSelectedDoctype().getDocumentScanMode().equals(this.f4743a.getScanMode()) && selectionModel.getSelectedDoctype().isThirdPartyOcrDefined() && NVDocumentVariant.PLASTIC.equals(selectionModel.getSelectedVariant()) && PluginRegistry.hasPlugin(PluginMode.TEMPLATE_MATCHER)) {
                try {
                    if (new TemplateModel(((NVScanView) getView()).getContext()).getTemplate(selectionModel.getSelectedCountry(), selectionModel.getSelectedDoctype().getId(), selectionModel.getSelectedDoctype().getDocumentScanSide()) != null) {
                        selectionModel.getSelectedDoctype().setDocumentScanMode(DocumentScanMode.TEMPLATEMATCHER);
                        ScanSide sideToScan = this.f4743a.getSideToScan();
                        this.f4743a = new NVScanPartModel(sideToScan, DocumentScanMode.TEMPLATEMATCHER, this.f4743a.getPartIndex());
                        selectionModel.getUploadModel().replace(sideToScan, this.f4743a);
                        DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
                    }
                } catch (Exception e) {
                }
            }
            if (this.f4743a.getSideToScan() == ScanSide.FACE && this.f4743a.getScanMode() == DocumentScanMode.FACE_MANUAL && PluginRegistry.hasPlugin(PluginMode.FACE) && new Benchmark(((NVScanView) getView()).getContext()).getDeviceCategory() != DeviceCategory.SLOW) {
                ScanSide sideToScan2 = this.f4743a.getSideToScan();
                this.f4743a = new NVScanPartModel(sideToScan2, DocumentScanMode.FACE, this.f4743a.getPartIndex());
                selectionModel.getUploadModel().replace(sideToScan2, this.f4743a);
                DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
            }
        }
        return this.f4743a;
    }

    /* access modifiers changed from: protected */
    public boolean startCameraFrontfacing() {
        DocumentScanMode scanMode = getScanPartModel().getScanMode();
        return this.f4752j.isCameraFrontfacing() || scanMode == DocumentScanMode.FACE_MANUAL || scanMode == DocumentScanMode.FACE;
    }

    /* access modifiers changed from: protected */
    public boolean canSwitchCamera() {
        DocumentScanMode scanMode = getScanPartModel().getScanMode();
        return (this.cameraManager == null || !this.cameraManager.hasMultipleCameras() || scanMode == DocumentScanMode.FACE_MANUAL || scanMode == DocumentScanMode.FACE) ? false : true;
    }

    /* access modifiers changed from: protected */
    public boolean enableFlashOnStart() {
        return false;
    }

    public boolean control(int i) {
        boolean control = super.control(i);
        if (i == BaseScanControl.toggleFlash) {
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.FLASH, this.cameraManager.isFlashOn() ? "ON" : "OFF"));
        } else if (i == BaseScanControl.toggleCamera) {
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.CAMERA, this.cameraManager.isFrontFacing() ? "BACK" : "FRONT"));
        }
        return control;
    }

    public void onPreviewAvailable(PreviewProperties previewProperties) {
        super.onPreviewAvailable(previewProperties);
        this.mExtractionClient.setDataExtractionActive(this.f4748f == GuiState.SCAN);
        if (!this.f4752j.isShowHelpBeforeScan()) {
            this.f4750h.post(new Runnable() {
                public void run() {
                    ((NVScanView) C4899ag.this.getView()).hideHelp();
                }
            });
        }
    }

    public void onCameraError(Throwable th) {
        ((NVScanView) getView()).onError(new JumioException(NVErrorCase.NO_CAMERA_CONNECTION, 0));
    }

    public void onManualRefocus(int i, int i2) {
        super.onManualRefocus(i, i2);
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.REFOCUS));
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        if (this.f4752j == null) {
            this.f4752j = (MerchantSettingsModel) DataAccess.load(((NVScanView) getView()).getContext(), MerchantSettingsModel.class);
            if (this.f4752j == null) {
                ((NVScanView) getView()).onError(new JumioException(NVErrorCase.OCR_LOADING_FAILED, 0));
                return;
            }
        }
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        boolean z = true;
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
        this.f4747e = selectionModel != null && selectionModel.isVerificationRequired();
        if (this.f4743a == null) {
            this.f4743a = (NVScanPartModel) DataAccess.load(((NVScanView) getView()).getContext(), NVScanPartModel.class);
            if (this.f4743a == null) {
                ((NVScanView) getView()).onError(new JumioException(NVErrorCase.OCR_LOADING_FAILED, 0));
                return;
            }
        }
        if (this.f4744b == null) {
            this.f4744b = (NVScanPartModel) DataAccess.load(((NVScanView) getView()).getContext(), "fallbackScanPartModel");
        }
        if (this.mOverlay != null) {
            onUpdate(new ExtractionUpdate(ExtractionUpdateState.receiveClickListener, new C4906b()));
            this.mOverlay.setVisible(4);
        }
        ScanSide sideToScan = this.f4743a.getSideToScan();
        if (this.f4752j.isShowHelpBeforeScan() && this.f4748f == GuiState.SCAN) {
            this.f4745c = new NVHelpConfiguration();
            this.f4745c.documentType = selectionModel.getSelectedDoctype().getId();
            this.f4745c.documentVariant = selectionModel.getSelectedVariant();
            this.f4745c.scanMode = this.f4743a.getScanMode();
            this.f4745c.side = sideToScan;
            this.f4745c.part = this.f4743a.getPartIndex() + 1;
            this.f4745c.totalParts = selectionModel.getUploadModel().getScans().size();
            NVHelpConfiguration nVHelpConfiguration = this.f4745c;
            if (this.f4744b == null) {
                z = false;
            }
            nVHelpConfiguration.isUSDLFallback = z;
            this.f4745c.showFallback = false;
            ((NVScanView) getView()).showHelp(this.f4745c, false);
        }
        this.f4751i = new UploadManager(((NVScanView) getView()).getContext(), selectionModel.getUploadModel().getActivePart().getSideToScan(), ((NVScanView) getView()).getCredentialsModel(), this.f4747e);
        this.f4751i.addSubscribers();
        this.f4751i.add(new Subscriber<Boolean>() {
            /* renamed from: a */
            public void onResult(Boolean bool) {
            }

            public void onError(Throwable th) {
                if (C4899ag.this.isActive()) {
                    ((NVScanView) C4899ag.this.getView()).onError(th);
                }
            }
        });
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.f4751i.removeSubscribers();
        this.f4750h.removeCallbacks(this.f4749g);
        DataAccess.update(((NVScanView) getView()).getContext(), NVScanPartModel.class, this.f4743a);
        if (this.f4744b != null) {
            DataAccess.update(((NVScanView) getView()).getContext(), "fallbackScanPartModel", this.f4744b);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mOrientationListener != null) {
            this.mOrientationListener.disable();
            this.mOrientationListener = null;
        }
    }

    /* renamed from: a */
    public void onUpdate(final ExtractionUpdate extractionUpdate) {
        if (isActive() && extractionUpdate != null) {
            int state = extractionUpdate.getState();
            if (state == ExtractionUpdateState.shotTaken) {
                ((NVScanView) getView()).extractionStarted();
                this.f4750h.removeCallbacks(this.f4749g);
                ImageData scannedImage = this.f4743a.getScannedImage();
                this.cameraManager.getImageData(scannedImage);
                this.cameraManager.stopPreview(false);
                scannedImage.setFocusConfidence(((Float) extractionUpdate.getData()).floatValue());
                try {
                    ((Vibrator) ((NVScanView) getView()).getContext().getSystemService("vibrator")).vibrate(100);
                } catch (Exception e) {
                }
            } else if (state == ExtractionUpdateState.notifyFlash) {
                this.cameraManager.requestFlashHint(((Boolean) extractionUpdate.getData()).booleanValue());
            } else if (state == ExtractionUpdateState.notifyFocus) {
                Point point = (Point) extractionUpdate.getData();
                this.cameraManager.requestFocus(point.x, point.y);
            } else if (state == ExtractionUpdateState.saveImage) {
                Bitmap bitmap = (Bitmap) extractionUpdate.getData();
                if (Log.isLogEnabledForLevel(LogLevel.DEBUG)) {
                    NetverifyLogUtils.dumpImageInSubfolder(bitmap, this.mExtractionClient.getClass().getSimpleName(), CompressFormat.JPEG, 80, String.valueOf(this.f4743a.getPartIndex()));
                }
                File file = new File(Environment.getDataDirectory(((NVScanView) getView()).getContext()), String.format("tmp_%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
                try {
                    CameraUtils.saveBitmap(bitmap, file, CompressFormat.WEBP, 75);
                } catch (IOException e2) {
                    Log.printStackTrace(e2);
                }
                this.f4743a.getScannedImage().setImagePath(file.getAbsolutePath());
            } else if (state == ExtractionUpdateState.saveExactImage) {
                Bitmap bitmap2 = (Bitmap) extractionUpdate.getData();
                File file2 = new File(Environment.getDataDirectory(((NVScanView) getView()).getContext()), String.format("tmp_%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
                try {
                    CameraUtils.saveBitmap(bitmap2, file2, CompressFormat.JPEG, 80);
                } catch (IOException e3) {
                    Log.printStackTrace(e3);
                }
                this.f4743a.getScannedImage().setExactImagePath(file2.getAbsolutePath());
            } else {
                this.f4750h.post(new Runnable() {
                    public void run() {
                        if (C4899ag.this.mOverlay != null && C4899ag.this.isActive()) {
                            C4899ag.this.mOverlay.update(extractionUpdate);
                            ((NVScanView) C4899ag.this.getView()).invalidateDrawView();
                        }
                    }
                });
            }
        }
    }

    /* renamed from: a */
    public void onResult(DocumentDataModel documentDataModel) {
        DocumentDataModel documentDataModel2;
        boolean z;
        if (isActive()) {
            Log.m1909d("ScanPresenter", "onResult");
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.SCAN_TRIGGERED));
            DocumentScanMode scanMode = this.f4743a.getScanMode();
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
            if (documentDataModel == null && selectionModel.getUploadModel().getExtractionMethod() == NVExtractionMethod.NONE && this.f4743a.getSideToScan() == ScanSide.FRONT) {
                documentDataModel2 = new DocumentDataModel();
            } else {
                documentDataModel2 = documentDataModel;
            }
            this.f4743a.setDocumentInfo(documentDataModel2);
            switch (scanMode) {
                case PDF417:
                    String addressLine = documentDataModel2.getAddressLine();
                    String city = documentDataModel2.getCity();
                    if (this.f4747e || this.f4752j.isDataExtractionOnMobileOnly() || !"USA".equals(selectionModel.getSelectedCountry().getIsoCode()) || !(addressLine == null || addressLine.length() == 0 || city == null || city.length() == 0)) {
                        z = false;
                        break;
                    } else {
                        this.mExtractionClient.unsubscribe(this);
                        this.mExtractionClient.destroy();
                        mo46828h();
                        this.f4745c = new NVHelpConfiguration();
                        this.f4745c.documentType = NVDocumentType.DRIVER_LICENSE;
                        this.f4745c.documentVariant = NVDocumentVariant.PLASTIC;
                        this.f4745c.scanMode = DocumentScanMode.CSSN;
                        this.f4745c.side = ScanSide.FRONT;
                        this.f4745c.part = 0;
                        this.f4745c.totalParts = 0;
                        this.f4745c.isUSDLFallback = true;
                        this.f4745c.showFallback = false;
                        ((NVScanView) getView()).showHelp(this.f4745c, false);
                        return;
                    }
                    break;
                case MRP:
                case MRV:
                case TD1:
                case TD2:
                case CNIS:
                    z = this.f4747e;
                    break;
                case CSSN:
                case LINEFINDER:
                case TEMPLATEMATCHER:
                    z = true;
                    break;
                case MANUAL:
                    z = true;
                    break;
                case FACE:
                    LivenessModel livenessModel = new LivenessModel();
                    livenessModel.setLivenessDetected(documentDataModel2.getLivenessDetected());
                    DataAccess.update(((NVScanView) getView()).getContext(), LivenessModel.class, livenessModel);
                    if ((documentDataModel2 instanceof C4886aa) && ((C4886aa) documentDataModel2).mo46795a().length != 0) {
                        NVBackend.liveness(((NVScanView) getView()).getContext(), ((NVScanView) getView()).getCredentialsModel(), null, ((C4886aa) documentDataModel2).mo46795a());
                        z = false;
                        break;
                    } else {
                        z = false;
                        break;
                    }
                default:
                    z = false;
                    break;
            }
            this.cameraManager.stopPreview(true);
            this.mExtractionClient.cancel();
            if (z) {
                ((NVScanView) getView()).showConfirmation(this.f4743a.getScannedImage().getExactImagePath(), false);
                this.f4748f = GuiState.CONFIRMATION;
            } else if (!this.f4747e) {
                m2975k();
            } else {
                m2966b(false);
            }
        }
    }

    @InvokeOnUiThread
    public void onError(Throwable th) {
        super.onError(th);
    }

    /* access modifiers changed from: protected */
    public void onScreenAngleChanged(ScreenAngle screenAngle) {
        String str;
        super.onScreenAngleChanged(screenAngle);
        switch (screenAngle) {
            case PORTRAIT:
                str = "P";
                break;
            case LANDSCAPE:
                str = "L";
                break;
            case INVERTED_LANDSCAPE:
                str = "IL";
                break;
            case INVERTED_PORTRAIT:
                str = "IP";
                break;
            default:
                str = "P";
                break;
        }
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), (Screen) null, UserAction.ORIENTATION_CHANGED, str));
    }

    public void addChildren(ViewGroup viewGroup) {
        super.addChildren(viewGroup);
        this.f4746d = viewGroup;
    }

    /* renamed from: a */
    public boolean mo46821a() {
        return this.f4747e;
    }

    /* renamed from: b */
    public ScanSide mo46822b() {
        if (this.f4743a != null) {
            return this.f4743a.getSideToScan();
        }
        return null;
    }

    /* renamed from: c */
    public void mo46823c() {
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.CONFIRMATION, UserAction.CONFIRM));
        if (!this.f4747e) {
            m2975k();
        } else {
            m2966b(false);
        }
    }

    /* renamed from: d */
    public void mo46824d() {
        this.f4748f = GuiState.SCAN;
        JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.CONFIRMATION, UserAction.RETRY));
        this.f4743a.getScannedImage().clear();
        this.mExtractionClient.reinit();
        onStart();
        if (!this.f4752j.isShowHelpBeforeScan()) {
            ((NVScanView) getView()).hideHelp();
        }
    }

    /* renamed from: a */
    private boolean m2963a(SelectionModel selectionModel) {
        if (selectionModel == null || selectionModel.getSelectedDoctype().getId() != NVDocumentType.PASSPORT) {
            return false;
        }
        NfcController nfcController = ((NVScanView) getView()).getNfcController();
        DocumentDataModel documentInfo = this.f4743a.getDocumentInfo();
        String isoCode = selectionModel.getSelectedCountry().getIsoCode();
        if (!(documentInfo == null || documentInfo.getIssuingCountry() == null || documentInfo.getIssuingCountry().length() == 0)) {
            isoCode = documentInfo.getIssuingCountry();
        }
        if (!nfcController.hasNfcFeature() || !nfcController.hasRootCertificate(isoCode)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo46815a(NVScanPartModel nVScanPartModel) {
        Context context = ((NVScanView) getView()).getContext();
        DataAccess.delete(context, NVScanPartModel.class);
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(context, SelectionModel.class);
        if (selectionModel != null) {
            selectionModel.getUploadModel().replace(nVScanPartModel.getSideToScan(), nVScanPartModel);
            DataAccess.update(context, SelectionModel.class, selectionModel);
            return;
        }
        Log.m1929w("ScanPresenter", "NVScanPresenter#storeScanPart(): Selection model was not found in persistence!!");
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo46825e() {
        String str;
        String str2;
        DocumentDataModel documentInfo = this.f4743a.getDocumentInfo();
        if (documentInfo != null) {
            Bundle bundle = new Bundle();
            String issuingCountry = documentInfo.getIssuingCountry();
            ((NVScanView) getView()).getContext();
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
            if (issuingCountry != null || selectionModel == null) {
                str = issuingCountry;
            } else {
                str = selectionModel.getSelectedCountry().getIsoCode();
            }
            if (str != null) {
                str2 = str.toLowerCase();
            } else {
                str2 = str;
            }
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(((NVScanView) getView()).getContext(), InitiateModel.class);
            bundle.putString("country", str2);
            bundle.putString("ppnumber", documentInfo.getIdNumber());
            bundle.putLong("dob", documentInfo.getDob().getTime());
            bundle.putLong("doe", documentInfo.getExpiryDate().getTime());
            bundle.putString("scanReference", initiateModel != null ? initiateModel.getJumioScanRef() : "");
            ((NVScanView) getView()).showNFC(bundle);
        }
    }

    /* renamed from: k */
    private void m2975k() {
        this.f4748f = GuiState.LOADING;
        ((NVScanView) getView()).showLoading();
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
        if (selectionModel == null) {
            Log.m1929w("ScanPresenter", "NVScanPresenter#extractDataAndClose(): data model cannot be null!");
            return;
        }
        if (selectionModel.getUploadModel().has(this.f4743a.getSideToScan())) {
            selectionModel.getUploadModel().replace(this.f4743a.getSideToScan(), this.f4743a);
        } else {
            selectionModel.getUploadModel().add(this.f4743a);
        }
        selectionModel.getUploadModel().setDocumentData(this.f4743a.getSideToScan(), this.f4743a.getDocumentInfo());
        DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
        if (this.f4743a.getScanMode() == DocumentScanMode.TEMPLATEMATCHER || this.f4743a.getScanMode() == DocumentScanMode.CSSN) {
            this.f4751i.startExtractData(selectionModel, this.f4743a.getSideToScan());
        } else {
            this.f4751i.startData();
        }
    }

    /* renamed from: b */
    private void m2966b(boolean z) {
        boolean z2;
        mo46815a(this.f4743a);
        MerchantSettingsModel merchantSettingsModel = (MerchantSettingsModel) DataAccess.load(((NVScanView) getView()).getContext(), MerchantSettingsModel.class);
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
        if (merchantSettingsModel == null || selectionModel == null || selectionModel.getUploadModel() == null) {
            onError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
            return;
        }
        if (!(merchantSettingsModel != null && merchantSettingsModel.isEnableEpassport()) || !m2963a(selectionModel) || this.f4743a.getPartIndex() != 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z) {
            this.f4751i.startExtractData(selectionModel, ScanSide.FRONT);
        } else {
            this.f4751i.startAddPart(selectionModel);
        }
        if (z2) {
            mo46825e();
        } else if (selectionModel.getUploadModel() == null || !selectionModel.getUploadModel().hasNext()) {
            if (selectionModel.getUploadModel().getExtractionMethod() == NVExtractionMethod.OCR) {
                for (NVScanPartModel nVScanPartModel : selectionModel.getUploadModel().getScans()) {
                    if (nVScanPartModel.getScanMode() == DocumentScanMode.TEMPLATEMATCHER || nVScanPartModel.getScanMode() == DocumentScanMode.CSSN) {
                        this.f4751i.startExtractData(selectionModel, nVScanPartModel.getSideToScan());
                    }
                }
            }
            this.f4751i.startData();
            NVBackend.finalizeCall(((NVScanView) getView()).getContext(), ((NVScanView) getView()).getCredentialsModel(), null);
            ((NVScanView) getView()).scansComplete();
        } else {
            this.f4743a = selectionModel.getUploadModel().nextPart();
            DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
            DataAccess.update(((NVScanView) getView()).getContext(), NVScanPartModel.class, this.f4743a);
            ((NVScanView) getView()).partComplete();
        }
    }

    /* renamed from: a */
    public void mo46819a(C4905a aVar, boolean z) {
        boolean z2;
        boolean z3 = false;
        boolean z4 = true;
        switch (aVar) {
            case HELP:
                if (this.f4745c == null) {
                    SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
                    this.f4745c = new NVHelpConfiguration();
                    this.f4745c.documentType = selectionModel.getSelectedDoctype().getId();
                    this.f4745c.documentVariant = selectionModel.getSelectedVariant();
                    this.f4745c.scanMode = this.f4743a.getScanMode();
                    this.f4745c.side = this.f4743a.getSideToScan();
                    this.f4745c.part = this.f4743a.getPartIndex() + 1;
                    this.f4745c.totalParts = selectionModel.getUploadModel().getScans().size();
                    NVHelpConfiguration nVHelpConfiguration = this.f4745c;
                    if (this.f4744b != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    nVHelpConfiguration.isUSDLFallback = z2;
                    NVHelpConfiguration nVHelpConfiguration2 = this.f4745c;
                    if (!(!selectionModel.getSelectedDoctype().hasFallbackScan() || this.f4743a.getScanMode() == DocumentScanMode.CSSN || this.f4743a.getScanMode() == DocumentScanMode.LINEFINDER)) {
                        z3 = true;
                    }
                    nVHelpConfiguration2.showFallback = z3;
                }
                ((NVScanView) getView()).showHelp(this.f4745c, true);
                return;
            case CONFIRMATION:
                ((NVScanView) getView()).showConfirmation(this.f4743a.getScannedImage().getExactImagePath(), true);
                this.f4748f = GuiState.CONFIRMATION;
                return;
            case BRANDING:
                ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load(((NVScanView) getView()).getContext(), ServerSettingsModel.class);
                if (serverSettingsModel == null || !serverSettingsModel.isBrandingEnabled()) {
                    z4 = false;
                }
                if ((this.f4743a.getScanMode() == DocumentScanMode.MRP || this.f4743a.getScanMode() == DocumentScanMode.MRV) && !z) {
                    z4 = false;
                }
                ((NVScanView) getView()).updateBranding(z4);
                return;
            default:
                return;
        }
    }

    /* renamed from: f */
    public GuiState mo46826f() {
        return this.f4748f;
    }

    /* renamed from: a */
    public void mo46816a(GuiState guiState) {
        this.f4748f = guiState;
    }

    /* renamed from: a */
    public void mo46820a(boolean z) {
        boolean z2;
        boolean z3 = true;
        if (isActive()) {
            if (!z && this.f4748f == GuiState.HELP) {
                PluginMode pluginMode = PluginRegistry.getPluginMode(this.f4743a.getScanMode());
                String str = "";
                if (pluginMode == PluginMode.BARCODE) {
                    try {
                        Class cls = Class.forName("com.jumio.nv.barcode.vision.BarcodeVisionPlugin");
                        if (cls != null && this.plugin.getClass().getSimpleName().equals(cls.getSimpleName())) {
                            str = "_NATIVE";
                        }
                    } catch (Exception e) {
                        Log.printStackTrace(e);
                    }
                }
                MetaInfo metaInfo = new MetaInfo();
                metaInfo.put("side", this.f4743a.getSideToScan().toString());
                metaInfo.put("type", pluginMode.toString() + str);
                JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.SCAN, metaInfo));
            }
            this.f4748f = z ? GuiState.HELP : GuiState.SCAN;
            ExtractionClient extractionClient = this.mExtractionClient;
            if (this.f4748f == GuiState.SCAN) {
                z2 = true;
            } else {
                z2 = false;
            }
            extractionClient.setDataExtractionActive(z2);
            if (this.mOverlay != null) {
                this.mOverlay.setVisible(this.f4748f == GuiState.SCAN ? 0 : 4);
                ((NVScanView) getView()).invalidateDrawView();
            }
            this.f4750h.removeCallbacks(this.f4749g);
            if (this.f4748f == GuiState.SCAN) {
                this.f4750h.postDelayed(this.f4749g, 10000);
                ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load(((NVScanView) getView()).getContext(), ServerSettingsModel.class);
                if (serverSettingsModel == null || !serverSettingsModel.isBrandingEnabled()) {
                    z3 = false;
                }
                if ((this.f4743a.getScanMode() == DocumentScanMode.MRP || this.f4743a.getScanMode() == DocumentScanMode.MRV) && !((NVScanView) getView()).getRotationManager().isScreenPortrait()) {
                    z3 = false;
                }
                ((NVScanView) getView()).updateBranding(z3);
                return;
            }
            ((NVScanView) getView()).updateBranding(false);
        }
    }

    /* renamed from: g */
    public void mo46827g() {
        NVBackend.forceRetry();
    }

    /* renamed from: a */
    public void mo46817a(JumioException jumioException) {
        InitiateModel initiateModel = (InitiateModel) DataAccess.load(((NVScanView) getView()).getContext(), InitiateModel.class);
        ((NVScanView) getView()).getContext().sendBroadcast(new C4884a(jumioException.getErrorCase().code(), jumioException.getDetailedErrorCase(), jumioException.getErrorCase().localizedMessage(((NVScanView) getView()).getContext()), initiateModel != null ? initiateModel.getJumioScanRef() : null));
    }

    /* renamed from: h */
    public void mo46828h() {
        if (!DocumentScanMode.CSSN.equals(this.f4743a.getScanMode())) {
            mo46815a(this.f4743a);
            this.f4744b = this.f4743a;
            DataAccess.update(((NVScanView) getView()).getContext(), "fallbackScanPartModel", this.f4744b);
            DataAccess.update(((NVScanView) getView()).getContext(), NVScanPartModel.class, this.f4743a);
            this.f4743a = new NVScanPartModel(ScanSide.FRONT, DocumentScanMode.CSSN, 0);
            Plugin plugin = PluginRegistry.getPlugin(this.f4743a.getScanMode());
            this.mExtractionClient = plugin.getExtractionClient(((NVScanView) getView()).getContext());
            this.mExtractionClient.configure(this.f4743a);
            this.mExtractionClient.subscribe(this);
            this.mExtractionClient.setFlags(((NVScanView) getView()).getRotationManager().isPortrait(), ((NVScanView) getView()).getRotationManager().isTablet(), ((NVScanView) getView()).getRotationManager().isInverted());
            this.mExtractionClient.setPreviewProperties(this.mPreviewProperties);
            this.mExtractionClient.reinit();
            this.mOverlay = plugin.getOverlay(((NVScanView) getView()).getContext(), null);
            this.mOverlay.calculate(this.f4743a.getScanMode(), this.viewWidth, this.viewHeight);
            this.mOverlay.prepareDraw(this.f4743a.getSideToScan(), this.cameraManager.isFrontFacing(), ((NVScanView) getView()).getRotationManager().isPortrait() || ((NVScanView) getView()).getRotationManager().isTablet());
            ((NVScanView) getView()).invalidateDrawView();
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
            selectionModel.getSelectedDoctype().setDocumentScanSide(ScanSide.FRONT);
            DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
            this.f4750h.removeCallbacks(this.f4749g);
            this.f4750h.postDelayed(this.f4749g, 10000);
        }
    }

    /* renamed from: i */
    public boolean mo46829i() {
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
        return selectionModel.getSelectedDoctype().getDocumentScanSide() == this.f4743a.getSideToScan() && selectionModel.getSelectedDoctype().hasFallbackScan();
    }

    /* renamed from: j */
    public void mo46830j() {
        boolean z = true;
        if (!DocumentScanMode.CSSN.equals(this.f4743a.getScanMode()) && !DocumentScanMode.MANUAL.equals(this.f4743a.getScanMode())) {
            ScanSide sideToScan = this.f4743a.getSideToScan();
            DocumentScanMode scanMode = this.f4743a.getScanMode();
            int partIndex = this.f4743a.getPartIndex();
            boolean i = mo46829i();
            if (i && getView() != null) {
                DocumentScanMode documentScanMode = DocumentScanMode.CSSN;
                SelectionModel selectionModel = (SelectionModel) DataAccess.load(((NVScanView) getView()).getContext(), SelectionModel.class);
                if (selectionModel != null) {
                    if (selectionModel.getSelectedDoctype() != null) {
                        selectionModel.getSelectedDoctype().setDocumentScanMode(documentScanMode);
                        selectionModel.getSelectedDoctype().setDocumentScanSide(ScanSide.FRONT);
                    }
                    if (selectionModel.getUploadModel() != null) {
                        if (!this.f4747e) {
                            this.f4743a = new NVScanPartModel(ScanSide.FRONT, documentScanMode, partIndex);
                            selectionModel.getUploadModel().remove(sideToScan);
                            selectionModel.getUploadModel().add(this.f4743a);
                        } else if (sideToScan == ScanSide.FRONT) {
                            this.f4743a = new NVScanPartModel(ScanSide.FRONT, documentScanMode, partIndex);
                            selectionModel.getUploadModel().replace(sideToScan, this.f4743a);
                        } else {
                            this.f4743a = new NVScanPartModel(ScanSide.BACK, DocumentScanMode.LINEFINDER, partIndex);
                            selectionModel.getUploadModel().replace(sideToScan, this.f4743a);
                            if (selectionModel.getUploadModel().getScan(ScanSide.FRONT) != null) {
                                selectionModel.getUploadModel().getScan(ScanSide.FRONT).setScanMode(documentScanMode);
                            }
                        }
                    }
                    DataAccess.update(((NVScanView) getView()).getContext(), SelectionModel.class, selectionModel);
                }
                if (!this.f4747e || scanMode != DocumentScanMode.PDF417 || !i || sideToScan == ScanSide.FRONT) {
                    Plugin plugin = PluginRegistry.getPlugin(this.f4743a.getScanMode());
                    this.mExtractionClient = plugin.getExtractionClient(((NVScanView) getView()).getContext());
                    this.mExtractionClient.configure(this.f4743a);
                    this.mExtractionClient.subscribe(this);
                    this.mExtractionClient.setFlags(((NVScanView) getView()).getRotationManager().isPortrait(), ((NVScanView) getView()).getRotationManager().isTablet(), ((NVScanView) getView()).getRotationManager().isInverted());
                    this.mExtractionClient.setPreviewProperties(this.mPreviewProperties);
                    this.mExtractionClient.reinit();
                    if (this.mOverlay != null) {
                        this.mOverlay.setVisible(8);
                        this.mOverlay = plugin.getOverlay(((NVScanView) getView()).getContext(), null);
                        this.mOverlay.addViews(this.f4746d);
                        this.mOverlay.calculate(this.f4743a.getScanMode(), this.viewWidth, this.viewHeight);
                        this.mOverlay.prepareDraw(this.f4743a.getSideToScan(), this.cameraManager.isFrontFacing(), ((NVScanView) getView()).getRotationManager().isPortrait() || ((NVScanView) getView()).getRotationManager().isTablet());
                        ((NVScanView) getView()).invalidateDrawView();
                    }
                    ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load(((NVScanView) getView()).getContext(), ServerSettingsModel.class);
                    NVScanView nVScanView = (NVScanView) getView();
                    if (serverSettingsModel == null || !serverSettingsModel.isBrandingEnabled()) {
                        z = false;
                    }
                    nVScanView.updateBranding(z);
                    onUpdate(new ExtractionUpdate(ExtractionUpdateState.receiveClickListener, new C4906b()));
                    return;
                }
                m2966b(true);
            }
        }
    }
}
