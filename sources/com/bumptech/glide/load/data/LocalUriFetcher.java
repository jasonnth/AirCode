package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T> implements DataFetcher<T> {
    private final Context context;
    private T data;
    private final Uri uri;

    /* access modifiers changed from: protected */
    public abstract void close(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T loadResource(Uri uri2, ContentResolver contentResolver) throws FileNotFoundException;

    public LocalUriFetcher(Context context2, Uri uri2) {
        this.context = context2.getApplicationContext();
        this.uri = uri2;
    }

    public final T loadData(Priority priority) throws Exception {
        this.data = loadResource(this.uri, this.context.getContentResolver());
        return this.data;
    }

    public void cleanup() {
        if (this.data != null) {
            try {
                close(this.data);
            } catch (IOException e) {
                if (Log.isLoggable("LocalUriFetcher", 2)) {
                    Log.v("LocalUriFetcher", "failed to close data", e);
                }
            }
        }
    }

    public void cancel() {
    }

    public String getId() {
        return this.uri.toString();
    }
}
