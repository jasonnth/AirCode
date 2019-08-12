package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.HomeLayout */
public abstract class HomeLayout implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.HomeLayout$Builder */
    public static abstract class Builder {
        public abstract HomeLayout build();

        @JsonProperty("floors")
        public abstract Builder floors(List<Floor> list);
    }

    public abstract List<Floor> floors();

    public static Builder builder() {
        return new Builder();
    }
}
