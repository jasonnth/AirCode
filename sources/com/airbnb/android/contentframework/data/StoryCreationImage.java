package com.airbnb.android.contentframework.data;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Parcelable;
import com.airbnb.android.contentframework.imagepicker.PhotoMetadataUtils;

public abstract class StoryCreationImage implements Parcelable {

    public enum PhotoType {
        Square(1.0f),
        Portrait(0.75f),
        Landscape(1.33f);
        
        public final float aspectRatio;

        private PhotoType(float aspectRatio2) {
            this.aspectRatio = aspectRatio2;
        }
    }

    public abstract String filePath();

    public abstract int height();

    public abstract PhotoType photoType();

    public abstract Uri uri();

    public abstract int width();

    public static StoryCreationImage fromUri(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Point size = PhotoMetadataUtils.getBitmapSize(contentResolver, uri);
        return new AutoValue_StoryCreationImage(uri, getPhotoTypeForImageSize(context, size), PhotoMetadataUtils.getPath(contentResolver, uri), size.x, size.y);
    }

    private static PhotoType getPhotoTypeForImageSize(Context context, Point imageSize) {
        int width = imageSize.x;
        int height = imageSize.y;
        if (width == 0 || height == 0 || width == height) {
            return PhotoType.Square;
        }
        float ratio = ((float) imageSize.x) / ((float) imageSize.y);
        return width > height ? ratio < PhotoType.Landscape.aspectRatio ? PhotoType.Square : PhotoType.Landscape : ratio > PhotoType.Portrait.aspectRatio ? PhotoType.Square : PhotoType.Portrait;
    }
}
