package jumio.p317nv.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.Log.LogLevel;
import com.jumio.commons.log.LogUtils;
import com.jumio.core.ImageQuality;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.barcode.decoder.PDF417Data;
import com.jumio.p311nv.barcode.decoder.PDF417DataDecoder;
import com.jumio.p311nv.barcode.enums.BarcodeFormat;
import com.jumio.p311nv.barcode.environment.BarcodeEnvironment;
import com.jumio.p311nv.barcode.environment.RecognizerWrapper;
import com.jumio.p311nv.barcode.environment.RecognizerWrapper.C4446a;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.utils.NetverifyLogUtils;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/* renamed from: jumio.nv.barcode.b */
/* compiled from: BarcodeClient */
public class C4881b extends ExtractionClient<ExtractionUpdate, DocumentDataModel> {

    /* renamed from: a */
    private C4883a f4694a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f4695b = false;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public RecognizerWrapper f4696c;

    /* renamed from: d */
    private long f4697d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Point[] f4698e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f4699f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f4700g;

    /* renamed from: h */
    private DocumentScanMode f4701h;

    /* renamed from: jumio.nv.barcode.b$a */
    /* compiled from: BarcodeClient */
    class C4883a extends Thread {

        /* renamed from: b */
        private byte[] f4704b;

        public C4883a(byte[] bArr) {
            this.f4704b = Arrays.copyOf(bArr, bArr.length);
        }

        public void run() {
            int i;
            int i2;
            boolean z;
            Bitmap yuv2bitmap;
            int i3;
            int i4;
            boolean z2 = false;
            if (C4881b.this.mIsPortrait) {
                int i5 = C4881b.this.getPreviewProperties().preview.height;
                i = C4881b.this.getPreviewProperties().preview.width;
                i2 = i5;
            } else {
                int i6 = C4881b.this.getPreviewProperties().preview.width;
                i = C4881b.this.getPreviewProperties().preview.height;
                i2 = i6;
            }
            float calculateFocus = ImageQuality.calculateFocus(this.f4704b, i2, i, false);
            C4881b.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.notifyFlash, Boolean.valueOf(ImageQuality.isFlashNeeded(this.f4704b, i2, i, false))));
            C4881b.this.f4698e = null;
            String recognize = C4881b.this.f4696c.recognize(this.f4704b, i2, i, i2);
            if (!(recognize == null || recognize.length() == 0 || recognize.startsWith("Result error:"))) {
                z2 = true;
            }
            if (z2) {
                if (C4881b.this.f4698e == null || C4881b.this.f4699f == 0 || C4881b.this.f4700g == 0) {
                    yuv2bitmap = CameraUtils.yuv2bitmap(this.f4704b, C4881b.this.mIsPortrait, C4881b.this.getPreviewProperties());
                    i3 = i;
                    i4 = i2;
                } else {
                    C4880a aVar = new C4880a(C4881b.this.f4698e, C4881b.this.f4699f, C4881b.this.f4700g);
                    yuv2bitmap = C4881b.this.m2923a(this.f4704b, aVar.mo46784a(), 5);
                    i3 = C4881b.this.mIsPortrait ? aVar.mo46784a().height() : aVar.mo46784a().width();
                    i4 = C4881b.this.mIsPortrait ? aVar.mo46784a().width() : aVar.mo46784a().height();
                }
                z = C4881b.this.mo46788a(recognize, this.f4704b, yuv2bitmap, i4, i3, calculateFocus);
            } else {
                Log.m1909d("Photopay", recognize);
                z = z2;
            }
            synchronized (C4881b.this) {
                this.f4704b = null;
                C4881b.this.f4695b = z;
            }
        }
    }

    public C4881b(Context context) {
        super(context);
        BarcodeEnvironment.loadPhotopayNativeAPILib();
        this.f4696c = new RecognizerWrapper();
    }

    public void configure(StaticModel staticModel) {
        if (!(staticModel instanceof NVScanPartModel)) {
            throw new IllegalArgumentException("Configuration model should be an instance of ScanPartModel");
        }
        ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load(this.mContext, ServerSettingsModel.class);
        this.f4701h = ((NVScanPartModel) staticModel).getScanMode();
        try {
            String init = this.f4696c.init(serverSettingsModel.getBarcodeScannerKey(), BarcodeFormat.PDF417.getType());
            this.f4696c.mo43356a(new C4446a() {
                /* renamed from: a */
                public void mo43359a(Point[] pointArr, int i, int i2) {
                    C4881b.this.f4698e = pointArr;
                    C4881b.this.f4700g = i2;
                    C4881b.this.f4699f = i;
                }
            });
            if (init == null || init.length() == 0) {
                this.f4697d = System.currentTimeMillis();
                return;
            }
            throw new Exception(init);
        } catch (Exception e) {
            this.f4696c = null;
            publishError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.f4695b = false;
    }

    public void destroy() {
        cancel();
        if (this.f4696c != null) {
            this.f4696c.mo43355a();
        }
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        synchronized (this) {
            if (!this.f4695b && this.f4696c != null) {
                this.f4695b = true;
                this.f4694a = new C4883a(bArr);
                this.f4694a.start();
            }
        }
    }

    public void cancel() {
        super.cancel();
        synchronized (this) {
            if (this.f4694a != null) {
                this.f4694a.interrupt();
            }
        }
    }

    /* renamed from: a */
    public boolean mo46788a(String str, byte[] bArr, Bitmap bitmap, int i, int i2, float f) {
        DocumentDataModel documentDataModel = new DocumentDataModel();
        if (this.f4701h == DocumentScanMode.PDF417) {
            PDF417DataDecoder pDF417DataDecoder = new PDF417DataDecoder();
            String simpleName = getClass().getSimpleName();
            try {
                if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                    NetverifyLogUtils.logInfoInSubfolder(str, simpleName, "rawdata.text");
                }
                PDF417Data decode = pDF417DataDecoder.decode(str);
                documentDataModel.setIdNumber(decode.getIdNumber());
                documentDataModel.setIssuingDate(decode.getIssueDate());
                documentDataModel.setExpiryDate(decode.getExpiryDate());
                String issuingCountry = decode.getIssuingCountry();
                if (issuingCountry == null || issuingCountry.length() == 0) {
                    issuingCountry = documentDataModel.getSelectedCountry();
                }
                documentDataModel.setIssuingCountry(issuingCountry);
                documentDataModel.setLastName(decode.getLastName());
                documentDataModel.setFirstName(decode.getFirstName());
                documentDataModel.setMiddleName(decode.getMiddleName());
                documentDataModel.setDob(decode.getDateOfBirth());
                documentDataModel.setGender(decode.getGender());
                documentDataModel.setAddressLine(decode.getAddressStreet1());
                documentDataModel.setCity(decode.getAddressCity());
                documentDataModel.setSubdivision(decode.getAddressState());
                documentDataModel.setPostCode(decode.getAddressZip());
                if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Image dimensions:").append("\r\n");
                    sb.append("width: " + i);
                    sb.append("height: " + i2);
                    sb.append("\r\n").append("\r\n");
                    sb.append("Raw Data:").append("\r\n");
                    String str2 = "";
                    sb.append(str.replaceAll(String.format("[^\\x20-\\x7E%s]+", new Object[]{str2}), "<Binary Data>"));
                    sb.append("\r\n").append("\r\n");
                    sb.append("Result:").append("\r\n");
                    LogUtils.appendParameter(sb, "getIdNumber() ", decode.getIdNumber());
                    LogUtils.appendParameter(sb, "getIssueDate() ", decode.getIssueDate());
                    LogUtils.appendParameter(sb, "getExpiryDate() ", decode.getExpiryDate());
                    LogUtils.appendParameter(sb, "getIssuingCountry() ", decode.getIssuingCountry());
                    LogUtils.appendParameter(sb, "getLastName() ", decode.getLastName());
                    LogUtils.appendParameter(sb, "getFirstName() ", decode.getFirstName());
                    LogUtils.appendParameter(sb, "getMiddleName() ", decode.getMiddleName());
                    LogUtils.appendParameter(sb, "getDateOfBirth() ", decode.getDateOfBirth());
                    if (decode.getGender() != null) {
                        LogUtils.appendParameter(sb, "getGender() ", decode.getGender().name());
                    }
                    LogUtils.appendParameter(sb, "getAddressStreet1() ", decode.getAddressStreet1());
                    LogUtils.appendParameter(sb, "getAddressCity() ", decode.getAddressCity());
                    LogUtils.appendParameter(sb, "getAddressState() ", decode.getAddressState());
                    LogUtils.appendParameter(sb, "getAddressZip() ", decode.getAddressZip());
                    sb.append("\r\n").append("\r\n");
                    sb.append("Unused:").append("\r\n");
                    LogUtils.appendParameter(sb, "getAddressStreet2() ", decode.getAddressStreet2());
                    LogUtils.appendParameter(sb, "getEndorsementCodes() ", decode.getEndorsementCodes());
                    if (decode.getEyeColor() != null) {
                        LogUtils.appendParameter(sb, "getEyeColor() ", decode.getEyeColor().name());
                    }
                    LogUtils.appendParameter(sb, "getHeight() ", decode.getHeight());
                    LogUtils.appendParameter(sb, "getRestrictionCodes() ", decode.getRestrictionCodes());
                    LogUtils.appendParameter(sb, "getVehicleClass() ", decode.getVehicleClass());
                    sb.append("\r\n").append("\r\n");
                    sb.append("Unparsed:").append("\r\n");
                    sb.append(decode.getUnparsedData().toString().replaceAll(String.format("[^\\x20-\\x7E%s]+", new Object[]{str2}), "<Binary Data>"));
                    if (decode.getIdNumber() != null) {
                        decode.getIdNumber();
                    } else {
                        Long.toString(this.f4697d);
                    }
                    NetverifyLogUtils.logInfoInSubfolder(sb.toString(), simpleName, null);
                }
            } catch (Exception e) {
                if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Raw Data:").append("\r\n");
                    sb2.append(str);
                    sb2.append("\r\n").append("\r\n");
                    sb2.append("Result:").append("\r\n");
                    StringWriter stringWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(stringWriter));
                    sb2.append(stringWriter.toString());
                    NetverifyLogUtils.logInfoInSubfolder(sb2.toString(), simpleName, null);
                }
                return false;
            }
        }
        System.gc();
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(this.mContext, SelectionModel.class);
        String addressLine = documentDataModel.getAddressLine();
        String city = documentDataModel.getCity();
        if (selectionModel == null || selectionModel.isVerificationRequired() || !"USA".equals(selectionModel.getSelectedCountry().getIsoCode()) || this.f4701h != DocumentScanMode.PDF417 || !(addressLine == null || addressLine.length() == 0 || city == null || city.length() == 0)) {
            publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(f)));
        }
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, bitmap));
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, bitmap));
        publishResult(documentDataModel);
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public Bitmap m2923a(byte[] bArr, Rect rect, int i) {
        int i2;
        int i3;
        int i4;
        int height;
        int width;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (this.mIsPortrait) {
            i2 = getPreviewProperties().preview.height;
            i3 = getPreviewProperties().preview.width;
            i4 = rect.top;
            i5 = rect.left;
            height = rect.width();
            width = rect.height();
            i6 = height;
            i7 = width;
        } else {
            i2 = getPreviewProperties().preview.width;
            i3 = getPreviewProperties().preview.height;
            i4 = rect.top;
            height = rect.height();
            width = rect.width();
            i5 = rect.left;
            i6 = width;
            i7 = height;
        }
        if (i > 0) {
            int i10 = (int) (((float) i6) * (((float) i) / 100.0f));
            int i11 = (int) (((float) i7) * (((float) i) / 100.0f));
            int max = Math.max(0, i5 - i10);
            int max2 = Math.max(0, i4 - i11);
            int i12 = i6 + (i10 * 2);
            if (i12 + max > i2) {
                i12 = (i2 - max) - 1;
            }
            int i13 = i7 + (i11 * 2);
            if (i13 + max2 > i3) {
                i13 = (i3 - max2) - 1;
            }
            if (this.mIsPortrait) {
                i8 = i12;
                i9 = i13;
            } else {
                i8 = i13;
                i9 = i12;
            }
            return CameraUtils.yuv2bitmap(bArr, this.mIsPortrait, getPreviewProperties(), i12, i13, max, max2, i9, i8);
        }
        return CameraUtils.yuv2bitmap(bArr, this.mIsPortrait, getPreviewProperties(), i6, i7, i5, i4, width, height);
    }
}
