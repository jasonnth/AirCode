package com.airbnb.android.p011p3.models;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_RoomDetail reason: invalid class name */
abstract class C$AutoValue_RoomDetail extends RoomDetail {
    private final String caption;
    private final RoomPhoto photo;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_RoomDetail$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.RoomDetail.Builder {
        private String caption;
        private RoomPhoto photo;

        Builder() {
        }

        public com.airbnb.android.p011p3.models.RoomDetail.Builder photo(RoomPhoto photo2) {
            if (photo2 == null) {
                throw new NullPointerException("Null photo");
            }
            this.photo = photo2;
            return this;
        }

        public com.airbnb.android.p011p3.models.RoomDetail.Builder caption(String caption2) {
            if (caption2 == null) {
                throw new NullPointerException("Null caption");
            }
            this.caption = caption2;
            return this;
        }

        public RoomDetail build() {
            String missing = "";
            if (this.photo == null) {
                missing = missing + " photo";
            }
            if (this.caption == null) {
                missing = missing + " caption";
            }
            if (missing.isEmpty()) {
                return new AutoValue_RoomDetail(this.photo, this.caption);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_RoomDetail(RoomPhoto photo2, String caption2) {
        if (photo2 == null) {
            throw new NullPointerException("Null photo");
        }
        this.photo = photo2;
        if (caption2 == null) {
            throw new NullPointerException("Null caption");
        }
        this.caption = caption2;
    }

    public RoomPhoto photo() {
        return this.photo;
    }

    public String caption() {
        return this.caption;
    }

    public String toString() {
        return "RoomDetail{photo=" + this.photo + ", caption=" + this.caption + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RoomDetail)) {
            return false;
        }
        RoomDetail that = (RoomDetail) o;
        if (!this.photo.equals(that.photo()) || !this.caption.equals(that.caption())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.photo.hashCode()) * 1000003) ^ this.caption.hashCode();
    }
}
