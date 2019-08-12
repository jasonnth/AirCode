package com.airbnb.android.contentframework.controller;

import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.data.StoryCreationImage.PhotoType;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.utils.ImageUtil;
import java.io.IOException;

public class ImageUploadProcessor {
    private static final int MAX_HEIGHT_LANDSCAPE = 1080;
    private static final int MAX_WIDTH = 1440;

    public static String processPhotoForUpload(StoryCreationImage image) throws IOException {
        Rect region;
        int inSampleSize = 1;
        PhotoType photoType = image.photoType();
        int width = image.width();
        int height = image.height();
        switch (photoType) {
            case Landscape:
                inSampleSize = getInSampleSize(height, MAX_HEIGHT_LANDSCAPE);
                break;
            case Portrait:
                inSampleSize = getInSampleSize(width, MAX_WIDTH);
                break;
            case Square:
                inSampleSize = getInSampleSize(Math.min(width, height), MAX_WIDTH);
                break;
        }
        Options options = new Options();
        options.inSampleSize = inSampleSize;
        if (image.width() >= image.height()) {
            int horizontalCrop = (int) Math.max(0.0f, (((float) width) - (((float) height) * photoType.aspectRatio)) / 2.0f);
            region = new Rect(horizontalCrop, 0, width - horizontalCrop, height);
        } else {
            int verticalCrop = (int) Math.max(0.0f, (((float) height) - (((float) width) / photoType.aspectRatio)) / 2.0f);
            region = new Rect(0, verticalCrop, width, height - verticalCrop);
        }
        return ImageUtils.compressBitmapForUpload(BitmapRegionDecoder.newInstance(image.filePath(), false).decodeRegion(region, options), ImageUtil.getExifOrientation(image.filePath()));
    }

    private static int getInSampleSize(int srcSize, int maxSize) {
        int inSampleSize = 1;
        while ((srcSize / 2) / inSampleSize >= maxSize) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }
}
