package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class GuardedResultAsyncTask<Result> extends AsyncTask<Void, Void, Result> {
    private final ReactContext mReactContext;

    /* access modifiers changed from: protected */
    public abstract Result doInBackgroundGuarded();

    /* access modifiers changed from: protected */
    public abstract void onPostExecuteGuarded(Result result);

    protected GuardedResultAsyncTask(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    /* access modifiers changed from: protected */
    public final Result doInBackground(Void... params) {
        try {
            return doInBackgroundGuarded();
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(Result result) {
        try {
            onPostExecuteGuarded(result);
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
        }
    }
}
