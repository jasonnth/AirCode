package android.support.p000v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.p000v4.p001os.CancellationSignal;
import android.support.p000v4.p001os.OperationCanceledException;

/* renamed from: android.support.v4.content.ContentResolverCompat */
public final class ContentResolverCompat {
    private static final ContentResolverCompatImpl IMPL;

    /* renamed from: android.support.v4.content.ContentResolverCompat$ContentResolverCompatImpl */
    interface ContentResolverCompatImpl {
        Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);
    }

    /* renamed from: android.support.v4.content.ContentResolverCompat$ContentResolverCompatImplBase */
    static class ContentResolverCompatImplBase implements ContentResolverCompatImpl {
        ContentResolverCompatImplBase() {
        }

        public Cursor query(ContentResolver resolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            return resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        }
    }

    /* renamed from: android.support.v4.content.ContentResolverCompat$ContentResolverCompatImplJB */
    static class ContentResolverCompatImplJB extends ContentResolverCompatImplBase {
        ContentResolverCompatImplJB() {
        }

        public Cursor query(ContentResolver resolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
            Object obj;
            if (cancellationSignal != null) {
                try {
                    obj = cancellationSignal.getCancellationSignalObject();
                } catch (Exception e) {
                    if (ContentResolverCompatJellybean.isFrameworkOperationCanceledException(e)) {
                        throw new OperationCanceledException();
                    }
                    throw e;
                }
            } else {
                obj = null;
            }
            return ContentResolverCompatJellybean.query(resolver, uri, projection, selection, selectionArgs, sortOrder, obj);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            IMPL = new ContentResolverCompatImplJB();
        } else {
            IMPL = new ContentResolverCompatImplBase();
        }
    }

    public static Cursor query(ContentResolver resolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return IMPL.query(resolver, uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }
}
