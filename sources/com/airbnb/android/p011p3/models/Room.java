package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.Room */
public abstract class Room implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.Room$Builder */
    public static abstract class Builder {
        public abstract Room build();

        @JsonProperty("details")
        public abstract Builder details(List<RoomDetail> list);

        @JsonProperty("highlights")
        public abstract Builder highlights(List<String> list);

        @JsonProperty("id")
        /* renamed from: id */
        public abstract Builder mo11092id(long j);

        @JsonProperty("name")
        public abstract Builder name(String str);

        @JsonProperty("photos")
        public abstract Builder photos(List<RoomPhoto> list);
    }

    public abstract List<RoomDetail> details();

    public abstract List<String> highlights();

    /* renamed from: id */
    public abstract long mo11086id();

    public abstract String name();

    public abstract List<RoomPhoto> photos();

    public static Builder builder() {
        return new Builder();
    }
}
