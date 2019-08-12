package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;
import java.util.List;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_FreeTimeItem reason: invalid class name */
abstract class C$AutoValue_FreeTimeItem extends FreeTimeItem {
    private final String confirmationCode;
    private final AirDateTime endsAt;
    private final AirDateTime startsAt;
    private final List<Suggestion> suggestions;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_FreeTimeItem$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder {
        private String confirmationCode;
        private AirDateTime endsAt;
        private AirDateTime startsAt;
        private List<Suggestion> suggestions;

        Builder() {
        }

        private Builder(FreeTimeItem source) {
            this.startsAt = source.startsAt();
            this.endsAt = source.endsAt();
            this.confirmationCode = source.confirmationCode();
            this.suggestions = source.suggestions();
        }

        public com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder startsAt(AirDateTime startsAt2) {
            if (startsAt2 == null) {
                throw new NullPointerException("Null startsAt");
            }
            this.startsAt = startsAt2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder endsAt(AirDateTime endsAt2) {
            if (endsAt2 == null) {
                throw new NullPointerException("Null endsAt");
            }
            this.endsAt = endsAt2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder confirmationCode(String confirmationCode2) {
            if (confirmationCode2 == null) {
                throw new NullPointerException("Null confirmationCode");
            }
            this.confirmationCode = confirmationCode2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder suggestions(List<Suggestion> suggestions2) {
            this.suggestions = suggestions2;
            return this;
        }

        public FreeTimeItem build() {
            String missing = "";
            if (this.startsAt == null) {
                missing = missing + " startsAt";
            }
            if (this.endsAt == null) {
                missing = missing + " endsAt";
            }
            if (this.confirmationCode == null) {
                missing = missing + " confirmationCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_FreeTimeItem(this.startsAt, this.endsAt, this.confirmationCode, this.suggestions);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_FreeTimeItem(AirDateTime startsAt2, AirDateTime endsAt2, String confirmationCode2, List<Suggestion> suggestions2) {
        if (startsAt2 == null) {
            throw new NullPointerException("Null startsAt");
        }
        this.startsAt = startsAt2;
        if (endsAt2 == null) {
            throw new NullPointerException("Null endsAt");
        }
        this.endsAt = endsAt2;
        if (confirmationCode2 == null) {
            throw new NullPointerException("Null confirmationCode");
        }
        this.confirmationCode = confirmationCode2;
        this.suggestions = suggestions2;
    }

    public AirDateTime startsAt() {
        return this.startsAt;
    }

    public AirDateTime endsAt() {
        return this.endsAt;
    }

    public String confirmationCode() {
        return this.confirmationCode;
    }

    public List<Suggestion> suggestions() {
        return this.suggestions;
    }

    public String toString() {
        return "FreeTimeItem{startsAt=" + this.startsAt + ", endsAt=" + this.endsAt + ", confirmationCode=" + this.confirmationCode + ", suggestions=" + this.suggestions + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FreeTimeItem)) {
            return false;
        }
        FreeTimeItem that = (FreeTimeItem) o;
        if (this.startsAt.equals(that.startsAt()) && this.endsAt.equals(that.endsAt()) && this.confirmationCode.equals(that.confirmationCode())) {
            if (this.suggestions == null) {
                if (that.suggestions() == null) {
                    return true;
                }
            } else if (this.suggestions.equals(that.suggestions())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.startsAt.hashCode()) * 1000003) ^ this.endsAt.hashCode()) * 1000003) ^ this.confirmationCode.hashCode()) * 1000003) ^ (this.suggestions == null ? 0 : this.suggestions.hashCode());
    }

    public com.airbnb.android.itinerary.data.models.FreeTimeItem.Builder toBuilder() {
        return new Builder(this);
    }
}
