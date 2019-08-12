package com.miteksystems.misnap.p312a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import com.miteksystems.misnap.C4540R;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.events.OnCapturedFrameEvent;
import com.miteksystems.misnap.events.OnFrameProcessedEvent;
import com.miteksystems.misnap.events.OnTorchStateEvent;
import com.miteksystems.misnap.events.TorchStateEvent;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.params.MiSnapConstants;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.storage.PreferenceManager;
import com.miteksystems.misnap.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p314de.greenrobot.event.EventBus;

/* renamed from: com.miteksystems.misnap.a.b */
public class C4545b {
    /* access modifiers changed from: private */

    /* renamed from: B */
    public static final String f3568B = C4545b.class.getSimpleName();

    /* renamed from: d */
    static int f3569d = 0;

    /* renamed from: e */
    public static boolean f3570e = false;

    /* renamed from: f */
    static boolean f3571f = false;

    /* renamed from: A */
    public BroadcastReceiver f3572A = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            int i;
            int i2 = -1;
            if (context != null && intent != null) {
                String action = intent.getAction();
                if (action.equals(SDKConstants.CAMERA_MANAGER_BROADCASTER)) {
                    i = intent.getIntExtra(SDKConstants.CAM_BROADCAST_MESSAGE_ID, 0);
                    i2 = intent.getIntExtra(SDKConstants.CAM_BROADCAST_MESSAGE_PARAM1, -1);
                } else if (action.equals(SDKConstants.UI_FRAGMENT_BROADCASTER)) {
                    i = intent.getIntExtra(SDKConstants.UI_FRAGMENT_BROADCAST_MESSAGE_ID, 0);
                } else {
                    i = -1;
                }
                switch (i) {
                    case SDKConstants.CAM_INIT_CAMERA /*20000*/:
                        C4545b.m2064a(C4545b.this);
                        return;
                    case SDKConstants.CAM_PREPARE_CAMERA /*20001*/:
                        C4545b bVar = C4545b.this;
                        if (C4545b.f3569d == 1) {
                            try {
                                C4545b.f3569d = 2;
                                bVar.f3592t = new C4553g(bVar.f3576a, bVar, bVar.f3577b, bVar.f3578c);
                                bVar.f3578c = null;
                                C4545b.f3569d = 5;
                                Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_CAM_CAMERA_SURFACE_PREPARED);
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                C4545b.f3569d = 1;
                                Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_CREATING_CAMERA_VIEW);
                                return;
                            }
                        } else {
                            Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_SDK_STATE_ERROR);
                            return;
                        }
                    case SDKConstants.CAM_START_PREVIEW /*20003*/:
                        C4545b.this.m2074k();
                        return;
                    case SDKConstants.CAM_STATE_PREVIEW_STARTED /*20004*/:
                        C4545b.m2069e(C4545b.this);
                        return;
                    case SDKConstants.CAM_STATE_STOP /*20006*/:
                        C4545b.f3568B;
                        C4545b.this.mo44347d();
                        return;
                    case SDKConstants.CAM_STATE_GOOD_FRAME_STUFF /*20008*/:
                        C4545b.this.mo44347d();
                        return;
                    case SDKConstants.CAM_STATE_READY /*20009*/:
                        C4545b.m2066b(C4545b.this);
                        return;
                    case SDKConstants.CAM_STATE_MANUAL_BUTTON_CLICKED /*20011*/:
                        C4545b.m2070f(C4545b.this);
                        return;
                    case SDKConstants.CAM_RESTART_PREVIEW /*20012*/:
                        C4545b.m2068d(C4545b.this);
                        return;
                    case SDKConstants.CAM_LOW_LIGHT_DETECTED /*20013*/:
                        C4545b bVar2 = C4545b.this;
                        if (bVar2.f3577b.getmTorchMode() == 1 && !bVar2.mo44346c() && !C4545b.f3571f) {
                            bVar2.mo44342a(true, false);
                            return;
                        }
                        return;
                    case SDKConstants.CAM_TOO_MUCH_LIGHT_DETECTED /*20014*/:
                        C4545b bVar3 = C4545b.this;
                        if (bVar3.f3577b.getmTorchMode() == 1 && bVar3.mo44346c() && !C4545b.f3571f) {
                            bVar3.mo44342a(false, false);
                            return;
                        }
                        return;
                    case SDKConstants.CAM_SWITCH_FOCUS_MODE /*20015*/:
                        C4545b bVar4 = C4545b.this;
                        if (bVar4.f3586n && bVar4.f3592t != null) {
                            bVar4.mo44341a(false);
                            Utils.uxpEvent(bVar4.f3582j, C4540R.string.misnap_uxp_focus_mode_switch);
                            return;
                        }
                        return;
                    case SDKConstants.CAM_SWITCH_CAPTURE_MODE /*20016*/:
                        C4545b bVar5 = C4545b.this;
                        if (bVar5.f3592t == null) {
                            return;
                        }
                        if (i2 == 1 && bVar5.f3592t.f3614e.isCurrentModeVideo()) {
                            C4553g gVar = bVar5.f3592t;
                            if (gVar.f3616g != null) {
                                gVar.f3616g.removeCallbacks(gVar.f3620p);
                                gVar.f3616g.removeCallbacks(gVar.f3618n);
                                gVar.f3616g.removeCallbacks(gVar.f3617m);
                            }
                            gVar.f3613d.f3583k.cancelAutoFocus();
                            C4553g.f3605i = false;
                            gVar.f3615f.deinit();
                            gVar.f3614e.setmCaptureMode(1);
                            bVar5.mo44348e();
                            bVar5.mo44340a(bVar5.f3577b.getmFocusMode());
                            return;
                        } else if (i2 == 2 && !bVar5.f3592t.f3614e.isCurrentModeVideo()) {
                            C4553g gVar2 = bVar5.f3592t;
                            C4553g.f3605i = false;
                            gVar2.f3613d.f3583k.cancelAutoFocus();
                            gVar2.f3614e.setmCaptureMode(2);
                            gVar2.f3615f.init();
                            bVar5.mo44348e();
                            bVar5.mo44340a(bVar5.f3577b.getmFocusMode());
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    };

    /* renamed from: C */
    private int f3573C;

    /* renamed from: D */
    private int f3574D;

    /* renamed from: E */
    private boolean f3575E;

    /* renamed from: a */
    public Context f3576a = null;

    /* renamed from: b */
    ParameterManager f3577b = null;

    /* renamed from: c */
    IAnalyzer f3578c;

    /* renamed from: g */
    boolean f3579g = false;

    /* renamed from: h */
    OrientationEventListener f3580h;

    /* renamed from: i */
    String f3581i = "infinity";

    /* renamed from: j */
    Context f3582j = null;

    /* renamed from: k */
    Camera f3583k = null;

    /* renamed from: l */
    Parameters f3584l = null;

    /* renamed from: m */
    C4550d f3585m;

    /* renamed from: n */
    boolean f3586n = false;

    /* renamed from: o */
    boolean f3587o = false;

    /* renamed from: p */
    boolean f3588p = false;

    /* renamed from: q */
    boolean f3589q = false;

    /* renamed from: r */
    boolean f3590r = false;

    /* renamed from: s */
    Size f3591s = null;

    /* renamed from: t */
    public C4553g f3592t = null;

    /* renamed from: u */
    boolean f3593u = false;

    /* renamed from: v */
    boolean f3594v = false;

    /* renamed from: w */
    boolean f3595w = false;

    /* renamed from: x */
    protected AtomicBoolean f3596x;

    /* renamed from: y */
    C4552f f3597y;

    /* renamed from: z */
    public int f3598z = -1;

    /* renamed from: f */
    static /* synthetic */ void m2070f(C4545b bVar) {
        bVar.f3579g = true;
        bVar.mo44341a(true);
    }

    public C4545b(Context context, ParameterManager parameterManager, IAnalyzer iAnalyzer) {
        this.f3576a = context;
        this.f3582j = this.f3576a.getApplicationContext();
        this.f3577b = parameterManager;
        this.f3585m = new C4550d();
        this.f3578c = iAnalyzer;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SDKConstants.CAMERA_MANAGER_BROADCASTER);
        intentFilter.addAction(SDKConstants.UI_FRAGMENT_BROADCASTER);
        LocalBroadcastManager.getInstance(this.f3576a).registerReceiver(this.f3572A, intentFilter);
        f3570e = true;
        this.f3575E = false;
        this.f3596x = new AtomicBoolean(false);
        EventBus.getDefault().register(this);
        this.f3573C = 0;
        f3569d = 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Camera mo44339a() {
        if (this.f3577b.getUseFrontCamera() == 0) {
            this.f3597y = new C4544a();
        } else {
            this.f3597y = new C4551e();
        }
        if (!this.f3576a.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            throw new Exception("MiSnap: Camera Hardware does not exits");
        }
        if (this.f3583k != null) {
            this.f3583k.release();
        }
        int i = 0;
        while (i < Camera.getNumberOfCameras()) {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == this.f3597y.mo44332a()) {
                try {
                    this.f3583k = Camera.open(i);
                    this.f3598z = i;
                    Utils.setCameraId(this.f3598z);
                    break;
                } catch (Exception e) {
                    if (this.f3583k != null) {
                        this.f3583k.release();
                        this.f3583k = null;
                    }
                }
            } else {
                i++;
            }
        }
        if (this.f3583k != null) {
            return this.f3583k;
        }
        throw new Exception("MiSnap: Trouble starting native Camera");
    }

    /* renamed from: b */
    public final Parameters mo44345b() {
        try {
            if (this.f3584l == null && this.f3583k != null) {
                this.f3584l = this.f3583k.getParameters();
            }
            return this.f3584l;
        } catch (Exception e) {
            this.f3584l = null;
            return null;
        }
    }

    /* renamed from: a */
    public final void mo44341a(boolean z) {
        mo44340a(1);
        if (this.f3592t != null) {
            C4553g gVar = this.f3592t;
            if (gVar.f3616g != null && gVar.f3618n != null) {
                gVar.f3616g.post(gVar.f3618n);
                if (!z) {
                    gVar.f3616g.postDelayed(gVar.f3617m, 1500);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo44342a(boolean z, boolean z2) {
        int i = 1;
        if (z2) {
            f3571f = true;
        }
        if (this.f3595w) {
            boolean c = mo44346c();
            if (c != z) {
                m2065a(!c, mo44345b());
                boolean c2 = mo44346c();
                EventBus eventBus = EventBus.getDefault();
                String str = "SET";
                if (!c2) {
                    i = 0;
                }
                eventBus.post(new OnTorchStateEvent(str, i));
                return;
            }
            EventBus eventBus2 = EventBus.getDefault();
            String str2 = "SET";
            if (!z) {
                i = 0;
            }
            eventBus2.post(new OnTorchStateEvent(str2, i));
            return;
        }
        EventBus.getDefault().post(new OnTorchStateEvent("SET", -1));
    }

    /* renamed from: c */
    public final boolean mo44346c() {
        try {
            Parameters b = mo44345b();
            if (b != null) {
                return b.getFlashMode().equals("torch");
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m2074k() {
        if (f3569d == 3) {
            try {
                C4550d.m2092a(this.f3576a, this.f3583k, this.f3598z);
                this.f3583k.setPreviewCallback(this.f3592t);
                this.f3583k.startPreview();
                f3569d = 4;
            } catch (Exception e) {
                e.printStackTrace();
                Utils.broadcastMsgToMiSnap(this.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_STARTING_CAMERA);
            }
        }
    }

    /* renamed from: d */
    public final synchronized void mo44347d() {
        try {
            if (this.f3580h != null) {
                this.f3580h.disable();
                this.f3580h = null;
            }
            if (EventBus.getDefault().isRegistered(this)) {
                try {
                    EventBus.getDefault().unregister(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.f3592t != null) {
                C4553g gVar = this.f3592t;
                C4553g.f3604h = true;
                gVar.post(new Runnable() {
                    public final void run() {
                        C4553g.this.setVisibility(8);
                    }
                });
                if (gVar.f3616g != null) {
                    gVar.f3616g.removeCallbacks(gVar.f3620p);
                    gVar.f3616g.removeCallbacks(gVar.f3617m);
                    gVar.f3616g.removeCallbacks(gVar.f3618n);
                }
                gVar.getHolder().removeCallback(gVar);
                C4553g.f3606j = false;
                C4553g.f3605i = false;
                gVar.mo44359a();
                this.f3592t = null;
            }
            if (this.f3583k != null) {
                this.f3583k.stopPreview();
                this.f3583k.setPreviewCallback(null);
                this.f3583k.release();
                this.f3583k = null;
            }
        } catch (Exception e2) {
        }
        f3569d = 0;
    }

    /* renamed from: e */
    public final void mo44348e() {
        if (this.f3586n && this.f3592t != null) {
            if (this.f3577b.getmFocusMode() == 1) {
                mo44341a(false);
            } else {
                this.f3577b.getmFocusMode();
            }
        }
    }

    /* renamed from: f */
    public final void mo44349f() {
        if (this.f3583k != null && this.f3576a != null) {
            PreferenceManager preferenceManager = new PreferenceManager(this.f3597y.mo44332a());
            if (!preferenceManager.getBoolean(this.f3576a, MiSnapConstants.PREF_TORCH_CALCULATION_DONE_KEY)) {
                Parameters parameters = this.f3583k.getParameters();
                List list = parameters != null ? parameters.getSupportedFlashModes() : null;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if ("torch".equals((String) it.next())) {
                            this.f3595w = true;
                            break;
                        }
                    }
                }
                preferenceManager.save(this.f3576a, MiSnapConstants.PREF_TORCH_SUPPORTED_KEY, this.f3595w);
                preferenceManager.save(this.f3576a, MiSnapConstants.PREF_TORCH_CALCULATION_DONE_KEY, true);
                return;
            }
            this.f3595w = preferenceManager.getBoolean(this.f3576a, MiSnapConstants.PREF_TORCH_SUPPORTED_KEY);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo44343a(Size size) {
        Parameters b = mo44345b();
        if (size != null) {
            b.setPreviewSize(size.width, size.height);
            new StringBuilder("Preview size = ").append(size.width).append("x").append(size.height);
            PreferenceManager preferenceManager = new PreferenceManager(this.f3597y.mo44332a());
            if (!preferenceManager.getBoolean(this.f3576a, MiSnapConstants.PREF_PREVIEW_SIZE_CALCULATION_DONE_KEY)) {
                preferenceManager.save(this.f3576a, MiSnapConstants.PREF_PREVIEW_WIDTH_KEY, String.valueOf(size.width));
                preferenceManager.save(this.f3576a, MiSnapConstants.PREF_PREVIEW_HEIGHT_KEY, String.valueOf(size.height));
                preferenceManager.save(this.f3576a, MiSnapConstants.PREF_PREVIEW_SIZE_CALCULATION_DONE_KEY, true);
            }
        } else {
            this.f3577b.setmCaptureMode(1);
        }
        Size a = C4550d.m2091a(b.getSupportedPictureSizes(), size, this.f3577b.getmImageHorizontalPixels(), this.f3597y.mo44337f());
        if (a == null) {
            return false;
        }
        b.setPictureSize(a.width, a.height);
        new StringBuilder("Picture size = ").append(a.width).append("x").append(a.height);
        Utils.savePictureSizeInPrefFile(this.f3582j, a.width, a.height);
        this.f3591s = a;
        this.f3583k.setParameters(b);
        return true;
    }

    /* renamed from: g */
    public final boolean mo44350g() {
        try {
            Parameters b = mo44345b();
            if (b != null) {
                b.setPictureFormat(256);
                b.setPreviewFormat(17);
                b.setJpegQuality(this.f3577b.getmImageQuality());
                this.f3583k.setParameters(b);
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /* renamed from: h */
    public final boolean mo44351h() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        try {
            if (this.f3595w) {
                switch (this.f3577b.getmTorchMode()) {
                    case 0:
                        Utils.uxpEvent(this.f3582j, C4540R.string.misnap_uxp_flash_off);
                        m2065a(false, mo44345b());
                        f3571f = true;
                        break;
                    case 1:
                        Utils.uxpEvent(this.f3582j, C4540R.string.misnap_uxp_flash_auto_on);
                        if (this.f3577b.getmJobName().contains(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE) || this.f3577b.getmJobName().contains("PASSPORT") || this.f3577b.getmJobName().equals("PDF417") || this.f3577b.getmJobName().equals(MiSnapApiConstants.PARAMETER_DOCTYPE_BARCODES)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        f3571f = z2;
                        if (!z) {
                            z3 = true;
                        }
                        m2065a(z3, mo44345b());
                        break;
                    case 2:
                        Utils.uxpEvent(this.f3582j, C4540R.string.misnap_uxp_flash_on);
                        m2065a(true, mo44345b());
                        f3571f = true;
                        break;
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private void m2065a(boolean z, Parameters parameters) {
        if (parameters != null) {
            try {
                if (this.f3595w) {
                    String str = z ? "torch" : "off";
                    if (!z) {
                        if (Build.MODEL.contains("Behold II")) {
                            parameters.set("flash-value", 1);
                        } else {
                            parameters.set("flash-value", 2);
                        }
                    }
                    parameters.setFlashMode(str);
                    this.f3583k.setParameters(parameters);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo44344a(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null) {
            try {
                this.f3583k.setPreviewDisplay(surfaceHolder);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        return true;
    }

    /* renamed from: i */
    public final void mo44352i() {
        this.f3596x.set(true);
    }

    /* renamed from: a */
    public final void mo44340a(int i) {
        boolean z = true;
        try {
            Parameters b = mo44345b();
            if (b != null) {
                if (i == 1) {
                    if (this.f3586n) {
                        b.setFocusMode("auto");
                        this.f3581i = "auto";
                    }
                    z = false;
                } else if (this.f3588p) {
                    b.setFocusMode("continuous-picture");
                    this.f3581i = "continuous-picture";
                } else if (this.f3587o) {
                    b.setFocusMode("continuous-video");
                    this.f3581i = "continuous-video";
                } else {
                    if (this.f3586n) {
                        b.setFocusMode("auto");
                        this.f3581i = "auto";
                    }
                    z = false;
                }
                if (!z) {
                    if (this.f3597y.mo44336e()) {
                        return;
                    }
                    if (this.f3590r) {
                        b.setFocusMode("infinity");
                        this.f3581i = "infinity";
                    } else if (this.f3589q) {
                        b.setFocusMode("fixed");
                        this.f3581i = "fixed";
                    } else {
                        return;
                    }
                }
                this.f3583k.setParameters(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEvent(TorchStateEvent torchStateEvent) {
        int i = 0;
        if (torchStateEvent.function.equals("GET")) {
            if (this.f3576a != null) {
                if (!this.f3595w) {
                    i = -1;
                } else if (mo44346c()) {
                    i = 1;
                }
            }
            EventBus.getDefault().post(new OnTorchStateEvent(torchStateEvent.function, i));
        } else if (torchStateEvent.function.equals("SET")) {
            mo44342a(torchStateEvent.newState, true);
        }
    }

    public void onEvent(OnFrameProcessedEvent onFrameProcessedEvent) {
        int i = onFrameProcessedEvent.frameChecksPassed;
        if ((i & 1) == 0 || (i & 64) == 0 || (i & 2) == 0) {
            this.f3573C = 0;
            return;
        }
        if ((i & 8) == 0) {
            int i2 = this.f3573C + 1;
            this.f3573C = i2;
            if (i2 >= 3) {
                Utils.sendMsgToCameraMgr(this.f3582j, SDKConstants.CAM_TOO_MUCH_LIGHT_DETECTED);
            } else {
                return;
            }
        } else if ((i & 4) == 0) {
            int i3 = this.f3573C + 1;
            this.f3573C = i3;
            if (i3 >= 3) {
                Utils.sendMsgToCameraMgr(this.f3582j, SDKConstants.CAM_LOW_LIGHT_DETECTED);
            } else {
                return;
            }
        }
        this.f3573C = 0;
    }

    public void onEvent(OnCapturedFrameEvent onCapturedFrameEvent) {
        this.f3575E = true;
    }

    /* renamed from: a */
    static /* synthetic */ void m2064a(C4545b bVar) {
        if (f3569d == 0) {
            new Thread(new Runnable() {
                public final void run() {
                    try {
                        if (C4545b.this.f3583k == null) {
                            C4545b.this.f3583k = C4545b.this.mo44339a();
                        }
                        C4545b.f3568B;
                        C4545b bVar = C4545b.this;
                        if (!(bVar.f3583k == null || bVar.f3576a == null)) {
                            PreferenceManager preferenceManager = new PreferenceManager(bVar.f3597y.mo44332a());
                            if (!preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_FOCUS_CALCULATION_DONE_KEY)) {
                                Parameters parameters = bVar.f3583k.getParameters();
                                if (parameters != null) {
                                    List supportedFocusModes = parameters.getSupportedFocusModes();
                                    if (supportedFocusModes != null) {
                                        bVar.f3586n = supportedFocusModes.contains("auto");
                                        if (!bVar.f3586n && bVar.f3597y.mo44332a() == 0) {
                                            bVar.f3586n = bVar.f3576a.getPackageManager().hasSystemFeature("android.hardware.camera.autofocus");
                                        }
                                        if (VERSION.SDK_INT >= 9) {
                                            bVar.f3587o = supportedFocusModes.contains("continuous-video");
                                        }
                                        if (VERSION.SDK_INT >= 14) {
                                            bVar.f3588p = supportedFocusModes.contains("continuous-picture");
                                        }
                                        bVar.f3589q = supportedFocusModes.contains("fixed");
                                        bVar.f3590r = supportedFocusModes.contains("infinity");
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_AUTO_FOCUS_SUPPORTED_KEY, bVar.f3586n);
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_VIDEO_FOCUS_SUPPORTED_KEY, bVar.f3587o);
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_PICTURE_FOCUS_SUPPORTED_KEY, bVar.f3588p);
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_INFINITY_FOCUS_SUPPORTED_KEY, bVar.f3590r);
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_FIXED_FOCUS_SUPPORTED_KEY, bVar.f3589q);
                                        preferenceManager.save(bVar.f3576a, MiSnapConstants.PREF_FOCUS_CALCULATION_DONE_KEY, true);
                                    }
                                }
                            } else {
                                bVar.f3586n = preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_AUTO_FOCUS_SUPPORTED_KEY);
                                bVar.f3587o = preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_VIDEO_FOCUS_SUPPORTED_KEY);
                                bVar.f3588p = preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_PICTURE_FOCUS_SUPPORTED_KEY);
                                bVar.f3590r = preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_INFINITY_FOCUS_SUPPORTED_KEY);
                                bVar.f3589q = preferenceManager.getBoolean(bVar.f3576a, MiSnapConstants.PREF_FIXED_FOCUS_SUPPORTED_KEY);
                            }
                            if (bVar.f3597y.mo44336e() && !bVar.f3586n && !bVar.f3587o && !bVar.f3588p) {
                                bVar.f3577b.setmCaptureMode(1);
                            }
                        }
                        C4545b bVar2 = C4545b.this;
                        if (!(bVar2.f3583k == null || bVar2.f3576a == null)) {
                            PreferenceManager preferenceManager2 = new PreferenceManager(bVar2.f3597y.mo44332a());
                            if (!preferenceManager2.getBoolean(bVar2.f3576a, MiSnapConstants.PREF_RESO_CALCULATION_DONE_KEY)) {
                                Parameters parameters2 = bVar2.f3583k.getParameters();
                                if (parameters2 != null) {
                                    List supportedPreviewSizes = parameters2.getSupportedPreviewSizes();
                                    if (!(supportedPreviewSizes == null || supportedPreviewSizes.size() == 0)) {
                                        C4549c b = bVar2.f3597y.mo44333b();
                                        Camera camera = bVar2.f3583k;
                                        camera.getClass();
                                        Size size = new Size(camera, b.f3602a, b.f3603b);
                                        C4549c c = bVar2.f3597y.mo44334c();
                                        Camera camera2 = bVar2.f3583k;
                                        camera2.getClass();
                                        Size size2 = new Size(camera2, c.f3602a, c.f3603b);
                                        bVar2.f3593u = supportedPreviewSizes.contains(size);
                                        bVar2.f3594v = supportedPreviewSizes.contains(size2);
                                        preferenceManager2.save(bVar2.f3576a, MiSnapConstants.PREF_HIGH_RES_SUPPORTED, bVar2.f3593u);
                                        preferenceManager2.save(bVar2.f3576a, MiSnapConstants.PREF_LOW_RES_SUPPORTED_KEY, bVar2.f3594v);
                                        preferenceManager2.save(bVar2.f3576a, MiSnapConstants.PREF_RESO_CALCULATION_DONE_KEY, true);
                                    }
                                }
                            } else {
                                bVar2.f3593u = preferenceManager2.getBoolean(bVar2.f3576a, MiSnapConstants.PREF_HIGH_RES_SUPPORTED);
                                bVar2.f3594v = preferenceManager2.getBoolean(bVar2.f3576a, MiSnapConstants.PREF_LOW_RES_SUPPORTED_KEY);
                            }
                            if (!bVar2.f3593u && !bVar2.f3594v) {
                                bVar2.f3577b.setmCaptureMode(1);
                            }
                        }
                        C4545b.this.mo44349f();
                        C4545b.f3569d = 1;
                        Utils.broadcastMsgToMiSnap(C4545b.this.f3576a, SDKConstants.MISNAP_CAM_CAMERA_INITIALZED);
                        if (C4545b.this.f3596x.get()) {
                            C4545b.this.mo44347d();
                            C4545b.this.f3596x.set(false);
                        } else if (C4545b.this.f3577b.getmMiSnapLockView() == 0) {
                            C4545b bVar3 = C4545b.this;
                            bVar3.f3580h = new OrientationEventListener(C4545b.this.f3582j) {
                                public final void onOrientationChanged(int i) {
                                    C4545b.m2072h(C4545b.this);
                                }
                            };
                            bVar3.f3580h.enable();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Utils.broadcastMsgToMiSnap(C4545b.this.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_STARTING_CAMERA);
                    }
                }
            }).start();
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m2066b(C4545b bVar) {
        int i;
        if (f3569d == 5) {
            f3569d = 3;
            Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_CAM_CAMERA_PREPARED);
            EventBus eventBus = EventBus.getDefault();
            String str = "GET";
            if (bVar.mo44346c()) {
                i = 1;
            } else {
                i = 0;
            }
            eventBus.post(new OnTorchStateEvent(str, i));
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m2068d(C4545b bVar) {
        if (f3569d == 3) {
            bVar.m2074k();
        } else if (f3569d == 4) {
            if (VERSION.SDK_INT < 14) {
                bVar.f3583k.stopPreview();
            }
            f3569d = 3;
            try {
                C4550d.m2092a(bVar.f3576a, bVar.f3583k, bVar.f3598z);
                if (VERSION.SDK_INT < 14) {
                    bVar.f3583k.setPreviewCallback(bVar.f3592t);
                    bVar.f3583k.startPreview();
                }
                f3569d = 4;
            } catch (Exception e) {
                e.printStackTrace();
                Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_STARTING_CAMERA);
            }
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m2069e(C4545b bVar) {
        if (f3569d == 4) {
            Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_CAM_CAMERA_PREVIEW_STARTS);
            bVar.mo44348e();
        }
    }

    /* renamed from: h */
    static /* synthetic */ void m2072h(C4545b bVar) {
        if (bVar.f3582j != null && bVar.f3582j != null && !bVar.f3575E) {
            int deviceCurrentOrientation = Utils.getDeviceCurrentOrientation(bVar.f3582j);
            if (deviceCurrentOrientation != bVar.f3574D) {
                new StringBuilder("Rotate from ").append(bVar.f3574D).append(" to ").append(deviceCurrentOrientation);
                bVar.f3574D = deviceCurrentOrientation;
                if (Utils.isScreenLandscapeLeft(bVar.f3576a)) {
                    Utils.uxpEvent(bVar.f3582j, C4540R.string.misnap_uxp_rotate_landscape_left);
                } else {
                    Utils.uxpEvent(bVar.f3582j, C4540R.string.misnap_uxp_rotate_landscape_right);
                }
                Utils.sendMsgToCameraMgr(bVar.f3582j, SDKConstants.CAM_RESTART_PREVIEW);
            }
        }
    }
}
