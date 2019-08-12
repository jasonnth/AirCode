package com.appboy.enums;

import com.appboy.models.IPutIntoJson;

public enum Gender implements IPutIntoJson<String> {
    MALE,
    FEMALE;

    public String forJsonPut() {
        switch (this) {
            case MALE:
                return "m";
            case FEMALE:
                return "f";
            default:
                return null;
        }
    }
}
