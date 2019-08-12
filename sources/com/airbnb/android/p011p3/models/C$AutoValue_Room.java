package com.airbnb.android.p011p3.models;

import java.util.List;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_Room reason: invalid class name */
abstract class C$AutoValue_Room extends Room {
    private final List<RoomDetail> details;
    private final List<String> highlights;

    /* renamed from: id */
    private final long f10654id;
    private final String name;
    private final List<RoomPhoto> photos;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_Room$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.Room.Builder {
        private List<RoomDetail> details;
        private List<String> highlights;

        /* renamed from: id */
        private Long f10655id;
        private String name;
        private List<RoomPhoto> photos;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.p011p3.models.Room.Builder mo11092id(long id) {
            this.f10655id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.p011p3.models.Room.Builder name(String name2) {
            if (name2 == null) {
                throw new NullPointerException("Null name");
            }
            this.name = name2;
            return this;
        }

        public com.airbnb.android.p011p3.models.Room.Builder highlights(List<String> highlights2) {
            if (highlights2 == null) {
                throw new NullPointerException("Null highlights");
            }
            this.highlights = highlights2;
            return this;
        }

        public com.airbnb.android.p011p3.models.Room.Builder photos(List<RoomPhoto> photos2) {
            if (photos2 == null) {
                throw new NullPointerException("Null photos");
            }
            this.photos = photos2;
            return this;
        }

        public com.airbnb.android.p011p3.models.Room.Builder details(List<RoomDetail> details2) {
            if (details2 == null) {
                throw new NullPointerException("Null details");
            }
            this.details = details2;
            return this;
        }

        public Room build() {
            String missing = "";
            if (this.f10655id == null) {
                missing = missing + " id";
            }
            if (this.name == null) {
                missing = missing + " name";
            }
            if (this.highlights == null) {
                missing = missing + " highlights";
            }
            if (this.photos == null) {
                missing = missing + " photos";
            }
            if (this.details == null) {
                missing = missing + " details";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Room(this.f10655id.longValue(), this.name, this.highlights, this.photos, this.details);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Room(long id, String name2, List<String> highlights2, List<RoomPhoto> photos2, List<RoomDetail> details2) {
        this.f10654id = id;
        if (name2 == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name2;
        if (highlights2 == null) {
            throw new NullPointerException("Null highlights");
        }
        this.highlights = highlights2;
        if (photos2 == null) {
            throw new NullPointerException("Null photos");
        }
        this.photos = photos2;
        if (details2 == null) {
            throw new NullPointerException("Null details");
        }
        this.details = details2;
    }

    /* renamed from: id */
    public long mo11086id() {
        return this.f10654id;
    }

    public String name() {
        return this.name;
    }

    public List<String> highlights() {
        return this.highlights;
    }

    public List<RoomPhoto> photos() {
        return this.photos;
    }

    public List<RoomDetail> details() {
        return this.details;
    }

    public String toString() {
        return "Room{id=" + this.f10654id + ", name=" + this.name + ", highlights=" + this.highlights + ", photos=" + this.photos + ", details=" + this.details + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room that = (Room) o;
        if (this.f10654id != that.mo11086id() || !this.name.equals(that.name()) || !this.highlights.equals(that.highlights()) || !this.photos.equals(that.photos()) || !this.details.equals(that.details())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((int) (((long) (1 * 1000003)) ^ ((this.f10654id >>> 32) ^ this.f10654id))) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.highlights.hashCode()) * 1000003) ^ this.photos.hashCode()) * 1000003) ^ this.details.hashCode();
    }
}
