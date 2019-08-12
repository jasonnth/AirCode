package jumio.p317nv.core;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.commons.log.Log;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.core.plugins.PluginRegistry.PluginMode;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.models.CountryDocumentModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.models.TemplateModel;
import com.jumio.p311nv.view.interactors.SelectionView;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: jumio.nv.core.ah */
/* compiled from: SelectionPresenter */
public class C4907ah extends Presenter<SelectionView> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public MerchantSettingsModel f4767a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public ServerSettingsModel f4768b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public List<Country> f4769c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public SelectionModel f4770d;

    /* renamed from: e */
    private C4911a f4771e;

    /* renamed from: f */
    private C4912b f4772f;

    /* renamed from: g */
    private boolean f4773g;

    /* renamed from: h */
    private boolean f4774h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public long f4775i = 0;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public ConditionVariable f4776j = new ConditionVariable(false);

    /* renamed from: jumio.nv.core.ah$a */
    /* compiled from: SelectionPresenter */
    class C4911a implements Subscriber<String> {
        private C4911a() {
        }

        @InvokeOnUiThread
        /* renamed from: a */
        public void onResult(String str) {
            if (!C4907ah.this.isActive()) {
            }
        }

        @InvokeOnUiThread
        public void onError(Throwable th) {
            if (C4907ah.this.isActive()) {
                JumioException errorFromThrowable = NVBackend.errorFromThrowable(((SelectionView) C4907ah.this.getView()).getContext(), th, C4923f.class);
                if (!errorFromThrowable.getErrorCase().retry()) {
                    ((SelectionView) C4907ah.this.getView()).onError(errorFromThrowable);
                }
            }
        }
    }

    /* renamed from: jumio.nv.core.ah$b */
    /* compiled from: SelectionPresenter */
    class C4912b implements Subscriber<String> {

        /* renamed from: b */
        private ServerSettingsModel f4783b = new ServerSettingsModel();

        public C4912b() {
        }

        @InvokeOnUiThread(false)
        /* renamed from: a */
        public void onResult(String str) {
            if (C4907ah.this.getView() != null) {
                this.f4783b.onResult(str);
                DataAccess.update(((SelectionView) C4907ah.this.getView()).getContext(), ServerSettingsModel.class, this.f4783b);
                Log.m1924v("SelectionPresenter", "Settings model finally loaded, removing myself");
                NVBackend.unregisterFromUpdates(C4930j.class, this);
                if (C4907ah.this.f4767a.isSendDebugInfo() || this.f4783b.isAnalyticsEnabled()) {
                    NVBackend.unlockAnalytics(((SelectionView) C4907ah.this.getView()).getContext(), ((SelectionView) C4907ah.this.getView()).getCredentialsModel());
                } else {
                    JumioAnalytics.disable();
                }
                C4907ah.this.f4768b = this.f4783b;
                C4907ah.this.mo46839b();
            }
        }

        @InvokeOnUiThread
        public void onError(Throwable th) {
            if (C4907ah.this.isActive()) {
                ((SelectionView) C4907ah.this.getView()).onError(NVBackend.errorFromThrowable(((SelectionView) C4907ah.this.getView()).getContext(), th, C4930j.class));
            }
        }
    }

    /* renamed from: jumio.nv.core.ah$c */
    /* compiled from: SelectionPresenter */
    class C4913c implements Subscriber<Void> {
        private C4913c() {
        }

        /* renamed from: a */
        public void onResult(Void voidR) {
            C4907ah.this.f4776j.open();
        }

        public void onError(Throwable th) {
            C4907ah.this.f4776j.open();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        if (this.f4767a == null) {
            Log.m1909d("SelectionPresenter", "loading merchant settings from persistence");
            this.f4767a = (MerchantSettingsModel) DataAccess.load(((SelectionView) getView()).getContext(), MerchantSettingsModel.class);
            if (this.f4767a == null) {
                this.f4767a = new MerchantSettingsModel();
            }
        }
        SelectionModel selectionModel = (SelectionModel) DataAccess.load(((SelectionView) getView()).getContext(), SelectionModel.class);
        this.f4770d = selectionModel;
        if (selectionModel == null) {
            this.f4770d = new SelectionModel();
            DataAccess.update(((SelectionView) getView()).getContext(), SelectionModel.class, this.f4770d);
        }
        if (this.f4768b == null) {
            Log.m1909d("SelectionPresenter", "loading server settings from persistence");
            this.f4768b = (ServerSettingsModel) DataAccess.load(((SelectionView) getView()).getContext(), ServerSettingsModel.class);
        }
        if (this.f4768b == null || !this.f4768b.isLoaded()) {
            this.f4772f = new C4912b();
            NVBackend.registerForUpdates(((SelectionView) getView()).getContext(), C4930j.class, this.f4772f);
            if (this.f4767a.isCountryPreSelected()) {
                ArrayList arrayList = new ArrayList(this.f4767a.getSupportedDocumentTypes());
                arrayList.remove(NVDocumentType.PASSPORT);
                arrayList.remove(NVDocumentType.VISA);
                if (arrayList.size() != 0) {
                    this.f4776j.close();
                    this.f4775i = System.currentTimeMillis();
                    Country country = new Country(this.f4767a.getIsoCode());
                    TemplateModel templateModel = new TemplateModel(((SelectionView) getView()).getContext());
                    templateModel.add(new C4913c());
                    templateModel.getOrLoad(country, (NVDocumentType[]) arrayList.toArray(new NVDocumentType[arrayList.size()]));
                    return;
                }
                return;
            }
            this.f4776j.open();
            return;
        }
        this.f4776j.open();
        ((SelectionView) getView()).hideLoading(false);
        mo46839b();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.f4771e = new C4911a();
        NVBackend.registerForUpdates(((SelectionView) getView()).getContext(), C4923f.class, this.f4771e);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.f4772f != null) {
            NVBackend.unregisterFromUpdates(C4930j.class, this.f4772f);
        }
        DataAccess.update(((SelectionView) getView()).getContext(), SelectionModel.class, this.f4770d);
        NVBackend.unregisterFromUpdates(C4923f.class, this.f4771e);
    }

    /* renamed from: a */
    public void mo46837a(DocumentType documentType, NVDocumentVariant nVDocumentVariant) {
        this.f4773g = true;
        this.f4770d.setSelectedDoctype(documentType);
        this.f4770d.setSelectedVariant(nVDocumentVariant);
        m3004e();
    }

    /* renamed from: a */
    public void mo46836a() {
        NVBackend.forceRetry();
    }

    /* renamed from: a */
    public void mo46838a(JumioException jumioException) {
        InitiateModel initiateModel = (InitiateModel) DataAccess.load(((SelectionView) getView()).getContext(), InitiateModel.class);
        ((SelectionView) getView()).getContext().sendBroadcast(new C4884a(jumioException.getErrorCase().code(), jumioException.getDetailedErrorCase(), jumioException.getErrorCase().localizedMessage(((SelectionView) getView()).getContext()), initiateModel != null ? initiateModel.getJumioScanRef() : null));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo46839b() {
        boolean z;
        this.f4773g = false;
        this.f4774h = false;
        this.f4770d.setVerificationRequired(this.f4768b.isVerificationAllowed() && this.f4767a.requireVerification());
        CountryDocumentModel countryModel = this.f4768b.getCountryModel();
        boolean isVerificationRequired = this.f4770d.isVerificationRequired();
        if (this.f4770d.isVerificationRequired() || !this.f4767a.isDataExtractionOnMobileOnly()) {
            z = false;
        } else {
            z = true;
        }
        this.f4769c = countryModel.getCountriesFor(isVerificationRequired, z, this.f4767a.getSupportedDocumentTypes(), this.f4767a.getDocumentVariant());
        Collections.sort(this.f4769c);
        InitiateModel initiateModel = (InitiateModel) DataAccess.load(((SelectionView) getView()).getContext(), InitiateModel.class);
        if (initiateModel == null || initiateModel.getJumioScanRef() == null) {
            NVBackend.initiate(((SelectionView) getView()).getContext(), ((SelectionView) getView()).getCredentialsModel(), this.f4767a, this.f4768b, null);
        }
        Country c = m2999c();
        this.f4770d.setSelectedCountry(c);
        this.f4770d.setSelectedDoctype(null);
        this.f4770d.setSelectedVariant(null);
        if (c != null) {
            List documentTypesFor = this.f4768b.getCountryModel().getDocumentTypesFor(c, this.f4770d.isVerificationRequired(), !this.f4770d.isVerificationRequired() && this.f4767a.isDataExtractionOnMobileOnly(), this.f4767a.getSupportedDocumentTypes(), this.f4767a.getDocumentVariant());
            if (documentTypesFor.size() == 1) {
                this.f4770d.setSelectedDoctype((DocumentType) documentTypesFor.get(0));
            }
            if (this.f4770d.getSelectedDoctype() != null) {
                if (this.f4767a.isDocumentVariantPreSelected() && this.f4770d.getSelectedDoctype().isDocumentVariantAccepted(this.f4767a.getDocumentVariant())) {
                    this.f4770d.setSelectedVariant(this.f4767a.getDocumentVariant());
                } else if (!this.f4770d.getSelectedDoctype().hasPaperVariant()) {
                    this.f4770d.setSelectedVariant(NVDocumentVariant.PLASTIC);
                }
            }
            if (this.f4767a.isCountryPreSelected() && this.f4767a.getIsoCode().equals(this.f4770d.getSelectedCountry().getIsoCode()) && m3001d()) {
                this.f4773g = true;
                this.f4774h = true;
            }
        }
        final Handler handler = new Handler(Looper.getMainLooper());
        if (!m3001d() || !this.f4773g) {
            handler.post(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    Country selectedCountry = C4907ah.this.f4770d.getSelectedCountry();
                    boolean z3 = selectedCountry != null && selectedCountry.getIsoCode().equals(C4907ah.this.f4767a.getIsoCode());
                    SelectionView selectionView = (SelectionView) C4907ah.this.getView();
                    List i = C4907ah.this.f4769c;
                    if (selectedCountry == null || !z3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    selectionView.onCountriesReceived(i, selectedCountry, z);
                    if (selectedCountry != null) {
                        CountryDocumentModel countryModel = C4907ah.this.f4768b.getCountryModel();
                        boolean isVerificationRequired = C4907ah.this.f4770d.isVerificationRequired();
                        if (!C4907ah.this.f4770d.isVerificationRequired() && C4907ah.this.f4767a.isDataExtractionOnMobileOnly()) {
                            z2 = true;
                        }
                        ((SelectionView) C4907ah.this.getView()).onDocumentTypesReceived(countryModel.getDocumentTypesFor(selectedCountry, isVerificationRequired, z2, C4907ah.this.f4767a.getSupportedDocumentTypes(), C4907ah.this.f4767a.getDocumentVariant()), C4907ah.this.f4767a.getDocumentVariant());
                    }
                    ((SelectionView) C4907ah.this.getView()).hideLoading(true);
                }
            });
        } else {
            new Thread(new Runnable() {
                public void run() {
                    C4907ah.this.f4776j.block(5000 - (System.currentTimeMillis() - C4907ah.this.f4775i));
                    handler.post(new Runnable() {
                        public void run() {
                            C4907ah.this.m3004e();
                        }
                    });
                }
            }).start();
        }
    }

    /* renamed from: c */
    private Country m2999c() {
        Country country;
        Country country2;
        if (this.f4770d.getSelectedCountry() != null) {
            country = null;
            for (Country isoCode : this.f4769c) {
                if (isoCode.getIsoCode().equals(this.f4770d.getSelectedCountry().getIsoCode())) {
                    country2 = this.f4770d.getSelectedCountry();
                } else {
                    country2 = country;
                }
                country = country2;
            }
        } else {
            String isoCode2 = this.f4767a.getIsoCode();
            country = (!this.f4767a.isCountryPreSelected() || !m2997b(isoCode2)) ? m2997b(this.f4768b.getCountryForIp()) ? m2994a(this.f4768b.getCountryForIp()) : null : m2994a(isoCode2);
        }
        if (country != null && PluginRegistry.hasPlugin(PluginMode.TEMPLATE_MATCHER)) {
            new TemplateModel(((SelectionView) getView()).getContext()).getOrLoad(country, null);
        }
        return country;
    }

    /* renamed from: a */
    private Country m2994a(String str) {
        for (Country country : this.f4769c) {
            if (country.getIsoCode().equals(str)) {
                return country;
            }
        }
        return null;
    }

    /* renamed from: b */
    private boolean m2997b(String str) {
        return m2994a(str) != null;
    }

    /* renamed from: d */
    private boolean m3001d() {
        return (this.f4770d == null || this.f4770d.getSelectedCountry() == null || this.f4770d.getSelectedDoctype() == null || this.f4770d.getSelectedVariant() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m3004e() {
        if (this.f4770d != null) {
            this.f4770d.setUploadModel(null);
            if (m3001d()) {
                m3006f();
            }
        }
    }

    /* renamed from: f */
    private void m3006f() {
        this.f4770d.buildUploadModel(((SelectionView) getView()).getContext());
        NVScanPartModel nVScanPartModel = (NVScanPartModel) this.f4770d.getUploadModel().getScans().get(0);
        DataAccess.delete(((SelectionView) getView()).getContext(), "fallbackScanPartModel");
        DataAccess.update(((SelectionView) getView()).getContext(), NVScanPartModel.class, nVScanPartModel);
        this.f4770d.getUploadModel().setActivePart(0);
        DataAccess.update(((SelectionView) getView()).getContext(), SelectionModel.class, this.f4770d);
        if (!this.f4774h) {
            MetaInfo metaInfo = new MetaInfo();
            metaInfo.put("country", this.f4770d.getSelectedCountry().getIsoCode());
            metaInfo.put("idType", this.f4770d.getSelectedDoctype().toString());
            metaInfo.put("idStyle", this.f4770d.getSelectedVariant().toString());
            JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.SCAN_OPTIONS, UserAction.SCAN_OPTIONS_COMPLETED, metaInfo));
        }
        ((SelectionView) getView()).jumpToScanFragment(this.f4774h);
    }
}
