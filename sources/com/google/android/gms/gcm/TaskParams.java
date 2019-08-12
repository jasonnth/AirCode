package com.google.android.gms.gcm;

import android.net.Uri;
import android.os.Bundle;
import java.util.List;

public class TaskParams {
    private final Bundle extras;
    private final String tag;
    private final List<Uri> zzbhf;

    public TaskParams(String str, Bundle bundle, List<Uri> list) {
        this.tag = str;
        this.extras = bundle;
        this.zzbhf = list;
    }
}
