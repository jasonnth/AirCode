package jumio.p317nv.core;

import android.os.Handler;
import com.jumio.commons.log.Log;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.api.helpers.UploadManager;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.MrtdDataModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.UploadDataModel;
import com.jumio.p311nv.view.interactors.UploadView;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioException;

/* renamed from: jumio.nv.core.ai */
/* compiled from: UploadPresenter */
public class C4914ai extends Presenter<UploadView> {

    /* renamed from: a */
    static final /* synthetic */ boolean f4785a = (!C4914ai.class.desiredAssertionStatus());
    /* access modifiers changed from: private */

    /* renamed from: b */
    public UploadManager f4786b;

    /* renamed from: c */
    private C4916a f4787c;

    /* renamed from: jumio.nv.core.ai$a */
    /* compiled from: UploadPresenter */
    class C4916a implements Subscriber<Void> {
        private C4916a() {
        }

        /* renamed from: a */
        public void onResult(Void voidR) {
            if (C4914ai.this.isActive()) {
                Log.m1909d("UploadPresenter", "finalize call finished");
                ((UploadView) C4914ai.this.getView()).uploadComplete();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        C4914ai.this.mo46846a();
                    }
                }, 2000);
            }
        }

        public void onError(Throwable th) {
            if (C4914ai.this.isActive()) {
                Log.m1909d("UploadPresenter", "finalize call finished");
                C4914ai.this.f4786b.onError(th, C4922e.class);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        Log.m1919i("UploadPresenter", "registering for updates");
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((UploadView) getView()).getContext(), SelectionModel.class);
        this.f4786b = new UploadManager(((UploadView) getView()).getContext(), selectionModel.getUploadModel().getActivePart().getSideToScan(), ((UploadView) getView()).getCredentialsModel(), selectionModel != null && selectionModel.isVerificationRequired());
        this.f4786b.addSubscribers();
        this.f4786b.add(new Subscriber<Boolean>() {
            /* renamed from: a */
            public void onResult(Boolean bool) {
            }

            public void onError(Throwable th) {
                if (C4914ai.this.isActive()) {
                    ((UploadView) C4914ai.this.getView()).onError(th);
                }
            }
        });
        this.f4787c = new C4916a();
        NVBackend.registerForUpdates(((UploadView) getView()).getContext(), C4922e.class, this.f4787c);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        Log.m1919i("UploadPresenter", "unregistering from updates");
        NVBackend.unregisterFromUpdates(C4922e.class, this.f4787c);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo46846a() {
        if (isActive()) {
            SelectionModel selectionModel = (SelectionModel) DataAccess.load(((UploadView) getView()).getContext(), SelectionModel.class);
            NetverifyDocumentData netverifyDocumentData = new NetverifyDocumentData();
            if (f4785a || selectionModel != null) {
                UploadDataModel uploadModel = selectionModel.getUploadModel();
                selectionModel.populateData(netverifyDocumentData);
                if (uploadModel.getDocumentData() != null) {
                    uploadModel.getDocumentData().populateData(netverifyDocumentData);
                }
                MrtdDataModel mrtdData = uploadModel.getMrtdData();
                if (mrtdData != null && mrtdData.getRootCertCheck() == 1) {
                    netverifyDocumentData.setEPassportStatus(EPassportStatus.NOT_AVAILABLE);
                }
                netverifyDocumentData.setExtractionMethod(uploadModel.getExtractionMethod());
                InitiateModel initiateModel = (InitiateModel) DataAccess.load(((UploadView) getView()).getContext(), InitiateModel.class);
                ((UploadView) getView()).getContext().sendBroadcast(new C4884a(netverifyDocumentData, initiateModel != null ? initiateModel.getJumioScanRef() : null));
                return;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    public void mo46848b() {
        NVBackend.forceRetry();
    }

    /* renamed from: a */
    public void mo46847a(JumioException jumioException) {
        InitiateModel initiateModel = (InitiateModel) DataAccess.load(((UploadView) getView()).getContext(), InitiateModel.class);
        ((UploadView) getView()).getContext().sendBroadcast(new C4884a(jumioException.getErrorCase().code(), jumioException.getDetailedErrorCase(), jumioException.getErrorCase().localizedMessage(((UploadView) getView()).getContext()), initiateModel != null ? initiateModel.getJumioScanRef() : null));
    }
}
