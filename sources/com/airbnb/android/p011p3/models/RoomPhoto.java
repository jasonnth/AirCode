package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.RoomPhoto */
public abstract class RoomPhoto implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.RoomPhoto$Builder */
    public static abstract class Builder {
        public abstract RoomPhoto build();

        @JsonProperty("dominant_saturated_color")
        public abstract Builder dominantSaturatedColor(String str);

        @JsonProperty("id")
        /* renamed from: id */
        public abstract Builder mo11106id(long j);

        @JsonProperty("large")
        public abstract Builder largeUrl(String str);

        @JsonProperty("preview_encoded_png")
        public abstract Builder previewEncodedPng(String str);
    }

    public abstract String dominantSaturatedColor();

    /* renamed from: id */
    public abstract long mo11101id();

    public abstract String largeUrl();

    public abstract String previewEncodedPng();

    public static Builder builder() {
        return new Builder();
    }
}
