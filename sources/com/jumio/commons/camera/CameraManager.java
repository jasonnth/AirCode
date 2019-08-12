package com.jumio.commons.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.Parameters;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.facebook.react.uimanager.ViewProps;
import com.jumio.commons.PersistWith;
import com.jumio.commons.camera.ImageData.CameraPosition;
import com.jumio.commons.utils.DeviceRotationManager;
import com.jumio.commons.view.ViewFader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraManager implements SurfaceTextureListener, OnClickListener, OnTouchListener {
    protected static final int CAMERA_OPEN_TIMEOUT = 2000;
    private static final String[] FALLBACK_AUTO_FOCUS_LIST = {"GT-I9100", "SPH-D710", "SGH-T989", "SCH-I605", "SAMSUNG-SGH-I727", "GT-I9100G", "SAMSUNG-SGH-I777", "SPH-D710BST", "GT-I9100P", "GT-I9100T", "SGH-S959G", "SGH-T989D", "SGH-I727R", "GT-I9100M", "SPH-D710VMUB", "SAMSUNG-SGH-T989", "SGH-I757M", "SGH-I777", "GT-I9210", "GT-I9105P", "GT-I9105", "GT-I9105I", "GT-I9105G", "SAMSUNG-SGH-I717", "SGH-T879", "SGH-I717M", "SGH-I717R", "GT-N7000", "GT-N7005", "DROIDX"};
    private static final String TAG = "CameraManager";
    private AutoFocusCallback autoFocusCallback;
    protected byte[] callbackBuffer;
    protected Camera camera;
    protected final Object cameraAccessLock;
    protected ICameraCallback cameraCallback;
    protected int cameraId;
    protected boolean enableFlashOnStartUp;
    protected ExecutorService executorService;
    protected final Object flashCheckLock;
    protected CameraFlashThread flashCheckThread;
    protected boolean flashOn;
    protected boolean focusing;
    protected boolean frontFacing;
    protected boolean inPreview;
    protected boolean isFaceDetectionRunning;
    protected boolean manualFocusEnabled;
    protected int orientation;
    protected boolean pausePreview;
    protected PreviewProperties previewProperties;
    protected android.hardware.Camera.Size previewSize;
    protected Size requestedSize;
    protected DeviceRotationManager rotationManager;
    protected TextureView textureView;

    public class InitCameraRunnable implements Runnable {
        int displayRotation;
        int height;
        boolean isPortrait;
        SurfaceTexture surface;
        int width;

        public InitCameraRunnable(SurfaceTexture surface2, int width2, int height2, boolean isPortrait2, int displayRotation2) {
            this.surface = surface2;
            this.width = width2;
            this.height = height2;
            this.isPortrait = isPortrait2;
            this.displayRotation = displayRotation2;
        }

        public void run() {
            synchronized (CameraManager.this.cameraAccessLock) {
                CameraManager.this.initCamera();
                CameraManager.this.initPreview(this.surface, this.width, this.height, this.isPortrait, this.displayRotation);
                if (!CameraManager.this.pausePreview) {
                    CameraManager.this.startPreview();
                }
            }
        }
    }

    @PersistWith("PreviewProperties")
    public static class PreviewProperties implements Serializable {
        private static final long serialVersionUID = 2336255597278613463L;
        public boolean frontFacing;
        public int orientation;
        public Size preview;
        public int previewFormat = 17;
        public Size scaledPreview;
        public Size surface;

        public PreviewProperties copy() {
            PreviewProperties copy = new PreviewProperties();
            copy.scaledPreview = this.scaledPreview.copy();
            copy.surface = this.surface.copy();
            copy.preview = this.preview.copy();
            copy.frontFacing = this.frontFacing;
            copy.orientation = this.orientation;
            return copy;
        }

        public float getScaleFactor() {
            return ((float) this.scaledPreview.width) / ((float) this.preview.width);
        }

        public float getBytesPerPixel() {
            PixelFormat pf = new PixelFormat();
            PixelFormat.getPixelFormatInfo(this.previewFormat, pf);
            return ((float) pf.bitsPerPixel) / 8.0f;
        }
    }

    public class ReleaseCameraRunnable implements Runnable {
        public ReleaseCameraRunnable() {
        }

        public void run() {
            synchronized (CameraManager.this.cameraAccessLock) {
                if (CameraManager.this.camera != null) {
                    CameraManager.this.camera.release();
                    CameraManager.this.camera = null;
                }
                if (CameraManager.this.callbackBuffer != null) {
                    CameraManager.this.callbackBuffer = null;
                }
            }
        }
    }

    public static class Size implements Serializable {
        public int height;
        public int width;

        public Size(int w, int h) {
            this.width = w;
            this.height = h;
        }

        public Size copy() {
            return new Size(this.width, this.height);
        }
    }

    public class StopCameraPreviewRunnable implements Runnable {
        public StopCameraPreviewRunnable() {
        }

        public void run() {
            CameraManager.this.setFlash(false);
            synchronized (CameraManager.this.cameraAccessLock) {
                if (CameraManager.this.inPreview && CameraManager.this.camera != null) {
                    CameraManager.this.camera.stopPreview();
                }
            }
            CameraManager.this.inPreview = false;
        }
    }

    public class SurfaceChangedRunnable implements Runnable {
        int displayRotation;
        int height;
        boolean isPortrait;
        SurfaceTexture surface;
        int width;

        public SurfaceChangedRunnable(SurfaceTexture surface2, int width2, int height2, boolean isPortrait2, int displayRotation2) {
            this.surface = surface2;
            this.width = width2;
            this.height = height2;
            this.isPortrait = isPortrait2;
            this.displayRotation = displayRotation2;
        }

        public void run() {
            synchronized (CameraManager.this.cameraAccessLock) {
                if (CameraManager.this.camera != null) {
                    if (CameraManager.this.cameraCallback != null) {
                        CameraManager.this.cameraCallback.onStopPreview();
                    }
                    CameraManager.this.camera.stopPreview();
                    CameraManager.this.initPreview(this.surface, this.width, this.height, this.isPortrait, this.displayRotation);
                    if (!CameraManager.this.pausePreview) {
                        CameraManager.this.startPreview();
                    }
                }
            }
        }
    }

    public CameraManager(TextureView textureView2, ICameraCallback cameraCallback2, DeviceRotationManager rotationManager2) {
        this(textureView2, cameraCallback2, rotationManager2, false, false);
    }

    public CameraManager(TextureView textureView2, ICameraCallback cameraCallback2, DeviceRotationManager rotationManager2, boolean frontFacing2, boolean enableFlashOnStart) {
        this.flashCheckLock = new Object();
        this.cameraAccessLock = new Object();
        this.inPreview = false;
        this.pausePreview = false;
        this.frontFacing = false;
        this.flashOn = false;
        this.manualFocusEnabled = false;
        this.focusing = false;
        this.isFaceDetectionRunning = false;
        this.enableFlashOnStartUp = false;
        this.requestedSize = null;
        this.autoFocusCallback = new AutoFocusCallback() {
            public void onAutoFocus(boolean success, Camera camera) {
                CameraManager.this.focusing = false;
            }
        };
        this.textureView = textureView2;
        this.textureView.setSurfaceTextureListener(this);
        this.textureView.setOpaque(false);
        this.textureView.setOnClickListener(this);
        this.textureView.setOnTouchListener(this);
        setCameraFacing(frontFacing2);
        this.cameraCallback = cameraCallback2;
        this.rotationManager = rotationManager2;
        this.enableFlashOnStartUp = enableFlashOnStart;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public static boolean hasFrontFacingCamera(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    public static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    @Deprecated
    public static int getFrontCameraId() {
        int frontCameraId = -1;
        CameraInfo cameraInfo = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo != null && cameraInfo.facing == 1) {
                frontCameraId = i;
            }
        }
        return frontCameraId;
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (!this.pausePreview) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            switch (event.getAction()) {
                case 0:
                    requestFocus(x, y);
                    if (this.cameraCallback != null) {
                        this.cameraCallback.onManualRefocus(x, y);
                        break;
                    }
                    break;
            }
        }
        return false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.executorService.submit(new InitCameraRunnable(surface, width, height, this.rotationManager.isScreenPortrait(), this.rotationManager.getDisplayRotation()));
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        this.executorService.submit(new SurfaceChangedRunnable(this.textureView.getSurfaceTexture(), this.textureView.getWidth(), this.textureView.getHeight(), this.rotationManager.isScreenPortrait(), this.rotationManager.getDisplayRotation()));
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        stopPreview(this.pausePreview);
        destroy();
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public void setCameraFacing(boolean frontFacing2) {
        this.cameraId = 0;
        CameraInfo cameraInfo = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int id = 0;
        while (id < numberOfCameras) {
            Camera.getCameraInfo(id, cameraInfo);
            this.frontFacing = cameraInfo.facing == 1;
            if (frontFacing2 && cameraInfo.facing == 1) {
                this.cameraId = id;
                return;
            } else if (frontFacing2 || cameraInfo.facing != 0) {
                id++;
            } else {
                this.cameraId = id;
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isFlashSupported() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.cameraAccessLock
            monitor-enter(r3)
            android.hardware.Camera r2 = r4.camera     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x0028
            android.hardware.Camera r2 = r4.camera     // Catch:{ all -> 0x002b }
            android.hardware.Camera$Parameters r0 = r2.getParameters()     // Catch:{ all -> 0x002b }
            java.util.List r1 = r0.getSupportedFlashModes()     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0028
            java.lang.String r2 = "torch"
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x002b }
            if (r2 != 0) goto L_0x0025
            java.lang.String r2 = "on"
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x0028
        L_0x0025:
            r2 = 1
            monitor-exit(r3)     // Catch:{ all -> 0x002b }
        L_0x0027:
            return r2
        L_0x0028:
            monitor-exit(r3)     // Catch:{ all -> 0x002b }
            r2 = 0
            goto L_0x0027
        L_0x002b:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.camera.CameraManager.isFlashSupported():boolean");
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isFrontFacing() {
        return this.frontFacing;
    }

    public boolean isFlashOn() {
        return this.flashOn;
    }

    public boolean isFocusing() {
        return this.manualFocusEnabled && this.focusing;
    }

    public String getPreviewSize() {
        return String.format(Locale.GERMAN, "%dx%d", new Object[]{Integer.valueOf(this.previewSize.width), Integer.valueOf(this.previewSize.height)});
    }

    public boolean hasMultipleCameras() {
        return Camera.getNumberOfCameras() > 1;
    }

    public void toggleFlash() {
        setFlash(!this.flashOn);
    }

    public void requestFlashHint(boolean pulsate) {
        synchronized (this.flashCheckLock) {
            if (this.flashCheckThread != null) {
                this.flashCheckThread.pulsate(pulsate && !isFlashOn());
            }
        }
    }

    public void setFlashHint(ViewFader fader) {
        synchronized (this.flashCheckLock) {
            if (this.flashCheckThread == null) {
                this.flashCheckThread = new CameraFlashThread(fader);
                this.flashCheckThread.start();
            }
        }
    }

    public void cancelFlashHint() {
        synchronized (this.flashCheckLock) {
            if (this.flashCheckThread != null) {
                this.flashCheckThread.interrupt();
                this.flashCheckThread = null;
            }
        }
    }

    public void requestFocus() {
        requestFocus(this.textureView.getWidth() / 2, this.textureView.getHeight() / 2);
    }

    public void requestFocus(int x, int y) {
        int w = this.textureView.getWidth();
        int x2 = (int) ((2000.0f * (((float) x) / ((float) w))) - 1000.0f);
        int y2 = (int) ((2000.0f * (((float) y) / ((float) this.textureView.getHeight()))) - 1000.0f);
        synchronized (this.cameraAccessLock) {
            if (this.manualFocusEnabled && !this.focusing && this.camera != null) {
                this.focusing = true;
                this.camera.autoFocus(this.autoFocusCallback);
            } else if (!this.manualFocusEnabled && !this.focusing && this.camera != null) {
                Rect focusRect = new Rect(x2 - 50, y2 - 50, 50, 50);
                ArrayList<Area> list = new ArrayList<>();
                list.add(new Area(focusRect, 1));
                try {
                    if (this.camera.getParameters().getMaxNumFocusAreas() > 0) {
                        this.camera.getParameters().setFocusAreas(list);
                    }
                    if (this.camera.getParameters().getMaxNumMeteringAreas() > 0) {
                        this.camera.getParameters().setMeteringAreas(list);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setFaceDetectionListener(FaceDetectionListener faceDetectionListener) {
        if (this.camera != null) {
            this.camera.setFaceDetectionListener(faceDetectionListener);
        }
    }

    public void setRequestedSize(Size requestedSize2) {
        this.requestedSize = requestedSize2;
    }

    public boolean isFaceDetectionPossible() {
        if (this.camera != null && this.camera.getParameters().getMaxNumDetectedFaces() > 0) {
            return true;
        }
        return false;
    }

    public synchronized void startFaceDetection() {
        try {
            if (!this.isFaceDetectionRunning) {
                this.isFaceDetectionRunning = true;
                synchronized (this.cameraAccessLock) {
                    if (this.camera != null) {
                        this.camera.startFaceDetection();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public synchronized void stopFaceDetection() {
        synchronized (this.cameraAccessLock) {
            if (this.camera != null) {
                this.camera.stopFaceDetection();
            }
        }
        this.isFaceDetectionRunning = false;
    }

    public void setFlash(boolean turnFlashOn) {
        synchronized (this.cameraAccessLock) {
            if (this.camera != null && isFlashSupported()) {
                this.flashOn = turnFlashOn;
                Parameters params = this.camera.getParameters();
                List<String> supportedFlashModes = params.getSupportedFlashModes();
                if (supportedFlashModes != null && supportedFlashModes.contains("torch")) {
                    params.setFlashMode(turnFlashOn ? "torch" : "off");
                } else if (supportedFlashModes != null && supportedFlashModes.contains(ViewProps.f3131ON)) {
                    params.setFlashMode(turnFlashOn ? ViewProps.f3131ON : "off");
                }
                this.camera.setParameters(params);
            }
        }
    }

    public void getImageData(ImageData imageData) {
        imageData.setCameraPosition(isFrontFacing() ? CameraPosition.FRONT : CameraPosition.BACK);
        imageData.setOrientationMode(this.rotationManager.getScreenOrientation());
        imageData.setImageSize(this.previewProperties.preview);
        imageData.setFlashMode(isFlashOn());
    }

    public void startPreview() {
        synchronized (this.cameraAccessLock) {
            if (this.camera != null) {
                try {
                    this.camera.startPreview();
                } catch (Exception e) {
                }
                if (this.callbackBuffer == null) {
                    this.callbackBuffer = new byte[(((this.previewSize.width * this.previewSize.height) * ImageFormat.getBitsPerPixel(17)) / 8)];
                }
                this.camera.addCallbackBuffer(this.callbackBuffer);
                this.camera.setPreviewCallbackWithBuffer(this.cameraCallback);
                this.inPreview = true;
            }
            this.pausePreview = false;
        }
    }

    public void addCallbackBuffer() {
        synchronized (this.cameraAccessLock) {
            if (!(this.camera == null || this.callbackBuffer == null)) {
                this.camera.addCallbackBuffer(this.callbackBuffer);
            }
        }
    }

    public void stopPreview(boolean pause) {
        this.executorService.submit(new StopCameraPreviewRunnable());
        this.pausePreview = pause;
    }

    public boolean isPreviewRunning() {
        return !this.pausePreview;
    }

    public void destroy() {
        this.executorService.submit(new ReleaseCameraRunnable());
        cancelFlashHint();
    }

    public void changeCamera() {
        try {
            int numberOfCameras = Camera.getNumberOfCameras();
            if (numberOfCameras > 0) {
                this.cameraId++;
                if (this.cameraId >= numberOfCameras) {
                    this.cameraId = 0;
                }
                stopPreview(false);
                destroy();
                this.executorService.submit(new InitCameraRunnable(this.textureView.getSurfaceTexture(), this.textureView.getWidth(), this.textureView.getHeight(), this.rotationManager.isScreenPortrait(), this.rotationManager.getDisplayRotation()));
            }
        } catch (Exception e) {
        }
    }

    public void reinitCamera() {
        this.executorService.submit(new SurfaceChangedRunnable(this.textureView.getSurfaceTexture(), this.textureView.getWidth(), this.textureView.getHeight(), this.rotationManager.isScreenPortrait(), this.rotationManager.getDisplayRotation()));
    }

    private Camera getCameraWithId(int cameraId2) {
        boolean z = true;
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras <= 0) {
            cameraId2 = 0;
        }
        if (cameraId2 >= numberOfCameras) {
            cameraId2 = 0;
        }
        this.cameraId = cameraId2;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(cameraId2, cameraInfo);
        if (cameraInfo.facing != 1) {
            z = false;
        }
        this.frontFacing = z;
        this.orientation = cameraInfo.orientation;
        Camera camera2 = null;
        long start = System.currentTimeMillis();
        Throwable tt = null;
        while (camera2 == null && System.currentTimeMillis() - start <= 2000) {
            try {
                camera2 = Camera.open(cameraId2);
            } catch (Throwable t) {
                camera2 = null;
                tt = t;
            }
        }
        if (camera2 == null && tt != null) {
            this.cameraCallback.onCameraError(tt);
        }
        return camera2;
    }

    private android.hardware.Camera.Size getBestPreviewSize(Parameters parameters) {
        android.hardware.Camera.Size result = (android.hardware.Camera.Size) parameters.getSupportedPreviewSizes().get(0);
        for (android.hardware.Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width >= result.width && size.height >= result.height) {
                result = size;
            }
        }
        return result;
    }

    private android.hardware.Camera.Size getRequestedPreviewSize(Parameters parameters) {
        android.hardware.Camera.Size result = (android.hardware.Camera.Size) parameters.getSupportedPreviewSizes().get(0);
        for (android.hardware.Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width >= this.requestedSize.width && size.height >= this.requestedSize.height) {
                result = size;
            }
        }
        return result;
    }

    /* access modifiers changed from: private */
    public void initCamera() {
        synchronized (this.cameraAccessLock) {
            this.camera = getCameraWithId(this.cameraId);
        }
        final boolean isFlashSupported = isFlashSupported();
        if (isFlashSupported && this.enableFlashOnStartUp) {
            setFlash(true);
        }
        if (this.cameraCallback != null && this.camera != null) {
            this.textureView.post(new Runnable() {
                public void run() {
                    CameraManager.this.cameraCallback.onCameraAvailable(isFlashSupported);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r6 = r21.camera.getParameters();
        r16 = java.util.Arrays.asList(FALLBACK_AUTO_FOCUS_LIST).contains(android.os.Build.MODEL);
        r14 = r6.getSupportedFocusModes();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        if (r14 == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        if (r16 != false) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        if (r14.contains("continuous-picture") == false) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        r6.setFocusMode("continuous-picture");
        r21.manualFocusEnabled = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (r21.requestedSize != null) goto L_0x02a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        r17 = getBestPreviewSize(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r21.previewSize = r17;
        r21.previewProperties = new com.jumio.commons.camera.CameraManager.PreviewProperties();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007b, code lost:
        if (r23 <= r24) goto L_0x02ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007d, code lost:
        r8 = ((float) r23) / ((float) r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0089, code lost:
        r13 = 0;
        r12 = 0;
        r10 = 1.0f;
        r11 = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6.set("metering", "center");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x024c, code lost:
        if (r16 != false) goto L_0x026b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0257, code lost:
        if (r14.contains("continuous-video") == false) goto L_0x026b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0259, code lost:
        r6.setFocusMode("continuous-video");
        r21.manualFocusEnabled = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0274, code lost:
        if (r14.contains("auto") == false) goto L_0x0288;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0276, code lost:
        r6.setFocusMode("auto");
        r21.manualFocusEnabled = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0291, code lost:
        if (r14.contains("macro") == false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0293, code lost:
        r6.setFocusMode("macro");
        r21.manualFocusEnabled = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x02a5, code lost:
        r17 = getRequestedPreviewSize(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x02ad, code lost:
        r8 = ((float) r24) / ((float) r23);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPreview(android.graphics.SurfaceTexture r22, int r23, int r24, boolean r25, int r26) {
        /*
            r21 = this;
            r0 = r21
            java.lang.Object r0 = r0.cameraAccessLock
            r18 = r0
            monitor-enter(r18)
            r0 = r21
            android.hardware.Camera r0 = r0.camera     // Catch:{ all -> 0x0249 }
            r17 = r0
            if (r17 != 0) goto L_0x0011
            monitor-exit(r18)     // Catch:{ all -> 0x0249 }
        L_0x0010:
            return
        L_0x0011:
            r0 = r21
            android.hardware.Camera r0 = r0.camera     // Catch:{ Throwable -> 0x0239 }
            r17 = r0
            r0 = r17
            r1 = r22
            r0.setPreviewTexture(r1)     // Catch:{ Throwable -> 0x0239 }
        L_0x001e:
            monitor-exit(r18)     // Catch:{ all -> 0x0249 }
            r0 = r21
            android.hardware.Camera r0 = r0.camera
            r17 = r0
            android.hardware.Camera$Parameters r6 = r17.getParameters()
            java.lang.String[] r17 = FALLBACK_AUTO_FOCUS_LIST
            java.util.List r17 = java.util.Arrays.asList(r17)
            java.lang.String r18 = android.os.Build.MODEL
            boolean r16 = r17.contains(r18)
            java.util.List r14 = r6.getSupportedFocusModes()
            if (r14 == 0) goto L_0x0058
            if (r16 != 0) goto L_0x024c
            java.lang.String r17 = "continuous-picture"
            r0 = r17
            boolean r17 = r14.contains(r0)
            if (r17 == 0) goto L_0x024c
            java.lang.String r17 = "continuous-picture"
            r0 = r17
            r6.setFocusMode(r0)
            r17 = 0
            r0 = r17
            r1 = r21
            r1.manualFocusEnabled = r0
        L_0x0058:
            r0 = r21
            com.jumio.commons.camera.CameraManager$Size r0 = r0.requestedSize
            r17 = r0
            if (r17 != 0) goto L_0x02a5
            r0 = r21
            android.hardware.Camera$Size r17 = r0.getBestPreviewSize(r6)
        L_0x0066:
            r0 = r17
            r1 = r21
            r1.previewSize = r0
            com.jumio.commons.camera.CameraManager$PreviewProperties r17 = new com.jumio.commons.camera.CameraManager$PreviewProperties
            r17.<init>()
            r0 = r17
            r1 = r21
            r1.previewProperties = r0
            r0 = r23
            r1 = r24
            if (r0 <= r1) goto L_0x02ad
            r0 = r23
            float r0 = (float) r0
            r17 = r0
            r0 = r24
            float r0 = (float) r0
            r18 = r0
            float r8 = r17 / r18
        L_0x0089:
            r13 = 0
            r12 = 0
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r17 = "metering"
            java.lang.String r18 = "center"
            r0 = r17
            r1 = r18
            r6.set(r0, r1)     // Catch:{ Exception -> 0x03ea }
        L_0x009c:
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            if (r17 == 0) goto L_0x01a4
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            r0 = r17
            int r0 = r0.width
            r17 = r0
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.height
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            float r7 = r17 / r18
            if (r25 == 0) goto L_0x02fd
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            r0 = r17
            int r0 = r0.width
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.height
            r18 = r0
            r0 = r17
            r1 = r18
            r6.setPreviewSize(r0, r1)
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            com.jumio.commons.camera.CameraManager$Size r18 = new com.jumio.commons.camera.CameraManager$Size
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r19 = r0
            r0 = r19
            int r0 = r0.height
            r19 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r20 = r0
            r0 = r20
            int r0 = r0.width
            r20 = r0
            r18.<init>(r19, r20)
            r0 = r18
            r1 = r17
            r1.preview = r0
            int r17 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r17 < 0) goto L_0x02bb
            r13 = r23
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            r0 = r17
            int r0 = r0.width
            r17 = r0
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.height
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 / r18
            r0 = r23
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 * r18
            r0 = r17
            int r12 = (int) r0
            r10 = 1065353216(0x3f800000, float:1.0)
            float r0 = (float) r12
            r17 = r0
            r0 = r24
            float r0 = (float) r0
            r18 = r0
            float r11 = r17 / r18
        L_0x0152:
            android.hardware.Camera$CameraInfo r4 = new android.hardware.Camera$CameraInfo
            r4.<init>()
            r0 = r21
            int r0 = r0.cameraId
            r17 = r0
            r0 = r17
            android.hardware.Camera.getCameraInfo(r0, r4)
            r3 = 0
            switch(r26) {
                case 0: goto L_0x03c9;
                case 1: goto L_0x03cc;
                case 2: goto L_0x03d0;
                case 3: goto L_0x03d4;
                default: goto L_0x0166;
            }
        L_0x0166:
            int r0 = r4.facing
            r17 = r0
            r18 = 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x03d8
            int r0 = r4.orientation
            r17 = r0
            int r17 = r17 + r3
            r0 = r17
            int r9 = r0 % 360
            int r0 = 360 - r9
            r17 = r0
            r0 = r17
            int r9 = r0 % 360
        L_0x0184:
            r0 = r21
            android.hardware.Camera r0 = r0.camera
            r17 = r0
            r0 = r17
            r0.setDisplayOrientation(r9)
            r0 = r21
            android.hardware.Camera r0 = r0.camera
            r17 = r0
            r0 = r17
            r0.setParameters(r6)
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            r0 = r17
            r0.orientation = r9
        L_0x01a4:
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            com.jumio.commons.camera.CameraManager$Size r18 = new com.jumio.commons.camera.CameraManager$Size
            r0 = r18
            r0.<init>(r13, r12)
            r0 = r18
            r1 = r17
            r1.scaledPreview = r0
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            com.jumio.commons.camera.CameraManager$Size r18 = new com.jumio.commons.camera.CameraManager$Size
            r0 = r18
            r1 = r23
            r2 = r24
            r0.<init>(r1, r2)
            r0 = r18
            r1 = r17
            r1.surface = r0
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            r0 = r21
            boolean r0 = r0.frontFacing
            r18 = r0
            r0 = r18
            r1 = r17
            r1.frontFacing = r0
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            int r18 = r6.getPreviewFormat()
            r0 = r18
            r1 = r17
            r1.previewFormat = r0
            r0 = r21
            com.jumio.commons.camera.ICameraCallback r0 = r0.cameraCallback
            r17 = r0
            if (r17 == 0) goto L_0x0207
            r0 = r21
            com.jumio.commons.camera.ICameraCallback r0 = r0.cameraCallback
            r17 = r0
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r18 = r0
            r17.onPreviewAvailable(r18)
        L_0x0207:
            android.graphics.Matrix r5 = new android.graphics.Matrix
            r5.<init>()
            r0 = r23
            float r0 = (float) r0
            r17 = r0
            r18 = 1073741824(0x40000000, float:2.0)
            float r17 = r17 / r18
            r0 = r24
            float r0 = (float) r0
            r18 = r0
            r19 = 1073741824(0x40000000, float:2.0)
            float r18 = r18 / r19
            r0 = r17
            r1 = r18
            r5.setScale(r10, r11, r0, r1)
            r0 = r21
            android.view.TextureView r0 = r0.textureView
            r17 = r0
            com.jumio.commons.camera.CameraManager$3 r18 = new com.jumio.commons.camera.CameraManager$3
            r0 = r18
            r1 = r21
            r0.<init>(r5)
            r17.post(r18)
            goto L_0x0010
        L_0x0239:
            r15 = move-exception
            java.lang.String r17 = "CameraManager"
            java.lang.String r19 = "Exception in setPreviewTexture()"
            r0 = r17
            r1 = r19
            com.jumio.commons.log.Log.m1915e(r0, r1, r15)     // Catch:{ all -> 0x0249 }
            goto L_0x001e
        L_0x0249:
            r17 = move-exception
            monitor-exit(r18)     // Catch:{ all -> 0x0249 }
            throw r17
        L_0x024c:
            if (r16 != 0) goto L_0x026b
            java.lang.String r17 = "continuous-video"
            r0 = r17
            boolean r17 = r14.contains(r0)
            if (r17 == 0) goto L_0x026b
            java.lang.String r17 = "continuous-video"
            r0 = r17
            r6.setFocusMode(r0)
            r17 = 0
            r0 = r17
            r1 = r21
            r1.manualFocusEnabled = r0
            goto L_0x0058
        L_0x026b:
            java.lang.String r17 = "auto"
            r0 = r17
            boolean r17 = r14.contains(r0)
            if (r17 == 0) goto L_0x0288
            java.lang.String r17 = "auto"
            r0 = r17
            r6.setFocusMode(r0)
            r17 = 1
            r0 = r17
            r1 = r21
            r1.manualFocusEnabled = r0
            goto L_0x0058
        L_0x0288:
            java.lang.String r17 = "macro"
            r0 = r17
            boolean r17 = r14.contains(r0)
            if (r17 == 0) goto L_0x0058
            java.lang.String r17 = "macro"
            r0 = r17
            r6.setFocusMode(r0)
            r17 = 1
            r0 = r17
            r1 = r21
            r1.manualFocusEnabled = r0
            goto L_0x0058
        L_0x02a5:
            r0 = r21
            android.hardware.Camera$Size r17 = r0.getRequestedPreviewSize(r6)
            goto L_0x0066
        L_0x02ad:
            r0 = r24
            float r0 = (float) r0
            r17 = r0
            r0 = r23
            float r0 = (float) r0
            r18 = r0
            float r8 = r17 / r18
            goto L_0x0089
        L_0x02bb:
            int r17 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r17 >= 0) goto L_0x0152
            r0 = r24
            float r0 = (float) r0
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.width
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r19 = r0
            r0 = r19
            int r0 = r0.height
            r19 = r0
            r0 = r19
            float r0 = (float) r0
            r19 = r0
            float r18 = r18 / r19
            float r17 = r17 / r18
            r0 = r17
            int r13 = (int) r0
            r12 = r24
            float r0 = (float) r13
            r17 = r0
            r0 = r23
            float r0 = (float) r0
            r18 = r0
            float r10 = r17 / r18
            r11 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0152
        L_0x02fd:
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            r0 = r17
            int r0 = r0.width
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.height
            r18 = r0
            r0 = r17
            r1 = r18
            r6.setPreviewSize(r0, r1)
            r0 = r21
            com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.previewProperties
            r17 = r0
            com.jumio.commons.camera.CameraManager$Size r18 = new com.jumio.commons.camera.CameraManager$Size
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r19 = r0
            r0 = r19
            int r0 = r0.width
            r19 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r20 = r0
            r0 = r20
            int r0 = r0.height
            r20 = r0
            r18.<init>(r19, r20)
            r0 = r18
            r1 = r17
            r1.preview = r0
            int r17 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r17 > 0) goto L_0x0387
            r13 = r23
            r0 = r23
            float r0 = (float) r0
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.width
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r19 = r0
            r0 = r19
            int r0 = r0.height
            r19 = r0
            r0 = r19
            float r0 = (float) r0
            r19 = r0
            float r18 = r18 / r19
            float r17 = r17 / r18
            r0 = r17
            int r12 = (int) r0
            r10 = 1065353216(0x3f800000, float:1.0)
            float r0 = (float) r12
            r17 = r0
            r0 = r24
            float r0 = (float) r0
            r18 = r0
            float r11 = r17 / r18
            goto L_0x0152
        L_0x0387:
            int r17 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r17 <= 0) goto L_0x0152
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r17 = r0
            r0 = r17
            int r0 = r0.width
            r17 = r0
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r0 = r21
            android.hardware.Camera$Size r0 = r0.previewSize
            r18 = r0
            r0 = r18
            int r0 = r0.height
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 / r18
            r0 = r24
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 * r18
            r0 = r17
            int r13 = (int) r0
            r12 = r24
            float r0 = (float) r13
            r17 = r0
            r0 = r23
            float r0 = (float) r0
            r18 = r0
            float r10 = r17 / r18
            r11 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0152
        L_0x03c9:
            r3 = 0
            goto L_0x0166
        L_0x03cc:
            r3 = 90
            goto L_0x0166
        L_0x03d0:
            r3 = 180(0xb4, float:2.52E-43)
            goto L_0x0166
        L_0x03d4:
            r3 = 270(0x10e, float:3.78E-43)
            goto L_0x0166
        L_0x03d8:
            int r0 = r4.orientation
            r17 = r0
            int r17 = r17 - r3
            r0 = r17
            int r0 = r0 + 360
            r17 = r0
            r0 = r17
            int r9 = r0 % 360
            goto L_0x0184
        L_0x03ea:
            r17 = move-exception
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.camera.CameraManager.initPreview(android.graphics.SurfaceTexture, int, int, boolean, int):void");
    }

    public void onClick(View v) {
        requestFocus();
    }
}
