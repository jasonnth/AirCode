package com.airbnb.android.core.interfaces;

public interface UpdateRequestListener {
    void onUpdateError(String str);

    void onUpdateStarted();

    void onUpdated();
}
