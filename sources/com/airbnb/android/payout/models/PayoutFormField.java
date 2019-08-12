package com.airbnb.android.payout.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class PayoutFormField implements Parcelable {

    public static abstract class Builder {
        public abstract PayoutFormField build();

        @JsonProperty("confirm_field")
        public abstract Builder confirmField(boolean z);

        @JsonProperty("error_text")
        public abstract Builder errorText(String str);

        @JsonProperty("field_type")
        public abstract Builder fieldType(String str);

        @JsonProperty("helper_text")
        public abstract Builder helperText(String str);

        @JsonProperty("hint_text")
        public abstract Builder hintText(String str);

        @JsonProperty("label")
        public abstract Builder label(String str);

        @JsonProperty("max_length")
        public abstract Builder maxLength(Integer num);

        @JsonProperty("min_length")
        public abstract Builder minLength(Integer num);

        @JsonProperty("name")
        public abstract Builder name(String str);

        @JsonProperty("regex")
        public abstract Builder regex(String str);

        @JsonProperty("required")
        public abstract Builder required(boolean z);

        public abstract Builder values(List<String> list);
    }

    public abstract boolean confirmField();

    public abstract String errorText();

    public abstract String fieldType();

    public abstract String helperText();

    public abstract String hintText();

    public abstract String label();

    public abstract Integer maxLength();

    public abstract Integer minLength();

    public abstract String name();

    public abstract String regex();

    public abstract boolean required();

    public abstract List<String> values();

    public static Builder builder() {
        return new Builder();
    }
}
