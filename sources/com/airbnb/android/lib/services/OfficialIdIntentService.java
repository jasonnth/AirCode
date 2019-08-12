package com.airbnb.android.lib.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.events.OfficialIDStatusEvent;
import com.airbnb.android.core.requests.OfficialIdStatusRequest;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.squareup.otto.Bus;

public class OfficialIdIntentService extends IntentService {
    private static final int CHECK_SERVER_STATUS_INTERVAL = 10000;
    private static final String KEY_SCAN_REFERENCE_ID = "scan_reference_id";
    private static final int MAX_SERVER_RETRIES = 25;
    private static final String TAG = OfficialIdIntentService.class.getSimpleName();
    private static final long WAKE_LOCK_TIMEOUT_MILLIS = 300000;
    AirbnbApi mAirbnbApi;
    Bus mBus;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private String mScanReferenceId;
    /* access modifiers changed from: private */
    public State mState;

    private enum State {
        RUNNING,
        SUCCESS,
        ERROR
    }

    public static Intent intentForOfficialId(Context context, String scanReferenceId) {
        Intent intent = new Intent(context, OfficialIdIntentService.class);
        Bundle extras = new Bundle();
        extras.putString(KEY_SCAN_REFERENCE_ID, scanReferenceId);
        intent.putExtras(extras);
        return intent;
    }

    public OfficialIdIntentService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r6.mState = com.airbnb.android.lib.services.OfficialIdIntentService.State.ERROR;
        r6.mHandler.post(com.airbnb.android.lib.services.OfficialIdIntentService$$Lambda$1.lambdaFactory$(r6));
     */
    @android.annotation.SuppressLint({"Wakelock"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r7) {
        /*
            r6 = this;
            com.airbnb.android.lib.AirbnbApplicationFacade r3 = com.airbnb.android.lib.AirbnbApplication.instance(r6)
            com.airbnb.android.core.BaseGraph r3 = r3.component()
            com.airbnb.android.lib.AirbnbGraph r3 = (com.airbnb.android.lib.AirbnbGraph) r3
            r3.inject(r6)
            android.os.Handler r3 = new android.os.Handler
            android.os.Looper r4 = android.os.Looper.getMainLooper()
            r3.<init>(r4)
            r6.mHandler = r3
            java.lang.String r3 = "power"
            java.lang.Object r1 = r6.getSystemService(r3)
            android.os.PowerManager r1 = (android.os.PowerManager) r1
            r3 = 1
            java.lang.String r4 = TAG
            android.os.PowerManager$WakeLock r2 = r1.newWakeLock(r3, r4)
            r4 = 300000(0x493e0, double:1.482197E-318)
            r2.acquire(r4)
            java.lang.String r3 = "scan_reference_id"
            java.lang.String r3 = r7.getStringExtra(r3)
            r6.mScanReferenceId = r3
            r0 = 25
            com.airbnb.android.lib.services.OfficialIdIntentService$State r3 = com.airbnb.android.lib.services.OfficialIdIntentService.State.RUNNING
            r6.mState = r3
        L_0x003d:
            com.airbnb.android.lib.services.OfficialIdIntentService$State r3 = r6.mState
            com.airbnb.android.lib.services.OfficialIdIntentService$State r4 = com.airbnb.android.lib.services.OfficialIdIntentService.State.RUNNING
            if (r3 != r4) goto L_0x005a
            if (r0 < 0) goto L_0x005a
            java.lang.Object r4 = r6.mLock
            monitor-enter(r4)
            int r0 = r0 + -1
            if (r0 >= 0) goto L_0x0064
            com.airbnb.android.lib.services.OfficialIdIntentService$State r3 = com.airbnb.android.lib.services.OfficialIdIntentService.State.ERROR     // Catch:{ all -> 0x006e }
            r6.mState = r3     // Catch:{ all -> 0x006e }
            android.os.Handler r3 = r6.mHandler     // Catch:{ all -> 0x006e }
            java.lang.Runnable r5 = com.airbnb.android.lib.services.OfficialIdIntentService$$Lambda$1.lambdaFactory$(r6)     // Catch:{ all -> 0x006e }
            r3.post(r5)     // Catch:{ all -> 0x006e }
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
        L_0x005a:
            boolean r3 = r2.isHeld()
            if (r3 == 0) goto L_0x0063
            r2.release()
        L_0x0063:
            return
        L_0x0064:
            r6.executeOfficialIDRequest()     // Catch:{ all -> 0x006e }
            java.lang.Object r3 = r6.mLock     // Catch:{ InterruptedException -> 0x0071 }
            r3.wait()     // Catch:{ InterruptedException -> 0x0071 }
        L_0x006c:
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            goto L_0x003d
        L_0x006e:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            throw r3
        L_0x0071:
            r3 = move-exception
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.lib.services.OfficialIdIntentService.onHandleIntent(android.content.Intent):void");
    }

    static /* synthetic */ void lambda$onHandleIntent$0(OfficialIdIntentService officialIdIntentService) {
        officialIdIntentService.mBus.post(new OfficialIDStatusEvent(officialIdIntentService.mState == State.SUCCESS));
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mState == State.RUNNING) {
            this.mHandler.post(OfficialIdIntentService$$Lambda$2.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$onDestroy$1(OfficialIdIntentService officialIdIntentService) {
        officialIdIntentService.mBus.post(new OfficialIDStatusEvent(officialIdIntentService.mState == State.SUCCESS));
    }

    private void executeOfficialIDRequest() {
        new OfficialIdStatusRequest(this.mScanReferenceId, new NonResubscribableRequestListener<OfficialIdStatusResponse>() {
            public void onResponse(OfficialIdStatusResponse response) {
                if (OfficialIdIntentService.this.mState == State.RUNNING) {
                    String str = response.status;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case 2524:
                            if (str.equals(OfficialIdStatusResponse.f1090OK)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 66247144:
                            if (str.equals(OfficialIdStatusResponse.ERROR)) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            OfficialIdIntentService.this.mState = State.SUCCESS;
                            break;
                        case 1:
                            OfficialIdIntentService.this.mState = State.ERROR;
                            break;
                        default:
                            OfficialIdIntentService.this.mHandler.postDelayed(OfficialIdIntentService$1$$Lambda$1.lambdaFactory$(this), 10000);
                            return;
                    }
                }
                if (OfficialIdIntentService.this.mState != State.RUNNING) {
                    OfficialIdIntentService.this.mHandler.post(OfficialIdIntentService$1$$Lambda$2.lambdaFactory$(this));
                    synchronized (OfficialIdIntentService.this.mLock) {
                        OfficialIdIntentService.this.mLock.notifyAll();
                    }
                }
            }

            static /* synthetic */ void lambda$onResponse$0(C71731 r2) {
                synchronized (OfficialIdIntentService.this.mLock) {
                    OfficialIdIntentService.this.mLock.notifyAll();
                }
            }

            static /* synthetic */ void lambda$onResponse$1(C71731 r4) {
                OfficialIdIntentService.this.mBus.post(new OfficialIDStatusEvent(OfficialIdIntentService.this.mState == State.SUCCESS));
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                OfficialIdIntentService.this.mHandler.postDelayed(OfficialIdIntentService$1$$Lambda$3.lambdaFactory$(this), 10000);
            }

            static /* synthetic */ void lambda$onErrorResponse$2(C71731 r2) {
                synchronized (OfficialIdIntentService.this.mLock) {
                    OfficialIdIntentService.this.mLock.notifyAll();
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }
}
