package com.airbnb.android.contentframework.data;

import android.net.Uri;
import com.airbnb.android.contentframework.data.StoryCreationImage.PhotoType;

/* renamed from: com.airbnb.android.contentframework.data.$AutoValue_StoryCreationImage reason: invalid class name */
abstract class C$AutoValue_StoryCreationImage extends StoryCreationImage {
    private final String filePath;
    private final int height;
    private final PhotoType photoType;
    private final Uri uri;
    private final int width;

    C$AutoValue_StoryCreationImage(Uri uri2, PhotoType photoType2, String filePath2, int width2, int height2) {
        if (uri2 == null) {
            throw new NullPointerException("Null uri");
        }
        this.uri = uri2;
        if (photoType2 == null) {
            throw new NullPointerException("Null photoType");
        }
        this.photoType = photoType2;
        if (filePath2 == null) {
            throw new NullPointerException("Null filePath");
        }
        this.filePath = filePath2;
        this.width = width2;
        this.height = height2;
    }

    public Uri uri() {
        return this.uri;
    }

    public PhotoType photoType() {
        return this.photoType;
    }

    public String filePath() {
        return this.filePath;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public String toString() {
        return "StoryCreationImage{uri=" + this.uri + ", photoType=" + this.photoType + ", filePath=" + this.filePath + ", width=" + this.width + ", height=" + this.height + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryCreationImage)) {
            return false;
        }
        StoryCreationImage that = (StoryCreationImage) o;
        if (!this.uri.equals(that.uri()) || !this.photoType.equals(that.photoType()) || !this.filePath.equals(that.filePath()) || this.width != that.width() || this.height != that.height()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((1 * 1000003) ^ this.uri.hashCode()) * 1000003) ^ this.photoType.hashCode()) * 1000003) ^ this.filePath.hashCode()) * 1000003) ^ this.width) * 1000003) ^ this.height;
    }
}
