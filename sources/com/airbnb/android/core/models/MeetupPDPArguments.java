package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class MeetupPDPArguments implements Parcelable {

    public static abstract class Builder {
        public abstract MeetupPDPArguments build();

        @JsonProperty("meetup_activity_id")
        /* renamed from: id */
        public abstract Builder mo9030id(Long l);
    }

    /* renamed from: id */
    public abstract Long mo9028id();

    public static Builder builder() {
        return new Builder();
    }
}
