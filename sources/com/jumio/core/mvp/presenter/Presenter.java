package com.jumio.core.mvp.presenter;

import com.jumio.commons.log.Log;

public abstract class Presenter<V> {
    private final String LOGTAG = "PresenterLifecycle";
    private boolean isActive = false;
    private V mView;

    /* access modifiers changed from: protected */
    public abstract void onStart();

    /* access modifiers changed from: protected */
    public abstract void onStop();

    public V getView() {
        return this.mView;
    }

    public final void attachView(V v) {
        if (this.mView != v) {
            this.mView = v;
            Log.m1919i("PresenterLifecycle", name() + "#onCreate()");
            onCreate();
        }
    }

    public final void detachView() {
        if (this.mView != null) {
            Log.m1919i("PresenterLifecycle", name() + "#onDestroy()");
            onDestroy();
            this.mView = null;
        }
    }

    public final void activate() {
        if (!this.isActive) {
            this.isActive = true;
            Log.m1919i("PresenterLifecycle", name() + "#onStart()");
            onStart();
        }
    }

    public final void deactivate() {
        if (this.isActive) {
            this.isActive = false;
            Log.m1919i("PresenterLifecycle", name() + "#onStop()");
            onStop();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public boolean isActive() {
        return this.isActive;
    }

    private String name() {
        return getClass().getSimpleName();
    }
}
