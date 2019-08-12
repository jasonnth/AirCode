package com.airbnb.android.p011p3;

import com.airbnb.android.p011p3.models.HomeLayout;

/* renamed from: com.airbnb.android.p3.HomeTourController */
public interface HomeTourController {
    HomeLayout getHomeLayout();

    long getSelectedRoomId();

    void onImageClicked(int i);
}
