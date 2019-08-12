package com.airbnb.android.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore.Images.Media;
import com.airbnb.android.core.ScreenshotObserver.ScreenshotHandler;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Listing;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import permissions.dispatcher.PermissionUtils;

public class ScreenshotManager implements ScreenshotHandler {
    public static final String ENTRY_POINT_LISTING = "listing";
    private final Context context;
    private String entryPoint;
    private Listing listing;
    private ScreenshotObserver screenshotObserver;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntryPoint {
    }

    public ScreenshotManager(Context context2) {
        this.context = context2;
    }

    public void registerForListing(Listing listing2) {
        this.listing = listing2;
        register();
        this.entryPoint = "listing";
    }

    private void register() {
        if (PermissionUtils.hasSelfPermissions(this.context, "android.permission.READ_EXTERNAL_STORAGE")) {
            this.screenshotObserver = new ScreenshotObserver(new Handler(Looper.getMainLooper()), this.context, this);
            this.context.getContentResolver().registerContentObserver(Media.EXTERNAL_CONTENT_URI, true, this.screenshotObserver);
        }
    }

    public void unregister() {
        if (this.screenshotObserver != null) {
            this.context.getContentResolver().unregisterContentObserver(this.screenshotObserver);
        }
        String str = this.entryPoint;
        char c = 65535;
        switch (str.hashCode()) {
            case 181975684:
                if (str.equals("listing")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.listing = null;
                return;
            default:
                return;
        }
    }

    public void handleScreenshot(String screenshotPath) {
        String str = this.entryPoint;
        char c = 65535;
        switch (str.hashCode()) {
            case 181975684:
                if (str.equals("listing")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.context.startActivity(ShareActivityIntents.newIntentForListingScreenshot(this.context, this.listing, screenshotPath));
                return;
            default:
                return;
        }
    }
}
