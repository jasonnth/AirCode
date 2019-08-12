package com.airbnb.android.core.react;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.view.View;
import p032rx.functions.Action0;

public interface AirReactInstanceManager {
    void addReactInstanceEventListener(Action0 action0);

    void createReactContextInBackground();

    Context getCurrentReactContext();

    CountingIdlingResource getFirstRenderIdlingResources();

    boolean isSuccessfullyInitialized();

    void onBackPressed();

    void onHostDestroy(Activity activity);

    void onHostPause(Activity activity);

    void onHostResume(Activity activity, Object obj);

    void preloadScreen(String str);

    void setDevSupportEnabled(boolean z);

    void showDevOptionsDialog();

    void signalFirstRenderComplete(Activity activity);

    void signalWaitingForFirstRender(Activity activity);

    void startReactApplication(View view, String str, Bundle bundle);
}
