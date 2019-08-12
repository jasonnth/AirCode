package com.airbnb.android.itinerary.data.models;

/* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_FlightEntryPointItem reason: invalid class name */
abstract class C$AutoValue_FlightEntryPointItem extends FlightEntryPointItem {
    private final String acceptText;
    private final String dismissText;

    /* renamed from: id */
    private final String f8329id;
    private final String title;

    /* renamed from: com.airbnb.android.itinerary.data.models.$AutoValue_FlightEntryPointItem$Builder */
    static final class Builder extends com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder {
        private String acceptText;
        private String dismissText;

        /* renamed from: id */
        private String f8330id;
        private String title;

        Builder() {
        }

        private Builder(FlightEntryPointItem source) {
            this.f8330id = source.mo57128id();
            this.title = source.title();
            this.acceptText = source.acceptText();
            this.dismissText = source.dismissText();
        }

        /* renamed from: id */
        public com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder mo57135id(String id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f8330id = id;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder title(String title2) {
            if (title2 == null) {
                throw new NullPointerException("Null title");
            }
            this.title = title2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder acceptText(String acceptText2) {
            if (acceptText2 == null) {
                throw new NullPointerException("Null acceptText");
            }
            this.acceptText = acceptText2;
            return this;
        }

        public com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder dismissText(String dismissText2) {
            if (dismissText2 == null) {
                throw new NullPointerException("Null dismissText");
            }
            this.dismissText = dismissText2;
            return this;
        }

        public FlightEntryPointItem build() {
            String missing = "";
            if (this.f8330id == null) {
                missing = missing + " id";
            }
            if (this.title == null) {
                missing = missing + " title";
            }
            if (this.acceptText == null) {
                missing = missing + " acceptText";
            }
            if (this.dismissText == null) {
                missing = missing + " dismissText";
            }
            if (missing.isEmpty()) {
                return new AutoValue_FlightEntryPointItem(this.f8330id, this.title, this.acceptText, this.dismissText);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_FlightEntryPointItem(String id, String title2, String acceptText2, String dismissText2) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f8329id = id;
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (acceptText2 == null) {
            throw new NullPointerException("Null acceptText");
        }
        this.acceptText = acceptText2;
        if (dismissText2 == null) {
            throw new NullPointerException("Null dismissText");
        }
        this.dismissText = dismissText2;
    }

    /* renamed from: id */
    public String mo57128id() {
        return this.f8329id;
    }

    public String title() {
        return this.title;
    }

    public String acceptText() {
        return this.acceptText;
    }

    public String dismissText() {
        return this.dismissText;
    }

    public String toString() {
        return "FlightEntryPointItem{id=" + this.f8329id + ", title=" + this.title + ", acceptText=" + this.acceptText + ", dismissText=" + this.dismissText + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FlightEntryPointItem)) {
            return false;
        }
        FlightEntryPointItem that = (FlightEntryPointItem) o;
        if (!this.f8329id.equals(that.mo57128id()) || !this.title.equals(that.title()) || !this.acceptText.equals(that.acceptText()) || !this.dismissText.equals(that.dismissText())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.f8329id.hashCode()) * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.acceptText.hashCode()) * 1000003) ^ this.dismissText.hashCode();
    }

    public com.airbnb.android.itinerary.data.models.FlightEntryPointItem.Builder toBuilder() {
        return new Builder(this);
    }
}
