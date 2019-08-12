package com.miteksystems.misnap.p312a;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.common.ConnectionResult;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;
import com.miteksystems.misnap.C4540R;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse.ExtraInfo;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.analyzer.MiBaseAnalyzer;
import com.miteksystems.misnap.imaging.JPEGProcessor;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.utils.Utils;

/* renamed from: com.miteksystems.misnap.a.g */
public class C4553g extends SurfaceView implements AutoFocusCallback, ErrorCallback, PictureCallback, PreviewCallback, Callback, IAnalyzeResponse {

    /* renamed from: h */
    static boolean f3604h = false;

    /* renamed from: i */
    static boolean f3605i = false;

    /* renamed from: j */
    static boolean f3606j = false;

    /* renamed from: k */
    static boolean f3607k = false;

    /* renamed from: l */
    public static boolean f3608l = false;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public static final String f3609q = C4553g.class.getSimpleName();

    /* renamed from: a */
    final int f3610a = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;

    /* renamed from: b */
    Context f3611b = null;

    /* renamed from: c */
    Context f3612c = null;

    /* renamed from: d */
    C4545b f3613d = null;

    /* renamed from: e */
    ParameterManager f3614e;

    /* renamed from: f */
    IAnalyzer f3615f;
    /* access modifiers changed from: 0000 */

    /* renamed from: g */
    public Handler f3616g;

    /* renamed from: m */
    Runnable f3617m = new Runnable() {
        public final void run() {
            if (C4553g.this.f3616g != null && !C4553g.f3607k && !C4553g.f3604h) {
                try {
                    if (!C4553g.f3605i) {
                        C4553g.this.f3616g.post(C4553g.this.f3618n);
                    }
                    C4553g.this.f3616g.postDelayed(C4553g.this.f3617m, 1500);
                } catch (Exception e) {
                }
            }
        }
    };

    /* renamed from: n */
    Runnable f3618n = new Runnable() {
        public final void run() {
            try {
                if (C4553g.f3608l) {
                    C4553g.f3609q;
                    new StringBuilder("autoFocus received:mFocusing ").append(C4553g.f3605i).append(" mSurfaceDestroyed: ").append(C4553g.f3604h);
                }
                if (!C4553g.f3607k && !C4553g.f3605i && !C4553g.f3604h && C4553g.this.f3613d.f3583k != null) {
                    C4553g.f3609q;
                    C4553g.this.f3613d.f3583k.autoFocus(C4553g.this);
                    Utils.uxpEvent(C4553g.this.f3612c, C4540R.string.misnap_uxp_misnap_focus);
                    C4553g.f3605i = true;
                }
            } catch (Exception e) {
            }
        }
    };

    /* renamed from: o */
    Runnable f3619o = new Runnable() {
        public final void run() {
            try {
                C4553g.this.f3613d.f3583k.takePicture(null, null, null, C4553g.this);
            } catch (Exception e) {
            }
        }
    };

    /* renamed from: p */
    Runnable f3620p = new Runnable() {
        public final void run() {
            C4553g.f3609q;
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: r */
    public int f3621r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public int f3622s = 1;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public int f3623t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public int f3624u;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public int f3625v;

    /* renamed from: w */
    private JPEGProcessor f3626w;

    public C4553g(Context context, C4545b bVar, ParameterManager parameterManager, IAnalyzer iAnalyzer) {
        super(context);
        this.f3611b = context;
        this.f3612c = this.f3611b.getApplicationContext();
        this.f3613d = bVar;
        this.f3614e = parameterManager;
        this.f3626w = new JPEGProcessor();
        f3606j = false;
        f3605i = false;
        f3604h = false;
        this.f3615f = iAnalyzer;
        this.f3615f.init();
        getHolder().addCallback(this);
        getHolder().setType(3);
        this.f3616g = new Handler();
        if (parameterManager.getmAllowScreenshots() == 0 && VERSION.SDK_INT >= 17) {
            setSecure(true);
        }
    }

    public void onAutoFocus(boolean z, Camera camera) {
        int i = 0;
        f3605i = false;
        if (this.f3613d.f3579g) {
            if (this.f3614e.getUseFrontCamera() == 0) {
                i = 100;
                if (VERSION.SDK_INT >= 23) {
                    i = 600;
                }
            }
            this.f3613d.f3583k.setPreviewCallback(null);
            this.f3616g.postDelayed(this.f3619o, (long) i);
        }
    }

    public void onPictureTaken(final byte[] bArr, Camera camera) {
        if (this.f3615f instanceof MiBaseAnalyzer) {
            ((MiBaseAnalyzer) this.f3615f).onManualPictureTaken(new IAnalyzeResponse() {
                public final void onAnalyzeFail(int i, ExtraInfo extraInfo) {
                    C4553g.this.m2110a(bArr);
                }

                public final void onAnalyzeSuccess(int i, ExtraInfo extraInfo) {
                    C4553g.this.m2110a(bArr);
                }
            }, bArr);
            return;
        }
        new ExtraInfo();
        m2110a(bArr);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2110a(byte[] bArr) {
        m2117c();
        Size pictureSize = this.f3613d.mo44345b().getPictureSize();
        m2114b(m2118c(this.f3626w.scaleAndCompress(this.f3612c, bArr, this.f3614e.getmImageQuality(), pictureSize.width, pictureSize.height)));
    }

    public synchronized void onPreviewFrame(byte[] bArr, Camera camera) {
        if (!(f3604h || this.f3612c == null || bArr == null || camera == null)) {
            if (!f3606j) {
                f3606j = true;
                Utils.sendMsgToCameraMgr(this.f3612c, SDKConstants.CAM_STATE_PREVIEW_STARTED);
            } else if (!f3605i && !this.f3613d.f3579g && this.f3615f != null) {
                this.f3615f.analyze(this, bArr, this.f3622s, this.f3623t, this.f3621r);
            }
        }
    }

    public void onAnalyzeFail(int i, ExtraInfo extraInfo) {
        switch (i) {
            case 1:
                this.f3616g.post(this.f3620p);
                return;
            default:
                return;
        }
    }

    public void onAnalyzeSuccess(int i, ExtraInfo extraInfo) {
        if (this.f3614e.isCurrentModeVideo()) {
            Utils.uxpEvent(this.f3612c, C4540R.string.misnap_uxp_capture_time);
        } else {
            Utils.uxpEvent(this.f3612c, C4540R.string.misnap_uxp_capture_manual);
        }
        if (Utils.isScreenLandscapeLeft(this.f3612c)) {
            Utils.uxpEvent(this.f3612c, C4540R.string.misnap_uxp_device_landscape_left);
        } else {
            Utils.uxpEvent(this.f3612c, C4540R.string.misnap_uxp_device_landscape_right);
        }
        this.f3616g.removeCallbacks(this.f3617m);
        m2117c();
        if (extraInfo == null || extraInfo.yuvImage == null) {
            m2114b((byte[]) null);
        } else {
            m2114b(m2118c(this.f3626w.convertYUVtoJPEG(extraInfo.yuvImage, this.f3622s, this.f3623t, this.f3614e.getmImageQuality())));
        }
    }

    /* renamed from: b */
    private void m2114b(byte[] bArr) {
        if (bArr != null) {
            Utils.sendFinalFrame(this.f3612c, bArr);
        }
    }

    /* renamed from: c */
    private void m2117c() {
        mo44359a();
        Utils.broadcastMsgToMiSnap(this.f3612c, SDKConstants.MISNAP_CAM_GOOD_FRAME_RECEIVED);
        Utils.sendMsgToCameraMgr(this.f3612c, SDKConstants.CAM_STATE_GOOD_FRAME_STUFF);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        f3604h = true;
        f3605i = false;
        f3606j = false;
        f3607k = false;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public void surfaceChanged(final SurfaceHolder surfaceHolder, int i, final int i2, final int i3) {
        if (this.f3613d != null && this.f3616g != null) {
            this.f3616g.post(new Runnable() {
                public final void run() {
                    int c;
                    int b;
                    if (C4553g.this.f3614e.getUsePortraitOrientation() == 1) {
                        C4553g.this.mo44360a(surfaceHolder, i3, i2);
                    } else {
                        C4553g.this.mo44360a(surfaceHolder, i2, i3);
                    }
                    if (C4553g.this.f3613d.mo44345b() != null) {
                        C4553g.this.f3622s = C4553g.this.f3613d.mo44345b().getPreviewSize().width;
                        C4553g.this.f3623t = C4553g.this.f3613d.mo44345b().getPreviewSize().height;
                        C4553g.this.f3624u = C4553g.this.f3622s;
                        C4553g.this.f3625v = C4553g.this.f3623t;
                        C4553g.this.f3621r = C4553g.this.f3613d.mo44345b().getPreviewFormat();
                        if (C4553g.this.f3614e.getUseFrontCamera() == 1) {
                            if (C4553g.this.f3614e.getUsePortraitOrientation() == 0) {
                                c = C4553g.this.f3622s;
                                b = C4553g.this.f3623t;
                            } else {
                                c = C4553g.this.f3623t;
                                b = C4553g.this.f3622s;
                            }
                            float abs = Math.abs((((float) c) / ((float) b)) - (((float) i2) / ((float) i3)));
                            new C4551e();
                            if (abs > 0.12f) {
                                float min = Math.min(((float) i2) / ((float) c), ((float) i3) / ((float) b));
                                int i = (int) (((float) c) * min);
                                int i2 = (int) (((float) b) * min);
                                C4553g.f3609q;
                                new StringBuilder("Screen=").append(i2).append("x").append(i3).append(" Preview=").append(C4553g.this.f3622s).append("x").append(C4553g.this.f3623t).append(" Fill=").append(i).append("x").append(i2);
                                C4553g.this.getHolder().setFixedSize(i, i2);
                                LayoutParams layoutParams = C4553g.this.getLayoutParams();
                                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                                    ((RelativeLayout.LayoutParams) layoutParams).addRule(13);
                                } else if (layoutParams instanceof FrameLayout.LayoutParams) {
                                    ((FrameLayout.LayoutParams) layoutParams).gravity = 17;
                                }
                                layoutParams.width = i;
                                layoutParams.height = i2;
                                C4553g.this.setLayoutParams(layoutParams);
                            }
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo44360a(SurfaceHolder surfaceHolder, int i, int i2) {
        boolean z;
        try {
            C4545b bVar = this.f3613d;
            if (bVar.mo44345b() == null) {
                Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_CONFIGURING_CAMERA);
                z = false;
            } else if (bVar.f3597y.mo44335d()) {
                if (bVar.f3593u) {
                    C4549c b = bVar.f3597y.mo44333b();
                    Camera camera = bVar.f3583k;
                    camera.getClass();
                    z = bVar.mo44343a(new Size(camera, b.f3602a, b.f3603b));
                } else {
                    z = false;
                }
                if (!z) {
                    C4549c c = bVar.f3597y.mo44334c();
                    Camera camera2 = bVar.f3583k;
                    camera2.getClass();
                    z = bVar.mo44343a(new Size(camera2, c.f3602a, c.f3603b));
                }
            } else {
                z = bVar.mo44343a(C4550d.m2090a(i, i2, bVar.f3577b, bVar.f3583k, bVar.mo44345b().getSupportedPreviewSizes(), bVar.f3593u, bVar.f3594v, bVar.f3597y));
            }
            if (!z) {
                Utils.broadcastMsgToMiSnap(bVar.f3576a, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_CAMERA_NOT_SUFFICIENT);
            }
            this.f3613d.mo44340a(this.f3614e.getmFocusMode());
            this.f3613d.mo44350g();
            this.f3613d.mo44351h();
            this.f3613d.mo44344a(surfaceHolder);
            Utils.sendMsgToCameraMgr(this.f3612c, SDKConstants.CAM_STATE_READY);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        } catch (Exception e2) {
            e2.printStackTrace();
            Utils.broadcastMsgToMiSnap(this.f3612c, SDKConstants.MISNAP_ERROR_STATE, MiSnapAPI.RESULT_ERROR_CONFIGURING_CAMERA);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo44359a() {
        if (this.f3615f != null) {
            this.f3615f.deinit();
            this.f3615f = null;
        }
    }

    public void onError(int i, Camera camera) {
    }

    /* renamed from: c */
    private byte[] m2118c(byte[] bArr) {
        int i = 0;
        if (Utils.needToRotateFrameBy180Degrees(this.f3611b)) {
            i = 180;
        }
        if (this.f3614e.getUsePortraitOrientation() == 1) {
            i += 90;
            this.f3624u = this.f3622s;
            this.f3625v = this.f3623t;
        }
        if (this.f3614e.getUseFrontCamera() == 1) {
            i = (360 - i) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
        }
        return this.f3626w.rotateJPEG(bArr, i, this.f3614e.getmImageQuality());
    }
}
