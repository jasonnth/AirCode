package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class ReactAuthenticatedWebViewArguments implements Parcelable {

    public static abstract class Builder {
        public abstract ReactAuthenticatedWebViewArguments build();

        @JsonProperty("url")
        public abstract Builder url(String str);
    }

    public abstract String getUrl();

    public static Builder builder() {
        return new Builder();
    }
}
