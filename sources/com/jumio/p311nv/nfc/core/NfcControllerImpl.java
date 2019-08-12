package com.jumio.p311nv.nfc.core;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import com.jumio.core.mvp.model.PublisherWithUpdate;
import com.jumio.core.mvp.model.SubscriberWithUpdate;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.p311nv.nfc.core.communication.TagAccessSpec;
import jumio.p317nv.nfc.C5059d;
import jumio.p317nv.nfc.C5086e;
import jumio.p317nv.nfc.C5101g;
import jumio.p317nv.nfc.C5106j;
import jumio.p317nv.nfc.C5112o;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* renamed from: com.jumio.nv.nfc.core.NfcControllerImpl */
public class NfcControllerImpl extends PublisherWithUpdate<C5112o, C5112o> implements NfcController {
    private static final int PENDING_INTENT_NFC_SETTINGS = 11;
    private static final int PENDING_INTENT_NFC_TECH_DETECTED = 10;
    private static final String TAG = "NfcControllerImp";
    private C5106j mAuthenticator;
    private Context mContext;
    boolean mForegroundDispatchActive;
    private boolean mIsActive;
    private transient NfcAdapter mNfcAdapter;
    private C5086e mNfcFeatureService;
    private TagAccessSpec mTagAccess;

    /* renamed from: com.jumio.nv.nfc.core.NfcControllerImpl$a */
    class C4468a implements SubscriberWithUpdate<C5112o, C5112o> {
        private C4468a() {
        }

        /* renamed from: a */
        public void onUpdate(C5112o oVar) {
            NfcControllerImpl.this.publishUpdate(oVar);
        }

        /* renamed from: b */
        public void onResult(C5112o oVar) {
            NfcControllerImpl.this.publishResult(oVar);
        }

        public void onError(Throwable th) {
            NfcControllerImpl.this.publishError(th);
        }
    }

    public NfcControllerImpl(Context context) {
        this(context, null);
    }

    NfcControllerImpl(Context context, TagAccessSpec tagAccessSpec) {
        this.mForegroundDispatchActive = false;
        this.mIsActive = false;
        this.mContext = context;
        this.mTagAccess = tagAccessSpec;
        this.mNfcFeatureService = new C5086e(context);
    }

    public void start(Activity activity) {
        enableForegroundDispatch(activity, 10);
    }

    public void pause(Activity activity) {
        disableForegroundDispatch(activity);
    }

    public void stop() {
        this.mNfcFeatureService = null;
    }

    /* access modifiers changed from: protected */
    public synchronized void enableForegroundDispatch(Activity activity, int i) {
        if (!this.mForegroundDispatchActive) {
            this.mForegroundDispatchActive = true;
            initNfcAdapter();
            if (this.mNfcAdapter != null) {
                Intent intent = new Intent();
                intent.addFlags(PKIFailureInfo.duplicateCertReq);
                PendingIntent createPendingResult = activity.createPendingResult(i, intent, 0);
                if (createPendingResult != null) {
                    try {
                        this.mNfcAdapter.enableForegroundDispatch(activity, createPendingResult, new IntentFilter[]{new IntentFilter("android.nfc.action.TECH_DISCOVERED"), new IntentFilter("android.nfc.action.TAG_DISCOVERED")}, new String[][]{new String[]{"android.nfc.tech.IsoDep"}});
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void disableForegroundDispatch(Activity activity) {
        this.mForegroundDispatchActive = false;
        initNfcAdapter();
        if (this.mNfcAdapter != null) {
            try {
                this.mNfcAdapter.disableForegroundDispatch(activity);
            } catch (Exception e) {
            }
        }
    }

    public synchronized boolean isNfcEnabled() {
        return getFeatureProvider().mo47150b();
    }

    /* access modifiers changed from: protected */
    public void tagDetected(C5101g gVar, TagAccessSpec tagAccessSpec) {
        if (this.mAuthenticator == null || !this.mAuthenticator.mo47182a()) {
            this.mAuthenticator = new C5106j(getFeatureProvider().mo47151c(), gVar, tagAccessSpec);
            this.mAuthenticator.subscribe(new C4468a());
            this.mAuthenticator.mo47183b();
        }
    }

    public boolean hasRootCertificate(String str) {
        return getFeatureProvider().mo47149a(str);
    }

    public boolean hasNfcFeature() {
        return getFeatureProvider().mo47148a();
    }

    public boolean consumeIntent(int i, Intent intent) {
        if (!this.mIsActive) {
            return true;
        }
        switch (i) {
            case 10:
                resolveIntent(intent);
                return true;
            case 11:
                return false;
            default:
                return false;
        }
    }

    public void setTagAccess(Object obj) {
        if (!(obj instanceof TagAccessSpec)) {
            throw new IllegalArgumentException("accessSpec must be of type TagAccessSpec");
        }
        this.mTagAccess = (TagAccessSpec) obj;
    }

    public void setEnabled(boolean z) {
        this.mIsActive = z;
    }

    private void resolveIntent(Intent intent) {
        if (this.mTagAccess == null) {
            throw new C5059d("NFC tag access was null! Call NfcController.setTagAccess() before starting the scan!");
        }
        String action = intent.getAction();
        if ("android.nfc.action.TECH_DISCOVERED".equals(action) && (intent.getFlags() & 1048576) == 0) {
            tagDetected(new C5101g((Tag) intent.getParcelableExtra("android.nfc.extra.TAG")), this.mTagAccess);
        } else if ("android.nfc.action.TAG_DISCOVERED".equals(action) && (intent.getFlags() & 1048576) == 0) {
            tagDetected(new C5101g((Tag) intent.getParcelableExtra("android.nfc.extra.TAG")), this.mTagAccess);
        }
    }

    private C5086e getFeatureProvider() {
        if (this.mNfcFeatureService != null) {
            return this.mNfcFeatureService;
        }
        throw new IllegalStateException("NfcFeatureProvider service not bound!");
    }

    private synchronized void initNfcAdapter() {
        NfcManager nfcManager = (NfcManager) this.mContext.getSystemService("nfc");
        if (nfcManager != null) {
            this.mNfcAdapter = nfcManager.getDefaultAdapter();
        }
        if (this.mNfcAdapter != null) {
            try {
                this.mNfcAdapter.isEnabled();
            } catch (Exception e) {
            }
            try {
                this.mNfcAdapter.isEnabled();
            } catch (Exception e2) {
            }
        }
    }
}
