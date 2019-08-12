package com.airbnb.android.lib.tasks;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.utils.ImageUtil;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class HaloImageScalingTask extends AsyncTask<Void, Void, Bitmap> {
    final int imageHeight;
    final Uri imageUri;
    WeakReference<HaloImageView> imageViewReference;
    final int imageWidth;

    public HaloImageScalingTask(Uri uri, HaloImageView haloImageView) {
        this.imageViewReference = new WeakReference<>(haloImageView);
        this.imageUri = uri;
        this.imageWidth = haloImageView.getWidth();
        this.imageHeight = haloImageView.getHeight();
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(Void... params) {
        File file = new File(this.imageUri.getPath());
        try {
            return ImageUtils.makeCircleBitmap(ImageUtils.rotateIfNeeded(ImageUtils.scaleBitmap(file.getAbsolutePath(), this.imageWidth, this.imageHeight), ImageUtil.getExifOrientation(file.getAbsolutePath())));
        } catch (IOException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap bitmap) {
        HaloImageView haloImageView = (HaloImageView) this.imageViewReference.get();
        if (haloImageView != null && bitmap != null) {
            haloImageView.setImageBitmap(bitmap);
        }
    }
}
