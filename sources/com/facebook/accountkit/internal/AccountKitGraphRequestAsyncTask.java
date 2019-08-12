package com.facebook.accountkit.internal;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

final class AccountKitGraphRequestAsyncTask extends AsyncTask<Void, Void, AccountKitGraphResponse> {
    private static final int BACKOFF_INTERVAL_SEC = 5;
    private static final int MAX_NUM_RETRIES = 4;
    private static final String TAG = AccountKitGraphRequestAsyncTask.class.getCanonicalName();
    private static volatile AccountKitGraphRequestAsyncTask currentAsyncTask;
    /* access modifiers changed from: private */
    public final Callback callback;
    private final HttpURLConnection connection;
    private Exception exception;
    /* access modifiers changed from: private */
    public final int numRetries;
    /* access modifiers changed from: private */
    public final AccountKitGraphRequest request;

    static AccountKitGraphRequestAsyncTask getCurrentAsyncTask() {
        return currentAsyncTask;
    }

    static void setCurrentAsyncTask(AccountKitGraphRequestAsyncTask task) {
        currentAsyncTask = task;
    }

    static AccountKitGraphRequestAsyncTask cancelCurrentAsyncTask() {
        AccountKitGraphRequestAsyncTask task = currentAsyncTask;
        if (task != null) {
            task.cancel(true);
        }
        return task;
    }

    AccountKitGraphRequestAsyncTask(AccountKitGraphRequest request2, Callback callback2) {
        this(null, request2, callback2, 0);
    }

    private AccountKitGraphRequestAsyncTask(HttpURLConnection connection2, AccountKitGraphRequest request2, Callback callback2, int numRetries2) {
        this.connection = connection2;
        this.request = request2;
        this.callback = callback2;
        this.numRetries = numRetries2;
    }

    public String toString() {
        return "{AccountKitGraphRequestAsyncTask:  connection: " + this.connection + ", request: " + this.request + "}";
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Handler handler;
        super.onPreExecute();
        if (this.request.getCallbackHandler() == null) {
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.request.setCallbackHandler(handler);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(AccountKitGraphResponse result) {
        super.onPostExecute(result);
        if (result == null || result.getError() == null || result.getError().getException().getError().getErrorType() != Type.NETWORK_CONNECTION_ERROR || result.getError().getException().getError().getDetailErrorCode() == 101 || this.numRetries >= 4) {
            if (this.callback != null) {
                this.callback.onCompleted(result);
            }
            if (this.exception != null) {
                Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.exception.getMessage()}));
                return;
            }
            return;
        }
        new Handler(AccountKitController.getApplicationContext().getMainLooper()).post(new Runnable() {
            public void run() {
                int newNumRetries = AccountKitGraphRequestAsyncTask.this.numRetries + 1;
                final AccountKitGraphRequestAsyncTask asyncTask = new AccountKitGraphRequestAsyncTask(null, AccountKitGraphRequestAsyncTask.this.request, AccountKitGraphRequestAsyncTask.this.callback, newNumRetries);
                Utility.getBackgroundExecutor().schedule(new Runnable() {
                    public void run() {
                        if (!AccountKitGraphRequestAsyncTask.this.isCancelled() && !asyncTask.isCancelled()) {
                            asyncTask.executeOnExecutor(Utility.getThreadPoolExecutor(), new Void[0]);
                        }
                    }
                }, (long) (newNumRetries * 5), TimeUnit.SECONDS);
                if (AccountKitGraphRequestAsyncTask.this.request.isLoginRequest()) {
                    AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(asyncTask);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public AccountKitGraphResponse doInBackground(Void... params) {
        try {
            if (this.connection == null) {
                return this.request.executeAndWait();
            }
            return AccountKitGraphRequest.executeConnectionAndWait(this.connection, this.request);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }
}
