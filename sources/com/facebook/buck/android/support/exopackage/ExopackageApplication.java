package com.facebook.buck.android.support.exopackage;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.buck.android.support.exopackage.ApplicationLike;

public abstract class ExopackageApplication<T extends ApplicationLike> extends Application {
    private static final int NATIVE_LIBRARY_MASK = 2;
    private static final int SECONDARY_DEX_MASK = 1;
    private T delegate;
    private final String delegateClassName;
    private final int exopackageFlags;

    protected ExopackageApplication(int exopackageFlags2) {
        this(DefaultApplicationLike.class.getName(), exopackageFlags2);
    }

    protected ExopackageApplication(String delegateClassName2, int exopackageFlags2) {
        this.delegateClassName = delegateClassName2;
        this.exopackageFlags = exopackageFlags2;
    }

    private boolean isExopackageEnabledForSecodaryDex() {
        return (this.exopackageFlags & 1) != 0;
    }

    private boolean isExopackageEnabledForNativeLibraries() {
        return (this.exopackageFlags & 2) != 0;
    }

    private T createDelegate() {
        if (isExopackageEnabledForSecodaryDex()) {
            ExopackageDexLoader.loadExopackageJars(this);
        }
        if (isExopackageEnabledForNativeLibraries()) {
            ExopackageSoLoader.init(this);
        }
        try {
            return (ApplicationLike) Class.forName(this.delegateClassName).getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void ensureDelegate() {
        if (this.delegate == null) {
            this.delegate = createDelegate();
        }
    }

    /* access modifiers changed from: protected */
    public void onBaseContextAttached() {
    }

    public final T getDelegateIfPresent() {
        return this.delegate;
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        onBaseContextAttached();
        ensureDelegate();
    }

    public final void onCreate() {
        super.onCreate();
        ensureDelegate();
        this.delegate.onCreate();
    }

    public final void onTerminate() {
        super.onTerminate();
        if (this.delegate != null) {
            this.delegate.onTerminate();
        }
    }

    public final void onLowMemory() {
        super.onLowMemory();
        if (this.delegate != null) {
            this.delegate.onLowMemory();
        }
    }

    @TargetApi(14)
    public final void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (this.delegate != null) {
            this.delegate.onTrimMemory(level);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.delegate != null) {
            this.delegate.onConfigurationChanged(newConfig);
        }
    }
}
