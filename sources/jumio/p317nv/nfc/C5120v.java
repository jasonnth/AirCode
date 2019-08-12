package jumio.p317nv.nfc;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.log.Log;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.PublisherWithUpdate;
import com.jumio.core.mvp.model.SubscriberWithUpdate;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.p311nv.NetverifyMrzData;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.UploadDataModel;
import com.jumio.p311nv.nfc.core.communication.TagAccessSpec;
import com.jumio.persistence.DataAccess;
import java.util.ArrayList;
import java.util.Iterator;
import org.jmrtd.lds.MRZInfo;

/* renamed from: jumio.nv.nfc.v */
/* compiled from: NfcPresenter */
public class C5120v extends Presenter<C5119u> {

    /* renamed from: a */
    private TagAccessSpec f5656a = new TagAccessSpec();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public ArrayList<C5112o> f5657b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String f5658c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C5111n f5659d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Bitmap f5660e;

    /* renamed from: f */
    private NfcController f5661f;

    /* renamed from: g */
    private C5124a f5662g = new C5124a();

    /* renamed from: jumio.nv.nfc.v$a */
    /* compiled from: NfcPresenter */
    class C5124a implements SubscriberWithUpdate<C5112o, C5112o> {

        /* renamed from: b */
        private boolean f5668b;

        /* renamed from: c */
        private String f5669c;

        private C5124a() {
            this.f5669c = null;
        }

        @InvokeOnUiThread
        /* renamed from: a */
        public void onUpdate(C5112o oVar) {
            int ordinal;
            if (C5120v.this.isActive()) {
                if (oVar.mo47202a() == C5113p.INIT) {
                    this.f5668b = false;
                    C5100f.m3694a("NfcPresenter", "onStarted");
                    C5120v.this.f5657b = new ArrayList();
                    this.f5669c = null;
                    ((C5119u) C5120v.this.getView()).mo47226c();
                    JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN, UserAction.NFC_EXTRACTION_STARTED));
                    return;
                }
                C5120v.this.f5657b.add(oVar);
                switch (oVar.mo47202a()) {
                    case BAC_CHECK:
                    case READ_LDS:
                    case PASSIVE_AUTH_DSC_CHECK:
                    case PASSIVE_AUTH_ROOT_CERT_CHECK:
                    case PASSIVE_AUTH_HASH_CHECK:
                    case ACTIVE_AUTH_CHECK:
                    case ADDITIONAL_DATA:
                    case FACE_IMAGE:
                        ordinal = oVar.mo47202a().ordinal();
                        break;
                    default:
                        ordinal = C5113p.values().length;
                        break;
                }
                ((C5119u) C5120v.this.getView()).mo47222a(ordinal);
                C5100f.m3694a("NfcPresenter", "onProgressUpdate: " + oVar.toString());
                if (oVar.mo47202a() == C5113p.READ_LDS) {
                    C5120v.this.f5658c = (String) oVar.mo47210e();
                    if (C5120v.this.f5658c != null) {
                        int length = C5120v.this.f5658c.length();
                        String substring = C5120v.this.f5658c.substring(0, length / 2);
                        String substring2 = C5120v.this.f5658c.substring(length / 2);
                        Log.m1919i("NfcPresenter", "MRZ line 1: " + substring);
                        Log.m1919i("NfcPresenter", "MRZ line 2: " + substring2);
                    }
                }
                if (oVar.mo47202a() == C5113p.FACE_IMAGE && oVar.mo47207b() == C5114q.SUCCESSFUL) {
                    C5120v.this.f5660e = (Bitmap) oVar.mo47210e();
                }
                if (oVar.mo47202a() == C5113p.ADDITIONAL_DATA) {
                    C5120v.this.f5659d = (C5111n) oVar.mo47210e();
                }
            }
        }

        @InvokeOnUiThread
        /* renamed from: b */
        public void onResult(C5112o oVar) {
            DocumentDataModel documentDataModel;
            UploadDataModel uploadDataModel = null;
            C5100f.m3694a("NfcPresenter", "onComplete");
            if (!this.f5668b) {
                StringBuilder sb = new StringBuilder("NFC Scan results\n");
                Iterator it = C5120v.this.f5657b.iterator();
                while (it.hasNext()) {
                    sb.append(((C5112o) it.next()).toString()).append("\n");
                }
                if (C5120v.this.f5659d != null) {
                    sb.append("\n").append(C5120v.this.f5659d.toString());
                }
                if (C5120v.this.f5658c != null) {
                    sb.append("\n").append(C5120v.this.f5658c);
                }
                C5100f.m3694a("NfcPresenter", sb.toString());
                final C5117t tVar = new C5117t(C5120v.this.f5657b, C5120v.this.f5659d, C5120v.this.f5658c);
                C5120v.this.m3809a(tVar);
                if (C5120v.this.isActive()) {
                    ((C5119u) C5120v.this.getView()).mo47227d();
                    SelectionModel selectionModel = (SelectionModel) DataAccess.load(((C5119u) C5120v.this.getView()).getContext(), SelectionModel.class);
                    if (selectionModel != null) {
                        uploadDataModel = selectionModel.getUploadModel();
                        documentDataModel = uploadDataModel.getDocumentData();
                    } else {
                        documentDataModel = null;
                    }
                    if (!(documentDataModel == null || uploadDataModel == null)) {
                        C5111n b = tVar.mo47220b();
                        MRZInfo c = b.mo47199c();
                        NetverifyMrzData mrzData = documentDataModel.getMrzData();
                        mrzData.setMrzLine1(c.getMrzLine1());
                        mrzData.setMrzLine2(c.getMrzLine2());
                        mrzData.setMrzLine3(c.getMrzLine3());
                        if (!(b.mo47194a() == null || b.mo47194a().f5615h == null)) {
                            documentDataModel.setIssuingDate(b.mo47194a().f5615h);
                        }
                        documentDataModel.setEPassportStatus(tVar.getVerificationStatus());
                        documentDataModel.setPlaceOfBirth(tVar.getPlaceOfBirth());
                        uploadDataModel.setMrtdData(tVar);
                    }
                    DataAccess.update(((C5119u) C5120v.this.getView()).getContext(), SelectionModel.class, selectionModel);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            C5120v.this.mo47229a(tVar.getVerificationStatus());
                        }
                    }, 2000);
                }
            }
        }

        @InvokeOnUiThread
        public void onError(Throwable th) {
            if (th instanceof C5112o) {
                C5112o oVar = (C5112o) th;
                this.f5668b = true;
                this.f5669c = oVar.toString();
                C5100f.m3694a("NfcPresenter", "onError: " + oVar.toString());
                ((C5119u) C5120v.this.getView()).mo47223a(oVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.f5661f = ((C5119u) getView()).mo47225b();
        this.f5661f.setTagAccess(this.f5656a);
        if (!this.f5661f.isNfcEnabled()) {
            m3813b();
        }
        if (this.f5661f instanceof PublisherWithUpdate) {
            ((PublisherWithUpdate) this.f5661f).subscribe(this.f5662g);
        }
        Log.m1909d("NfcPresenter", "onCreate() " + hashCode());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.m1909d("NfcPresenter", "onDestroy() " + hashCode());
        if (this.f5661f instanceof PublisherWithUpdate) {
            ((PublisherWithUpdate) this.f5661f).unsubscribe(this.f5662g);
        }
        if (this.f5660e != null) {
            this.f5660e.recycle();
            this.f5660e = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        mo47232a(this.f5661f.isNfcEnabled());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
    }

    /* renamed from: a */
    public void mo47231a(String str) {
    }

    /* renamed from: a */
    public TagAccessSpec mo47228a() {
        return this.f5656a;
    }

    /* renamed from: a */
    public void mo47230a(TagAccessSpec tagAccessSpec) {
        this.f5656a = tagAccessSpec;
    }

    /* renamed from: a */
    public void mo47232a(boolean z) {
        this.f5661f.setEnabled(z);
    }

    /* renamed from: a */
    public void mo47229a(EPassportStatus ePassportStatus) {
        if (isActive()) {
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((C5119u) getView()).getContext(), SelectionModel.class);
            selectionModel.getUploadModel().getDocumentData().setEPassportStatus(ePassportStatus);
            DataAccess.update(((C5119u) getView()).getContext(), SelectionModel.class, selectionModel);
            if (selectionModel.getUploadModel().hasNext()) {
                DataAccess.update(((C5119u) getView()).getContext(), NVScanPartModel.class, selectionModel.getUploadModel().nextPart());
                DataAccess.update(((C5119u) getView()).getContext(), SelectionModel.class, selectionModel);
                ((C5119u) getView()).mo47224a(false);
                return;
            }
            NVBackend.data(((C5119u) getView()).getContext(), ((C5119u) getView()).mo47221a(), null);
            NVBackend.finalizeCall(((C5119u) getView()).getContext(), ((C5119u) getView()).mo47221a(), null);
            ((C5119u) getView()).mo47224a(true);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3809a(C5117t tVar) {
        String str = "NfcFragment";
        if (tVar == null) {
            C5100f.m3698c(str, "no MrtdScanResult present");
            return;
        }
        for (C5112o oVar : tVar.mo47219a()) {
            if (oVar == null) {
                C5100f.m3698c(str, "result missing!");
            } else {
                C5100f.m3694a(str, oVar.mo47211f());
            }
        }
        if (tVar.mo47220b() != null) {
            C5100f.m3694a(str, tVar.mo47220b().toString());
        } else {
            C5100f.m3698c(str, "no additional data present!");
        }
        C5100f.m3694a(str, "MRZ: " + tVar.getMrzString());
    }

    /* renamed from: b */
    private void m3813b() {
        if (this.f5661f.hasNfcFeature() && !this.f5661f.isNfcEnabled()) {
            final Context context = ((C5119u) getView()).getContext();
            new Builder(context, 5).setTitle(NVStrings.getExternalString(context, NVStrings.NFC_ENABLE_DIALOG_TITLE)).setMessage(NVStrings.getExternalString(context, NVStrings.NFC_ENABLE_DIALOG_TEXT)).setPositiveButton(17039379, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    context.startActivity(new Intent("android.settings.NFC_SETTINGS"));
                }
            }).setNegativeButton(17039369, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    C5120v.this.mo47229a(EPassportStatus.NOT_PERFORMED);
                }
            }).show();
        }
    }
}
