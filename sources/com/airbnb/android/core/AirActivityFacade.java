package com.airbnb.android.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.ActionBar;

public interface AirActivityFacade {
    void finish();

    Context getBaseContext();

    ActionBar getSupportActionBar();

    void runOnUiThread(Runnable runnable);

    void setResult(int i, Intent intent);

    void startActivityForResult(Intent intent, int i);

    void startActivityForResult(Intent intent, int i, Bundle bundle);
}
