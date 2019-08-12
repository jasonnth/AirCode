package com.airbnb.android.core.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PhotoCompressor {
    /* access modifiers changed from: private */
    public static final String TAG = PhotoCompressor.class.getSimpleName();
    /* access modifiers changed from: private */
    public final Set<CompressionTask> activeTasks = new HashSet();
    /* access modifiers changed from: private */
    public final Context context;

    public interface PhotoCompressionCallback {
        void onCompressionFailure();

        void onPhotoCompressed(String str);
    }

    public static abstract class SimpleCompressionCallback implements PhotoCompressionCallback {
        private final Context context;

        public SimpleCompressionCallback(Context context2) {
            this.context = context2;
        }

        public void onCompressionFailure() {
            BugsnagWrapper.notify((Throwable) new IOException("Photo compression failed"));
            Toast.makeText(this.context, C0716R.string.profile_photo_error, 0).show();
        }
    }

    private class CompressionTask extends AsyncTask<Uri, Void, String> {
        private PhotoCompressionCallback callback;

        public CompressionTask(Uri uri, PhotoCompressionCallback callback2) {
            if (uri == null) {
                throw new NullPointerException("uri is null");
            }
            this.callback = callback2;
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{uri});
        }

        public void cancelAndClearCallback() {
            this.callback = null;
            cancel(false);
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Uri... params) {
            Uri uri = params[0];
            try {
                return ImageUtils.compressBitmapForUpload(Media.getBitmap(PhotoCompressor.this.context.getContentResolver(), uri), ImageUtil.getExifOrientation(new File(uri.getPath()).getAbsolutePath()));
            } catch (IOException e) {
                C0715L.m1191e(PhotoCompressor.TAG, "Error opening photo uri: " + uri);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String filePath) {
            PhotoCompressor.this.activeTasks.remove(this);
            if (this.callback != null) {
                if (TextUtils.isEmpty(filePath)) {
                    this.callback.onCompressionFailure();
                } else {
                    this.callback.onPhotoCompressed(filePath);
                }
            }
        }
    }

    public PhotoCompressor(Context context2) {
        this.context = context2;
    }

    public void compressPhoto(Uri uri, PhotoCompressionCallback callback) {
        this.activeTasks.add(new CompressionTask(uri, callback));
    }

    public void cancelCompressionJobs() {
        for (CompressionTask task : this.activeTasks) {
            task.cancelAndClearCallback();
        }
        this.activeTasks.clear();
    }
}
