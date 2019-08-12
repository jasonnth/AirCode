package com.airbnb.android.core.businesstravel.models;

import android.os.Parcelable;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.FluentIterable;
import java.util.Collections;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessTravelReadyFilterCriteria implements Parcelable {

    public static abstract class Builder {
        @JsonProperty("amenities_to_filter_out")
        public abstract Builder amenitiesToFilterOut(List<Integer> list);

        public abstract BusinessTravelReadyFilterCriteria build();

        @JsonProperty("hosting_amenities")
        public abstract Builder hostingAmenities(List<Integer> list);

        @JsonProperty("listing_types")
        public abstract Builder listingTypes(List<Integer> list);

        @JsonProperty("room_types")
        public abstract Builder roomTypes(List<String> list);
    }

    public abstract List<Integer> amenitiesToFilterOut();

    public abstract List<Integer> hostingAmenities();

    public abstract List<Integer> listingTypes();

    public abstract List<String> roomTypes();

    public static Builder builder() {
        return new Builder();
    }

    public List<Amenity> getHostingAmenitiesFromIds() {
        if (hostingAmenities() != null) {
            return FluentIterable.from((Iterable<E>) hostingAmenities()).transform(BusinessTravelReadyFilterCriteria$$Lambda$1.lambdaFactory$()).filter(BusinessTravelReadyFilterCriteria$$Lambda$2.lambdaFactory$()).toList();
        }
        return Collections.emptyList();
    }

    static /* synthetic */ boolean lambda$getHostingAmenitiesFromIds$1(Amenity amenity) {
        return amenity != null;
    }

    public List<Amenity> getAmenitiesToFilterOutFromIds() {
        if (amenitiesToFilterOut() != null) {
            return FluentIterable.from((Iterable<E>) amenitiesToFilterOut()).transform(BusinessTravelReadyFilterCriteria$$Lambda$3.lambdaFactory$()).filter(BusinessTravelReadyFilterCriteria$$Lambda$4.lambdaFactory$()).toList();
        }
        return Collections.emptyList();
    }

    static /* synthetic */ boolean lambda$getAmenitiesToFilterOutFromIds$3(Amenity amenity) {
        return amenity != null;
    }

    public List<C6120RoomType> getRoomTypesFromServerKeys() {
        if (roomTypes() != null) {
            return FluentIterable.from((Iterable<E>) roomTypes()).transform(BusinessTravelReadyFilterCriteria$$Lambda$5.lambdaFactory$()).toList();
        }
        return Collections.emptyList();
    }

    public List<PropertyType> getPropertyTypesFromIds() {
        if (listingTypes() != null) {
            return FluentIterable.from((Iterable<E>) listingTypes()).transform(BusinessTravelReadyFilterCriteria$$Lambda$6.lambdaFactory$()).toSet().asList();
        }
        return Collections.emptyList();
    }
}
