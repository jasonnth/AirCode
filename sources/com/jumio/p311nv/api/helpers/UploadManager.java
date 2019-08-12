package com.jumio.p311nv.api.helpers;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.jumio.commons.camera.ImageData;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.ImageUtil;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.models.UploadDataModel;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.models.CredentialsModel;
import jumio.p317nv.core.C4884a;
import jumio.p317nv.core.C4918b;
import jumio.p317nv.core.C4920c;
import jumio.p317nv.core.C4921d;

/* renamed from: com.jumio.nv.api.helpers.UploadManager */
public class UploadManager extends Publisher<Boolean> {

    /* renamed from: a */
    private byte[] f3315a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f3316b;

    /* renamed from: c */
    private ScanSide f3317c;

    /* renamed from: d */
    private CredentialsModel f3318d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f3319e;

    /* renamed from: f */
    private C4440c f3320f;

    /* renamed from: g */
    private C4438a f3321g;

    /* renamed from: h */
    private C4439b f3322h;

    /* renamed from: com.jumio.nv.api.helpers.UploadManager$a */
    class C4438a implements Subscriber<Void> {
        private C4438a() {
        }

        @InvokeOnUiThread(false)
        /* renamed from: a */
        public void onResult(Void voidR) {
            UploadManager.this.onResult(voidR);
        }

        public void onError(Throwable th) {
            UploadManager.this.onError(th, C4918b.class);
        }
    }

    /* renamed from: com.jumio.nv.api.helpers.UploadManager$b */
    class C4439b implements Subscriber<Void> {
        private C4439b() {
        }

        @InvokeOnUiThread(false)
        /* renamed from: a */
        public void onResult(Void voidR) {
            if (UploadManager.this.f3319e) {
                UploadManager.this.onResult(voidR);
                return;
            }
            NetverifyDocumentData netverifyDocumentData = new NetverifyDocumentData();
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(UploadManager.this.f3316b, SelectionModel.class);
            if (selectionModel != null) {
                selectionModel.populateData(netverifyDocumentData);
                if (!(selectionModel.getUploadModel() == null || selectionModel.getUploadModel().getDocumentData() == null)) {
                    selectionModel.getUploadModel().getDocumentData().populateData(netverifyDocumentData);
                    netverifyDocumentData.setExtractionMethod(selectionModel.getUploadModel().getExtractionMethod());
                }
            }
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(UploadManager.this.f3316b, InitiateModel.class);
            String str = initiateModel != null ? initiateModel.getJumioScanRef() : null;
            DataAccess.delete(UploadManager.this.f3316b, MerchantSettingsModel.class);
            DataAccess.delete(UploadManager.this.f3316b, ServerSettingsModel.class);
            DataAccess.delete(UploadManager.this.f3316b, SelectionModel.class);
            DataAccess.delete(UploadManager.this.f3316b, InitiateModel.class);
            UploadManager.this.f3316b.sendBroadcast(new C4884a(netverifyDocumentData, str));
        }

        public void onError(Throwable th) {
            UploadManager.this.onError(th, C4920c.class);
        }
    }

    /* renamed from: com.jumio.nv.api.helpers.UploadManager$c */
    class C4440c implements Subscriber<DocumentDataModel> {
        private C4440c() {
        }

        @InvokeOnUiThread(false)
        /* renamed from: a */
        public void onResult(DocumentDataModel documentDataModel) {
            if (UploadManager.this.f3319e) {
                UploadManager.this.onResult(documentDataModel);
                return;
            }
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(UploadManager.this.f3316b, SelectionModel.class);
            if (selectionModel == null) {
                throw new RuntimeException("NVScanPresenter#extractDataAndClose(): selectionmodel cannot be null!!");
            }
            UploadDataModel uploadModel = selectionModel.getUploadModel();
            NVScanPartModel activePart = selectionModel.getUploadModel().getActivePart();
            if (((NVScanPartModel) DataAccess.load(UploadManager.this.f3316b, "fallbackScanPartModel")) == null || uploadModel.getDocumentData() == null) {
                uploadModel.setDocumentData(activePart.getSideToScan(), documentDataModel);
            } else {
                uploadModel.getDocumentData().setAddressLine(documentDataModel.getAddressLine());
                uploadModel.getDocumentData().setCity(documentDataModel.getCity());
                uploadModel.getDocumentData().setPostCode(documentDataModel.getPostCode());
                uploadModel.getDocumentData().setSubdivision(documentDataModel.getSubdivision());
                uploadModel.add(activePart);
            }
            DataAccess.update(UploadManager.this.f3316b, SelectionModel.class, selectionModel);
            UploadManager.this.startData();
        }

        public void onError(Throwable th) {
            UploadManager.this.onError(th, C4921d.class);
        }
    }

    public UploadManager(Context context, ScanSide scanSide, CredentialsModel credentialsModel, boolean z) {
        this.f3316b = context;
        this.f3317c = scanSide;
        this.f3318d = credentialsModel;
        this.f3319e = z;
    }

    public void addSubscribers() {
        if (this.f3320f == null) {
            this.f3320f = new C4440c();
            NVBackend.registerForUpdates(this.f3316b, C4921d.class, this.f3320f);
        }
        if (this.f3321g == null) {
            this.f3321g = new C4438a();
            NVBackend.registerForUpdates(this.f3316b, C4918b.class, this.f3321g);
        }
        if (this.f3322h == null) {
            this.f3322h = new C4439b();
            NVBackend.registerForUpdates(this.f3316b, C4920c.class, this.f3322h);
        }
    }

    public void removeSubscribers() {
        if (this.f3320f != null) {
            NVBackend.unregisterFromUpdates(C4921d.class, this.f3320f);
        }
        if (this.f3321g != null) {
            NVBackend.unregisterFromUpdates(C4918b.class, this.f3321g);
        }
        if (this.f3322h != null) {
            NVBackend.unregisterFromUpdates(C4920c.class, this.f3322h);
        }
    }

    public void startAddPart(SelectionModel selectionModel) {
        NVScanPartModel scan = selectionModel.getUploadModel().getScan(this.f3317c);
        Log.m1919i("Network", "add part of " + selectionModel.getSelectedDoctype() + " on " + scan.getSideToScan());
        byte[] imageData = scan.getScannedImage().getImageData();
        this.f3321g = new C4438a();
        NVBackend.addPart(this.f3316b, this.f3318d, scan, null, imageData);
    }

    public void startData() {
        NVBackend.data(this.f3316b, this.f3318d, null);
    }

    public void startExtractData(SelectionModel selectionModel, ScanSide scanSide) {
        ImageData imageDataForPart = selectionModel.getUploadModel().getImageDataForPart(scanSide);
        if (imageDataForPart != null) {
            this.f3315a = ImageUtil.bitmapToFormat(BitmapFactory.decodeFile(imageDataForPart.getImagePath()), CompressFormat.JPEG, 80);
        }
        Log.m1919i("Network", "extract data of " + selectionModel.getSelectedDoctype() + " on " + scanSide);
        NVBackend.extractData(this.f3316b, this.f3318d, null, this.f3315a);
    }

    public void cancel() {
        NVBackend.cancelAllPending();
    }

    @InvokeOnUiThread(false)
    public void onResult(Object obj) {
        if (obj instanceof DocumentDataModel) {
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(this.f3316b, SelectionModel.class);
            if (selectionModel != null) {
                selectionModel.getUploadModel().setDocumentData(this.f3317c, (DocumentDataModel) obj);
                DataAccess.update(this.f3316b, SelectionModel.class, selectionModel);
            }
        }
        publishResult(Boolean.valueOf(true));
    }

    public void onError(Throwable th, Class<?> cls) {
        publishError(NVBackend.errorFromThrowable(this.f3316b, th, cls));
    }
}
