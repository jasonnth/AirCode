package com.miteksystems.facialcapture.science.analyzer;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.daon.sdk.face.DaonFace;
import com.daon.sdk.face.DaonFace.AnalysisCallback;
import com.daon.sdk.face.LivenessResult;
import com.daon.sdk.face.QualityResult;
import com.daon.sdk.face.Result;
import com.daon.sdk.face.YUV;
import com.miteksystems.facialcapture.science.api.events.OnFacialCaptureProcessedEvent;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureParamMgr;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse.ExtraInfo;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.params.ParameterManager;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import p314de.greenrobot.event.EventBus;

public class FacialCaptureAnalyzer implements AnalysisCallback, IAnalyzer {
    private static final int EXPOSURE_THRESHOLD = 200;
    private static final int EYES_FOUND_CONFIDENCE = 500;
    private static final int EYES_OPEN_CONFIDENCE = 700;
    private static final int EYES_OPEN_LIVENESS_MAX_THRESHOLD = 50;
    private static final int EYES_OPEN_MIN_THRESHOLD = 833;
    private static final int FACE_FRONTAL_THRESHOLD = 500;
    private static final int GRAYSCALE_DENSITY_THRESHOLD = 250;
    private static final int OVERALL_QUALITY_THRESHOLD = 650;
    private static final String TAG = FacialCaptureAnalyzer.class.getSimpleName();
    protected Context mAppContext;
    protected WeakReference<IAnalyzeResponse> mCallback;
    private boolean mCaptureEyesOpen;
    protected DaonFace mDaonFace;
    protected volatile Future mDaonThread;
    private int mEyeMaxDistanceApart;
    private int mEyeMinDistanceApart;
    protected byte[] mEyesOpenPreviewYuv;
    protected FacialCaptureUxp mFacialCaptureUxp;
    protected volatile boolean mIsInitialized;
    private int mLightingMinThreshold;
    private int mLivenessThreshold;
    private int mNumFrames;
    private int mNumFramesToIgnore;
    protected ParameterManager mParamMgr;
    protected int mPreviewHeight;
    protected int mPreviewWidth;
    protected int mScreenHeight;
    protected int mScreenWidth;
    private int mSharpnessMinThreshold;
    private int mThresholdBlink;
    protected FacialCaptureParamMgr mThresholds;
    private Object mYuvLock = new Object();
    protected ExecutorService threadPoolExecutor;

    public FacialCaptureAnalyzer(Context activityContext, ParameterManager paramMgr, FacialCaptureParamMgr thresholds) {
        this.mAppContext = activityContext.getApplicationContext();
        this.mParamMgr = paramMgr;
        this.mThresholds = thresholds;
        WindowManager wm = (WindowManager) activityContext.getSystemService("window");
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        this.mScreenWidth = dm.widthPixels;
        this.mScreenHeight = dm.heightPixels;
    }

    public boolean init() {
        this.mIsInitialized = false;
        this.threadPoolExecutor = Executors.newSingleThreadExecutor();
        createEngine();
        createUxpTracker();
        if (this.mDaonFace == null) {
            return false;
        }
        loadParameters();
        float daonBlinkThreshold = ((float) this.mThresholdBlink) / 1000.0f;
        Bundle config = new Bundle();
        config.putFloat("blink.settings.threshold", daonBlinkThreshold);
        this.mDaonFace.setConfiguration(config);
        this.mNumFrames = 0;
        this.mIsInitialized = true;
        return true;
    }

    public void setEngineForTesting(DaonFace engine) {
        this.mDaonFace = engine;
    }

    public void setFacialCaptureUxpForTesting(FacialCaptureUxp uxp) {
        this.mFacialCaptureUxp = uxp;
    }

    /* access modifiers changed from: protected */
    public DaonFace createEngine() {
        if (this.mDaonFace == null) {
            this.mDaonFace = new DaonFace(this.mAppContext, DaonFace.OPTION_LIVENESS_BLINK | DaonFace.OPTION_QUALITY | DaonFace.OPTION_DEVICE_POSITION);
        }
        return this.mDaonFace;
    }

    /* access modifiers changed from: protected */
    public FacialCaptureUxp createUxpTracker() {
        if (this.mFacialCaptureUxp == null) {
            this.mFacialCaptureUxp = new FacialCaptureUxp(this.mAppContext);
        }
        return this.mFacialCaptureUxp;
    }

    public void setNumFramesToIgnore(int numFrames) {
        this.mNumFramesToIgnore = numFrames;
    }

    private void loadParameters() {
        this.mThresholdBlink = this.mThresholds.getBlinkThreshold();
        this.mEyeMinDistanceApart = this.mThresholds.getEyeMinDistance();
        this.mEyeMaxDistanceApart = this.mThresholds.getEyeMaxDistance();
        this.mLivenessThreshold = this.mThresholds.getLivenessThreshold();
        this.mLightingMinThreshold = this.mThresholds.getLightingMinThreshold();
        this.mSharpnessMinThreshold = this.mThresholds.getSharpnessMinThreshold();
        this.mCaptureEyesOpen = this.mThresholds.getCaptureEyesOpen() != 0;
    }

    public void deinit() {
        this.mIsInitialized = false;
        if (this.threadPoolExecutor != null) {
            this.threadPoolExecutor.shutdownNow();
            this.threadPoolExecutor = null;
        }
        if (this.mDaonFace != null) {
            this.mDaonFace.stop();
        }
        synchronized (this.mYuvLock) {
            this.mEyesOpenPreviewYuv = null;
        }
    }

    public void analyze(IAnalyzeResponse callbackToCall, final byte[] imageBytes, int previewWidth, int previewHeight, int previewImageType) {
        if (!this.mIsInitialized) {
            callbackToCall.onAnalyzeFail(2, null);
        } else if (this.mDaonThread == null || this.mDaonThread.isDone()) {
            this.mCallback = new WeakReference<>(callbackToCall);
            if (!(this.mPreviewWidth == previewWidth && this.mPreviewHeight == previewHeight)) {
                this.mDaonFace.setImageSize(previewWidth, previewHeight);
                this.mPreviewWidth = previewWidth;
                this.mPreviewHeight = previewHeight;
            }
            this.mDaonThread = this.threadPoolExecutor.submit(new Runnable() {
                public void run() {
                    if (FacialCaptureAnalyzer.this.mIsInitialized) {
                        FacialCaptureAnalyzer.this.mDaonFace.analyze(imageBytes, FacialCaptureAnalyzer.this);
                    }
                }
            });
        } else {
            callbackToCall.onAnalyzeFail(3, null);
        }
    }

    public void onAnalysisResult(YUV yuv, Result result) {
        if (this.mCallback.get() == null) {
            this.mDaonThread.cancel(true);
            return;
        }
        int resultCode = screenCallbackResults(yuv);
        if (resultCode != 0) {
            ((IAnalyzeResponse) this.mCallback.get()).onAnalyzeFail(resultCode, null);
            this.mDaonThread.cancel(true);
            return;
        }
        boolean deviceUpright = result.isDeviceUpright();
        boolean faceFound = result.isFaceFound();
        LivenessResult lr = result.getLivenessResult();
        boolean blinkDetected = lr.isBlink();
        int livenessScore = (int) (1000.0f * lr.getScore());
        boolean livenessDetected = livenessScore >= this.mLivenessThreshold;
        QualityResult qr = result.getQualityResult();
        int faceFrontalScore = (int) (1000.0f * qr.getFaceFrontalConfidence());
        if (faceFrontalScore >= 500) {
        }
        int eyesFoundScore = (int) (1000.0f * qr.getEyesFoundConfidence());
        if (eyesFoundScore >= 500) {
        }
        int eyesOpenScore = (int) (1000.0f * qr.getEyesOpenConfidence());
        if (eyesOpenScore >= 700) {
        }
        int uniformLightingScore = (int) (1000.0f * qr.getUniformLightingConfidence());
        boolean uniformLighting = uniformLightingScore >= this.mLightingMinThreshold;
        int eyeDistanceApart = (qr.getEyeDistance() * 1000) / this.mPreviewHeight;
        boolean faceTooClose = eyeDistanceApart > this.mEyeMaxDistanceApart;
        boolean faceTooFarAway = eyeDistanceApart < this.mEyeMinDistanceApart;
        boolean eyeDistanceGood = eyeDistanceApart > 0 && !faceTooFarAway && !faceTooClose;
        int sharpnessScore = qr.getSharpness() * 10;
        boolean sharpnessGood = sharpnessScore >= this.mSharpnessMinThreshold;
        int exposureScore = qr.getExposure() * 10;
        if (exposureScore >= 200) {
        }
        int grayscaleDensityScore = (qr.getGrayscaleDensity() * 1000) / 255;
        if (grayscaleDensityScore >= 250) {
        }
        int qualityScore = (int) (1000.0f * qr.getGlobalScore());
        boolean qualityGood = qualityScore >= 650;
        Rect facePosition = result.getRecognitionResult().getFacePosition();
        facePosition.set(this.mScreenWidth - ((facePosition.bottom * this.mScreenHeight) / this.mScreenWidth), this.mScreenHeight - ((facePosition.right * this.mScreenHeight) / this.mScreenWidth), this.mScreenWidth - ((facePosition.top * this.mScreenHeight) / this.mScreenWidth), this.mScreenHeight - ((facePosition.left * this.mScreenHeight) / this.mScreenWidth));
        Log.d(TAG, "Upright = " + deviceUpright + ", pose angle = " + result.getQualityResult().getPoseAngle() + ", face found = " + faceFound + ", blink = " + blinkDetected + ", frontal = " + faceFrontalScore + ", eyes found = " + eyesFoundScore + ", eyes open = " + eyesOpenScore + ", liveness = " + livenessScore + ", uniform lighting = " + uniformLightingScore + ", eye distance = " + eyeDistanceApart + ", sharpness = " + sharpnessScore + ", exposure = " + exposureScore + ", grayscale = " + grayscaleDensityScore + ", quality = " + qualityScore + ", face pos = (" + facePosition.top + "," + facePosition.left + ")-(" + facePosition.bottom + "," + facePosition.right + ")");
        OnFacialCaptureProcessedEvent event = new OnFacialCaptureProcessedEvent(deviceUpright, faceFound, blinkDetected, livenessDetected, uniformLighting, eyeDistanceGood, faceTooFarAway, faceTooClose, sharpnessGood, qualityGood);
        EventBus.getDefault().post(event);
        FacialCaptureFrameResult internalData = new FacialCaptureFrameResult(event, eyeDistanceApart, uniformLightingScore, sharpnessScore, qualityScore, eyesOpenScore, livenessScore);
        this.mFacialCaptureUxp.addPerFrameUxpData(internalData);
        synchronized (this.mYuvLock) {
            int resultCode2 = checkImageQuality(yuv, internalData);
            switch (resultCode2) {
                case 0:
                    ((IAnalyzeResponse) this.mCallback.get()).onAnalyzeSuccess(resultCode2, new ExtraInfo(this.mEyesOpenPreviewYuv));
                    break;
                case 1:
                case 2:
                    ((IAnalyzeResponse) this.mCallback.get()).onAnalyzeFail(resultCode2, null);
                    break;
            }
        }
        this.mDaonThread.cancel(true);
    }

    public boolean isReadyForImageCapture(FacialCaptureFrameResult result) {
        return result.isDeviceUpright && result.isFaceFound && result.isLightingUniform && result.isFaceDistanceGood && result.isSharpnessGood;
    }

    private int screenCallbackResults(YUV yuv) {
        if (!this.mIsInitialized) {
            return 2;
        }
        int i = this.mNumFrames;
        this.mNumFrames = i + 1;
        if (i < this.mNumFramesToIgnore) {
            return 1;
        }
        if (yuv != null && yuv.getData() != null) {
            return 0;
        }
        Log.d(TAG, "Camera preview frame is null");
        return 1;
    }

    private int checkImageQuality(YUV yuv, FacialCaptureFrameResult internalData) {
        if (isReadyForImageCapture(internalData) && this.mParamMgr.isCurrentModeVideo()) {
            if (this.mCaptureEyesOpen && internalData.livenessScore <= 50 && internalData.eyesOpenScore >= EYES_OPEN_MIN_THRESHOLD) {
                Log.d(TAG, "Saving eyes OPEN. score = " + internalData.eyesOpenScore + ", liveness = " + internalData.livenessScore);
                this.mEyesOpenPreviewYuv = (byte[]) yuv.getData().clone();
            }
            if (internalData.isBlinkDetected && internalData.isLivenessDetected) {
                this.mFacialCaptureUxp.addFinalFrameUxpData(internalData);
                if (!this.mIsInitialized) {
                    return 2;
                }
                if (this.mEyesOpenPreviewYuv == null) {
                    Log.d(TAG, "Saving eyes CLOSED. score = " + internalData.eyesOpenScore + ", liveness = " + internalData.livenessScore);
                    this.mEyesOpenPreviewYuv = (byte[]) yuv.getData().clone();
                }
                return 0;
            }
        }
        return 1;
    }
}
