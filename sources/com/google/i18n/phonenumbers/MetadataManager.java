package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

final class MetadataManager {
    private static final Map<Integer, PhoneMetadata> callingCodeToAlternateFormatsMap = Collections.synchronizedMap(new HashMap());
    private static final Set<Integer> countryCodeSet = AlternateFormatsCountryCodeSet.getCountryCodeSet();
    private static final Logger logger = Logger.getLogger(MetadataManager.class.getName());
    private static final Set<String> regionCodeSet = ShortNumbersRegionCodeSet.getRegionCodeSet();
    private static final Map<String, PhoneMetadata> regionCodeToShortNumberMetadataMap = Collections.synchronizedMap(new HashMap());

    private MetadataManager() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0021 A[SYNTHETIC, Splitter:B:17:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0042 A[SYNTHETIC, Splitter:B:30:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadataCollection loadMetadataAndCloseInput(java.io.InputStream r8) {
        /*
            r2 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0014 }
            r3.<init>(r8)     // Catch:{ IOException -> 0x0014 }
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadataCollection r1 = new com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadataCollection     // Catch:{ all -> 0x002f }
            r1.<init>()     // Catch:{ all -> 0x002f }
            r1.readExternal(r3)     // Catch:{ IOException -> 0x0025 }
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0013:
            return r1
        L_0x0014:
            r0 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x001e }
            java.lang.String r5 = "cannot load/parse metadata"
            r4.<init>(r5, r0)     // Catch:{ all -> 0x001e }
            throw r4     // Catch:{ all -> 0x001e }
        L_0x001e:
            r4 = move-exception
        L_0x001f:
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0024:
            throw r4
        L_0x0025:
            r0 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x002f }
            java.lang.String r5 = "cannot load/parse metadata"
            r4.<init>(r5, r0)     // Catch:{ all -> 0x002f }
            throw r4     // Catch:{ all -> 0x002f }
        L_0x002f:
            r4 = move-exception
            r2 = r3
            goto L_0x001f
        L_0x0032:
            r8.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x0013
        L_0x0036:
            r0 = move-exception
            java.util.logging.Logger r4 = logger
            java.util.logging.Level r5 = java.util.logging.Level.WARNING
            java.lang.String r6 = "error closing input stream (ignored)"
            r4.log(r5, r6, r0)
            goto L_0x0013
        L_0x0042:
            r8.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x0024
        L_0x0046:
            r0 = move-exception
            java.util.logging.Logger r5 = logger
            java.util.logging.Level r6 = java.util.logging.Level.WARNING
            java.lang.String r7 = "error closing input stream (ignored)"
            r5.log(r6, r7, r0)
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.MetadataManager.loadMetadataAndCloseInput(java.io.InputStream):com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadataCollection");
    }
}
