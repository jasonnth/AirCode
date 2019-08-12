package com.airbnb.android.core.models;

/* renamed from: com.airbnb.android.core.models.$AutoValue_CheckinArguments reason: invalid class name */
abstract class C$AutoValue_CheckinArguments extends CheckinArguments {
    private final long listingId;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_CheckinArguments$Builder */
    static final class Builder extends com.airbnb.android.core.models.CheckinArguments.Builder {
        private Long listingId;

        Builder() {
        }

        public com.airbnb.android.core.models.CheckinArguments.Builder listingId(long listingId2) {
            this.listingId = Long.valueOf(listingId2);
            return this;
        }

        public CheckinArguments build() {
            String missing = "";
            if (this.listingId == null) {
                missing = missing + " listingId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CheckinArguments(this.listingId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_CheckinArguments(long listingId2) {
        this.listingId = listingId2;
    }

    public long listingId() {
        return this.listingId;
    }

    public String toString() {
        return "CheckinArguments{listingId=" + this.listingId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CheckinArguments)) {
            return false;
        }
        if (this.listingId != ((CheckinArguments) o).listingId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (1 * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId));
    }
}
