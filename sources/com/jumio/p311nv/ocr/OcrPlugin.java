package com.jumio.p311nv.ocr;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.ocr.C5147b;
import jumio.p317nv.ocr.C5156g;

/* renamed from: com.jumio.nv.ocr.OcrPlugin */
public class OcrPlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C5147b(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new C5156g(context);
    }

    public <T> void populateData(Context context, T t) {
    }
}
