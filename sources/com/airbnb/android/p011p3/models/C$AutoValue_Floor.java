package com.airbnb.android.p011p3.models;

import java.util.List;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_Floor reason: invalid class name */
abstract class C$AutoValue_Floor extends Floor {

    /* renamed from: id */
    private final long f10650id;
    private final String name;
    private final List<Room> rooms;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_Floor$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.Floor.Builder {

        /* renamed from: id */
        private Long f10651id;
        private String name;
        private List<Room> rooms;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.p011p3.models.Floor.Builder mo11018id(long id) {
            this.f10651id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.p011p3.models.Floor.Builder name(String name2) {
            if (name2 == null) {
                throw new NullPointerException("Null name");
            }
            this.name = name2;
            return this;
        }

        public com.airbnb.android.p011p3.models.Floor.Builder rooms(List<Room> rooms2) {
            if (rooms2 == null) {
                throw new NullPointerException("Null rooms");
            }
            this.rooms = rooms2;
            return this;
        }

        public Floor build() {
            String missing = "";
            if (this.f10651id == null) {
                missing = missing + " id";
            }
            if (this.name == null) {
                missing = missing + " name";
            }
            if (this.rooms == null) {
                missing = missing + " rooms";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Floor(this.f10651id.longValue(), this.name, this.rooms);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Floor(long id, String name2, List<Room> rooms2) {
        this.f10650id = id;
        if (name2 == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name2;
        if (rooms2 == null) {
            throw new NullPointerException("Null rooms");
        }
        this.rooms = rooms2;
    }

    /* renamed from: id */
    public long mo11014id() {
        return this.f10650id;
    }

    public String name() {
        return this.name;
    }

    public List<Room> rooms() {
        return this.rooms;
    }

    public String toString() {
        return "Floor{id=" + this.f10650id + ", name=" + this.name + ", rooms=" + this.rooms + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Floor)) {
            return false;
        }
        Floor that = (Floor) o;
        if (this.f10650id != that.mo11014id() || !this.name.equals(that.name()) || !this.rooms.equals(that.rooms())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((int) (((long) (1 * 1000003)) ^ ((this.f10650id >>> 32) ^ this.f10650id))) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.rooms.hashCode();
    }
}
