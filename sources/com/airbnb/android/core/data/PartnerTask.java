package com.airbnb.android.core.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

public abstract class PartnerTask<T> extends AsyncTask<Void, Void, T> {
    private static final String TAG = PartnerTask.class.getSimpleName();
    AffiliateInfo mAffiliateInfo;
    private final String mColumn;
    private final String mContentUri;
    private final Context mContext;

    /* access modifiers changed from: protected */
    public abstract T getToken(Cursor cursor, int i);

    /* access modifiers changed from: protected */
    public abstract boolean isValidToken(T t);

    /* access modifiers changed from: protected */
    public abstract void storeToken(T t);

    public PartnerTask(Context context, String contentUri, String column) {
        this.mContext = context;
        this.mContentUri = contentUri;
        this.mColumn = column;
    }

    /* access modifiers changed from: protected */
    public T doInBackground(Void... params) {
        try {
            Cursor cursor = this.mContext.getContentResolver().query(Uri.parse(this.mContentUri), null, null, null, null);
            if (cursor != null) {
                T receivedToken = null;
                if (cursor.moveToFirst()) {
                    receivedToken = getToken(cursor, cursor.getColumnIndex(this.mColumn));
                }
                cursor.close();
                return receivedToken;
            }
        } catch (SecurityException e) {
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
                Log.e(TAG, "Security exception with error: " + message);
            }
        } catch (RuntimeException e2) {
            Log.e(TAG, "Exception" + e2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(T token) {
        if (isValidToken(token)) {
            storeToken(token);
        }
    }

    public void getTokenAsync() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
