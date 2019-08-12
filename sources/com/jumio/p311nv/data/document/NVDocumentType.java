package com.jumio.p311nv.data.document;

import android.content.Context;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.jumio.p311nv.data.NVStrings;
import com.miteksystems.misnap.params.MiSnapApiConstants;

/* renamed from: com.jumio.nv.data.document.NVDocumentType */
public enum NVDocumentType {
    PASSPORT,
    VISA,
    DRIVER_LICENSE,
    IDENTITY_CARD;

    public static NVDocumentType fromString(String str) {
        if (str.equals(OfficialIdActivity.DRIVERS_LICENSE_TYPE) || str.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return DRIVER_LICENSE;
        }
        if (str.equals(OfficialIdActivity.ID_TYPE)) {
            return IDENTITY_CARD;
        }
        if (str.equals("PASSPORT")) {
            return PASSPORT;
        }
        if (str.equals("VISA")) {
            return VISA;
        }
        return null;
    }

    public String toString() {
        String str = "";
        switch (this) {
            case PASSPORT:
                return "PASSPORT";
            case VISA:
                return "VISA";
            case DRIVER_LICENSE:
                return OfficialIdActivity.DRIVERS_LICENSE_TYPE;
            case IDENTITY_CARD:
                return OfficialIdActivity.ID_TYPE;
            default:
                return str;
        }
    }

    public String getLocalizedName(Context context) {
        String str = "";
        switch (this) {
            case PASSPORT:
                str = NVStrings.DOCUMENTTYPE_PASSPORT;
                break;
            case VISA:
                str = NVStrings.DOCUMENTTYPE_VISA;
                break;
            case DRIVER_LICENSE:
                str = NVStrings.DOCUMENTTYPE_DRIVERLICENSE;
                break;
            case IDENTITY_CARD:
                str = NVStrings.DOCUMENTTYPE_IDCARD;
                break;
        }
        return NVStrings.getExternalString(context, str);
    }
}
