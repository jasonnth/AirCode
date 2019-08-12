package com.airbnb.android.core.businesstravel.models;

import java.util.List;

/* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelReadyFilterCriteria reason: invalid class name */
abstract class C$AutoValue_BusinessTravelReadyFilterCriteria extends BusinessTravelReadyFilterCriteria {
    private final List<Integer> amenitiesToFilterOut;
    private final List<Integer> hostingAmenities;
    private final List<Integer> listingTypes;
    private final List<String> roomTypes;

    /* renamed from: com.airbnb.android.core.businesstravel.models.$AutoValue_BusinessTravelReadyFilterCriteria$Builder */
    static final class Builder extends com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria.Builder {
        private List<Integer> amenitiesToFilterOut;
        private List<Integer> hostingAmenities;
        private List<Integer> listingTypes;
        private List<String> roomTypes;

        Builder() {
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria.Builder amenitiesToFilterOut(List<Integer> amenitiesToFilterOut2) {
            this.amenitiesToFilterOut = amenitiesToFilterOut2;
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria.Builder hostingAmenities(List<Integer> hostingAmenities2) {
            this.hostingAmenities = hostingAmenities2;
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria.Builder listingTypes(List<Integer> listingTypes2) {
            this.listingTypes = listingTypes2;
            return this;
        }

        public com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria.Builder roomTypes(List<String> roomTypes2) {
            this.roomTypes = roomTypes2;
            return this;
        }

        public BusinessTravelReadyFilterCriteria build() {
            return new AutoValue_BusinessTravelReadyFilterCriteria(this.amenitiesToFilterOut, this.hostingAmenities, this.listingTypes, this.roomTypes);
        }
    }

    C$AutoValue_BusinessTravelReadyFilterCriteria(List<Integer> amenitiesToFilterOut2, List<Integer> hostingAmenities2, List<Integer> listingTypes2, List<String> roomTypes2) {
        this.amenitiesToFilterOut = amenitiesToFilterOut2;
        this.hostingAmenities = hostingAmenities2;
        this.listingTypes = listingTypes2;
        this.roomTypes = roomTypes2;
    }

    public List<Integer> amenitiesToFilterOut() {
        return this.amenitiesToFilterOut;
    }

    public List<Integer> hostingAmenities() {
        return this.hostingAmenities;
    }

    public List<Integer> listingTypes() {
        return this.listingTypes;
    }

    public List<String> roomTypes() {
        return this.roomTypes;
    }

    public String toString() {
        return "BusinessTravelReadyFilterCriteria{amenitiesToFilterOut=" + this.amenitiesToFilterOut + ", hostingAmenities=" + this.hostingAmenities + ", listingTypes=" + this.listingTypes + ", roomTypes=" + this.roomTypes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessTravelReadyFilterCriteria)) {
            return false;
        }
        BusinessTravelReadyFilterCriteria that = (BusinessTravelReadyFilterCriteria) o;
        if (this.amenitiesToFilterOut != null ? this.amenitiesToFilterOut.equals(that.amenitiesToFilterOut()) : that.amenitiesToFilterOut() == null) {
            if (this.hostingAmenities != null ? this.hostingAmenities.equals(that.hostingAmenities()) : that.hostingAmenities() == null) {
                if (this.listingTypes != null ? this.listingTypes.equals(that.listingTypes()) : that.listingTypes() == null) {
                    if (this.roomTypes == null) {
                        if (that.roomTypes() == null) {
                            return true;
                        }
                    } else if (this.roomTypes.equals(that.roomTypes())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ (this.amenitiesToFilterOut == null ? 0 : this.amenitiesToFilterOut.hashCode())) * 1000003) ^ (this.hostingAmenities == null ? 0 : this.hostingAmenities.hashCode())) * 1000003) ^ (this.listingTypes == null ? 0 : this.listingTypes.hashCode())) * 1000003;
        if (this.roomTypes != null) {
            i = this.roomTypes.hashCode();
        }
        return h ^ i;
    }
}
