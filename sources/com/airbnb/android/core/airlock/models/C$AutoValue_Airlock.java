package com.airbnb.android.core.airlock.models;

import java.util.List;

/* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_Airlock reason: invalid class name */
abstract class C$AutoValue_Airlock extends Airlock {
    private final String actionName;
    private final String firstName;
    private final FrictionInitData frictionInitData;
    private final List<List<AirlockFrictionType>> frictions;

    /* renamed from: id */
    private final long f8424id;
    private final long userId;

    /* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_Airlock$Builder */
    static final class Builder extends com.airbnb.android.core.airlock.models.Airlock.Builder {
        private String actionName;
        private String firstName;
        private FrictionInitData frictionInitData;
        private List<List<AirlockFrictionType>> frictions;

        /* renamed from: id */
        private Long f8425id;
        private Long userId;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.core.airlock.models.Airlock.Builder mo8260id(long id) {
            this.f8425id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.core.airlock.models.Airlock.Builder userId(long userId2) {
            this.userId = Long.valueOf(userId2);
            return this;
        }

        public com.airbnb.android.core.airlock.models.Airlock.Builder actionName(String actionName2) {
            this.actionName = actionName2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.Airlock.Builder firstName(String firstName2) {
            if (firstName2 == null) {
                throw new NullPointerException("Null firstName");
            }
            this.firstName = firstName2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.Airlock.Builder frictionInitData(FrictionInitData frictionInitData2) {
            if (frictionInitData2 == null) {
                throw new NullPointerException("Null frictionInitData");
            }
            this.frictionInitData = frictionInitData2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.Airlock.Builder frictions(List<List<AirlockFrictionType>> frictions2) {
            if (frictions2 == null) {
                throw new NullPointerException("Null frictions");
            }
            this.frictions = frictions2;
            return this;
        }

        public Airlock build() {
            String missing = "";
            if (this.f8425id == null) {
                missing = missing + " id";
            }
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (this.firstName == null) {
                missing = missing + " firstName";
            }
            if (this.frictionInitData == null) {
                missing = missing + " frictionInitData";
            }
            if (this.frictions == null) {
                missing = missing + " frictions";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Airlock(this.f8425id.longValue(), this.userId.longValue(), this.actionName, this.firstName, this.frictionInitData, this.frictions);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Airlock(long id, long userId2, String actionName2, String firstName2, FrictionInitData frictionInitData2, List<List<AirlockFrictionType>> frictions2) {
        this.f8424id = id;
        this.userId = userId2;
        this.actionName = actionName2;
        if (firstName2 == null) {
            throw new NullPointerException("Null firstName");
        }
        this.firstName = firstName2;
        if (frictionInitData2 == null) {
            throw new NullPointerException("Null frictionInitData");
        }
        this.frictionInitData = frictionInitData2;
        if (frictions2 == null) {
            throw new NullPointerException("Null frictions");
        }
        this.frictions = frictions2;
    }

    /* renamed from: id */
    public long mo8253id() {
        return this.f8424id;
    }

    public long userId() {
        return this.userId;
    }

    public String actionName() {
        return this.actionName;
    }

    public String firstName() {
        return this.firstName;
    }

    public FrictionInitData frictionInitData() {
        return this.frictionInitData;
    }

    public List<List<AirlockFrictionType>> frictions() {
        return this.frictions;
    }

    public String toString() {
        return "Airlock{id=" + this.f8424id + ", userId=" + this.userId + ", actionName=" + this.actionName + ", firstName=" + this.firstName + ", frictionInitData=" + this.frictionInitData + ", frictions=" + this.frictions + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Airlock)) {
            return false;
        }
        Airlock that = (Airlock) o;
        if (this.f8424id != that.mo8253id() || this.userId != that.userId() || (this.actionName != null ? !this.actionName.equals(that.actionName()) : that.actionName() != null) || !this.firstName.equals(that.firstName()) || !this.frictionInitData.equals(that.frictionInitData()) || !this.frictions.equals(that.frictions())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((int) (((long) (((int) (((long) (1 * 1000003)) ^ ((this.f8424id >>> 32) ^ this.f8424id))) * 1000003)) ^ ((this.userId >>> 32) ^ this.userId))) * 1000003) ^ (this.actionName == null ? 0 : this.actionName.hashCode())) * 1000003) ^ this.firstName.hashCode()) * 1000003) ^ this.frictionInitData.hashCode()) * 1000003) ^ this.frictions.hashCode();
    }
}
