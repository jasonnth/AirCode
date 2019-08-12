package com.airbnb.android.core.airlock.models;

import java.util.Map;

/* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_FrictionInitData reason: invalid class name */
abstract class C$AutoValue_FrictionInitData extends FrictionInitData {
    private final Map<String, BaseAirlockFriction> frictionMap;

    /* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_FrictionInitData$Builder */
    static final class Builder extends com.airbnb.android.core.airlock.models.FrictionInitData.Builder {
        private Map<String, BaseAirlockFriction> frictionMap;

        Builder() {
        }

        public com.airbnb.android.core.airlock.models.FrictionInitData.Builder frictionMap(Map<String, BaseAirlockFriction> frictionMap2) {
            if (frictionMap2 == null) {
                throw new NullPointerException("Null frictionMap");
            }
            this.frictionMap = frictionMap2;
            return this;
        }

        public FrictionInitData build() {
            String missing = "";
            if (this.frictionMap == null) {
                missing = missing + " frictionMap";
            }
            if (missing.isEmpty()) {
                return new AutoValue_FrictionInitData(this.frictionMap);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_FrictionInitData(Map<String, BaseAirlockFriction> frictionMap2) {
        if (frictionMap2 == null) {
            throw new NullPointerException("Null frictionMap");
        }
        this.frictionMap = frictionMap2;
    }

    public Map<String, BaseAirlockFriction> frictionMap() {
        return this.frictionMap;
    }

    public String toString() {
        return "FrictionInitData{frictionMap=" + this.frictionMap + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrictionInitData)) {
            return false;
        }
        return this.frictionMap.equals(((FrictionInitData) o).frictionMap());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.frictionMap.hashCode();
    }
}
