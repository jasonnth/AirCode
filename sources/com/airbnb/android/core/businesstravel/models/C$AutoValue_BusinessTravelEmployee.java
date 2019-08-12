package com.airbnb.android.core.businesstravel.models;

/* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelEmployee reason: invalid class name */
abstract class C$AutoValue_BusinessTravelEmployee extends BusinessTravelEmployee {
    private final boolean admin;
    private final long businessEntityId;
    private final String email;

    /* renamed from: id */
    private final long f8434id;
    private final boolean thirdPartyBookable;
    private final long userId;
    private final boolean verified;

    /* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelEmployee$Builder */
    static final class Builder extends com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder {
        private Boolean admin;
        private Long businessEntityId;
        private String email;

        /* renamed from: id */
        private Long f8435id;
        private Boolean thirdPartyBookable;
        private Long userId;
        private Boolean verified;

        Builder() {
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setEmail(String email2) {
            if (email2 == null) {
                throw new NullPointerException("Null email");
            }
            this.email = email2;
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setThirdPartyBookable(boolean thirdPartyBookable2) {
            this.thirdPartyBookable = Boolean.valueOf(thirdPartyBookable2);
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setVerified(boolean verified2) {
            this.verified = Boolean.valueOf(verified2);
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setAdmin(boolean admin2) {
            this.admin = Boolean.valueOf(admin2);
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setId(long id) {
            this.f8435id = Long.valueOf(id);
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setUserId(long userId2) {
            this.userId = Long.valueOf(userId2);
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee.Builder setBusinessEntityId(long businessEntityId2) {
            this.businessEntityId = Long.valueOf(businessEntityId2);
            return this;
        }

        public BusinessTravelEmployee build() {
            String missing = "";
            if (this.email == null) {
                missing = missing + " email";
            }
            if (this.thirdPartyBookable == null) {
                missing = missing + " thirdPartyBookable";
            }
            if (this.verified == null) {
                missing = missing + " verified";
            }
            if (this.admin == null) {
                missing = missing + " admin";
            }
            if (this.f8435id == null) {
                missing = missing + " id";
            }
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (this.businessEntityId == null) {
                missing = missing + " businessEntityId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelEmployee(this.email, this.thirdPartyBookable.booleanValue(), this.verified.booleanValue(), this.admin.booleanValue(), this.f8435id.longValue(), this.userId.longValue(), this.businessEntityId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelEmployee(String email2, boolean thirdPartyBookable2, boolean verified2, boolean admin2, long id, long userId2, long businessEntityId2) {
        if (email2 == null) {
            throw new NullPointerException("Null email");
        }
        this.email = email2;
        this.thirdPartyBookable = thirdPartyBookable2;
        this.verified = verified2;
        this.admin = admin2;
        this.f8434id = id;
        this.userId = userId2;
        this.businessEntityId = businessEntityId2;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isThirdPartyBookable() {
        return this.thirdPartyBookable;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public long getId() {
        return this.f8434id;
    }

    public long getUserId() {
        return this.userId;
    }

    public long getBusinessEntityId() {
        return this.businessEntityId;
    }

    public String toString() {
        return "BusinessTravelEmployee{email=" + this.email + ", thirdPartyBookable=" + this.thirdPartyBookable + ", verified=" + this.verified + ", admin=" + this.admin + ", id=" + this.f8434id + ", userId=" + this.userId + ", businessEntityId=" + this.businessEntityId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessTravelEmployee)) {
            return false;
        }
        BusinessTravelEmployee that = (BusinessTravelEmployee) o;
        if (this.email.equals(that.getEmail()) && this.thirdPartyBookable == that.isThirdPartyBookable() && this.verified == that.isVerified() && this.admin == that.isAdmin() && this.f8434id == that.getId() && this.userId == that.getUserId() && this.businessEntityId == that.getBusinessEntityId()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int h = ((((1 * 1000003) ^ this.email.hashCode()) * 1000003) ^ (this.thirdPartyBookable ? 1231 : 1237)) * 1000003;
        if (this.verified) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (!this.admin) {
            i2 = 1237;
        }
        return (int) (((long) (((int) (((long) (((int) (((long) ((h2 ^ i2) * 1000003)) ^ ((this.f8434id >>> 32) ^ this.f8434id))) * 1000003)) ^ ((this.userId >>> 32) ^ this.userId))) * 1000003)) ^ ((this.businessEntityId >>> 32) ^ this.businessEntityId));
    }
}
