package com.airbnb.android.core.businesstravel.models;

/* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessEntityGroup reason: invalid class name */
abstract class C$AutoValue_BusinessEntityGroup extends BusinessEntityGroup {

    /* renamed from: id */
    private final long f8432id;
    private final String paymentMethodDisplayName;

    /* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessEntityGroup$Builder */
    static final class Builder extends com.airbnb.android.core.businesstravel.models.BusinessEntityGroup.Builder {

        /* renamed from: id */
        private Long f8433id;
        private String paymentMethodDisplayName;

        Builder() {
        }

        public com.airbnb.android.core.businesstravel.models.BusinessEntityGroup.Builder setPaymentMethodDisplayName(String paymentMethodDisplayName2) {
            if (paymentMethodDisplayName2 == null) {
                throw new NullPointerException("Null paymentMethodDisplayName");
            }
            this.paymentMethodDisplayName = paymentMethodDisplayName2;
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessEntityGroup.Builder setId(long id) {
            this.f8433id = Long.valueOf(id);
            return this;
        }

        public BusinessEntityGroup build() {
            String missing = "";
            if (this.paymentMethodDisplayName == null) {
                missing = missing + " paymentMethodDisplayName";
            }
            if (this.f8433id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessEntityGroup(this.paymentMethodDisplayName, this.f8433id.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessEntityGroup(String paymentMethodDisplayName2, long id) {
        if (paymentMethodDisplayName2 == null) {
            throw new NullPointerException("Null paymentMethodDisplayName");
        }
        this.paymentMethodDisplayName = paymentMethodDisplayName2;
        this.f8432id = id;
    }

    public String getPaymentMethodDisplayName() {
        return this.paymentMethodDisplayName;
    }

    public long getId() {
        return this.f8432id;
    }

    public String toString() {
        return "BusinessEntityGroup{paymentMethodDisplayName=" + this.paymentMethodDisplayName + ", id=" + this.f8432id + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessEntityGroup)) {
            return false;
        }
        BusinessEntityGroup that = (BusinessEntityGroup) o;
        if (!this.paymentMethodDisplayName.equals(that.getPaymentMethodDisplayName()) || this.f8432id != that.getId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((1 * 1000003) ^ this.paymentMethodDisplayName.hashCode()) * 1000003)) ^ ((this.f8432id >>> 32) ^ this.f8432id));
    }
}
