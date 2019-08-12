package com.jumio.p311nv.linefinder;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.core.C4944u;
import jumio.p317nv.core.C4948x;

/* renamed from: com.jumio.nv.linefinder.LineFinderPlugin */
public class LineFinderPlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C4944u(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new C4948x(context);
    }

    public <T> void populateData(Context context, T t) {
    }
}
