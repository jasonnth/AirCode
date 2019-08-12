package com.jumio.sdk.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.OrientationEventListener;
import android.view.TextureView;
import android.view.ViewGroup;
import com.jumio.commons.camera.CameraManager;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.ICameraCallback;
import com.jumio.commons.enums.ScreenAngle;
import com.jumio.commons.utils.DeviceRotationManager;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.exceptions.MissingPluginException;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.SubscriberWithUpdate;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.gui.DrawView.DrawViewInterface;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.models.BaseScanPartModel;
import com.jumio.sdk.view.interactors.BaseScanView;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseScanPresenter<Interactor extends BaseScanView, Update, Result> extends Presenter<Interactor> implements ICameraCallback, SubscriberWithUpdate<Update, Result>, DrawViewInterface {
    private static final String TAG = "BaseScanPresenter";
    protected static final int WEBP_QUALITY = 75;
    protected CameraManager cameraManager;
    /* access modifiers changed from: protected */
    public ExtractionClient mExtractionClient;
    protected OrientationHandler mOrientationListener;
    /* access modifiers changed from: protected */
    public Overlay mOverlay;
    protected PreviewProperties mPreviewProperties;
    protected Plugin plugin;
    protected int viewHeight;
    protected int viewWidth;

    public static class BaseScanControl {
        public static int flashOnStartupEnabled = f3554id.getAndIncrement();
        public static int hasFlash = f3554id.getAndIncrement();
        public static int hasMultipleCameras = f3554id.getAndIncrement();

        /* renamed from: id */
        protected static AtomicInteger f3554id = new AtomicInteger(0);
        public static int isCameraFrontfacing = f3554id.getAndIncrement();
        public static int isFlashOn = f3554id.getAndIncrement();
        public static int startCameraFrontfacing = f3554id.getAndIncrement();
        public static int toggleCamera = f3554id.getAndIncrement();
        public static int toggleFlash = f3554id.getAndIncrement();
    }

    protected class OrientationHandler extends OrientationEventListener {
        public OrientationHandler(Context context) {
            super(context);
        }

        public OrientationHandler(Context context, int rate) {
            super(context, rate);
        }

        public void onOrientationChanged(int orientation) {
            if (orientation != -1 && BaseScanPresenter.this.isActive()) {
                DeviceRotationManager rotationManager = ((BaseScanView) BaseScanPresenter.this.getView()).getRotationManager();
                ScreenAngle oldAngle = rotationManager.getAngle();
                rotationManager.setAngleFromScreen();
                ScreenAngle newAngle = rotationManager.getAngle();
                if (!newAngle.equals(oldAngle)) {
                    BaseScanPresenter.this.onScreenAngleChanged(newAngle);
                }
                if (BaseScanPresenter.this.cameraManager != null && BaseScanPresenter.this.cameraManager.isPreviewRunning() && rotationManager.isSensorOrientation()) {
                    if ((oldAngle == ScreenAngle.LANDSCAPE && newAngle == ScreenAngle.INVERTED_LANDSCAPE) || ((oldAngle == ScreenAngle.INVERTED_LANDSCAPE && newAngle == ScreenAngle.LANDSCAPE) || ((rotationManager.isTablet() && oldAngle == ScreenAngle.PORTRAIT && newAngle == ScreenAngle.INVERTED_PORTRAIT) || (rotationManager.isTablet() && oldAngle == ScreenAngle.INVERTED_PORTRAIT && newAngle == ScreenAngle.PORTRAIT)))) {
                        BaseScanPresenter.this.cameraManager.reinitCamera();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean canSwitchCamera();

    /* access modifiers changed from: protected */
    public abstract boolean enableFlashOnStart();

    /* access modifiers changed from: protected */
    public abstract BaseScanPartModel getScanPartModel();

    /* access modifiers changed from: protected */
    public abstract boolean startCameraFrontfacing();

    public void onCameraAvailable(boolean b) {
        if (isActive()) {
            ((BaseScanView) getView()).resetCameraControls(this.cameraManager.isFrontFacing(), this.cameraManager.isFlashOn());
        }
    }

    public void onPreviewAvailable(PreviewProperties previewProperties) {
        if (isActive()) {
            this.mPreviewProperties = previewProperties;
            DataAccess.update(((BaseScanView) getView()).getContext(), PreviewProperties.class, this.mPreviewProperties);
            ((BaseScanView) getView()).getRotationManager().setAngleFromScreen();
            if (this.mOverlay != null) {
                this.mOverlay.prepareDraw(getScanPartModel().getSideToScan(), this.cameraManager.isFrontFacing(), ((BaseScanView) getView()).getRotationManager().isPortrait() || ((BaseScanView) getView()).getRotationManager().isTablet());
                ((BaseScanView) getView()).invalidateDrawView();
            }
            this.mExtractionClient.cancel();
            this.mExtractionClient.setPreviewProperties(previewProperties);
            this.mExtractionClient.setFlags(((BaseScanView) getView()).getRotationManager().isPortrait(), ((BaseScanView) getView()).getRotationManager().isTablet(), ((BaseScanView) getView()).getRotationManager().isInverted());
            this.mExtractionClient.reinit();
            ((BaseScanView) getView()).updateCameraControls(canSwitchCamera(), this.cameraManager.isFlashSupported());
        }
    }

    public void onManualRefocus(int x, int y) {
        if (this.cameraManager != null) {
            this.cameraManager.requestFocus(x, y);
        }
    }

    public void onStopPreview() {
        this.mPreviewProperties = null;
        DataAccess.delete(((BaseScanView) getView()).getContext(), PreviewProperties.class);
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        if (!this.cameraManager.isFocusing() && this.mExtractionClient != null) {
            this.mExtractionClient.process(data);
        }
        this.cameraManager.addCallbackBuffer();
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        TextureView textureView = ((BaseScanView) getView()).getTextureView();
        DocumentScanMode scanMode = getScanPartModel().getScanMode();
        this.plugin = PluginRegistry.getPlugin(scanMode);
        if (this.plugin == null) {
            onError(new MissingPluginException(scanMode));
            return;
        }
        if (this.mOverlay == null) {
            this.mOverlay = this.plugin.getOverlay(((BaseScanView) getView()).getContext(), null);
        }
        if (scanMode == DocumentScanMode.NFC) {
            if (this.cameraManager != null) {
                this.cameraManager.stopPreview(true);
            }
            textureView.setVisibility(4);
        } else {
            textureView.setVisibility(0);
            if (this.cameraManager == null) {
                this.cameraManager = new CameraManager(textureView, this, ((BaseScanView) getView()).getRotationManager(), startCameraFrontfacing(), enableFlashOnStart());
            } else {
                this.cameraManager.reinitCamera();
            }
        }
        this.mOrientationListener = new OrientationHandler<>(((BaseScanView) getView()).getContext(), 1);
        this.mOrientationListener.enable();
        this.mExtractionClient = this.plugin.getExtractionClient(((BaseScanView) getView()).getContext());
        this.mExtractionClient.subscribe(this);
        this.mExtractionClient.configure(getScanPartModel());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (this.cameraManager != null) {
            this.cameraManager.startPreview();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.cameraManager != null) {
            this.cameraManager.stopPreview(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mExtractionClient != null) {
            this.mExtractionClient.unsubscribe(this);
            this.mExtractionClient.destroy();
        }
        if (this.mOrientationListener != null) {
            this.mOrientationListener.disable();
            this.mOrientationListener = null;
        }
        if (this.cameraManager != null) {
            this.cameraManager.stopPreview(false);
            this.cameraManager.destroy();
        }
    }

    @InvokeOnUiThread
    public void onError(Throwable error) {
        if (isActive()) {
            if (this.mExtractionClient != null) {
                this.mExtractionClient.cancel();
            }
            ((BaseScanView) getView()).onError(error);
        }
    }

    public void draw(Canvas canvas) {
        if (this.mOverlay != null) {
            this.mOverlay.doDraw(canvas);
        }
    }

    public void addChildren(ViewGroup rootview) {
        if (this.mOverlay != null) {
            this.mOverlay.addViews(rootview);
        }
    }

    public Rect getOverlayBounds() {
        if (this.mOverlay != null) {
            return this.mOverlay.getOverlayBounds();
        }
        return null;
    }

    public void measure(int w, int h) {
        boolean z;
        boolean z2 = false;
        this.viewWidth = w;
        this.viewHeight = h;
        if (this.mOverlay != null) {
            this.mOverlay.calculate(getScanPartModel().getScanMode(), w, h);
            Overlay overlay = this.mOverlay;
            ScanSide sideToScan = getScanPartModel().getSideToScan();
            if (this.cameraManager == null || !this.cameraManager.isFrontFacing()) {
                z = false;
            } else {
                z = true;
            }
            if (((BaseScanView) getView()).getRotationManager().isPortrait() || ((BaseScanView) getView()).getRotationManager().isTablet()) {
                z2 = true;
            }
            overlay.prepareDraw(sideToScan, z, z2);
        }
    }

    public boolean control(int control) {
        if (control == BaseScanControl.toggleCamera) {
            this.cameraManager.changeCamera();
            return true;
        } else if (control == BaseScanControl.toggleFlash) {
            this.cameraManager.toggleFlash();
            return true;
        } else if (control == BaseScanControl.hasMultipleCameras) {
            return canSwitchCamera();
        } else {
            if (control == BaseScanControl.hasFlash) {
                return this.cameraManager.isFlashSupported();
            }
            if (control == BaseScanControl.isCameraFrontfacing) {
                return this.cameraManager.isFrontFacing();
            }
            if (control == BaseScanControl.isFlashOn) {
                return this.cameraManager.isFlashOn();
            }
            if (control == BaseScanControl.flashOnStartupEnabled) {
                return enableFlashOnStart();
            }
            if (control == BaseScanControl.startCameraFrontfacing) {
                return startCameraFrontfacing();
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onScreenAngleChanged(ScreenAngle screenAngle) {
    }
}
