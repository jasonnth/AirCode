package com.airbnb.android.react.maps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import p005cn.jpush.android.JPushConstants.PushService;

public class AirMapModule extends ReactContextBaseJavaModule {
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";

    public AirMapModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "AirMapModule";
    }

    public Activity getActivity() {
        return getCurrentActivity();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    @ReactMethod
    public void takeSnapshot(int tag, ReadableMap options, Promise promise) {
        final ReactApplicationContext context = getReactApplicationContext();
        final String format = options.hasKey("format") ? options.getString("format") : SNAPSHOT_FORMAT_PNG;
        final CompressFormat compressFormat = format.equals(SNAPSHOT_FORMAT_PNG) ? CompressFormat.PNG : format.equals(SNAPSHOT_FORMAT_JPG) ? CompressFormat.JPEG : null;
        final double quality = options.hasKey("quality") ? options.getDouble("quality") : 1.0d;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Integer width = Integer.valueOf(options.hasKey("width") ? (int) (((double) displayMetrics.density) * options.getDouble("width")) : 0);
        final Integer height = Integer.valueOf(options.hasKey("height") ? (int) (((double) displayMetrics.density) * options.getDouble("height")) : 0);
        final String result = options.hasKey(PushService.PARAM_RESULT) ? options.getString(PushService.PARAM_RESULT) : "file";
        final int i = tag;
        final Promise promise2 = promise;
        ((UIManagerModule) context.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                AirMapView view = (AirMapView) nvhm.resolveView(i);
                if (view == null) {
                    promise2.reject("AirMapView not found");
                } else if (view.map == null) {
                    promise2.reject("AirMapView.map is not valid");
                } else {
                    view.map.snapshot(new SnapshotReadyCallback() {
                        public void onSnapshotReady(Bitmap snapshot) {
                            if (snapshot == null) {
                                promise2.reject("Failed to generate bitmap, snapshot = null");
                                return;
                            }
                            if (!(width.intValue() == 0 || height.intValue() == 0 || (width.intValue() == snapshot.getWidth() && height.intValue() == snapshot.getHeight()))) {
                                snapshot = Bitmap.createScaledBitmap(snapshot, width.intValue(), height.intValue(), true);
                            }
                            if (result.equals("file")) {
                                try {
                                    File tempFile = File.createTempFile("AirMapSnapshot", "." + format, context.getCacheDir());
                                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                                    snapshot.compress(compressFormat, (int) (quality * 100.0d), outputStream);
                                    AirMapModule.closeQuietly(outputStream);
                                    promise2.resolve(Uri.fromFile(tempFile).toString());
                                } catch (Exception e) {
                                    promise2.reject((Throwable) e);
                                }
                            } else if (result.equals(AirMapModule.SNAPSHOT_RESULT_BASE64)) {
                                ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                                snapshot.compress(compressFormat, (int) (quality * 100.0d), outputStream2);
                                AirMapModule.closeQuietly(outputStream2);
                                promise2.resolve(Base64.encodeToString(outputStream2.toByteArray(), 2));
                            }
                        }
                    });
                }
            }
        });
    }
}
