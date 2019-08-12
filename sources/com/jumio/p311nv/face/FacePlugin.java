package com.jumio.p311nv.face;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.core.C4938r;
import jumio.p317nv.core.C4941s;

/* renamed from: com.jumio.nv.face.FacePlugin */
public class FacePlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C4938r(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new C4941s(context);
    }

    public <T> void populateData(Context context, T t) {
    }
}
