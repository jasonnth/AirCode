package com.airbnb.android.photopicker;

import android.content.Context;
import java.io.File;

public class FileUtils {
    private static final String DEFAULT_CAPTURE_FILENAME = "captured_photo";
    private static final String TEMPORARY_FILE = "temporary_photo_file";

    public static File getBaseFileDir(Context context) {
        File file = new File(context.getExternalCacheDir(), "photo-picker-photos");
        file.mkdirs();
        return file;
    }

    public static File createPhotoFile(Context context) {
        return new File(getBaseFileDir(context), DEFAULT_CAPTURE_FILENAME + System.currentTimeMillis() + ".jpg");
    }

    public static File createTemporaryFile(Context context) {
        return new File(getBaseFileDir(context), TEMPORARY_FILE + System.currentTimeMillis() + ".jpg");
    }
}
