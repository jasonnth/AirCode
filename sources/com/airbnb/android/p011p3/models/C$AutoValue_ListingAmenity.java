package com.airbnb.android.p011p3.models;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingAmenity reason: invalid class name */
abstract class C$AutoValue_ListingAmenity extends ListingAmenity {
    private final String description;

    /* renamed from: id */
    private final long f10652id;
    private final String name;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingAmenity$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.ListingAmenity.Builder {
        private String description;

        /* renamed from: id */
        private Long f10653id;
        private String name;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.p011p3.models.ListingAmenity.Builder mo11029id(long id) {
            this.f10653id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingAmenity.Builder name(String name2) {
            if (name2 == null) {
                throw new NullPointerException("Null name");
            }
            this.name = name2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingAmenity.Builder description(String description2) {
            this.description = description2;
            return this;
        }

        public ListingAmenity build() {
            String missing = "";
            if (this.f10653id == null) {
                missing = missing + " id";
            }
            if (this.name == null) {
                missing = missing + " name";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ListingAmenity(this.f10653id.longValue(), this.name, this.description);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ListingAmenity(long id, String name2, String description2) {
        this.f10652id = id;
        if (name2 == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name2;
        this.description = description2;
    }

    /* renamed from: id */
    public long mo11025id() {
        return this.f10652id;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public String toString() {
        return "ListingAmenity{id=" + this.f10652id + ", name=" + this.name + ", description=" + this.description + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingAmenity)) {
            return false;
        }
        ListingAmenity that = (ListingAmenity) o;
        if (this.f10652id == that.mo11025id() && this.name.equals(that.name())) {
            if (this.description == null) {
                if (that.description() == null) {
                    return true;
                }
            } else if (this.description.equals(that.description())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((int) (((long) (1 * 1000003)) ^ ((this.f10652id >>> 32) ^ this.f10652id))) * 1000003) ^ this.name.hashCode()) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode());
    }
}
