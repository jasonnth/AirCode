package com.airbnb.android.p011p3.models;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_RoomPhoto reason: invalid class name */
abstract class C$AutoValue_RoomPhoto extends RoomPhoto {
    private final String dominantSaturatedColor;

    /* renamed from: id */
    private final long f10656id;
    private final String largeUrl;
    private final String previewEncodedPng;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_RoomPhoto$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.RoomPhoto.Builder {
        private String dominantSaturatedColor;

        /* renamed from: id */
        private Long f10657id;
        private String largeUrl;
        private String previewEncodedPng;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.p011p3.models.RoomPhoto.Builder mo11106id(long id) {
            this.f10657id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.p011p3.models.RoomPhoto.Builder largeUrl(String largeUrl2) {
            if (largeUrl2 == null) {
                throw new NullPointerException("Null largeUrl");
            }
            this.largeUrl = largeUrl2;
            return this;
        }

        public com.airbnb.android.p011p3.models.RoomPhoto.Builder previewEncodedPng(String previewEncodedPng2) {
            if (previewEncodedPng2 == null) {
                throw new NullPointerException("Null previewEncodedPng");
            }
            this.previewEncodedPng = previewEncodedPng2;
            return this;
        }

        public com.airbnb.android.p011p3.models.RoomPhoto.Builder dominantSaturatedColor(String dominantSaturatedColor2) {
            if (dominantSaturatedColor2 == null) {
                throw new NullPointerException("Null dominantSaturatedColor");
            }
            this.dominantSaturatedColor = dominantSaturatedColor2;
            return this;
        }

        public RoomPhoto build() {
            String missing = "";
            if (this.f10657id == null) {
                missing = missing + " id";
            }
            if (this.largeUrl == null) {
                missing = missing + " largeUrl";
            }
            if (this.previewEncodedPng == null) {
                missing = missing + " previewEncodedPng";
            }
            if (this.dominantSaturatedColor == null) {
                missing = missing + " dominantSaturatedColor";
            }
            if (missing.isEmpty()) {
                return new AutoValue_RoomPhoto(this.f10657id.longValue(), this.largeUrl, this.previewEncodedPng, this.dominantSaturatedColor);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_RoomPhoto(long id, String largeUrl2, String previewEncodedPng2, String dominantSaturatedColor2) {
        this.f10656id = id;
        if (largeUrl2 == null) {
            throw new NullPointerException("Null largeUrl");
        }
        this.largeUrl = largeUrl2;
        if (previewEncodedPng2 == null) {
            throw new NullPointerException("Null previewEncodedPng");
        }
        this.previewEncodedPng = previewEncodedPng2;
        if (dominantSaturatedColor2 == null) {
            throw new NullPointerException("Null dominantSaturatedColor");
        }
        this.dominantSaturatedColor = dominantSaturatedColor2;
    }

    /* renamed from: id */
    public long mo11101id() {
        return this.f10656id;
    }

    public String largeUrl() {
        return this.largeUrl;
    }

    public String previewEncodedPng() {
        return this.previewEncodedPng;
    }

    public String dominantSaturatedColor() {
        return this.dominantSaturatedColor;
    }

    public String toString() {
        return "RoomPhoto{id=" + this.f10656id + ", largeUrl=" + this.largeUrl + ", previewEncodedPng=" + this.previewEncodedPng + ", dominantSaturatedColor=" + this.dominantSaturatedColor + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RoomPhoto)) {
            return false;
        }
        RoomPhoto that = (RoomPhoto) o;
        if (this.f10656id != that.mo11101id() || !this.largeUrl.equals(that.largeUrl()) || !this.previewEncodedPng.equals(that.previewEncodedPng()) || !this.dominantSaturatedColor.equals(that.dominantSaturatedColor())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((int) (((long) (1 * 1000003)) ^ ((this.f10656id >>> 32) ^ this.f10656id))) * 1000003) ^ this.largeUrl.hashCode()) * 1000003) ^ this.previewEncodedPng.hashCode()) * 1000003) ^ this.dominantSaturatedColor.hashCode();
    }
}
