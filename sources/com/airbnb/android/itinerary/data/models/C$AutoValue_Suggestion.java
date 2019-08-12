package com.airbnb.android.itinerary.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_Suggestion reason: invalid class name */
abstract class C$AutoValue_Suggestion extends Suggestion {

    /* renamed from: id */
    private final long f1156id;
    private final String pictureUrl;
    private final String title;
    private final SuggestionType type;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_Suggestion$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.Suggestion.Builder {

        /* renamed from: id */
        private Long f1157id;
        private String pictureUrl;
        private String title;
        private SuggestionType type;

        Builder() {
        }

        private Builder(Suggestion source) {
            this.f1157id = Long.valueOf(source.mo10321id());
            this.title = source.title();
            this.pictureUrl = source.pictureUrl();
            this.type = source.type();
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.Suggestion.Builder mo10328id(long id) {
            this.f1157id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.itinerary.data.models.Suggestion.Builder title(String title2) {
            this.title = title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.Suggestion.Builder pictureUrl(String pictureUrl2) {
            this.pictureUrl = pictureUrl2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.Suggestion.Builder type(SuggestionType type2) {
            this.type = type2;
            return this;
        }

        public Suggestion build() {
            String missing = "";
            if (this.f1157id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Suggestion(this.f1157id.longValue(), this.title, this.pictureUrl, this.type);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Suggestion(long id, String title2, String pictureUrl2, SuggestionType type2) {
        this.f1156id = id;
        this.title = title2;
        this.pictureUrl = pictureUrl2;
        this.type = type2;
    }

    @JsonProperty
    /* renamed from: id */
    public long mo10321id() {
        return this.f1156id;
    }

    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @JsonProperty("picture_url")
    public String pictureUrl() {
        return this.pictureUrl;
    }

    @JsonProperty
    public SuggestionType type() {
        return this.type;
    }

    public String toString() {
        return "Suggestion{id=" + this.f1156id + ", title=" + this.title + ", pictureUrl=" + this.pictureUrl + ", type=" + this.type + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Suggestion)) {
            return false;
        }
        Suggestion that = (Suggestion) o;
        if (this.f1156id == that.mo10321id() && (this.title != null ? this.title.equals(that.title()) : that.title() == null) && (this.pictureUrl != null ? this.pictureUrl.equals(that.pictureUrl()) : that.pictureUrl() == null)) {
            if (this.type == null) {
                if (that.type() == null) {
                    return true;
                }
            } else if (this.type.equals(that.type())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((int) (((long) (1 * 1000003)) ^ ((this.f1156id >>> 32) ^ this.f1156id))) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.pictureUrl == null ? 0 : this.pictureUrl.hashCode())) * 1000003;
        if (this.type != null) {
            i = this.type.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.itinerary.data.models.Suggestion.Builder toBuilder() {
        return new Builder(this);
    }
}
