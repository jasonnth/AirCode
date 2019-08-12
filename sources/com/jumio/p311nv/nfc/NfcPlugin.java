package com.jumio.p311nv.nfc;

import android.content.Context;
import android.os.Bundle;
import com.jumio.commons.log.Log;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.nfc.C5126w;

/* renamed from: com.jumio.nv.nfc.NfcPlugin */
public class NfcPlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        Log.m1929w("NfcPlugin", "Caution! NFC reading is not done via the extraction client mechanism!");
        return null;
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return C5126w.m3828a(bundle);
    }

    public <T> void populateData(Context context, T t) {
    }
}
