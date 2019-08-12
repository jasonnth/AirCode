package com.airbnb.android.airmapview;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.Locale;

public class AirMapType {
    private static final String ARG_FILE_NAME = "map_file_name";
    private static final String ARG_MAP_DOMAIN = "map_domain";
    private static final String ARG_MAP_URL = "map_url";
    private final String domain;
    private final String fileName;
    private final String mapUrl;

    public AirMapType(String fileName2, String mapUrl2, String domain2) {
        this.fileName = fileName2;
        this.mapUrl = mapUrl2;
        this.domain = domain2;
    }

    /* access modifiers changed from: 0000 */
    public String getFileName() {
        return this.fileName;
    }

    /* access modifiers changed from: 0000 */
    public String getMapUrl() {
        return this.mapUrl;
    }

    /* access modifiers changed from: 0000 */
    public String getDomain() {
        return this.domain;
    }

    public Bundle toBundle() {
        return toBundle(new Bundle());
    }

    public Bundle toBundle(Bundle bundle) {
        bundle.putString(ARG_MAP_DOMAIN, getDomain());
        bundle.putString(ARG_MAP_URL, getMapUrl());
        bundle.putString(ARG_FILE_NAME, getFileName());
        return bundle;
    }

    public static AirMapType fromBundle(Bundle bundle) {
        return new AirMapType(bundle.getString(ARG_FILE_NAME, ""), bundle.getString(ARG_MAP_URL, ""), bundle.getString(ARG_MAP_DOMAIN, ""));
    }

    public String getMapData(Resources resources) {
        return AirMapUtils.getStringFromFile(resources, this.fileName).replace("MAPURL", this.mapUrl).replace("LANGTOKEN", Locale.getDefault().getLanguage()).replace("REGIONTOKEN", Locale.getDefault().getCountry());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof AirMapType)) {
            return false;
        }
        AirMapType that = (AirMapType) o;
        if (this.domain == null ? that.domain != null : !this.domain.equals(that.domain)) {
            return false;
        }
        if (this.fileName == null ? that.fileName != null : !this.fileName.equals(that.fileName)) {
            return false;
        }
        if (this.mapUrl != null) {
            if (this.mapUrl.equals(that.mapUrl)) {
                return true;
            }
        } else if (that.mapUrl == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.fileName != null) {
            result = this.fileName.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.mapUrl != null) {
            i = this.mapUrl.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.domain != null) {
            i2 = this.domain.hashCode();
        }
        return i4 + i2;
    }
}
