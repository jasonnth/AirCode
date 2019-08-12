package com.paypal.android.sdk.data.collector;

import android.content.Context;
import com.paypal.android.sdk.onetouch.core.metadata.MetadataIdProvider;
import com.paypal.android.sdk.onetouch.core.metadata.MetadataIdProviderImpl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class PayPalDataCollector {
    /* access modifiers changed from: private */
    public static MetadataIdProvider sMetadataIdProvider;

    public static String getClientMetadataId(Context context) {
        return getClientMetadataId(context, null);
    }

    public static String getClientMetadataId(Context context, String pairingId) {
        return getClientMetadataId(context, InstallationIdentifier.getInstallationGUID(context), pairingId);
    }

    static String getClientMetadataId(Context context, String applicationGuid, String pairingId) {
        Map<String, Object> params;
        if (sMetadataIdProvider != null) {
            return sMetadataIdProvider.generatePairingId(pairingId);
        }
        if (context == null) {
            return "";
        }
        sMetadataIdProvider = new MetadataIdProviderImpl();
        if (pairingId != null) {
            params = new HashMap<>();
            params.put("RISK_MANAGER_PAIRING_ID", pairingId);
        } else {
            params = Collections.emptyMap();
        }
        String init = sMetadataIdProvider.init(context.getApplicationContext(), applicationGuid, params);
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            public void run() {
                PayPalDataCollector.sMetadataIdProvider.flush();
            }
        });
        return init;
    }
}
