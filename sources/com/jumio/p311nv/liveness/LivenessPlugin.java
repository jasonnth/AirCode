package com.jumio.p311nv.liveness;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.core.C4894ae;
import jumio.p317nv.core.C4951z;

/* renamed from: com.jumio.nv.liveness.LivenessPlugin */
public class LivenessPlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C4951z(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new C4894ae(context);
    }

    public <T> void populateData(Context context, T t) {
    }
}
