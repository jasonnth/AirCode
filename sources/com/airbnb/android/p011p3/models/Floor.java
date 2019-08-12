package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.Floor */
public abstract class Floor implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.Floor$Builder */
    public static abstract class Builder {
        public abstract Floor build();

        @JsonProperty("id")
        /* renamed from: id */
        public abstract Builder mo11018id(long j);

        @JsonProperty("name")
        public abstract Builder name(String str);

        @JsonProperty("rooms")
        public abstract Builder rooms(List<Room> list);
    }

    /* renamed from: id */
    public abstract long mo11014id();

    public abstract String name();

    public abstract List<Room> rooms();

    public static Builder builder() {
        return new Builder();
    }
}
