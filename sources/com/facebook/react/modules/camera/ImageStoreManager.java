package com.facebook.react.modules.camera;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64OutputStream;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@ReactModule(name = "ImageStoreManager")
public class ImageStoreManager extends ReactContextBaseJavaModule {
    private static final int BUFFER_SIZE = 8192;

    private class GetBase64Task extends GuardedAsyncTask<Void, Void> {
        private final Callback mError;
        private final Callback mSuccess;
        private final String mUri;

        private GetBase64Task(ReactContext reactContext, String uri, Callback success, Callback error) {
            super(reactContext);
            this.mUri = uri;
            this.mSuccess = success;
            this.mError = error;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... params) {
            try {
                InputStream is = ImageStoreManager.this.getReactApplicationContext().getContentResolver().openInputStream(Uri.parse(this.mUri));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Base64OutputStream b64os = new Base64OutputStream(baos, 0);
                byte[] buffer = new byte[8192];
                while (true) {
                    try {
                        int bytesRead = is.read(buffer);
                        if (bytesRead > -1) {
                            b64os.write(buffer, 0, bytesRead);
                        } else {
                            this.mSuccess.invoke(baos.toString());
                            return;
                        }
                    } catch (IOException e) {
                        this.mError.invoke(e.getMessage());
                        return;
                    } finally {
                        ImageStoreManager.closeQuietly(is);
                        ImageStoreManager.closeQuietly(b64os);
                    }
                }
            } catch (FileNotFoundException e2) {
                this.mError.invoke(e2.getMessage());
            }
        }
    }

    public ImageStoreManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ImageStoreManager";
    }

    @ReactMethod
    public void getBase64ForTag(String uri, Callback success, Callback error) {
        new GetBase64Task(getReactApplicationContext(), uri, success, error).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }
}
