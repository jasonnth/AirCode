package com.airbnb.p027n2.primitives.messaging;

import android.view.View;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageInputListener */
public interface MessageInputListener {
    void onCameraIconClicked();

    void onCombinationCameraIconClicked();

    void onPhotoIconClicked();

    void onSavedMessageIconClicked();

    void onSendButtonClicked();

    void onTextIconClicked(View view);
}
