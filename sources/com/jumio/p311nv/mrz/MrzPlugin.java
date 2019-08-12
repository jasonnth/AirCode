package com.jumio.p311nv.mrz;

import android.content.Context;
import android.os.Bundle;
import com.jumio.core.overlay.Overlay;
import com.jumio.core.plugins.Plugin;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.extraction.ExtractionClient;
import jumio.p317nv.mrz.C4956a;
import jumio.p317nv.mrz.C4963c;
import jumio.p317nv.mrz.C4965d;

/* renamed from: com.jumio.nv.mrz.MrzPlugin */
public class MrzPlugin implements Plugin {
    public ExtractionClient getExtractionClient(Context context) {
        return new C4956a(context);
    }

    public Overlay getOverlay(Context context, Bundle bundle) {
        return new C4965d(context);
    }

    public <T> void populateData(Context context, T t) {
        C4963c cVar = (C4963c) DataAccess.load(context, C4963c.class);
        if (t instanceof NetverifyDocumentData) {
            cVar.populateData((NetverifyDocumentData) t);
        }
    }
}
