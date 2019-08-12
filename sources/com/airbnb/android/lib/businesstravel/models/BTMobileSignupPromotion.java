package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BTMobileSignupPromotion implements Parcelable {

    public static abstract class Builder {
        @JsonProperty("body")
        public abstract Builder body(String str);

        @JsonProperty("bold_text")
        public abstract Builder boldText(String str);

        public abstract BTMobileSignupPromotion build();

        @JsonProperty("title")
        public abstract Builder title(String str);
    }

    public abstract String body();

    public abstract String boldText();

    public abstract String title();

    public static Builder builder() {
        return new Builder();
    }
}
