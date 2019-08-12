package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.BaseGraph;

public interface PhotoUploadManagerGraph extends BaseGraph {
    void inject(PhotoUploadRetryBroadcastReceiver photoUploadRetryBroadcastReceiver);

    void inject(PhotoUploadService photoUploadService);
}
