package com.jumio.p311nv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.common.util.UriUtil;
import com.jumio.MobileSDK;
import com.jumio.analytics.DismissType;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.camera.CameraManager;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.JumioBroadcastManager;
import com.jumio.commons.utils.StringUtil;
import com.jumio.core.BuildConfig;
import com.jumio.core.enums.JumioCameraPosition;
import com.jumio.core.enums.JumioDataCenter;
import com.jumio.core.exceptions.MissingPermissionException;
import com.jumio.core.exceptions.PlatformNotSupportedException;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.core.plugins.PluginRegistry.PluginMode;
import com.jumio.core.util.JumioUrlValidator;
import com.jumio.p311nv.api.calls.NVBackend;
import com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.data.NVStrings.C4448a;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.environment.NVEnvironment;
import com.jumio.p311nv.models.BackendModel;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.LivenessModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.models.TemplateModel;
import com.jumio.p311nv.utils.NetverifyLogUtils;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.models.CredentialsModel;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import jumio.p317nv.core.C4930j;

/* renamed from: com.jumio.nv.NetverifySDK */
public class NetverifySDK extends MobileSDK {
    public static final String EXTRA_DETAILED_ERROR_CODE = "com.jumio.nv.NetverifySDK.EXTRA_DETAILED_ERROR_CODE";
    public static final String EXTRA_ERROR_CODE = "com.jumio.nv.NetverifySDK.EXTRA_ERROR_CODE";
    public static final String EXTRA_ERROR_MESSAGE = "com.jumio.nv.NetverifySDK.EXTRA_ERROR_MESSAGE";
    public static final String EXTRA_SCAN_DATA = "com.jumio.nv.NetverifySDK.EXTRA_SCAN_DATA";
    public static final String EXTRA_SCAN_REFERENCE = "com.jumio.nv.NetverifySDK.EXTRA_SCAN_REFERENCE";
    public static int REQUEST_CODE = 200;

    /* renamed from: a */
    private static NetverifySDK f3269a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final MerchantSettingsModel f3270b;

    /* renamed from: c */
    private CredentialsModel f3271c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Activity f3272d;

    /* renamed from: e */
    private boolean f3273e;

    /* renamed from: f */
    private String f3274f = "";

    /* renamed from: g */
    private String f3275g = "";

    /* renamed from: h */
    private String f3276h = "";

    /* renamed from: i */
    private String f3277i = "";

    /* renamed from: j */
    private ArrayList<NVDocumentType> f3278j = null;

    /* renamed from: k */
    private NVDocumentVariant f3279k = null;

    /* renamed from: l */
    private String f3280l = "";

    /* renamed from: m */
    private boolean f3281m = false;

    /* renamed from: n */
    private boolean f3282n = false;

    /* renamed from: o */
    private boolean f3283o = false;

    /* renamed from: p */
    private boolean f3284p = false;

    /* renamed from: q */
    private boolean f3285q = false;

    /* renamed from: r */
    private String f3286r = "";

    /* renamed from: s */
    private boolean f3287s = true;

    /* renamed from: t */
    private boolean f3288t = false;

    /* renamed from: u */
    private boolean f3289u = true;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public C4428c f3290v = null;

    /* renamed from: com.jumio.nv.NetverifySDK$a */
    class C4426a implements UncaughtExceptionHandler {
        private C4426a() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            if (th instanceof Exception) {
                Log.m1909d(JumioAnalytics.LOGTAG, "uncaught exception: " + th);
                JumioAnalytics.add(MobileEvents.exception(JumioAnalytics.getSessionId(), (Exception) th));
                JumioAnalytics.add(MobileEvents.sdkLifecycle(JumioAnalytics.getSessionId(), DismissType.ABORTED));
                JumioAnalytics.flush();
            }
        }
    }

    /* renamed from: com.jumio.nv.NetverifySDK$b */
    class C4427b extends C4426a {

        /* renamed from: c */
        private final UncaughtExceptionHandler f3295c;

        public C4427b(UncaughtExceptionHandler uncaughtExceptionHandler) {
            super();
            Log.m1924v(JumioAnalytics.LOGTAG, "Wrapping handler :" + uncaughtExceptionHandler.getClass().toString());
            this.f3295c = uncaughtExceptionHandler;
        }

        public void uncaughtException(Thread thread, Throwable th) {
            super.uncaughtException(thread, th);
            if (this.f3295c != null) {
                this.f3295c.uncaughtException(thread, th);
            }
        }
    }

    /* renamed from: com.jumio.nv.NetverifySDK$c */
    class C4428c extends Handler implements Subscriber<String> {

        /* renamed from: b */
        private NetverifyInitiateCallback f3297b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f3298c;

        /* renamed from: d */
        private int f3299d;

        /* renamed from: com.jumio.nv.NetverifySDK$c$a */
        class C4429a implements Subscriber<Void> {
            private C4429a() {
            }

            /* renamed from: a */
            public void onResult(Void voidR) {
                C4428c.this.sendEmptyMessage(C4428c.this.f3298c);
            }

            public void onError(Throwable th) {
            }
        }

        private C4428c(Looper looper, NetverifyInitiateCallback netverifyInitiateCallback) {
            super(looper);
            this.f3298c = 100;
            this.f3299d = 200;
            this.f3297b = netverifyInitiateCallback;
        }

        public void handleMessage(Message message) {
            if (NetverifySDK.this.f3290v != null) {
                NVBackend.unregisterFromUpdates(C4930j.class, this);
            }
            if (message.what == this.f3298c) {
                this.f3297b.onNetverifyInitiateSuccess();
            } else if (message.what == this.f3299d) {
                JumioException jumioException = (JumioException) message.obj;
                this.f3297b.onNetverifyInitiateError(jumioException.getErrorCase().code(), jumioException.getDetailedErrorCase(), jumioException.getErrorCase().localizedMessage(NetverifySDK.this.f3272d), jumioException.getErrorCase().retry());
            }
        }

        @InvokeOnUiThread(false)
        /* renamed from: a */
        public void onResult(String str) {
            ServerSettingsModel serverSettingsModel = new ServerSettingsModel();
            serverSettingsModel.onResult(str);
            DataAccess.update((Context) NetverifySDK.this.f3272d, ServerSettingsModel.class, serverSettingsModel);
            NVBackend.unregisterFromUpdates(C4930j.class, this);
            if (NetverifySDK.this.f3270b.isCountryPreSelected()) {
                ArrayList arrayList = new ArrayList(NetverifySDK.this.f3270b.getSupportedDocumentTypes());
                arrayList.remove(NVDocumentType.PASSPORT);
                arrayList.remove(NVDocumentType.VISA);
                Country country = new Country(NetverifySDK.this.f3270b.getIsoCode());
                TemplateModel templateModel = new TemplateModel(NetverifySDK.this.f3272d);
                templateModel.add(new C4429a());
                templateModel.getOrLoad(country, (NVDocumentType[]) arrayList.toArray(new NVDocumentType[arrayList.size()]));
                return;
            }
            sendEmptyMessage(this.f3298c);
        }

        @InvokeOnUiThread(false)
        public void onError(Throwable th) {
            Message message = new Message();
            message.what = this.f3299d;
            message.obj = NVBackend.errorFromThrowable(NetverifySDK.this.f3272d, th, C4930j.class);
            sendMessage(message);
        }
    }

    private NetverifySDK(Activity activity, String str, String str2, JumioDataCenter jumioDataCenter) throws PlatformNotSupportedException {
        if (Thread.getDefaultUncaughtExceptionHandler() != null) {
            Thread.setDefaultUncaughtExceptionHandler(new C4427b(Thread.getDefaultUncaughtExceptionHandler()));
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new C4426a());
        }
        if (activity == null) {
            throw new NullPointerException("rootActivity is null");
        }
        checkSDKRequirements(false);
        NVEnvironment.checkOcrVersion(activity);
        DataAccess.delete((Context) activity, MerchantSettingsModel.class);
        DataAccess.delete((Context) activity, ServerSettingsModel.class);
        DataAccess.delete((Context) activity, SelectionModel.class);
        DataAccess.delete((Context) activity, LivenessModel.class);
        DataAccess.delete((Context) activity, InitiateModel.class);
        this.f3272d = activity;
        this.f3271c = new CredentialsModel();
        this.f3271c.setApiToken(str);
        this.f3271c.setApiSecret(str2);
        this.f3271c.setDataCenter(jumioDataCenter);
        this.f3270b = new MerchantSettingsModel();
        this.f3270b.setContext(activity);
        JumioAnalytics.start();
        this.f3273e = true;
        JumioAnalytics.add(MobileEvents.sdkLifecycle(JumioAnalytics.getSessionId(), DismissType.INSTANCE_CREATED));
    }

    public static synchronized NetverifySDK create(Activity activity, String str, String str2, JumioDataCenter jumioDataCenter) throws PlatformNotSupportedException {
        NetverifySDK netverifySDK;
        synchronized (NetverifySDK.class) {
            if (f3269a == null) {
                f3269a = new NetverifySDK(activity, str, str2, jumioDataCenter);
            }
            netverifySDK = f3269a;
        }
        return netverifySDK;
    }

    /* renamed from: a */
    private static synchronized void m1947a() {
        synchronized (NetverifySDK.class) {
            f3269a = null;
        }
    }

    public static boolean isSupportedPlatform() {
        return isSupportedPlatform(false);
    }

    public static String getDebugID() {
        try {
            UUID sessionId = JumioAnalytics.getSessionId();
            if (sessionId != null) {
                return sessionId.toString();
            }
            return null;
        } catch (IllegalStateException e) {
            return null;
        }
    }

    public synchronized void destroy() {
        DataAccess.delete((Context) this.f3272d, MerchantSettingsModel.class);
        DataAccess.delete((Context) this.f3272d, ServerSettingsModel.class);
        DataAccess.delete((Context) this.f3272d, SelectionModel.class);
        DataAccess.delete((Context) this.f3272d, LivenessModel.class);
        DataAccess.delete((Context) this.f3272d, InitiateModel.class);
        DataAccess.delete((Context) this.f3272d, DeviceCategory.class);
        DataAccess.delete((Context) this.f3272d, NVScanPartModel.class);
        DataAccess.delete((Context) this.f3272d, "fallbackScanPartModel");
        DataAccess.delete((Context) this.f3272d, PreviewProperties.class);
        DataAccess.delete((Context) this.f3272d, BackendModel.class);
        DataAccess.delete((Context) this.f3272d, DocumentDataModel.class);
        JumioBroadcastManager.destroy();
        m1947a();
        super.destroy();
        JumioAnalytics.shutdown(new Runnable() {
            public void run() {
                NVBackend.freeEncryption();
            }
        });
        this.f3273e = false;
    }

    public void setCustomerId(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.f3275g = str;
    }

    public void setPreselectedCountry(String str) {
        if (str == null) {
            str = "";
        }
        this.f3276h = str;
    }

    public void setPreselectedDocumentTypes(ArrayList<NVDocumentType> arrayList) {
        this.f3278j = new ArrayList<>();
        if (arrayList != null && arrayList.size() != 0) {
            this.f3278j.addAll(arrayList);
        }
    }

    public void setPreselectedDocumentVariant(NVDocumentVariant nVDocumentVariant) {
        this.f3279k = nVDocumentVariant;
    }

    public void setMerchantScanReference(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.f3280l = str;
    }

    public void setRequireFaceMatch(boolean z) {
        this.f3282n = true;
        this.f3281m = z;
    }

    public void setRequireVerification(boolean z) {
        this.f3283o = z;
    }

    public void setMerchantReportingCriteria(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.f3274f = str;
    }

    public void setCameraPosition(JumioCameraPosition jumioCameraPosition) {
        this.f3284p = jumioCameraPosition == JumioCameraPosition.FRONT;
    }

    public void setDataExtractionOnMobileOnly(boolean z) {
        this.f3285q = z;
    }

    public void setCallbackUrl(String str) {
        if (str == null) {
            str = "";
        }
        if (!new JumioUrlValidator(new String[]{UriUtil.HTTPS_SCHEME}, false, false, false, false).isValid(str) || str.length() > 255) {
            android.util.Log.w("NetverifySDK", "The provided callback url is not valid!");
        } else {
            this.f3286r = str;
        }
    }

    public void setEnableEpassport(boolean z) {
        this.f3287s = z;
    }

    public void sendDebugInfoToJumio(boolean z) {
        this.f3288t = z;
    }

    public void setShowHelpBeforeScan(boolean z) {
        this.f3289u = z;
    }

    public void setAdditionalInformation(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.f3277i = str;
    }

    public synchronized void initiate(NetverifyInitiateCallback netverifyInitiateCallback) throws IllegalArgumentException {
        if (netverifyInitiateCallback == null) {
            throw new IllegalArgumentException("NetverifyInitiateCallback must be set!");
        } else if (this.f3290v == null) {
            this.f3290v = new C4428c(Looper.getMainLooper(), netverifyInitiateCallback);
            m1949b();
            ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load((Context) this.f3272d, ServerSettingsModel.class);
            if (serverSettingsModel == null || !serverSettingsModel.isLoaded()) {
                NVBackend.settings(this.f3272d, this.f3271c, this.f3270b, this.f3290v);
            } else {
                netverifyInitiateCallback.onNetverifyInitiateSuccess();
            }
        }
    }

    /* renamed from: b */
    private void m1949b() {
        boolean z;
        boolean z2;
        boolean z3 = true;
        NetverifyLogUtils.init();
        if (!this.f3273e) {
            JumioAnalytics.start();
            this.f3273e = true;
        }
        if (this.f3278j == null) {
            this.f3278j = new ArrayList<>();
        }
        if (this.f3278j.size() == 0) {
            this.f3278j.add(NVDocumentType.PASSPORT);
            this.f3278j.add(NVDocumentType.VISA);
            this.f3278j.add(NVDocumentType.IDENTITY_CARD);
            this.f3278j.add(NVDocumentType.DRIVER_LICENSE);
        }
        MerchantSettingsModel merchantSettingsModel = (MerchantSettingsModel) DataAccess.load((Context) this.f3272d, MerchantSettingsModel.class);
        if (merchantSettingsModel != null) {
            boolean z4 = this.f3278j.size() == merchantSettingsModel.getSupportedDocumentTypes().size();
            Iterator it = this.f3278j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!merchantSettingsModel.getSupportedDocumentTypes().contains((NVDocumentType) it.next())) {
                    z4 = false;
                    break;
                }
            }
            if (!z4) {
                DataAccess.delete((Context) this.f3272d, ServerSettingsModel.class);
            }
        } else {
            DataAccess.delete((Context) this.f3272d, ServerSettingsModel.class);
        }
        DataAccess.delete((Context) this.f3272d, MerchantSettingsModel.class);
        DataAccess.delete((Context) this.f3272d, SelectionModel.class);
        DataAccess.delete((Context) this.f3272d, LivenessModel.class);
        DataAccess.delete((Context) this.f3272d, InitiateModel.class);
        DataAccess.delete((Context) this.f3272d, DeviceCategory.class);
        DataAccess.delete((Context) this.f3272d, NVScanPartModel.class);
        DataAccess.delete((Context) this.f3272d, "fallbackScanPartModel");
        DataAccess.delete((Context) this.f3272d, PreviewProperties.class);
        DataAccess.delete((Context) this.f3272d, BackendModel.class);
        DataAccess.delete((Context) this.f3272d, DocumentDataModel.class);
        this.f3270b.setCountryIsoCode(this.f3276h);
        this.f3270b.setCountryPreSelected(this.f3276h.length() != 0);
        if (this.f3278j.size() == 1) {
            this.f3270b.setPreSelectedDocumentType((NVDocumentType) this.f3278j.get(0));
        }
        this.f3270b.setSupportedDocumentTypes(this.f3278j);
        this.f3270b.setDocumentVariant(this.f3279k);
        MerchantSettingsModel merchantSettingsModel2 = this.f3270b;
        if (this.f3279k != null) {
            z = true;
        } else {
            z = false;
        }
        merchantSettingsModel2.setDocumentVariantPreSelected(z);
        MerchantSettingsModel merchantSettingsModel3 = this.f3270b;
        if (!this.f3281m || !CameraManager.hasFrontFacingCamera(this.f3272d)) {
            z2 = false;
        } else {
            z2 = true;
        }
        merchantSettingsModel3.setFaceMatchEnabled(z2);
        this.f3270b.setFaceMatchSet(this.f3282n);
        this.f3270b.setMerchantScanReference(this.f3280l);
        this.f3270b.setMerchantReportingCriteria(this.f3274f);
        this.f3270b.setCustomerId(this.f3275g);
        this.f3270b.setRequireVerification(this.f3283o);
        this.f3270b.setCameraFacingFront(this.f3284p);
        this.f3270b.setCallbackUrl(this.f3286r);
        this.f3270b.setDataExtractionOnMobileOnly(this.f3285q);
        this.f3270b.setEnableEpassport(this.f3287s);
        this.f3270b.setSendDebugInfo(this.f3288t);
        this.f3270b.setShowHelpBeforeScan(this.f3289u);
        this.f3270b.setAdditionalInformation(this.f3277i);
        JumioAnalytics.allowEvent(303, this.f3288t);
        JumioAnalytics.add(MobileEvents.sdkParameters(JumioAnalytics.getSessionId(), m1950c()));
        boolean hasSystemFeature = this.f3272d.getPackageManager().hasSystemFeature("android.hardware.nfc");
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.f3272d);
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            z3 = false;
        }
        String str = BuildConfig.VERSION_NAME;
        JumioAnalytics.add(MobileEvents.mobileDeviceInformation(JumioAnalytics.getSessionId(), str.substring(0, str.indexOf(" (")), hasSystemFeature, z3));
        if (PluginRegistry.getPlugin(PluginMode.NFC) == null) {
            this.f3270b.setEnableEpassport(false);
        }
        Logger.getLogger("org.jmrtd").setLevel(Level.OFF);
        DataAccess.update((Context) this.f3272d, MerchantSettingsModel.class, this.f3270b);
        NVStrings.setFactory(new C4448a());
    }

    /* renamed from: c */
    private MetaInfo m1950c() {
        int i;
        boolean z;
        boolean z2;
        int i2;
        boolean z3 = true;
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.put("dataCenter", this.f3271c.getDataCenter().toString());
        metaInfo.put("preselectedCountry", this.f3270b.getIsoCode() != null ? this.f3270b.getIsoCode() : "");
        if (this.f3270b.getDocumentVariant() == NVDocumentVariant.PAPER) {
            i = 1;
        } else if (this.f3270b.getDocumentVariant() == NVDocumentVariant.PLASTIC) {
            i = 2;
        } else {
            i = 0;
        }
        metaInfo.put("preselectedDocumentStyle", Integer.valueOf(i));
        if (this.f3278j != null) {
            Iterator it = this.f3278j.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                switch ((NVDocumentType) it.next()) {
                    case PASSPORT:
                        i2 = i3 | 1;
                        break;
                    case DRIVER_LICENSE:
                        i2 = i3 | 2;
                        break;
                    case IDENTITY_CARD:
                        i2 = i3 | 4;
                        break;
                    case VISA:
                        i2 = i3 | 8;
                        break;
                    default:
                        i2 = i3;
                        break;
                }
                i3 = i2;
            }
            if (i3 > 0) {
                metaInfo.put("preselectedDocumentTypes", String.valueOf(i3));
            }
        }
        metaInfo.put("requireFaceMatch", Boolean.valueOf(this.f3270b.isFaceMatchEnabled()));
        metaInfo.put("requireVerification", Boolean.valueOf(this.f3270b.requireVerification()));
        metaInfo.put("merchantReportingCriteria", Boolean.valueOf(!StringUtil.nullOrEmpty(this.f3270b.getMerchantReportingCriteria())));
        String str = "merchantScanReference ";
        if (!StringUtil.nullOrEmpty(this.f3270b.getMerchantScanReference())) {
            z = true;
        } else {
            z = false;
        }
        metaInfo.put(str, Boolean.valueOf(z));
        String str2 = "customerId";
        if (!StringUtil.nullOrEmpty(this.f3270b.getCustomerId())) {
            z2 = true;
        } else {
            z2 = false;
        }
        metaInfo.put(str2, Boolean.valueOf(z2));
        metaInfo.put("cameraPositionFront", Boolean.valueOf(this.f3270b.isCameraFrontfacing()));
        String str3 = "callbackUrl";
        if (StringUtil.nullOrEmpty(this.f3270b.getCallbackUrl())) {
            z3 = false;
        }
        metaInfo.put(str3, Boolean.valueOf(z3));
        metaInfo.put("dataExtractionOnMobileOnly", Boolean.valueOf(this.f3270b.isDataExtractionOnMobileOnly()));
        metaInfo.put("sendDebugInfoToJumio", Boolean.valueOf(this.f3270b.isSendDebugInfo()));
        metaInfo.put("ePassportEnabled", Boolean.valueOf(this.f3270b.isEnableEpassport()));
        metaInfo.put("helpEnabled", Boolean.valueOf(this.f3270b.isShowHelpBeforeScan()));
        return metaInfo;
    }

    public void start() throws MissingPermissionException {
        this.f3272d.startActivityForResult(getIntent(), REQUEST_CODE);
    }

    public Intent getIntent() throws MissingPermissionException {
        if (!hasAllRequiredPermissions(this.f3272d)) {
            throw new MissingPermissionException(getMissingPermissions(this.f3272d));
        }
        m1949b();
        if (this.f3290v != null) {
            NVBackend.unregisterFromUpdates(C4930j.class, this.f3290v);
            this.f3290v = null;
        } else {
            NVBackend.settings(this.f3272d, this.f3271c, this.f3270b, null);
        }
        return super.getIntent(this.f3272d, NetverifyActivity.class, this.f3271c);
    }
}
