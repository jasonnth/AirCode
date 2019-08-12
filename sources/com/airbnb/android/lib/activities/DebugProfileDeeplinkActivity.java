package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.utils.DeepLinkUtils;

public class DebugProfileDeeplinkActivity extends AirActivity {
    private static final int ACTION_START_PROFILING = 0;
    private static final int ACTION_STOP_PROFILING = 1;
    private static final int BYTES_PER_MEGABYTE = 1048576;
    private static final long DEFAULT_BUFFER_SIZE_MEGABYTES = 120;
    private static final String DEFAULT_TRACE_PATH = "method_profile_trace";
    private static final String KEY_ACTION = "action";

    public static Intent intentForStartMethodProfiling(Context context) {
        return intent(context).putExtra("action", 0);
    }

    public static Intent intentForStopMethodProfiling(Context context) {
        return intent(context).putExtra("action", 1);
    }

    private static Intent intent(Context context) {
        return new Intent(context, DebugProfileDeeplinkActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        switch (intent.getIntExtra("action", 0)) {
            case 0:
                String tracePath = DeepLinkUtils.getParamAsString(intent, "trace_path");
                if (TextUtils.isEmpty(tracePath)) {
                    tracePath = DEFAULT_TRACE_PATH;
                }
                long bufferSize = DeepLinkUtils.getParamAsId(intent, "buffer_size");
                if (bufferSize == -1) {
                    bufferSize = DEFAULT_BUFFER_SIZE_MEGABYTES;
                }
                Debug.startMethodTracing(tracePath, (int) (bufferSize * 1048576));
                break;
            case 1:
                Debug.stopMethodTracing();
                break;
        }
        finish();
    }
}
