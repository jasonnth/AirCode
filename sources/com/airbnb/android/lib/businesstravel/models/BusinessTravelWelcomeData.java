package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessTravelWelcomeData implements Parcelable {

    public static abstract class Builder {
        public abstract BusinessTravelWelcomeData build();

        @JsonProperty("button_text")
        public abstract Builder buttonText(String str);

        @JsonProperty("legal_disclaimer")
        public abstract Builder legalDisclaimer(LegalDisclaimer legalDisclaimer);

        @JsonProperty("marquee_body")
        public abstract Builder marqueeBody(String str);

        @JsonProperty("marquee_img_url")
        public abstract Builder marqueeImageUrl(String str);

        @JsonProperty("marquee_title")
        public abstract Builder marqueeTitle(String str);
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class LegalDisclaimer implements Parcelable {

        public static abstract class Builder {
            public abstract LegalDisclaimer build();

            @JsonProperty("fullText")
            public abstract Builder fullText(String str);

            @JsonProperty("links")
            public abstract Builder links(List<Link> list);
        }

        public abstract String fullText();

        public abstract List<Link> links();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class Link implements Parcelable {

        public static abstract class Builder {
            public abstract Link build();

            @JsonProperty("text")
            public abstract Builder text(String str);

            @JsonProperty("url")
            public abstract Builder url(String str);
        }

        public abstract String text();

        public abstract String url();

        public static Builder builder() {
            return new Builder();
        }
    }

    public abstract String buttonText();

    public abstract LegalDisclaimer legalDisclaimer();

    public abstract String marqueeBody();

    public abstract String marqueeImageUrl();

    public abstract String marqueeTitle();

    public static Builder builder() {
        return new Builder();
    }
}
