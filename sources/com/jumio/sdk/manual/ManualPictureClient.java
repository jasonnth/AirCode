package com.jumio.sdk.manual;

import android.content.Context;
import android.graphics.Bitmap;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.core.ImageQuality;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ManualPictureClient extends ExtractionClient<ExtractionUpdate, StaticModel> {
    private final Executor mCheckQueue = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public final AtomicBoolean mInImageCheck = new AtomicBoolean(false);
    private AtomicBoolean mPictureTaken = new AtomicBoolean(false);
    private final AtomicBoolean mTakePicture = new AtomicBoolean(false);

    private class ImageCheckTask extends Thread {
        private byte[] data;

        public ImageCheckTask(byte[] data2) {
            this.data = Arrays.copyOf(data2, data2.length);
        }

        public void run() {
            int width;
            int height;
            if (ManualPictureClient.this.isPortrait()) {
                width = ManualPictureClient.this.getPreviewProperties().preview.height;
                height = ManualPictureClient.this.getPreviewProperties().preview.width;
            } else {
                width = ManualPictureClient.this.getPreviewProperties().preview.width;
                height = ManualPictureClient.this.getPreviewProperties().preview.height;
            }
            ImageQuality.isFlashNeeded(this.data, width, height, false);
            this.data = null;
            System.gc();
            synchronized (ManualPictureClient.this) {
                ManualPictureClient.this.mInImageCheck.set(false);
            }
        }
    }

    private class SavingTask extends Thread {
        private byte[] data;

        public SavingTask(byte[] data2) {
            this.data = Arrays.copyOf(data2, data2.length);
        }

        public void run() {
            ManualPictureClient.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(1.0f)));
            Bitmap bitmap = CameraUtils.yuv2bitmap(this.data, ManualPictureClient.this.isPortrait(), ManualPictureClient.this.getPreviewProperties(), true);
            ManualPictureClient.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, bitmap));
            ManualPictureClient.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, bitmap));
            ManualPictureClient.this.publishResult(null);
            this.data = null;
            System.gc();
        }
    }

    public ManualPictureClient(Context context) {
        super(context);
    }

    public void configure(StaticModel configurationModel) {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mInImageCheck.set(false);
        this.mTakePicture.set(false);
        this.mPictureTaken.set(false);
    }

    public void destroy() {
    }

    public void feed(byte[] item) {
        if (this.mInImageCheck.compareAndSet(false, true)) {
            this.mCheckQueue.execute(new ImageCheckTask(item));
        }
        if (this.mTakePicture.get() && !this.mPictureTaken.get()) {
            this.mPictureTaken.set(true);
            new SavingTask(item).start();
        }
    }

    public void takePicture() {
        this.mTakePicture.set(true);
    }

    public boolean takePictureManually() {
        return true;
    }
}
