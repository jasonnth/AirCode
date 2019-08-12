package com.airbnb.android.contentframework.imagepicker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.app.LoaderManager.LoaderCallbacks;
import android.support.p000v4.content.CursorLoader;
import android.support.p000v4.content.Loader;
import java.lang.ref.WeakReference;
import p005cn.jpush.android.data.DbHelper;

public class MediaLoader implements LoaderCallbacks<Cursor> {
    public static final String COLUMN_COUNT = "count";
    private static final int LOADER_ID = 2;
    private static final String ORDER_BY = "_id DESC";
    private static final String[] PROJECTION = {DbHelper.TABLE_ID};
    private static final Uri QUERY_URI = Files.getContentUri("external");
    private static final String SELECTION_ALL = "media_type=? AND _size>0 AND (mime_type=? OR mime_type=?)";
    private static final String[] SELECTION_ALL_ARGS = {String.valueOf(1), "image/jpeg", "image/png"};
    private MediaItemLoaderCallbacks callbacks;
    private WeakReference<Context> contextRef;
    private LoaderManager loaderManager;

    public interface MediaItemLoaderCallbacks {
        void onMediaLoad(Cursor cursor);

        void onMediaReset();
    }

    public void onCreate(FragmentActivity context, MediaItemLoaderCallbacks callbacks2) {
        this.contextRef = new WeakReference<>(context);
        this.loaderManager = context.getSupportLoaderManager();
        this.callbacks = callbacks2;
        this.loaderManager.initLoader(2, new Bundle(), this);
    }

    public void onDestroy() {
        this.loaderManager.destroyLoader(2);
        this.callbacks = null;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return null;
        }
        return new CursorLoader(context, QUERY_URI, PROJECTION, SELECTION_ALL, SELECTION_ALL_ARGS, ORDER_BY);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (((Context) this.contextRef.get()) != null) {
            this.callbacks.onMediaLoad(data);
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        if (((Context) this.contextRef.get()) != null) {
            this.callbacks.onMediaReset();
        }
    }
}
