package com.miteksystems.misnap.analyzer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse.ExtraInfo;
import com.miteksystems.misnap.events.OnFrameProcessedEvent;
import com.miteksystems.misnap.imaging.PreviewFrameConverter;
import com.miteksystems.misnap.natives.MiSnapScience;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.utils.Utils;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import p314de.greenrobot.event.EventBus;

public class MiSnapAnalyzer extends MiBaseAnalyzer {
    public static final int MEASURED_FILL_WIDTH = 0;
    public static final int MEASURED_FRAME_CHECKS_PASSED = 1;
    private static final int NATIVE_DOCTYPE_CHECK_BACK = 2;
    private static final int NATIVE_DOCTYPE_CHECK_FRONT = 1;
    private static final int NATIVE_DOCTYPE_GENERIC = 100;
    private static final int NATIVE_DOCTYPE_LICENSE = 40;
    private static final int NATIVE_DOCTYPE_PASSPORT = 70;
    public static final int SHARPNESS = 1;
    /* access modifiers changed from: private */
    public static final String TAG = MiSnapAnalyzer.class.getSimpleName();
    protected Context mAppContext;
    protected IAnalyzeResponse mCallback;
    /* access modifiers changed from: private */
    public int mFrameChecksPassed;
    /* access modifiers changed from: private */
    public int mFrameFillWidth;
    long mFrameStartTime;
    /* access modifiers changed from: private */
    public volatile boolean mIsAnalyzingFrame;
    protected boolean mIsInitialized;
    protected boolean mIsTheOnlyAnalyzer;
    /* access modifiers changed from: private */
    public int mNativeColorSpace;
    /* access modifiers changed from: private */
    public int mNativeDocType;
    long mNumFrames;
    protected ParameterManager mParamMgr;
    /* access modifiers changed from: private */
    public int mPreviewHeight;
    /* access modifiers changed from: private */
    public int mPreviewWidth;
    /* access modifiers changed from: private */
    public byte[] mPreviewYUV;
    protected MiSnapScience mScience;

    public static class MiSnapAnalyzerExtraInfo extends ExtraInfo {
        public int[][] fourCorners;
        public int[] measuredValues;
        public int[] returnValues;

        public MiSnapAnalyzerExtraInfo(byte[] bArr, int[] iArr) {
            this(bArr, iArr, null, null);
        }

        public MiSnapAnalyzerExtraInfo(byte[] bArr, int[] iArr, int[] iArr2) {
            this(bArr, iArr, iArr2, null);
        }

        public MiSnapAnalyzerExtraInfo(byte[] bArr, int[] iArr, int[] iArr2, int[][] iArr3) {
            super(bArr);
            this.returnValues = iArr;
            this.measuredValues = iArr2;
            this.fourCorners = iArr3;
        }
    }

    /* renamed from: com.miteksystems.misnap.analyzer.MiSnapAnalyzer$a */
    class C4561a extends AsyncTask<Void, Void, int[]> {
        C4561a() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2123a();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            int[] iArr = (int[]) obj;
            super.onPostExecute(iArr);
            if (iArr == null) {
                Log.d(MiSnapAnalyzer.TAG, "mIsAnalyzingFrame = false");
                MiSnapAnalyzer.this.mCallback.onAnalyzeFail(1, null);
                MiSnapAnalyzer.this.mIsAnalyzingFrame = false;
                return;
            }
            boolean postAnalyzer = MiSnapAnalyzer.this.postAnalyzer(iArr);
            MiSnapAnalyzerExtraInfo miSnapAnalyzerExtraInfo = new MiSnapAnalyzerExtraInfo(MiSnapAnalyzer.this.mPreviewYUV, iArr, new int[]{MiSnapAnalyzer.this.mFrameFillWidth, MiSnapAnalyzer.this.mFrameChecksPassed}, MiSnapAnalyzer.this.getFourCornersAs4x2Array(iArr));
            if (MiSnapAnalyzer.this.mIsTheOnlyAnalyzer && (!MiSnapAnalyzer.this.mParamMgr.isCurrentModeVideo() || postAnalyzer)) {
                MiSnapAnalyzer.this.addGoodFrameUxpData(miSnapAnalyzerExtraInfo);
            }
            if (postAnalyzer) {
                MiSnapAnalyzer.this.mCallback.onAnalyzeSuccess(0, miSnapAnalyzerExtraInfo);
            } else {
                MiSnapAnalyzer.this.mCallback.onAnalyzeFail(1, miSnapAnalyzerExtraInfo);
            }
            MiSnapAnalyzer.this.mIsAnalyzingFrame = false;
        }

        /* renamed from: a */
        private int[] m2123a() {
            Log.v(MiSnapAnalyzer.TAG, "onPreviewFrame - received requ:" + (MiSnapAnalyzer.this.mPreviewYUV != null ? MiSnapAnalyzer.this.mPreviewYUV.length : 0));
            int[] iArr = new int[28];
            try {
                if (MiSnapAnalyzer.this.mFrameStartTime == 0) {
                    MiSnapAnalyzer.this.mFrameStartTime = System.currentTimeMillis();
                }
                if (MiSnapAnalyzer.this.mScience != null) {
                    MiSnapAnalyzer.this.mScience.Analyze(MiSnapAnalyzer.this.mPreviewYUV, MiSnapAnalyzer.this.mPreviewWidth, MiSnapAnalyzer.this.mPreviewHeight, MiSnapAnalyzer.this.mNativeColorSpace, MiSnapAnalyzer.this.mNativeDocType, iArr);
                }
                MiSnapAnalyzer.this.mNumFrames++;
                Log.d(MiSnapAnalyzer.TAG, "MiSnapAnalyzer FPS: " + ((((float) MiSnapAnalyzer.this.mNumFrames) * 1000.0f) / ((float) (System.currentTimeMillis() - MiSnapAnalyzer.this.mFrameStartTime))));
                Log.v(MiSnapAnalyzer.TAG, "onPreviewFrame - resp received from SL");
                return iArr;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public MiSnapAnalyzer(Context context, ParameterManager parameterManager, boolean z) {
        this.mAppContext = context.getApplicationContext();
        this.mParamMgr = parameterManager;
        this.mIsTheOnlyAnalyzer = z;
    }

    public boolean init() {
        Log.d(TAG, "Initializing MiSnapAnalyzer");
        if (this.mScience == null) {
            this.mScience = new MiSnapScience();
        }
        this.mIsInitialized = MiSnapScience.f3675a;
        return this.mIsInitialized;
    }

    public void deinit() {
        Log.d(TAG, "Deinit MiSnapAnalyzer");
        this.mIsInitialized = false;
        this.mPreviewYUV = null;
        this.mScience = null;
    }

    public void analyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3) {
        if (!this.mIsInitialized) {
            Log.d(TAG, "Analyze - skip frame");
            iAnalyzeResponse.onAnalyzeFail(2, null);
        } else if (this.mIsAnalyzingFrame) {
            Log.d(TAG, "Analyze - skip frame");
            iAnalyzeResponse.onAnalyzeFail(3, null);
        } else if (!this.mParamMgr.isCurrentModeVideo()) {
            Log.d(TAG, "Analyze - skip frame");
            iAnalyzeResponse.onAnalyzeFail(4, null);
        } else if (bArr == null || bArr.length == 0) {
            Log.d(TAG, "Analyze - skip frame");
            iAnalyzeResponse.onAnalyzeFail(1, null);
        } else {
            forceAnalyze(iAnalyzeResponse, bArr, i, i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void forceAnalyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3) {
        this.mIsAnalyzingFrame = true;
        Log.d(TAG, "Analyze");
        this.mCallback = iAnalyzeResponse;
        this.mPreviewYUV = bArr;
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
        this.mNativeColorSpace = getNativeColorSpace(i3);
        this.mNativeDocType = getNativeDocType();
        Log.d(TAG, "Analyze execute");
        C4561a aVar = new C4561a();
        if (VERSION.SDK_INT >= 11) {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            aVar.execute(new Void[0]);
        }
    }

    public void onManualPictureTaken(IAnalyzeResponse iAnalyzeResponse, byte[] bArr) {
        Bitmap convertPreviewFrameToBitmap = PreviewFrameConverter.convertPreviewFrameToBitmap(bArr);
        ByteBuffer convertBitmapToRgbaArray = PreviewFrameConverter.convertBitmapToRgbaArray(convertPreviewFrameToBitmap);
        int width = convertPreviewFrameToBitmap.getWidth();
        int height = convertPreviewFrameToBitmap.getHeight();
        convertPreviewFrameToBitmap.recycle();
        forceAnalyze(iAnalyzeResponse, convertBitmapToRgbaArray.array(), width, height, 5);
    }

    /* access modifiers changed from: protected */
    public int getNativeColorSpace(int i) {
        if (i == 17) {
            return 4;
        }
        if (i < 0 || i > 5) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int getNativeDocType() {
        if (this.mParamMgr.isCheckFront()) {
            return 1;
        }
        if (this.mParamMgr.isCheckBack()) {
            return 2;
        }
        if (this.mParamMgr.isLicense()) {
            return 40;
        }
        if (this.mParamMgr.isPassport()) {
            return 70;
        }
        return 100;
    }

    public boolean postAnalyzerForTesting(IAnalyzeResponse iAnalyzeResponse, int[] iArr) {
        this.mCallback = iAnalyzeResponse;
        return postAnalyzer(iArr);
    }

    public boolean postAnalyzer(int[] iArr) {
        if (iArr.length < 28) {
            return false;
        }
        try {
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = iArr[2];
            int i4 = iArr[3];
            int i5 = iArr[4];
            int i6 = iArr[5];
            int i7 = iArr[6];
            int i8 = iArr[7];
            int i9 = iArr[20];
            int i10 = iArr[21];
            int i11 = iArr[22];
            int i12 = iArr[23];
            int[][] fourCornersAs4x2Array = getFourCornersAs4x2Array(iArr);
            int[][] glareCornersAs2x2Array = getGlareCornersAs2x2Array(iArr);
            int[] iArr2 = {iArr[16], iArr[17]};
            int[] iArr3 = {iArr[18], iArr[19]};
            Log.v(TAG, "nScienceFourCornerConfidence:" + i3);
            Log.v(TAG, "nScienceBrightness:" + i);
            Log.v(TAG, "nScienceSharpness:" + i2);
            Log.v(TAG, "nScienceSkewAngle:" + i4);
            Log.v(TAG, "nScienceRotationAngle:" + i5);
            Log.v(TAG, "nScienceMinHorizontalFill:" + i6);
            Log.v(TAG, "nScienceMinPadding:" + i8);
            Log.v(TAG, "nScienceAreaRatio:" + i7);
            Log.v(TAG, "nScienceCorners: A:" + fourCornersAs4x2Array[0][0] + "," + fourCornersAs4x2Array[0][1] + " B:" + fourCornersAs4x2Array[1][0] + "," + fourCornersAs4x2Array[1][1] + " C:" + fourCornersAs4x2Array[2][0] + "," + fourCornersAs4x2Array[2][1] + " D:" + fourCornersAs4x2Array[3][0] + "," + fourCornersAs4x2Array[3][1]);
            Log.v(TAG, "nScienceSolidBackground:" + i10);
            Log.v(TAG, "nScienceContrast:" + i11);
            Log.v(TAG, "nScienceMicrConfidence: " + i12);
            Log.v(TAG, "nScienceGlareConfidence:" + i9);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_confidence, i3);
            if (i2 == 1000 && this.mParamMgr.isLicense()) {
                i2 = 500;
            }
            if (i3 < 0) {
                return false;
            }
            this.mFrameChecksPassed = 0;
            if (i3 >= this.mParamMgr.getCornerConfidence()) {
                this.mFrameChecksPassed |= 1;
            }
            if (i8 >= this.mParamMgr.getMinPadding()) {
                this.mFrameChecksPassed |= 64;
            }
            if (i6 >= this.mParamMgr.getmMiSnapViewfinderMinHorizontalFill()) {
                this.mFrameChecksPassed |= 2;
            }
            if (i < this.mParamMgr.getmMaxBrightnessThreshold()) {
                this.mFrameChecksPassed |= 8;
            }
            if (i >= this.mParamMgr.getmBrightnessThreshold()) {
                this.mFrameChecksPassed |= 4;
            }
            if (i11 >= this.mParamMgr.getmContrastThreshold()) {
                this.mFrameChecksPassed |= 256;
            }
            if (i10 >= this.mParamMgr.getmSolidBackgroundThreshold()) {
                this.mFrameChecksPassed |= 512;
            }
            if (i4 <= this.mParamMgr.getmAngleThreshold()) {
                this.mFrameChecksPassed |= 16;
            }
            if (i5 <= this.mParamMgr.getmAngleThreshold()) {
                this.mFrameChecksPassed |= 128;
            }
            if (i2 >= this.mParamMgr.getmSharpnessThreshold()) {
                this.mFrameChecksPassed |= 32;
            }
            if (i12 >= this.mParamMgr.getMicrConfidence()) {
                this.mFrameChecksPassed |= 2048;
            }
            if (i9 >= this.mParamMgr.getmNoGlareThreshold()) {
                this.mFrameChecksPassed |= 1024;
            }
            try {
                OnFrameProcessedEvent onFrameProcessedEvent = new OnFrameProcessedEvent(this.mFrameChecksPassed, fourCornersAs4x2Array, glareCornersAs2x2Array);
                EventBus.getDefault().post(onFrameProcessedEvent);
                if (this.mParamMgr.isCheckBack() && !this.mParamMgr.isCurrentModeVideo()) {
                    rescanImageForMicr();
                }
                boolean didFrameCheckFail = onFrameProcessedEvent.didFrameCheckFail(1);
                if (onFrameProcessedEvent.didFrameCheckFail(1024)) {
                    Log.v(TAG, "UI_FRAGMENT_GLARE_DETECTED");
                    Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_glare_failure, i9);
                    return false;
                }
                if (onFrameProcessedEvent.didFrameCheckFail(256)) {
                    Log.v(TAG, "UI_FRAGMENT_LOW_CONTRAST_CHECK_FAILED");
                    Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_low_contrast_failure, i11);
                    if (didFrameCheckFail) {
                        return false;
                    }
                }
                if (onFrameProcessedEvent.didFrameCheckFail(512)) {
                    Log.v(TAG, "UI_FRAGMENT_BUSY_BACKGROUND_CHECK_FAILED");
                    Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_busy_background_failure, i10);
                    if (didFrameCheckFail) {
                        return false;
                    }
                }
                if (onFrameProcessedEvent.didFrameCheckFail(1)) {
                    Log.v(TAG, "UI_FRAGMENT_CORNERS_NOT_FOUND - no corners");
                    Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_not_found, i3);
                    return false;
                } else if (onFrameProcessedEvent.didFrameCheckFail(16) || onFrameProcessedEvent.didFrameCheckFail(128)) {
                    Context context = this.mAppContext;
                    int i13 = C4562R.string.misnap_uxp_angle_failure;
                    if (i4 <= i5) {
                        i4 = i5;
                    }
                    Utils.uxpEvent(context, i13, i4);
                    Log.v(TAG, "UI_FRAGMENT_ANGLE_CHECK_FAILED");
                    return false;
                } else if (onFrameProcessedEvent.didFrameCheckFail(2)) {
                    Log.v(TAG, "Horizontal Min Fill check failed");
                    Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_closeness_failure, i6);
                    return false;
                } else {
                    this.mFrameFillWidth = i6;
                    if (onFrameProcessedEvent.didFrameCheckFail(64)) {
                        Log.v(TAG, "Min padding check failed");
                        Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_tooclose_failure, i8);
                        return false;
                    } else if (onFrameProcessedEvent.didFrameCheckFail(8)) {
                        Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_max_brightness_failure, i);
                        Log.v(TAG, "UI_FRAGMENT_CORNERS_FOUND - fail max brightness");
                        return false;
                    } else if (onFrameProcessedEvent.didFrameCheckFail(4)) {
                        Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_brightness_failure, i);
                        Log.v(TAG, "UI_FRAGMENT_BRIGHTNESS_CHECK_FAILED");
                        return false;
                    } else if (onFrameProcessedEvent.didFrameCheckFail(32)) {
                        Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_sharpness_failure, i2);
                        Log.v(TAG, "UI_FRAGMENT_SHARPNESS_CHECK_FAILED");
                        return false;
                    } else if (this.mParamMgr.isCheckBack() && this.mParamMgr.isCurrentModeVideo() && this.mScience != null && rescanImageForMicr()) {
                        return false;
                    } else {
                        Log.v(TAG, "All Good: ");
                        Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_videoframe);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            Log.e("MiSnapCamera", e2.getMessage());
            return false;
        }
    }

    private boolean rescanImageForMicr() {
        int[] iArr = new int[28];
        this.mScience.Analyze(this.mPreviewYUV, this.mPreviewWidth, this.mPreviewHeight, this.mNativeColorSpace, 1, iArr);
        if (iArr[23] < 450) {
            return false;
        }
        this.mFrameChecksPassed &= -2049;
        EventBus.getDefault().post(new OnFrameProcessedEvent(this.mFrameChecksPassed, (int[][]) Array.newInstance(Integer.TYPE, new int[]{4, 2}), (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 2})));
        Log.v(TAG, "Check Front found instead of a Check Back. MICR detected.");
        return true;
    }

    public void addGoodFrameUxpData(ExtraInfo extraInfo) {
        if (extraInfo instanceof MiSnapAnalyzerExtraInfo) {
            MiSnapAnalyzerExtraInfo miSnapAnalyzerExtraInfo = (MiSnapAnalyzerExtraInfo) extraInfo;
            int[] iArr = miSnapAnalyzerExtraInfo.returnValues;
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_angle, iArr[3]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_brightness, iArr[0]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_sharpness, iArr[1]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_micr, iArr[23]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_low_contrast, iArr[22]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_busy_background, iArr[21]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_glare, iArr[20]);
            Utils.uxpEvent(this.mAppContext, C4562R.string.misnap_uxp_measured_width, miSnapAnalyzerExtraInfo.measuredValues[0]);
        }
    }

    /* access modifiers changed from: private */
    public int[][] getFourCornersAs4x2Array(int[] iArr) {
        boolean z = this.mParamMgr.getUsePortraitOrientation() == 1;
        boolean needToRotateFrameBy180Degrees = Utils.needToRotateFrameBy180Degrees(this.mAppContext);
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{4, 2});
        for (int i = 0; i < 4; i++) {
            int i2 = (i * 2) + 8;
            iArr2[i] = new int[]{iArr[i2 + 0], iArr[i2 + 1]};
        }
        if (needToRotateFrameBy180Degrees) {
            for (int i3 = 0; i3 < 4; i3++) {
                iArr2[i3] = new int[]{this.mPreviewWidth - iArr2[i3][0], this.mPreviewHeight - iArr2[i3][1]};
            }
        }
        if (z) {
            if (this.mParamMgr.getUseFrontCamera() == 1) {
                for (int i4 = 0; i4 < 4; i4++) {
                    iArr2[i4] = new int[]{iArr2[i4][1], this.mPreviewWidth - iArr2[i4][0]};
                }
            } else {
                for (int i5 = 0; i5 < 4; i5++) {
                    iArr2[i5] = new int[]{this.mPreviewHeight - iArr2[i5][1], iArr2[i5][0]};
                }
            }
        }
        return iArr2;
    }

    private int[][] getGlareCornersAs2x2Array(int[] iArr) {
        boolean z = this.mParamMgr.getUsePortraitOrientation() == 1;
        boolean needToRotateFrameBy180Degrees = Utils.needToRotateFrameBy180Degrees(this.mAppContext);
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 2});
        iArr2[0][0] = iArr[24];
        iArr2[0][1] = iArr[25];
        iArr2[1][0] = iArr[26];
        iArr2[1][1] = iArr[27];
        if (needToRotateFrameBy180Degrees) {
            for (int i = 0; i < 2; i++) {
                iArr2[i] = new int[]{this.mPreviewWidth - iArr2[i][0], this.mPreviewHeight - iArr2[i][1]};
            }
        }
        if (z) {
            if (this.mParamMgr.getUseFrontCamera() == 1) {
                for (int i2 = 0; i2 < 2; i2++) {
                    iArr2[i2] = new int[]{iArr2[i2][1], this.mPreviewWidth - iArr2[i2][0]};
                }
            } else {
                for (int i3 = 0; i3 < 2; i3++) {
                    iArr2[i3] = new int[]{this.mPreviewHeight - iArr2[i3][1], iArr2[i3][0]};
                }
            }
        }
        return iArr2;
    }
}
