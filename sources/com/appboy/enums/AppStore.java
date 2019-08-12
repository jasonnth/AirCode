package com.appboy.enums;

import com.appboy.models.IPutIntoJson;
import java.util.Locale;

public enum AppStore implements IPutIntoJson<String> {
    GOOGLE_PLAY_STORE,
    KINDLE_STORE;

    public String forJsonPut() {
        switch (this) {
            case GOOGLE_PLAY_STORE:
                return "Google Play Store";
            case KINDLE_STORE:
                return "Kindle Store";
            default:
                return null;
        }
    }

    public static String serverStringToEnumString(String serverString) {
        return serverString.replace(" ", "_").toUpperCase(Locale.US);
    }
}
