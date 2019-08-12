package com.airbnb.android.core.models;

/* renamed from: com.airbnb.android.core.models.$AutoValue_MeetupPDPArguments reason: invalid class name */
abstract class C$AutoValue_MeetupPDPArguments extends MeetupPDPArguments {

    /* renamed from: id */
    private final Long f8466id;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_MeetupPDPArguments$Builder */
    static final class Builder extends com.airbnb.android.core.models.MeetupPDPArguments.Builder {

        /* renamed from: id */
        private Long f8467id;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.core.models.MeetupPDPArguments.Builder mo9030id(Long id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f8467id = id;
            return this;
        }

        public MeetupPDPArguments build() {
            String missing = "";
            if (this.f8467id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_MeetupPDPArguments(this.f8467id);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_MeetupPDPArguments(Long id) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f8466id = id;
    }

    /* renamed from: id */
    public Long mo9028id() {
        return this.f8466id;
    }

    public String toString() {
        return "MeetupPDPArguments{id=" + this.f8466id + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MeetupPDPArguments)) {
            return false;
        }
        return this.f8466id.equals(((MeetupPDPArguments) o).mo9028id());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.f8466id.hashCode();
    }
}
