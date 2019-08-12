package com.airbnb.android.core.businesstravel.models;

/* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelReadyData reason: invalid class name */
abstract class C$AutoValue_BusinessTravelReadyData extends BusinessTravelReadyData {
    private final BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria;

    /* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelReadyData$Builder */
    static final class Builder extends com.airbnb.android.core.businesstravel.models.BusinessTravelReadyData.Builder {
        private BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria;

        Builder() {
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelReadyData.Builder businessTravelReadyFilterCriteria(BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria2) {
            if (businessTravelReadyFilterCriteria2 == null) {
                throw new NullPointerException("Null businessTravelReadyFilterCriteria");
            }
            this.businessTravelReadyFilterCriteria = businessTravelReadyFilterCriteria2;
            return this;
        }

        public BusinessTravelReadyData build() {
            String missing = "";
            if (this.businessTravelReadyFilterCriteria == null) {
                missing = missing + " businessTravelReadyFilterCriteria";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelReadyData(this.businessTravelReadyFilterCriteria);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelReadyData(BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria2) {
        if (businessTravelReadyFilterCriteria2 == null) {
            throw new NullPointerException("Null businessTravelReadyFilterCriteria");
        }
        this.businessTravelReadyFilterCriteria = businessTravelReadyFilterCriteria2;
    }

    public BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria() {
        return this.businessTravelReadyFilterCriteria;
    }

    public String toString() {
        return "BusinessTravelReadyData{businessTravelReadyFilterCriteria=" + this.businessTravelReadyFilterCriteria + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessTravelReadyData)) {
            return false;
        }
        return this.businessTravelReadyFilterCriteria.equals(((BusinessTravelReadyData) o).businessTravelReadyFilterCriteria());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.businessTravelReadyFilterCriteria.hashCode();
    }
}
