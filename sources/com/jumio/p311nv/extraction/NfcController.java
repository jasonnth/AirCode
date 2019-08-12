package com.jumio.p311nv.extraction;

import android.app.Activity;
import android.content.Intent;
import java.io.Serializable;

/* renamed from: com.jumio.nv.extraction.NfcController */
public interface NfcController extends Serializable {
    boolean consumeIntent(int i, Intent intent);

    boolean hasNfcFeature();

    boolean hasRootCertificate(String str);

    boolean isNfcEnabled();

    void pause(Activity activity);

    void setEnabled(boolean z);

    void setTagAccess(Object obj);

    void start(Activity activity);

    void stop();
}
