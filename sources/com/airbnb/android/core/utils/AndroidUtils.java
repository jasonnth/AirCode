package com.airbnb.android.core.utils;

import android.content.Context;
import android.os.Looper;
import android.util.TypedValue;
import com.airbnb.android.utils.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class AndroidUtils {
    public static void validateMainThread() {
        Check.state(Looper.myLooper() == Looper.getMainLooper());
    }

    public static int getResource(Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(16843534, outValue, true);
        return outValue.resourceId;
    }

    public static boolean assetExists(Context context, String assetFileName) {
        InputStream stream = null;
        try {
            stream = context.getAssets().open(assetFileName);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }
}
