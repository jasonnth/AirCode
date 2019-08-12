package com.jumio.p311nv.barcode.overlay;

import android.content.Context;
import android.graphics.Rect;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.p311nv.NVOverlay;
import com.jumio.p311nv.NVOverlay.NVOverlayConfig;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;

/* renamed from: com.jumio.nv.barcode.overlay.BarcodeOverlay */
public class BarcodeOverlay extends NVOverlay {
    private Rect overlayBounds;
    private NVOverlayConfig overlayConfig = new NVOverlayConfig();

    public BarcodeOverlay(Context context) {
        super(context);
    }

    public void update(ExtractionUpdate extractionUpdate) {
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        super.calculate(documentScanMode, i, i2);
        int i3 = (int) ((((float) i) - (((float) i) * 0.9f)) / 2.0f);
        int i4 = (int) ((((float) i2) - (((float) i2) * 0.95f)) / 2.0f);
        this.overlayBounds = new Rect(i3, i4, i - i3, i2 - (i4 * 3));
    }

    public Rect getOverlayBounds() {
        return this.overlayBounds;
    }

    public NVOverlayConfig getNetverifyOverlayConfiguration(Context context) {
        this.overlayConfig.drawBrackets = false;
        this.overlayConfig.dimBackground = false;
        return this.overlayConfig;
    }
}
