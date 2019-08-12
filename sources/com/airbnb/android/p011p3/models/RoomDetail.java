package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.RoomDetail */
public abstract class RoomDetail implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.RoomDetail$Builder */
    public static abstract class Builder {
        public abstract RoomDetail build();

        @JsonProperty("caption")
        public abstract Builder caption(String str);

        @JsonProperty("photo")
        public abstract Builder photo(RoomPhoto roomPhoto);
    }

    public abstract String caption();

    public abstract RoomPhoto photo();

    public static Builder builder() {
        return new Builder();
    }
}
