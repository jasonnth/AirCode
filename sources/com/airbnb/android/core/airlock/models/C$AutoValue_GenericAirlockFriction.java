package com.airbnb.android.core.airlock.models;

/* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_GenericAirlockFriction reason: invalid class name */
abstract class C$AutoValue_GenericAirlockFriction extends GenericAirlockFriction {
    private final double version;

    /* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_GenericAirlockFriction$Builder */
    static final class Builder extends com.airbnb.android.core.airlock.models.GenericAirlockFriction.Builder {
        private Double version;

        Builder() {
        }

        public com.airbnb.android.core.airlock.models.GenericAirlockFriction.Builder version(double version2) {
            this.version = Double.valueOf(version2);
            return this;
        }

        public GenericAirlockFriction build() {
            String missing = "";
            if (this.version == null) {
                missing = missing + " version";
            }
            if (missing.isEmpty()) {
                return new AutoValue_GenericAirlockFriction(this.version.doubleValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_GenericAirlockFriction(double version2) {
        this.version = version2;
    }

    public double version() {
        return this.version;
    }

    public String toString() {
        return "GenericAirlockFriction{version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GenericAirlockFriction)) {
            return false;
        }
        if (Double.doubleToLongBits(this.version) != Double.doubleToLongBits(((GenericAirlockFriction) o).version())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (1 * 1000003)) ^ ((Double.doubleToLongBits(this.version) >>> 32) ^ Double.doubleToLongBits(this.version)));
    }
}
