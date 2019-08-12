package com.apollographql.apollo.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Error {
    private final Map<String, Object> customAttributes;
    private final List<C3105Location> locations;
    private final String message;

    /* renamed from: com.apollographql.apollo.api.Error$Location */
    public static class C3105Location {
        private final long column;
        private final long line;

        public C3105Location(long line2, long column2) {
            this.line = line2;
            this.column = column2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            C3105Location location = (C3105Location) o;
            if (this.line != location.line) {
                return false;
            }
            if (this.column != location.column) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((int) (this.line ^ (this.line >>> 32))) * 31) + ((int) (this.column ^ (this.column >>> 32)));
        }

        public String toString() {
            return "Location{line=" + this.line + ", column=" + this.column + '}';
        }
    }

    public Error(String message2, List<C3105Location> locations2, Map<String, Object> customAttributes2) {
        Map<String, Object> emptyMap;
        this.message = message2;
        this.locations = locations2 != null ? Collections.unmodifiableList(locations2) : Collections.emptyList();
        if (customAttributes2 != null) {
            emptyMap = Collections.unmodifiableMap(customAttributes2);
        } else {
            emptyMap = Collections.emptyMap();
        }
        this.customAttributes = emptyMap;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Error)) {
            return false;
        }
        Error error = (Error) o;
        if (this.message != null) {
            if (!this.message.equals(error.message)) {
                return false;
            }
        } else if (error.message != null) {
            return false;
        }
        if (this.locations.equals(error.locations)) {
            return this.customAttributes.equals(error.customAttributes);
        }
        return false;
    }

    public int hashCode() {
        return ((((this.message != null ? this.message.hashCode() : 0) * 31) + this.locations.hashCode()) * 31) + this.customAttributes.hashCode();
    }

    public String toString() {
        return "Error{message='" + this.message + '\'' + ", locations=" + this.locations + ", customAttributes=" + this.customAttributes + '}';
    }
}
