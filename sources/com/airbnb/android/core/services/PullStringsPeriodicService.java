package com.airbnb.android.core.services;

import com.airbnb.android.core.utils.PullStringsDownloader;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

public class PullStringsPeriodicService extends GcmTaskService {
    public int onRunTask(TaskParams taskParams) {
        new PullStringsDownloader(this).pullStrings();
        return 0;
    }
}
