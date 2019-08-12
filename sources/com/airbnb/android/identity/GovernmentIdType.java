package com.airbnb.android.identity;

import com.jumio.p311nv.data.document.NVDocumentType;
import java.util.HashMap;
import java.util.Map;

public enum GovernmentIdType {
    PASSPORT("passport", NVDocumentType.PASSPORT),
    VISA("visa", NVDocumentType.VISA),
    DRIVING_LICENSE("drivers_license", NVDocumentType.DRIVER_LICENSE),
    ID_CARD("id_card", NVDocumentType.IDENTITY_CARD),
    UNKNOWN("unknown", null);
    
    private static final Map<NVDocumentType, GovernmentIdType> docTypeGovIdTypeMap = null;
    public final NVDocumentType documentType;
    public final String trackingEventData;

    static {
        int i;
        GovernmentIdType[] values;
        docTypeGovIdTypeMap = new HashMap();
        for (GovernmentIdType idType : values()) {
            docTypeGovIdTypeMap.put(idType.documentType, idType);
        }
    }

    private GovernmentIdType(String trackingEventData2, NVDocumentType documentType2) {
        this.trackingEventData = trackingEventData2;
        this.documentType = documentType2;
    }

    public static GovernmentIdType getGovernmentIdType(NVDocumentType nvDocumentType) {
        return (GovernmentIdType) docTypeGovIdTypeMap.get(nvDocumentType);
    }
}
