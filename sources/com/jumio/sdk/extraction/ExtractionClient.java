package com.jumio.sdk.extraction;

import android.content.Context;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.core.environment.Environment;
import com.jumio.core.mvp.model.DynamicModel;
import com.jumio.core.mvp.model.StaticModel;

public abstract class ExtractionClient<U, R> extends DynamicModel<byte[], U, R> {
    private final float FOCUS_THRESHOLD = 0.12f;
    private final int INTENSITY_THRESHOLD = 70;
    private boolean dataExtractionActive = false;
    /* access modifiers changed from: protected */
    public Context mContext;
    /* access modifiers changed from: protected */
    public boolean mIsInverted;
    /* access modifiers changed from: protected */
    public boolean mIsPortrait;
    /* access modifiers changed from: protected */
    public boolean mIsTablet;
    private PreviewProperties mPreviewProperties;

    public static class ExtractionUpdate<T> {
        private final T mData;
        private final int mState;

        public ExtractionUpdate(int state, T data) {
            this.mState = state;
            this.mData = data;
        }

        public T getData() {
            return this.mData;
        }

        public int getState() {
            return this.mState;
        }
    }

    @Deprecated
    public static class ImageCheckResult {
        private boolean mFlashNeeded;
        private boolean mRefocusNeeded;

        public ImageCheckResult(boolean flashNeeded, boolean refocusNeeded) {
            this.mFlashNeeded = flashNeeded;
            this.mRefocusNeeded = refocusNeeded;
        }

        public boolean isFlashNeeded() {
            return this.mFlashNeeded;
        }

        public boolean isRefocusNeeded() {
            return this.mRefocusNeeded;
        }
    }

    public abstract void configure(StaticModel staticModel);

    public abstract void destroy();

    /* access modifiers changed from: protected */
    public abstract void init();

    public ExtractionClient(Context context) {
        this.mContext = context;
        Environment.loadJniInterfaceLib();
        Environment.loadJniImageQualityLib();
        CameraUtils.setYuvConversion(new YuvConversionWrapper());
    }

    public void reinit() {
        if (!isDataExtractionActive()) {
            init();
            setDataExtractionActive(true);
        }
    }

    public synchronized void process(byte[] imageData) {
        if (isDataExtractionActive()) {
            feed(imageData);
        }
    }

    public void cancel() {
        setDataExtractionActive(false);
        System.gc();
    }

    public boolean isDataExtractionActive() {
        return this.dataExtractionActive;
    }

    public void setDataExtractionActive(boolean dataExtractionActive2) {
        this.dataExtractionActive = dataExtractionActive2;
    }

    public void setPreviewProperties(PreviewProperties properties) {
        if (properties != null) {
            this.mPreviewProperties = properties.copy();
        }
    }

    public boolean takePictureManually() {
        return false;
    }

    public void takePicture() {
    }

    public void setFlags(boolean isPortrait, boolean isTablet, boolean isInverted) {
        this.mIsPortrait = isPortrait;
        this.mIsTablet = isTablet;
        this.mIsInverted = isInverted;
    }

    public boolean isPortrait() {
        return this.mIsPortrait;
    }

    public PreviewProperties getPreviewProperties() {
        return this.mPreviewProperties;
    }
}
