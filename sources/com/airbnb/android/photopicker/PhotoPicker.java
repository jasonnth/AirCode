package com.airbnb.android.photopicker;

import android.content.Context;
import android.content.Intent;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PhotoPicker {
    public static final int CAMERA = 1;
    public static final int CAMERA_OR_GALLERY = 0;
    public static final String EXTRA_PHOTO_PATH = "photo_path";
    public static final int GALLERY = 2;
    public static final int RESULT_ERROR = 1;

    public static class Builder implements Serializable {
        static final String EXTRA_BUNDLE = "bundle";
        boolean allowMultiple;
        String applicationId;
        int height;
        int imageSource;
        int width;

        private Builder() {
            this.imageSource = 0;
            this.width = -1;
            this.height = -1;
            this.applicationId = null;
            this.allowMultiple = false;
        }

        public Builder setSource(int imageSource2) {
            this.imageSource = imageSource2;
            return this;
        }

        public Builder targetOutputDimensions(int width2, int height2) {
            this.width = width2;
            this.height = height2;
            return this;
        }

        public Builder setApplicationId(String applicationId2) {
            this.applicationId = applicationId2;
            return this;
        }

        public Intent create(Context context) {
            return new Intent(context, PhotoPickerActivity.class).putExtra("bundle", this);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageSource {
    }

    public static Builder builder() {
        return new Builder();
    }
}
