package com.airbnb.android.core;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import com.airbnb.erf.Experiments;

public class ScreenshotObserver extends ContentObserver {
    private static final long DEFAULT_DETECT_WINDOW_SECONDS = 3;
    private static final String EXTERNAL_CONTENT_URI_MATCHER = (Media.EXTERNAL_CONTENT_URI.toString() + "/.+");
    private static final String[] PROJECTION = {"_data", "date_added"};
    private static final String SCREENSHOT_DIRECTORY = "screenshot";
    private static final String SORT_ORDER = "date_added DESC";
    private final Context context;
    private final ScreenshotHandler screenshotHandler;

    public interface ScreenshotHandler {
        void handleScreenshot(String str);
    }

    public ScreenshotObserver(Handler handler, Context context2, ScreenshotHandler screenshotHandler2) {
        super(handler);
        this.context = context2;
        this.screenshotHandler = screenshotHandler2;
    }

    public void onChange(boolean selfChange, Uri uri) {
        if (uri.toString().matches(EXTERNAL_CONTENT_URI_MATCHER)) {
            Cursor cursor = this.context.getContentResolver().query(uri, PROJECTION, null, null, SORT_ORDER);
            String recentScreenshotPath = getRecentScreenshotPath(cursor);
            if (recentScreenshotPath != null && Experiments.showShareSheetForScreenshot()) {
                this.screenshotHandler.handleScreenshot(recentScreenshotPath);
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getRecentScreenshotPath(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndex("_data"));
            long dateAdded = cursor.getLong(cursor.getColumnIndex("date_added"));
            long currentTime = System.currentTimeMillis() / 1000;
            if (path != null && path.toLowerCase().contains(SCREENSHOT_DIRECTORY) && currentTime - dateAdded >= 0 && currentTime - dateAdded <= 3) {
                return path;
            }
        }
        return null;
    }

    private static void sendScreenshotObserverError(Exception e) {
    }
}
