package com.airbnb.android.p011p3.models;

import java.util.List;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_HomeLayout reason: invalid class name */
abstract class C$AutoValue_HomeLayout extends HomeLayout {
    private final List<Floor> floors;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_HomeLayout$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.HomeLayout.Builder {
        private List<Floor> floors;

        Builder() {
        }

        public com.airbnb.android.p011p3.models.HomeLayout.Builder floors(List<Floor> floors2) {
            if (floors2 == null) {
                throw new NullPointerException("Null floors");
            }
            this.floors = floors2;
            return this;
        }

        public HomeLayout build() {
            String missing = "";
            if (this.floors == null) {
                missing = missing + " floors";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomeLayout(this.floors);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomeLayout(List<Floor> floors2) {
        if (floors2 == null) {
            throw new NullPointerException("Null floors");
        }
        this.floors = floors2;
    }

    public List<Floor> floors() {
        return this.floors;
    }

    public String toString() {
        return "HomeLayout{floors=" + this.floors + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomeLayout)) {
            return false;
        }
        return this.floors.equals(((HomeLayout) o).floors());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.floors.hashCode();
    }
}
