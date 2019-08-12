package com.airbnb.android.core.businesstravel.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessTravelReadyData implements Parcelable {

    public static abstract class Builder {
        public abstract BusinessTravelReadyData build();

        @JsonProperty("filter_criteria")
        public abstract Builder businessTravelReadyFilterCriteria(BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria);
    }

    public abstract BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria();
}
