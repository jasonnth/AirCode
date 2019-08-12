package com.miteksystems.misnap;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.appevents.AppEventsConstants;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.analyzer.NoAnalyzer;
import com.miteksystems.misnap.events.CaptureCurrentFrameEvent;
import com.miteksystems.misnap.events.OnCaptureModeChangedEvent;
import com.miteksystems.misnap.events.OnCapturedFrameEvent;
import com.miteksystems.misnap.events.OnFrameProcessedEvent;
import com.miteksystems.misnap.events.OnShutdownEvent;
import com.miteksystems.misnap.events.OnStartedEvent;
import com.miteksystems.misnap.events.SetCaptureModeEvent;
import com.miteksystems.misnap.events.ShutdownEvent;
import com.miteksystems.misnap.imaging.JPEGProcessor;
import com.miteksystems.misnap.p312a.C4545b;
import com.miteksystems.misnap.p312a.C4550d;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.params.MiSnapConstants;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.ParamsHelper;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.storage.PreferenceManager;
import com.miteksystems.misnap.utils.MibiData;
import com.miteksystems.misnap.utils.UXPManager;
import com.miteksystems.misnap.utils.Utils;
import java.util.ArrayList;
import org.json.JSONException;
import p314de.greenrobot.event.EventBus;

public class MiSnapCameraFragment extends Fragment {
    private static final int BARCODE_SCAN_REQUEST_CODE = 999;
    private static final int CC_SCAN_REQUEST_CODE = 499;
    /* access modifiers changed from: private */
    public static final String TAG = MiSnapCameraFragment.class.getSimpleName();
    static int mAssistAttempts = 0;
    static boolean mCameraInitialized = false;
    static boolean mParamsInitialized = false;
    private static int mSMState = 1;
    C4545b mCameraMgr = null;
    boolean mGoodFrameReceived = false;
    LayoutParams mMiSnapLayoutParams = null;
    ParameterManager mMiSnapParamsMgr = null;
    private BroadcastReceiver mMiSnapReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null && intent.getAction().equals(SDKConstants.MISNAP_BROADCASTER)) {
                switch (intent.getIntExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_ID, 0)) {
                    case SDKConstants.MISNAP_CAM_CAMERA_INITIALZED /*50000*/:
                        MiSnapCameraFragment.this.processCameraInitialized();
                        return;
                    case SDKConstants.MISNAP_CAM_CAMERA_SURFACE_PREPARED /*50001*/:
                        MiSnapCameraFragment.this.processCameraSurfacePrepared();
                        return;
                    case SDKConstants.MISNAP_CAM_CAMERA_PREVIEW_STARTS /*50002*/:
                        MiSnapCameraFragment.this.processCameraPreviewStarted();
                        return;
                    case SDKConstants.MISNAP_CAM_CAMERA_PREPARED /*50003*/:
                        MiSnapCameraFragment.this.processCameraMgrPrepared();
                        return;
                    case SDKConstants.MISNAP_CAM_GOOD_FRAME_RECEIVED /*50004*/:
                        MiSnapCameraFragment.this.processGoodFrameMessage();
                        return;
                    case SDKConstants.MISNAP_FINAL_FRAME_RECEIVED /*50010*/:
                        MiSnapCameraFragment.this.processFinalFrameMessage(intent.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA));
                        return;
                    case SDKConstants.MISNAP_ERROR_STATE /*50011*/:
                        String stringExtra = intent.getStringExtra(SDKConstants.MISNAP_BROADCAST_MESSAGE_PARAM1);
                        if (stringExtra != null) {
                            MiSnapCameraFragment.this.handleErrorState(stringExtra);
                            return;
                        } else {
                            MiSnapCameraFragment.this.handleErrorState("Cancelled");
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    };
    Runnable mMiSnapStartAutoFocusTimeout = new Runnable() {
        public final void run() {
            if (!MiSnapCameraFragment.this.mGoodFrameReceived) {
                MiSnapCameraFragment.TAG;
                Utils.sendMsgToCameraMgr(MiSnapCameraFragment.this.getActivity(), SDKConstants.CAM_SWITCH_FOCUS_MODE);
            }
        }
    };
    public Handler mMiSnapStateMachine = new Handler(new Callback() {
        public final boolean handleMessage(Message message) {
            if (message == null || MiSnapCameraFragment.this.getActivity() == null || MiSnapCameraFragment.this.getActivity().isFinishing()) {
                return false;
            }
            switch (message.what) {
                case 2:
                    MiSnapCameraFragment.this.processMiSnapParameters();
                    break;
                case 3:
                    MiSnapCameraFragment.this.processParamsInitializedState();
                    break;
                case 5:
                    MiSnapCameraFragment.this.processCameraInitializedState();
                    break;
                case 6:
                    MiSnapCameraFragment.this.processPrepareCameraState();
                    break;
                case 8:
                    MiSnapCameraFragment.this.previewStartedState();
                    break;
                case 11:
                    MiSnapCameraFragment.this.startPreview();
                    break;
                case 17:
                    MiSnapCameraFragment.this.handleGoodFrameEvent();
                    break;
                case 18:
                    MiSnapCameraFragment.this.processCameraPreparedState();
                    break;
                case 19:
                    MiSnapCameraFragment.this.restartMiSnapSession();
                    break;
                case 20:
                    MiSnapCameraFragment.this.shutdownMiSnap(message.what, (String) message.obj);
                    break;
            }
            return true;
        }
    });
    PreferenceManager mPreferenceMgr = null;
    private String mSelectedDocType = null;
    Object mSyncBlock = new Object();
    private int mWarnings;
    protected Runnable waitForScreenUnlockRunnable = new Runnable() {
        public final void run() {
            MiSnapCameraFragment.this.waitForScreenUnlockThenInit();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C4540R.layout.misnap_fragment, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        waitForScreenUnlockThenInit();
        this.mWarnings = 0;
    }

    /* access modifiers changed from: protected */
    public void waitForScreenUnlockThenInit() {
        KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService("keyguard");
        int i = getActivity().getWindow().getAttributes().flags;
        if (!keyguardManager.inKeyguardRestrictedInputMode() || (524288 & i) != 0) {
            init();
            synchronized (this.mSyncBlock) {
                if (getmCurrentMiSnapState() == 1) {
                    sendMiSnapSMMessage(2);
                }
            }
            return;
        }
        this.mMiSnapStateMachine.postDelayed(this.waitForScreenUnlockRunnable, 200);
    }

    public void onPause() {
        super.onPause();
        this.mMiSnapStateMachine.removeCallbacks(this.waitForScreenUnlockRunnable);
        deinit();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String stringExtra = intent.getStringExtra(MiSnapAPI.RESULT_CODE);
        Intent intent2 = null;
        switch (i) {
            case CC_SCAN_REQUEST_CODE /*499*/:
                intent2 = new Intent();
                intent2.putExtra(MiSnapAPI.RESULT_MIBI_DATA, buildMibiData(getActivity(), stringExtra));
                intent2.putExtra(MiSnapAPI.RESULT_CODE, stringExtra);
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_NUMBER, intent.getStringExtra(MiSnapAPI.CREDIT_CARD_NUMBER));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_REDACTED_NUMBER, intent.getStringExtra(MiSnapAPI.CREDIT_CARD_REDACTED_NUMBER));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_FORMATTED_NUMBER, intent.getStringExtra(MiSnapAPI.CREDIT_CARD_FORMATTED_NUMBER));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_TYPE, intent.getStringExtra(MiSnapAPI.CREDIT_CARD_TYPE));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_CVV, intent.getIntExtra(MiSnapAPI.CREDIT_CARD_CVV, -1));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_EXPIRY_MONTH, intent.getIntExtra(MiSnapAPI.CREDIT_CARD_EXPIRY_MONTH, -1));
                intent2.putExtra(MiSnapAPI.CREDIT_CARD_EXPIRY_YEAR, intent.getIntExtra(MiSnapAPI.CREDIT_CARD_EXPIRY_YEAR, -1));
                break;
            case BARCODE_SCAN_REQUEST_CODE /*999*/:
                intent2 = new Intent();
                intent2.putExtra(MiSnapAPI.RESULT_MIBI_DATA, buildMibiData(getActivity(), stringExtra));
                intent2.putExtra(MiSnapAPI.RESULT_CODE, stringExtra);
                intent2.putExtra(MiSnapAPI.RESULT_PDF417_DATA, intent.getStringExtra(MiSnapAPI.RESULT_PDF417_DATA));
                break;
        }
        getActivity().setResult(i2, intent2);
        getActivity().finish();
    }

    /* access modifiers changed from: 0000 */
    public void init() {
        setmCurrentMiSnapState(1);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SDKConstants.MISNAP_BROADCASTER);
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mMiSnapReceiver, intentFilter);
        EventBus.getDefault().register(this);
        this.mMiSnapLayoutParams = new LayoutParams(-2, -1);
        this.mMiSnapLayoutParams.gravity = 17;
        createManagersThatDoNotDependOnParameters();
    }

    /* access modifiers changed from: 0000 */
    public void deinit() {
        stopAllTimers();
        if (this.mCameraMgr != null) {
            this.mCameraMgr.mo44352i();
            C4545b bVar = this.mCameraMgr;
            if (bVar.f3572A != null && C4545b.f3570e) {
                LocalBroadcastManager.getInstance(bVar.f3576a).unregisterReceiver(bVar.f3572A);
                C4545b.f3570e = false;
            }
            bVar.mo44347d();
            this.mCameraMgr = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (this.mMiSnapReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mMiSnapReceiver);
        }
        mParamsInitialized = false;
        mCameraInitialized = false;
        mAssistAttempts = 0;
        mSMState = 1;
    }

    /* access modifiers changed from: protected */
    public IAnalyzer createAnalyzer(ParameterManager parameterManager) {
        return new NoAnalyzer();
    }

    private void createManagersThatDoNotDependOnParameters() {
        this.mMiSnapParamsMgr = new ParameterManager();
        UXPManager.getInstance();
    }

    private void createManagersThatDependOnParameters() {
        Utils.setCameraFacing(this.mMiSnapParamsMgr.getUseFrontCamera());
        this.mPreferenceMgr = new PreferenceManager(this.mMiSnapParamsMgr.getUseFrontCamera());
        this.mCameraMgr = new C4545b(getActivity(), this.mMiSnapParamsMgr, createAnalyzer(this.mMiSnapParamsMgr));
    }

    /* access modifiers changed from: private */
    public void startPreview() {
        synchronized (this.mSyncBlock) {
            Utils.sendMsgToCameraMgr(getActivity(), SDKConstants.CAM_START_PREVIEW);
        }
    }

    /* access modifiers changed from: private */
    public void shutdownMiSnap(int i, String str) {
        EventBus.getDefault().post(new OnShutdownEvent(i, str));
        deinit();
    }

    /* access modifiers changed from: 0000 */
    public void restartMiSnapSession() {
        synchronized (this.mSyncBlock) {
            sendMiSnapSMMessage(2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleGoodFrameEvent() {
        stopAllTimers();
    }

    /* access modifiers changed from: 0000 */
    public void handleErrorState(String str) {
        EventBus.getDefault().post(new ShutdownEvent(5, str));
    }

    /* access modifiers changed from: protected */
    public void stopAllTimers() {
        getmMiSnapStateMachine().removeCallbacks(this.mMiSnapStartAutoFocusTimeout);
    }

    public ParameterManager getSettingsObj() {
        return this.mMiSnapParamsMgr;
    }

    /* access modifiers changed from: private */
    public void previewStartedState() {
        synchronized (this.mSyncBlock) {
            if (this.mMiSnapParamsMgr.isCurrentModeVideo()) {
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_start_auto_capture_mode);
            } else {
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_start_manual_capture_mode);
            }
            setmCurrentMiSnapState(8);
            startFocusModeSwitchTimer();
            EventBus.getDefault().post(new OnStartedEvent(this.mMiSnapParamsMgr.getmCaptureMode(), SDKConstants.RESULT_SUCCESS));
        }
    }

    /* access modifiers changed from: 0000 */
    public void startFocusModeSwitchTimer() {
        if (this.mMiSnapParamsMgr != null && this.mMiSnapParamsMgr.getmFocusMode() == 3) {
            getmMiSnapStateMachine().removeCallbacks(this.mMiSnapStartAutoFocusTimeout);
            getmMiSnapStateMachine().postDelayed(this.mMiSnapStartAutoFocusTimeout, (long) this.mMiSnapParamsMgr.getmMiSnapForcedFocusDelay());
        }
    }

    /* access modifiers changed from: private */
    public void processCameraPreparedState() {
        try {
            if (this.mCameraMgr.f3592t != null) {
                setmCurrentMiSnapState(18);
                ((ViewGroup) getView()).addView(this.mCameraMgr.f3592t);
                Utils.sendMsgToUIFragment(getActivity(), SDKConstants.UI_FRAGMENT_INITIALIZE);
                return;
            }
            Utils.broadcastMsgToMiSnap(getActivity(), SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_SDK_STATE_ERROR);
        } catch (Exception e) {
            Utils.broadcastMsgToMiSnap(getActivity(), SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_SDK_STATE_ERROR);
        }
    }

    /* access modifiers changed from: private */
    public void processPrepareCameraState() {
        if (getmCurrentMiSnapState() == 6) {
            synchronized (this.mSyncBlock) {
                Utils.sendMsgToCameraMgr(getActivity(), SDKConstants.CAM_PREPARE_CAMERA);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processParamsInitializedState() {
        /*
            r3 = this;
            java.lang.Object r1 = r3.mSyncBlock
            monitor-enter(r1)
            boolean r0 = mParamsInitialized     // Catch:{ all -> 0x0021 }
            r2 = 1
            if (r0 != r2) goto L_0x001f
            r0 = 3
            r3.setmCurrentMiSnapState(r0)     // Catch:{ all -> 0x0021 }
            com.miteksystems.misnap.params.ParameterManager r0 = r3.mMiSnapParamsMgr     // Catch:{ all -> 0x0021 }
            boolean r0 = r3.transferJobToAnotherActivity(r0)     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0016
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
        L_0x0015:
            return
        L_0x0016:
            android.support.v4.app.FragmentActivity r0 = r3.getActivity()     // Catch:{ all -> 0x0021 }
            r2 = 20000(0x4e20, float:2.8026E-41)
            com.miteksystems.misnap.utils.Utils.sendMsgToCameraMgr(r0, r2)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            goto L_0x0015
        L_0x0021:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miteksystems.misnap.MiSnapCameraFragment.processParamsInitializedState():void");
    }

    /* access modifiers changed from: private */
    public void processCameraInitializedState() {
        synchronized (this.mSyncBlock) {
            if (mCameraInitialized) {
                setmCurrentMiSnapState(5);
                sendMiSnapSMMessage(6);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean processMiSnapParameters() {
        int i;
        boolean z = false;
        if (getmCurrentMiSnapState() == 2) {
            synchronized (this.mSyncBlock) {
                if (mParamsInitialized) {
                    getmMiSnapStateMachine().sendEmptyMessage(3);
                } else if (getActivity().getIntent() != null) {
                    String stringExtra = getActivity().getIntent().getStringExtra(MiSnapAPI.JOB_SETTINGS);
                    if (stringExtra == null || stringExtra.length() <= 0) {
                        z = true;
                    } else {
                        switch (getResources().getConfiguration().screenLayout & 15) {
                            case 3:
                                i = 7;
                                break;
                            case 4:
                                i = 10;
                                break;
                            default:
                                i = 0;
                                break;
                        }
                        processParams(stringExtra, i);
                    }
                }
            }
        }
        return z;
    }

    private boolean transferJobToAnotherActivity(ParameterManager parameterManager) {
        String str = parameterManager.getmJobName();
        char c = 65535;
        switch (str.hashCode()) {
            case 1878720662:
                if (str.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_CREDIT_CARD)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                try {
                    Intent intent = new Intent(getActivity(), Class.forName("com.miteksystems.misnap.cardio.CardIoWrapperActivity"));
                    intent.putExtra(MiSnapAPI.CreditCardGuideColor, this.mMiSnapParamsMgr.getCreditCardGuideColor());
                    intent.putExtra(MiSnapAPI.CreditCardSuppressConfirmScreen, this.mMiSnapParamsMgr.getCreditCardSuppressConfirmScreen());
                    intent.putExtra(MiSnapAPI.CreditCardRequireCVV, this.mMiSnapParamsMgr.getCreditCardRequireCVV());
                    intent.putExtra(MiSnapAPI.CreditCardRequireExpiry, this.mMiSnapParamsMgr.getCreditCardRequireExpiry());
                    startActivityForResult(intent, CC_SCAN_REQUEST_CODE);
                    return true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
            default:
                return false;
        }
    }

    public Handler getmMiSnapStateMachine() {
        return this.mMiSnapStateMachine;
    }

    /* access modifiers changed from: 0000 */
    public void sendMiSnapSMMessage(int i) {
        setmCurrentMiSnapState(i);
        getmMiSnapStateMachine().sendEmptyMessage(i);
    }

    public int getmCurrentMiSnapState() {
        return mSMState;
    }

    public void setmCurrentMiSnapState(int i) {
        mSMState = i;
    }

    /* access modifiers changed from: private */
    public void processCameraInitialized() {
        mCameraInitialized = true;
        getmMiSnapStateMachine().sendEmptyMessage(5);
    }

    /* access modifiers changed from: private */
    public void processCameraSurfacePrepared() {
        getmMiSnapStateMachine().sendEmptyMessage(18);
    }

    /* access modifiers changed from: private */
    public void processCameraPreviewStarted() {
        getmMiSnapStateMachine().sendEmptyMessage(8);
    }

    /* access modifiers changed from: private */
    public void processCameraMgrPrepared() {
        sendMiSnapSMMessage(11);
    }

    /* access modifiers changed from: private */
    public void processGoodFrameMessage() {
        this.mGoodFrameReceived = true;
        getmMiSnapStateMachine().sendEmptyMessage(17);
    }

    /* access modifiers changed from: private */
    public void processFinalFrameMessage(byte[] bArr) {
        EventBus.getDefault().post(new OnCapturedFrameEvent(buildReturnIntent(-1, bArr, this.mMiSnapParamsMgr.isCurrentModeVideo() ? MiSnapAPI.RESULT_SUCCESS_VIDEO : MiSnapAPI.RESULT_SUCCESS_STILL)));
        UXPManager.getInstance().cleanup();
    }

    /* access modifiers changed from: protected */
    public Intent buildReturnIntent(int i, byte[] bArr, String str) {
        String buildMibiData = buildMibiData(getActivity(), str);
        Intent intent = new Intent();
        if (bArr != null) {
            JPEGProcessor jPEGProcessor = new JPEGProcessor();
            byte[] addMibiData = jPEGProcessor.addMibiData(bArr, buildMibiData);
            jPEGProcessor.saveJPEGImage(addMibiData, false);
            if (this.mPreferenceMgr != null) {
                if (this.mMiSnapParamsMgr.isCurrentModeVideo()) {
                    intent.putExtra(MiSnapAPI.RESULT_ORIGINAL_PIC_HEIGHT, this.mPreferenceMgr.getString(getActivity(), MiSnapConstants.PREF_PREVIEW_HEIGHT_KEY));
                    intent.putExtra(MiSnapAPI.RESULT_ORIGINAL_PIC_WIDTH, this.mPreferenceMgr.getString(getActivity(), MiSnapConstants.PREF_PREVIEW_WIDTH_KEY));
                } else {
                    intent.putExtra(MiSnapAPI.RESULT_ORIGINAL_PIC_HEIGHT, this.mPreferenceMgr.getString(getActivity(), MiSnapConstants.PREF_PICTURE_HEIGHT_KEY));
                    intent.putExtra(MiSnapAPI.RESULT_ORIGINAL_PIC_WIDTH, this.mPreferenceMgr.getString(getActivity(), MiSnapConstants.PREF_PICTURE_WIDTH_KEY));
                }
            }
            intent.putExtra(MiSnapAPI.RESULT_PICTURE_DATA, addMibiData);
            intent.putExtra(MiSnapAPI.RESULT_IMAGE_QUALITY, this.mMiSnapParamsMgr.getmImageQuality());
            intent.putExtra(MiSnapAPI.RESULT_WARNINGS, new ArrayList(OnFrameProcessedEvent.getRankedWarnings(this.mWarnings)));
        }
        intent.putExtra(MiSnapAPI.RESULT_MIBI_DATA, buildMibiData);
        intent.putExtra(MiSnapAPI.RESULT_CODE, str);
        return intent;
    }

    /* access modifiers changed from: protected */
    public String buildMibiData(Context context, String str) {
        MibiData mibiData = new MibiData();
        if (this.mCameraMgr == null || this.mMiSnapParamsMgr == null) {
            String str2 = "MiSnap SDK error: Context or Camera Mgr or Parameters object is null";
            try {
                mibiData.put("Error", str2);
                return mibiData.getMibiData();
            } catch (JSONException e) {
                return str2;
            }
        } else {
            new C4550d();
            int a = C4550d.m2089a(context, this.mCameraMgr.f3598z);
            try {
                String appVersion = ParamsHelper.getAppVersion(getActivity().getIntent());
                if (appVersion.length() > 0) {
                    mibiData.put(MiSnapAPI.AppVersion, appVersion);
                }
                mibiData.put("MibiVersion", SDKConstants.MIBI_DATA_VERSION);
                mibiData.put("Autocapture", this.mMiSnapParamsMgr.isCurrentModeVideo() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                mibiData.put("Device", Build.DEVICE);
                mibiData.put("Document", this.mMiSnapParamsMgr.getmJobName());
                PreferenceManager preferenceManager = new PreferenceManager(this.mMiSnapParamsMgr.getUseFrontCamera());
                if (!preferenceManager.getBoolean(context, MiSnapConstants.PREF_TORCH_SUPPORTED_KEY)) {
                    mibiData.put("Torch", "NA");
                } else if (this.mCameraMgr != null) {
                    if (this.mCameraMgr.mo44346c()) {
                        mibiData.put("Torch", "ON");
                    } else {
                        mibiData.put("Torch", "OFF");
                    }
                }
                if (this.mMiSnapParamsMgr.isCurrentModeVideo()) {
                    mibiData.put("ImageWidth", preferenceManager.getString(context, MiSnapConstants.PREF_PREVIEW_WIDTH_KEY));
                    mibiData.put("ImageHeight", preferenceManager.getString(context, MiSnapConstants.PREF_PREVIEW_HEIGHT_KEY));
                } else {
                    mibiData.put("ImageWidth", preferenceManager.getString(context, MiSnapConstants.PREF_PICTURE_WIDTH_KEY));
                    mibiData.put("ImageHeight", preferenceManager.getString(context, MiSnapConstants.PREF_PICTURE_HEIGHT_KEY));
                }
                mibiData.put("1080p", String.valueOf(preferenceManager.getBoolean(context, MiSnapConstants.PREF_HIGH_RES_SUPPORTED)));
                mibiData.put("720p", String.valueOf(preferenceManager.getBoolean(context, MiSnapConstants.PREF_LOW_RES_SUPPORTED_KEY)));
                mibiData.put("AutoFocus", String.valueOf(preferenceManager.getBoolean(context, MiSnapConstants.PREF_AUTO_FOCUS_SUPPORTED_KEY)));
                mibiData.put("ContVideoFocus", String.valueOf(preferenceManager.getBoolean(context, MiSnapConstants.PREF_VIDEO_FOCUS_SUPPORTED_KEY)));
                mibiData.put("ContPictureFocus", String.valueOf(preferenceManager.getBoolean(context, MiSnapConstants.PREF_PICTURE_FOCUS_SUPPORTED_KEY)));
                mibiData.put("Manufacturer", Build.MANUFACTURER);
                mibiData.put("MiSnapVersion", context.getString(C4540R.string.misnap_versionName));
                mibiData.put("SDKVersion", "MiSnap " + context.getString(C4540R.string.misnap_versionName));
                mibiData.put("Model", Build.MODEL);
                if (a == 0) {
                    mibiData.put("Orientation", Utils.isScreenLandscapeLeft(context) ? "LAND_LEFT" : "LAND_RIGHT");
                } else {
                    mibiData.put("Orientation", Utils.isScreenPortraitNormal(context) ? "PORTRAIT_NORMAL" : "PORTRAIT_UPSIDE_DOWN");
                }
                mibiData.put("OS", VERSION.RELEASE);
                mibiData.put("Platform", InternalLogger.EVENT_PARAM_SDK_ANDROID);
                mibiData.put("MiSnapResultCode", str);
                mibiData.put("Warnings", String.valueOf(this.mWarnings));
                if (!"".equals(this.mMiSnapParamsMgr.getMDVersion())) {
                    mibiData.put("ServerVersion", this.mMiSnapParamsMgr.getMDVersion());
                } else if (!"".equals(this.mMiSnapParamsMgr.getMIPVersion())) {
                    mibiData.put("ServerVersion", this.mMiSnapParamsMgr.getMIPVersion());
                }
                mibiData.putUxpMetrics(UXPManager.getInstance().getUXPMetrics());
                if (this.mMiSnapParamsMgr.mMiSnapParameters != null) {
                    mibiData.putParams(this.mMiSnapParamsMgr.mMiSnapParameters);
                    mibiData.removeParam("RequiredMaxImageSize");
                    mibiData.removeParam("CameraViewFinderMinVericalFill");
                    mibiData.removeParam("TutorialBrandNewUserDuration");
                    mibiData.removeParam("SecurityResult");
                    mibiData.removeParam(MiSnapAPI.AppVersion);
                }
                if (this.mMiSnapParamsMgr.mMiSnapChangedValues != null) {
                    mibiData.putChangedParams(this.mMiSnapParamsMgr.mMiSnapChangedValues.toString());
                }
                new StringBuilder("Mibi: ").append(mibiData.getMibiData());
                return mibiData.getMibiData();
            } catch (Exception e2) {
                String str3 = "Error preparing the MIBI data";
                try {
                    mibiData.put("Error", str3);
                    return mibiData.getMibiData();
                } catch (JSONException e3) {
                    return str3;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void exitApplication(int i, byte[] bArr, String str) {
        getmMiSnapStateMachine().sendMessage(getmMiSnapStateMachine().obtainMessage(20, i, 0, str));
    }

    /* access modifiers changed from: protected */
    public int processParams(String str, int i) {
        int i2;
        int i3 = 0;
        if (str == null || this.mMiSnapParamsMgr == null) {
            return SDKConstants.ERROR_PROCESSING_JOB_PARAMETERS;
        }
        int verifyThem = this.mMiSnapParamsMgr.verifyThem(str, i);
        createManagersThatDependOnParameters();
        if (verifyThem == SDKConstants.RESULT_SUCCESS) {
            if (VERSION.SDK_INT >= 9) {
                boolean z = this.mMiSnapParamsMgr.getUsePortraitOrientation() == 1;
                if (this.mMiSnapParamsMgr.getmMiSnapLockView() == 0) {
                    FragmentActivity activity = getActivity();
                    if (z) {
                        i2 = 7;
                    } else {
                        i2 = 6;
                    }
                    activity.setRequestedOrientation(i2);
                } else if (!Utils.isScreenLandscapeLeft(getActivity())) {
                    getActivity().setRequestedOrientation(z ? 9 : 8);
                } else {
                    FragmentActivity activity2 = getActivity();
                    if (z) {
                        i3 = 1;
                    }
                    activity2.setRequestedOrientation(i3);
                }
            }
            mParamsInitialized = true;
            getmMiSnapStateMachine().sendEmptyMessage(3);
        } else {
            handleErrorState(MiSnapAPI.RESULT_ERROR_INTENT_PARAMETERS);
        }
        return verifyThem;
    }

    public void onEvent(ShutdownEvent shutdownEvent) {
        String str;
        int i = 0;
        switch (shutdownEvent.reason) {
            case 0:
                i = -1;
                if (!this.mMiSnapParamsMgr.isCurrentModeVideo()) {
                    str = MiSnapAPI.RESULT_SUCCESS_STILL;
                    break;
                } else {
                    str = MiSnapAPI.RESULT_SUCCESS_VIDEO;
                    break;
                }
            case 1:
                if (ShutdownEvent.EXT_HELP_BUTTON.equals(shutdownEvent.extendedReason)) {
                    Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_help_button);
                }
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_help_begin);
                break;
            case 2:
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_help_begin);
                break;
            case 3:
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_measured_failover);
                break;
            case 4:
                Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_cancel);
                break;
            case 5:
                str = shutdownEvent.extendedReason;
                break;
        }
        str = "Cancelled";
        exitApplication(i, null, str);
    }

    public void onEvent(CaptureCurrentFrameEvent captureCurrentFrameEvent) {
        Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_capture_manual);
        getmMiSnapStateMachine().removeCallbacks(this.mMiSnapStartAutoFocusTimeout);
        Utils.sendMsgToCameraMgr(getActivity(), SDKConstants.CAM_STATE_MANUAL_BUTTON_CLICKED);
    }

    public void onEvent(SetCaptureModeEvent setCaptureModeEvent) {
        if (setCaptureModeEvent.mode == 1) {
            Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_measured_failover);
            Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_start_manual_capture_mode);
        } else {
            Utils.uxpEvent(getActivity().getApplicationContext(), C4540R.string.misnap_uxp_start_auto_capture_mode);
        }
        Utils.sendMsgToCameraMgr(getActivity(), SDKConstants.CAM_SWITCH_CAPTURE_MODE, setCaptureModeEvent.mode);
        startFocusModeSwitchTimer();
        EventBus.getDefault().post(new OnCaptureModeChangedEvent(setCaptureModeEvent.mode));
    }

    public void onEvent(OnFrameProcessedEvent onFrameProcessedEvent) {
        if (!this.mMiSnapParamsMgr.isCurrentModeVideo()) {
            this.mWarnings = onFrameProcessedEvent.getFrameChecksFailed();
        } else if (onFrameProcessedEvent.didFrameCheckFail(2048) && this.mMiSnapParamsMgr.isCheckBack()) {
            this.mWarnings |= 2048;
        }
    }
}
