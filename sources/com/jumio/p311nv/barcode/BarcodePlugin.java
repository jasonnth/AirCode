package com.jumio.p311nv.barcode;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.p311nv.barcode.overlay.BarcodeOverlay;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.barcode.C4881b;

/* renamed from: com.jumio.nv.barcode.BarcodePlugin */
public class BarcodePlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C4881b(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new BarcodeOverlay(context);
    }

    public <T> void populateData(Context context, T t) {
    }
}
